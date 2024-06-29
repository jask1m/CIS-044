import java.util.*;

public class Lab7 {

	public static void main(String[] args) {
		// Add you tests here
		System.out.println("Running tests for 'findMissing' method:");
		int[] a1 = { 1, 2, 3, 4, 6 };
		System.out.println("expected output: 5");
		System.out.println("Actual output: " + findMissing(a1));
		int[] a2 = { 1, 2, 4, 5, 6, 7 };
		System.out.println("expected output: 3");
		System.out.println("Actual output: " + findMissing(a2));

		System.out.println("Running tests for 'countingSort' method:");
		int[] a3 = { 9, 2, 4, 8, 9, 4, 3, 2, 8, 1, 2, 7, 2, 5 };
		System.out.println("unsorted array: [9, 2, 4, 8, 9, 4, 3, 2, 8, 1, 2, 7, 2, 5]");
		countingSort(a3, 9);
		System.out.print("sorted array: " + Arrays.toString(a3));
		System.out.println("\n2nd test");
		int[] a4 = { 3, 2, 4, 8, 9, 4, 3, 2, 8, 1, 2, 7, 2, 5 };
		System.out.println("unsorted array: [3, 2, 4, 8, 9, 4, 3, 2, 8, 1, 2, 7, 2, 5]");
		countingSort(a4, 9);
		System.out.print("sorted array: " + Arrays.toString(a4));
	}

	// Problem 1
	public static int findMissing(int[] a) {
		int nums = a.length + 1;
		int sum = (nums * (nums + 1)) / 2;
		for (int i = 0; i < nums - 1; i++) {
			sum -= a[i];
		}
		return sum;
	}

	// Problem 2
	public static void countingSort(int[] a, int n) {
		int[] count = new int[n];
		for (int i = 0; i < a.length; i++) {
			count[a[i] - 1] += 1;
		}

		int index = 0;
		//Iterates through each index 'i' of the 'count; array
		for (int i = 0; i < n; i++) {
       			//executes as long as the count of element 'i+1'
			while (count[i] > 0) {
				a[index] = i + 1;
				index++;
				count[i]--;
			}
		}
	}
}
