package boxDodge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class DodgerMan extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public static int dodgerSpeed = 20;
	Random random = new Random();

	DodgerMan(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		int randImage = random.nextInt(1);
		if (needImage) {
			if (randImage == 0) {
				loadImage("Peter.png");
			} else if (randImage == 1) {
				loadImage("MaskPeter.png");
			}
		}
	}

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}

	public void right() {
		x += dodgerSpeed;
	}

	public void left() {
		x -= dodgerSpeed;
	}

	public void up() {
		y -= dodgerSpeed;
	}

	public void down() {
		y += dodgerSpeed;
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
}