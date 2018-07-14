package euler;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import javafx.scene.shape.Path;

/**
 *
 * @author eanvinz
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file 
 * containing over five-thousand first names, begin by sorting it into 
 * alphabetical order. 
 * Then working out the alphabetical value for each name, multiply this 
 * value by its alphabetical position in the list to obtain a name score.
 * 
 * For example, when the list is sorted into alphabetical order, COLIN, 
 * which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. 
 * So, COLIN would obtain a score of 938 × 53 = 49714.
 * 
 * What is the total of all the name scores in the file?
 */
public class Euler_22 extends EulerBase {
    
    Euler_22() { super("Names scores", "871198282"); }
    
    public void run() {
        
        long sum = 0;
                
        ToLongFunction<String> nameWorth = x -> x.chars()
                                                .filter(y -> y != '"')
                                                .map(y -> (y - 'A') + 1)
                                                .sum();
        
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get("p022_names.txt")));

            List<Long> scores = Arrays.stream(fileContent.split(","))
                .sorted()
                .mapToLong(nameWorth).boxed()
                .collect(Collectors.toList());

            sum = LongStream.range(0, scores.size())
                    .map(x -> (x+1) * scores.get((int)x))                    
                    .sum();
        }
        catch (IOException e) {
            System.err.println(e);
        }
        
        printout(sum);
    }
}
