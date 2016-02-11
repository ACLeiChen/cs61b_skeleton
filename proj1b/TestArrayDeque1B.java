import static org.junit.Assert.*;

import org.junit.Test;


public class TestArrayDeque1B {


    @Test
    public void testisEmpty() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
        assertTrue(sad1.isEmpty());
        assertEquals(0, sad1.size());

    }

    @Test
    public void testaddFirst() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
        sad1.addFirst(3);
        sad1.addFirst(2);
        sad1.addFirst(1);
        int expected = 3;
        int actual = sad1.size();
        assertEquals(expected, actual);
        assertFalse(sad1.isEmpty());
        expected = 1;
        actual = sad1.get(0);
        assertEquals(expected, actual);
        expected = 2;
        actual = sad1.get(1);
        assertEquals(expected, actual);
        expected = 3;
        actual = sad1.get(2);
        assertEquals(expected, actual);

    }

    @Test
    public void testaddLast() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
        sad1.addLast(1);
        sad1.addLast(2);
        sad1.addLast(3);
        int expected = 3;
        int actual = sad1.size();
        assertEquals(expected, actual);
        assertFalse(sad1.isEmpty());
        expected = 1;
        actual = sad1.get(0);
        assertEquals(expected, actual);
        expected = 2;
        actual = sad1.get(1);
        assertEquals(expected, actual);
        expected = 3;
        actual = sad1.get(2);
        assertEquals(expected, actual);
    }

    @Test
    public void testremoveFirst() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
        sad1.addFirst(3);
        sad1.addFirst(2);
        sad1.addFirst(1);
        sad1.removeFirst();
        int expected = 2;
        int actual = sad1.size();
        assertEquals(expected, actual);
        actual = sad1.get(0);
        assertEquals(2, actual);
        actual = sad1.get(1);
        assertEquals(3, actual);
        
        sad1.removeFirst();
        actual = sad1.get(0);
        assertEquals(3, actual);


    }

    @Test
    public void testremoveLast() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
        sad1.addFirst(3);
        sad1.addFirst(2);
        sad1.addFirst(1);
        sad1.removeLast();
        int expected = 2;
        int actual = sad1.size();
        assertEquals(expected, actual);
        actual = sad1.get(0);
        assertEquals(1, actual);
        actual = sad1.get(1);
        assertEquals(2, actual);

        sad1.removeLast();
        actual = sad1.get(0);
        assertEquals(1, actual);
    }
    
    @Test
    public void testget() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
        sad1.addFirst(3);
        sad1.addFirst(2);
        sad1.addFirst(1);
        int expected = 3;
        int actual = sad1.get(2);
        assertEquals(expected, actual);
        expected = 2;
        actual = sad1.get(1);
        assertEquals(expected, actual);
        expected = 1;
        actual = sad1.get(0);
        assertEquals(expected, actual);
        String expected2 = null;
        actual = sad1.get(10); //bug?
        assertEquals("Oh noooo!\n" +
                "This is bad:\n" +
                "An empty array should return " + expected2 + " but we get " + actual +
                ".\n", expected2, actual);
        //assertEquals(expected2, actual);
        //assertFalse(sad1.isEmpty());

    }




    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.TestRunner.runTests("all", TestArrayDeque1B.class);
    }


}