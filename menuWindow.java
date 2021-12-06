import javax.swing.JFrame;

public class menuWindow extends JFrame{
	menuWindow(){
		menuPanel  panel = new menuPanel ();
		this.add(panel);
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}
