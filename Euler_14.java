package euler;

import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 *
 * @author eanvinz
 * The following iterative sequence is defined for the set of positive integers:
 *
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 * 
 * Using the rule above and starting with 13, we generate the following sequence:
 *
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) 
 * contains 10 terms. Although it has not been proved yet (Collatz Problem), 
 * it is thought that all starting numbers finish at 1.
 *
 * Which starting number, under one million, produces the longest chain?
 *
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 */
public class Euler_14 extends EulerBase {
    
    Euler_14() { super("Longest Collatz sequence", "837799"); }
    
    private int chainLength = 0;
    
    public void run() {
        
        int longestChainNumber = IntStream.iterate(1, collatzStart -> collatzStart + 1)
                .limit(1_000_000)
                .mapToObj(CollatzSequence::new)
                .max(Comparator.comparing(CollatzSequence::getSequenceLength))
                .get().getSequenceNumber();

        printout(longestChainNumber);        
    }
}

class CollatzSequence {
    private int sequenceNumber;
    private int sequenceLength = 0;
            
    private class CollatzGenerator {
    
        private long lastGenerated = 0;
    
        public long next() {
            if (lastGenerated == 0)
                lastGenerated = sequenceNumber;
            else if (lastGenerated % 2 == 0)
                lastGenerated /= 2;
            else
                lastGenerated = lastGenerated * 3 + 1;
        
            return lastGenerated;
        }
    }

    public CollatzSequence(int number) {
        sequenceNumber = number;
        calculateSequenceLength();        
    }
    
    public int getSequenceNumber() { return sequenceNumber; }
    public int getSequenceLength() { return sequenceLength; }    
    
    private void calculateSequenceLength() {

        CollatzGenerator generator = new CollatzGenerator();
        
        LongStream.generate(generator::next)
            .peek(x -> sequenceLength++) // hack for takeWhile() behaviour
            .filter(x -> x == 1)
            .findFirst();        
    }
}
