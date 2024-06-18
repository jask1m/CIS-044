import java.util.Arrays;
/**
   A class that implements the ADT list by using an array.
   The list is never full.
   @author Frank M. Carrano
*/
public class AList<T> implements ListInterface<T> 
{

	public static void main(String[] args) {
		System.out.println("Running tests for getLastIndex:");

    AList<String> list1 = new AList<>();
    list1.add("dog");
    list1.add("cat");
    list1.add("bird");
    list1.add("cat");
    list1.add("dog");

    // Test getLastIndex
    System.out.println("Expected output for 'cat': 4");
    System.out.println("Actual output for 'cat': " + list1.getLastIndex("cat"));
    System.out.println("Expected output for 'dog': 5");
    System.out.println("Actual output for 'dog': " + list1.getLastIndex("dog"));
    System.out.println("Expected output for 'bird': 3");
    System.out.println("Actual output for 'bird': " + list1.getLastIndex("bird"));
    System.out.println("Expected output for 'fish': -1");
    System.out.println("Actual output for 'fish': " + list1.getLastIndex("fish"));


		System.out.println("\nRunning tests for equals:");

		AList<String> list2 = new AList<>();
    list2.add("dog");
    list2.add("cat");
    list2.add("bird");
    list2.add("cat");
    list2.add("dog");

    AList<String> list3 = new AList<>();
    list3.add("dog");
    list3.add("cat");
    list3.add("bird");
    list3.add("cat");
    list3.add("dog");

    AList<String> list4 = new AList<>();
    list4.add("dog");
    list4.add("cat");
    list4.add("bird");
    list4.add("cat");

    // Test equals
    System.out.println("Expected output for list1.equals(list2): true");
    System.out.println("Actual output for list1.equals(list2): " + list1.equals(list2));
    System.out.println("Expected output for list1.equals(list3): true");
    System.out.println("Actual output for list1.equals(list3): " + list1.equals(list3));
    System.out.println("Expected output for list1.equals(list4): false");
    System.out.println("Actual output for list1.equals(list4): " + list1.equals(list4));
    System.out.println("Expected output for list1.equals(null): false");
    System.out.println("Actual output for list1.equals(null): " + list1.equals(null));
    System.out.println("Expected output for list1.equals(\"dog\"): false");
    System.out.println("Actual output for list1.equals(\"dog\"): " + list1.equals("dog"));
	}

	public int getLastIndex(T item) {
		// loop through each element starting from the end
		for (int i = numberOfEntries - 1; i >= 0; i--) {
			// check if each index is equal to the parameter item
			if (getEntry(i + 1).equals(item))
				// return the list index if equal
				return i + 1;
		}
		// return -1 if item not in list
		return - 1;
	}

	public boolean equals(Object other) {
		// first check if other points to same reference
		if (this == other) {
			return true;
		}

		// check if other's type is of AList
		if (other == null || getClass() != other.getClass()){
			return false;
		}

		// cast object into AList
		AList<?> otherList = (AList<?>) other;

		// check if the lengths vary
		if (otherList.getLength() != getLength())
			return false;

		// iterate and check for differences
		for (int i = 0; i < numberOfEntries; i++) {
			if (!list[i].equals(otherList.getEntry(i + 1)))
				return false;
		}

		return true;
	}

	private T[] list;   // array of list entries
    private int numberOfEntries;
    private static final int DEFAULT_INITIAL_CAPACITY = 25;  
    
    public AList() {
	this(DEFAULT_INITIAL_CAPACITY); // call next constructor
    } // end default constructor
    
    public AList(int initialCapacity) {
	numberOfEntries = 0;
	// the cast is safe because the new array contains null entries
	@SuppressWarnings("unchecked")
	    T[] tempList = (T[])new Object[initialCapacity];
	list = tempList; 
    } // end constructor
    
    public void add(T newEntry) {  
	ensureCapacity();
	list[numberOfEntries] = newEntry;
	numberOfEntries++;
    } // end add
    
    
    public boolean add(int newPosition, T newEntry) {  
	boolean isSuccessful = true;
	if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {	
	    ensureCapacity();
	    makeRoom(newPosition);				
	    list[newPosition - 1] = newEntry;
	    numberOfEntries++;
	}
	else
	    isSuccessful = false;
	return isSuccessful;
	
    } // end add
    
    public T remove(int givenPosition) { 
	
	T result = null; // return value

	if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {	
	    assert !isEmpty();
	    result = list[givenPosition - 1]; // get entry to be removed
	    
	    // move subsequent entries towards entry to be removed, 
	    // unless it is last in list
	    if (givenPosition < numberOfEntries)
		removeGap(givenPosition);
	    
	    numberOfEntries--;
	} // end if
	
	return result; // return reference to removed entry, or
	// null if either list is empty or givenPosition
	// is invalid
	
    } // end remove

    public void clear() { 
	numberOfEntries = 0;
    } // end clear

    public boolean replace(int givenPosition, T newEntry)  { 
	boolean isSuccessful = true;
	
	if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) { // test catches empty list 
	    assert !isEmpty();
	    list[givenPosition - 1] = newEntry;
	}
	else
	    isSuccessful = false;
	
	return isSuccessful;
	
    } // end replace
    
    public T getEntry(int givenPosition)  { 
	T result = null; // result to return
	
	if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
	    assert !isEmpty();
	    result = list[givenPosition - 1];
	} // end if
	
	return result;
	
    } // end getEntry
    
    public boolean contains(T anEntry) { 
	boolean found = false;
	for (int index = 0; !found && (index < numberOfEntries); index++) {
	    if (anEntry.equals(list[index]))
		found = true;
	} // end for
	
	return found;
    } // end contains
    
    public int getLength() {
	return numberOfEntries;
    } // end getLength
    
    public boolean isEmpty() {
	return numberOfEntries == 0; // or getLength() == 0
    } // end isEmpty
    
    public T[] toArray() {
	// the cast is safe because the new array contains null entries
	@SuppressWarnings("unchecked")
	    T[] result = (T[])new Object[numberOfEntries];
	
	for (int index = 0; index < numberOfEntries; index++) {
	    result[index] = list[index];
	} // end for
	
	return result;
    } // end toArray
    
    // Doubles the size of the array list if it is full.
    private void ensureCapacity() {
	if (numberOfEntries == list.length)
	    list = Arrays.copyOf(list, 2 * list.length);
    } // end ensureCapacity
    
    private void makeRoom(int newPosition) {
	assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
	
	int newIndex = newPosition - 1;
	int lastIndex = numberOfEntries - 1;
	
	// move each entry to next higher index, starting at end of
	// list and continuing until the entry at newIndex is moved
	for (int index = lastIndex; index >= newIndex; index--)
	    list[index + 1] = list[index];
    }  // end makeRoom
    
    private void removeGap(int givenPosition) {
	assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
	
	int removedIndex = givenPosition - 1;
	int lastIndex = numberOfEntries - 1;
	
	for (int index = removedIndex; index < lastIndex; index++)
	    list[index] = list[index + 1];
    } // end removeGap
 
} // end AList