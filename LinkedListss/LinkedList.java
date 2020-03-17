public class LinkedList{
    public static void main(String[] args){
        LinLis list = new LinLis();
        MySpecialLinkedListUtils lis = new MySpecialLinkedListUtils();
        list.insert(20);
        list.insert(6);
        list.insert(100);
        list.insert(7);
        list.insert(20);
        // list.insert(8);
        //System.out.println("");
        //double[] Values= new double[5];
        //Values = list.summary();
        /*for (int i=0;i<5;i++)
            System.out.println(Values[i]);
            */
        //  lis.PrintList(lis.reverse(list.head));
        //list.evenIndexedElements();
        //list.removeCentralNode();
        //System.out.println(lis.palindrome(list.head));
        lis.PrintList(MySpecialLinkedListUtils.insertionSort(list.head));
    }
}