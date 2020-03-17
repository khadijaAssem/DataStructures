package eg.edu.alexu.csd.datastructure.linkedList.cs53_cs28;

import java.awt.Point;


/*
 * singly linked list
 */
public class LinkedList implements ILinkedList {
 private Node head;                  //head
 public class Node {                 //Node
  private Object value = null;       //value
  private Node next = null;          //next
 }
 public void add(int index , Object element) {
  if (index > size() || index < 0) {
   throw new IndexOutOfBoundsException();
  }
  Node current = head;
  Node store;
  Node n = new Node();
  n.value = element;
  if (head == null) {
   head = n;
  }
  else if (index == 0) {
   store = head;
   head = n;
   n.next = store;
  }
  else {
   for (int i = 0 ;i < index-1 ; i++) {
    current = current.next;
    store = current.next;
    current.next = n;
    n.next = store;
   }
  }
 }
 public void add(Object value) {
  Node node = new Node();
  node.value = value;
  node.next = null;
  if (head == null) {
   head = node;
  } 
  else {
   Node n = head;
   while (n.next != null){
    n = n.next;   
   }
   n.next = node;
  }
 }
 public Object get(int index) {
  int i = 0;
  Node Current = head;
  if (index > size() || index < 0) {
   throw new IndexOutOfBoundsException();
  }
  while (i < index) {
   Current = Current.next;
   i++;
  }
  return Current.value;
 }
 public void set(int index, Object element){
  int i = 0;
  Node Current = head;
  while (i < index) {
   Current = Current.next;
   i++;
  }
  Current.value = element;
 }
 public void clear() {
  head = null;
 }
 public boolean isEmpty() {
  if (head == null) {
	return true;
  }  
  return false;
 }
 public void remove(int index) {
  int i=0;
  Node Current = head;
  if(head == null || index<0 || index >= size()) { 
	throw new IndexOutOfBoundsException();
  }
  else if(index == 0) {
	  head = Current.next;
  } 
  else {
   while (i < index - 1) {
    Current = Current.next;
    i++;
   }
   Current.next = Current.next.next;
  }
 }
 public int size() {
  int Size = 0;
  if (head == null) {
   return 0;
  }
  Node Current = head;
  while (Current != null) {
    Size++;
	Current = Current.next;
  }
  return Size;
 }
 public ILinkedList sublist(int fromIndex, int toIndex) {
  ILinkedList Sub = new LinkedList();
  Node Current = head;
  for (int i = 0 ; i < size() ; i++) {
   if (i == fromIndex) {
    Sub.add(Current.value);
    while (i < toIndex) {
     Current = Current.next;
     Sub.add(Current.value);
     i++;
    }
   }
   Current = Current.next;
  }
  return Sub;
 }
 public boolean contains(Object o) {
  Node check = head;
  while (check != null) {
   if (check.value == o) {
	return true;   
   }
   check = check.next;
  }
  return false;
 }
 public int IndexOf(Object o) {
  if (head == null) {
   return -1;
  }
  int index = 0;
  Node Current = head;
  while (Current.next != null && Current.value != o) {
   index++;
   Current = Current.next;
  }
  return index;
 }
 public void printList(LinkedList list) { 
  Node currNode = list.head; 
  System.out.print("LinkedList: "); 
  while (currNode != null) { 
   System.out.print(currNode.value + " "); 
   currNode = currNode.next; 
  } 
 } 
 public void PrintList_Points(LinkedList list) { 
  Node currNode = list.head;
  System.out.print("Result: "); 
  for (int i = 0 ; i < list.size() ; i++) {
  System.out.print("(" + ((Point)list.get(i)).x +"," + ((Point)list.get(i)).y + ")");
   if (i < list.size()-1) {
    System.out.print(",");  
   }
  }	
 } 
}
