// This file began as a template by Prudence Wong that provided the function headers 
// (but the function bodies were all written by me with the exception of bugOne)

// Enter your name: Matt Corthorne
// Enter your student ID: [redacted]
//
class COMP108W03 {

	// print the content of an array of size n
	static void printArray(int[] data, int n) {
		int i;

		for (i=0; i < n; i++)
			System.out.print(data[i] + " ");
		System.out.println();
	}

	// data[] is an array, n is size of array, key is the number we want to find
	static void seqSearch(int[] data, int n, int key) {
		int i, count;
		boolean found;

		// start sequential search on the array called data[]
		found = false;	// to indicate if the number is found
		i = 0;		// an index variable to iterate through the array
		count = 0;	// to count how many comparisons are made
		while (i<n && found==false) {
			if (data[i] == key) {
				found = true;
			} else {
				i = i+1;
			}
			count = count+1;
		}
		System.out.print("The number " + key + " is ");
		if (found == false)
			System.out.print("not ");
		System.out.println("found by sequential search and the number of comparisons used is " + count);
	}

	// data[] is an array in ascending order, n is size of array, key is the number we want to find
	// You can assume that data[] is already sorted
	// refer to Lecture 6
	static void binarySearch(int[] data, int n, int key) {
		int first = 0;
		int last = n - 1;
		int pos = (int) ((last + first) / 2); // Index for first comparison is calculated 
		boolean found = false;
		int count = 1;
		
		while (found == false) {
			if (data[pos] == key) {
				found = true;
				System.out.print("Item found at index " + pos + ". This took " + count + " comparisons.");
			}
			else {
				if (data[pos] > key) {
					last = pos - 1;
				}
				else {
					first = pos + 1;
				}
				pos =  (int) ((last + first) / 2);
				count = count + 1;
			}
		}
	}

	// print the smallest number in the array of size n
	static void findMin(int[] data, int n) {
		int i, min;

		min = data[0];
		i = 1;
		while (i < n) {
			if (data[i] < min)
				min = data[i];
			i++;
		}
		System.out.println("The smallest number is " + min + ".");
	}

	// print the largest number in the array of size n
	// refer to Lecture 8
	static void findMax(int[] data, int n) {
		int i, max;

		max = data[0];
		i = 1;
		while (i < n) {
			if (data[i] > max)
				max = data[i];
			i++;
		}
		System.out.println("The largest number is " + max + ".");
	}

	// print the second smallest number in the array of size n
	// refer to Lecture 8
	static void findSecondMin(int[] data, int n) {
		int i, fmin, smin;
		
		if (data[0] < data[1]) {
			fmin = data[0];
			smin = data[1];
		}
		else {
			fmin = data[1];
			smin = data[0];
		}
		i = 2;
		while (i < n) {
			if (data[i] < fmin) { // If smaller than both then do this:
				smin = fmin;
				fmin = data[i];
			}
			else if ((data[i] < smin) & (data[i] > fmin)) { // If smaller than one but not the other
				smin = data[i];
			}
			i++;
		}
		System.out.println("The smallest number is " + fmin + ". The second smallest number is " + smin + ".");
	}

	// print the second largest number in the array of size n
	// refer to Lecture 8
	static void findSecondMax(int[] data, int n) {
		int i, fmax, smax;
		
		if (data[0] > data[1]) {
			fmax = data[0];
			smax = data[1];
		}
		else {
			fmax = data[1];
			smax = data[0];
		}
		i = 2;
		while (i < n) {
			if (data[i] > fmax) { // If smaller than both then do this:
				smax = fmax;
				fmax = data[i];
			}
			else if ((data[i] > smax) & (data[i] < fmax)) { // If smaller than one but not the other
				smax = data[i];
			}
			i++;
		}
		System.out.println("The largest number is " + fmax + ". The second largest number is " + smax + ".");
	}
	
	// print the smallest number and its position in an array of size n
	// Find the bug and fix it by altering ONE line of code
	// (This function was written by Prudence Wong and has been kept in to prevent the checker from throwing errors)
	static void bugOne(int[] data, int n) {
		int i, pos, min;

		pos = 0;
		min = data[0];
		i = 1;
		while (i < n) {
			if (data[i] < min) {
				min = data[i];
				pos = i;
			}
			i++;
		}
		System.out.println("The smallest number is " + min + " at position " + pos + ".");
	}
}
