// Name: Praise Olukilede
// USC NetID: olukiled
// CS 455 PA4
// Fall 2022

public class ScoreTable {
   // point value system
   private static final int[] pointGrade = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
//                                          {a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z}

   /**
    * This gets the total point value for the given word
    * @param string PRE : a word which only contains characters between [a-zA-Z]
    * @return the total point value of the word
    */
   public static int getWordValue(String string) {
      int total = 0;
      // char[] cArr = string.toCharArray();
      for(int i = 0; i < string.length(); i++){
         char x = string.charAt(i);
         if (Character.isUpperCase(x)) {
            x = Character.toLowerCase(x);
         }
         total += point(x);
      }
      return total;
   }

   /**
    * gets point value of character and returns
    * @param ch PRE: a letter between [a-z]
    * @return point value of character
    */
   private static int point(char ch) {
      if (ch == 'a') {
         return pointGrade[0];
      } else {
         // 'd' - 'a' = 3
         return pointGrade[ch - 'a'];
      }
   }
}
