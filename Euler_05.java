package euler;

import java.util.stream.IntStream;


/**
 * @author eanvinz
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
class Euler_05 extends EulerBase {
    
    Euler_05() { super("Smallest multiple", "232792560"); }
        
    public void run() {
        
        int minnum = IntStream.iterate(20, x -> x + 20)
                .filter(x -> IntStream.rangeClosed(1, 20)
                                .allMatch(y -> x % y == 0))
                .findFirst()
                .getAsInt();
        
        printout(minnum);                        
    }
}
