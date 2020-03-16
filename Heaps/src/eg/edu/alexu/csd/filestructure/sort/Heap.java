package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;

public class Heap implements IHeap {
    int size =1;
    private ArrayList<Node> heap;
    private int j;

    public Heap(){
        heap = new ArrayList<>();
        heap.add(0,null);
    }

    @Override
    public INode getRoot() {
        if(size-1==0)
            return null;
        return heap.get(1);
    }

    @Override
    public int size() {
        return size-1;
    }

    @Override
    public void heapify(INode node) {
        if(j*2<size && j*2+1<size && node!=null) {
            Comparable l = node.getLeftChild().getValue();
            Comparable n = node.getValue();
            Comparable r = node.getRightChild().getValue();
            if (l.compareTo(r) >= 0 && l.compareTo(n) > 0) {
                node.setValue(l);
                node.getLeftChild().setValue(n);
                j=j*2;
                heapify(node.getLeftChild());
            } else if (r.compareTo(l) >= 0 && r.compareTo(n) > 0) {
                node.setValue(r);
                node.getRightChild().setValue(n);
                j=j*2+1;
                heapify(node.getRightChild());
            }
        }
        else if(j*2<size&&node!=null){
            Comparable l = node.getLeftChild().getValue();
            Comparable n = node.getValue();
            if (l.compareTo(n) > 0) {
                node.setValue(l);
                node.getLeftChild().setValue(n);
                j=j*2;
                heapify(node.getLeftChild());
            }
        }
    }

    @Override
    public Comparable extract() {
        if(size==1) return null;
        Comparable x ;
        try {
            x = heap.get(1).getValue();
        }
        catch (Exception e) {return null;}
        heap.get(1).setValue(heap.get(size-1).getValue());
        j = 1;
        heapify(heap.get(1));
        heap.get(size-1).setValue(x);
        size--;
        return x;
    }

    @Override
    public void insert(Comparable element) {
        if (element==null) return;
        INode nNode = new Node(heap,size);
        nNode.setValue(element);
        heap.add(size++,(Node) nNode);
        int i=size-1;
        while (i!=1) {
            Comparable p = nNode.getParent().getValue();
            Comparable n = nNode.getValue();
            if (p.compareTo(n) < 0) {
                nNode.setValue(p);
                nNode.getParent().setValue(n);
                nNode = nNode.getParent();
            }
            i/=2;
        }
    }
    @Override
    public void build(Collection unordered) {
        size = 1;
        if(unordered==null) return;
        int x = unordered.size();
        ArrayList<Comparable> values = new ArrayList<Comparable>(unordered);
        for(int i=0;i<x;i++)
            insert(values.get(i));
    }
    public void setSize(int s){
        this.size = s;
    }
}