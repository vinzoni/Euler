package euler;


import java.util.stream.IntStream;

// DISCLAIMER:
//
// These are my solutions of Project Euler (https://projecteuler.net) problems.
//
// I choose this project mainly to practice my newly acquired knowledge of 
// Functional Programming in Java 8.
//
// The rules of (my) game are as follows:
//
// 1. I must solve each problem (right answer) ...
// 2. ... in a reasonable (run)time, well below the single minute guarantee
//        (as I have a fast PC) but ...
// 3. ... I am not interested in extreme optimization, I will be content with a
//        "fast enough" solution.
// 4. I stick to functional style as much as possibile, even when an imperative
//    idiom looks much appropriate or easier.
// 5. I refrain using library functions that would make the task at hand trivial.
//    
//

public class Euler {

    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
       
        final int LAST_EULER_DONE = 24;
        
        for (int i=LAST_EULER_DONE; i<=LAST_EULER_DONE; ++i) {            
//        for (int i=1; i<=LAST_EULER_DONE; ++i) {
            String className = String.format("euler.Euler_%02d", i);
            EulerBase euler = (EulerBase) Class.forName(className).newInstance();
            euler.run();
        }
    }
}

abstract class EulerBase {

    private final String title;
    private final String expectedResult;
    
    private final MyTimer timer = new MyTimer();
    
    public EulerBase(String title, String expectedResult) {
        this.title = title;
        this.expectedResult = expectedResult;
        
        System.out.printf("%s: %-40s", getClass().getSimpleName(), title);        
        
        timer.start();        
    }
    
    abstract public void run();
    
    private String elapsed() {
        return "(" + timer.stop() + ")";
    }   
    
    private String expected() {
        return "[ Expected: " + expectedResult + " ]";
    }    
    
    public <T> void printout(T result) {
        System.out.printf("%16s %s %s\n", result, elapsed(), expected());        
    }
}
