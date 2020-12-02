import java.util.ArrayList;

public class Node {
    public Node parent;
    public Node root;
    public ArrayList<Node> nodeList;
    int[] coords;
    Node[] children;

    public Node(Node p, int[] c) {
        parent = p;
        if (p != null) {
            root = parent.root;
        }
        else {
            root = this;
            parent = this;
        }
        coords = c;
        children = new Node[4];
        nodeList = new ArrayList<Node>();
    }

    public void addToNodeList(Node n) {
        nodeList.add(n);
    }

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    public void addChild(Node n, char direction) {
        if (direction == 'u') {
            children[0] = n;
        } else if (direction == 'l') {
            children[1] = n;
        } else if (direction == 'r') {
            children[2] = n;
        } else if (direction == 'd') {
            children[3] = n;
        }
    }

    public Node up() {
        return children[0];
    }

    public Node left() {
        return children[1];
    }

    public Node right() {
        return children[2];
    }

    public Node down() {
        return children[3];
    }

    public String toString() {
        String s = "";
        int r = coords[0];
        int c = coords[1];

        int pR = parent.coords[0];
        int pC = parent.coords[1];

        int rR = root.coords[0];
        int rC = root.coords[1];

        s += "Node Coords: " + Integer.toString(c) + ", " + Integer.toString(r);
        s += "\tParent Coords: " + Integer.toString(pC) + ", " + Integer.toString(pR);
        s += "\tRoot Coords: " + Integer.toString(rC) + ", " + Integer.toString(rR);

        return s;
        
    }

}