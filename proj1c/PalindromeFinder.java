/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("words.txt");

        //OffByOne obo = new OffByOne();
        OffByN ob_five = new OffByN(5);

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && Palindrome.isPalindrome(word, ob_five)) {
                System.out.println(word);
            }
        }
    }
} 
