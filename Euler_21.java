package euler;

import java.util.stream.IntStream;

/**
 *
 * @author eanvinz
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b 
 * are called amicable numbers.
 * 
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; 
 * therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 *
 * Evaluate the sum of all the amicable numbers under 10000.
 */
class Euler_21 extends EulerBase {
    
    Euler_21() { super("Amicable numbers", "31626"); }
    
    public void run() {
        
        int sum = 0;
        
        sum = IntStream.rangeClosed(1, 9999)
                .filter(x -> isAmicable(x))
                .sum();

        printout(sum);
    }
  
    private boolean isAmicable(int x) {
        int d_a = sumOfProperDivisors(x);
        int d_b = sumOfProperDivisors(d_a);
        return (d_a > 1) && (x == d_b) && (d_a != x);
    }
    
    private int sumOfProperDivisors(int n) {

        if (n == 1) return 1;

        return IntStream.rangeClosed(1, n-1)
                .filter(x -> n % x == 0)
                .sum();
    }
}