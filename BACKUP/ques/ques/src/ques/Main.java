/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ques;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.*;
import ml.options.*;

/**
 *
 * @author agjackso
 */
public class Main {
   
   public static int TABSTOP = 3;
   public static String IMAGES_LOCAL_HTML = "c:/Users/agjackso/Desktop/aj/content/core/images/";
   public static String IMAGES_KIOWOK = "../assets/imagesQues/";
   public static String CORE_BASE_DIR = "c:/Users/agjackso/Desktop/aj/content/core/";

    /**
     * @param args the command line arguments
     */
	public static void main(String [] args)
	{
        Options opt = new Options(args, 2);
        opt.addSet("main");
        OptionSet set = opt.getSet("main");
        set.addOption("h", Options.Multiplicity.ZERO_OR_ONE);
        set.addOption("help", Options.Multiplicity.ZERO_OR_ONE);
        set.addOption("p", Options.Multiplicity.ZERO_OR_ONE);
        set.addOption("d", Options.Separator.EQUALS, Options.Multiplicity.ONCE);
          
        boolean ok = opt.check("main");
        
        if (!ok) {
            System.err.println("Error with command line format -- ");
            System.err.println("");
            usageMessage();
            System.exit(1);
        }
           
        if (set.isSet("h") || set.isSet("help")) {
            System.err.println("Help requested -- ");
            System.err.println("");
            usageMessage();
            System.exit(1);
        }
  
        Main m = new Main(set);
	}

    static void usageMessage()
    {
       System.err.println("Usage: ");
       System.err.println("  java -jar c:/Users/agjackso/Desktop/aj/bin/ques.jar [-h | -help] -d=<destination> <core_input_file> <output_file>");
       System.err.println("");
       System.err.println("Where:");
       System.err.println("  * \"destination\" is either \"bb8\", \"edu\" (Educator), \"html\" (local HTML viewing), or \"kiowok\".");
       System.err.println("  * \"core_input_file\" is the file in the core area (name is relative to the start of the core area).");
       System.err.println("  * \"output_file\" is created in the current working direcgtory.");
       System.err.println("");
       System.err.println("Example:");
       System.err.println("  java -jar c:/Users/agjackso/Desktop/aj/bin/ques.jar -d=edu java/gui.xml gui.html");
       
       
    }

	public Main(OptionSet set)
	{
	   try
            {
               // BufferedReader br = new BufferedReader(new FileReader(fileIn));
                String fileIn = CORE_BASE_DIR + set.getData().get(0);
                String fileOut  = set.getData().get(1);
                String destination = "?";
                
               // Get the required destination
               if (set.isSet("d"))
                  destination = set.getOption("d").getResultValue(0);
               else {
                   usageMessage();
                   System.exit(1);
               }
                
                DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

                //Configure the factory object
                factory.setValidating(false);
                factory.setNamespaceAware(false);

                DocumentBuilder builder =
                    factory.newDocumentBuilder();

                Document document = builder.parse(
                    new File(fileIn));
                
                if (set.isSet("p")) {
                    System.out.println("The points feature is not implemented yet.");
                }
                else if (destination.trim().equals("d2l"))
                    new D2LWriter(fileOut).writeD2LFile(document);
                else if (destination.trim().equals("edu"))
                    new EduWriter(fileOut).writeEduFile(document);
                else if (destination.trim().equals("bb8"))
                    new BB8Writer(fileOut).writeEduFile(document);
                else if (destination.trim().equals("html"))
                    new HTMLWriter(fileOut, IMAGES_LOCAL_HTML, "Questions").writeHTMLFile(document);
                else if (destination.trim().equals("kiowok"))
                    new HTMLWriter(fileOut, IMAGES_KIOWOK, "Kiowok - Questions").writeHTMLFile(document);
                else
                {
                    usageMessage();
                    System.exit(1);
                }
            }
            catch(Exception e)
            {
               System.out.println("Error: " + e);
            }
	}
}
