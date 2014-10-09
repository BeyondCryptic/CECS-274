import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Deck {
	public String[] Deck;
	private int cardIndex;
	public Deck() {
	}
	public void addCards(String[] card) {
		Deck = Arrays.copyOf(card, card.length);
	}
	public void shuffle() {
		Random random = new Random();
		Collections.shuffle(Arrays.asList(Deck), random);
		cardIndex = 0;
	}
	public void display() {
		for (int i = cardIndex; i < Deck.length; i++) {
			System.out.println(Deck[i]);
		}
	}
	public String deal() {
		if (cardIndex >= 52) {
			return null;
		}
		else {
			//String tempCardDeal;
			return Deck[cardIndex];	
		}
	}
	public void increaseCardIndex() {
		Deck[cardIndex] = null;
		cardIndex++;
	}
}
