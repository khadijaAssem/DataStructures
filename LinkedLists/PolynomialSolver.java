package eg.edu.alexu.csd.datastructure.linkedList.cs53_cs28;
import java.awt.*;

public class PolynomialSolver implements IPolynomialSolver {
    LinkedList Polys = new LinkedList();
    LinkedList Alphabet = new LinkedList();
    LinkedList R = new LinkedList();

    public void setPolynomial(char poly, int[][] terms) {
        for (int i = 0; i < terms.length-1; i++)
            for (int j = 0; j < terms.length-i-1; j++)
                if (terms[j][1] < terms[j+1][1]) {
                    int temp = terms[j][1];
                    terms[j][1] = terms[j+1][1];
                    terms[j+1][1] = temp;
                }
        LinkedList term = new LinkedList();
        Point temp;
        for (int i = 0; i <terms.length; i++) {
            temp = new Point(terms[i][0],terms[i][1]);
            term.add(temp);
        }
        Alphabet.add(poly);
        Polys.add(term);
    }
    public String print(char poly){
        String Polynomial="";
        if (Alphabet.contains(poly)==false)
            throw new IndexOutOfBoundsException();
        LinkedList Current = (LinkedList) Polys.get(Alphabet.IndexOf(poly));
        for (int i=0;i<Current.size();i++){
            if (((Point)Current.get(i)).x!=0) {
                if (((Point)Current.get(i)).x!=1||((Point)Current.get(i)).y==0)
                    Polynomial=Polynomial+(char)(((Point)Current.get(i)).x+48);
                if (((Point)Current.get(i)).y!=0){
                    Polynomial=Polynomial+'X';
                    if (((Point)Current.get(i)).y!=1)
                        Polynomial=Polynomial+'^'+(char)(((Point)Current.get(i)).y+48);
                }
            }
            if (i+1!=Current.size()) Polynomial=Polynomial+'+';
        }
        return Polynomial;
    }
    public int[][] add(char poly1, char poly2) {
        int i=0,j=0,count=0;
        LinkedList term1=(LinkedList) Polys.get(Alphabet.IndexOf(poly1));
        LinkedList term2=(LinkedList) Polys.get(Alphabet.IndexOf(poly2));
        LinkedList term=new LinkedList();
        Point temp;
        while(i!=term1.size() && j!=term2.size())
            if(((Point)term1.get(i)).y > ((Point)term2.get(j)).y) {
                temp=new Point(((Point)term1.get(i)).x,((Point)term1.get(i)).y);
                term.add(temp);
                i++;
                count++;
            }
            else if(((Point)term1.get(i)).y < ((Point)term2.get(j)).y) {
                temp=new Point(((Point)term2.get(j)).x,((Point)term2.get(j)).y);
                term.add(temp);
                j++;
                count++;
            }
            else {
                temp=new Point(((Point)term1.get(i)).x+((Point)term2.get(j)).x,((Point)term1.get(i)).y);
                term.add(temp);
                i++;
                j++;
                count++;
            }

        while(i!=term1.size() || j!=term2.size()) {
            if (i!=term1.size()) {
                temp = new Point(((Point) term1.get(i)).x, ((Point) term1.get(i)).y);
                term.add(temp);
                i++;
            }
            if (j!=term2.size()) {
                temp = new Point(((Point) term2.get(j)).x, ((Point) term2.get(j)).y);
                term.add(temp);
                j++;
            }
        }
        R.set(0,term);
        int terms[][]=ToArray(term,count);
        return terms;
    }
    public int[][] subtract(char poly1, char poly2) {
        int i=0,j=0,count=0;
        int index1=Alphabet.IndexOf(poly1);
        int index2=Alphabet.IndexOf(poly2);
        LinkedList term1=(LinkedList) Polys.get(index1);
        LinkedList term2=(LinkedList) Polys.get(index2);
        LinkedList term=new LinkedList();
        Point temp;
        while(i!=term1.size()&& j!=term2.size())
            if(((Point)term1.get(i)).y > ((Point)term2.get(j)).y) {
                temp=new Point(((Point)term1.get(i)).x,((Point)term1.get(i)).y);
                term.add(temp);
                i++;
                count++;
            }
            else if(((Point)term1.get(i)).y < ((Point)term2.get(j)).y){
                temp=new Point(((Point)term2.get(j)).x,((Point)term2.get(j)).y);
                term.add(temp);
                j++;
                count++;
            }
            else {
                temp=new Point(((Point)term1.get(i)).x-((Point)term2.get(j)).x,((Point)term1.get(i)).y);
                term.add(temp);
                i++;
                j++;
                count++;
            }
        while(i!=term1.size() || j!=term2.size()) {
            if(i!=term1.size()) {
                temp=new Point(((Point)term1.get(i)).x,((Point)term1.get(i)).y);
                term.add(temp);
                i++;
                count++;
            }
            if(j!=term2.size()) {
                temp=new Point(((Point)term2.get(j)).x,((Point)term2.get(j)).y);
                term.add(temp);
                j++;
                count++;
            }
        }
        R.set(1,term);
        int terms[][]=ToArray(term,count);
        return terms;
    }
    public int[][] multiply(char poly1, char poly2) {
        int i=0,j=0,count=0;
        LinkedList term1=(LinkedList) Polys.get(Alphabet.IndexOf(poly1));
        LinkedList term2=(LinkedList) Polys.get(Alphabet.IndexOf(poly2));
        LinkedList term=new LinkedList();
        Point temp;
        while (i!=term1.size()) {
            while (j!=term2.size()) {
                int coeff, power;
                coeff = ((Point)term1.get(i)).x * ((Point)term2.get(j)).x;
                power =((Point)term1.get(i)).y + ((Point)term2.get(j)).y;
                temp=new Point(coeff,power);
                term.add(temp);
                count++;j++;
            }
            j=0;i++;
        }
        R.add(2,term);
        int terms[][]=ToArray(term,count);
        return terms;
    }
    public int[][] ToArray(LinkedList term,int count){
        int terms[][]=new int[count][2];
        for(int k=0;k<count;k++) {
            terms[k][0]=((Point)term.get(k)).x;
            terms[k][1]=((Point)term.get(k)).y;
        }
        return terms;
    }
    public  float evaluatePolynomial(char poly, float value) {
        float result=0;
        int index1=Alphabet.IndexOf(poly),i=0;
        LinkedList term1=(LinkedList) Polys.get(index1);
        while(i!=term1.size()){
            result+=((Point)term1.get(i)).x *Math.pow(value, ((Point)term1.get(i)).y);
            i++;
        }
        return result;
    }
    public void clearPolynomial(char poly) {
        Polys.remove(Alphabet.IndexOf(poly));
        Alphabet.remove(Alphabet.IndexOf(poly));
    }
}
