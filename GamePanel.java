import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
	
	// Planning on implementing settings page where game can be configured and saved in files
	static final int screenWidth = 500;
	static final int screenHeight = 500; 
	static final int unit = 25;
	static final int gameUnits = (screenWidth*screenHeight)/unit;
	static final int delay = 75;
	static int x[] = new int[gameUnits];
	int y[] = new int[gameUnits];
	int bodyLength = 3;
	final int bodyLengthConst = 3;
	int points = 0;
	int totalApples, aX, aY; // Apple variables (points, apple x coordinate, apple y coordinate)
	char direction = 'R';
	Boolean running = false;
	Timer t;
	Random r;
	Font pointFont = new Font ("SansSerif", 10, 12);
	Font menuTitle = new Font("SansSerif", 70, 70);
	boolean menuV = true;
	boolean highscoresV = false;
	boolean settingsV = false;
	boolean gameOn = false;
	
	GamePanel(){
		r = new Random();
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.darkGray);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		//mainMenu(); // 
		startG();
	}
	
	public void pauseProgram() {
		
	}
	
	public void mainMenu() {
		menuV = true;
		Timer t = new Timer (delay, this);
		t.start();

		cleanSnake(1);
		cleanSnake(2);
		
		bodyLength = 3;
	}
	
	public void cleanSnake(int num) {
		for (int i = bodyLength - 1 ; i > 2 ; i--) {
			//x[] = ArrayUtils.removeElement(x[], i);
		}
	}
	
	public void highscores() {
		highscoresV = true;
	}
	
	public void settings() {
		settingsV = true;
	}
	
	public void startG() {
		spawnApple();
		randomStart();
		running = true;
	}
	
	public void randomStart() { // Work in progress
		
		// random number to determine the starting direction
		/*int directionNum = r.nextInt(4);
		
		if (directionNum == 1) {
			direction = 'R';
		}
		else if(directionNum == 2) {
			direction = 'L';
		}
		else if(directionNum == 3) {
			direction = 'U';
		}
		else if(directionNum == 4) {
			direction = 'D';
		}
		
		x[0] = (int)(screenWidth/(unit*2))*unit;
		y[0] = (int)(screenHeight/(unit*2))*unit;*/
	}
	
	public void spawnApple() {
		aX = r.nextInt((int)(screenWidth/unit))*unit; // random x coordinate for apple
		aY = r.nextInt((int)(screenHeight/unit))*unit; // random y coordinate for apple
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		
		if (running == true) {
			// Grid lines
			for (int i = 0 ; i < screenWidth/unit ; i++) {
				
				// Color
				g.setColor(Color.gray);
				
				// Vertical grid lines
				g.drawLine(i*unit, 0, i*unit, screenHeight);
				
				// Horizontal grid lines
				if (i >= (screenWidth/unit)) {
					g.drawLine(0, i*unit, screenWidth, i*unit+5);
				}
				else {
					g.drawLine(0, i*unit, screenWidth, i*unit);
				}
			}
			
			// Apple
			g.setColor(Color.red);
			g.fillOval(aX+1, aY+1, unit-1, unit-1); // +1 and -1 are just to center it better
			
			//Snake
			for(int i = 0 ; i < bodyLength ; i++) {
				if (i == 0) {
					// Snake head color
					g.setColor(new Color(0,170,0));
				}
				else {
					g.setColor(new Color(0,121,0));
				}
				g.fillRect(x[i], y[i], unit+1, unit+1);
			}
			
			//Points
			g.setColor(Color.GREEN);
			g.setFont(pointFont);
			g.drawString("Points: " + points, 10, 10);
		}
		
		if(menuV == true){
			//g.setFont(menuTitle);
			//g.drawString("MENU", 100, 100);
		}
	}
	
	public void move() {
		for (int i = bodyLength; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		// Move up
		case 'U':
			y[0] = y[0] - unit;
			break;
		// Move down
		case 'D':
			y[0] = y[0] + unit;
			break;
		// Move right
		case 'R':
			x[0] = x[0] + unit;
			break;
		// Move left
		case 'L':
			x[0] = x[0] - unit;
			break;
		}
	}
	
	public void appleCheck() {
		if (x[0] == aX && y[0] == aY) {
			bodyLength++;
			points+=10;
			spawnApple();
		}
	}
	
	// This method checks if the snake hits a wall
	public void wallCheck() {
		
		// left and right walls
		if(x[0] < 0 || x[0] > screenWidth-unit) {
			running = false;
		}
		
		//Top and bottom walls
		if(y[0] < 0 || y[0] > screenHeight-unit) {
			running = false;
		}
	}
	
	// This method checks if the snake collides with itself
	public void bodyCheck () {
		for (int i = bodyLength ; i > 0 ; i--) {
			if( (x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
	}
	
	public void gameOver() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			appleCheck();
			wallCheck();
			bodyCheck();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent k) {
			
			// WASD keys for direction
			switch(k.getKeyCode()) {
			case KeyEvent.VK_A:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_D:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_W:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_S:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			
			// Arrow keys for direction
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			case KeyEvent.VK_P:
				startG(); // work in progress
				break;
			case KeyEvent.VK_H:
				highscores(); // work in progress
				break;
			case KeyEvent.VK_E:
				settings(); // work in progress
				break;
			}
		}
	}
}
	

