/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertexceltohtml;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.JOptionPane;

/**
 *
 * @author agjackso
 */
public class ConvertExcelToHTML {
    public static final String END_OF_COURSES_MARKER = "Tot.";  
    
    public static final String [] CAMPUS = {"Auburn Hills", "Highland Lakes", "Orchard Ridge", "Royal Oak", "Southfield", "OnLine"};
    public static final String [] CAMPUS_ABBREV = {"AH", "HL", "OR", "RO", "SF", "OL"};
    public static final String [] TIME = {"Saturday", "Morning", "Afternoon", "Evening" };
    public static final String [] SEMESTER = { "Fall", "Winter", "Summer" };
    public static final int START_YEAR = 2017;
    public static final String [] SEM_YEAR = { SEMESTER[0] + " " + START_YEAR, SEMESTER[1] + " " + (START_YEAR + 1), SEMESTER[2] + " " + (START_YEAR + 1) };
    
    public static final String INDENT = "   ";
    public static final String EOL = "\n";
    
    public static final String OUTFILE = "annual.html";
    public static final String INFILE = "annual.csv";
    public static final int SKIP_LINES = 4;
    
    public static PrintWriter out = null;
    public static Scanner in = null;
    
    public static void main(String[] args) {
        try {
            out = new PrintWriter(OUTFILE);
            in = new Scanner(new File(INFILE));
            
            startDocument();
            createNavTable();
            createSemesterTables();
            endDocument();    
            
            in.close();
            out.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error processing");
        }

    }

    private static void startDocument() {
        out.printf("<html><head><style>table {width:50%%; } td {text-align: center; }</style></head><body>%s", EOL);
    }

    private static void createNavTable() {
        out.printf("<table id=\"navTable\">%s%s<tbody>%s", EOL, INDENT, EOL);
        
        out.printf("<tr>%s", EOL);
        out.printf("%s<th scope=\"col\">%s</th>%s", INDENT, SEM_YEAR[0], EOL);
        out.printf("%s<th scope=\"col\">%s</th>%s", INDENT, SEM_YEAR[1], EOL);
        out.printf("%s<th scope=\"col\">%s</th>%s", INDENT, SEM_YEAR[2], EOL);
        out.printf("</tr>%s", EOL);
        
        
        for (int j = 0; j < CAMPUS.length; j++) {
            out.printf("<tr>%s", EOL);
            
            for (int i = 0; i < SEMESTER.length; i++)
                out.printf("<td><a href=\"#%s\">%s</a></td>%s", SEMESTER[i] + CAMPUS_ABBREV[j], CAMPUS[j], EOL);
            
            out.printf("</tr>%s", EOL);
        }
        
        out.printf("%s</tbody>%s</table>%s", INDENT, EOL, EOL);
    }

    private static void createSemesterTables() {
        // Skip first lines of CSV infile
        int courseCount = 0;
        
        TreeMap<String, ArrayList<String>> data  = new TreeMap<>();
        ArrayList<String> list = null;
        
        ArrayList<String> offering = grabCourseOfferings();
        
        for (int m = 0; m < offering.size(); m++) {
            String line = offering.get(courseCount++);
            String [] token = line.split(",");
            int x = 0; // start of line
            String course = token[x++];
            //x++; // skip empty column
            
            for (int i = 0; i < SEMESTER.length; i++) {
                for (int j = 0; j < CAMPUS.length - 1; j++) {
                    if (courseCount == 1) {  // i.e., first course for this semester/campus
                        list = new ArrayList<>();
                        data.put(SEM_YEAR[i] + " " + CAMPUS[j], list);
                    }
                    else {
                        list = data.get(SEM_YEAR[i] + " " + CAMPUS[j]);

                        if (list == null) {
                            JOptionPane.showMessageDialog(null, "ERROR - should always be a TreeMap entry here");
                            System.exit(1);
                        }
                    }
                    
                    boolean noOfferings = true;
                    String oneCourse = "";
                    
                    for (int k = 0; k < TIME.length; k++) {
                        String numOffered = token[x++];
                        
                        if (numOffered.trim().length() != 0)
                            noOfferings = false;
                        
                        oneCourse += "<td>" + numOffered + "</td> ";
                    }
                    
                    if (!noOfferings) {
                        list.add(INDENT + "<tr>" + EOL);
                        list.add(INDENT + INDENT + "<td>CIS " + course + "</td> ");
                        list.add(oneCourse);
                        list.add("</tr>" + EOL);
                    }
                }
                
                x++; // skip empty col
                
                int j = CAMPUS.length - 1;
                if (courseCount == 1) {  // i.e., first course for this semester/campus
                    list = new ArrayList<>();
                    data.put(SEM_YEAR[i] + " " + CAMPUS[j], list);
                }
                else {
                    list = data.get(SEM_YEAR[i] + " " + CAMPUS[j]);

                    if (list == null) {
                        JOptionPane.showMessageDialog(null, "ERROR - should always be a TreeMap entry here");
                        System.exit(1);
                    }
                }

                boolean noOfferings = true;
                String oneCourse = "";


                String numOffered = token[x++];

                if (numOffered.trim().length() != 0)
                    noOfferings = false;

                oneCourse += "<td>" + numOffered + "</td> ";


                if (!noOfferings) {
                    list.add(INDENT + "<tr>" + EOL);
                    list.add(INDENT + INDENT + "<td>CIS " + course + "</td> ");
                    list.add(oneCourse);
                    list.add("</tr>" + EOL);
                }
                
                x++; // skip empty col
            }
        }
        
        out.printf("<br><h2>COURSE OFFERINGS BY SEMESTER</h2>%s", EOL);
        for (int i = 0; i < SEMESTER.length; i++) {
            for (int j = 0; j < CAMPUS.length; j++) {
                out.printf("<h4 id=\"%s\">%s - %s Campus</h4>%s", SEMESTER[i] + CAMPUS_ABBREV[j], SEM_YEAR[i], CAMPUS[j], EOL);
                out.printf("<table border=\"1\">%s%s<tbody>%s", EOL, INDENT, EOL);
                
                out.printf("%s<tr><th scope=\"col\">Course</th>", INDENT);
                
                if (j == CAMPUS.length - 1)
                    out.printf("<th scope=\"col\">Sections</th>");
                else {
                    for (int k = 0; k < TIME.length; k++)
                        out.printf("<th scope=\"col\">%s</th>", TIME[k]);
                }
                
                out.printf("</tr>%s", EOL);
                
                list = data.get(SEM_YEAR[i] + " " + CAMPUS[j]);
                for (String sections : list)
                    out.printf("%s", sections);
                out.printf("%s</tbody>%s</table>%s", INDENT, EOL, EOL);
                
                out.printf("<a href=\"#navTable\">Back to top</a><br>%s", EOL);
            }
        }
    }

    private static void endDocument() {
        out.printf("</body></html>");
    }

    private static ArrayList<String> grabCourseOfferings() {
        for (int skip = 0; skip < SKIP_LINES; skip++)
            in.nextLine();
        
        ArrayList<String> list = new ArrayList<>();
        boolean done = false;
        
        while (!done && in.hasNext()) {
            String line = in.nextLine();
            if (line.contains(END_OF_COURSES_MARKER))
                done = true;
            else
                list.add(line);
        }
        
        return list;
    }
}
