// https://en.wikipedia.org/wiki/Bubble_sort,
// https://www.geeksforgeeks.org/copy-constructor-in-java/

package gSmithPhoneBook;

public class PhoneBookList {
	private PhoneBookNode front;
	private int length;
	private String name;
	
	// constructor with a front node
	public PhoneBookList(String name, PhoneBookNode frontNode) {
		this.front = frontNode;
		this.length = 1;
		this.name = name;
		
	} // end of constructor with front node
	
	// empty constructor
	public PhoneBookList(String name) {
		this.front = null;
		this.length = 0;
		this.name = name;
		
	} // end of empty constructor
	
	// copy constructor
	public PhoneBookList(String name, PhoneBookList currentList) {
		PhoneBookNode currentNode = currentList.front;
		
		// every node from currentList is being copied and added,
		// except the id field of each copy is different than original
		while (currentNode != null) {
			this.add(new PhoneBookNode(currentNode));
			currentNode = currentNode.next;
			
		}
		
		this.name = name;
		
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
		System.out.println(getName().toUpperCase() + " PHONE BOOK\n");
		
		PhoneBookNode currentNode = front;
		
		for (int index = 0; index < this.getLength(); index++) {
			currentNode.printString();
			System.out.println();
			currentNode = currentNode.next;
			
		}
		System.out.println();
		System.out.println();
		
	} // end of printString
	
	public String getName() {
		return this.name;
		
	} // end of getName
	
	public void merge(PhoneBookList otherList) {
		// we actually need to create a "copy" of list so that
		// we have distinct nodes (new ids)
		PhoneBookList newList = new PhoneBookList("", otherList);
		
		PhoneBookNode thisEnd = this.getEnd();
		PhoneBookNode otherFront = newList.front;
		
		thisEnd.next = otherFront;
		this.length += newList.length;
		
	} // end of merge
	
	public void moveByID(PhoneBookList otherList, int nodeID) {
		PhoneBookNode movedNode = otherList.getNodeFromID(nodeID);
		if (movedNode != null) {
			otherList.remove(otherList.getIndexFromID(nodeID));
			this.add(movedNode);
			
		} else {
			System.out.println("Phone book entry not found. No entry to move.\n");
			
		}
		
	} // end of moveByID
	
	public void moveByIndex(PhoneBookList otherList, int index) {
		PhoneBookNode movedNode = otherList.getNode(index);
		if (movedNode != null) {
			otherList.remove(index);
			this.add(movedNode);
			
		} else {
			System.out.println("Phone book entry not found. No entry to move.\n");
			
		}
		
	} // end of moveByIndex
	
	public PhoneBookNode getNodeFromID(int id) {
		PhoneBookNode currentNode = this.front;
		
		while (currentNode != null) {
			if (currentNode.getID() == id) {
				return currentNode;
				
			} else {
				currentNode = currentNode.next;
				
			} // end of if/else
			
		} // end of while
		return currentNode;
		
	} // end of getNodeFromID
	
	public int getIndexFromID(int id) {
		PhoneBookNode currentNode = this.front;
		
		int index = 0;
		while (currentNode != null) {
			if (currentNode.getID() == id) {
				return index;
				
			} else {
				currentNode = currentNode.next;
				index++;
				
			} // end of if/else
			
		} // end of while
		return -1; // out of bounds index
		
	} // end of getIndexFromID
	
	public void modifyByID(int nodeID, String firstName, String lastName, String streetAddress, 
			String city, String stateAbbrev, Integer phoneAreaCode, Integer phoneNumber) {
		
		PhoneBookNode modifiedNode = getNodeFromID(nodeID);
		modifyNode(modifiedNode, firstName, lastName, streetAddress, city, stateAbbrev, phoneAreaCode, phoneNumber);
		
	} // end of modifyByID
	
	public void modifyByIndex(int index, String firstName, String lastName, String streetAddress, 
			String city, String stateAbbrev, Integer phoneAreaCode, Integer phoneNumber) {
		
		PhoneBookNode modifiedNode = getNode(index);
		modifyNode(modifiedNode, firstName, lastName, streetAddress, city, stateAbbrev, phoneAreaCode, phoneNumber);
		
	} // end of modifyByIndex
	
	// modify each field corresponding to an input parameter if the parameter is not null
	// we use the wrapper class Integer so that null is valid for phoneAreaCode and phoneNumber parameters
	public void modifyNode(PhoneBookNode node, String firstName, String lastName, String streetAddress, 
			String city, String stateAbbrev, Integer phoneAreaCode, Integer phoneNumber) {
		
		if (firstName != null) {
			node.setFirstName(firstName);
			
		}
		
		if (lastName != null) {
			node.setLastName(lastName);
			
		}
		
		if (streetAddress != null) {
			node.setStreetAddress(streetAddress);
			
		}
		
		if (city != null) {
			node.setCity(city);
			
		}
		
		if (stateAbbrev != null) {
			node.setStateAbbrev(stateAbbrev);
			
		}
		
		if (phoneAreaCode != null) {
			node.setPhoneAreaCode(phoneAreaCode);
			
		}
		
		if (phoneNumber != null) {
			node.setPhoneNumber(phoneNumber);
			
		}
		
	} // end of modifyNode

} // end of PhoneBookList class
