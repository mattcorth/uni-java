//
// Enter your name: Matt Corthorne
// Enter your student ID: 201507354
//
/*
Time complexity of notExists() = O(n^2) where n is the length of the largest array (in this case array1). 
This is because the longest and most time consuming  part of the method is the nested loop, so large that 
the other lines are relatively insignificant

Time complexity of count() also = O(n^2) where n is the lenth of the largest array (in this case array1). 
This is because of the nested loop and the fact that the rest of the block of code isn't nearly as time 
consuming as the loops.

*/
import java.util.*;
import java.io.*;

class COMP108W04 {

	// print the content of an array of size n
	static void printArray(int[] data, int n) {
		int i;

		for (i=0; i < n; i++)
			System.out.print(data[i] + " ");
		System.out.println();
	}

	// Input: array1[] with size1 entries and array2[] with size2 entries
	// print all entries of array1[] that does not exist in array2[]
	static void notExists(int array1[], int size1, int array2[], int size2) {
		int[] notPresent = new int[size1];
		int pointer = 0;
		boolean flgHere;
		for (int x = 0; x < size1; x ++) {
			flgHere = false;
			for (int y = 0; y < size2; y ++) {
				if (array1[x] == array2[y]) {
					flgHere = true;
				}
			}
			if (flgHere == false) {
				notPresent[pointer] = array1[x];
				pointer += 1;
			}
		}
		
		printArray(notPresent, pointer);
	}

	// Input: array1[] with size1 entries and array2[] with size2 entries
	// for each entry in array2[], count how many times it appears in array1[]
	static void count(int array1[], int size1, int array2[], int size2) {
		int[] listCount = new int[size2];
		int count;
		for (int x = 0; x < size2; x ++) {
			count = 0;
			for (int y = 0; y < size1; y ++) {
				if (array2[x] == array1[y]) {
					count += 1;
				}
			}
			listCount[x] = count;
		}
		printArray(listCount, size2);
	}

}
