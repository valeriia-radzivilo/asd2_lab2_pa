package ArrWork;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final ArrayList<Integer> col_list;
    private final int depth;
    private final ArrayList<Node> children ;

    boolean visited = false;

    Node(ArrayList<Integer> col_list, int depth){
        this.col_list = col_list;
        this.children = new ArrayList<>();

        this.depth = depth;
    }

    public void setVisited(boolean val)
    {
        visited = val;
    }
    public ArrayList<Integer> get_col_list(){
        return col_list;
    }



    public void addChild(Node child){
        children.add(child);
    }
    public ArrayList<Node> getChildren() {
        return children;
    }

    public int getDepth() {
        return depth;
    }
}