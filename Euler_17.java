package euler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 *
 * @author eanvinz
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, 
 * then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out 
 * in words, how many letters would be used?
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and 
 * forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 
 * 20 letters. 
 * The use of "and" when writing out numbers is in compliance with 
 * British usage.
 */
class Euler_17 extends EulerBase {
    
    Euler_17() { super("Number letter counts", "21124"); }
    
    private final static Map<Integer, Integer> letterCountForNumber = new HashMap<>();
    static {
        letterCountForNumber.put(1, "one".length());
        letterCountForNumber.put(2, "two".length());
        letterCountForNumber.put(3, "three".length());
        letterCountForNumber.put(4, "four".length());
        letterCountForNumber.put(5, "five".length());
        letterCountForNumber.put(6, "six".length());
        letterCountForNumber.put(7, "seven".length());
        letterCountForNumber.put(8, "eight".length());
        letterCountForNumber.put(9, "nine".length());
        letterCountForNumber.put(10, "ten".length());        
        letterCountForNumber.put(11, "eleven".length());
        letterCountForNumber.put(12, "twelve".length());
        letterCountForNumber.put(13, "thirteen".length());
        letterCountForNumber.put(14, "fourteen".length());
        letterCountForNumber.put(15, "fifteen".length());
        letterCountForNumber.put(16, "sixteen".length());
        letterCountForNumber.put(17, "seventeen".length());
        letterCountForNumber.put(18, "eighteen".length());
        letterCountForNumber.put(19, "nineteen".length());
        letterCountForNumber.put(20, "twenty".length());        
        letterCountForNumber.put(30, "thirty".length());                
        letterCountForNumber.put(40, "forty".length());                
        letterCountForNumber.put(50, "fifty".length());                
        letterCountForNumber.put(60, "sixty".length());                
        letterCountForNumber.put(70, "seventy".length());                
        letterCountForNumber.put(80, "eighty".length());                
        letterCountForNumber.put(90, "ninety".length());                
        letterCountForNumber.put(1000, "one thousand".length() - 1);                        
    };
    
    private final int HOW_MANY = 1000;

    public void run() {

        class WeightedNumber {
            public int number;
            public int weight = 0;
            
            private void calculateWeight(int num) {
                
                if (num > 1000) {
                    throw new RuntimeException("max supported number: 1000");
                }
                
                if (num == 1000) {
                    this.weight += letterCountForNumber.get(num);
                    return;
                }
                
                if (num / 100 > 0) {
                    this.weight += letterCountForNumber.get(num/100);
                    this.weight += "hundred".length();
                    if (num % 100 > 0) this.weight += "and".length();
                    num %= 100;
                    
                }
                if (num == 0) return;

                if (num / 10 > 1) {
                    this.weight += letterCountForNumber.get((num / 10) * 10);
                    num %= 10;
                }
                
                if (num == 0) return;
                
                this.weight += letterCountForNumber.get(num);
            }
            
            WeightedNumber(int num) {
                this.number = num;
                calculateWeight(num);
            }
        }
        
        int sum = IntStream.iterate(1, x -> x + 1)
                .limit(HOW_MANY)
                .map(x -> new WeightedNumber(x).weight)
                .sum();
       
        printout(sum);
    }
}

