package com.company;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 Part 1.) read in a short file of words. first letter q, 2nd letter is not a u.
 * 	Scrabble score for each word.
 * 	4 F H V W Y. all these letters get a score of 4.
 * 	Using ScrabblePoints.txt, take in this file and create the letters with score.
 * 	Letter as a key and score as the value.
 *
 *  After inputing the rules into the map, read the records in "Q words.txt"
 *  Go through each word, and calculate a total score for every word
 *  return the score for the word when reading the record in the file.
 */

public class ScrabbleScores {

    //used to read in the file
    private Scanner in;
    private File scrabbleFile;
    private File qWords;
    private Map<Character,Integer> scoreMap;


    /**
     * default constructor
     */
    ScrabbleScores(){
        scrabbleFile = new File("C:\\Users\\Keval\\IdeaProjects\\CollectionsProjectHomework\\src\\com\\company\\ScrabblePoints.txt");
        qWords = new File("C:\\Users\\Keval\\IdeaProjects\\CollectionsProjectHomework\\src\\com\\company\\Qwords.txt");
    }


    /**
     * adds values to ScoreMap from ScrabblePoints.txt
     * @throws FileNotFoundException
     */
    public void createScoreMap() throws FileNotFoundException {
        //variable declaration
        Scanner textFile = new Scanner(scrabbleFile);
        scoreMap = new HashMap<>();
        char character = ' ';
        int points = 0;

        while(textFile.hasNextLine()){                      //read a line
            String line = textFile.nextLine();              //save the line

            //get the integer from the front of the line
            if(line.charAt(1) == '0') {
                points = 10;
            }
            else {
                points = Character.getNumericValue(line.charAt(0));
            }

            //get the rest of the values from the string
            for(int i = 2; i < line.length(); i++){
                if(line.charAt(i) != ' ') {
                    character = line.charAt(i);
                    scoreMap.put(character, points); // insert in the HashMap
                }
            }

        }
//        System.out.println(scoreMap); // print the letters and it's value
        textFile.close();
    }


    /**
     * sum the score for every character per word and output it for all words in the file
     * @return
     * @throws FileNotFoundException
     */
    public void sumScores() throws FileNotFoundException {
        //variable declaration
        Scanner textFile = new Scanner(qWords);

        //read a word, sum every character and output it, then repeat for all other words in file
        while (textFile.hasNext()) {
            int sum = 0;
            String line = textFile.next();
            for (int i = 0; i < line.length(); i++) {
                String letter = line.substring(i, i+1);
                int value = scoreMap.get(letter.toUpperCase().charAt(0));
                sum += value;
            }
            System.out.printf("Score for word: %s is : %d%n",line,sum);
        }
        textFile.close();
    }
}
