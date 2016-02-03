/**circular sentinel topology*/
import java.util.StringJoiner;

public class LinkedListDeque {
	public class Node {
		public int item;     /* Equivalent of first */
		public Node previous;
		public Node next; /* Equivalent of rest */

		public Node(int i, Node p, Node h) {
			item = i;
			previous = p;
			next = h;		
		}
	} 

	private Node sentinel;
	private int size; 

	/** Creates an empty list. */
	public LinkedListDeque() {
		size = 0;
		sentinel = new Node(0, null, null);
	}

	public void addFirst(int x) {
		Node oldFrontNode = sentinel.next;
		Node newNode = new Node(x, sentinel, oldFrontNode);
		sentinel.next = newNode;
		oldFrontNode.previous = newNode;
		size += 1;
	}

	/** Puts an item at the back of the list. */
	public void addLast(int x) {
		Node oldLastNode = sentinel.previous;
		Node newNode = new Node(x, oldLastNode, sentinel);
		oldLastNode.next = newNode;
		sentinel.previous = newNode;
		size += 1;
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

	/**Prints the items in the Deque from first to last, separated by a space.*/
	public void printDeque() {
		StringJoiner separator = new StringJoiner(" ");

		for (int i = 0; i < size; i++) {
			int newItem = get(i);
			separator.add(Integer.toString(newItem));
			System.out.print(separator.toString());
		}

		/************************************
		An example of StringJoiner. 
		StringJoiner separator = new StringJoiner(" ");
		String str = "Ho";
		int n = 3;
		for (int i = 0; i<n; i++){
		    sj.add(str);
		}
		String text = sj.toString();
		System.out.println(text); //Ho Ho Ho
		*************************************/
	}

	/**Removes and returns the item at the front of the Deque. If no such item exists, returns null.*/
	public int removeFirst() {
		if (size == 0 ) {
			return 0;//for int type
		}
		Node oldSecondNode = sentinel.next.next;
		sentinel.next.previous = null;
		sentinel.next.next = null;
		int remove_value = sentinel.next.item;
		sentinel.next.item = 0;
		sentinel.next = oldSecondNode;
		oldSecondNode.previous = sentinel;
		size -= 1;
		return remove_value;
	}

	/**Removes and returns the item at the back of the Deque. If no such item exists, returns null.*/
	public int removeLast() {
		return 0;
	}

	/**Gets the item at the given index*/
	public int get(int index) {
		return 0;
	}

	/**Same as get, but uses recursion.*/
	public int getRecursive(int index) {
		return 0;
	}
    public static void main(String[] args) {

    }

}