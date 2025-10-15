import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Dog {
	private int x, y;
	private int vx, vy;
	private Image img;
	private AffineTransform tx;
	private int newY;
	public Dog() {
		img = getImage("/imgs/Freiza.png"); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); // initialize the location of the image
					// use your variables

		x = 110;
		vy=0;
		y = 450;

	}

	public Dog(String fileName) {
		img = getImage("/imgs/" + fileName); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); // initialize the location of the image
					// use your variables
		
		x = 110;
		vy=0;
		y = 550;
	}

	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		// these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		
		
		y += vy;
		update();
		g2.drawImage(img, tx, null);
		
		if(y==250) {
			vy*=-1;
		}
	}
	
	public void up() {
		y = 450;
		vy = -10;
		
	}
	

	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(2, 2);
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
 
	public void setXVar(int x2) {
		// TODO Auto-generated method stub
		   
			x=x2-45;
			
			
	}
	public void setYVar(int y2) {
		// TODO Auto-generated method stub
		   
		y=y2-45;
		
		
}

}
