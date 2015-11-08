package pontus.starlines.game.particles;

import pontus.starlines.game.Colors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Trail extends Particle {

	private Color c = Colors.TRAIL;

	
	public Trail(float x, float y) {
		super(x, y);
		vel.x = (float) (Math.random() - 0.5) * 200;
		vel.y = (float) (Math.random() - 0.5) * 200;
	}
	
	float s = 15;

	float a = 1;
	
	@Override
	public void render(ShapeRenderer sr, float delta) {
		
		s -= 50 * delta;

		if (s <= 0) {
			ParticleHandler.particles.remove(this);
		}
		
//		a -= 1 * delta;
//		if (a <= 0) a = 0;
		
		sr.setColor(c.r, c.g, c.b, a);
		sr.circle(position.x, position.y, s);
	}

}
