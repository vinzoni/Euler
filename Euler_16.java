package euler;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *
 * @author eanvinz
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * What is the sum of the digits of the number 2^1000?
 */
class Euler_16 extends EulerBase {
    
    Euler_16() { super("Power digit sum", "1366"); }

    private String power = "1";
    
    Supplier<String> powerOfTwoGenerator = new Supplier<String> () {
        
        String power = "1";
        
        public String get() {
            NumericStringComputer nsa = new NumericStringComputer();
            power = nsa.sum(power, power);
            return power;
        }
    };

    public void run() {
        
        NumericStringComputer nsa = new NumericStringComputer();
        final int exponent = 1000; 
                
        long sum = Stream.generate(powerOfTwoGenerator)
                .skip(exponent - 1)
                .findFirst().get().chars() // 2^exponent 
                .map(x -> x - '0')
                .sum();
       
        printout(sum);
    }
}

