package eg.edu.alexu.csd.datastructure.linkedList.cs53_cs28;
public class DLinkedList implements ILinkedList {//Double
    private static Node head;//head
    private static Node tail;//tail
    private static int size = 0;//size

    private static class Node {//node
        private Object data;
        private Node prev;
        private Node next;
        Node(final Object d) {
            data = d;
        } //Node
    }
    public void add(final int index , final Object element) {
        Node current = head;
        Node store;
        Node addelement = new Node(element);
        if (head == null) {
            addelement.prev = null;
            head = addelement;
            tail = addelement;
        }
        else if (index == 0) {
            store = head;
            head = addelement;
            addelement.prev = null;
            addelement.next = store;
            if (tail == null){
                tail = addelement;}
        }
        else if (index == size) {
            tail.next = addelement;
            addelement.prev = tail;
            tail = addelement;
        }
        else if(index > size || index < 0){
            throw new IndexOutOfBoundsException();}
        else {
            for(int i = 0 ; i < index - 1 ; i++){
                current = current.next;}
            store = current.next;
            current.next = addelement;
            addelement.prev = current;
            addelement.next = store;
            if (addelement.next != null){
                addelement.next.prev = addelement;}
        }
        size++;
    }
    public void add(final Object element) {
        Node addelement = new Node(element);
        if (head == null) {
            addelement.prev = null;
            head = addelement;
            tail = addelement; }
        else {
            tail.next = addelement;
            addelement.prev = tail;
            tail = addelement;
        }
        size++;
    }
    //Object
    public Object get(final int index) {
        Node current = head;
        Object value = null;
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();}
        else {
            for (int i = 0 ; i < index ; i++){
                current = current.next;}
            value = current.data;
        }
        return value;
    }
    //Set element t an index
    public void set(final int index, final Object element) {
        Node current = head;
        if (index == size - 1){
            tail.data = element;}
        else if (index == 0){
            head.data = element;}
        else if (index >= size || index < 0 || head == null){
            throw new IndexOutOfBoundsException();}
        else {
            for (int i = 0 ; i < index - 1; i++){
                current = current.next;}
            current.next.data = element;
        }
    }
    //clear
    public void clear() {
        Node current = head;
        if (head == null) {
            size = 0;
            return;
        }
        while (current != null) {
            head = current.next;
            current = current.next;
        }
        size = 0;
        return;
    }
    //Check if empty
    public boolean isEmpty() {
        return size == 0;
    }
    //Remove
    public void remove(final int index) {
        Node current = head;
        if(head == null || index < 0 || index >= size){
            throw new IndexOutOfBoundsException();}
        else if(index == 0){
            head = current.next;}
        else {
            for (int i = 0; i < index- 1; i++){
                current = current.next;}
            current.next = current.next.next;
            if (index == size - 1){
                tail = current;}
        }
        size--;
    }
    //Returns size
    public int size() {
        return size;
    }
    //Sublist
    public ILinkedList sublist(final int fromIndex, final int toIndex) {
        if (head == null || fromIndex < 0 || fromIndex >= size || toIndex >= size || toIndex < 0) {
            throw new IndexOutOfBoundsException();}
        Node assist = head;
        int count = 0;
        ILinkedList home = new LinkedList();
        while (assist != null) {
            if (count >= fromIndex && count <= toIndex){
                home.add(assist.data);}
            assist = assist.next;
            count++;
        }
        return home;
    }
    //Chexk whether functions contains an object or not
    public boolean contains(final Object o) {
        Node check = head;
        while (check != null){
            if (check.data == o) { return true ; }
            check = check.next;
        }
        return false;
    }
}
