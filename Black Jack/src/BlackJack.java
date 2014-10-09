import java.util.Scanner;
public class BlackJack {
	
	public static int moneyInPocket = 10;
	public static int betAmount;
	public static boolean playAgain;
	public static boolean playerBusted;
	public static boolean dealerBusted;
	public static boolean playerStand;
	public static boolean dealerStand;
	public static Card myCard;
	public static Deck myDeck;
	
	public static void main(String[] args) {
		showMenu();	
	}
	private static void showMenu() {
		boolean menu = true;
		String choice;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select an option:\n"
				+ "1. New Deck\n"
				+ "2. Shuffle cards in Deck\n"
				+ "3. Display all cards remaining in the deck\n"
				+ "4. Play Black Jack\n"
				+ "5. Exit game");
		
		if (playAgain == false) {
			myCard = new Card();
			myDeck = new Deck();
		}
		
		while (menu == true) {
			
			choice = scan.nextLine();
			//System.out.println(choice);
			
			if (choice.equals("1")) {
				if (playAgain == true) {
					System.out.println("You cannot create a new deck if you want to play again!");
				}
				else {
					myDeck.addCards(myCard.createCards());
					System.out.println("Deck created.");
				}
		}
			if (choice.equals("2")) {
				myDeck.shuffle();
				System.out.println("Deck shuffled.");
			}
			if (choice.equals("3")) {
				myDeck.display();
				System.out.println("\nPlease select an option:\n"
						+ "1. New Deck\n"
						+ "2. Shuffle cards in Deck\n"
						+ "3. Display all cards remaining in the deck\n"
						+ "4. Play Black Jack\n"
						+ "5. Exit game");
			}
			if (choice.equals("4")) {
				playGame();
			}
			if (choice.equals("5")) {
				System.exit(0);
			}
		}
	}
	private static void askBet() {
		if (moneyInPocket == 0){
			System.out.println("You have no more money! You cannot play anymore!");
			System.exit(0);
		}
		Scanner scan = new Scanner(System.in);
		System.out.println("You have " + moneyInPocket + " dollars. How much do you want to bet?");
		betAmount = scan.nextInt();
		System.out.println("You are betting " + betAmount + " dollars.");
		moneyInPocket = moneyInPocket - betAmount;
	}
	private static void playGame() {
		Scanner scan = new Scanner(System.in);
		String choice;
		DealerHand dHand = new DealerHand();
		Hand myHand = new Hand();
		askBet();
		if (myDeck.deal() == null) {
			System.out.println("\nWe ran out of cards. Please create/shuffle a new deck.\n");
			playerBusted = false;
			dealerBusted = false;
			playerStand = false;
			dealerStand = false;
			playAgain = false;
			showMenu();
		}
		else {
			String tempCard = myDeck.deal();
			if (tempCard == null) {
				System.out.println("\nWe ran out of cards. Please create/shuffle a new deck.\n");
				playerBusted = false;
				dealerBusted = false;
				playerStand = false;
				dealerStand = false;
				playAgain = false;
				showMenu();
			}
			myDeck.increaseCardIndex();
			String tempCard2 = myDeck.deal();
			if (tempCard2 == null) {
				System.out.println("\nWe ran out of cards. Please create/shuffle a new deck.\n");
				playerBusted = false;
				dealerBusted = false;
				playerStand = false;
				dealerStand = false;
				playAgain = false;
				showMenu();
			}
			myDeck.increaseCardIndex();
			String tempCard3 = myDeck.deal();
			if (tempCard3 == null) {
				System.out.println("\nWe ran out of cards. Please create/shuffle a new deck.\n");
				playerBusted = false;
				dealerBusted = false;
				playerStand = false;
				dealerStand = false;
				playAgain = false;
				showMenu();
			}
			myDeck.increaseCardIndex();
			String tempCard4 = myDeck.deal();
			if (tempCard4 == null) {
				System.out.println("\nWe ran out of cards. Please create/shuffle a new deck.\n");
				playerBusted = false;
				dealerBusted = false;
				playerStand = false;
				dealerStand = false;
				playAgain = false;
				showMenu();
			}
			myDeck.increaseCardIndex();
			System.out.println("\nCard in dealer's hand: ?????");
			dHand.pickUpCard(tempCard);
			System.out.println("\nCard in your hand: " + myHand.pickUpCard(tempCard2));
			System.out.println("\nCard in dealer's hand: " + dHand.pickUpCard(tempCard3));
			System.out.println("\nCard in your hand: " + myHand.pickUpCard(tempCard4));
			System.out.println("\nCurrent cards in dealer's hand: ");
			dHand.display(1);
			System.out.println("Dealer hand value (shhhhh!): " + dHand.getHandValue());
			System.out.println("\nCurrent cards in your hand: ");
			myHand.display();
			System.out.println("Player hand value: " + myHand.getHandValue());
			while (playerBusted == false && playerStand == false) {
				System.out.println("\nGet a new card? [Y/N]");
				
				choice = scan.nextLine();
				
				if (choice.toLowerCase().equals("y")) {
					String tempCard5 = myDeck.deal();
					if (tempCard5 == null) {
						System.out.println("\nWe ran out of cards. Please create/shuffle a new deck.\n");
						playerBusted = false;
						dealerBusted = false;
						playerStand = false;
						dealerStand = false;
						playAgain = false;
						showMenu();
					}
					System.out.println("\nPlayer draws: " + myHand.pickUpCard(tempCard5));
					myDeck.increaseCardIndex();
					System.out.println("\nCurrent cards in your hand: ");
					myHand.display();
					if (myHand.getHandValue() > 21) {
						System.out.println("Player hand value: " + myHand.getHandValue());
						System.out.println("\nPlayer busted!");
						playerBusted = true;
						bustedWinOrLose();
					}
					else {
						System.out.println("Player hand value: " + myHand.getHandValue());
					}
				}
				else if (choice.toLowerCase().equals("n")) {
					System.out.println("\nCurrent cards in your hand: ");
					myHand.display();
					System.out.println("Player hand value: " + myHand.getHandValue());
					System.out.println("Player stands.");
					playerStand = true;
				}
			}
			while (dealerStand == false && playerStand == true) {
				if (dHand.getHandValue() < 17) {
					while (dHand.getHandValue() < 17) {
						String tempCard6 = myDeck.deal();
						if (myDeck.deal() == null) {
							System.out.println("\nWe ran out of cards. Please create/shuffle a new deck.\n");
							playerBusted = false;
							dealerBusted = false;
							playerStand = false;
							dealerStand = false;
							playAgain = false;
							showMenu();
						}
						System.out.println("\nDealer draws: " + dHand.pickUpCard(tempCard6));
						myDeck.increaseCardIndex();
						System.out.println("Current cards in dealer's hand: ");
						dHand.display(0);
					}
				}
				else {
					if (dHand.getHandValue() > 21 && dealerBusted == false) {
						System.out.println("\nDealer hand value: " + dHand.getHandValue());
						System.out.println("Dealer busted!");
						dealerBusted = true;
						bustedWinOrLose();
					}
					else if (dealerBusted == false){
						System.out.println("\nCurrent cards in dealer's hand: ");
						dHand.display(0);
						System.out.println("Dealer hand value: " + dHand.getHandValue());
						System.out.println("Dealer stands.");
						dealerStand = true;
					}
				}
			}
		}
		if (dealerStand == true && playerStand == true) {
			if (dHand.getHandValue() > myHand.getHandValue()) {
				System.out.println("\nSorry! The house wins!");
				System.out.println("You currently have: " + moneyInPocket + " dollars.");
				playAgain();
			}
			else if (myHand.getHandValue() > dHand.getHandValue()) {
				System.out.println("\nCongratulations! You won!");
				moneyInPocket = moneyInPocket + (betAmount*2);
				System.out.println("You currently have: " + moneyInPocket + " dollars.");
				playAgain();
			}
			else if (myHand.getHandValue() == dHand.getHandValue()) {
				System.out.println("\nIt's a tie!");
				moneyInPocket = moneyInPocket + (betAmount*2);
				System.out.println("You currently have: " + moneyInPocket + " dollars.");
				playAgain();
			}
		}
	}
	private static void bustedWinOrLose() {
		if (dealerBusted == true || playerBusted == true) {
			if (playerBusted == true) {
				System.out.println("\nSorry! The house wins!");
				System.out.println("You currently have: " + moneyInPocket + " dollars.");
				playAgain();
			}
			else if (dealerBusted == true) {
				System.out.println("\nCongratulations! You won!");
				moneyInPocket = moneyInPocket + (betAmount*2);
				System.out.println("You currently have: " + moneyInPocket + " dollars.");
				playAgain();
			}
		}
	}
	private static void playAgain() {
			System.out.println("\nDo you want to play again? [Y/N]");
			Scanner scan = new Scanner(System.in);
			String choice = scan.nextLine();
			if (choice.toLowerCase().equals("y")&& moneyInPocket != 0) {
				playerBusted = false;
				dealerBusted = false;
				playerStand = false;
				dealerStand = false;
				playAgain = true;
				playGame();
			}
			else {
				if (moneyInPocket == 0){
					System.out.println("You have no more money! You cannot play anymore even if you didn't want to!");
					System.exit(0);
				}
				playerBusted = false;
				dealerBusted = false;
				playerStand = false;
				dealerStand = false;
				playAgain = true;
				showMenu();
		}
	}
}
