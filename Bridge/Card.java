package FinalFinal;

import java.util.ArrayList;

public class Card {
	int num = 2;
	int suit = 0;
	String suite;
	String rank;
	String file;

	Card(int s, int n) {
		suit = s;
		num = n;
		suite = suit == 1 ? "diamond" : suit == 2 ? "club" : suit == 3 ? "heart" : "spade";
		rank = num == 11 ? "J" : num == 12 ? "Q" : num == 13 ? "K" : num == 14 ? "A" : Integer.toString(num);
		file = "images\\Playing_card_" + suite + "_" + rank +".jpg";
	}

	public static Card getWinner(int startSuit, Card p1Card, Card p2Card, Card p3Card, Card p4Card) {
		ArrayList<Card> al = new ArrayList<Card>(4);
		al.add(p1Card);
		al.add(p2Card);
		al.add(p3Card);
		al.add(p4Card);
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i).suit != startSuit) {
				//System.out.println("removed " + al.get(i).suite + " " + al.get(i).rank);
				al.remove(i--);
			}
		}
		
		Card largestCard = al.get(0);
		for (int i = 1; i < al.size(); i++) {
			Card c = al.get(i);
			if (c.num > largestCard.num) {
				largestCard = c;
			}
		}
		return largestCard;
	}
}
