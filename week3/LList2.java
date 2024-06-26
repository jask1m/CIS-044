/**
   A class that implements the ADT list by using a chain of nodes.
   The chain has both a head reference and a tail reference.
   @author Frank M. Carrano
   @version 3.0
*/
public class LList2<T> implements ListInterface<T>
{
	public static void main(String[] args) {
    // Test getLastIndex method
    System.out.println("Testing getLastIndex method:");
    LList2<String> list1 = new LList2<>();
    list1.add("apple");
    list1.add("banana");
    list1.add("cherry");
    list1.add("date");
    list1.add("banana");
    list1.add("elderberry");

    System.out.println("Last index of 'banana': " + list1.getLastIndex("banana") + " (Expected: 5)");
    System.out.println("Last index of 'apple': " + list1.getLastIndex("apple") + " (Expected: 1)");
    System.out.println("Last index of 'elderberry': " + list1.getLastIndex("elderberry") + " (Expected: 6)");
    System.out.println("Last index of 'fig': " + list1.getLastIndex("fig") + " (Expected: -1)");

    LList2<String> emptyList = new LList2<>();
    System.out.println("Last index of 'apple' in empty list: " + emptyList.getLastIndex("apple") + " (Expected: -1)");

    LList2<String> listWithNull = new LList2<>();
    listWithNull.add("apple");
    listWithNull.add(null);
    listWithNull.add("banana");
    listWithNull.add(null);
    System.out.println("Last index of null: " + listWithNull.getLastIndex(null) + " (Expected: 4)");

    // Test equals method
    System.out.println("\nTesting equals method:");
    LList2<String> list2 = new LList2<>();
    list2.add("apple");
    list2.add("banana");
    list2.add("cherry");
    list2.add("date");
    list2.add("banana");
    list2.add("elderberry");

    LList2<String> list3 = new LList2<>();
    list3.add("apple");
    list3.add("banana");
    list3.add("cherry");

    LList2<Integer> list4 = new LList2<>();
    list4.add(1);
    list4.add(2);
    list4.add(3);

    System.out.println("list1 equals list2: " + list1.equals(list2) + " (Expected: true)");
    System.out.println("list1 equals list3: " + list1.equals(list3) + " (Expected: false)");
    System.out.println("list1 equals emptyList: " + list1.equals(emptyList) + " (Expected: false)");
    System.out.println("emptyList equals emptyList: " + emptyList.equals(emptyList) + " (Expected: true)");
    System.out.println("list1 equals null: " + list1.equals(null) + " (Expected: false)");
    System.out.println("list1 equals 'apple': " + list1.equals("apple") + " (Expected: false)");
    System.out.println("list1 equals list4: " + list1.equals(list4) + " (Expected: false)");
}

	public int getLastIndex(T item) {
		Node current = firstNode;
		int last = -1;
		for (int i = 1; i <= numberOfEntries; i++) {
			if ((item == null && current.getData() == null) || (item != null && current.getData().equals(item))) {
				last = i;
			}
			current = current.getNextNode();
		}
		return last;
	}

	public boolean equals(Object other) {
		// check if the two objects hold same reference
		if (this == other) {
			return true;
		}

		// check if other's type is of AList
		if (other == null || getClass() != other.getClass()) {
			return false;
		}

		// cast other to type LList2
		LList2<?> otherList = (LList2<?>) other;
		// check for same number of entries
		if (numberOfEntries != otherList.numberOfEntries) {
			return false;
		}

		// iterate and check for differences
		for (int i = 1; i <= numberOfEntries; i++) {
			T element = this.getEntry(i);
			Object otherElement = otherList.getEntry(i);

			if (!element.equals(otherElement)) {
				return false;
			}
		}
		return true;
	}

	private Node firstNode; // head reference to first node
	private Node lastNode;  // tail reference to last node
	private int  numberOfEntries;

	public LList2()
	{
		clear();
	} // end default constructor

	public final void clear() // NOTICE clear is not final in interface and that is OK
	{
		firstNode = null;
  		lastNode = null;
		numberOfEntries = 0;
	} // end clear
  
	public void add(T newEntry) 	  // OutOfMemoryError possible
	{
		Node newNode = new Node(newEntry); // create new node

		if (isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);
		
  		lastNode = newNode;
  		numberOfEntries++;
	}  // end add

