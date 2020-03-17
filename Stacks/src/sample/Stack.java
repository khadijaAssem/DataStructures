package sample;

/**
 * Stack implementation and some utilities
 * @author Khadija Assem
 */
public class Stack implements IStack {
    private Node head;
    /**
     * Node of the linked list that is used to implement the stack
     */
    public class Node {
        private Object value = null;
        private Node next = null;
    }

    /**
     * pops object from the stack
     * @return the last object pushed to the stack if stack isn't but it throws an exception if it was empty
     */
    @Override
    public Object pop() {
        Object element = "";
        try {
            int i = 0;
            Node Current = head;
            while (i < size() - 1) {
                Current = Current.next;
                i++;
            }
            element = Current.value;
            remove(size() - 1);
        }catch (Exception e){
            System.out.println("Stack is empty");
        }
        return element;
    }

    /**
     * @return the last object pushed to the stack without removing it if stack isn't but it throws an exception if it was empty
     */
    @Override
    public Object peek() {
        Object N ="";
        try {
            int i = 0;
            Node Current = head;
            while (i < size() - 1) {
                Current = Current.next;
                i++;
            }
            N=Current.value;
        }catch (Exception e){
            System.out.println("Stack is empty");
        }
        return N;
    }

    /**
     * push element to stack
     * @param element
     */
    @Override
    public void push(Object element) {
        Node node = new Node();
        node.value = element;
        node.next = null;
        if (head == null)
            head = node;
        else {
            Node n = head;
            while (n.next != null)
                n = n.next;
            n.next = node;
        }
    }

    /**
     * Checks if stack is empty
     * @return true oif stack is empty and false if not
     */
    @Override
    public boolean isEmpty() {
        if (head == null)
            return true;
        return false;
    }

    /**
     * fides the size of stack
     * @return the stack's size
     */
    @Override
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
    /**
     * removes element from the linked list used to implement the stack
     * @param index
     */
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

    /**
     * Prints the stack
     * @param list
     */
    public String printStack(Stack list) {
        String X="";
        if (list.isEmpty()){ System.out.println("Stack is empty"); return null;}
        Node currNode = list.head;
        while (currNode != null) {
            X+=(currNode.value + " ");
            currNode = currNode.next;
        }
        return X;
    }
}