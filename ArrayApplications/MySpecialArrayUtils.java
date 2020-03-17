import java.util.Scanner;
import java.math.BigInteger;

public class MySpecialArrayUtils {
      public static void reverse(int[] arr){
        int arrayLength = arr.length;
        for (int i=0;i<arrayLength/2;i++){
            int temp= arr[i];
            arr[i]=arr[arrayLength-i-1];
            arr[arrayLength-i-1]=temp;
        }
    }
    public static int[] sumEvenOdd(int[] arr){
        int arrayLength = arr.length;
        int [] Sum=new int [2];
        for(int i=0;i<arrayLength;i++){
            if(arr[i]%2==1)
                Sum[1]+=arr[i];
            else Sum[0]+=arr[i];
        }
        return Sum;
    }
    public static BigDecimal Average(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return BigDecimal.valueOf(0);
        else {
            BigDecimal sum = BigDecimal.valueOf(0);
            for (int i=0;i<n;i++) {
                BigDecimal No = BigDecimal.valueOf(arr[i]);
                sum=sum.add(No);
            }
            BigDecimal TotalNo = BigDecimal.valueOf(n);
            return sum.divide(TotalNo);
        }
    }
    public static void moveValue(int[] arr, int val){
        int arrayLength = arr.length;
        for (int i=0;i<arrayLength;i++){
            if (arr[i]==val){
                for (int j=i;j<arrayLength-1;j++) {
                    int temp= arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
                i--;
                arrayLength--;
            }
        }
    }
    /*public static void Transpose(int[][] arr){
        //in case it was a squared matrix!!:In place 
        int row=arr[0].length;
        int col=arr.length;
        for(int i = 0; i < row; i++) {
            for(int j = i+1; j < col; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
    }*/
    public static int[][] transpose(int[][] arr){
        //If rectangular: not in place
        int row=arr[0].length;
        int col=arr.length;
        int [][] Narr=new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++)
                Narr[i][j]=arr[j][i];
        }
        return Narr;
    }
}
