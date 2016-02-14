/**
 * Created by ChenLei on 2016/2/14.
 */
public class Palindrome {

    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> thisDeque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++)
        {
            thisDeque.addLast(word.charAt(i));
        }
        return thisDeque;
    }

    public static boolean isPalindrome(String word) {
        if ((word.length() == 1)||(word.length() == 0)) {
            return true;
        }
        Deque<Character> thisDeque= wordToDeque(word);
        return twocharcomparsion(thisDeque, 0);

    }
    /**a helper method for isPalindrome() to use recursion*/
    private static boolean twocharcomparsion(Deque<Character> thisDeque, int index) {
        char a_char = thisDeque.get(index);
        char b_char = thisDeque.get(thisDeque.size() - 1 - index);
        if ((a_char != b_char)||(index >= thisDeque.size() / 2)) {
            return false;
        }
        if (index == (thisDeque.size()/2) - 1) {
            return  true;
        }
        return twocharcomparsion(thisDeque, index + 1);
    }


    /**reloading isPalinDrome() with HOF, phase 2 */
    public static boolean isPalindrome(String word, CharacterComparator cc) {
        if ((word.length() == 1)||(word.length() == 0)) {
            return true;
        }
        Deque<Character> thisDeque= wordToDeque(word);
        return twocharcomparsion(thisDeque, 0, cc);
    }

    /**a helper method for the reloading isPalindrome() to use recursion, phase 2*/
    private static boolean twocharcomparsion(Deque<Character> thisDeque, int index, CharacterComparator cc) {
        char a_char = thisDeque.get(index);
        char b_char = thisDeque.get(thisDeque.size() - 1 - index);
        if ((!cc.equalChars(a_char, b_char))||(index >(thisDeque.size() / 2))) {
            return false;
        }
        if (index == (thisDeque.size()/2) - 1) {
            return  true;
        }
        return twocharcomparsion(thisDeque, index + 1, cc);
    }

    public static void main(String[] args) {
        Deque<Character> thisDeque = wordToDeque("horse");
        thisDeque.printDeque();
        System.out.print("\n");
        OffByOne obo = new OffByOne();
        System.out.print(isPalindrome("flake", obo));
    }
}
