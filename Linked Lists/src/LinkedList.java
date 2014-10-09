import java.util.Random;
import java.util.Scanner;

public class LinkedList {
	public static int numberOfWords;
	public static String userChoice;
	public static String addWord;
	public static String deleteWord;
	public static List myList;
	public static boolean findWord;
	public static void main(String[] args) {
		myList = new List();
		askUser();
	}
	public static String createWords() {
		Random random = new Random();
		String word = "";
		for (int i = 0; i < 5; i++) {
			char letter = (char) (random.nextInt(26)+97);
			word = word + letter;
		}
		return word;
	}
	public static void askUser() {
		askNumber();
		for (int i = 0; i < numberOfWords; i++) {
			myList.addInOrder(createWords());
		}
		displayList();
		displayBar();
	}
	public static void displayList() {
		myList.display();
		System.out.println("\n\nList size: " + myList.size());
	}
	public static void askNumber() {
		Scanner scan = new Scanner(System.in);
		System.out.println("How many words do you want? Enter a number between 15 and 240.");
		numberOfWords = scan.nextInt();
		if (numberOfWords >=15 && numberOfWords <= 240) {
			System.out.println("Generating " + numberOfWords + " words now...\n");
		}
		else {
			System.out.println("Invalid number. Please try again...");
			askNumber();
		}
	}
	public static boolean findWord() {
		if (myList.find(deleteWord)) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void displayBar() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\n(A)dd     (D)elete     (Q)uit");
		userChoice = scan.nextLine().toLowerCase();
		if (userChoice.equals("a") || userChoice.equals("add")) {
			/*System.out.println("What word do you want to add?");
			addWord = scan.nextLine();*/
			addWord = createWords();
			myList.addInOrder(addWord);
			myList.display(addWord);
			System.out.println("\n\nList size: " + myList.size());
			displayBar();
		}
		else if (userChoice.equals("d") || userChoice.equals("delete")) {
			System.out.println("What word do you want to delete?");
			deleteWord = scan.nextLine();
			if (findWord()) {
				int findCount = myList.findCount();
				for (int i = 0; i < findCount; i++) {
					myList.delete(deleteWord);
				}
				//myList.delete(deleteWord);
				displayList();
				displayBar();
			}
			else {
				System.out.println("\nThe word is not in the list!\n");
				displayList();
				displayBar();
			}
		}
		else if (userChoice.equals("q") || userChoice.equals("quit")) {
			System.out.println("Bye!");
			System.exit(0);
		}
		else {
			System.out.println("Invalid choice! Try again.");
			displayBar();
		}
	}
}
