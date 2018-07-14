package euler;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * @author eanvinz
 * A palindromic number reads the same both ways. The largest palindrome made from the product 
 * of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
class Euler_04 extends EulerBase {
    
    Euler_04() { super("Largest palindrome product", "906609"); }
    
    public void run() {
        
        IntPredicate palindrome = x -> {
                StringBuilder sb = new StringBuilder(""+x);
                return sb.toString().equals(sb.reverse().toString());
        };

        int maxnum = IntStream.rangeClosed(100, 999)
                 .flatMap(x -> IntStream.rangeClosed(100, 999).map(y -> x * y))
                 .filter(palindrome)
                 .max()
                 .getAsInt();

        printout(maxnum);                
    }
}

/**
 * @author razibul islam
 * An iterative solution to the same problem (euler_4) for comparison.
 */
class LargestPalindrome {

	private static void largestPalindrome(int i, int j) {
		int palindrome=0;
		for(int p=i; p<=j; p++){
			for(int q=i; q<=j; q++){
				int product=p*q;
				if(isPalindrome(product) && product>palindrome){
					palindrome=product;
				}
			}
		}
		System.out.println(palindrome);
	}

	private static boolean isPalindrome(int product) {
		StringBuffer sb=new StringBuffer(Integer.toString(product));
		return Integer.toString(product).equalsIgnoreCase(sb.reverse().toString());
	}
}

