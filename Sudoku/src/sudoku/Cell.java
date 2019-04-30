package sudoku;

/**
 *
 * @author agjackso
 */
public class Cell {
    public static final int EMPTY = -1;
    private int value;

    public Cell() {
        this(EMPTY);
    }
    
    public Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (this.value == Cell.EMPTY)
            this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.value;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%2d", value);
    }
}
