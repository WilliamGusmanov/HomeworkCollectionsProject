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
 *  
 *   * @author William Gusmanov, Bryan Vu, Kev
 */

package hwpackage;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScrabbleScores {

    /** scanner is used to read scrabbleFile and qWords  */
    private Scanner in;
    /** scrabbleFile to determine point value of each char */
    private String scrabbleName; 
    private File scrabbleFile;
    /** words to score */
    private String qWordsName;	
    private File qWords;
    /** map that stores a character as a key and integer as the value
     * the integer is used as a "point value" of a char 
     */
    private Map<Character,Integer> scoreMap;


    /**
     * default constructor
     */
    ScrabbleScores(){
        scrabbleFile = new File("ScrabblePoints.txt");
        qWords = new File("Qwords.txt");
    }//end of default constructor
	
    /**
    * constructor that takes path names of both files. 
    */
    ScrabbleScores(String ScrabbleName, String QwordsName){
		scrabbleFile = new File(ScrabbleName);
		qWords = new File(QwordsName);
    }//end constructor definition

    /**
     * adds values to ScoreMap from ScrabblePoints.txt
     * @throws FileNotFoundException
     */
    public void createScoreMap() throws FileNotFoundException {
        Scanner textFile = new Scanner(scrabbleFile);
        scoreMap = new HashMap<>();
        char character = ' ';
        int points = 0;

        while(textFile.hasNextLine()){
            String line = textFile.nextLine();

            if(line.charAt(1) == '0') {
                points = 10;
            }//end if 
            else {
                points = Character.getNumericValue(line.charAt(0));
            }//end else

            for(int i = 2; i < line.length(); i++){
                if(line.charAt(i) != ' ') {
                    character = line.charAt(i);
                    scoreMap.put(character, points); // insert in the HashMap
                }//end if in for
            }//end for loop
        }//end while loop
    textFile.close();
    }//end method definition


    /**
     * Method to add scores 
     * Score is derived from map
     * @throws FileNotFoundException
     */
    public void sumScores() throws FileNotFoundException {
        Scanner textFile = new Scanner(qWords);

        while (textFile.hasNext()) {
            int sum = 0;
            String line = textFile.next();
            for (int i = 0; i < line.length(); i++) {
                String letter = line.substring(i, i+1);
                int value = scoreMap.get(letter.toUpperCase().charAt(0));
                sum += value;
            }//end for loop
            System.out.printf("Score for word: %s is : %d%n",line,sum);
        }//end while loop
        textFile.close();
    }//end method definition
}//end class
