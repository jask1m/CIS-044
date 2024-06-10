public class Lab2 {
  public static void main(String[] arg) {
		// write your test code here
		int[] a = { 2, 3, 5, 7, 11, 13, 17, 19, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 23, 29, 31, 37, 41, 43 };
		System.out.println("Running tests for problem 4:");
		int result = min(a, 0, 10);
		System.out.println("The expected output is: 2");
		System.out.println("The actual output is:" + result);
    int result2 = min(a, 11, 15);
		System.out.println("The expected output is: 61");
		System.out.println("The actual output is:" + result2 + "\n");

    System.out.println("Running tests for problem 5:");
    long result3 = computePay(39);
		System.out.println("The output for computePay(39) is:" + result3);
    long result4 = computeSavings(39);
		System.out.println("The output for computeSavings(39) is:" + result4);
	}
	// Problem 4
	public static int min(int[] a, int begin, int end) {
    return minRecursiveMethod(a, begin, end);
	}

	private static int minRecursiveMethod(int[] arr, int start, int end) {
			if (start == end) {
					return arr[start];
			}
			int half = (start + end) / 2;
			int minLeft = minRecursiveMethod(arr, start, half);
			int minRight = minRecursiveMethod(arr, half + 1, end);
			return Math.min(minLeft, minRight);
	}

	
	// Problem 5
	public static long computePay(int day) {
		// base case
			// checks whether day is 1
		if (day == 1) {
			return 1;
		}
		else {
			return 2 * computePay(day - 1);
		}
	}
	
	public static long computeSavings(int day) {
		// base case
		if (day == 1) {
			return 1;
		}
		else {
			return computePay(day) + computeSavings(day - 1);
		}
	}
}