   public boolean add(int newPosition, T newEntry)  // OutOfMemoryError possible	                                                 
	{
      boolean isSuccessful = true;

      if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) 
      {
         Node newNode = new Node(newEntry);

         if (isEmpty())
         {
            firstNode = newNode;
            lastNode = newNode;
         }
         else if (newPosition == 1)
         {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
         }
         else if (newPosition == numberOfEntries + 1)
         {
            lastNode.setNextNode(newNode);
            lastNode = newNode;
         } 
         else
         {
            Node nodeBefore = getNodeAt(newPosition - 1);
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);
         } // end if	    
         numberOfEntries++;
      }
      else
         isSuccessful = false;
       
	   return isSuccessful;
	} // end add

	public T remove(int givenPosition)
	{
      T result = null;                 // return value

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         assert !isEmpty();
         if (givenPosition == 1)        // case 1: remove first entry
         {
            result = firstNode.getData();     // save entry to be removed 
            firstNode = firstNode.getNextNode();
            if (numberOfEntries == 1)
               lastNode = null; // solitary entry was removed
            }
            else                           // case 2: givenPosition > 1
            {
               Node nodeBefore = getNodeAt(givenPosition - 1);
               Node nodeToRemove = nodeBefore.getNextNode();
               Node nodeAfter = nodeToRemove.getNextNode();
               nodeBefore.setNextNode(nodeAfter);  // disconnect the node to be removed
               result = nodeToRemove.getData();  // save entry to be removed

               if (givenPosition == numberOfEntries)
                  lastNode = nodeBefore; // last node was removed
         } // end if

         numberOfEntries--;
      } // end if

      return result;                   // return removed entry, or 
                                      // null if operation fails
	} // end remove

	public boolean replace(int givenPosition, T newEntry)
	{
		boolean isSuccessful = true;

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {   
      	assert !isEmpty();

			Node desiredNode = getNodeAt(givenPosition);
			desiredNode.setData(newEntry);
      }    
		else
			isSuccessful = false;
			
		return isSuccessful;
   } // end replace

   public T getEntry(int givenPosition)
   {
      T result = null;  // result to return
      
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
         result = getNodeAt(givenPosition).getData();
     	} // end if
      
      return result;
   } // end getEntry

	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while
		
		return found;
	} // end contains

   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

   public boolean isEmpty()
   {
   		boolean result;
   		
      	if (numberOfEntries == 0) // or getLength() == 0
      	{
      		assert firstNode == null;
      		result = true;
      	}
      	else
      	{
      		assert firstNode != null;
      		result = false;
      	} // end if
      	
      	return result;
   } // end isEmpty
	
   public T[] toArray()
   {
      // the cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // warning: [unchecked] unchecked cast

	  int index = 0;
     Node currentNode = firstNode;
	  while ((index < numberOfEntries) && (currentNode != null))
	  { 
	    result[index] = currentNode.getData();
	    currentNode = currentNode.getNextNode();
       index++; 
	  } // end while
     
     return result;
   } // end toArray

	// Returns a reference to the node at a given position.
	// Precondition:  List is not empty; 1 <= givenPosition <= numberOfEntries.	
	private Node getNodeAt(int givenPosition)
	{
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		
      if (givenPosition == numberOfEntries)
         currentNode = lastNode;
      else if (givenPosition > 1)      // traverse the chain to locate the desired node
		{
         for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();
		} // end if
      
		assert currentNode != null;
		return currentNode;
	} // end getNodeAt
	
	private class Node 
	{
		private T data;  // data portion
		private Node next;  // next to next node

		private Node(T dataPortion)//  PRIVATE or PUBLIC is OK
		{
			data = dataPortion;
			next = null;	
		} // end constructor

		private Node(T dataPortion, Node nextNode)//  PRIVATE or PUBLIC is OK
		{
			data = dataPortion;
			next = nextNode;	
		} // end constructor

		private T getData()
		{
			return data;
		} // end getData
		
		private void setData(T newData)
		{
			data = newData;
		} // end setData
		
		private Node getNextNode()
		{
			return next;
		} // end getNextNode
		
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		} // end setNextNode
	} // end Node
} // end LList2

