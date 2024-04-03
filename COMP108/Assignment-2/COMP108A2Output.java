//
// Coded by Prudence Wong 2021-03-06
// Do not change this file
// Changes in this file will NOT be graded
//
// A class to store output
//
class COMP108A2Output {
	public int hitCount;
	public int missCount;
	public int[] compare;
	public String cabFromHead;
	public String cabFromTail;
	public String cabFromHeadFreq;
	
	// Constructor
	// parameter size is used to define the size of the array to store number of comparisons
	public COMP108A2Output(int size) {
		hitCount = 0;
		missCount = 0;
		cabFromHead = "";
		cabFromTail = "";
		cabFromHeadFreq = "";
		compare = new int[size];
		for (int i=0; i<size; i++)
			compare[i] = 0;
	}

	// an auxiliary method to print its attributes in a readable form
	public void print(boolean freq) {
		System.out.println();
		for (int i=0; i<compare.length; i++)
			System.out.print(compare[i] + ",");
		System.out.println();
		System.out.println(hitCount + " h " + missCount + " m");
		System.out.println("From head to tail: " + cabFromHead);
		System.out.println("From tail to head: " + cabFromTail);
		if (freq == true)
			System.out.println("Frequency from head to tail: " + cabFromHeadFreq);
	}	
}
