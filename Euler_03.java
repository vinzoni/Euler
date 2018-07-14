package euler;

import java.math.BigInteger;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author eanvinz
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */
class Euler_03 extends EulerBase {
    
    Euler_03() { super("Largest prime factor", "6857"); }
    
    private Optional<Integer> getPrimeFactorFor(BigInteger n) {
            
        PrimeNumbersGenerator primes = new PrimeNumbersGenerator();
        
        Optional<Integer> newPrime = Stream.generate(primes::next)
                    .map(x -> BigInteger.valueOf(x))
                    .filter(x -> n.remainder(x).equals(BigInteger.ZERO))
                    .map(x -> x.intValue())
                    .findFirst();
            
        return newPrime;
    }
    
    public void run() {

        BigInteger numerone = new BigInteger("600851475143");        
        MyTimer timer = new MyTimer(); timer.start();

        Integer bestPrime = new Integer(1);
        
        BigInteger num = numerone;
        while (num.compareTo(BigInteger.valueOf(1)) > 0) {
            
            bestPrime = getPrimeFactorFor(num).orElse(bestPrime);
            
            while (num.remainder(BigInteger.valueOf(bestPrime)).equals(BigInteger.ZERO))
                num = num.divide(BigInteger.valueOf(bestPrime));
        }
        
        printout(bestPrime);        
    }
}

