import javax.swing.JFrame;

public class GameWindow extends JFrame{
	GameWindow(){
		
		GamePanel  panel = new GamePanel ();
		this.add(panel);
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}
