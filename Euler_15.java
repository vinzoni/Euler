package euler;

import java.util.function.LongUnaryOperator;
import java.util.stream.LongStream;

/**
 *
 * @author eanvinz
 * Starting in the top left corner of a 2×2 grid, and only being able to move 
 * to the right and down, there are exactly 6 routes to the bottom right corner:
 * r-r-d-d, r-d-r-d, r-d-d-r, d-r-r-d, d-r-d-r, d-d-r-r
 *
 * How many such routes are there through a 20×20 grid?
 */
public class Euler_15 extends EulerBase {
    
    Euler_15() { super("Lattice paths", "137846528820"); }
    
    private final static int GRID_SIDE_LEN = 20; // brute force elapsed time
                                                 // 12 -->    2704156 (00::00::00)
                                                 // 16 -->  601080390 (00::04::39)
                                                 // 18 --> 9075135300 (01::25::05)

    private static final LongUnaryOperator countBinaryDigitsOne = 
                x -> x == 1 ? 1 : ((x % 2) + Euler_15.countBinaryDigitsOne.applyAsLong(x/2));

    // Algorithm: counting the binary numbers GRID_SIDE_LEN*2 long, having
    // the same number of '0' and '1' (0 = go right, 1 = go down)
    private long bruteForceApproach() {

        return LongStream.rangeClosed(1, (long)Math.pow(2, GRID_SIDE_LEN*2))
                .map(countBinaryDigitsOne::applyAsLong)                
                .filter(x -> x == GRID_SIDE_LEN)
                .count();
    }

    // Algorithm: for side=n I have to distribuite n tokens on 2n slots:
    // I have 2n choices for the first tokens, 2n-1 for the second and so on.
    // Having two tokens on slots 1,2 or 2,1 is the same thing, so:
    // res(N) = ( 2n * 2n-1 * ... * n+1 ) / n!
    // Problem: if n=20 than n! = very big.
    // Solution: 2n * 2n-1 * 2(n-1) * ... * n+1
    //           ------------------------------
    //            n *      *  (n-1)  
    //           ==============================
    //            2 * 2n-1 * 2 * ....
    //
    private long optimizedApproach() {
        
        LongUnaryOperator fact = n -> LongStream.rangeClosed(1, n)
                                            .reduce(1, (x , y) -> x * y);
                
        return LongStream.rangeClosed(GRID_SIDE_LEN+1, GRID_SIDE_LEN*2)
               .map(x -> x % 2 == 1 ? x : 2)
               .reduce(1, (x, y) -> x * y)
                / fact.applyAsLong(GRID_SIDE_LEN / 2);
        
    }
    
    public void run() {
        
//        long routesNumber = bruteForceApproach();
        long routesNumber = optimizedApproach();

        printout(routesNumber);        
    }
}
