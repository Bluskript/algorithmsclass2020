import java.util.Iterator;

/**
 * Assignment: ArrayStackOfIntegers Implementation Description: Implement the
 * ArrayStackOfIntegers ADT using arrays
 * 
 * Language: Java 11
 * 
 * @author Danil Korennykh
 * @created December 9, 2020
 */
public class ArrayStackOfIntegers_DK implements Iterable<Integer> {
	private Integer[] items;
	private int n;

	public ArrayStackOfIntegers_DK(int capacity) {
		this.items = new Integer[capacity];
		this.n = 0;
	}

	public boolean isEmpty() {
		return this.n == 0;
	}

	public boolean isFull() {
		return this.n == this.items.length;
	}

	public void push(Integer item) {
		this.items[n] = item;
		n++;
		return;
	}

	public Integer pop() {
		n--;
		return this.items[n];
	}

	public Integer getSize() {
		return this.n;
	}

	public Iterator<Integer> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<Integer> {
		public Integer next() {
			return pop();
		}

		@Override
		public boolean hasNext() {
			return n == 0;
		}
	}
}
