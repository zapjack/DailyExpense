/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emilocate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author agjackso
 */
public class EmiLocate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        if (args.length < 5) {
            System.out.println("Usage:  java EmiLocate filename row col padding");
            System.exit(0);
        }
        String filename = args[1].trim();
        int targetRow = Integer.parseInt(args[2].trim());
        int targetCol = Integer.parseInt(args[3].trim());
        int padding = Integer.parseInt(args[4].trim());
        
        Scanner scan = new Scanner(new File(filename));
        int num = 0;
        
        while (scan.hasNext()) {
            String line = scan.nextLine();
            int inRange = Math.abs(num - targetRow);
            
            if (inRange <= padding) 
                print(num, line, targetCol, padding);
            
        }
    }
    
    public static void print(int lineNum, String line, int target, int padding) {
        String [] tokens = line.split(" ");
        
        System.out.printf("%10d: ", lineNum);
        
        for (int i = target - padding; i < target + padding; i++)
            System.out.printf("%6d ", tokens[i]);
        
        System.out.println();
    }
}
