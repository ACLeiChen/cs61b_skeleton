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

    public static void main(String[] args) {
        Deque<Character> thisDeque = wordToDeque("horse");
        thisDeque.printDeque();
        System.out.print("\n");
        System.out.print(isPalindrome("racecar"));
    }
}
