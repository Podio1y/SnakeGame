import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

class SnakePanel extends JPanel implements ActionListener{
    int screenHeight = 600;
    int screenWidth = 600;
    int unit = 20;
    char direction = 'L';
    int length = 3;
    int delay = 80;
    // boolean running = true;
    int appleX = 0;
    int appleY = 0;
    String theme = "norm";
    boolean decreaseDelay = false;
    int score = 0;

    // Default colors/color variables
    Color bgLines = new Color(0,0,0);
    Color bgColor = Color.GRAY;
    Color snakeBody = new Color(0,150,0);
    Color snakeHead = new Color(0,255,0);
    Color appleColor = new Color(255,0,0);

    // Fonts
    Font scoreFont = new Font("SansSerif", 10, 12);

    public void setTheme(){

        if (theme == "norm"){

            bgLines = new Color(0,0,0);
            snakeBody = new Color(0,150,0);
            snakeHead = new Color(0,255,0);
            appleColor = new Color(255,0,0);
        }
        else if(theme == "purple"){

            bgColor = new Color(177,132,186);
            bgLines = new Color(140,58,210);
            snakeBody = new Color(0,0,122);
            snakeHead = new Color(40,40,255);
            appleColor = new Color(158,53,53);
        }
    }

    Random r;
    Timer t;

    int [] x = new int [(screenWidth/unit)*(screenHeight/unit)];
    int [] y  = new int [(screenWidth/unit)*(screenHeight/unit)];
    
    public SnakePanel(){
        r = new Random();
        setTheme();
        //t = new Timer(delay, this);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(bgColor);
		this.setFocusable(true);
        this.setVisible(true);
		this.addKeyListener(new MyKeyAdapter());
        assignCoords();
        randomStart();
        startGame();
        t = new Timer(delay, this);
        t.start();
    }

    public void assignCoords(){
        for (int i = 0 ;  i < ((screenWidth/unit)*(screenHeight/unit)) ; i++){
            x[i] = -1;
            y[i] = -1;
        }
        appleX = r.nextInt(screenWidth/unit);
        appleY = r.nextInt(screenHeight/unit);
    }

    public boolean wallCollide(){
        for (int i = 0 ; i < length ; i++){

            // Wall collisions
            if ( (x[i] < 0 || x[i] > ((screenWidth/unit) - 1)) || (y[i] < 0 || y[i] > ((screenHeight/unit) - 1))){
                return true;
            }
        }
        return false;
    }

    public boolean selfCollide(){
        for (int i = 0 ; i < length ; i++){

            for (int j = i + 1 ; j < length ; j++){
                // System.out.println(x[i] + " - " + x[j] + " - " + y[i] + " - " + y[j]);
                if (x[i] == x[j] && y[i] == y[j]){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean appleCollide(){

        System.out.println(appleX + " .. " + appleY);
        if(x[0] == appleX && y[0] == appleY){
            length++;
            return true;
        }
        return false;
    }

    public void spawnApple(){
        appleX = r.nextInt(screenWidth/unit);
        appleY = r.nextInt(screenHeight/unit);
    }

    public void randomStart(){
        int xStart = r.nextInt(screenWidth/(unit*2)) + (screenWidth/(unit*4));
        int yStart = r.nextInt(screenHeight/(unit*2)) + (screenHeight/(unit*4));

        for (int i = 0 ; i < 3 ; i++){
            x[i] = xStart + i;
            y[i] = yStart;
        }
    }

    public void startGame(){
        // t = new Timer(delay, this);
        // t.start();
        
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void move(){

        for (int i = length ; i > 0 ; i--){
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch(direction) {
		// Move up
		case 'U':
			y[0] = y[0] - 1;
			break;
		// Move down
		case 'D':
			y[0] = y[0] + 1;
			break;
		// Move right
		case 'R':
			x[0] = x[0] + 1;
			break;
		// Move left
		case 'L':
			x[0] = x[0] - 1;
			break;
		}
    }

    public void draw(Graphics g){
        background(g);
        drawApple(g);
        drawSnake(g);
        drawScore(g);
    }

    public void drawApple(Graphics g){
        g.setColor(appleColor);
        g.fillOval(appleX * unit, appleY * unit, unit, unit);
    }

    public void drawSnake(Graphics g){
        for (int i = 0 ; i < ((screenWidth/unit)*(screenHeight/unit)) ; i++){
            if (x[i] == -1){
                break;
            }
            else if (i != 0){
                g.setColor(snakeBody);
                g.fillRect(x[i]*unit, y[i]*unit, unit, unit);
            }
            else{
                g.setColor(snakeHead);
                g.fillRect(x[i]*unit, y[i]*unit, unit, unit);
            }
        }
    }

    public void background(Graphics g){
        g.setColor(bgLines);
        for (int i = 0 ; i < screenWidth ; i += unit){
            g.drawLine(i, 0, i, screenHeight);
        }
        for (int i = 0 ; i < screenHeight ; i += unit){
            g.drawLine(0, i, screenWidth, i);
        }
    }

    public void drawScore(Graphics g){
        g.setFont(scoreFont);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10,10);
    }

    //@Override
	public void actionPerformed(ActionEvent e) {
        // System.out.println(selfCollide() + " - " + wallCollide());
        if (decreaseDelay){
            delay += 5;
            //t.cancel();
            Timer t = new Timer(delay, this);
            t.start();
            decreaseDelay = false;
        }
        System.out.println("x: " + x[0] + " y: " + y[0]);
        if (!selfCollide() && !wallCollide()){
            move();
            repaint();
            if (appleCollide()){
                spawnApple();
                score += 10;
            }
        }
        else{
            System.out.println("gameover");
            System.exit(0);
        }
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent k) {
			
			// WASD keys for direction
			switch(k.getKeyCode()) {
                case KeyEvent.VK_A:
                    direction = 'L';
                    System.out.println("left");
                    break;
                case KeyEvent.VK_D:
                    direction = 'R';
                    System.out.println("right");
                    break;
                case KeyEvent.VK_W:
                    direction = 'U';
                    System.out.println("up");
                    break;
                case KeyEvent.VK_S:
                    direction = 'D';
                    System.out.println("down");
                    break;
                case KeyEvent.VK_J:
                    decreaseDelay = true;
                    System.out.println("delay: " + delay);
                    break;
		    }
	    }
    }
}