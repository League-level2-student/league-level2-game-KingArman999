package boxDodge;

import java.awt.Color;
import java.awt.Graphics;

public class Box extends GameObject {

	Box(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
	}

	void update() {
		y += speed;
		super.update();
	}

	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
}
