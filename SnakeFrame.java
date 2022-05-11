import javax.swing.JFrame;

class SnakeFrame extends JFrame{

	static Menu menu = new Menu();

    public SnakeFrame(){
        SnakePanel game = new SnakePanel();
		this.add(game);
		this.setTitle("My Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
    }

	public static char getChoice(){
		return menu.choice;
	}

	public static void menuInput(){
		while(getChoice() != 'Q'){
			System.out.print(""); // For some reason this line MUST be here or else the if statement doesnt work?!
			if (menu.choice == 'I'){
				System.out.println("instructions");
			}
			else if(menu.choice == 'H'){
				System.out.println("highscores");
				//break;
			}
			else if(menu.choice == 'S'){
				System.out.println("settings");
			}
			//menu.choice = '.';
		}
	}

    public static void main(String[] args){

		// JFrame test = new JFrame();
		// test.add(menu);
		// test.setTitle("My Snake Game - Menu");
		// test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// test.setVisible(true);
		// test.pack();
		// test.setResizable(false);
		// test.setLocationRelativeTo(null);
		
		// menuInput();
		// System.exit(0);
		
        SnakeFrame frame = new SnakeFrame();
    }
}