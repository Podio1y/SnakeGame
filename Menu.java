import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Menu extends JPanel implements ActionListener{
    int screenHeight = 600;
    int screenWidth = 600;
    int unit = 20;
    char choice = 'M';
    String theme = "norm";

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
    
    public Menu(){
        r = new Random();
        setTheme();
        //t = new Timer(delay, this);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(bgColor);
		this.setFocusable(true);
        this.setVisible(true);
		this.addKeyListener(new MyKeyAdapter());
        // t = new Timer(delay, this);
        // t.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        background(g);
        drawOptions(g);
    }

    public void drawOptions(Graphics g){
        if (choice == 'H'){
            g.fillRect(100,100,100,100);
            choice = '.';
        }
        g.drawString("[P]lay", 10,10);
        g.drawString("[H]ighscores", 10,30);
        g.drawString("[I]nstructions", 10,50);
        g.drawString("[S]ettings", 10,70);
        g.drawString("[Q]uit", 10,90);
        //g.setColor(Color.BLACK);
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

    //@Override
	public void actionPerformed(ActionEvent e) {
        repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent k) {
			
			// WASD keys for choice
			switch(k.getKeyCode()) {
                case KeyEvent.VK_Q:
                    choice = 'Q';
                    break;
                case KeyEvent.VK_H:
                    choice = 'H';
                    System.out.println("testest");
                    break;
                case KeyEvent.VK_I:
                    choice = 'I';
                    break;
                case KeyEvent.VK_S:
                    choice = 'S';
                    break;
		    }
            repaint();
	    }
    }
}