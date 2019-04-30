/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base7;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author agjackso
 */
public class MyDigit extends Canvas {
    public static final double WIDTH = 70.0;
    public static final double HEIGHT = 100.0;
    private static final String chars = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.?!:;,0123456789@#$%^*()-_+=<>|/\\";
    public MyDigit(String c) {
        // super(width, height);
        super(WIDTH, HEIGHT);
        drawDigit(c);
    }
    
    public boolean drawDigit(String c) {    
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setFill(Color.LIGHTGREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
          
        if (c == null) 
            return false;
        
        int i = chars.indexOf(c);
        if (i < 0)
            return false;
        
        int x = 46, y = 75;
        int count = 0;
        
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(30, 35, 10, 60);
        gc.strokeRect(30, 35, 10, 60);
        
        while (count < 7) {
            if (i % 2 == 1) {
                gc.strokeLine(x + 7, y, x + 7, y + 15);
            } else {
                gc.strokeOval(x, y, 15, 15);
            }
            if (i > 0)
                i /= 2;
            count++;
//            System.out.println("x: " + x);
            x = nextX(x, count);
//            System.out.println(x);
            y = nextY(y, count);
        }
        
        return true;
    }

    private int nextX(int x, int count) {
        if (count <= 2)
            return x;
        else if (count == 3)
            return 27;
        else 
            return 8;
    }

    private int nextY(int y, int count) {
        if (count <= 3)
            return y - 20;
        else
            return y + 20;
    }
    
}
