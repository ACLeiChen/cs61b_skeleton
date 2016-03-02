package editor;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.geometry.VPos;
import javafx.scene.text.Font;

/**
 * Created by ChenLei on 2016/2/28. It's basically a Linked list structure with
 * circular sentinel implementation. currentText is a node which is always pointing
 * at the current node. Most of the code is copied from proj1/LinkedListDeque.java.
 */
public class TextList {

    private class Node {
        private Text item;     /* Equivalent of first */
        private Node previous;
        private Node next; /* Equivalent of rest */

        private Node(Text i, Node p, Node h) {
            item = i;
            previous = p;
            next = h;
        }
    }

    private Node sentinel;
    private Node currentText;
    private int size;
    //2D vecter for rendering
    private Text[][] textVector;
    /** Creates an empty list. */
    public TextList() {
        size = 0;
        sentinel = new Node(null, null, null);
        currentText = sentinel;
        /**At a circular sentinel topology, an empty list means
         its previous and next both have to point at itself.*/
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        textVector = new Text[10][10];
    }


    /**insert a node based on the current Position*/
    public void insert(Text insertedText, Group root) {
        root.getChildren().add(insertedText);
        Node oldNodeAfterCurrent = currentText.next;
        Node newNode = new Node(insertedText, currentText, oldNodeAfterCurrent);
        currentText.next = newNode;
        oldNodeAfterCurrent.previous = newNode;
        //update currentText
        currentText = newNode;
        size += 1;
    }

    /**delete a node based on the current Position*/
    public void delete(Group root) {
        if((size != 0) && (currentText != sentinel)) {
            Node previousNode = currentText.previous;
            Node nextNode = currentText.next;
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
            //remove it from root.
            root.getChildren().remove(currentText.item);
            currentText.item = null;
            currentText.previous = null;
            currentText.next = null;
            //update currentText
            currentText = previousNode;
            if (size > 0) {
                size -= 1;
            }
        }
    }
    /**returns the currentText*/
    public Text getCurrentText() {
        return currentText.item;
    }
    /**returns previous text*/
    public Text getPreviousText() {
        //if next node is not sentinel
        if (currentText.previous.item != null) {
            currentText = currentText.previous;
            return currentText.item;
        }return null;

    }
    /**returns next text*/
    public Text getNextText() {
        //if next node is not sentinel
        if (currentText.next.item != null) {
            currentText = currentText.next;
            return currentText.item;
        }return null;

    }

    /**find corresponding Text and update the currentText*/
    public void findText(int cursorX, int cursorY) {

    }
    /**map cursorX and cursorY to the current text*/
    public void textMapping(int x, int y, Text thisText) {

    }
    /**if x or y is larger than textVector’s xsize or ysize , then resize.*/
    public void resize(int x,int y) {

    }

    /**render all the text, also map them with their position*/
    public void renderText(int windowWidth, String fontName, int fontSize) {
        if (size != 0) {
            /**for the first text*/
            int x = 0;
            int y = 0;
            Node thisNode = new Node(null, null, null);
            Text thisText = new Text();
            thisNode = sentinel;
            thisNode = thisNode.next;
            thisText = thisNode.item;
            //The above 2 line cannot be put in the helper method, because the condition
            // of while-loop also uses thisNode.
            renderTextHelper(thisText, x, y, fontName, fontSize);
            /**for the rest text, iterate*/
            if (size > 1) {
                while (thisNode.next != sentinel) {
                    x = x + (int)thisText.getLayoutBounds().getWidth();
                    thisNode = thisNode.next;
                    thisText = thisNode.item;
                    int currentWidth = (int)thisText.getLayoutBounds().getWidth();
                    // word wraps at the edge of the window, position needs to be changed
                    if ((x + currentWidth) > windowWidth) {
                        x = 0;
                        y = y + (int)thisText.getLayoutBounds().getHeight();
                    }
                    renderTextHelper(thisText, x, y, fontName, fontSize);
                }
            }
        }
    }

    private void renderTextHelper(Text thisText, int x, int y, String fontName, int fontSize) {
        thisText.setX(x);
        thisText.setY(y);
        thisText.setTextOrigin(VPos.TOP);
        thisText.setFont(Font.font(fontName, fontSize));
        //thisText.toFront();
        textMapping(x, y, thisText);
    }

    /**Returns true if deque is empty, false otherwise.*/
    
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }return false;
    }

    /**Returns the number of items in the Deque.*/
    
    public int size() {
        return size;
    }

    /**Removes and returns the item at the back of the Deque.
     If no such item exists, returns null.*/
    
    public Text removeLast() {
        if (size == 0 ) {
            return null;//for int type
        }
        Node oldSecondLastNode = sentinel.previous.previous;
        sentinel.previous.previous = null;
        sentinel.previous.next = null;
        Text remove_value = sentinel.previous.item;
        sentinel.previous.item = null;
        sentinel.previous = oldSecondLastNode;
        oldSecondLastNode.next = sentinel;
        size -= 1;
        currentText = oldSecondLastNode;
        return remove_value;
    }

    /**probably need to rewrite get()*/
    public Text get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = sentinel.next;
        for (int i = 0; i< index; i++) {
            p = p.next;
        }
        return p.item;
    }

}