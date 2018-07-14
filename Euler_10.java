package euler;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * @author eanvinz
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 *
 */
class Euler_10 extends EulerBase {
    
    Euler_10() { super("Summation of primes", "142913828922"); }
    
    private BigInteger sum = BigInteger.ZERO;

    public void run() {
    
        int upperBound = 2_000_000;

        PrimeNumbersGenerator primes = new PrimeNumbersGenerator();        
        Stream.generate(primes::next)
                .allMatch(x -> {
                            if (x < upperBound)
                                sum = sum.add(BigInteger.valueOf(x)); // side effect, for missing takewhile op.
                            return x < upperBound;
                            }) ;

        printout(sum);                                                                
    }
}