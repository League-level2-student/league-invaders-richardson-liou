import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    Timer frameDraw;
    Timer alienSpawn;
    int currentState = MENU;
    Font titleFont;
    Font subTitle;
    RocketShip rocket = new RocketShip(250,700,50,50);
    ObjectManager objMan = new ObjectManager(rocket);
    
    public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
    
    GamePanel(){
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	subTitle = new Font("Arial", Font.PLAIN, 25);
    	frameDraw = new Timer(1000/60, this);
    	alienSpawn = new Timer(1000, objMan);
    	frameDraw.start();
    	alienSpawn.start();
    	
    	if (needImage) {
		    loadImage ("space.jpeg");
		}
    }
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}

	}
	
	void updateMenuState(){
		
	}
	
	void updateGameState() {
		objMan.update();
		if (objMan.rocket.isActive==false) {
			currentState = END;
		}
		
	}
	
	void updateEndState() {
		
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("League Invaders", 100, 150);
		
		g.setFont(subTitle);
		g.setColor(Color.YELLOW);
		g.drawString("Press Enter to start", 150, 400);
		g.drawString("Press SPACE for instructions", 100, 600);
	}
	
	void drawGameState(Graphics g) {

		g.drawImage(image, WIDTH, HEIGHT, null);
		objMan.draw(g);
	}
	
	void drawEndState(Graphics g) {
		int killed = objMan.getScore();
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER", 100, 150);
		
		g.setFont(subTitle);
		g.setColor(Color.YELLOW);
		g.drawString("Press Enter to restart", 150, 400);
		g.drawString("You killed "+killed +" enemies.", 100, 600);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		System.out.println("Action");
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		        rocket = new RocketShip(250,700,50,50);
		        objMan = new ObjectManager(rocket);
		        alienSpawn.start();
		    } else if(currentState == END) {
		        alienSpawn.stop();
		    }
		    
		    else {
		    	currentState++;
		    	frameDraw.start();
		    }
		}
		if (currentState == GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				if(rocket.y >0) {
			    System.out.println("UP");
			    rocket.up();
				}
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				if(rocket.y <=LeagueInvaders.HEIGHT -50) {
			    System.out.println("DOWN");
			    rocket.down();
				}
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				if(rocket.x<LeagueInvaders.WIDTH-50) {
			    System.out.println("RIGHT");
			    rocket.right();
				}
			}
			
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				if(rocket.x>0) {
			    System.out.println("Left");
			    rocket.left();
				}
			}
			
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				objMan.addProjectile(rocket.getProjectile());
			}
			
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
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	
	
	
}
