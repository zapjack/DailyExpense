package sudoku;

import java.util.Random;

/**
 *
 * @author agjackso
 */
public class Square {
    public static final int ROWS = 3;
    public static final int COLS = 3;
    public static final int NUM_VALUES = ROWS * COLS;
    
    Random rand = new Random();
    
    private int [] filled = new int[NUM_VALUES]; // 0 is not filled, 1 is filled
    private Cell [][] cell;

    public Square() {
        cell = new Cell[ROWS][COLS];
        
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                cell[i][j] = new Cell(); 
    }
    
    public boolean fill(int value, int row, int col) {
        if (filled[value] == 1)
            return false;   
        
        return true;
    }
    
    @Override
    public String toString() {
        String s = "";
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++)
                s += cell[i][j].toString() + " ";    
            s += "\n";
        }
            
        return s;
    }   
}
