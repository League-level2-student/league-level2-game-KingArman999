package boxDodge;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int INFORMATION = 3;
	final int HIGHSCORE = 4;
	int currentState = MENU;
	Font titleFont = new Font("Arial", Font.BOLD, 70);
	Font enterFont = new Font("Arial", Font.PLAIN, 36);
	Font iFont = new Font("Arial", Font.PLAIN, 30);
	Font sFont = new Font("Arial", Font.PLAIN, 30);
	Font informationFont = new Font("Arial", Font.PLAIN, 29);
	Font informationBigFont = new Font("Arial", Font.BOLD, 70);
	Font scoreFont = new Font("Arial", Font.PLAIN, 20);
	Font endScoreFont = new Font("Arial", Font.PLAIN, 40);
	Font powerFont = new Font("Arial", Font.PLAIN, 15);
	Font scoreBoardFont = new Font("Arial", Font.BOLD, 50);
	Font niceFont = new Font("Arial", Font.BOLD, 80);
	Font highScoreFont = new Font("Arial", Font.PLAIN, 30);
	Timer frameDraw;
	DodgerMan dodger = new DodgerMan(250, 700, 50, 50);
	ObjectManager object = new ObjectManager(dodger);
	Timer boxSpawn;
	String colorChoice;
	ArrayList<Integer> scores = new ArrayList<Integer>();
	ArrayList<String> players = new ArrayList<String>();

	GamePanel() {
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		} else if (currentState == INFORMATION) {
			drawInformationState(g);
		} else if (currentState == HIGHSCORE) {
			drawHighScoreState(g);
		}
	}

	void updateMenuState() {

	}

	void updateInformationState() {

	}

	void updateGameState() {
		object.update();
		if (dodger.isActive == false) {
			currentState = END;
		}
	}

	void updateEndState() {

	}

	void updateHigeScoreState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, BoxDodge.WIDTH, BoxDodge.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.DARK_GRAY);
		g.drawString("BOX DODGE", 75, 150);
		g.setFont(enterFont);
		g.drawString("Press ENTER to start", 120, 400);
		g.setFont(iFont);
		g.drawString("Press I for insructions", 150, 525);
		g.setFont(sFont);
		g.drawString("Press S for the high scores", 120, 625);
	}

	void drawHighScoreState(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, BoxDodge.WIDTH, BoxDodge.HEIGHT);
		g.setFont(scoreBoardFont);
		g.setColor(Color.DARK_GRAY);
		g.drawString("SCOREBOARD", 100, 150);
		g.setFont(highScoreFont);
		for (int i = 0; i < scores.size(); i++) {
			String name;
			if (players.get(i).length() > 8) {
				name = players.get(i).substring(0, 8);
			}else if (players.get(i).length() == 0) {
				name = "_______";
			}
			else {
				name = players.get(i);
			}
			g.drawString((i + 1) + ". " + name, 80, 250 + i * 50);
			g.drawString(String.valueOf(scores.get(i)), (int) (BoxDodge.WIDTH * 0.75), 250 + i * 50);
			if (i == 4) {
				break;
			}
		}
		g.setFont(enterFont);
		g.drawString("Press ENTER to go back", 90, 600);
	}

	void drawInformationState(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, BoxDodge.WIDTH, BoxDodge.HEIGHT);
		g.setColor(Color.DARK_GRAY);
		g.setFont(informationBigFont);
		g.drawString("INFORMATION", 40, 100);
		g.setFont(informationFont);
		g.drawString("Use the arrow keys to move foward, back,", 30, 200);
		g.drawString("left and right. Navigate your dodger through", 30, 250);
		g.drawString("the falling boxes. If you are ever in a pinch,", 30, 300);
		g.drawString("press SPACE to clear all the boxes off the", 30, 350);
		g.drawString("screen. Use this power wisely though, as", 30, 400);
		g.drawString("you only have 3 uses.", 30, 450);
		g.setFont(enterFont);
		g.drawString("Press ENTER to go back", 90, 600);
		g.drawString("to the menu", 185, 650);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, BoxDodge.WIDTH, BoxDodge.HEIGHT);
		object.draw(g);
		g.setColor(Color.GREEN);
		g.setFont(scoreFont);
		g.drawString("Score: " + object.getScore(), 50, 100);
		g.setFont(powerFont);
		g.drawString("Powers left: " + object.getPower(), 450, 100);
		if (object.score == 69) {
			g.setColor(Color.ORANGE);
			g.fillRect(0, 0, BoxDodge.WIDTH, BoxDodge.HEIGHT);
			g.setColor(Color.RED);
			g.setFont(niceFont);
			g.drawString("NICE", 100, 650);
			g.setColor(Color.YELLOW);
			g.drawString("NICE", 300, 500);
			g.setColor(Color.GREEN);
			g.drawString("NICE", 100, 350);
			g.setColor(Color.BLUE);
			g.drawString("NICE", 300, 200);
		}
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, BoxDodge.WIDTH, BoxDodge.HEIGHT);
		g.setColor(Color.DARK_GRAY);
		g.setFont(endScoreFont);
		g.drawString("Your score was " + object.score, 130, 400);
		g.setFont(enterFont);
		g.drawString("Press ENTER to try again", 90, 600);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 75, 150);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		} else if (currentState == INFORMATION) {
			updateInformationState();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU) {
				currentState = GAME;
				dodger = new DodgerMan(250, 700, 50, 50);
				object = new ObjectManager(dodger);
				startGame();
				return;
			}

			if (currentState == END) {
				currentState = MENU;
				object.playerName = JOptionPane.showInputDialog("Enter your name");
				addScore(object.score, object.playerName);
				return;
			}
			if (currentState == INFORMATION) {
				currentState = MENU;
				return;
			}
			if (currentState == HIGHSCORE) {
				currentState = MENU;
				return;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (dodger.y > 0) {
				dodger.up();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (dodger.x < BoxDodge.WIDTH - dodger.width - 30) {
				dodger.right();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (dodger.x > 0) {
				dodger.left();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (dodger.y < BoxDodge.HEIGHT - dodger.height - 45) {
				dodger.down();
			}
		}

		if (currentState == END) {
			dodger.isActive = false;
			DodgerMan dodger = new DodgerMan(250, 700, 50, 50);
			ObjectManager object = new ObjectManager(dodger);
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (object.power >= 1) {
				object.score += object.boxes.size();
				object.boxes.clear();
				object.power -= 1;
			}
		}

		if (currentState == GAME) {
			if (object.score >= 25) {
				startFasterGame();
			}
			if (object.score >= 60) {
				startFastestGame();
			}
		}

		if (currentState == MENU) {
			if (e.getKeyCode() == KeyEvent.VK_S) {
				currentState = HIGHSCORE;
			}
			if (e.getKeyCode() == KeyEvent.VK_I) {
				currentState = INFORMATION;
			}
		}
	}

	public void addScore(int score, String playerName) {

		if (scores.isEmpty()) {
			scores.add(score);
			players.add(playerName);
		} else {
			for (int i = 0; i < scores.size(); i++) {
				if (score > scores.get(i)) {
					scores.add(i, score);
					players.add(i, playerName);
					return;
				}
			}
			scores.add(score);
			players.add(playerName);
		}
	}

	void startGame() {
		boxSpawn = new Timer(800, object);
		boxSpawn.start();
	}

	void startFasterGame() {
		boxSpawn.setDelay(600);
		Box.boxSpeed = 4;
		DodgerMan.dodgerSpeed = 25;
	}

	void startFastestGame() {
		boxSpawn.setDelay(400);
		Box.boxSpeed = 5;
		DodgerMan.dodgerSpeed = 30;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
