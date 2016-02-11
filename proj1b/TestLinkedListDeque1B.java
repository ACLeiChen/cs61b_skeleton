import static org.junit.Assert.*;

import org.junit.Test;


public class TestLinkedListDeque1B {


    @Test
    public void testisEmpty() {
        StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
        assertTrue(slld1.isEmpty());

    }

    @Test
    public void testaddFirst() {
        StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
        slld1.addFirst(3);
        slld1.addFirst(2);
        slld1.addFirst(1);
        int expected = 3;
        assertEquals(expected, slld1.size());
        assertFalse(slld1.isEmpty());

    }

    @Test
    public void testaddLast() {
        StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
        slld1.addFirst(1);
        slld1.addLast(2);
        slld1.addLast(3);
        int expected = 3;
        assertEquals(expected, slld1.size());
        assertFalse(slld1.isEmpty());
        int actual = slld1.get(2);
        assertEquals(expected, actual);
    }

    @Test
    public void testremoveFirst() {
        StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
        slld1.addFirst(3);
        slld1.addFirst(2);
        slld1.addFirst(1);
        slld1.removeFirst();
        int expected = 2;
        int actual = slld1.size();
        assertEquals(expected, actual);
        actual = slld1.get(0);
        assertEquals(2, actual);
        actual = slld1.get(1);
        assertEquals(3, actual);

    }

    @Test
    public void testremoveLast() {
        StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
        slld1.addFirst(3);
        slld1.addFirst(2);
        slld1.addFirst(1);
        slld1.removeLast();
        int expected = 2;
        int actual = slld1.size();
        assertEquals(expected, actual);

        actual = slld1.get(0);
        assertEquals(1, actual);
        Integer expected2 = 2;
        //actual = slld1.get(1);   //bug

        assertEquals("Oh noooo!\n" +
                "This is bad:\n" +
                "The 1st number should be " + expected2 + " but we get " + slld1.get(1), expected2, slld1.get(1));

    }

    @Test
    public void testget() {
        StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
        slld1.addFirst(9);
        slld1.addFirst(7);
        slld1.addFirst(5);
        slld1.addFirst(3);
        slld1.addFirst(1);
        int expected = 5;
        int actual = slld1.get(2);
        assertEquals(expected, actual);
        expected = 3;
        actual = slld1.get(1);
        assertEquals(expected, actual);
        expected = 1;
        actual = slld1.get(0);
        assertEquals(expected, actual);
        String expected2 = null;
        actual = slld1.get(10); //bug
        assertEquals(expected2, actual);


    }

    @Test
    public void printDeque() {
        StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
        slld1.addFirst(3);
        slld1.addFirst(2);
        slld1.addFirst(1);
        slld1.printDeque();

    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.TestRunner.runTests("all", TestLinkedListDeque1B.class);
    }


}