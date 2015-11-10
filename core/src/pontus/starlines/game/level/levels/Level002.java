package pontus.starlines.game.level.levels;

import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;
import pontus.starlines.game.level.Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Level002 extends Level {

	public Level002() {
		super(new Rectangle(-200, -400, 400, 100), new Circle(0, 350, 50));
		setName("Level - 2");

		addWall(new Line(-100, 0, 100, 0));

	}

	float time = 0;

	@Override
	public void render(SpriteBatch sb, float delta) {
		super.render(sb, delta);
		time += delta;

		sb.begin();
		{

			displayText(-1, 3, "You see that line there?", 0, -100, sb, time);
			displayText(2, 5, "Good, remember that one!", 0, -130, sb, time);
			displayText(4, 8, "\"Just Don't\" hit it ok?", 0, -160, sb, time);

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
