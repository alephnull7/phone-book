package gSmithPhoneBook;

public class PhoneBookNode extends PhoneBookEntry {
	// PhoneBookList class accesses next
	protected PhoneBookNode next;
	
	public PhoneBookNode(String firstName, String lastName, String streetAddress, String city, String stateAbbrev,
			int phoneAreaCode, int phoneNumber) {
		super(firstName, lastName, streetAddress, city, stateAbbrev, phoneAreaCode, phoneNumber);
		setNext();
		
	} // end of parameterized constructor
	
	// copy constructor
	public PhoneBookNode(PhoneBookNode currentNode) {
		// this is not a true copy since the id field will be different;
		// all PhoneBookNodes are distinct
		super(currentNode.firstName, currentNode.lastName, currentNode.streetAddress, 
				currentNode.city, currentNode.stateAbbrev.toString(), 
				currentNode.phoneAreaCode, currentNode.phoneNumber);
		
		// we want this new node to initially be orphaned
		setNext();
		
	} // end of copy constructor
	
	public void setNext() {
		this.next = null;
		
	} // end of null setNext
	
	public void setNext(PhoneBookNode nextNode) {
		this.next = nextNode;
		
	} // end of setNext
	
	public boolean getIsThisStringFirst(String thisString, String otherString) {
		// if the strings are the same we'll pick thisString as being 
		// first alphabetically so we won't have to switch nodes on a sort
		if (thisString.equalsIgnoreCase(otherString)) {
			return true;
			
		}
		
		int thisStringLength = thisString.length();
		int otherStringLength = otherString.length();
		
		// we want to compare string chars for as long as possible
		int minStringLength = Math.min(thisStringLength, otherStringLength);
		
		char thisChar, otherChar;
		
		for (int charIndex = 0; charIndex < minStringLength; charIndex++) {
			thisChar = thisString.charAt(charIndex);
			otherChar = otherString.charAt(charIndex);
			
			if (thisChar > otherChar) {
				return false;
				
			} else if (thisChar < otherChar) {
				return true;
				
			} // end of if else
			
		} // end of for loop
		
		// we only reach here if all chars tested have been equal,
		// but the strings themselves aren't
		
		if (otherStringLength > thisStringLength) {
			return true;
			
		} else {
			return false;
			
		}
		
	} // end of getIsThisStringFirst
	
	public boolean getIsLastNameFirst(PhoneBookNode otherNode) {
		boolean isThisStringFirst = getIsThisStringFirst(this.lastName, otherNode.lastName);
		return isThisStringFirst;
		
	} // end of getIsLastNameFirst
	
	public boolean getIsFirstNameFirst(PhoneBookNode otherNode) {
		boolean isThisStringFirst = getIsThisStringFirst(this.firstName, otherNode.firstName);
		return isThisStringFirst;
		
	} // end of getIsFirstNameFirst
	
	public boolean getIsStateAbbrevFirst(PhoneBookNode otherNode) {
		boolean isThisStringFirst = getIsThisStringFirst(this.getStateAbbrev(), otherNode.getStateAbbrev());
		return isThisStringFirst;
		
	} // end of getIsStateAbbrevFirst
	
	public boolean getIsCityFirst(PhoneBookNode otherNode) {
		boolean isThisStringFirst = getIsThisStringFirst(this.city, otherNode.city);
		return isThisStringFirst;
		
	} // end of getIsCityFirst

} // end of PhoneBookNode class
