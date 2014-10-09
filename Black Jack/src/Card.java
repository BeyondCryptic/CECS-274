
public class Card {
	private String[] cardSuit;
	private String[] cardRank;
	private String[] cardSet;
	private int cardNumber;
	public Card() {
		cardSuit = new String[] {"Spades", "Hearts", "Diamonds", "Clubs"};
		cardRank = new String[] {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		cardSet = new String[52];
		cardNumber = 0;
	}
	public String[] createCards() {
		for (int i = 0; i < cardSuit.length; i++) {
			for (int j = 0; j < cardRank.length; j++) {
				cardSet[cardNumber] = cardRank[j] + " of " + cardSuit[i];
				cardNumber++;
			}
		}
		cardNumber = 0;
		return cardSet;
	}
}
