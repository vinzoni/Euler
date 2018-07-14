package euler;

import java.util.stream.IntStream;

/**
 * @author eanvinz
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that 
 * the 6th prime is 13.
 * What is the 10 001st prime number?
 */
class Euler_07 extends EulerBase {
    
    Euler_07() { super("10001st prime", "104743"); }
    
    public void run() {
        
        PrimeNumbersGenerator primes = new PrimeNumbersGenerator();
        
        int prime = IntStream.generate(primes::next)
                .skip(10_000)
                .findFirst().getAsInt();
        
        printout(prime);                                        
    }
}