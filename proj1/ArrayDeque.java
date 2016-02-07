import java.util.StringJoiner;

public class ArrayDeque { 

	private int[] items;
	private int size; 
	private static int resize_factor = 2;
	private int nextFirst;
	private int nextLast; 

	/** Creates an empty list. */
	public ArrayDeque() {
		size = 0;
		items = new int[8];
		nextFirst = 4;
		nextLast = 5;
	}

   	/** Puts an item at the start of the Deque. */
    public void addFirst(int x) {
    	/**the maximam size of a Deque is size = items.length - 2*/
   		if (size == items.length - 2) {
   			resize(items.length*resize_factor);
   		}
   		items[nextFirst] = x;
   		nextFirst = minusOne(nextFirst);
		size += 1;
	}
   

	/** Puts an item at the back of the Deque. */
	public void addLast(int x) {
		if (size == items.length - 2) {
   			resize(items.length*resize_factor);
   		}
   		items[nextLast] = x;
   		nextLast = plusOne(nextLast);
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

	/**Prints the items in the Deque from first to last,
	 separated by a space.*/
	public void printDeque() {
		StringJoiner separator = new StringJoiner(" ");
		/**for loop parameters should be considered.*/
		for (int i = 0; i < size; i++) {
			int newItem = get(i);
			//separator.add(newItem.toString());
			separator.add(Integer.toString(newItem));
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
	public int removeFirst() {
		/**First, check if the Deque is empty*/
		if (plusOne(nextFirst) == nextLast) {
			return 0;
		}
		int remove_value = items[plusOne(nextFirst)];
		items[plusOne(nextFirst)] = 0;
		nextFirst = plusOne(nextFirst);
		maintainusage();
		size -= 1;
		return remove_value;
	}

	/**Removes and returns the item at the back of the Deque.
	 If no such item exists, returns null.*/
	public int removeLast() {
		/**First, check if the Deque is empty*/
		if (plusOne(nextFirst) == nextLast) {
			return 0;
		}
		int remove_value = items[minusOne(nextLast)];
		items[minusOne(nextLast)] = 0;
		nextLast = minusOne(nextLast);
		maintainusage();
		size -= 1;
		return remove_value;
	}

	/**Gets the item at the given index.
	 If no such item exists, returns null.*/
	public int get(int index) {
		if (index >= size) {
			return 0;
		}
		return items[index + plusOne(nextFirst)];
	}

	/**resize the Deque whenever it's full*/
   	private void resize(int capacity) {
   		int[] a = new int[capacity];
    	a = copyToNewArray(a);
    	items = a; 
   	}

   	/**whenever the array usage ratio is < 25%, it will be chopped in half*/
   	private void maintainusage() {
   		if ((size + 2) * 4 < items.length) {
   			int[] a = new int[items.length>>2];
   			a = copyToNewArray(a);
   			items = a;
   			
   		}
   	}

   	/**copy the items array in the correct order to a new array a.
   	Then return a.*/
   	private int[] copyToNewArray(int[] a) {
   		if (nextFirst < nextLast) {
   			System.arraycopy(items, nextFirst + 1, a, 1, size);
   		}else {
   			System.arraycopy(items, nextFirst + 1, a, 1, items.length - 1 - nextFirst);
   			System.arraycopy(items, 0, a, items.length - nextFirst, nextLast);
   		}
   		nextFirst = 0;
   		nextLast = size + 1;
   		return a;

   	}

   	/**consider the circular topology, when index is 7
   	(if the length of item is 8), plusOne should be 0*/
   	private int plusOne(int index) {
   		if (index == items.length - 1) {
   			return 0;
   		}
   		return index + 1;
   	}

   	/**consider the circular topology, when index is 0, 
   	minusOne should be 7(if the length of item is 8)*/
   	private int minusOne(int index) {
   		if (index == 0) {
   			return items.length - 1;
   		}
   		return index - 1;
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