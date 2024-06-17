import java.util.* ;

public class Lab5
{
	public static void main(String[] args)  {
		// Add you tests here
	
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
					first = mid +1;
				}
				else {
					last = mid -1;
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
					if (a[j].compareTo(a[i]) < 0) {
						smallest = j;
					}
					else if (a[j].compareTo(a[i]) > 0) {
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
		//  
} 
