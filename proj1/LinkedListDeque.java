/**circular sentinel topology*/
import java.util.StringJoiner;

public class LinkedListDeque<Iguodala> implements  Deque<Iguodala>{
	public class Node {
		public Iguodala item;     /* Equivalent of first */
		public Node previous;
		public Node next; /* Equivalent of rest */

		public Node(Iguodala i, Node p, Node h) {
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
		sentinel = new Node(null, null, null);
		/**At a circular sentinel topology, an empty list means
		 its previous and next both have to point at itself.*/
      	sentinel.previous = sentinel;
      	sentinel.next = sentinel;
	}
	@Override
	public void addFirst(Iguodala x) {
		Node oldFrontNode = sentinel.next;
		Node newNode = new Node(x, sentinel, oldFrontNode);
		sentinel.next = newNode;
		oldFrontNode.previous = newNode;
		size += 1;
	}
   

	/** Puts an item at the back of the list. */
	@Override
	public void addLast(Iguodala x) {
		Node oldLastNode = sentinel.previous;
		Node newNode = new Node(x, oldLastNode, sentinel);
		oldLastNode.next = newNode;
		sentinel.previous = newNode;
		size += 1;
	}

	/**Returns true if deque is empty, false otherwise.*/
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}return false;
	}

	/**Returns the number of items in the Deque.*/
	@Override
	public int size() {
		return size;
	}

	/**Prints the items in the Deque from first to last,
	 separated by a space.*/
	@Override
	public void printDeque() {
		StringJoiner separator = new StringJoiner(" ");

		for (int i = 0; i < size; i++) {
			Iguodala newItem = get(i);
			separator.add(newItem.toString());
			
		}
		System.out.print(separator.toString());
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

	/**Removes and returns the item at the front of the Deque.
	 If no such item exists, returns null.*/
	@Override
	public Iguodala removeFirst() {
		if (size == 0 ) {
			return null;
		}
		Node oldSecondNode = sentinel.next.next;
		sentinel.next.previous = null;
		sentinel.next.next = null;
		Iguodala remove_value = sentinel.next.item;
		sentinel.next.item = null;
		sentinel.next = oldSecondNode;
		oldSecondNode.previous = sentinel;
		size -= 1;
		return remove_value;
	}

	/**Removes and returns the item at the back of the Deque.
	 If no such item exists, returns null.*/
	@Override
	public Iguodala removeLast() {
		if (size == 0 ) {
			return null;//for int type
		}
		Node oldSecondLastNode = sentinel.previous.previous;
		sentinel.previous.previous = null;
		sentinel.previous.next = null;
		Iguodala remove_value = sentinel.previous.item;
		sentinel.previous.item = null;
		sentinel.previous = oldSecondLastNode;
		oldSecondLastNode.next = sentinel;
		size -= 1;
		return remove_value;
	}

	/**Gets the item at the given index.
	 If no such item exists, returns null.*/
	@Override
	public Iguodala get(int index) {
		if (index >= size) {
			return null;
		}
		Node p = sentinel.next;
		for (int i = 0; i< index; i++) {
			p = p.next;
		}
		return p.item;
	}

	/**Same as get, but uses recursion.*/
	public Iguodala getRecursive(int index) {
		if (index >= size) {
			return null;
		}return RecursiveHelp(index).item;
	}

	/**I use a helper function to involve recursion.
	 We need to discuss about it*/
	public Node RecursiveHelp(int index) {
		if (index == 0) {
			return sentinel.next;
		}return RecursiveHelp(index - 1).next;
	}
	/**************************************
	main() for easy test in terminal
    public static void main(String[] args) {
      LinkedListDeque k = new LinkedListDeque();
      k.addFirst(3);
      k.addLast(7);
      k.printDeque();
    }
	***************************************/
}