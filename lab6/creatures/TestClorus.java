package creatures;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the Clorus class   
 *  @authr ACLeiChen
 */

public class TestClorus {

    /* Replace with the magic word given in lab.
     * If you are submitting early, just put in "early" */
    public static final String MAGIC_WORD = "";

    @Test
    public void testBasics() {
        Clorus p = new Clorus(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), p.color());
        p.move();
        assertEquals(1.97, p.energy(), 0.01);
        p.move();
        assertEquals(1.94, p.energy(), 0.01);
        p.stay();
        assertEquals(1.93, p.energy(), 0.01);
        p.stay();
        assertEquals(1.92, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus p = new Clorus(2);
        Clorus newP = p.replicate();
        assertNotSame(p, newP);
        assertEquals(p.energy(), newP.energy(), 0.01);
        assertEquals(2, p.energy() + newP.energy(), 0.01);
    }

    @Test
    public void testChoose() {
        Clorus p = new Clorus(1.03);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        //Rule 1 test
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Plip());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        //Rule 2 test
        surrounded.replace(Direction.BOTTOM, new Empty());
        Plip k = new Plip();
        System.out.print("Plip k has an energy of " + k.energy() + "\n");
        System.out.print("Clorus p has an energy of " + p.energy() + "\n");
        actual = p.chooseAction(surrounded);
        expected = new Action(Action.ActionType.ATTACK, Direction.RIGHT);
        System.out.print("Clorus p has an energy of " + p.energy() + "\n");
        assertEquals(expected, actual);

        //Rule 3 test
        surrounded.replace(Direction.RIGHT, new Impassible());
        actual = p.chooseAction(surrounded);
        expected = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        assertEquals(expected, actual);

        //Rule 4 test
        p.move();
        p.move();
        assertEquals(0.97, p.energy(), 0.01);
        actual = p.chooseAction(surrounded);
        expected = new Action(Action.ActionType.MOVE, Direction.BOTTOM);
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestClorus.class));
    }
} 
