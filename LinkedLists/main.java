package eg.edu.alexu.csd.datastructure.linkedList.cs53_cs28;
import java.util.Scanner;
import java.awt.*;
import java.util.*;
public class l {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        LinkedList k = new LinkedList();
        PolynomialSolver solve = new PolynomialSolver();
        for (int i = 0; i < 3; i++)
            solve.R.add(0);
        while (true) {
            System.out.println("Please choose an action");
            System.out.println("-----------------------");
            System.out.println("1- Set a polynomial variable");
            System.out.println("2- Print the value of a polynomial variable");
            System.out.println("3- Add two polynomials");
            System.out.println("4- Subtract two polynomials");
            System.out.println("5- Multiply two polynomials");
            System.out.println("6- Evaluate a polynomial at some point");
            System.out.println("7- Clear a polynomial variable");
            System.out.println("\n====================================================================\n");
            int input= s.nextInt();
            if(input==1) {
                System.out.println("Insert the variable name: A, B, C...");
                char alpha=s.next().charAt(0);
                System.out.println("Insert the polynomial terms in the form:\n(coeff1,exponent1),(coeff2,exponent2), ..");
                ArrayList<ArrayList<Point> > List =  new ArrayList<ArrayList<Point> >();
                ArrayList<Point> a1 = new ArrayList<Point>();
                int i;
                String inp=s.next();
                char arrchar[]=inp.toCharArray();
                int j=0,count=0;
                for(i=0;i<arrchar.length;i++)
                    if(Character.isDigit(arrchar[i]))
                        count++;
                int arr[]=new int[count];
                for(i=0;i<arrchar.length;i++)
                    if(Character.isDigit(arrchar[i])) {
                        arr[j]=Character.getNumericValue(arrchar[i]);
                        j++;
                    }
                i=0;
                j=0;
                int x = 0,y;
                while(i<arr.length)
                    if(i%2==0){
                        x=arr[i];
                        i++;
                    }
                    else if(i%2!=0) {
                        y=arr[i];
                        i++;
                        a1.add(new Point(x,y));
                    }
                List.add(a1);
                int[][] terms = new int[a1.size()][2];
                for (i = 0; i < a1.size(); i++){
                    Point row = a1.get(i);
                    terms[i][0] = row.x;
                    terms[i][1] = row.y;
                }
                solve.setPolynomial(alpha, terms);
                System.out.println("Polynomial "+alpha+" is set");
                System.out.println("\n====================================================================\n");
            }
            else if (input == 2) {
                System.out.println("Insert the variable name: A, B, C...or R");
                char poly;
                do {
                    poly = s.next().charAt(0);
                    if (solve.Alphabet.contains(poly) == false)
                        System.out.println("Variable not set\nPlease Enter a valid Input.");
                }
                while (solve.Alphabet.contains(poly) == false);
                System.out.println(poly + " Value in " + poly + ":" + solve.print(poly));
                System.out.println("\n====================================================================\n");
            }
            else if (input == 3){
                System.out.println("Insert first operand variable name: A, B, C...");
                char poly1;
                do {
                    poly1 = s.next().charAt(0);
                    if (solve.Alphabet.contains(poly1) == false)
                        System.out.println("Variable not set\nPlease Enter a valid Input.");
                }
                while (solve.Alphabet.contains(poly1) == false);
                System.out.println("Insert second operand variable name: A, B, C...");
                char poly2;
                do {
                    poly2 = s.next().charAt(0);
                    if (solve.Alphabet.contains(poly2) == false)
                        System.out.println("Variable not set\nPlease Enter a valid Input.");
                }
                while (solve.Alphabet.contains(poly2) == false);
                solve.add(poly1, poly2);
                System.out.println("Result set in R: ");
                System.out.print("(" + poly1 + ")+(" + poly2 + ") = ");
                k.PrintList_Points((LinkedList) solve.R.get(0));
                System.out.println();
                System.out.println("\n====================================================================\n");
            }
            else if (input == 4) {
                System.out.println("Insert first operand variable name: A, B, C...");
                char poly1;
                do {
                    poly1 = s.next().charAt(0);
                    if (solve.Alphabet.contains(poly1) == false)
                        System.out.println("Variable not set\nPlease Enter a valid Input.");
                }
                while (solve.Alphabet.contains(poly1) == false);
                System.out.println("Insert second operand variable name: A, B, C...");
                char poly2;
                do {
                    poly2 = s.next().charAt(0);
                    if (solve.Alphabet.contains(poly2) == false)
                        System.out.println("Variable not set\nPlease Enter a valid Input.");
                }
                while (solve.Alphabet.contains(poly2) == false);
                solve.subtract(poly1, poly2);
                System.out.println("(" + poly1 + ")-(" + poly2 + ") = " );
                k.PrintList_Points((LinkedList) solve.R.get(1));
                System.out.println();
                System.out.println("\n====================================================================\n");
            }
            else if (input == 5) {
                System.out.println("Insert first operand variable name: A, B, C...");
                char poly1;
                do {
                    poly1 = s.next().charAt(0);
                    if (solve.Alphabet.contains(poly1) == false)
                        System.out.println("Variable not set\nPlease Enter a valid Input.");
                }
                while (solve.Alphabet.contains(poly1) == false);
                System.out.println("Insert second operand variable name: A, B, C...");
                char poly2;
                do {
                    poly2 = s.next().charAt(0);
                    if (solve.Alphabet.contains(poly2) == false)
                        System.out.println("Variable not set\nPlease Enter a valid Input.");
                }
                while (solve.Alphabet.contains(poly2) == false);
                solve.multiply(poly1, poly2);
                System.out.println("(" + poly1 + ")*(" + poly2 + ") = ");
                k.PrintList_Points((LinkedList) solve.R.get(2));
                System.out.println();
                System.out.println("\n====================================================================\n");
            }
            else if (input == 6) {
                char poly;
                System.out.println("Insert the variable name: A, B, C...");
                do {
                    poly = s.next().charAt(0);
                    if (solve.Alphabet.contains(poly) == false)
                        System.out.println("Variable not set\nPlease Enter a valid Input.");
                }
                while (solve.Alphabet.contains(poly) == false);
                System.out.println("Enter the value you want to substitute with");
                float result= solve.evaluatePolynomial(poly, s.nextFloat());
                System.out.println("\nResult = "+result);
                System.out.println("\n====================================================================\n");
            }
            else if(input==7) {
                System.out.println("Insert the variable name you want to clear: A, B, C...");
                char alpha=s.next().charAt(0);
                solve.clearPolynomial(alpha);
                System.out.println("Polynomial "+alpha+" is cleared.");
                System.out.println("\n====================================================================\n");
            }
            else if(input>8 || input<1)
                System.out.println("You Should choose number between 1 to 7.\n");
        }
    }
}
