//
// by Prudence Wong 2021-03-20
//
import java.util.*;
import java.io.*;

class COMP108W08App {

	private static Scanner keyboardInput = new Scanner (System.in);
	private static final int MaxDBCount = 100;
	private static final int MaxReqCount = 100;


	// main program
	public static void main(String[] args) throws Exception {
		COMP108W08 database = new COMP108W08();
		int databaseSize=0, requestSize=0;
		int[] request = new int[MaxReqCount];
		
		try {
			System.out.println();
			System.out.print("Enter the number of movies in the database (1-" + MaxDBCount + "): ");
			databaseSize = keyboardInput.nextInt();
			if (databaseSize > MaxDBCount || databaseSize <= 0)
				System.exit(0);
			System.out.print("Enter " + databaseSize + " integers: ");
			for (int i=0; i<databaseSize; i++) {
				database.insertTail(new Node(keyboardInput.nextInt()));
			}

			System.out.println();
			System.out.print("Enter the number of movies in the request sequence (1-" + MaxReqCount + "): ");
			requestSize = keyboardInput.nextInt();
			if (requestSize > MaxReqCount || requestSize <= 0)
				System.exit(0);
			System.out.print("Enter " + requestSize + " integers: ");
			for (int i=0; i<requestSize; i++) {
				request[i] = keyboardInput.nextInt();
			}
		}
		catch (Exception e) {
			keyboardInput.next();
			System.exit(0);
		}
		
		
		System.out.print("Content of database list: ");
		database.headToTail();
		database.tailToHead();
		System.out.print("Content of request array: ");
		printArray(request, requestSize);

		System.out.println();

		System.out.println("calling database.notExists()...");
		database.notExists(request, requestSize);
		System.out.println();

		System.out.println("calling database.count()...");
		database.count(request, requestSize);
		System.out.println();
	}

	// Do NOT change this method!
	// print array[0]..array[size-1]
	static void printArray(int[] array, int size) {
		for (int i=0; i<size; i++) {
			System.out.print(array[i] + ",");
		}
		System.out.println();
	}

}

