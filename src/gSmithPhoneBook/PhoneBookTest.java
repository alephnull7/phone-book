package gSmithPhoneBook;

public class PhoneBookTest {
	
	public static void main(String[] args) {
		// initialize empty list
		PhoneBookList bellinghamList = new PhoneBookList("Bellingham");
		System.out.printf("Empty list:%n%n");
		bellinghamList.printString();
		
		System.out.printf("Add entries to list:%n%n");
		bellinghamList.add(
				new PhoneBookNode("George", "Stewart", "123 Main Street", "Bellingham", "WZ", 360, 7654321), 0);
		bellinghamList.printString();
		
		bellinghamList.add(
				new PhoneBookNode("Franklin", "Smith", "321 Main Street", "Bellingham", "WA", 206, 1234567));
		bellinghamList.printString();
		
		bellinghamList.add(
				new PhoneBookNode("Roger", "Jobs", "777 Fancy Drive", "Bellingham", "WA", 360, 1112222), 2);
		bellinghamList.printString();
		
		System.out.printf("Sort list by last name:%n%n");
		bellinghamList.lastNameBubbleSort();
		bellinghamList.printString();
		
		// initialize list with front
		System.out.printf("Create a list with an initial entry and add another:%n%n");
		PhoneBookList seattleList = new PhoneBookList("Seattle",
				new PhoneBookNode("Ravi", "Ram", "333 Belmont Avenue", "Seattle", "WA", 206, 9990000));
		seattleList.add(
				new PhoneBookNode("Abe", "Smith", "4321 This Street", "Seattle", "WA", 206, 1000000));
		seattleList.printString();
		
		// create a new distinct copy of a list (new node ids)
		System.out.printf("Create a new list that contains the information of both previous lists, but distinct entries:%n%n");
		PhoneBookList washingtonList = new PhoneBookList("Washington", bellinghamList);
		washingtonList.merge(seattleList);
		washingtonList.printString();
		System.out.printf("We add a new entry, with its order corresponding to last name order:%n%n");
		washingtonList.addAlphabetically(
				new PhoneBookNode("John", "Keller", "Somewhere", "Olympia", "WA", 564, 9876543));
		washingtonList.printString();
		System.out.printf("We remove an entry from the list:%n%n");
		washingtonList.remove(3);
		washingtonList.printString();
		
		System.out.printf("We see that the original lists are unchanged:%n%n");
		bellinghamList.printString();
		seattleList.printString();
		
		// to move or update nodes we use either the id field of the node,
		// or the index of the node within in the list, so that we will find a unique node
		
		// move node to another list
		System.out.printf("We attempt, both unsuccessfully and successfully, to move an entry using its ID:%n%n");
		bellinghamList.moveByID(seattleList, 0);
		bellinghamList.moveByID(seattleList, 3);
		bellinghamList.printString();
		seattleList.printString();
		
		System.out.printf("We attempt, both unsuccessfully and successfully, to move an entry using its index:%n%n");
		seattleList.moveByIndex(bellinghamList, -1);
		seattleList.moveByIndex(bellinghamList, 1);
		bellinghamList.printString();
		seattleList.printString();
		
		// modify node in a list
		System.out.printf("We modify entries using their indices:%n%n");
		seattleList.modifyByIndex(0, null, null, null, null, null, null, null);
		seattleList.printString();
		seattleList.modifyByIndex(0, "Abraham", null, null, null, null, null, null);
		seattleList.printString();
		seattleList.modifyByIndex(0, null, null, null, null, null, null, 2000001);
		seattleList.printString();
		seattleList.modifyByIndex(1, null, null, "111 Yellow Brick Road", "Seattle", null, null, null);
		seattleList.printString();
		
		System.out.printf("We look at the current state of a list and then modify an entry using its ID:%n%n");
		bellinghamList.printString();
		bellinghamList.modifyByID(3, null, null, "321 Alabama Street", "Bellingham", null, null, null);
		bellinghamList.printString();

	} // end of main

} // end of PhoneBookTest class
