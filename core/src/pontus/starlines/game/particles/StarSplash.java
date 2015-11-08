package pontus.starlines.game.particles;

import pontus.starlines.game.Colors;
import pontus.starlines.game.Game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class StarSplash extends Particle {

	private Game game;
	
	private float size = 50;
	
	public StarSplash(float x, float y, Game g) {
		super(x, y);
		game = g;
		
		float s = 500;
		vel.x = (float) (Math.random() - 0.5) * s;
		vel.y = (float) (Math.random() - 0.5) * s;

	}

	@Override
	public void render(ShapeRenderer sr, float delta) {
		position.x += vel.x * delta;
		position.y += vel.y * delta;
		
		size -= 50 * delta;
		if (size <= 0) {
			ParticleHandler.particles.remove(this);
		}
		
		game.setColor(Colors.STAR, Colors.STAR_SHADOW);
		game.star(position.x, position.y, size);
	}

}
