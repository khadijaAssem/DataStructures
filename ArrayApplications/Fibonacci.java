import java.math.BigInteger;

public class Fibonacci{
    public static BigInteger fibo(int n){
        if (n==0)
            return BigInteger.valueOf(0);
        else if (n==1)
            return BigInteger.valueOf(1);
        else {
            BigInteger temp;
            BigInteger fib = new BigInteger("1");
            BigInteger prevFib = new BigInteger("1");
            for (int i = 2; i < n; i++) {
                temp= fib;
                fib = prevFib.add(fib);
                prevFib=temp;
            }
        }
        return fib;
    }
}
