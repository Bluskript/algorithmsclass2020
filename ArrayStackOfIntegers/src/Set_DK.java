import java.util.Collection;
import java.util.Iterator;

/**
 * Assignment: Set_YI
 * 
 * Description: Implement the ADT Set, Set_YI.java with the methods below. The
 * Set ADT uses a LinkedList structure.
 * 
 * In mathematics, a Set is a well-defined collection of distinct objects,
 * considered as an object in its own right. In this implementation of Set, the
 * Set ADT behaves like a LinkedList but it doesn't allow duplicates. Create a
 * Node class.
 * 
 * Language: Java 11
 * 
 * @author Danil Korennykh
 * @created December 9, 2020
 */
public class Set_DK<T> {
	private Node head;
	private Node tail;
	private Integer sizeNum;

	public void add(T val) {
		var added = new Node(val);

		Node n = this.head;

		if (n == null) {
			this.head = added;
			return;
		}

		while (n.getNext() != null) {
			if (n.getValue() == val)
				return;

			n = n.getNext();
		}

		this.tail.setNext(added);
		this.tail = added;
		this.sizeNum++;
	}

	public void addAll(Collection<T> c) {
		c.forEach(v -> this.add(v));
		this.sizeNum += c.size();
	}

	public void clear() {
		// push all the work on the GC to clean up the mess
		this.head = null;
		this.tail = null;
		this.sizeNum = 0;
	}

	public boolean contains(T val) {
		Node n = this.head;

		if (n == null) {
			return val == n;
		}

		while (n.getNext() != null) {
			if (n.getValue() == val)
				return true;

			n = n.getNext();
		}
		return false;
	}

	public boolean containsAll(Collection<T> c) {
		for (T val : c) {
			if (!this.contains(val)) {
				return false;
			}
		}
		return true;
	}

	public boolean equals(Set_DK<T> other) {
		Node n = this.head;

		if (n == null) {
			return other.contains(null);
		}

		while (n.getNext() != null) {
			if (!other.contains(n.getValue()))
				return false;

			n = n.getNext();
		}
		return true;
	}

	public boolean isEmpty() {
		return this.head == null;
	}

	public Iterator<Node> iterator() {
		return new SetIterator(this.head);
	}

	public boolean remove(T val) {
		Node n = this.head;

		if (n == null) {
			return false;
		}

		if (n.getNext().getValue() == val) {
			n.setNext(n.getNext().getNext());
			return true;
		}

		while (n.getNext() != null) {
			n = n.getNext();

			if (n.getNext().getValue() == val) {
				n.setNext(n.getNext().getNext());
				this.sizeNum--;
				return true;
			}
		}

		return false;
	}

	public void removeAll(Collection<T> c) {
		c.forEach(v -> this.remove(v));
		this.sizeNum -= c.size();
	}

	public int size() {
		return this.sizeNum;
	}

	class Node {
		private T data;
		private Node next;

		Node(T data) {
			this.data = data;
		}

		void setNext(Node next) {
			this.next = next;
		}

		Node getNext() {
			return this.next;
		}

		T getValue() {
			return this.data;
		}
	}

	private class SetIterator implements Iterator<Node> {
		private Node pos;

		SetIterator(Node start) {
			this.pos = start;
		}

		public Node next() {
			return pos.getNext();
		}

		@Override
		public boolean hasNext() {
			return pos.getNext() != null;
		}
	}
}
