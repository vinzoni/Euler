package euler;

import java.math.BigInteger;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *
 * @author eanvinz
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 *
 * Find the sum of the digits in the number 100!
 */
class Euler_20 extends EulerBase {
    
    Euler_20() { super("Factorial digit sum", "648"); }
    
    public void run() {
        
//        int sum = dontReinventTheWheelRun();
        int sum = ReinventingTheWheelImARealManRun();        

        printout(sum);
    }
    
    // I rely on BigInteger here    
    private int dontReinventTheWheelRun() {

        ToIntFunction<String> sumDigitsOfStringNumber = x -> Stream.of(x)
                                                                .findFirst()
                                                                .get()
                                                                .chars()
                                                                .map(y -> y - '0')
                                                                .sum();

        Function<Integer, BigInteger> bigfact = n -> IntStream.rangeClosed(1, n)
                                            .mapToObj(x -> "" + x)
                                            .map(x -> new BigInteger(x))
                                            .reduce(BigInteger.valueOf(1), (x , y) -> x.multiply(y));
        
        
        int sum = IntStream.of(100) 
                .mapToObj(bigfact::apply)                
                .map(x -> x.toString())
                .map(x -> sumDigitsOfStringNumber.applyAsInt(x))
                .findFirst().get();
        
        return sum;
    }

    // I don't rely on BigInteger here, writing my own string-math lib.
    private int ReinventingTheWheelImARealManRun() {

        ToIntFunction<String> sumDigitsOfStringNumber = x -> Stream.of(x)
                                                                .findFirst()
                                                                .get()
                                                                .chars()
                                                                .map(y -> y - '0')
                                                                .sum();
        
        NumericStringComputer nsc = new NumericStringComputer();
        
        Function<Integer, String> bigfact = n -> IntStream.rangeClosed(2, n)
                                            .mapToObj(x -> "" + x)
                                            .reduce("1", (x, y) -> nsc.multiply(x, y) );

        int sum = IntStream.of(100) 
                .mapToObj(bigfact::apply)  
                .map(x -> sumDigitsOfStringNumber.applyAsInt(x))
                .findFirst().get();
        
        return sum;
    }
}

