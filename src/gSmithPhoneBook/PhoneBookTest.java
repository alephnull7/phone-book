package gSmithPhoneBook;

public class PhoneBookTest {
	
	public static void main(String[] args) {
		// initialize empty list
		PhoneBookList bellinghamList = new PhoneBookList();
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
		PhoneBookList seattleList = new PhoneBookList(
				new PhoneBookNode("Ravi", "Ram", "333 Belmont Avenue", "Seattle", "WA", 206, 9990000));
		seattleList.add(
				new PhoneBookNode("Abe", "Smith", "4321 This Street", "Seattle", "WA", 206, 1000000));
		seattleList.printString();
		
		// initialize deep copy of list
		PhoneBookList washingtonList = new PhoneBookList(bellinghamList);
		washingtonList.merge(seattleList);
		washingtonList.printString();
		washingtonList.addAlphabetically(
				new PhoneBookNode("John", "Keller", "Somewhere", "Olympia", "WA", 564, 9876543));
		washingtonList.printString();
		
		bellinghamList.printString();
		seattleList.printString();

	}

}
