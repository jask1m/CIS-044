public class ArrayQueueLab<T> implements QueueInterface<T> {

	public static void main(String[] args) {
		System.out.println("Running tests for problem 4:");
		ArrayQueueLab<String> queue = new ArrayQueueLab<>();
		queue.enqueue("dog");
		queue.enqueue("cat");
		queue.enqueue("bird");
		ArrayQueueLab<String> queue2 = new ArrayQueueLab<>();
		queue2.enqueue("tshirt");
		queue2.enqueue("jeans");
		queue2.enqueue("hat");
		queue.splice(queue2);
		System.out.println("The expected output is:");
		System.out.println("dog\n cat\n bird\n tshirt\n jeans\n hat\n");
		System.out.println("The actual output is: ");
		while (!queue.isEmpty()) {
			System.out.println(queue.dequeue());
		}

		ArrayQueueLab<String> queue01 = new ArrayQueueLab<>();
		queue01.enqueue("red");
		queue01.enqueue("yellow");
		queue01.enqueue("blue");
		ArrayQueueLab<String> queue02 = new ArrayQueueLab<>();
		queue02.enqueue("pasta");
		queue02.enqueue("pizza");
		queue02.enqueue("burger");
		queue01.splice(queue02);
		System.out.println("\nThe expected output is:");
		System.out.println("red\n yellow\n blue\n pasta\n pizza\n burger\n");
		System.out.println("The actual output is: ");
		while (!queue01.isEmpty()) {
			System.out.println(queue01.dequeue());
		}

		System.out.println("\nRunning tests for problem 5:");
		ArrayQueueLab<String> queue3 = new ArrayQueueLab<>();
		queue3.enqueue("dog");
		queue3.enqueue("cat");
		queue3.enqueue("bird");
		boolean res = queue3.enqueueNoDuplicate("bird");
		String output;
		if (res) {
			output = "true";
		} else {
			output = "false";
		}
		System.out.println("The expected output is: false");
		System.out.println("The actual output is: " + output);
		boolean res2 = queue3.enqueueNoDuplicate("crocodile");
		String output2;
		if (res2) {
			output2 = "true";
		} else {
			output2 = "false";
		}
		System.out.println("The expected output is: true");
		System.out.println("The actual output is: " + output2);
	}

	// Problem 1, part 1
	public void splice(QueueInterface<T> anotherQueue) {
		// check if anotherQueue still contains items
		while (!anotherQueue.isEmpty()) {
			T item = anotherQueue.dequeue();
			enqueue(item);
		}
	}

	// Problem 1, part 2
	public boolean enqueueNoDuplicate(T item) {
		// loop through the queue
		int currentIndex = frontIndex;

		while (currentIndex != (backIndex + 1) % queue.length) {
			if (queue[currentIndex].equals(item)) {
				return false;
			}
			currentIndex = (currentIndex + 1) % queue.length;
		}
		enqueue(item);
		return true;
	}

	private T[] queue; // circular array of queue entries and one unused location
	private int frontIndex;
	private int backIndex;
	private static final int DEFAULT_INITIAL_CAPACITY = 50;

	public ArrayQueueLab() {
		this(DEFAULT_INITIAL_CAPACITY);
	} // end default constructor

	public ArrayQueueLab(int initialCapacity) {
		// the cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity + 1];
		queue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity;
	} // end constructor

	public void enqueue(T newEntry) {
		ensureCapacity();
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
	} // end enqueue

	public T getFront() {
		T front = null;
		if (!isEmpty())
			front = queue[frontIndex];

		return front;
	} // end getFront

	public T dequeue() {
		T front = null;
		if (!isEmpty()) {
			front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
		} // end if

		return front;
	} // end dequeue

	private void ensureCapacity() {
		if (frontIndex == ((backIndex + 2) % queue.length)) { // if array is full,
			T[] oldQueue = queue;
			int oldSize = oldQueue.length;
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[2 * oldSize];
			queue = tempQueue;
			for (int index = 0; index < oldSize - 1; index++) {
				queue[index] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldSize;
			} // end for

			frontIndex = 0;
			backIndex = oldSize - 2;
		} // end if
	} // end ensureCapacity

	public boolean isEmpty() {
		return frontIndex == ((backIndex + 1) % queue.length);
	} // end isEmpty

	public void clear() {
		if (!isEmpty()) {
			for (int index = frontIndex; index != backIndex; index = (index + 1) % queue.length)
				queue[index] = null;
			queue[backIndex] = null;
		}
		frontIndex = 0;
		backIndex = queue.length - 1;
	}

} 