public class LinLis {
    LinkedListNode head;
    public void insert(int value) {
        //Once u use insert u wanna create a new node
        //Creating a new node by creating a new object
        LinkedListNode node = new LinkedListNode();
        node.value = value;
        node.next = null;
        if (head == null) //If this is the first node
            head = node;
        else {
            LinkedListNode n = head;
            //We need to find the last node and assign it's next to the new node inserted
            while (n.next != null)
                n = n.next;
            n.next = node;
        }
    }
}
