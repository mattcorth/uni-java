// This assignment started as a template coded by my leturer, Prudence Wong, in 2021-03-06 
//   defining the key function headers but the function bodies were all written by me 
//
// Note: You are allowed to add additional methods if you need.
//
// Name: Matt Corthorne
// Student ID: [redacted]
// MWS username: [redacted]
//
// Time Complexity and explanation:
// f denotes initial cabinet size
// n denotes the total number of requests
// d denotes number of distinct requests
// You can use any of the above notations or define additional notation as you wish.
//
// appendIfMiss(): O(d*f)	The method must loop through each request						--> n
//							Each request involves looping through the cabinet				--> n*f
//							If each request was distinct then the program would have to
//							seach the full linked list more often, since there is a greater
//							chance of there being an item that doesn't exist in the list	--> d*f
//
// moveToFront(): O(d*f)	This method has the same problems as the previous method in that distinct requests require	--> d*f
//							a full search of the list although this algorithm is slightly worse off since new nodes are
//							added in the head position but if the requests are distinct, then they won't show up again
//							and might as well be added to the back of the list
//
// freqCount(): O(d*f*f)	The method must loop through each request								--> n
//							Each request involves looping through the cabinet						--> n*f
//							The node re-insertion means that the list must be looped through again	--> n*f*f
//							If each request was distinct then the program would have to
//							seach the full linked list more often, since there is a greater
//							chance of there being an item that doesn't exist in the list			--> d*f*f
//

class COMP108A2Cab {

	public COMP108A2Node head, tail;

	public COMP108A2Cab() {
		head = null;
		tail = null;
	}

	// append to end of list when miss
	public COMP108A2Output appendIfMiss(int rArray[], int rSize) {
		COMP108A2Output output = new COMP108A2Output(rSize);

		COMP108A2Node curr;					// Stores the current node being checked
		boolean found;						// Stores whether or not the item was found in the search
		int count = 0;						// Stores the number of comparisons
		for (int i = 0; i < rSize; i++) {	// Searches through the linked list

			curr = head;					// Resets the current node to point at the start of the list
			found = false;					// Resets the 'found' node to false
			count = 0;						// Resets the comparisons counter
			while (curr != null) {			// Searches through the linked list
				count ++;					// Increments the number of comparisons
				if (curr.data == rArray[i]) {	// If the item has been found, set values in the object 'output'
					found = true;
					output.hitCount++;
					output.compare[i] = count;
				}
				curr = curr.next;	// Increments current position in linked list
			}
			if (found == false) {	// If the node was not found, set values in the object 'output' and prepend to the list
				output.missCount++;
				output.compare[i] = count;
				COMP108A2Node new_Tail = new COMP108A2Node(rArray[i]);	// Create new node
				insertTail(new_Tail);									// Insert node in tail position
			}
		}

		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		return output;
	}

	// move the file requested to the beginning of the list
	public COMP108A2Output moveToFront(int rArray[], int rSize) {
		COMP108A2Output output = new COMP108A2Output(rSize);

		COMP108A2Node curr;					// Stores the current node being checked
		boolean found;						// Stores whether or not the item was found in the search
		int count = 0;						// Stores the number of comparisons
		for (int i = 0; i < rSize; i++) {	// Searches through the linked list

			curr = head;					// Resets the current node to point at the start of the list
			found = false;					// Resets the 'found' node to false
			count = 0;						// Resets the comparisons counter
			while (curr != null) {			// Searches through the linked list
				count ++;					// Increments the number of comparisons
				if (curr.data == rArray[i]) {	// If the item has been found, set values in the object 'output'
					found = true;
					output.hitCount++;
					output.compare[i] = count;

					COMP108A2Node new_Head = new COMP108A2Node(curr.data);	// Create new node
					insertHead(new_Head);                                   // Insert node in head position

					if (curr == tail) {		// If the node to be deleted is at the end of the list...
						curr = curr.prev;	// Move curr pointer back 1
						curr.next = null;	// Set the next pointer to null to cut off the next node
						tail = curr;		// Set the tail pointer to the current node
					}
					else {					// If the node to be deleted is is ano other position...
						// The old node must now be deleted by changing the pointers for the adjacent nodes
						curr = curr.prev;										// Set node to previous one
						curr.next = curr.next.next;								// Set the next pointer to the node after the next one
						curr = curr.next;										// Move curr pointer back 2
						curr.prev = curr.prev.prev;								// Set the prev pointer to the node before the previous one
					}
				}
				curr = curr.next;	// Increments current position in linked list
			}
			if (found == false) {	// If the node was not found, set values in the object 'output' and prepend to the list
				output.missCount++;
				output.compare[i] = count;
				COMP108A2Node new_Head = new COMP108A2Node(rArray[i]);	// Create new node
				insertHead(new_Head);									// Insert node in head position
			}
		}

		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		return output;
	}

