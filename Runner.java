package hwpackage;

public class Runner {
/**
 * - key implements the Comparable
 * - TreeMap needs to override hascode 
 * - HashMap needs to override equals
 * - the key will be a string which has a built-in compareTo method 
 *   as well as an override for hashCode and equals. 
 * 	 @param args
 * 
 * Part 1.) read in a short file of words. first letter q, 2nd letter is not a u. 
 * 	Scrabble score for each word.
 * 	4 F H V W Y. all these letters get a score of 4.
 * 	Using ScrabblePoints.txt, take in this file and create the letters with score. 
 * 	Letter as a key and score as the value.
 *  
 *  After inputing the rules into the map, read the records in "Q words.txt"
 *  Go through each word, and calculate a total score for every word
 *  return the score for the word when reading the record in the file.
 * 
 */
	public static void main(String[] args) {

		ScrabbleScores scrabby = new ScrabbleScores();
		
	}

}
