package boxDodge;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont = new Font("Arial", Font.BOLD, 70);
	Font enterFont = new Font("Arial", Font.PLAIN, 36);
	Font spaceFont = new Font("Arial", Font.PLAIN, 30);
	Timer frameDraw;
	DodgerMan dodger = new DodgerMan(250, 700, 50, 50);
	ObjectManager object = new ObjectManager(dodger);
	Timer boxSpawn;
	
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
		}
	}

	void updateMenuState() {

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
		g.setColor(Color.darkGray);
		g.drawString("BOX DODGE", 25, 120);
		g.setFont(enterFont);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Press ENTER to start", 70, 450);
		g.setFont(spaceFont);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Press SPACE for insructions", 50, 600);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, BoxDodge.WIDTH, BoxDodge.HEIGHT);
		object.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, BoxDodge.WIDTH, BoxDodge.HEIGHT);
		g.setFont(enterFont);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Press ENTER to try again", 40, 450);
		g.setFont(titleFont);
		g.setColor(Color.DARK_GRAY);
		g.drawString("GAME OVER", 25, 150);
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
			if (currentState == GAME) {
				currentState = END;
				boxSpawn.stop();
				return;
			}
			if (currentState == END) {
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
