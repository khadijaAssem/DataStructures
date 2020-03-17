package eg.edu.alexu.csd.datastructure.queue;

import java.util.EmptyStackException;

public class LinkedBased implements ILinkedBased {

    private Node head;
    public class Node {
        private Object value = null;
        private Node next = null;
    }
    public void enqueue(Object element) {
        Node current = head;
        Node store;
        Node n = new Node();
        n.value = element;
        if (head == null)
            head = n;
        else{
            store = head;
            head = n;
            n.next = store;
        }
    }
    public Object dequeue() {
        if (size()==0) throw new EmptyStackException();
        Object element;
        int i = 0;
        Node Current = head;
        while (i < size() - 1) {
            Current = Current.next;
            i++;
        }
        element = Current.value;
        remove(size() - 1);
        return element;
    }
    public boolean isEmpty() {
        return head==null?true:false;
    }
        public int size() {
        int Size = 0;
        if (head == null)
            return 0;
        Node Current = head;
        while (Current != null) {
            Size++;
            Current = Current.next;
        }
        return Size;
    }
    public void remove(int index) {
        int i=0;
        Node Current = head;
        if(head == null || index<0 || index >= size())
            throw new IndexOutOfBoundsException();
        else if(index == 0)
            head = Current.next;
        else {
            while (i < index - 1) {
                Current = Current.next;
                i++;
            }
            Current.next = Current.next.next;
        }
    }
    public void printQueue(LinkedBased list) {
        Node currNode = list.head;
        System.out.print("Queue: ");
        while (currNode != null) {
            System.out.print(currNode.value + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }
}
