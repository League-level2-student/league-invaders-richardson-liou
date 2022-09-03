import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObjectManager implements ActionListener{
	RocketShip rocket;
	List<Projectile> projectiles = new ArrayList();
	List<Alien> aliens = new ArrayList();
	Random random = new Random();

	ObjectManager(RocketShip r) {
		rocket = r;

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
	
	public void purgObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			Alien numAlien = aliens.get(i);
			if(numAlien.isActive == false) {
				aliens.remove(numAlien);
			}

		}
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile proj = projectiles.get(i);
			if(proj.isActive == false) {
				projectiles.remove(proj);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		addAlien();
		
	}

}
