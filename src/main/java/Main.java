import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "log.txt";
        File file = new File(path);
        PrintStream print = new PrintStream(file);
        PageGenerator pageGenerator = new PageGenerator();
        int[] sizes = {10, 15, 20};
        int[] frameSizes = {3, 5, 7};
        List<List<Integer>> allRefStrings = generateAllRefStrings(sizes, pageGenerator);
        for (int i = 0; i < allRefStrings.size(); i++) {
            print.println("---------- SIZE " + sizes[i] + " ----------\n");
            List<Integer> refStringList = allRefStrings.get(i);
            int[] refString = convertListToArray(refStringList);
            for (int j = 0; j < frameSizes.length; j++) {
                print.println("-------- FRAME SIZE " + frameSizes[j] + " --------");
                print.println("-----FIFO-----");
                int currFaultsFIFO = runAlgorithm(new FIFO(frameSizes[j]), refString, print);
                print.println("-----LRU-----");
                int currFaultsLRU = runAlgorithm(new LRU(frameSizes[j]), refString, print);
                print.println("-----OPT-----");
                int currFaultsOPT = runAlgorithm(new OPT(frameSizes[j], refString), refString, print);
                int[] results = {currFaultsFIFO, currFaultsLRU, currFaultsOPT};
                int[] min = findMin(results);
                String stringAlg = "";
                if (min[0] == 0) {
                    stringAlg = "FIFO";
                } else if (min[0] == 1) {
                    stringAlg = "LRU";
                } else {
                    stringAlg = "OPT";
                }
                print.println("Winner Is: " + stringAlg + " With " + min[1] + " Faults!\n");
            }
        }
    }

    //returns the min of an array of integers
    private static int[] findMin(int[] nums) {
        int minIndex = -1;
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minNum) {
                minNum = nums[i];
                minIndex = i;
            }
        }
        int[] result = {minIndex, minNum};
        return result;
    }

    //returns a List of Integer lists that represent all reference strings generated
    private static List<List<Integer>> generateAllRefStrings(int[] sizes, PageGenerator pageGenerator) {
        List<List<Integer>> allRefStrings = new ArrayList<>();
        for (int i = 0; i < sizes.length; i++) {
            allRefStrings.add(pageGenerator.getRandomReferenceString(sizes[i]));
        }
        return allRefStrings;
    }

    //converts a list to an array
    private static int[] convertListToArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    public static int runAlgorithm(ReplacementAlgorithm alg, int[] refString,
                                    PrintStream print) throws FileNotFoundException {
        for (int i = 0; i < refString.length; i++) {
            alg.insert(refString[i]);
        }
        print.println("Faults: " + alg.getPageFaultCount() + "\n");
        return alg.getPageFaultCount();
    }
}
