import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Foreground2 {
	private int x, y;
	private int vx, vy;
	private Image img;
	private AffineTransform tx;

	public Foreground2() {
		img = getImage("/imgs/BetterForeground2.png"); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); // initialize the location of the image
					// use your variables

		x = -200;
		y = 275;

	}

	public Foreground2(String fileName) {
		img = getImage("/imgs/" + fileName); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); // initialize the location of the image
					// use your variables
	}

	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}

	public void paint(Graphics g) {
		// these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		if (x < 0 || x > 780) {

			vx = -vx;
		}
		if (y < 0 || y > 450) {

			vy = -vy;
		}
		x += vx;
		y += vy;
		update();
		g2.drawImage(img, tx, null);

	}

	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(4, 3);
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.5, .5);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Tree.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
