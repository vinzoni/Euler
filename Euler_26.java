package euler;

import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
 *
 * 1/2	= 	0.5
 * 1/3	= 	0.(3)
 * 1/4	= 	0.25
 * 1/5	= 	0.2
 * 1/6	= 	0.1(6)
 * 1/7	= 	0.(142857)
 * 1/8	= 	0.125
 * 1/9	= 	0.(1)
 * 1/10	= 	0.1
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
 * 
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 */

public class Euler_26 extends EulerBase {
    
    Euler_26() { super("Reciprocal cycles", "???"); }

    private class ReciprocalCycle implements Comparator {
    	public int denominator;
    	public String recurringCycle;
    	
    	ReciprocalCycle (int d, int reciprocalCycle) {
    		denominator = d;
    		recurringCycle = Integer.valueOf(reciprocalCycle);
    	}
    	
    	public int compare(Object a, Object b) {
    		return a.recurringCycle.valueOf() < b.recurringCycle.valueOf());
    	}
    }
    
    public void run() {
        
        int d = IntStream.rangeClosed(2, 10)
        		.mapToObj(x -> new ReciprocalCycle(x, 10 % x))
        		.filter(x -> !x.recurringCycle.equals("0"))
        		.max().get().denominator;
        
        printout(d);
    }
}

