package FinalFinal;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	static ArrayList<Card> deck = new ArrayList<Card>();
	
	public void addCards() {
		for (int i = 1; i <= 4; i++)
			for (int j = 2; j <= 14; j++)
				deck.add(new Card(i,j));
	}
	public void shuffleCards() {
		Collections.shuffle(deck);
	}
	public void giveCards(ArrayList<Card> hand) {
		for (int i = 0; i < 13; i++) {
			int card = (int) (Math.random() * deck.size());
			hand.add(deck.get(card));
			deck.remove(card);
		}
	}
}
