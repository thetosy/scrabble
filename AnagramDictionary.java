// Name: Praise Olukilede
// USC NetID: olukiled
// CS 455 PA4
// Fall 2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * A dictionary of all anagram sets.
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   /**
    * Representation invariant:
    * anaDictionary cannot be null
    * <p>
    * <p>
    */

   private final Map<String, ArrayList<String>> anaDictionary;

   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.
    *
    * @param fileName the name of the file to read from
    * @throws FileNotFoundException      if the file is not found
    * @throws IllegalDictionaryException if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
         IllegalDictionaryException {
      anaDictionary = new HashMap<>();
      // scanner created to iterate through the file contents
      Scanner in = new Scanner(new File(fileName));
      while (in.hasNext()) {
         // String word = in.next().toLowerCase();
         String word = in.next();
         char[] sortWord = word.toCharArray();
         // sort the word alphabetically
         Arrays.sort(sortWord);
         String sortedWord = new String(sortWord);
         // add words to the map with the sorted form as the key
         ArrayList<String> anaWord;
         if (anaDictionary.containsKey(sortedWord)) {
            anaWord = anaDictionary.get(sortedWord);
         } else {
            anaWord = new ArrayList<>();
         }
         // if duplicate word is found
         if (anaWord.contains(word)) {
            throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + word);
         }
         anaWord.add(word);
         anaDictionary.put(sortedWord, anaWord);
      }
      in.close();
   }


   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    *
    * @param string to process
    * @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      // char[] sortWord = string.toLowerCase().toCharArray();
      char[] sortWord = string.toCharArray();
      Arrays.sort(sortWord);
      return anaDictionary.get(new String(sortWord));
   }
}
