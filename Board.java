import org.w3c.dom.Node;

public class Board {
    public int[][] intGrid;
    public char[][] charGrid;
    int size;

    public Board(int s) {
        size = s;
        intGrid = new int[size][size];
        charGrid = new char[size][size];
    }

    public void setBoard(int[][] iG, char[][] cG) {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                intGrid[r][c] = iG[r][c];
                charGrid[r][c] = cG[r][c];
            }
        }
    }

    public String toString() {
        String s = "  ";
        for (int i = 0; i < intGrid.length; i++) {
            if (i < 10) {
                s += " ";
            }
            s += Integer.toString(i);
            s += " ";
        }
        s += "\n";

        for (int r = 0; r < intGrid.length; r++) {
            s += Integer.toString(r);
            if (r < 10) {
                s += " ";
            }
            for (int c = 0; c < intGrid.length; c++) {
                s += "[";
                if (intGrid[r][c] != 0) {
                    s += Integer.toString(intGrid[r][c]);
                } 
                else if (charGrid[r][c] == 0) {
                    s += " ";
                } 
                else {
                    s += charGrid[r][c];
                }
                s += "]";
            }
            s += "\n";
        }
        return s;
    }

}