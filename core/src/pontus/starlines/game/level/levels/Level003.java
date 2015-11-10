package pontus.starlines.game.level.levels;

import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;
import pontus.starlines.game.level.Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Level003 extends Level {

	public Level003() {
		super(new Rectangle(-200, -400, 400, 100), new Circle(0, 350, 50));
		setName("Level - 3");

		addWall(new Line(-300, -50, 100, -50));
		addWall(new Line(-100, 50, 300, 50));

	}

	float time = 0;

	@Override
	public void render(SpriteBatch sb, float delta) {
		super.render(sb, delta);
		time += delta;

		sb.begin();
		{

			displayText(-1, 4, "Ok, slow but steady huh?", 0, -100, sb, time);
			displayText(3, 7, "If thats your way of playing..", 0, -130, sb, time);
			
		}
		sb.end();

	}

	public void reset() {
		time = 0;
		stars[0] = new Point(-200, 0);
		stars[1] = new Point(0, 150);
		stars[2] = new Point(200, 0);

	}

}
