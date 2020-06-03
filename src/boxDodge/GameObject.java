package boxDodge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean isActive;
	Rectangle collisionBox;
	
	GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isActive = true;
		collisionBox = new Rectangle(x, y, width, height);
	}

	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

}