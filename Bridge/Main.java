package FinalFinal;

import java.util.ArrayList;

public class Main {
	static ArrayList<Card> p1 = new ArrayList<Card>(13);
	static AI p2 = new AI(),
			p3 = new AI(),
			p4 = new AI();
	
	public static void main(String[] args) {
		Deck deck = new Deck();

		deck.addCards();
		deck.shuffleCards();
		deck.giveCards(p1);
		deck.giveCards(p2.hand);
		deck.giveCards(p3.hand);
		deck.giveCards(p4.hand);
		GUI.main(args);
		System.out.println("Thanks for playing!");
	}
}
