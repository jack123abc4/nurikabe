import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        int SIZE = 5;
        int[][] numGrid = new int[SIZE][SIZE];
        char[][] dotGrid = new char[SIZE][SIZE];
        numGrid[1][0] = 2;
        numGrid[3][0] = 2;

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                int n = numGrid[r][c];
                if (n != 0) {
                    n--;
                    for (int roamY = 0 - n; roamY <= n; roamY++) {
                        for (int roamX = 0 - (n - Math.abs(roamY)); roamX <= n - Math.abs(roamY); roamX++) {
                            if (r + roamY >= 0 && r + roamY < SIZE && c + roamX >= 0 && c + roamX < SIZE) {
                                boolean dotCheck = true;
                                for (int dotCheckY = -1; dotCheckY <= 1; dotCheckY++) {
                                    for (int dotCheckX = -1; dotCheckX <= 1; dotCheckX++) {
                                        try {
                                            if (numGrid[r + roamY + dotCheckY][c + roamX + dotCheckX] != 0
                                                    || dotGrid[r + roamY + dotCheckY][c + roamX + dotCheckX] == '*') {
                                                dotCheck = false;
                                            }
                                        } catch (Exception e) {
                                            continue;
                                        }
                                    }
                                }
                                if (dotCheck = true) {
                                    dotGrid[r + roamY][c + roamX] = '*';
                                }
                            }
                        }
                    }

                }
            }
        }

        int[][] numGridOne =   {{ 0 , 0 , 0 , 0 , 0 },
                                { 2 , 0 , 1 , 0 , 0 },
                                { 0 , 2 , 0 , 4 , 0 },
                                { 0 , 0 , 0 , 0 , 0 },
                                { 0 , 0 , 0 , 0 , 7 }};

        char[][] dotGridOne =  {{'*', 0 , 0 , 0 ,'*'},
                                {'*', 0 ,'*', 0 ,'*'},
                                { 0 , 0 , 0 ,'*','*'},
                                {'*','*', 0 , 0 , 0 },
                                {'*','*','*','*','*'}};
        //System.out.println(printGrid(numGridOne, dotGridOne));
        //checkGrid(numGridOne, dotGridOne);
        Board b = new Board(5);
        b.intGrid[2][2] = 5;
        int[] newCoords = {2, 2};
        Node n = new Node(null, newCoords);
        //System.out.println(b);
        ArrayList<Board> bList = searchTile(n, 5, b);
        for (int i = 0; i < bList.size(); i++) {
            System.out.println(bList.get(i));
        }
        
    }

    public static String printGrid(int[][] iGrid, char[][] cGrid) {
        String s = "  ";
        for (int i = 0; i < iGrid.length; i++) {
            if (i < 10) {
                s += " ";
            }
            s += Integer.toString(i);
            s += " ";
        }
        s += "\n";

        for (int r = 0; r < iGrid.length; r++) {
            s += Integer.toString(r);
            if (r < 10) {
                s += " ";
            }
            for (int c = 0; c < iGrid.length; c++) {
                s += "[";
                if (cGrid[r][c] == 0) {
                    s += " ";
                } else if (iGrid[r][c] != 0) {
                    s += Integer.toString(iGrid[r][c]);
                } else {
                    s += cGrid[r][c];
                }
                s += "]";
            }
            s += "\n";
        }
        return s;
    }

    public static boolean checkGrid(int[][] iGrid, char[][] cGrid) {
        boolean valid = true;
        // check for 2x2s
        for (int r = 0; r < iGrid.length - 1; r++) {
            for (int c = 0; c < iGrid.length - 1; c++) {
                if (cGrid[r][c] == 0 && cGrid[r + 1][c] == 0 && cGrid[r][c + 1] == 0 && cGrid[r + 1][c + 1] == 0) {
                    valid = false;
                    System.out.println("2x2 Error - found at " + Integer.toString(c) + ", " +
                    Integer.toString(r));
                }
            }
        }

        // check for contiguity
        ArrayList<int[]> borderCoords = new ArrayList<int[]>();
        for (int r = 0; r < iGrid.length; r++) {
            for (int c = 0; c < iGrid.length; c++) {
                if (cGrid[r][c] == 0) {
                    int[] coords = { r, c };
                    borderCoords.add(coords);
                }
            }
        }

        Node root = new Node(null, borderCoords.get(1));
        root.addToNodeList(root);
        search(root, cGrid);
        /*
        for (int i = 0; i < root.nodeList.size(); i++) {
            System.out.print(i+1);
            System.out.println(": " + root.nodeList.get(i));
        }
        */

        if (root.nodeList.size() != borderCoords.size()) {
            valid = false;
            System.out.println("Contiguity error.");
        }
        
        // valid?
        if (valid == true) {
            System.out.println("No errors, board is valid.");
        }
        return valid;

    }

    public static Node search(Node n, char[][] cGrid) {
        int r = n.coords[0];
        int c = n.coords[1];

        // check up
        if (r > 0 && cGrid[r - 1][c] == 0) {
            boolean isNewNode = true;
            int[] newCoords = { r - 1, c };
            for (int i = 0; i < n.root.nodeList.size(); i++) {
                if (n.root.nodeList.get(i).coords[0] == newCoords[0] && n.root.nodeList.get(i).coords[1] == newCoords[1]) {
                    isNewNode = false;
                }
            }
            if (isNewNode == true) {
                Node newNode = new Node(n, newCoords);
                n.root.addToNodeList(newNode);
                n.addChild(newNode, 'u');
                search(newNode, cGrid);
            }
        }

        // check left
        if (c > 0 && cGrid[r][c-1] == 0) {
            boolean isNewNode = true;
            int[] newCoords = { r, c - 1 };
            for (int i = 0; i < n.root.nodeList.size(); i++) {
                if (n.root.nodeList.get(i).coords[0] == newCoords[0] && n.root.nodeList.get(i).coords[1] == newCoords[1]) {
                    isNewNode = false;
                }
            }
            if (isNewNode == true) {
                Node newNode = new Node(n, newCoords);
                n.root.addToNodeList(newNode);
                n.addChild(newNode, 'l');
                search(newNode, cGrid);
            }
        }

        // check right
        if (c < cGrid.length-1 && cGrid[r][c+1] == 0) {
            boolean isNewNode = true;
            int[] newCoords = { r, c + 1 };
            for (int i = 0; i < n.root.nodeList.size(); i++) {
                if (n.root.nodeList.get(i).coords[0] == newCoords[0] && n.root.nodeList.get(i).coords[1] == newCoords[1]) {
                    isNewNode = false;
                }
            }
            if (isNewNode == true) {
                Node newNode = new Node(n, newCoords);
                n.root.addToNodeList(newNode);
                n.addChild(newNode, 'r');
                search(newNode, cGrid);
            }
        }

        // check down
        if (r < cGrid.length-1 && cGrid[r+1][c] == 0) {
            boolean isNewNode = true;
            int[] newCoords = { r + 1, c };
            for (int i = 0; i < n.root.nodeList.size(); i++) {
                if (n.root.nodeList.get(i).coords[0] == newCoords[0] && n.root.nodeList.get(i).coords[1] == newCoords[1]) {
                    isNewNode = false;
                }
            }
            if (isNewNode == true) {
                Node newNode = new Node(n, newCoords);
                n.root.addToNodeList(newNode);
                n.addChild(newNode, 'r');
                search(newNode, cGrid);
            }
        }
        return n;

    }

    public static ArrayList<Board> searchTile(Node n, int num, Board b) {
        ArrayList<Board> bList = new ArrayList<Board>();
        if (num == 1) {
            bList.add(b);
            return bList;
        }

        int r = n.coords[0];
        int c = n.coords[1];

        // check up
        if (r > 0 && b.charGrid[r - 1][c] == 0) {
            int[] newCoords = { r - 1, c };
            boolean isNewNode = true;
            Node parent = n;
            while (parent.parent != parent) {
                if (parent.parent.coords[0] == newCoords[0] && parent.parent.coords[1] == newCoords[1]) {
                    isNewNode = false;
                    
                }
                parent = parent.parent;
            }
            
            System.out.println(n);
            if (isNewNode == true) {
                Node newNode = new Node(n, newCoords);
                n.addChild(newNode, 'u');
                Board newBoard = new Board(b.size);
                newBoard.setBoard(b.intGrid, b.charGrid);
                newBoard.charGrid[newCoords[0]][newCoords[1]] = '*';
                //System.out.println("Searching... " + newNode);
                ArrayList<Board> newBoardList = searchTile(newNode, num-1, newBoard);
                for (int i = 0; i < newBoardList.size(); i++) {
                    bList.add(newBoardList.get(i));
                }
            }
        }

        // check left
        if (c > 0 && b.charGrid[r][c - 1] == 0) {
            int[] newCoords = { r, c - 1 };
            boolean isNewNode = true;
            Node parent = n;
            while (parent.parent != parent) {
                if (parent.parent.coords[0] == newCoords[0] && parent.parent.coords[1] == newCoords[1]) {
                    isNewNode = false;
                    
                }
                parent = parent.parent;
            }
            
            System.out.println(n);
            if (isNewNode == true) {
                Node newNode = new Node(n, newCoords);
                n.addChild(newNode, 'l');
                Board newBoard = new Board(b.size);
                newBoard.setBoard(b.intGrid, b.charGrid);
                newBoard.charGrid[newCoords[0]][newCoords[1]] = '*';
                //System.out.println("Searching... " + newNode);
                ArrayList<Board> newBoardList = searchTile(newNode, num-1, newBoard);
                for (int i = 0; i < newBoardList.size(); i++) {
                    bList.add(newBoardList.get(i));
                }
            }
        }

        // check right
        if (c < b.size-1 && b.charGrid[r][c + 1] == 0) {
            int[] newCoords = { r, c + 1 };
            boolean isNewNode = true;
            Node parent = n;
            while (parent.parent != parent) {
                if (parent.parent.coords[0] == newCoords[0] && parent.parent.coords[1] == newCoords[1]) {
                    isNewNode = false;
                    
                }
                parent = parent.parent;
            }
            
            System.out.println(n);
            if (isNewNode == true) {
                Node newNode = new Node(n, newCoords);
                n.addChild(newNode, 'r');
                Board newBoard = new Board(b.size);
                newBoard.setBoard(b.intGrid, b.charGrid);
                newBoard.charGrid[newCoords[0]][newCoords[1]] = '*';
                //System.out.println("Searching... " + newNode);
                ArrayList<Board> newBoardList = searchTile(newNode, num-1, newBoard);
                for (int i = 0; i < newBoardList.size(); i++) {
                    bList.add(newBoardList.get(i));
                }
            }
        }

        // check down
        if (r < b.size - 1 && b.charGrid[r + 1][c] == 0) {
            int[] newCoords = { r + 1, c };
            boolean isNewNode = true;
            Node parent = n;
            while (parent.parent != parent) {
                if (parent.parent.coords[0] == newCoords[0] && parent.parent.coords[1] == newCoords[1]) {
                    isNewNode = false;
                    
                }
                parent = parent.parent;
            }
            
            System.out.println(n);
            if (isNewNode == true) {
                Node newNode = new Node(n, newCoords);
                n.addChild(newNode, 'd');
                Board newBoard = new Board(b.size);
                newBoard.setBoard(b.intGrid, b.charGrid);
                newBoard.charGrid[newCoords[0]][newCoords[1]] = '*';
                //System.out.println("Searching... " + newNode);
                ArrayList<Board> newBoardList = searchTile(newNode, num-1, newBoard);
                for (int i = 0; i < newBoardList.size(); i++) {
                    bList.add(newBoardList.get(i));
                }
            }
        }
        return bList;
    }
}