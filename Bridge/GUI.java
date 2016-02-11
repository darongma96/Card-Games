package FinalFinal;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
	private int score1, score2;
	private Label ourScore = new Label("Our score: " + score1);
	private Label theirScore = new Label("Their score: " + score2);
	private ImageView[] imageView = new ImageView[52];
	private boolean ourTurn = true;
	private int startSuit, round;
	private int winner = 1, leftOver = -1;
	private Card p1Card, p2Card, p3Card, p4Card;
	
	private void cardImages(ArrayList<Card> player, AnchorPane pane, Stage stage, boolean isClickable) {
		int start = 0, end = 13;
		if (player == Main.p2.hand) {
			start = 13;
			end = 26;
		} else if (player == Main.p3.hand) {
			start = 26;
			end = 39;
		} else if (player == Main.p4.hand) {
			start = 39;
			end = 52;
		}
		for (int i = start; i < end; i++) {
			int index = i;
			Card c = player.get(i - start);
			String suit = c.suite;
			String rank = c.rank;
			Image image = new Image(c.file);
			imageView[i] = new ImageView(image);
			imageView[i].setFitHeight(175);
			imageView[i].setFitWidth(145);
			setAnchor(imageView[i], player, i - start);
			pane.getChildren().add(imageView[i]);
			
			if(isClickable) {
				imageView[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						if (ourTurn && (c.suit == startSuit || !hasSuit() || round == 0)) {
							ourTurn = false;
							System.out.println("p1 plays " + suit + " " + rank);
							
							Card bestCard = null;
							p1Card = c;
							
							pane.getChildren().remove(imageView[index]);
							Main.p1.remove(c);
							setAnchor(imageView[index], 1);
							pane.getChildren().add(imageView[index]);

							if (leftOver == 0) {
								//System.out.println("leftover 0");
								bestCard = Card.getWinner(startSuit, p1Card, p2Card, p3Card, p4Card);
								addScore(bestCard, p1Card, p2Card, p3Card, p4Card);
								removeCards(pane);
							} else if (leftOver == 1) {
								//System.out.println("leftover 1");
								int index = Main.p2.playCardIndex(startSuit);
								p2Card = Main.p2.hand.get(index);
								ImageView iv = (ImageView) pane.getChildren().get(index + Main.p1.size());
								pane.getChildren().remove(iv);
								Main.p2.hand.remove(index);
								setAnchor(iv, 2);
								pane.getChildren().add(iv);
								System.out.println("p2 plays " + p2Card.suite + " " + p2Card.rank);
								
								bestCard = Card.getWinner(startSuit, p1Card, p2Card, p3Card, p4Card);
								addScore(bestCard, p1Card, p2Card, p3Card, p4Card);
								removeCards(pane);
							} else if (leftOver == 2) {
								//System.out.println("leftover 2");
								int index = Main.p2.playCardIndex(startSuit);
								p2Card = Main.p2.hand.get(index);
								ImageView iv = (ImageView) pane.getChildren().get(index + Main.p1.size());
								pane.getChildren().remove(iv);
								Main.p2.hand.remove(index);
								setAnchor(iv, 2);
								pane.getChildren().add(iv);
								System.out.println("p2 plays " + p2Card.suite + " " + p2Card.rank);

								index = Main.p3.playCardIndex(startSuit);
								p3Card = Main.p3.hand.get(index);
								iv = (ImageView) pane.getChildren().get(index + 2 * Main.p1.size());
								pane.getChildren().remove(iv);
								Main.p3.hand.remove(index);
								setAnchor(iv, 3);
								pane.getChildren().add(iv);
								System.out.println("p3 plays " + p3Card.suite + " " + p3Card.rank);
								
								bestCard = Card.getWinner(startSuit, p1Card, p2Card, p3Card, p4Card);
								addScore(bestCard, p1Card, p2Card, p3Card, p4Card);
								removeCards(pane);
							}

							if (checkRound(stage)) {
								return;
							}
							/*if (round == 13) {
								stage.close();
								if (score1 > score2) {
									System.out.println("Your team wins " + score1 + "-" + score2);
									JOptionPane.showMessageDialog(null, "Your team is victorious!");
								} else {
									System.out.println("Your team loses " + score1 + "-" + score2);
									JOptionPane.showMessageDialog(null, "Your team is defeated!");
								}
								if (JOptionPane.showConfirmDialog(null, "Play again?", "Would you like to play again?", JOptionPane.YES_NO_OPTION) == 0) {
									// implementation for restarting gui
									return;
								} else {
									return;
								}
							}*/ /*else if (bestCard == p1Card) {
       REMOVE THIS LINE			System.out.println("bestCard == p1Card return;");
								return;
							}*/
							
							if (winner == 1) {
								if (bestCard == p1Card) {
									System.out.println("triggered");
									return;
								}
								startSuit = p1Card.suit;
								int index = Main.p2.playCardIndex(startSuit);
								p2Card = Main.p2.hand.get(index);
								ImageView iv = (ImageView) pane.getChildren().get(index + Main.p1.size());
								pane.getChildren().remove(iv);
								Main.p2.hand.remove(index);
								setAnchor(iv, 2);
								pane.getChildren().add(iv);
								System.out.println("p2 plays " + p2Card.suite + " " + p2Card.rank);

								index = Main.p3.playCardIndex(startSuit);
								p3Card = Main.p3.hand.get(index);
								iv = (ImageView) pane.getChildren().get(index + 2 * Main.p1.size());
								pane.getChildren().remove(iv);
								Main.p3.hand.remove(index);
								setAnchor(iv, 3);
								pane.getChildren().add(iv);
								System.out.println("p3 plays " + p3Card.suite + " " + p3Card.rank);

								index = Main.p4.playCardIndex(startSuit);
								p4Card = Main.p4.hand.get(index);
								iv = (ImageView) pane.getChildren().get(index + 3 * Main.p1.size());
								pane.getChildren().remove(iv);
								Main.p4.hand.remove(index);
								setAnchor(iv, 4);
								pane.getChildren().add(iv);
								System.out.println("p4 plays " + p4Card.suite + " " + p4Card.rank);
								
								bestCard = Card.getWinner(startSuit, p1Card, p2Card, p3Card, p4Card);
								//possible to do score1++ to change the gui display instead of setText()?
								addScore(bestCard, p1Card, p2Card, p3Card, p4Card);
								
								/*Platform.runLater(
										new Thread(){
											public void run(){
											try {System.out.println("trying to sleep");
												Thread.sleep(3000);
											} catch (InterruptedException e1) {
												e1.printStackTrace();
											}}
										}
								);*/
								/*try {
									stage.hide();
									stage.show();
									System.out.println("trying to sleep");
									Thread.sleep(2000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}*/
								
								removeCards(pane);
								if (checkRound(stage))
									return;
							}
							if (winner == 2) {
								int index = Main.p2.playCardIndex(startSuit);
								p2Card = Main.p2.hand.get(index);
								ImageView iv = (ImageView) pane.getChildren().get(index + Main.p1.size());
								pane.getChildren().remove(iv);
								startSuit = p2Card.suit;
								//System.out.println("start suit" + startSuit);
								Main.p2.hand.remove(index);
								setAnchor(iv, 2);
								pane.getChildren().add(iv);
								System.out.println("p2 plays " + p2Card.suite + " " + p2Card.rank);

								index = Main.p3.playCardIndex(startSuit);
								p3Card = Main.p3.hand.get(index);
								iv = (ImageView) pane.getChildren().get(index + 2 * Main.p1.size() - 1);
								pane.getChildren().remove(iv);
								Main.p3.hand.remove(index);
								setAnchor(iv, 3);
								pane.getChildren().add(iv);
								System.out.println("p3 plays " + p3Card.suite + " " + p3Card.rank);

								index = Main.p4.playCardIndex(startSuit);
								p4Card = Main.p4.hand.get(index);
								iv = (ImageView) pane.getChildren().get(index + 3 * Main.p1.size() - 2);
								pane.getChildren().remove(iv);
								Main.p4.hand.remove(index);
								setAnchor(iv, 4);
								pane.getChildren().add(iv);
								System.out.println("p4 plays " + p4Card.suite + " " + p4Card.rank);
								
								leftOver = 0;
								ourTurn = true;
							} else if (winner == 3) {
								int index = Main.p3.playCardIndex(startSuit);
								p3Card = Main.p3.hand.get(index);
								ImageView iv = (ImageView) pane.getChildren().get(index + 2 * Main.p1.size());
								pane.getChildren().remove(iv);
								startSuit = p3Card.suit;
								//System.out.println("start suit" + startSuit);
								Main.p3.hand.remove(index);
								setAnchor(iv, 3);
								pane.getChildren().add(iv);
								System.out.println("p3 plays " + p3Card.suite + " " + p3Card.rank);

								index = Main.p4.playCardIndex(startSuit);
								p4Card = Main.p4.hand.get(index);
								iv = (ImageView) pane.getChildren().get(index + 3 * Main.p1.size() - 1);
								pane.getChildren().remove(iv);
								Main.p4.hand.remove(index);
								setAnchor(iv, 4);
								pane.getChildren().add(iv);
								System.out.println("p4 plays " + p4Card.suite + " " + p4Card.rank);
								
								leftOver = 1;
								ourTurn = true;
							} else if (winner == 4) {
								int index = Main.p4.playCardIndex(startSuit);
								p4Card = Main.p4.hand.get(index);
								ImageView iv = (ImageView) pane.getChildren().get(index + 3 * Main.p1.size());
								pane.getChildren().remove(iv);
								startSuit = p4Card.suit;
								//System.out.println("start suit" + startSuit);
								Main.p4.hand.remove(index);
								setAnchor(iv, 4);
								pane.getChildren().add(iv);
								System.out.println("p4 plays " + p4Card.suite + " " + p4Card.rank);
								
								leftOver = 2;
								ourTurn = true;
							}

						}
					}
				});
			}
		}
	}
	private void removeCards(AnchorPane pane) {
		int size = pane.getChildren().size();
		System.out.println("attempting to removing cards");
		pane.getChildren().remove(size - 1);
		pane.getChildren().remove(size - 2);
		pane.getChildren().remove(size - 3);
		pane.getChildren().remove(size - 4);
		System.out.println("cards removed");
	}
	private boolean hasSuit() {
		boolean hasSuit = false;
		for (int i = 0; i < Main.p1.size(); i++) {
			if (Main.p1.get(i).suit == startSuit) {
				hasSuit = true;
				break;
			}
		}
		return hasSuit;
	}
	private boolean checkRound(Stage stage) {
		if (round == 13) {
			stage.close();
			if (score1 > score2) {
				System.out.println("Your team wins " + score1 + "-" + score2);
				JOptionPane.showMessageDialog(null, "Your team is victorious!");
			} else {
				System.out.println("Your team loses " + score1 + "-" + score2);
				JOptionPane.showMessageDialog(null, "Your team is defeated!");
			}
			return true;
		}
		return false;
	}
	private void addScore(Card bestCard, Card p1, Card p2, Card p3, Card p4) {
		//possible to do score1++ to change the gui display instead of setText()?
		if (bestCard == p1) {
			System.out.println("p1 wins");
			ourScore.setText("Our score: " + ++score1);
			winner = 1;
			leftOver = -1;
			ourTurn = true;
		} else if (bestCard == p2) {
			System.out.println("p2 wins");
			theirScore.setText("Their score: " + ++score2);
			winner = 2;
		} else if (bestCard == p3) {
			System.out.println("p3 wins");
			ourScore.setText("Our score: " + ++score1);
			winner = 3;									
		} else {
			System.out.println("p4 wins");
			theirScore.setText("Their score: " + ++score2);
			winner = 4;
		}
		round++;
		startSuit = 0;
	}
	private void setAnchor(ImageView imageView, ArrayList<Card> player, int i) {
		if (player == Main.p1) {
			AnchorPane.setLeftAnchor(imageView,i * 50.0 + 300);
			AnchorPane.setBottomAnchor(imageView, 0.0);
		} else if (player == Main.p2.hand) {
			AnchorPane.setTopAnchor(imageView, i * 30.0 + 100);
			AnchorPane.setLeftAnchor(imageView, 0.0);
		} else if (player == Main.p3.hand) {
			AnchorPane.setRightAnchor(imageView,i * 50.0 + 300);
			AnchorPane.setTopAnchor(imageView, 0.0);
		} else {
			AnchorPane.setTopAnchor(imageView, i * 30.0 + 100);
			AnchorPane.setRightAnchor(imageView, 0.0);
		}
	}
	private void setAnchor(ImageView imageView, int i) {
		if (i == 1) {
			AnchorPane.setLeftAnchor(imageView, 575.0);
			AnchorPane.setBottomAnchor(imageView, 190.0);
		} else if (i == 2) {
			AnchorPane.setTopAnchor(imageView, 300.0);
			AnchorPane.setLeftAnchor(imageView, 425.0);
		} else if (i == 3) {
			AnchorPane.setRightAnchor(imageView, 575.0);
			AnchorPane.setTopAnchor(imageView, 190.0);
		} else {
			AnchorPane.setTopAnchor(imageView, 300.0);
			AnchorPane.setRightAnchor(imageView, 425.0);
		}
 	}
	@Override
	public void start(Stage primaryStage) {
		AnchorPane anchorPane = new AnchorPane();
		Rectangle rect1 = new Rectangle(0,100,137,527);
		Rectangle rect2 = new Rectangle(248,0,737,167);
		Rectangle rect3 = new Rectangle(1153,100,139,526);
		
		Color blue = Color.BLUE;
		Color red = Color.SKYBLUE;
		Color yellow = Color.LIGHTBLUE;
		
		cardImages(Main.p1, anchorPane, primaryStage, true);
		cardImages(Main.p2.hand, anchorPane, primaryStage, false);
		cardImages(Main.p3.hand, anchorPane, primaryStage, false);
		cardImages(Main.p4.hand, anchorPane, primaryStage, false);

		rect1.setFill(blue);
		rect2.setFill(red);
		rect3.setFill(yellow);
		
		//anchorPane.getChildren().add(rect1);
		//anchorPane.getChildren().add(rect2);
		//anchorPane.getChildren().add(rect3);
		
		ourScore.setFont(new Font("Verdana",24));
		theirScore.setFont(new Font("Verdana",24));
		AnchorPane.setLeftAnchor(theirScore, 1100.0);
		anchorPane.getChildren().add(ourScore);
		anchorPane.getChildren().add(theirScore);

		Scene scene = new Scene(anchorPane, 1280, 720);
		primaryStage.setTitle("Bridge © 2015 Da Rong Ma");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
