package euler;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStringComputer {

    public String sum(String n1, String n2) {
        return new Adder().sum(n1, n2);
    }

    public String multiply(String n1, String n2) {
        return new Multiplier().multiply(n1, n2);
    }
    
    private class Adder { 
        
        private String operand1, operand2;        
        private int carryOn = 0;
    
        public String sum(String n1, String n2) {

            carryOn = 0;
        
            this.operand1 = new StringBuffer(n1).reverse().toString();
            this.operand2 = new StringBuffer(n2).reverse().toString();     

            String sum = IntStream.iterate(0, x -> x + 1)
                .limit(Math.max(n1.length(), n2.length()))
                .mapToObj(x -> String.valueOf(sumSingleDigit(x)))                               
                .collect(Collectors.joining());

            sum = carryOn == 1 ? sum + "1" : sum;                
            return new StringBuffer(sum).reverse().toString();
        }

        private int sumSingleDigit(int index) {
            // index allowed to be > strlen, to manage strings of different length
            // i.e. "1234" + "567" -> "1234" + "0567"
            char c1 = index < operand1.length() ? operand1.charAt(index) : '0';
            char c2 = index < operand2.length() ? operand2.charAt(index) : '0';        
    
            int val = (c1 - '0') + (c2 - '0') + carryOn;
            carryOn = 0;
            if (val > 9) {
                val = val % 10;
                carryOn = 1;
            }
            return val;
        }
    }
    private class Multiplier { 

        private int carryOn = 0;    
        private String zeros = "";
        private String operand1, operand2;        
    
        public String multiply(String n1, String n2) {

            carryOn = 0;
            zeros = "";
        
            Stream<String> partialMultiplyStream = partialMultiplication(n1, n2);
        
            String result = 
                     partialMultiplyStream
                        .map(x -> { x = x + zeros; zeros += "0"; return x; })
                        .reduce("", (x, y) -> sum(x, y));
            return result;
        }
        
        private Stream<String> partialMultiplication(String operand1, String operand2) {
            // input: 62 x 37 - output: 434, 186
            // I need operands reversed and with the shortest first
            String reverseOperand1 = new StringBuffer(operand1.length() > operand2.length() ? operand1 : operand2).reverse().toString();
            String reverseOperand2 = new StringBuffer(operand1.length() > operand2.length() ? operand2 : operand1).reverse().toString();

            return Stream.of(reverseOperand2)
                 .findFirst().get().chars()
                 .map(x -> { carryOn = 0; return x; } )                                         
                 .mapToObj(x -> Stream.of(reverseOperand1)
                                    .findFirst().get().chars()
                                    .map (y -> multiplySingleDigits(x, y))
                                    .mapToObj (y -> String.valueOf(y))
                                    .collect(Collectors.joining()))
                 .map (y -> new StringBuffer(y).reverse().toString())
                 .map (y -> carryOn > 0 ? String.valueOf(carryOn) + y : y);
        }

        private int multiplySingleDigit(int index1, int index2) {
            char c1 = operand1.charAt(index1);
            char c2 = operand2.charAt(index2);        
    
            int val = (c1 - '0') * (c2 - '0') + carryOn;
            carryOn = val / 10;
            val = val % 10;
            return val;
        }

        private int multiplySingleDigits(int d1, int d2) {
            int result = (d1 - '0') * (d2 - '0') + carryOn;
            carryOn = result / 10;
            return result % 10;        
        }
    }
}

