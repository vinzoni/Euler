package euler;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author eanvinz
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a^2 + b^2 = c^2
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
class Euler_09 extends EulerBase {
    
    Euler_09() { super("Special Pythagorean triplet", "31875000"); }

    // there must be a more concise way to make a triplet generator
    private static class Triplet {
        private static int next_a = 1;
        private static int next_b = 2;
        
        public static Triplet next() {
            Triplet t = new Triplet(next_a, next_b);
            next_b++;
            if (next_b == 999) {
                next_a ++;
                next_b = next_a + 1;
            }
            return t;
        }
        
        private int a, b, c;
        
        private Triplet(int a, int b) {
            this.a = a;
            this.b = b;
            this.c = 1000 - a - b;
        }
    } 
    
    public void run() {
      
        Predicate<Triplet> pithagorean = t -> (t.a * t.a) + (t.b * t.b) == (t.c * t.c);
        
        int product = Stream.generate(Triplet::next)
                .filter(pithagorean)
                .mapToInt(x -> x.a * x.b * x.c)                
                .findFirst().getAsInt();
        
        printout(product);                                                        
    }
}
