// Class: CS 145
// Date: 05/13/2022
// Assignment: Assignment 2 - Phone Book
// Reference: Chapters 1-16, 
// https://abbreviations.yourdictionary.com/articles/state-abbrev.html
//
// Purpose: Define the data fields used in PhoneBookNode class, and associated methods 
// IDE: Eclipse

package gSmithPhoneBook;

public class PhoneBookEntry {
	// unique entry identifier
	private int id;
	
	// fields that need to be accessed by PhoneBookNode
	protected String firstName;
	protected String lastName;
	protected String streetAddress;
	protected String city;
	protected StateAbbrev stateAbbrev;
	protected int phoneAreaCode;
	protected int phoneNumber;
	
	// class constants
	private static int currentID;
	private static int phoneAreaCodeLength;
	private static int phoneNumberLength;
	
	static {
		currentID = 0;
		phoneAreaCodeLength = 3;
		phoneNumberLength = 7;
		
	} // end of static block
	
	enum StateAbbrev {
		// states
		AL,
		AK,
		AZ,
		AR,
		CA,
		CO,
		CT,
		DE,
		FL,
		GA,
		HI,
		ID,
		IL,
		IN,
		IA,
		KS,
		KY,
		LA,
		ME,
		MD,
		MA,
		MI,
		MN,
		MS,
		MO,
		MT,
		NE,
		NV,
		NH,
		NJ,
		NM,
		NY,
		NC,
		ND,
		OH,
		OK,
		OR,
		PA,
		RI,
		SC,
		SD,
		TN,
		TX,
		UT,
		VT,
		VA,
		WA,
		WV,
		WI,
		WY,
		
		// commonwealths & territories
		AS, // american samoa
		DC, // district of columbia
		GU, // guam
		MP, // northern mariana islands
		PR, // puerto rico
		VI, // virgin islands
		
		// placeholder
		OTHER;
	} // end of StateAbbrev enum
	
	public PhoneBookEntry(String firstName, String lastName, String streetAddress, String city,
			String stateAbbrev, int phoneAreaCode, int phoneNumber) {
		
		setFirstName(firstName);
		setLastName(lastName);
		setStreetAddress(streetAddress);
		setCity(city);
		setStateAbbrev(stateAbbrev);
		setPhoneAreaCode(phoneAreaCode);
		setPhoneNumber(phoneNumber);
		setID();
		
	} // end of constructor
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		
	} // end of setFirstName
	
	public String getFirstName() { 
		return this.firstName;
		
	} // end of getFirstName
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
		
	} // end of setLastName
	
	public String getLastName() { 
		return this.lastName;
		
	} // end of getLastName
	
	public String getCompleteName() {
		return getFirstName() + " " + getLastName();
		
	} // end of getCompleteName
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
		
	} // end of setStreetAddress
	
	public String getStreetAddress() { 
		return this.streetAddress;
		
	} // end of getStreetAddress
	
	public void setCity(String city) {
		this.city = city;
		
	} // end of setCity
	
	public String getCity() { 
		return this.city;
		
	} // end of getCity
	
	public void setStateAbbrev(String stateAbbrev) {
		// if stateAbbrev is not a constant in StateAbbrev enum,
		// assign OTHER constant
		try {
			this.stateAbbrev = StateAbbrev.valueOf(stateAbbrev);
			
		} catch (IllegalArgumentException e) {
			this.stateAbbrev = StateAbbrev.OTHER;
			
		} // end of try-catch
		
	} // end of setStateAbbrev
	
	public String getStateAbbrev() {
		switch(this.stateAbbrev) {
			case OTHER: 
				return "Other";
				
			default:
				return this.stateAbbrev.toString();
		
		} // end of switch-case
		
	} // end of getStateAbbrev
	
	public String getCityAndState() {
		return getCity() + ", " + getStateAbbrev();
		
	} // end of getCityAndState
	
	public void setPhoneNumber(int phoneNumber) {
		if (getIntLength(phoneNumber) == PhoneBookEntry.phoneNumberLength) {
			this.phoneNumber = phoneNumber;
			
		} else {
			throw new IllegalArgumentException(
					String.format("A phone number must be %d digits", PhoneBookEntry.phoneNumberLength));
			
		} // end of if/else
		
	} // end of setPhoneNumber
	
	public int getPhoneNumber() { 
		return this.phoneNumber;
		
	} // end of getPhoneNumber
	
	public String getPhoneNumberString() {
		return Integer.toString(getPhoneNumber());
		
	} // end of getPhoneNumberString
	
	public void setPhoneAreaCode(int phoneAreaCode) {
		if (getIntLength(phoneAreaCode) == PhoneBookEntry.phoneAreaCodeLength) {
			this.phoneAreaCode = phoneAreaCode;
			
		} else {
			throw new IllegalArgumentException(
					String.format("An area code must be %d digits", PhoneBookEntry.phoneAreaCodeLength));
			
		} // end of if/else
		
	} // end of setPhoneAreaCode
	
	public int getPhoneAreaCode() { 
		return this.phoneAreaCode;
		
	} // end of getPhoneAreaCode
	
	public String getCompletePhoneNumber() {
		String phoneNumberString = getPhoneNumberString();
		
		return "(" + getPhoneAreaCode() + ") " + phoneNumberString.substring(0,3) 
			+ '-' + phoneNumberString.substring(3);
		
	} // end of getCompletePhoneNumber
	
	public void printString() {
		System.out.println(getID());
		System.out.println(getCompleteName());
		System.out.println(getStreetAddress());
		System.out.println(getCityAndState());
		System.out.println(getCompletePhoneNumber());
		
	} // end of printString
	
	public void setID() {
		this.id = PhoneBookEntry.currentID;
		PhoneBookEntry.currentID++;
		
	} // end of setID
	
	public int getID() {
		return this.id;
		
	} // end of getID
	
	public int getIntLength(int inputInt) {
		int length = 0;
		
		do {
			inputInt /= 10;
			length++;
			
		} while (inputInt != 0); // end of do-while
		
		return length;
		
	} // end of getIntLength

} // end of PhoneBookEntry class
