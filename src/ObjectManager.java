import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObjectManager implements ActionListener {
	RocketShip rocket;
	List<Projectile> projectiles = new ArrayList();
	List<Alien> aliens = new ArrayList();
	Random random = new Random();
	int score = 0;

	ObjectManager(RocketShip r) {
		rocket = r;

	}

	public int getScore() {
		return score;
	}
	
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	public void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}

	public void update() {
		for (int i = 0; i < aliens.size(); i++) {
			Alien numAlien = aliens.get(i);
			numAlien.update();
			int alieny = numAlien.y;
			if (alieny > LeagueInvaders.HEIGHT) {
				numAlien.isActive = false;
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			Projectile proj = projectiles.get(i);
			proj.update();
			int projy = proj.y;
			if (projy > LeagueInvaders.HEIGHT) {
				proj.isActive = false;

			}
		}
		rocket.update();
		if(rocket.isActive == true) {
		checkCollision();
		
		}
		purgeObjects();
	}

	public void draw(Graphics g) {
		rocket.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			Alien numAlien = aliens.get(i);
			numAlien.draw(g);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile proj = projectiles.get(i);
			proj.draw(g);
		}

	}

	public void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			Alien numAlien = aliens.get(i);
			if (numAlien.isActive == false) {
				aliens.remove(numAlien);
			}

		}
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile proj = projectiles.get(i);
			if (proj.isActive == false) {
				projectiles.remove(proj);
			}
		}
	}

	public void checkCollision() {
		for(int i = 0; i<aliens.size(); i++) {
			Alien numAlien = aliens.get(i);
			for (int k = 0; k < projectiles.size(); k++) {
				Projectile proj = projectiles.get(k);
				
				if(proj.collisionBox.intersects(numAlien.collisionBox)) {
					numAlien.isActive = false;
					proj.isActive = false;
					score +=1;
				}
			}
			
			if(rocket.collisionBox.intersects(numAlien.collisionBox)) {
				numAlien.isActive = false;
				rocket.isActive = false;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		addAlien();

	}

}
