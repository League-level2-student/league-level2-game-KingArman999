package boxDodge;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int INFORMATION = 3;
	int currentState = MENU;
	Font titleFont = new Font("Arial", Font.BOLD, 70);
	Font enterFont = new Font("Arial", Font.PLAIN, 36);
	Font iFont = new Font("Arial", Font.PLAIN, 30);
	Font informationFont = new Font("Arial", Font.PLAIN, 30);
	Font informationBigFont = new Font("Arial", Font.BOLD, 70);
	Font scoreFont = new Font("Arial", Font.PLAIN, 20);
	Font powerFont = new Font("Arial", Font.PLAIN, 15);
	Font colorFont = new Font("Arial", Font.PLAIN, 29);
	Timer frameDraw;
	DodgerMan dodger = new DodgerMan(250, 700, 50, 50);
	ObjectManager object = new ObjectManager(dodger);
	Timer boxSpawn;
	String colorChoice;

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

	void drawMenuState(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, BoxDodge.WIDTH, BoxDodge.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.DARK_GRAY);
		g.drawString("BOX DODGE", 75, 120);
		g.setFont(enterFont);
		g.drawString("Press ENTER to start", 120, 400);
		g.setFont(iFont);
		g.drawString("Press I for insructions", 150, 550);
		g.setFont(colorFont);
		g.drawString("Press C to choose your color", 110, 650);
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
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, BoxDodge.WIDTH, BoxDodge.HEIGHT);
		g.setFont(enterFont);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Press ENTER to try again", 90, 450);
		g.setFont(titleFont);
		g.setColor(Color.DARK_GRAY);
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
				return;
			}
			if (currentState == INFORMATION) {
				currentState = MENU;
				return;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (dodger.y > 0) {
				dodger.up();
				if (object.score>=45) {
					
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (dodger.x < BoxDodge.WIDTH - dodger.width - 19) {
				dodger.right();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (dodger.x > 0) {
				dodger.left();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (dodger.y < BoxDodge.HEIGHT - dodger.height - 30) {
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
		if (currentState == MENU) {
			if (e.getKeyCode() == KeyEvent.VK_I) {
				currentState = INFORMATION;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			if (currentState == MENU) {
				colorChoice = JOptionPane
						.showInputDialog("Choose a color: Blue, Pink, Orange, Yellow, Green, White, or Gray.");
			}
		}
	}

	void startGame() {
		boxSpawn = new Timer(1000, object);
		boxSpawn.start();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
