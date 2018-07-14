package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author eanvinz
 * In the 20×20 grid below, four numbers along a diagonal line have been marked 
 * in red. (in euler site, here I enclose them in () )
 * 08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08
 * 49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00
 * 81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65
 * 52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91
 * 22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80
 * 24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50
 * 32 98 81 28 64 23 67 10 (26) 38 40 67 59 54 70 66 18 38 64 70
 * 67 26 20 68 02 62 12 20 95 (63) 94 39 63 08 40 91 66 49 94 21
 * 24 55 58 05 66 73 99 26 97 17 (78) 78 96 83 14 88 34 89 63 72
 * 21 36 23 09 75 00 76 44 20 45 35 (14) 00 61 33 97 34 31 33 95
 * 78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92
 * 16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57
 * 86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58
 * 19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40
 * 04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66
 * 88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69
 * 04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36
 * 20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16
 * 20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54
 * 01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48
 *
 * The product of these numbers is 26 × 63 × 78 × 14 = 1788696.
 * What is the greatest product of four adjacent numbers in 
 * the same direction (up, down, left, right, or diagonally) 
 * in the 20×20 grid?
 */
class Euler_11 extends EulerBase {
    
    private final static int howManyGridElementToSum = 4;

    private final static String gridData = 
        "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08 " +
        "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00 " +
        "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65 " +
        "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91 " +
        "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80 " +
        "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50 " +
        "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70 " +
        "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21 " +
        "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72 " +
        "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95 " +
        "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92 " +
        "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57 " +
        "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58 " +
        "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40 " +
        "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66 " +
        "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69 " +
        "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36 " +   
        "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16 " +
        "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54 " +
        "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48 ";
     
    private Grid grid = new Grid();
            
    Euler_11() { super("Largest product in a grid", "70600674"); }

    private class GridElement {
        private int x, y, value;
        GridElement(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
        public int value() { return value; }
    }
    
    private class GridLine {
       
        private List<GridElement> collectGridElements(GridElement pivot, 
                                                        IntPredicate checkArrayLimits, 
                                                        IntFunction<GridElement> mapper) {
            return IntStream.iterate(0, i -> i + 1)                    
                .limit(howManyGridElementToSum)
                .filter(checkArrayLimits)
                .mapToObj(mapper)
                .collect(Collectors.toList());
        }
                
        public List<GridElement> vertical(GridElement pivot) {
            IntPredicate checkArrayLimits = i -> pivot.x + i < grid.length();
            IntFunction<GridElement> mapper = i -> new GridElement(pivot.x+i, pivot.y, grid.element(pivot.x+i, pivot.y));            
            return collectGridElements(pivot, checkArrayLimits, mapper);
        }
    
        public List<GridElement> horizontal(GridElement pivot) {
            IntPredicate checkArrayLimits = i -> pivot.y + i < grid.length();
            IntFunction<GridElement> mapper = i -> new GridElement(pivot.x, pivot.y+i, grid.element(pivot.x, pivot.y+i));            
            return collectGridElements(pivot, checkArrayLimits, mapper);
            
        }

        public List<GridElement> diagonal(GridElement pivot) {
            IntPredicate checkArrayLimits = i -> (pivot.x + i < grid.length()) && (pivot.y + i < grid.length());
            IntFunction<GridElement> mapper = i -> new GridElement(pivot.x+i, pivot.y+i, grid.element(pivot.x+i, pivot.y+i));            
            return collectGridElements(pivot, checkArrayLimits, mapper);
            
        }
        
        public List<GridElement> back_diagonal(GridElement pivot) {
            IntPredicate checkArrayLimits = i -> (pivot.x-i > -1) && (pivot.y+i < grid.length());
            IntFunction<GridElement> mapper = i -> new GridElement(pivot.x-i, pivot.y+i, grid.element(pivot.x-i, pivot.y+i));            
            return collectGridElements(pivot, checkArrayLimits, mapper);
        }
    }
    
    private class Grid {
        private int[][] grid;
        private int i = 0; // [ugly] I need an index for lambda in convertGridFromStringToIntArray()
        private int gridLength;
            
        Grid() {
            convertGridFromStringToIntArray();
        }
        
        private void convertGridFromStringToIntArray() {
            String[] numbers = Euler_11.gridData.split(" ");
            List<String> numberList = Arrays.asList(numbers);
            gridLength = (int) Math.sqrt(numberList.size());
            grid = new int[gridLength][gridLength];

            numberList.forEach(x -> {
                grid[i/gridLength][i%gridLength] = Integer.parseInt(x);
                i++; 
            } );
        }
        
        public int length() { return gridLength; }
        
        public int element(int x, int y) {
            return grid[x][y];
        }
        
        public Stream<GridElement> stream() {
            Stream<GridElement> str = IntStream.rangeClosed(0, grid.length-1)
                .boxed() // as there is no flatMapToObj()
                .flatMap(x -> IntStream.rangeClosed(0, grid.length-1)
                    .mapToObj(y -> new GridElement(x, y, grid[x][y])));
                return str;
        }

        private ToIntFunction<GridElement> 
                    getSequenceProductFunction(Function <GridElement, List<GridElement>> sequenceSupplier) {
            
            ToIntFunction<GridElement> 
                sequenceProduct = x -> sequenceSupplier.apply(x)
                        .stream()
                        .mapToInt(GridElement::value)
                        .reduce(1, (a, b) -> a * b);
                return sequenceProduct;
        }
        
        public IntStream sequenceProductStream(GridElement pivot) {            
            List<Integer> list = new ArrayList<>();
        
            ToIntFunction<GridElement> 
                verticalProduct = getSequenceProductFunction(x -> new GridLine().vertical(x));
            list.add(verticalProduct.applyAsInt(pivot));
            
            ToIntFunction<GridElement> 
                horizontalProduct = getSequenceProductFunction(x -> new GridLine().horizontal(x));
            list.add(horizontalProduct.applyAsInt(pivot));        
            
            ToIntFunction<GridElement> 
                diagonalProduct = getSequenceProductFunction(x -> new GridLine().diagonal(x));
            list.add(diagonalProduct.applyAsInt(pivot));                    
            
            ToIntFunction<GridElement> 
                backDiagonalProduct = getSequenceProductFunction(x -> new GridLine().back_diagonal(x));
            list.add(backDiagonalProduct.applyAsInt(pivot));
            
            return list.stream().mapToInt(Integer::intValue);
        }
    }
 
    public void run() {
    
        ToIntFunction<GridElement> bestProductOnGridElement =
                x -> grid.sequenceProductStream(x).max().getAsInt();
              
        int product = grid.stream() // iterate on all grid elements
                .mapToInt(bestProductOnGridElement)
                .max()
                .getAsInt();
        
        printout(product);                                                                        
    }
}
