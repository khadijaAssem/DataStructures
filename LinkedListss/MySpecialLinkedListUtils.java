public class MySpecialLinkedListUtils {

    public static void insert(int value,LinkedListNode head) {
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

    public static void PrintList(LinkedListNode head) {
        LinkedListNode node = head;
        while (node.next != null) {
            System.out.print(node.value + "\t");
            node = node.next;
        }
        System.out.println(node.value);
    }

    public static double[] summary(LinkedListNode head) {
        LinkedListNode node = head;
        double[] Values = new double[5];
        double Sum = 0;
        int Count = 0;
        while (node.next != null) {
            Sum += node.value;
            Count++;
            node = node.next;
        }
        Count++;
        Sum += node.value;
        Values[0] = Sum;
        Values[1] = Sum / Count;
        insertionSort(head);
        node = head;
        if (Count % 2 == 0) {
            for (int i = 0; i < Count / 2 - 1; i++)
                node = node.next;
            Values[2] = (node.value + (node.next).value) / 2;
        } else {
            for (int i = 0; i < Count / 2 - 1; i++)
                node = node.next;
            Values[2] = node.value;
        }
        node = head;
        while (node.next != null)
            node = node.next;
        Values[3] = node.value;
        Values[4] = head.value;
        return Values;
    }

    public static LinkedListNode reverse(LinkedListNode head){
        LinkedListNode templ=null;
        LinkedListNode current = head;
        LinkedListNode temp;
        while (current != null) {
            temp = current.next;
            current.next = templ;
            templ = current;
            current = temp;
        }
        head = templ;
        return head;
    }

    public static LinkedListNode evenIndexedElements(LinkedListNode head){
        if (head == null) return null;
        LinkedListNode node = head;
        while (node.next!=null) {
            node.next = node.next.next;
            node = node.next;
        }
        return head;
    }

    public static LinkedListNode removeCentralNode(LinkedListNode head){
        LinkedListNode node = head;
        double Count=0;
        while (node.next != null) {
            Count++;
            node = node.next;
        }
        // System.out.println(Count);
        node=head;
        double n=Count/2;
        //System.out.println(n);
        if (Count%2!=0) {
            for (int i = 0; i < n-2 ; i++)
                node = node.next;
            //System.out.println("**");
        }
        else for (int i=0;i<n-1;i++)
            node = node.next;
        node.next = node.next.next;
        node = head;
        while (node.next!=null){
            System.out.println(node.value);
            node=node.next;
        }
        // System.out.println(node.value);
        return head;
    }

    public static boolean palindrome(LinkedListNode head){
        LinkedListNode temp=head;
        double Count=0;
        while (temp.next != null) {
            Count++;
            temp = temp.next;
        }
        Count++;
        LinkedListNode tempo = reverse(head);
        LinkedListNode node = reverse(head);
        LinkedListNode noode = reverse(tempo);
        double Coount=0;
        if (node.value==noode.value) {
            System.out.println(node.value+""+noode.value);
            Coount++;
            while (node.next != null && noode.next != null) {
                System.out.println(node.value+"    "+noode.value);
                if (node.value == noode.value)
                    Coount++;
                else break;
                node = node.next;
                noode = noode.next;
                //System.out.println(Coount);
            }
        }
        System.out.println("$"+Count);
        System.out.println("$"+Coount);
        if (Coount==Count) return true;
        else return false;
    }
    /*public LinkedListNode insertionSort(LinkedListNode head){
        if (head==null) return head;
        LinkedListNode dum =
    }*/
    public static LinkedListNode insertionSort(LinkedListNode head)
    {
        LinkedListNode current = head;
        LinkedListNode tail = null;
        while(current != null&& tail != head )
        {
            LinkedListNode next = current;
            for(next=current ; next.next != tail;  next = next.next)
            {
                if(next.value <= next.next.value)
                {
                    double temp = next.value;
                    next.value = next.next.value;
                    next.next.value = temp;
                }
            }
            tail = next;
            current = head;
        }
        return current;
    }
}
