import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	GamePanel panel = new GamePanel();
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	LeagueInvaders(){
		frame = new JFrame();
	}
	public void setup() {
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		LeagueInvaders invade = new LeagueInvaders();
		invade.setup();
		
	}
}
