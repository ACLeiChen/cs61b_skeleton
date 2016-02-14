/**
 * Created by ChenLei on 2016/2/14.
 */
public class OffByN implements CharacterComparator {

    private int off_number;

    public OffByN(int x) {
        off_number = x;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if ((x - y) == off_number||(y - x) == off_number) {
            return true;
        }return false;
    }
}
