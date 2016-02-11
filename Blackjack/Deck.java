import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card>(52);
	
	public Deck() {
		this.addCards();
	}
	
	public Deck(boolean shuffle) {
		this.addCards();
		if (shuffle) {
			this.shuffleCards();
		}
	}
	
	public void addCards() {
		for (int i = 1; i <= 4; i++)
			for (int j = 2; j <= 14; j++)
				deck.add(new Card(i,j));
	}
	
	public void shuffleCards() {
		Collections.shuffle(deck);
	}
	
	public Card getCard() {
		/*for (int i = 0; i < 13; i++) {
			int card = (int) (Math.random() * deck.size());
			hand.add(deck.get(card));
			deck.remove(card);
		}*/
		return deck.remove((int) (Math.random() * deck.size()));
	}
}
