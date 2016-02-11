package FinalFinal;

import java.util.ArrayList;

public class AI {
	ArrayList<Card> hand = new ArrayList<Card>(13);
	
	public boolean hasSuit(int suit) {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).suit == suit) {
				return true;
			}
		}
		return false;
	}

	public int playCardIndex(int suit) {
		if (hasSuit(suit)) {
			for (int i = 0; i < hand.size(); i++) {
				Card card = hand.get(i);
				if (card.suit == suit) {
					return i;
				}
			}
		} else if (suit > 0) {
			int smallest = hand.get(0).num;
			int index = 0;
			for (int i = 1; i < hand.size(); i++) {
				Card card = hand.get(i);
				if (card.num < smallest) {
					smallest = card.num;
					index = i;
				}
			}
			//System.out.println(hand.get(smallest).suite + " " + hand.get(smallest).rank);
			return index;
		} else {
			int largest = hand.get(0).num;
			int index = 0;
			for (int i = 1; i < hand.size(); i++) {
				Card card = hand.get(i);
				if (card.num > largest) {
					largest = card.num;
					index = i;
				}
			}
			return index;
		}
		return -1;
	}
	
	/*public Card playCard(int suite) {
		if (hasSuite(suite)) {
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).suit == suite) {
					return hand.get(i);
				}
			}
		} else {
			int smallest = hand.get(0).num;
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).num < smallest) {
					smallest = hand.get(i).num;
				}
			}
			return hand.get(smallest);
		}
		return null;
	}*/
}
