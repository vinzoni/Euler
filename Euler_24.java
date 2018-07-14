package euler;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author eanvinz
 * A permutation is an ordered arrangement of objects. 
 * For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. 
 * If all of the permutations are listed numerically or alphabetically, 
 * we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 
 * are:
 *
 * 012   021   102   120   201   210
 *
 * What is the millionth lexicographic permutation of the digits 
 * 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class Euler_24 extends EulerBase {
    
    Euler_24() { super("Lexicographic permutations", "???-210"); }
    
//    algoritmo tentativo:
//
//    	1. condizione di uscita: i numeri sono ordinati dal piu` grande al piu` piccolo: 210 -> stop; altrimenti ...
//    	2. cerco i numeri x < y "piu in la"
//    	3. riscrivo uguali i numeri fino a x escluso
//    	4. scambio x con il piu` piccolo dei numeri > x piu a destra (forse x + 1)
//    	5. ordino i numeri a destra di x dal piu piccolo al piu grande

//algoritmo tentativo 2:
//
//    	Una permutazione di N (0, n-1) cifre e`  data dalla lista
//    	0[permutazione n-1 cifre], 1[permutazone n-1 cifre], ..., N-1[permutazione N-1 cifre]
//

    public void run() {
    
    	PermutationGenerator gen = new SecondTryPermutator();
    	
    	System.out.println();
        Stream.generate(gen::nextPermutation)
            .limit(6)
            .forEach(System.out::println);
    }
}

interface PermutationGenerator {

	public String nextPermutation();
}

//algoritmo tentativo 4:
//
//per prima cosa mi accontento di generare ad ogni giro un numero piu` grande del numero corrente.
//
//guardo la prima cifra: posso incrementarla?
//si: 
//  la incremento di uno
//  decremento di uno la corrispondente
//no:
//  passo alla seconda cifra e ripeto il procedimento
//  se arrivo all'ultima cifra tiro una eccezione.
//
class SecondTryPermutator implements PermutationGenerator {

	private String currentPermutation = "000";
	private int index = currentPermutation.length()-1;

	public String nextPermutation() {
      
	  if (currentPermutation.equals("000")) 
	  {
          currentPermutation = "012";
          return currentPermutation;
      }

	  for (int index = 0; index < currentPermutation.length(); ++index)
	  {
		  if (incrementable(currentPermutation.charAt(index))) 
		  {
			  decrement((char) (currentPermutation.charAt(index) + 1));
			  incrementAt(index);
			  return currentPermutation;
		  }
	  }

	  throw new IllegalStateException("end of range");
	}

	private boolean incrementable(char digit) {
	  return digit < '0' + currentPermutation.length() - 1;
	}

	private void decrement(char digit) {
	  currentPermutation = currentPermutation.replace(digit, --digit);
	}
  
	private void incrementAt(int index) {
	  String copy = "";
	  for (int i=0; i<currentPermutation.length(); ++i) {
		  if (i == index)
			  copy += (char) (currentPermutation.charAt(i) + 1);
		  else
			  copy += currentPermutation.charAt(i);
	  }
	  currentPermutation = copy;
	}
}

class FirstTryPermutator implements PermutationGenerator {

	private String currentPermutation = "000";

	public String nextPermutation() {
        if (currentPermutation.equals("000")) {
            currentPermutation = "012";
        }
        else {

            for (int i=currentPermutation.length()-1; i>0; --i) {
                int index2;

                if (i == 0) {
                   index2 = currentPermutation.length()-1;
                }

                else {
                   index2 = i-1;
                }
                if (currentPermutation.charAt(i) > currentPermutation.charAt(index2)) {
                    currentPermutation = swapCharsAtIndex(currentPermutation, i, index2);
                    break;
                }
            }            
        }
            
        return currentPermutation;
    }

	private String swapCharsAtIndex(String s, int index1, int index2) {
        String s2 = "";
        char c;
        for (int i=0; i < s.length(); ++i) {
            if (i == index1) {
                s2 += s.charAt(index2);
            }
            else if (i == index2) {
                s2 += s.charAt(index1);
            }
            else {
                s2 += s.charAt(i);    
            }
        }
        return s2;
    }
}
