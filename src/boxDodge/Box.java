package boxDodge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class Box extends GameObject {
	public BufferedImage image;
	public static BufferedImage image2;
	public static BufferedImage image1;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public static int boxSpeed = 3;
	Random random = new Random();

	Box(int x, int y, int width, int height) {
		super(x, y, width, height);
		int randImage = random.nextInt(2);
		if (needImage) {
			loadImages();
		}
		if (randImage == 0) {
			image= image1;
		} else if (randImage == 1) {
			image = image2;
		}
	}

	void update() {
		y += boxSpeed;
		super.update();
	}

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		}
	}

	void loadImages() {
		if (needImage) {
			try {
				image1 = ImageIO.read(this.getClass().getResourceAsStream("Box.png"));
				image2 = ImageIO.read(this.getClass().getResourceAsStream("Box2.png"));
				gotImage = true;
			} catch (Exception e) {
			}
			needImage = false;
		}
	}
}
