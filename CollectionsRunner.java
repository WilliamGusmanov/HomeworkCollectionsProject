/**
 * - Will read the novel War and Peace. Count how many times each word occurs in the book.
 * - Compare the use of Hashmap and TreeMap and compare performance time.
 * - Every time you read a word, remove all non-alphabetic characters
 * - convert all characters to lower case.
 * - look in map to see if the word exists already, if not, increment.
 *
 * - then print out all values in the hash structure
 * - print how many words in novel
 * -
 * @author William Gusmanov, Bryan Vu, Kev
 *
 */

package hwpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.time.Instant;
import java.time.Duration;
import javax.swing.JFileChooser;


public class CollectionsRunner {
	
	/** int to store the total words of the file (War and Peace) */
    static int totalwords;
    /** file path name */
    static String fileName = "WarAndPeace.txt";
    static File currentFile = new File(fileName);
    /** Hashmap to store the wordcount of each word within the file(War and Peace)*/
    static HashMap<String,Integer> wordHashMap = new HashMap<>();
    /** treemap that is converted from hashmap to make all the keys in alphabetical order */
    static TreeMap<String,Integer> wordTreeMap = new TreeMap<>();
    /** instant object to make the beginning of the timer */
    static Instant begin;
    /** instant object to mark the end of the timer */
    static Instant end;
    /** the duration is the time difference between the beginning and the end */
    static Duration timeDifference;

    /**
     * Static method to read file and time how long it takes to count all the words
     * Uses Duration to to keep track of the time of the while loop while "reading"
     * the file
     * @param wordMap the map used to store the word and the wordcount
     */
    public static void ReadFile(Map<String, Integer> wordMap){
        totalwords = 0;
        begin = Instant.now();
        try {
            Scanner reader = new Scanner(currentFile);
            reader.useDelimiter("[^A-Za-z]+");
            while(reader.hasNext()) { //&& totalwords != 150) {
                String inputLine = reader.next();
                inputLine = inputLine.toLowerCase();
                wordMap.computeIfPresent(inputLine, (key, val) -> val += 1);
                wordMap.putIfAbsent(inputLine, 1);
                totalwords++;
            }//end try
            end = Instant.now();
            timeDifference = Duration.between(begin, end);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }//end catch
    }//end readFile

    	
    public static void inputFile() {
        Scanner console = new Scanner(System.in);
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Choose File");
        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String outname = jFileChooser.getSelectedFile().getAbsolutePath();
            File validFile = new File (outname); //input file path as chosen using JFileChooser
            currentFile = validFile;
        }//end if statement
        console.close();
    }//end function definition

    /**
     * reads all the words and the number
     * @param wordMap the map that stores a word as a key, and the wordcount as a the value of the map
     */
    public static void readStatistics(Map<String, Integer> wordMap) {
        System.out.printf("Total words in War and Peace: %d%n", totalwords);
        System.out.printf("Duration: %s millisecndonds%n", timeDifference.toMillis());

        Iterator<Map.Entry<String,Integer>> itr = wordMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            System.out.printf("'%s' occured: %d%n", entry.getKey(),entry.getValue());
        }//end while loop
    }//end method definition
    
    public static void main(String[] args) {
	System.out.println("Open your War and Peace text file.");
	inputFile();
        System.out.println("Part II:");
	System.out.println("HashMap");
	ReadFile(wordHashMap);
	readStatistics(wordHashMap);
        System.out.println("TreeMap");
	ReadFile(wordTreeMap);
        readStatistics(wordTreeMap);
    }//end main
    
}//end class

