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
//								aren't factored into the time complexity because they are relatively insignificant compared to the loops.
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
			if (findHitIndex(cArray, cSize, rArray[i]) >= 0) { // Checks if rArray[i] is in the cache
				// Increments the hit counter and updates the hit pattern
				output.hitCount ++;
				output.hitPattern += "h";
			}
			else {											   // If not then...
				// Increments the miss counter and updates the miss pattern
				output.missCount ++;
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
			if (findHitIndex(cArray, cSize, rArray[i]) >= 0) { // Checks if rArray[i] is in the cache
				// Increments the hit counter and updates the hit pattern
				output.hitCount ++;
				output.hitPattern += "h";
			}
			else {										   // If not then...
				// Increments the miss counter and updates the miss pattern
				output.missCount ++;
				output.hitPattern += "m";
				// Replaces the missed page with the one in the front of the queue
				cArray[frontPointer] = rArray[i];
				// The front pointer must be incremented and modded so that it is smaller then cSize
				frontPointer = (frontPointer + 1) % cSize;
			}
		}
		return output;
	}

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
		for (int j = 0; j < cSize; j ++) {
			cCount[j] = 1;
		}

		/*
		A for loop that will cycle through each requested page
		in the register and check if it is a hit or a miss.
		If it is a miss then it will replace the cache
		that has been requested the least
		*/
		int tempIndex;
		for (int i = 0; i < rSize; i ++) {
			// Stores the index of the page in the cache that matches the request.
			// If they don't match then the stored value will be less than 0
			tempIndex = findHitIndex(cArray, cSize, rArray[i]);
			if (tempIndex >= 0) { // Checks if rArray[i] is in the cache
				// Increments the hit counter and updates the hit pattern
				output.hitCount ++;
				output.hitPattern += "h";
				// Updates the array that stores the hit count for the matching page in the cache
				cCount[tempIndex] ++;
			}
			else {				 // If not then...
				// Increments the miss counter and updates the miss pattern
				output.missCount ++;
				output.hitPattern += "m";
				// Stores the index of the page in the cache with the smallest hit count
				tempIndex = findIndexOfMinValue(cCount, cSize);
				// Replaces the page in the cache and resets the hit count array to 1 for that index
				cArray[tempIndex] = rArray[i];
				cCount[tempIndex] = 1;
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

		/*
		A for loop that will cycle through each requested page
		in the register and check if it is a hit or a miss.
		If it is a miss then it will replace the cache
		that's next request is the furthest away.
		*/
		for (int i = 0; i < rSize; i ++) {
			if (findHitIndex(cArray, cSize, rArray[i]) >= 0) { // Checks if rArray[i] is in the cache
				// Increments the hit counter and updates the hit pattern
				output.hitCount ++;
				output.hitPattern += "h";
			}
			else {											   // If not then...
				// Increments the miss counter and updates the miss pattern
				output.missCount ++;
				output.hitPattern += "m";
				// Works out the longest forward distance from the current cache and remaining requests and evicts the page from the cache
				cArray[discernLFD(cArray, cSize, rArray, rSize, i)] = rArray[i];
			}
		}
		return output;
	}

	/*
	An array to check if a value (page) is present in an array (cArray) of size 'cSize'
	It returns an integer that is either the index of the matching element in cArray or,
	if there is no match, it will then return -1
	*/
	static int findHitIndex(int[] array, int size, int item) {
		/*
		A for loop that will cycle through each element in cArray and
		check if it matches the value stored in the variable 'page'
		*/
		int hitIndex = -1; // Stores the return value.
		for (int i = 0; i < size; i ++) {
			if (array[i] == item) { // If the two pages match...
				// Store the corresponding index in hitIndex
				hitIndex = i;
			}
		}
		return hitIndex;
	}

	// This is a simple program to find the minimum value of an array and output the index where the value is stored
	static int findIndexOfMinValue(int[] array, int size) {

		int minIndex = 0;
		int minimum = array[0];
		for (int i = 1; i < size; i ++) {
    		if (array[i] < minimum) {
    			minIndex = i;
    			minimum = array[i];
    		}
		}
    	return minIndex;
	}

	/*
	This is a method to calculate the longest forward distance
	out of all of the pages in the cache
	*/
	static int discernLFD(int[] cArray, int cSize, int[] rArray, int rSize, int rFrontPointer) {
		// cCount stores the longest forward distance for all cache pages
		int[] cCount = new int[cSize]; // cCount is initialised to -1
		for (int j = 0; j < (cSize); j ++) {
			cCount[j] = -1;
		}

		for (int x = 0; x < cSize; x ++) { // Checks each value in the cache
			for (int y = rFrontPointer; y < rSize; y ++) { // Must loop through rArray from the front to the back, starting from the current position in the array
				if ((cArray[x] == rArray[y]) & (cCount[x] == -1)) { // If the 2 items are the same and the value is still unassigned (/= -2)
					cCount[x] = y;
				}
			}
			/*
			If no match has been found, give value 100 as this is larger
			than the maximum index for rArray. This makes the next part easier
			*/
			if (cCount[x] == -1) {
				cCount[x] = 100;
			}
		}

		/*
		This is a simple algorithm to find the index of the largest
		value in cCount. This represents the longest forward distance
		and the page that should next be evicted from the cache.
		*/
		int maxIndex = 0;
		int maximum = cCount[0];
		for (int i = 1; i < cSize; i ++) {
    		if (cCount[i] > maximum) {
    			maxIndex = i;
    			maximum = cCount[i];
    		}
		}
		return maxIndex;
	}
}