package boxDodge;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	DodgerMan man;
	ArrayList<Box> boxes = new ArrayList<Box>();
	Random rand = new Random();
	public int score = 0;
	public int power = 3;
	int columns = 5;
	int colwidth = BoxDodge.WIDTH / 5;

	ObjectManager(DodgerMan man) {
		this.man = man;
	}

	void addBox() {
		int col = rand.nextInt(5);
		boxes.add(new Box(col * colwidth, 0, 100, 50));
	}

	void update() {
		for (Box box : boxes) {
			if (box.y > BoxDodge.HEIGHT) {
				box.isActive = false;
			}
			box.update();
		}
		man.update();
		checkCollision();
		purgeObjects();
	}

	void draw(Graphics g) {
		man.draw(g);
		for (int i = 0; i < boxes.size(); i++) {
			boxes.get(i).draw(g);
		}
	}

	void purgeObjects() {
		for (int i = 0; i < boxes.size(); i++) {
			if (boxes.get(i).isActive == false) {
				boxes.remove(i);
				score +=1;
			}
		}
	}

	void checkCollision() {
		for (Box box : boxes) {
			if (man.collisionBox.intersects(box.collisionBox)) {
				man.isActive = false;
			}
		}
	}
	public int getScore() {
		return this.score;
	}
	public void setScore(int score1) {
		this.score = score1;
	}
	public int getPower() {
		return this.power;
	}
	public void setPower(int power1) {
		this.score = power1;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		addBox();
	}
}
