import java.util.StringTokenizer;

public class DealerHand {
	private String[] cardsInHand;
	private String tempStorage;
	private int cardValue;
	private int handValue = 0;
	private int finalHandValue;
	private int numberOfAces;
	private int cardIndex;
	private boolean checkAces;
	public DealerHand() {
		cardsInHand = new String[11];
		for (int i = 0; i < cardsInHand.length; i++) {
			cardsInHand[i] = "0";
		}
		cardIndex = 0;
		numberOfAces = 0;
		checkAces = true;
	}
	public String pickUpCard(String card) {
		cardsInHand[cardIndex] = card;
		cardIndex++;
		return card;
	}
	public int getCardValue(int index, int handValue) {
		StringTokenizer token = new StringTokenizer(cardsInHand[index]);
		tempStorage = token.nextToken(" ");
		if (tempStorage.equals("Jack") || tempStorage.equals("Queen") || tempStorage.equals("King")) {
			cardValue = 10;
		}
		else if (tempStorage.equals("Ace")) {
			if (handValue <= 10 && tempStorage.equals("Ace") && checkAces == true) {
				cardValue = 11;
				numberOfAces++;
			}
			else {
				cardValue = 1;
			}
		}
		else {
			cardValue = Integer.parseInt(tempStorage);
		}
		return cardValue;
	}
	public int getHandValue() {
		handValue = 0;
		for (int i = 0; i < cardsInHand.length; i++) {
			checkAces = true;
			handValue = handValue + getCardValue(i, handValue);
			checkAces = false;
		}
		if (handValue >= 22 && numberOfAces >= 2) {
			System.out.println("Hand value: " + handValue + " Ace #: " + numberOfAces);
			handValue = handValue - numberOfAces*10;
		}
		finalHandValue = handValue;
		return finalHandValue;
	}
	public void display(int hideCards) {
		if (hideCards == 1) {
			System.out.print("?????, ");
			for (int i = 1; i < cardIndex; i++) {
				if (i < cardIndex-1) {
					System.out.print(cardsInHand[i] + ", ");
				}
				else {
					System.out.println(cardsInHand[i]);
				}
			}
		}
		else {
			for (int i = 0; i < cardIndex; i++) {
				if (i < cardIndex-1) {
					System.out.print(cardsInHand[i] + ", ");
				}
				else {
					System.out.println(cardsInHand[i]);
				}
			}
		}
	}
}
