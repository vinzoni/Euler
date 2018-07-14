package euler;

import java.util.stream.IntStream;

/**
 * @author eanvinz
 * The sum of the squares of the first ten natural numbers is,
 * 1^2 + 2^2 + ... + 10^2 = 385
 *
 * The square of the sum of the first ten natural numbers is,
 * (1 + 2 + ... + 10)^2 = 552 = 3025
 *
 * Hence the difference between the sum of the squares of the first ten natural 
 * numbers and the square of the sum is 3025 − 385 = 2640.
 *
 * Find the difference between the sum of the squares of the first one hundred 
 * natural numbers and the square of the sum.
 */
class Euler_06 extends EulerBase {
    
    Euler_06() { super("Sum square difference", "25164150"); }
        
    public void run() {
        
        int difference = 0;
        int sumOfSquares = IntStream.rangeClosed(1, 100)
                .map(x -> x * x)
                .sum();

        int squareOfSum = IntStream.of(
                                IntStream.rangeClosed(1, 100)
                                .sum())
                .map(x -> x * x)
                .sum();
        
        difference = squareOfSum - sumOfSquares;

        printout(difference);                                
    }
}