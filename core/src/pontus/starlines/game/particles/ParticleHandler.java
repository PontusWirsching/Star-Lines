package pontus.starlines.game.particles;

import java.util.ArrayList;

import pontus.starlines.core.graphics.Screen;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ParticleHandler {

	static Screen game;
	
	public static void init(Screen s) {
		game = s;
	}
	
	public static ArrayList<Particle> particles = new ArrayList<Particle>();

	public static void spawn(Particle p) {
		particles.add(p);
	}

	public static Particle get(int index) {
		return particles.get(index);
	}

	public static void render(ShapeRenderer sr, float delta) {
		for (int i = 0; i < particles.size(); i++) {
			if (i >= particles.size()) break;
			Particle p = get(i);
			if (p == null) continue;
			p.position.x += p.vel.x * delta;
			p.position.y += p.vel.y * delta;
			p.render(sr, delta);
//			if (p.position.y < game.camera.viewportHeight / 2);
		}
	}

}
