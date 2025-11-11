// This file began as a template by Dr Prudence Wong that provided the function headers 
// (but all function bodies up to and including searchMax() were written by me)

// Name: Matt Corthorne
// Student ID: [redacted]

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

	// <------        Beyond this point, all methods were written by lecturer Dr Prudence Wong        ------>
	
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
