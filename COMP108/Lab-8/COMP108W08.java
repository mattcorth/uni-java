//
// by Prudence Wong 2021-03-20
//
// Name: Matt Corthorne
// Student ID: 201507354
//
// Time complexity:
// Express the worst case time complexity of your algorithm in big-O notation.
// You can assume that there are
// n nodes in the original database list,
// r requests in the request sequence, and
// k distinct requests in the request sequence.
// Justify your answer.
//
// notExisits: O(n*r) as the program must loop through the entirety of the nodes and requests although in once a match has been found it does exit the n loop early.
//
// count: O(n*r) as the program must loop through the entirety of the nodes and requests, regardless of the number of distinct requests.
//				 Even if the number of distinct values was important, my program would run the same as it must always check the full
//				 list in case of repeats since it doesn't know which values are distinct.
//

class COMP108W08 {

	public Node head, tail;

	public COMP108W08() {
		head = null;
		tail = null;
	}


	// Input: array[] with size entries
	// print all entries of array[] that does not exist in the list
	public void notExists(int array[], int size) {
		boolean found;
		Node curr;
		String returnStr = "";
		for (int i = 0; i < size; i++) {
			found = false;
			curr = head;
			while (curr != null) {
				if (curr.data == array[i]) {
					found = true;
					curr = tail.prev;
				}
				curr = curr.next;
			}
			if (found == false) {
				if (!returnStr.isEmpty()) {
					returnStr += " ";
				}
				returnStr += array[i];
			}
		}
		System.out.println(returnStr);
	}

	// Input: array[] with size entries
	// for each entry in the list, count how many times it appears in array[]
	public void count(int array[], int size) {
		int count;
		Node curr;
		curr = head;
		while (curr != null) {
			count = 0;
			for (int i = 0; i < size; i++) {
				if (curr.data == array[i]) {
					count++;
				}
			}
			System.out.print(count + " ");
			curr = curr.next;
		}
	}

	// DO NOT change this method
	// insert newNode to the head of the list
	public void insertHead(Node newNode) {
		newNode.next = head;
		newNode.prev = null;
		if (head == null)
			tail = newNode;
		else
			head.prev = newNode;
		head = newNode;
	}

	// DO NOT change this method
	// insert newNode to the tail of the list
	public void insertTail(Node newNode) {
		newNode.next = null;
		newNode.prev = tail;
		if (tail != null)
			tail.next = newNode;
		else head = newNode;
		tail = newNode;
	}

	// DO NOT change this method
	// this will turn the list into a String from head to tail
	public String headToTail(){
		Node curr;
		String outString="";

		curr = head;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the list into a String from tail to head
	public String tailToHead(){
		Node curr;
		String outString="";

		curr = tail;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.prev;
		}
		return outString;
	}
}
