// https://en.wikipedia.org/wiki/Bubble_sort,
// https://www.geeksforgeeks.org/copy-constructor-in-java/

package gSmithPhoneBook;

public class PhoneBookList {
	private PhoneBookNode front;
	private int length;
	
	// constructor with a front node
	public PhoneBookList(PhoneBookNode frontNode) {
		this.front = frontNode;
		this.length = 1;
		
	} // end of constructor with front node
	
	// empty constructor
	public PhoneBookList() {
		this.front = null;
		this.length = 0;
		
	} // end of empty constructor
	
	// copy constructor
	public PhoneBookList(PhoneBookList currentList) {
		PhoneBookNode currentNode = currentList.front;
		
		// every node from currentList is being copied and added,
		// except the id field of each copy is different than original
		while (currentNode != null) {
			this.add(new PhoneBookNode(currentNode));
			currentNode = currentNode.next;
			
		}
		
	} // end of copy constructor
	
	// find the last node in the list
	public PhoneBookNode getEnd() {
		if (front == null) {
			return null;
			
		} else {
			PhoneBookNode currentNode = front;
			
			while(currentNode.next != null) {
				currentNode = currentNode.next;
				
			}
			
			return currentNode;
		}
		
	} // end of getEnd
	
	// get node at indicated index
	public PhoneBookNode getNode(int index) {
		if (index >= this.length || index < 0) {
			return null;
			
		} else {
			PhoneBookNode currentNode = front;
			
			for (int currentIndex = 0; currentIndex < index; currentIndex++) {
				currentNode = currentNode.next;
				
			}
			
			return currentNode;
			
		}
		
	} // end of getNode

	// add node at end of list
	public void add(PhoneBookNode newNode) {
		if (front == null) {
			front = newNode;
			
		} else {
			PhoneBookNode endNode = getEnd();
			endNode.next = newNode;
			
		}
		
		this.length++;
		
	} // end of end of list add
	
	// add node at indicated index
	public void add(PhoneBookNode newNode, int index) {
		if (index == this.length) {
			add(newNode);
			
			// return to calling method
			return;	
		} 
		
		PhoneBookNode nextNode = getNode(index);
		
		// if the index is out of bounds
		if (nextNode == null) {
			throw new NullPointerException();
		
		// if we're dealing with the first node
		} else if (nextNode == front) {
			front = newNode;
			
		} else {
			PhoneBookNode previousNode = getNode(index - 1);
			
			// set newMode at index
			previousNode.next = newNode;

		}
		
		newNode.next = nextNode;
		this.length++;
		
	} // end of indexed add
	
	// add node in LastName alphabetical order
	public void addAlphabetically(PhoneBookNode newNode) {
		if (front == null) {
			front = newNode;
			this.length++;
			
		} else {
			PhoneBookNode currentNode = front;
			
			int index = 0;
			while(currentNode.getIsLastNameFirst(newNode)) {
				currentNode = currentNode.next;
				index++;
				
			}
			
			add(newNode, index);
			
		}
		
	} // end of addAlphabetically
	
	// remove node at indicated index
	public void remove(int index) {
		PhoneBookNode currentNode = getNode(index);
		
		// if the index is out of bounds
		if (currentNode == null) {
			throw new NullPointerException();
		
		// if we're dealing with the first node
		} else if (currentNode == front) {
			// change the front field to second node
			front = currentNode.next;
			
		} else {
			PhoneBookNode previousNode = getNode(index - 1);
			PhoneBookNode nextNode = currentNode.next;
			
			// move next reference to nextNode
			previousNode.next = nextNode;
			
		}
		
		// orphan currentNode
		currentNode.next = null;
		
		this.length--;
		
	} // end of remove
	
	// swap node at indicated index with the next node
	public void swapNodeWithNext(int nodeIndex) {
		int nextNodeIndex = nodeIndex + 1;
		PhoneBookNode nextNode = getNode(nextNodeIndex);
		
		// move nextNode
		remove(nextNodeIndex);
		add(nextNode, nodeIndex);
		
	} // end of swapNodeWithNext
	
	public int getLength() {
		return this.length;
		
	} // end of getLength
	
	// sorting of nodes by lastName using bubble sort
	public void lastNameBubbleSort() {
		PhoneBookNode currentNode;
		PhoneBookNode nextNode;
		boolean swapBool;
		
		// on first pass we want the final pair of 
		// nodes to start with the second to last node
		int maxIndex = this.length - 1;
		
		// keep iterating through nodes until no
		// swaps take place
		do {
			swapBool = false;
			currentNode = front;
			
			// iterate through all node pairs that need to be tested
			for (int nodeIndex = 0; nodeIndex < maxIndex; nodeIndex++) {
				nextNode = currentNode.next;
				
				// if we need to swap currentNode and nextNode
				if(!currentNode.getIsLastNameFirst(nextNode)) {
					swapNodeWithNext(nodeIndex);
					swapBool = true;
					
					// swap occurred, so next node pair begins with
					// currentNode; no update needed for next iteration
					
				} else {
					// no swap occurred, so next node pair begins with
					// nextNode
					currentNode = nextNode;
					
				}
				// this.printString();
				
			} // end of for loop 
			
			// the last node is properly sorted
			maxIndex--;
			
		} while (swapBool); // end of do-while
		
	} // end of lastNameBubbleSort
	
	// sorting of nodes by lastName and then firstName using bubble sort
	public void completeNameBubbleSort() {
		PhoneBookNode currentNode;
		PhoneBookNode nextNode;
		boolean swapBool;
		
		// on first pass we want the final pair of 
		// nodes to start with the second to last node
		int maxIndex = this.length - 1;
		
		// keep iterating through nodes until no
		// swaps take place
		do {
			swapBool = false;
			currentNode = front;
			
			// iterate through all node pairs that need to be tested
			for (int nodeIndex = 0; nodeIndex < maxIndex; nodeIndex++) {
				nextNode = currentNode.next;
				
				// if we need to swap currentNode and nextNode due to lastName
				if(!currentNode.getIsLastNameFirst(nextNode)) {
					swapNodeWithNext(nodeIndex);
					swapBool = true;
					
					// swap occurred, so next node pair begins with
					// currentNode; no update needed for next iteration
				
				// if lastName of the two nodes is the same, 
				// but we need to swap due to firstName
				} else if (nextNode.getIsLastNameFirst(currentNode) && nextNode.getIsFirstNameFirst(currentNode)) {
					swapNodeWithNext(nodeIndex);
					swapBool = true;
					
				}	else {
					// no swap occurred, so next node pair begins with
					// nextNode
					currentNode = nextNode;
					
				}
				// this.printString();
				
			} // end of for loop 
			
			// the last node is properly sorted
			maxIndex--;
			
		} while (swapBool); // end of do-while
		
	} // end of completeNameBubbleSort
	
	public void printString() {
		PhoneBookNode currentNode = front;
		
		for (int index = 0; index < this.getLength(); index++) {
			currentNode.printString();
			System.out.println();
			currentNode = currentNode.next;
			
		}
		System.out.println();
		
	} // end of printString
	
	public void merge(PhoneBookList otherList) {
		PhoneBookNode thisEnd = this.getEnd();
		PhoneBookNode otherFront = otherList.front;
		
		thisEnd.next = otherFront;
		this.length += otherList.length;
		
	} // end of merge
	
}
