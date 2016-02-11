

import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
	Button hit = new Button("Hit");
	Button stand = new Button("Stand");
	BorderPane border = new BorderPane();
	VBox left = new VBox();
	HBox top = new HBox();
	HBox bot = new HBox();
	VBox right = new VBox();
	Deck deck = new Deck(true);
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> dealer = new ArrayList<Card>();
	Rectangle rect = new Rectangle(0,0,1080,250);
	StackPane pane;
	
	public void startGame() {
		Card card;
		for (int i = 0; i < 2; i++) {
			card = deck.getCard();
			hand.add(card);
			bot.getChildren().add(new ImageView(new Image(card.getFile())));	
		}
		for (int i = 0; i < 2; i++) {
			card = deck.getCard();
			dealer.add(card);
			top.getChildren().add(new ImageView(new Image(card.getFile())));	
		}	
		pane.getChildren().add(rect);
	}
	
	public void addButtons() {
	    right.setPadding(new Insets(100, 700, 15, 12));
	    right.setSpacing(50);
		hit.setPrefSize(100, 50);
		stand.setPrefSize(100, 50);
		right.getChildren().add(hit);
		right.getChildren().add(stand);
				
		hit.setOnMouseClicked(e -> hitMe());
		stand.setOnMouseClicked(e -> dealerPlay());
	}
	
	public void hitMe() {
		Card card = deck.getCard();
		hand.add(card);
		bot.getChildren().add(new ImageView(new Image(card.getFile())));
		int handValue = totalValue(hand);
		if (handValue > 21) {
			Platform.runLater(new Runnable() {
				public void run() {
					JOptionPane.showConfirmDialog(null, "Bust");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					reset();
				}
			});			
		} else if (handValue == 21) {
			Platform.runLater(new Runnable() {
				public void run() {
					JOptionPane.showConfirmDialog(null, "Win");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					reset();
				}
			});
		}
	}


	public void dealerPlay() {
		pane.getChildren().remove(rect);	
		Platform.runLater(new Runnable() {
			public void run() {
				while (totalValue(dealer) < totalValue(hand)) {
					Platform.runLater(new Runnable() {
						public void run() {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {}
						}
					});
					Card card = deck.getCard();
					dealer.add(card);
					top.getChildren().add(new ImageView(new Image(card.getFile())));
				}
				getWinner();
			}
		});
	}
	
	public void getWinner() {
		if (totalValue(dealer) > 21) {
			Platform.runLater(new Runnable() {
				public void run() {
					JOptionPane.showConfirmDialog(null, "Dealer bust. You win!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					reset();
				}
			});
		} else if (totalValue(dealer) == 21) {
			Platform.runLater(new Runnable() {
				public void run() {
					JOptionPane.showConfirmDialog(null, "Dealer win!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					reset();
				}
			});
		} else if (totalValue(dealer) > totalValue(hand)) {
			Platform.runLater(new Runnable() {
				public void run() {
					JOptionPane.showConfirmDialog(null, "You lose.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					reset();
				}
			});
		} else if (totalValue(dealer) < totalValue(hand)) {
			Platform.runLater(new Runnable() {
				public void run() {
					JOptionPane.showConfirmDialog(null, "You win!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					reset();
				}
			});
		}
	}
	
	public int totalValue(ArrayList<Card> player) {
		int sum = 0;
		boolean ace = false;
		
		for (int i = 0; i < player.size(); i++) {
			if (player.get(i).getValue() == 11) {
				ace = true;
			}
		}
		System.out.println(ace);
		for (int i = 0; i < player.size(); i++) {
			System.out.println(player.get(i).getValue());
			sum += player.get(i).getValue();
		}
		
		if (sum <= 21) {
			return sum;
		} else if (sum > 21 && ace) {
			return sum - 10;
		} else {
			return sum;
		}
		
	}
	
	private void reset() {
		top.getChildren().clear();
		bot.getChildren().clear();
		hand.clear();
		dealer.clear();
		deck = new Deck(true);
		startGame();
	}
	
	@Override
	public void start(Stage primaryStage) {
		pane = new StackPane(border);
		pane.setAlignment(Pos.TOP_LEFT);
		StackPane.setMargin(rect, new Insets(0,0,0,200));
		border.setTop(top);	
		border.setLeft(left);
		border.setBottom(bot);	
		border.setRight(right);
		
		startGame();
		addButtons();
			
		Scene scene = new Scene(pane, 1280, 720);
		primaryStage.setTitle("Blackjack © 2015 Da Rong Ma");
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
