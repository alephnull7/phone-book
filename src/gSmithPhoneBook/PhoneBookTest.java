package gSmithPhoneBook;

public class PhoneBookTest {
	
	public static void main(String[] args) {
		// initialize empty list
		PhoneBookList bellinghamList = new PhoneBookList("Bellingham");
		System.out.println("Empty Bellingham list:");
		bellinghamList.printString();
		
		bellinghamList.add(
				new PhoneBookNode("George", "Stewart", "123 Main Street", "Bellingham", "WZ", 360, 7654321), 0);
		bellinghamList.printString();
		
		bellinghamList.add(
				new PhoneBookNode("Franklin", "Smith", "321 Main Street", "Bellingham", "WA", 206, 1234567));
		bellinghamList.printString();
		
		bellinghamList.add(
				new PhoneBookNode("Roger", "Jobs", "777 Fancy Drive", "Bellingham", "WA", 360, 1112222), 2);
		bellinghamList.printString();
		
		bellinghamList.lastNameBubbleSort();
		bellinghamList.printString();
		
		// initialize list with front
		PhoneBookList seattleList = new PhoneBookList("Seattle",
				new PhoneBookNode("Ravi", "Ram", "333 Belmont Avenue", "Seattle", "WA", 206, 9990000));
		seattleList.add(
				new PhoneBookNode("Abe", "Smith", "4321 This Street", "Seattle", "WA", 206, 1000000));
		seattleList.printString();
		
		// create a new distinct copy of a list (new node ids)
		PhoneBookList washingtonList = new PhoneBookList("Washington", bellinghamList);
		washingtonList.merge(seattleList);
		washingtonList.printString();
		washingtonList.addAlphabetically(
				new PhoneBookNode("John", "Keller", "Somewhere", "Olympia", "WA", 564, 9876543));
		washingtonList.printString();
		
		bellinghamList.printString();
		seattleList.printString();
		
		// to move or update nodes we use either the id field of the node,
		// or the index of the node within in the list, so that we will find a unique node
		
		// move node to another list
		bellinghamList.moveByID(seattleList, 0);
		bellinghamList.moveByID(seattleList, 3);
		bellinghamList.printString();
		seattleList.printString();
		
		seattleList.moveByIndex(bellinghamList, -1);
		seattleList.moveByIndex(bellinghamList, 1);
		bellinghamList.printString();
		seattleList.printString();
		
		// modify node in a list
		seattleList.modifyByIndex(0, null, null, null, null, null, null, null);
		seattleList.printString();
		seattleList.modifyByIndex(0, "Abraham", null, null, null, null, null, null);
		seattleList.printString();
		seattleList.modifyByIndex(0, null, null, null, null, null, null, 2000001);
		seattleList.printString();
		seattleList.modifyByIndex(1, null, null, "111 Yellow Brick Road", "Seattle", null, null, null);
		seattleList.printString();
		
		bellinghamList.printString();
		bellinghamList.modifyByID(3, null, null, "321 Alabama Street", "Bellingham", null, null, null);
		bellinghamList.printString();

	} // end of main

} // end of PhoneBookTest class
