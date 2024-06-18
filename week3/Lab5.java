import java.util.* ;

public class Lab5
{
	public static void main(String[] args) {
		// tests go here
		Integer[] testArray = {100, 12, 7, 120, 4, 14, 66, 5, 3};
		// Sort the array
		Arrays.sort(testArray);

		System.out.println("Sorted array: " + Arrays.toString(testArray));
		System.out.println("Running tests for inArrayIterativeSorted:");

		System.out.println("Expected output for 14: true");
		System.out.println("Actual output for 14: " + inArrayIterativeSorted(testArray, 14));

		System.out.println("Expected output for 66: true");
		System.out.println("Actual output for 66: " + inArrayIterativeSorted(testArray, 66));

		System.out.println("Expected output for 13: false");
		System.out.println("Actual output for 13: " + inArrayIterativeSorted(testArray, 13));

		System.out.println("Expected output for 100: true");
		System.out.println("Actual output for 100: " + inArrayIterativeSorted(testArray, 100));

		System.out.println("Expected output for 1: false");
		System.out.println("Actual output for 1: " + inArrayIterativeSorted(testArray, 1));

		System.out.println("Running tests for modifiedSelectionSort:");

		Integer[] arr1 = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3 };
    modifiedSelectionSort(arr1, 0);
    System.out.println("Expected output for arr1: [1, 1, 2, 3, 3, 4, 5, 5, 6, 9]");
    System.out.println("Actual output for arr1: " + Arrays.toString(arr1));

    // Test Case 2: String array
    String[] arr2 = { "banana", "apple", "orange", "pear", "grape" };
    modifiedSelectionSort(arr2, 0);
    System.out.println("Expected output for arr2: [apple, banana, grape, orange, pear]");
    System.out.println("Actual output for arr2: " + Arrays.toString(arr2));
}

	// Problem 1
	public static <T extends Comparable<? super T>> boolean inArrayIterativeSorted(T[] anArray, T anEntry) {
		int first = 0;
		int last = anArray.length - 1;
		while( first <= last) {
				int mid = (first + last) / 2;
				if (anArray[mid].compareTo(anEntry) == 0) {
					return true;
				} 
				else if (anArray[mid].compareTo(anEntry) > 0) {
					last = mid -1;
				}
				else {
					first = mid +1;
				}
		}
		return false;
	}
	
	// Problem 2
    public static <T extends Comparable<? super T>> void modifiedSelectionSort(T[] a, int n) {
			int last_index = a.length - 1;

			for (int i = 0; i < a.length / 2; i++) {
				int smallest = i;
				int largest = i;

				for (int j = i + 1; j <= last_index; j++) {
					if (a[j].compareTo(a[smallest]) < 0) {
						smallest = j;
					}
					else if (a[j].compareTo(a[largest]) > 0) {
						largest = j;
					}
				}

				if (i != smallest) {
					T tmp_sml = a[i];
					a[i] = a[smallest];
					a[smallest] = tmp_sml;
				}

				if (i == smallest) {
					largest = smallest;
				}
				
				if (last_index != largest) {
					T tmp = a[last_index];
					a[last_index] = a[largest];
					a[largest] = tmp;
				}

				last_index--;
			}
    } 
    // For problem 2, how many comparisons are necessary to sort n values?  Write your answer below
		// In this sorting method, there are two for loops. The outer for loop iterates n/2 times, considering that n is the length.
		// The inner loop is where the comparisons are being made.
		// there are n-i, and n-i comparisons to find the smallest and largest element respectfully.
		// the total comparisons in the inner loop is 2(n-i)
		// the inner loop comparisons can be considered as O(n)
		// since the outer loop iterates n/2 times, the total comparisons can be O(n) * O(n/2)
		// this gives a total of O(n^2 / 2) comparisons, or more simply, O(n^2)
} 
