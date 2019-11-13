/**
 * - Will read the novel War and Peace. Count how many times each word occurs in the book.
 * - Compare the use of Hashmap and TreeMap and compare performance time.
 * - Every time you read a word, remove all non-alphabetic characters
 * - convert all characters to lower case.
 * - look in map to see if the word exists already, if not, increment.
 *
 * - then print out all values in the hash structure
 * - print how many words in novel
 * @author William Gusmanov, Bryan Wu, Keval Varia
 * @version 2.0.0 11/12/2019
 */
package com.company;

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

    //variable declaration
    private static int totalwords;
    private static String fileName = "C:\\Users\\Keval\\IdeaProjects\\CollectionsProjectHomework\\src\\com\\company\\WarAndPeace.txt";
    private static File currentFile = new File(fileName);
    private static HashMap<String,Integer> wordHashMap = new HashMap<>();
    private static TreeMap<String,Integer> wordTreeMap = new TreeMap<>();
    private static Instant begin;
    private static Instant end;
    private static Duration timeDifference;


    /**
     * Function to read in the file
     * @param wordMap
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
            }
            end = Instant.now();
            timeDifference = Duration.between(begin, end);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * function to get an input file using the JFileChooser
     */
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
     */
    public static void readStatistics(Map<String, Integer> wordMap) {
        System.out.printf("Total words in War and Peace: %d%n", totalwords);
        System.out.printf("Duration: %s millisecndonds%n", timeDifference.toMillis());

        Iterator<Map.Entry<String,Integer>> itr = wordMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            System.out.printf("'%s' occured: %d%n", entry.getKey(),entry.getValue());
        }

    }


    /**
     * main to open the file, read it, and read statistics using an iterator
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Part II:");
        ReadFile(wordTreeMap);
        readStatistics(wordTreeMap);
    }
}

