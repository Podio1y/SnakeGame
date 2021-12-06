import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class menuPanel extends JPanel implements ActionListener{
	static int screenWidth = 500;
	static int screenHeight = 500; 
	static int unit = 25;
	static int delay = 75;
	int bodyLength = 3;
	Boolean leaveMenu, settings, highscores, running = false;
	char slider;
	Timer t;
	Random r;
	Font menuTitle = new Font("SansSerif", 70, 70);
	//Graphics g = new Graphics();
	
	menuPanel(){
		r = new Random();
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.darkGray);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
	}
	
	public void pauseProgram() {
		
	}
	
	public void highscorePage (){
		//g.drawString("Highscores", 100, 100);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.setFont(menuTitle);
		g.drawString("MAIN MENU", 10, 50);
		
	}
	
	public void gameOver() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent k) {
			
			// WASD keys for direction
			switch(k.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				slider = 'L';
				break;
			case KeyEvent.VK_RIGHT:
				slider = 'R';
				break;
			case KeyEvent.VK_P:
				leaveMenu = true;
				break;
			case KeyEvent.VK_H:
				highscorePage(); // work in progress
				break;
			case KeyEvent.VK_S:
				settings = true; // work in progress
				break;
			}
		}
	}
}
