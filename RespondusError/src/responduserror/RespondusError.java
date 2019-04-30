/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responduserror;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agjackso
 */
public class RespondusError {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            PrintWriter out = new PrintWriter("errorNoHtml.txt");
            out.println("Type: MC");
            out.println();
            out.println("Title: Error");
            out.print("1) ");
            
            char ch = 'a';
            
            for (int i = 4; i < 1030; i++) {
                if (i < 1000) {
                    if (i % 10 == 0)
                        out.print(" ");
                    else
                        out.print(ch);
                    if (i % 10 != 0)
                        ch++;
                    if (ch >= 'z')
                        ch = 'a';
                } else {
                    out.print("" + i % 10);
                }
            }
            
            out.println("");
                        out.println();
            out.println("*a) A");
                        out.println();
            out.println("b) B");            
            out.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Error: " + ex);
        }
        
    }
}

/*
package responduserror;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RespondusError {


    public static void main(String[] args) {
        try {
            PrintWriter out = new PrintWriter("error2.txt");
            out.println("Type: MC");
            out.println();
            out.println("Title: Error");
            out.print("1) [HTML]");
            
            for (int i = 10; i < 1030; i++) {
                if (i < 1000) {
                    if (i % 10 == 0)
                        out.print((char) ((65 + i) % 26));
                    else
                        out.print(".");
                } else {
                    out.print("" + i % 10);
                }
            }
            
            out.println("[/HTML]");
                        out.println();
            out.println("*a) [HTML]A[/HTML]");
                        out.println();
            out.println("b) [HTML]B[/HTML]");            
            out.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Error: " + ex);
        }
        
    }
}
*/

