// This file began as a template by Dr Prudence Wong that provided the function headers 
// (but all function bodies up to and including searchMoveToTail() were written by me)

// Name: Matt Corthorne
// Student ID: [redacted]
//

class COMP108W07 {

	public Node head, tail;

	public COMP108W07() {
		head = null;
		tail = null;
	}

	// sequential search if key is in the list
	// move the node to head if found, do nothing if not found
	public void searchMoveToHead(int key) {
		Node curr;
		curr = head;

		while (curr != null) {
			if (curr.data == key) {
				Node new_Head = new Node(curr.data);	// Create new node
				new_Head.next = head;					// Insert node in front of head node
				new_Head.prev = null;					// Set value in front of new head to null as it is the head of the list
				head.prev = new_Head;					// Links the old head to the new head
				head = new_Head;						// Set the head pointer to the new value

				// The old node must now be deleted by changing the pointers for the adjacent nodes
				if (curr == tail) {		// If the node to be removed is at the tail of the list
					curr = curr.prev;
					curr.next = null;
					tail = curr;
				}	else {				// If the node to be removed is not at the tail of the list
					curr = curr.prev;						// Set node to previous one
					curr.next = curr.next.next;				// Set the next pointer to the node after the next one
					curr = curr.next;						// Move curr pointer forward 2
					curr.prev = curr.prev.prev;				// Set the prev pointer to the node before the previous one
				}
			}
			curr = curr.next;
		}
	}


	// sequential search if key is in the list
	// move the node to tail if found, do nothing if not found
	public void searchMoveToTail(int key) {
		Node curr;
		curr = head;

		while (curr != null) {
			if (curr.data == key) {
				// New tail node is created and appended at the end of the list
				Node new_Tail = new Node(curr.data);	// Create new node
				new_Tail.prev = tail;					// Insert node in front of head node
				new_Tail.next = null;					// Set value in front of new head to null as it is the head of the list
				tail.next = new_Tail;					// Links the old head to the new head
				tail = new_Tail;						// Set the head pointer to the new value

				// The old node must now be deleted by changing the pointers for the adjacent nodes
				// This process is slightly different depending on if the node is a head or not
				if (curr == head) {		// If the node to be removed is at the head of the list
					curr = curr.next;	
					curr.prev = null;
					head = curr;
				}	else {				// If the node to be removed is not at the head of the list
					curr = curr.prev;						// Set node to previous one
					curr.next = curr.next.next;				// Set the next pointer to the node after the next one

					curr = curr.next;						// Move curr pointer forward 2
					curr.prev = curr.prev.prev;				// Set the prev pointer to the node before the previous one
				}
			}
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
