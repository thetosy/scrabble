// Name: Praise Olukilede
// USC NetID: olukiled
// CS 455 PA4
// Fall 2022

import java.io.FileNotFoundException;
import java.util.*;

public class WordFinder {
   /**
    * Takes in the user input of the string of characters to get anagrams of and prints out the list of words and
    * their associated point value
    * @param args filename containing list of words (optional)
    */
   public static void main(String[] args) {
      String filename = "";
      // Map<Integer, ArrayList<String>> scoreWordMap = new TreeMap<>(Collections.reverseOrder());
      // ScoreTable scoreTable = new ScoreTable();
      Scanner in = new Scanner(System.in);
      String userInput;
      try{
         if(args.length > 0){
            filename = args[0];
         }else{
            filename = "sowpods.txt";
         }
         AnagramDictionary dictionary = new AnagramDictionary(filename);
         System.out.println("Type . to quit.");
         // boolean keepRunning = true;
         while(true){
            System.out.print("Rack? ");
            userInput = in.next();
            if(userInput.equals(".")){
               break;
            }
            ArrayList<String> rack = Rack.getSubsets(userInput);
            Map<Integer, ArrayList<String>> scoreWordMap = createScoreWordMap(rack,dictionary);
            int wordCount = getTotalWordCount(scoreWordMap);
            System.out.println("We can make " + wordCount + " words from " + "\"" + userInput + "\"");
            print(scoreWordMap, wordCount);
         }

      }
      catch (IllegalDictionaryException e){
         System.out.println(e.getLocalizedMessage());
         System.out.println("Exiting program.");
      }
      catch (FileNotFoundException e){
         System.out.println("ERROR: Dictionary file " + "\"" + filename + "\"" + " does not exist.");
         System.out.println("Exiting program.");
      }
      // System.out.println("Exiting program.");
   }

   /**
    * prints out the all the words sorted by score
    * @param map contains scores and the associated list of words to that score
    * @param count total number of words in the map
    */
   public static void print(Map<Integer, ArrayList<String>> map, int count){
      if(count > 0){
         System.out.println("All of the words with their scores (sorted by score):");
         for(Integer key : map.keySet()){
            ArrayList<String> words = map.get(key);
            Collections.sort(words);
            for(String each : words){
               System.out.println(key + ": " + each);
            }
         }
      }
   }

   /**
    * gets the total number of words in the map
    * @param map contains scores and the associated list of words to that score
    * @return count of number of words in the map
    */
   public static int getTotalWordCount(Map<Integer, ArrayList<String>> map){
      int total = 0;
      for(Integer key : map.keySet()){
         total += map.get(key).size();
      }
      return total;
   }

   /**
    * creates the score-word mapping. Each score has a list of words that is gotten from the dictionary and subsets of
    * the word
    *
    * @param rack list of all subset of the word
    * @param dictionary contains all the words available to be used
    * @return a mapping of a list of words to their associated score value
    */
   public static Map<Integer, ArrayList<String>> createScoreWordMap(ArrayList<String> rack, AnagramDictionary dictionary){
      Map<Integer, ArrayList<String>> scoreWordMap = new TreeMap<>(Collections.reverseOrder());
      for(String word: rack){
         if(word.length() >= 2) {
            // System.out.println(word);
            ArrayList<String> values = dictionary.getAnagramsOf(word);
            if (values == null) {
               continue;
            }
            for (String eachWord : values) {
               int score = ScoreTable.getWordValue(eachWord);
               ArrayList<String> scoreWord;
               if (scoreWordMap.containsKey(score)) {
                  scoreWord = scoreWordMap.get(score);
               } else {
                  scoreWord = new ArrayList<>();
               }
               scoreWord.add(eachWord);
               scoreWordMap.put(score, scoreWord);
            }
         }
      }
      return scoreWordMap;
   }
}