	// move the file requested so that order is by non-increasing frequency
	public COMP108A2Output freqCount(int rArray[], int rSize) {
		COMP108A2Output output = new COMP108A2Output(rSize);

		COMP108A2Node curr;					// Stores the current node being checked
		boolean found;						// Stores whether or not the item was found in the search
		int count = 0;						// Stores the number of comparisons
		for (int i = 0; i < rSize; i++) {	// Searches through the linked list
		
			curr = head;					// Resets the current node to point at the start of the list
			found = false;					// Resets the 'found' node to false
			count = 0;						// Resets the comparisons counter
			while (curr != null) {			// Searches through the linked list
				count ++;					// Increments the number of comparisons
				if (curr.data == rArray[i]) {	// If the item has been found, set values in the object 'output'
					found = true;
					output.hitCount++;
					output.compare[i] = count;
					curr.freq++;

					moveNode(curr);			// Function to delete the node and re-insert it in the correct position
				}
				curr = curr.next;	// Increments current position in linked list
			}
			if (found == false) {	// If the node was not found, set values in the object 'output' and prepend to the list
				output.missCount++;
				output.compare[i] = count;
				COMP108A2Node new_Tail = new COMP108A2Node(rArray[i]);	// Create new node
				insertTail(new_Tail);									// Insert node in tail position
			}
		}
		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		output.cabFromHeadFreq = headToTailFreq();
		return output;
	}

	// Works out the position in the linked list where a particular frequency belongs
	// Value is the frequency of the item that must be moved
	// NOTE: this function assumes that the node in question has already been deleted from the linked list
	public int findNewPosition(int value) {

		if (head.freq < value) {
			return 0;
		}
		int i = 1;								// Stores the position of the node after which the node should be re-inserted
		COMP108A2Node curr = head;
        while (curr.next != null) {
        	if (curr.next.freq < value) {
        		return i;
        	}
        	i++;
            curr = curr.next;
        }
		if (curr.next == null) {	// If the node is to be re-inserted at the tail position then the return value is -1 as the node must be deleted in a special way
			i = (-1);
		}
		return i;
	}

	// This function deletes the node and re-inserts it in the correct position based of the value of its 'freq' attribute
	// The paarameter curr holds the node to be deleted and re-inserted
	public void moveNode(COMP108A2Node curr) {
		COMP108A2Node deletedNode = curr;	// A new node is created to store the position of the curr node. Otherwise, the node would be lost when
											// it is deleted from the list as there is no way back to it after the adjacent pointer are changed

		if (curr == head) { 		// If the node to be deleted is in the 'head' position...
			deleteHead();
		}
		else if (curr == tail) {	// If the node to be deleted is in the 'tail' position...
			curr = curr.prev;
			curr.next = null;
			tail = curr;
		}
		else {						// If the node to be deleted is in any other position...
			// The old node must now be deleted by changing the pointers for the adjacent nodes
			curr = curr.next;										// Move curr pointer back 2
			curr.prev = curr.prev.prev;								// Set the prev pointer to the node before the previous one
			curr = curr.prev;										// Set node to previous one
			curr.next = curr.next.next;								// Set the next pointer to the node after the next one
		}

		int position = findNewPosition(deletedNode.freq);	// Works out the position of the node in the list after which the deleted node should be re-inserted.
															// The function returns -1 if the node is to be re-inserted at the tail of the list.
		if (position == (0)) {			// If the new position is the head of the list...
			insertHead(deletedNode);
		}
		else if (position == (-1)) {	// If the new position is the tail of the list...
			insertTail(deletedNode);
		}
		else {							// If in any other position...

			curr = head;
			for (int i = 1; i < position; i++) {	// Loops until it reaches the value after which the node should be re-inserted
				curr = curr.next;
			}
			deletedNode.prev = curr;				// Set new node pointer 'prev'
			curr = curr.next;						// Move curr node forward 1 (skipping past the new node)
			deletedNode.next = curr;				// Set new node pointer 'next'

			curr.prev = deletedNode;				// Set adjacent node pointer 'prev'
			curr = curr.prev.prev;					// Move curr node back 2 (skipping past new node)
			curr.next = deletedNode;				// Set adjacent node pointer 'next'
		}
	}

	// DO NOT change this method
	// insert newNode to head of list
	public void insertHead(COMP108A2Node newNode) {

		newNode.next = head;
		newNode.prev = null;
		if (head == null)
			tail = newNode;
		else
			head.prev = newNode;
		head = newNode;
	}

	// DO NOT change this method
	// insert newNode to tail of list
	public void insertTail(COMP108A2Node newNode) {

		newNode.next = null;
		newNode.prev = tail;
		if (tail != null)
			tail.next = newNode;
		else head = newNode;
		tail = newNode;
	}

	// DO NOT change this method
	// delete the node at the head of the linked list
	public COMP108A2Node deleteHead() {
		COMP108A2Node curr;

		curr = head;
		if (curr != null) {
			head = head.next;
			if (head == null)
				tail = null;
			else
				head.prev = null;
		}
		return curr;
	}

	// DO NOT change this method
	// empty the cabinet by repeatedly removing head from the list
	public void emptyCab() {
		while (head != null)
			deleteHead();
	}


	// DO NOT change this method
	// this will turn the list into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	public String headToTail() {
		COMP108A2Node curr;
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
	// Only to be used for output, do not use it to manipulate the list
	public String tailToHead() {
		COMP108A2Node curr;
		String outString="";

		curr = tail;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.prev;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the frequency of the list nodes into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	public String headToTailFreq() {
		COMP108A2Node curr;
		String outString="";

		curr = head;
		while (curr != null) {
			outString += curr.freq;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

}
