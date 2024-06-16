/**
    A class of stacks whose entries are stored in an array.
    @author Frank M. Carrano
    @version 3.0
*/
import java.util.Arrays;
public class ArrayStack2Lab<T> implements StackInterface<T>
{
    public static void main(String[] args)  {
		// Add you lab tests here
        
        // TEST for display method
        System.out.println("Running tests for the 'display' method:\n");

        // Test Case 1
        ArrayStack2Lab<String> test1stack = new ArrayStack2Lab<String>();
        test1stack.push("dog");
        test1stack.push("cat");
        test1stack.push("fish");
        test1stack.push("crocodile");
        System.out.println("test 1: \nThe expected output is: \ncrocodile\nfish\ncat\ndog\nThe actual output is:");
        test1stack.display();

        // Test Case 2
        ArrayStack2Lab<String> test2stack = new ArrayStack2Lab<String>();
        System.out.println("test 2: \nThe expected output is: \nThe stack is empty\nThe acutal output is:");
        test2stack.display();

        // TEST for remove method
        System.out.println("\nRunning tests for the 'remove' method:\n");

        // Test Case 3
        ArrayStack2Lab<String> test3stack = new ArrayStack2Lab<String>();
        test3stack.push("apple");
        test3stack.push("orange");
        test3stack.push("pear");
        System.out.println("test 3: \nExpected output is: 2");  
        System.out.println("Actual output is: " + test3stack.remove(2));

        // Test Case 4
        ArrayStack2Lab<String> test4stack = new ArrayStack2Lab<String>();
        test4stack.push("apple");
        test4stack.push("orange");
        test4stack.push("pear");
        System.out.println("test 4: \nExpected output is: 3");  
        System.out.println("Actual output is: " + test4stack.remove(8));
	}

    // Problem 1
    public void display() {
        // check if the stack is empty
        if (topIndex == -1)
            // print appropriate message
            System.out.println("The stack is empty");
        
        // iterate through the stack starting from top
        for (int i = topIndex; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    // Problem 2
    public int remove(int n) {
        /* the method removes the n top most entries for a stack. If the stack contains less than n items, the stack becomes empty. The method returns the number of items removed. */

        // assign a counter 
        int numRemoved = 0;

        // handle cases where n is larger than contents in stack
        if (n >= topIndex + 1) {
            numRemoved = topIndex + 1;
            clear();
            return numRemoved;
        }

        // pop n times and update counter accordingly
        for (int i = 0; i < n; i++) {
            if (!isEmpty()) {
                pop();
                numRemoved++;
            }
        }

        // return counter
        return numRemoved;
    }

    
   private T[] stack;    // array of stack entries
   private int topIndex; // index of top entry
   private static final int DEFAULT_INITIAL_CAPACITY = 50;
  
   public ArrayStack2Lab()
   {
      this(DEFAULT_INITIAL_CAPACITY);
   } // end default constructor
  
   public ArrayStack2Lab(int initialCapacity)
   {
      // the cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
      topIndex = -1;
  } // end constructor
  
   public void push(T newEntry)   
   {  
       ensureCapacity();      
       topIndex++;      
       stack[topIndex] = newEntry;   
   } // end push   
   
   private void ensureCapacity()   
   {      if (topIndex == stack.length - 1) // if array is full, double size of array      
	   stack = Arrays.copyOf(stack, 2 * stack.length);   
   } // end ensureCapacity
   
   public T peek()   
   {  
       T top = null;         
       if (!isEmpty())       
	   top = stack[topIndex];             
       return top;   
   } // end peek
   
   public T pop()
   {
       T top = null;
       
       if (!isEmpty()) {
	   top = stack[topIndex];
	   stack[topIndex] = null;
	   topIndex--; 
       } // end if
       
       return top;
   } // end pop
   
   public boolean isEmpty()
   {   
       return topIndex < 0;
   } // end isEmpty

   public void clear()
   {
       for(int i = 0; i <= topIndex; ++i)
	   stack[i] = null;
       topIndex = -1;
   }
   
} // end ArrayStack2Lab
