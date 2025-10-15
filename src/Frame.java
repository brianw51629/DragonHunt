import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	Duck d = new Duck();
	Tree t = new Tree();
	Dog Do = new Dog();
	Foreground fo = new Foreground();
	Bush b = new Bush();
	Background bckg = new Background();
	Duck d2 = new Duck();
	Duck d3 = new Duck();
	Kamehameha k = new Kamehameha();
	Kamehameha2 k2 = new Kamehameha2();
	Kamehameha3 k3 = new Kamehameha3();
	Dog Do2 = new Dog("FreizaWGoku.png");
	Music hit = new Music("hit.wav",false);
	Music lose = new Music("lose.wav",false);
	Music miss = new Music("miss.wav",false);
	Music win = new Music("win.wav",false);
	Music background = new Music("background.wav", true);
	int shots = 3;
	int score = 0;
	static JLabel l;
	static JFrame fr;
	int wave = 1;
	boolean gameOver=false;
	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		bckg.paint(g);
		d.paint(g);
		d2.paint(g);
		Do.paint(g);
		Do2.paint(g);
		fo.paint(g);
		t.paint(g);
		b.paint(g);
		k.paint(g);
		k2.paint(g);
		k3.paint(g);
		
		
		
		Font plainFont = new Font("SanSerif", Font.PLAIN, 40);
		Font gameEndFont = new Font("SansSerif", Font.PLAIN,60);
		Font restartFont = new Font("SansSerif", Font.PLAIN,25);
		g.setFont(plainFont);
		g.drawString("Score: "+score, 715, 535);
		g.drawString("Ki Left:", 0, 535);
		g.drawString("Wave: "+wave, 350, 100);
		if(shots==0) {
			
			 
	       g.setColor(Color.black);
	       g.fillRect(0,0,900,600);
	       g.setColor(Color.white);
	       g.setFont(gameEndFont);
	       g.drawString("FAILURE", 250, 300);
	       g.setFont(restartFont);
	       g.drawString("Space to Restart", 250, 450);
	       Do.setXVar(550);
	       Do.setYVar(200);
	       Do.paint(g);
	        gameOver=true;
		}
		if(score==9) {
			g.setColor(Color.ORANGE);
		       g.fillRect(0,0,900,600);
		       g.setColor(Color.blue);
		       g.setFont(gameEndFont);
		       g.drawString("YOU WIN", 250, 300);
		       g.setFont(restartFont);
		       g.drawString("Space to Restart", 250, 450);
		       Do2.setXVar(550);
		       Do2.setYVar(200);
		       Do2.paint(g);
		       gameOver=true;
		}
		
	}

	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}

	public Frame() {
		//background.play();
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(900, 600));
		f.setBackground(new Color(0, 0, 0));
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1, 2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		if(d.getVX()==d2.getVX()) {
			d.setVX(d.getVX()*-1);
		}
		BufferedImage cursorImg;
		try {
					cursorImg =  ImageIO.read(new File("Kamehameha.png"));
					Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
						    cursorImg, new Point(0, 0), "blank cursor");
					f.getContentPane().setCursor(blankCursor);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		
	}

	
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
			
		
			
			if(gameOver==false) {
			
			if(d.hit(arg0)==true) {
				score++;
				Do2.up();
				
				if(score!=9) {
					hit.play();
				}
				
			
				
			}
			
			if(d2.hit(arg0)==true) {
				score++;
				Do2.up();
				
				
				if(score!=9) {
					hit.play();
				}
				
				
				
				
			}
		
			
			if((d.hit(arg0)==false)&&(d2.hit(arg0)==false)) {
				Do.setXVar(d.getX());
				
				
				
				Do.up();
				shots--;
				if(shots==2) {
					k3.update();
					miss.play();
				}
				if(shots==1) {
					k2.update();
					miss.play();
				}
				if(shots==0) {
					k.update();
					lose.play(); 
			      
				}
				
			}
			if(score==9) {
					win.play();
					
				}
			
			if((score==3)||(score==6)||(score==9)) {
				wave++;
				
				d.setNewV(wave);
				d2.setNewV(wave);
			}
			
			
			}	
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg32) {
		// TODO Auto-generated method stub
		
		if(gameOver) {
			shots=3;
			wave=1;
			score=0;
			d.setNewV(0);
			d2.setNewV(0);
			d.reset();
			d2.reset();
			k.reset();
			k2.reset();
			k3.reset();
			gameOver=false;
			
			
			System.out.println("reset");
		}
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
