package euler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author eanvinz
 * A perfect number is a number for which the sum of its proper divisors is 
 * exactly equal to the number. For example, the sum of the proper divisors 
 * of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect 
 * number.
 * A number n is called deficient if the sum of its proper divisors is less 
 * than n and it is called abundant if this sum exceeds n.
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest 
 * number that can be written as the sum of two abundant numbers is 24. 
 * By mathematical analysis, it can be shown that all integers greater than 
 * 28123 can be written as the sum of two abundant numbers. However, this 
 * upper limit cannot be reduced any further by analysis even though it is 
 * known that the greatest number that cannot be expressed as the sum of two 
 * abundant numbers is less than this limit.
 * Find the sum of all the positive integers which cannot be written as the 
 * sum of two abundant numbers.
 */
public class Euler_23 extends EulerBase {
    
    private final int LIMIT = 28123;
    
    Euler_23() { super("Non-abundant sums", "4179871"); }
    
    public void run() {
        
        IntPredicate abundantNumber = x -> { 
                                                int divSum = IntStream.rangeClosed(1, x/2)
                                                               .filter(y -> x % y == 0)
                                                               .sum();
                                                return divSum > x;
                                            };

        HashSet<Integer> abundantNumbersPool =                 
                IntStream.range(1, LIMIT)
                                .filter(abundantNumber)
                                .mapToObj(y -> new Integer(y))                        
                                .collect(Collectors.toCollection(HashSet::new));

        IntPredicate cannotBeWrittenAsAbundantSum = x -> abundantNumbersPool.stream()
                                                    .filter(y -> y < x)
                                                    .noneMatch(y -> abundantNumbersPool.contains(x-y));

        int sum = IntStream.rangeClosed(1, LIMIT)
                .filter(cannotBeWrittenAsAbundantSum)
                .sum();
       
        printout(sum);
    }
    
    public void run_iterative() {
        printout(new Iterative_Euler_23().run());
    }
}

class Iterative_Euler_23 {
    
    private final int LIMIT = 28123;
    
    private boolean abundantNumber(int x) 
    {
        int sum = 0;
        for (int divisor=x/2; divisor > 0; --divisor)             
        {
            if (x % divisor == 0) 
            {
                sum += divisor;
                if (sum > x) return true;
            }
        }
        return false;
    }
    
    private Set<Integer> fillAbundantPool() 
    {
        Set<Integer> list = new TreeSet<>();        
        for (int i=1; i<LIMIT; ++i)
        {
            if (abundantNumber(i))
                list.add(i);
        }
        return list;
    }
    
    public int run() {
   
        Set<Integer> abundantNumbersPool = fillAbundantPool();
        
        int sum = 0;
        for (int x = 1; x < LIMIT; ++x)
        {
            sum += x;
            for (Integer y: abundantNumbersPool)
            {
                if (x - y < 0)                
                {
                    break;
                }
                if (abundantNumbersPool.contains(x - y)) 
                {
                    sum -= x;
                    break;
                }
            }
        }
        return(sum);
    }
}
