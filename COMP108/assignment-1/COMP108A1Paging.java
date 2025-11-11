// This assignment started as a template coded by my leturer, Prudence Wong, in 2020-12-15 
//   defining the key function headers but the function bodies were all written by me
//
// Note: You are allowed to add additional methods if you need.
//
// Name: Matt Corthorne
// Student ID: [redacted]
//
// Time Complexity and explanation: You can use the following variables for easier reference.
// n denotes the number of requests, p denotes the size of the cache
// n and p can be different and there is no assumption which one is larger
//
// noEvict(): O(n*p).			This is because the program loops once for each element in rArray -of which there are n- per element in cArray, of which there are p.
//
// evictFIFO(): O(n*p). 		This method is the same as noEvict apart from some extra instructions for evicting pages, which
//								aren't factored into the time complexity because they are relatively insignificant compared to the arrays.
//
// evictLFU(): O(n*(p^3)).		This is because it has the same structure where rArray and cArray are looped through						-->   n*p
//								At the start a new array with the same size as cArray is declared and looped through to initialise it to 1	-->  (n*p)*p
//								When working out which page to evict, the program loops through each element of the new array				--> ((n*p)*p)*p
//								Overall, this simplifies to n*(p^3)
//
// evictLFD(): O((n^2)*(p^4))	This is because it has the same structure where rArray and cArray are looped through						-->    n*p
//								When working out which page to evict, new array with the same size as cArray is declared and looped through -->   (n*p)*p
//								When working out the forward distance for each element in the cache, a there is another nested loop
//								However, the inner loop decreases in size each time however, I was unsure about how to represent this		-->  ((n*p)*p)*n*p
//								When working out the largest forward distance there is  another loop of size p								--> (((n*p)*p)*n*p)*p
//								Overall, this simplifies to (n^2)*(p^4)
//

class COMP108A1Paging {


	// no eviction
	// Aim:
	// do not evict any page
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output noEvict(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();

		/*
		A for loop that will cycle through each requested page
		in the register and check if it is a hit or a miss.
		If it is a miss then it will keep the cache the same and change nothing
		*/
		for (int i = 0; i < rSize; i ++) {
			if (checkHit(cArray, cSize, rArray[i]) >= 0) { // Checks if rArray[i] is in the cache
				// Increments the hit counter and updates the hit pattern
				output.hitCount += 1;
				output.hitPattern += "h";
			}
			else {										   // If not then...
				// Increments the miss counter and updates the miss pattern
				output.missCount += 1;
				output.hitPattern += "m";
			}
		}
		return output;
	}

	// evict FIFO
	// Aim:
	// evict the number present in cache for longest time if next request is not in cache
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output evictFIFO(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();

		/*
		A for loop that will cycle through each requested page
		in the register and check if it is a hit or a miss.
		If it is a miss then it will replace the cache
		that was changed the least recently
		(i.e. the last in the queue)
		*/
		int frontPointer = 0;
		for (int i = 0; i < rSize; i ++) {
			if (checkHit(cArray, cSize, rArray[i]) >= 0) { // Checks if rArray[i] is in the cache
				// Increments the hit counter and updates the hit pattern
				output.hitCount += 1;
				output.hitPattern += "h";
			}
			else {										   // If not then...
				// Increments the miss counter and updates the miss pattern
				output.missCount += 1;
				output.hitPattern += "m";
				// Replaces the missed page with the one in the front of the queue
				cArray[frontPointer] = rArray[i];
				// The front pointer must be incremented and modded so that it is smaller then cSize
				frontPointer = (frontPointer + 1) % cSize;
			}
		}
		return output;
	}

	// ** Update to change cCount.length to cSize
	// ** Update to merge hitIndex and evictionPointer

