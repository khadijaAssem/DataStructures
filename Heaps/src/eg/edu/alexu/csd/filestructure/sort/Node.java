package eg.edu.alexu.csd.filestructure.sort;

import java.util.*;

public class Node implements INode {
    private ArrayList<Node> arr;
    private Comparable val;
    private int index;

    public Node(ArrayList<Node> arr, int index) {
        this.arr = arr;
        this.index = index;
    }

    @Override
    public INode getLeftChild() {
        if((index * 2) > (arr.size()-1)) return null;
        return arr.get(index * 2);
    }

    @Override
    public INode getRightChild() {
        if((index * 2) + 1 > (arr.size()-1)) return null;
        return arr.get(index * 2 + 1);
    }

    @Override
    public INode getParent() {
        return arr.get(index / 2);
    }

    @Override
    public Comparable getValue() {
        return val;
    }

    @Override
    public void setValue(Comparable value) {
        this.val = value;
    }
}
