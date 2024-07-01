import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedLinkedDictionary<K, V> {
	private Node firstNode;
	private int numberOfEntries;

	public static void main(String[] args) {
		UnsortedLinkedDictionary<String, Integer> dict = new UnsortedLinkedDictionary<>();

		// Test isEmpty() on empty dictionary
		System.out.println("Test isEmpty() on empty dictionary");
		System.out.println("Expected output: true");
		System.out.println("Actual output: " + dict.isEmpty());
		System.out.println();

		// Test getSize() on empty dictionary
		System.out.println("Test getSize() on empty dictionary");
		System.out.println("Expected output: 0");
		System.out.println("Actual output: " + dict.getSize());
		System.out.println();

		// Test add() and contains()
		System.out.println("Test add() and contains()");
		dict.add("one", 1);
		dict.add("two", 2);
		dict.add("three", 3);
		System.out.println("Expected output: true");
		System.out.println("Actual output: " + dict.contains("two"));
		System.out.println("Expected output: false");
		System.out.println("Actual output: " + dict.contains("four"));
		System.out.println();

		// Test getValue()
		System.out.println("Test getValue()");
		System.out.println("Expected output: 2");
		System.out.println("Actual output: " + dict.getValue("two"));
		System.out.println("Expected output: null");
		System.out.println("Actual output: " + dict.getValue("four"));
		System.out.println();

		// Test add() with existing key
		System.out.println("Test add() with existing key");
		Integer oldValue = dict.add("two", 22);
		System.out.println("Expected output: 2");
		System.out.println("Actual output: " + oldValue);
		System.out.println("Expected output: 22");
		System.out.println("Actual output: " + dict.getValue("two"));
		System.out.println();

		// Test remove()
		System.out.println("Test remove()");
		Integer removedValue = dict.remove("two");
		System.out.println("Expected output: 22");
		System.out.println("Actual output: " + removedValue);
		System.out.println("Expected output: false");
		System.out.println("Actual output: " + dict.contains("two"));
		System.out.println();

		// Test remove() non-existent key
		System.out.println("Test remove() non-existent key");
		removedValue = dict.remove("four");
		System.out.println("Expected output: null");
		System.out.println("Actual output: " + removedValue);
		System.out.println();

		// Test getSize() after additions and removal
		System.out.println("Test getSize() after additions and removal");
		System.out.println("Expected output: 2");
		System.out.println("Actual output: " + dict.getSize());
		System.out.println();

		// Test clear()
		System.out.println("Test clear()");
		dict.clear();
		System.out.println("Expected output: true");
		System.out.println("Actual output: " + dict.isEmpty());
		System.out.println("Expected output: 0");
		System.out.println("Actual output: " + dict.getSize());
		System.out.println();

		// Test add() after clear
		System.out.println("Test add() after clear");
		dict.add("new", 100);
		System.out.println("Expected output: 100");
		System.out.println("Actual output: " + dict.getValue("new"));
		System.out.println();
		System.out.println();
	}

	public UnsortedLinkedDictionary() {
		firstNode = null;
		numberOfEntries = 0;
	} // end default constructor

	public V add(K key, V value) {
		V val = null;
		Node currentNode = firstNode;
		while (currentNode != null) {
			if (currentNode.getKey().equals(key)) {
				val = currentNode.getValue();
				currentNode.setValue(value);
				return val;
			}
			currentNode = currentNode.getNextNode();
		}
		firstNode = new Node(key, value, firstNode);
		numberOfEntries++;
		return val;
	} // end add

	public V remove(K key) {
		V val = null;

		// handle case when linked list is empty or when 'key' is invalid
		if (isEmpty() || key == null) {
			return val;
		}

		// handle case when 'key' is the first node
		if (firstNode.getKey().equals(key)) {
			val = firstNode.getValue();
			firstNode = firstNode.getNextNode();
			numberOfEntries--;
			return val;
		}

		// handle remove
		Node prevNode = firstNode;
		Node currNode = firstNode.getNextNode();
		while (currNode != null) {
			if (currNode.getKey().equals(key)) {
				val = currNode.getValue();
				prevNode.setNextNode(currNode.getNextNode());
				numberOfEntries--;
				return val;
			}
			prevNode = currNode;
			currNode = currNode.getNextNode();
		}
		return val;
	} // end remove

	public V getValue(K key) {
		Node currentNode = firstNode;
		while (currentNode != null) {
			if (currentNode.getKey().equals(key)) {
				return currentNode.getValue();
			}
			currentNode = currentNode.getNextNode();
		}
		return null;
	} // end getValue

	public boolean contains(K key) {
		Node currentNode = firstNode;
		while (currentNode != null) {
			if (currentNode.getKey().equals(key))
				return true;
			currentNode = currentNode.getNextNode();
		}
		return false;
	} // end contains

	public boolean isEmpty() {
		return numberOfEntries == 0;
	} // end isEmpty

	public int getSize() {
		return numberOfEntries;
	} // end getSize

	public final void clear() {
		firstNode = null;
		numberOfEntries = 0;
	} // end clear

	private class Node {
		private K key;
		private V value;
		private Node next;

		private Node(K searchKey, V dataValue) {
			key = searchKey;
			value = dataValue;
			next = null;
		} // end constructor

		private Node(K searchKey, V dataValue, Node nextNode) {
			key = searchKey;
			value = dataValue;
			next = nextNode;
		} // end constructor

		private K getKey() {
			return key;
		} // end getKey

		private V getValue() {
			return value;
		} // end getValue

		private void setValue(V newValue) {
			value = newValue;
		} // end setValue

		private Node getNextNode() {
			return next;
		} // end getNextNode

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} // end setNextNode
	} // end Node
} // end UnsortedLinkedDictionary