	// evict LFU
	// Aim:
	// evict the number that is least freently used so far if next request is not in cache
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output evictLFU(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();

		/*
		Declares an array to hold the number of hits for each
		page in the cache, which is then initialised to 1
		*/
		int[] cCount = new int[cSize];
		for (int j = 0; j < (cCount.length); j ++) {
			cCount[j] = 1;
		}

		/*
		A for loop that will cycle through each requested page
		in the register and check if it is a hit or a miss.
		If it is a miss then it will replace the cache
		that has been requested the least
		*/
		int hitIndex;
		for (int i = 0; i < rSize; i ++) {
			// Stores the index of the page in the cache that matches the request.
			// If they don't match then the stored value will be less than 0
			hitIndex = checkHit(cArray, cSize, rArray[i]);
			if (hitIndex >= 0) { // Checks if rArray[i] is in the cache
				// Increments the hit counter and updates the hit pattern
				output.hitCount += 1;
				output.hitPattern += "h";
				// Updates the array that stores the hit count for the matching page in the cache
				cCount[hitIndex] += 1;
			}
			else {				 // If not then...
				// Increments the miss counter and updates the miss pattern
				output.missCount += 1;
				output.hitPattern += "m";
				// Stores the index of the page in the cache with the smallest hit count
				int evictionPointer = discernLFU(cCount, cSize);
				// Replaces the page in the cache and resets the hit count array to 1 for that index
				cArray[evictionPointer] = rArray[i];
				cCount[evictionPointer] = 1;
			}
		}
		return output;
	}

	// evict LFD
	// Aim:
	// evict the number whose next request is the latest
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output evictLFD(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();

		for (int i = 0; i < rSize; i ++) {
			if (checkHit(cArray, cSize, rArray[i]) >= 0) {
				output.hitCount += 1;
				output.hitPattern += "h";
			}
			else {
				output.missCount += 1;
				output.hitPattern += "m";
				int evictionPointer = discernLFD(cArray, cSize, rArray, rSize, i);
				cArray[evictionPointer] = rArray[i];
			}
		}
		return output;
	}

	// ** Update to get rid of flgHit

	/*
	An array to check if a value (page) is present in an array (cArray) of size 'cSize'
	It returns an integer that is either the index of the matching element in cArray or,
	if there is no match, it will then return -1
	*/
	static int checkHit(int[] cArray, int cSize, int page) {
		/*
		A for loop that will cycle through each element in cArray and
		check if it matches the value stored in the variable 'page'
		*/
		boolean flgHit = false; // A flag that represents whether or not a match has been found
		int hitIndex = 0; // Stores the return value
		for (int i = 0; i < cSize; i ++) {
			if (cArray[i] == page) { // If the two pages match...
				// Update the flag to say that a match has been found
				flgHit = true;
				// Store the corresponding index in hitIndex
				hitIndex = i;
			}
		}

		// If the for loop doesn't produce a match then store -1 in hitIndex
		if (flgHit == false) {
			hitIndex = -1;
		}
		return hitIndex;
	}

	static int discernLFU(int[] cCount, int cSize) {

		int evictionPointer;
		if (cSize == 1) {
			evictionPointer = 0;
		}
		else {
			int minIndex = 0;
			int minimum = cCount[0];
			for (int i = 1; i < cSize; i ++) {
    			if (cCount[i] < minimum) {
    				minIndex = i;
    				minimum = cCount[i];
    			}
			}
			evictionPointer = minIndex;
		}
    	return evictionPointer;
	}

	static int discernLFD(int[] cArray, int cSize, int[] rArray, int rSize, int rFrontPointer) {
		int[] cCount = new int[cSize]; // cCount initialised to -2
		for (int j = 0; j < (cSize); j ++) {
			cCount[j] = -2;
		}

		for (int x = 0; x < cSize; x ++) { // Checks each value in the cache
			for (int y = rFrontPointer; y < rSize; y ++) { // Must loop through rArray from the front to the back, starting from the current position in the array
				if ((cArray[x] == rArray[y]) & (cCount[x] == -2)) { // If the 2 items are the same and the value is still unassigned (/= -2)
					cCount[x] = y;
				}
			}
			if (cCount[x] == -2) { // if still not assigned give value -1 to signify that it has been checked but there is no match
				cCount[x] += 1;
			}
		}

		int evictionPointer;
		if (checkHit(cCount, cSize, -1) >= 0) {
			evictionPointer = discernLFU(cCount, cSize);
		}
		else {
			int maxIndex = 0;
			int maximum = cCount[0];
			for (int i = 1; i < cSize; i ++) {
    			if (cCount[i] > maximum) {
    				maxIndex = i;
    				maximum = cCount[i];
    			}
			}
			evictionPointer = maxIndex;
		}
		return evictionPointer;
	}
}