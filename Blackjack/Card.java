import java.util.ArrayList;

public class Card {
	private int num = 2;
	private int suit = 0;
	private int value = 0;
	private String suite;
	private String rank;
	private String file;

	public Card(int s, int n) {
		suit = s;
		num = n;
		suite = suit == 1 ? "diamond" : suit == 2 ? "club" : suit == 3 ? "heart" : "spade";
		rank = num == 11 ? "J" : num == 12 ? "Q" : num == 13 ? "K" : num == 14 ? "A" : Integer.toString(num);
		file = "images\\Playing_card_" + suite + "_" + rank +".jpg";
		value = num == 11 ? 10 : num == 12 ? 10 : num == 13 ? 10 : num == 1 ? 11 : num;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @return the suit
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * @return the suite
	 */
	public String getSuite() {
		return suite;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
}
