package pontus.starlines.game.particles;

import pontus.starlines.core.math.Point;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Particle {

	public Point position = new Point();
	
	public Vector2 vel = new Vector2();
	
	public Particle(float x, float y) {
		position.set(x, y);
	}
	
	public abstract void render(ShapeRenderer sr, float delta);

}
