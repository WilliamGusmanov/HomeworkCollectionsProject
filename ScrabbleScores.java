package hwpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
	Scanner in; 
	String ScrabbleFileName = "ScrabblePoints.txt";
	String QWordsName = "Qwords.txt";
	File scrabbleFile;
	File qWords;
	Map<Character,Integer> scoreMap;
	/**
	 * default constructor
	 */
	ScrabbleScores(){
		scrabbleFile = new File(ScrabbleFileName);
		qWords = new File(QWordsName);
	}
	ScrabbleScores(String ScrabblePath, String QwordsPath){
		scrabbleFile = new File(ScrabblePath);
		qWords = new File(QwordsPath);
	}
	
	/**
	 * adds values to ScoreMap from ScrabblePoints.txt
	 * @throws FileNotFoundException
	 */
	public void createScoreMap() throws FileNotFoundException {
		Scanner textFile = new Scanner(scrabbleFile);	
		scoreMap = new HashMap<>();
		while(textFile.hasNext()) {
			String line = textFile.nextLine();
			System.out.println(line);
			String[] lines = line.split(" ");
			int value = Integer.parseInt(lines[0]);
			for (int i = 1; i < lines.length; i++) { 
				scoreMap.put(lines[i].toLowerCase().charAt(0), value);
			}		
		}
		textFile.close();
	}
	
	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public void sumScores() throws FileNotFoundException {
		Scanner textFile = new Scanner(qWords);
		while (textFile.hasNext()) {
			int sum = 0;
			String line = textFile.nextLine();	
			for (int i = 0; i < line.length(); i++) {
				System.out.println(line.charAt(i));
				sum += scoreMap.get(line.charAt(i));
			
			}
			System.out.printf("Score for word: %s is : %d%n",line,sum);
		}
		
		textFile.close();
	}
}
