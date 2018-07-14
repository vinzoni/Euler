package euler;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class MyTimer {

    Instant t1;
    Instant t2;
    
    public void start() {
        t1 =  Instant.now();
    }

    public String stop() {
        t2 =  Instant.now();
        Duration d = Duration.between(t1, t2);
        return String.format("%02d::%02d::%02d", d.toHours(), d.toMinutes()%60, (d.toMillis()/1000)%60);
    }
}

class PrimeNumbersGenerator {
    
    private final Deque<Integer> primeNumbers = new ArrayDeque<> ();
    
    public int next() {
        
        Integer lastPrimeFoundSoFar = primeNumbers.isEmpty() ? 1 : primeNumbers.getLast();
        
        int nextPrime;
        
        if (lastPrimeFoundSoFar == 1) 
            nextPrime = 2;
        else if (lastPrimeFoundSoFar == 2) 
            nextPrime = 3;
        else nextPrime = computeNextSlowAndFunctional(lastPrimeFoundSoFar);
//        else nextPrime = computeNextFastButIterative(lastPrimeFoundSoFar);
        
        primeNumbers.add(nextPrime);
        return nextPrime;
    }
    
    private boolean isPrime(int candidate) {

        // I got Math.sqrt() idea from Pierre-Yves Saumont (on dzone.com)
        return IntStream.rangeClosed(2, (int)Math.sqrt(candidate))
                       .noneMatch(x -> candidate % x == 0);
        
    } 
    
    private int computeNextSlowAndFunctional(Integer lastPrimeFoundSoFar) {

        return IntStream.iterate(lastPrimeFoundSoFar + 2, x -> x + 2)
                .filter(primeCandidate -> isPrime(primeCandidate))
                .findFirst().getAsInt();
    }
    
    private int computeNextFastButIterative(Integer lastPrimeFoundSoFar) {

          for (int primeCandidate=lastPrimeFoundSoFar+2; ; primeCandidate+=2) {
              for(Integer x: primeNumbers) {
                  if (primeCandidate % x == 0)
                      break;
                  if (primeCandidate < x * x)                      
                      return primeCandidate;
              }
          }
    }
}

