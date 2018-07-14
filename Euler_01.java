package euler;

import java.util.stream.Stream;

/**
 * @author eanvinz
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. 
 * The sum of these multiples is 23. 
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */
class Euler_01 extends EulerBase {
    
    Euler_01() { super("Multiples of 3 and 5", "233168"); }
    
    public void run() {
        
        int sum = Stream.iterate(1, x -> x + 1)
                .limit(999)
                .mapToInt(x -> x)
                .filter(x -> (x % 3 == 0 || x % 5 == 0))                
                .sum();
        
        printout(sum);
    }
}
