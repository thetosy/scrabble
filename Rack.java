// Name: Praise Olukilede
// USC NetID: olukiled
// CS 455 PA4
// Fall 2022

import java.util.*;

/**
   A Rack of Scrabble tiles
 */

public class Rack {

   /**
    * Finds all subsets of the word
    * PRE: string must be a word with characters between [a-z]
    *
    * @param string word to get subset of
    * @return all subset of the given word
    */
   public static ArrayList<String> getSubsets(String string){
      // char[] stringChar = string.toCharArray();
      Map<Character, Integer> uniqueWords = new TreeMap<>();
      for(int i = 0; i < string.length(); i++){
         char letter = string.charAt(i);
         if(uniqueWords.containsKey(letter)){
            int count = uniqueWords.get(letter);
            uniqueWords.put(letter, count + 1);
         } else {
            uniqueWords.put(letter, 1);
         }
      }
      int[] letterCount = new int[uniqueWords.keySet().size()];
      StringBuilder finalWord = new StringBuilder();
      int count = 0;
      for(char letter : uniqueWords.keySet()){
         finalWord.append(letter);
         letterCount[count] = uniqueWords.get(letter);
         count++;
      }
      return allSubsets(finalWord.toString(), letterCount, 0);
   }

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      return allCombos;
   }
}
