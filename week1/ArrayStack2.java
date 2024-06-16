/**
    A class of stacks whose entries are stored in an array.
    @author Frank M. Carrano
    @version 3.0
*/
import java.util.Arrays;
public class ArrayStack2<T> implements StackInterface<T>
{
   private T[] stack;    // array of stack entries
   private int topIndex; // index of top entry
   private static final int DEFAULT_INITIAL_CAPACITY = 50;
  
   public ArrayStack2()
   {
      this(DEFAULT_INITIAL_CAPACITY);
   } // end default constructor
  
   public ArrayStack2(int initialCapacity)
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
   
} // end ArrayStack here
