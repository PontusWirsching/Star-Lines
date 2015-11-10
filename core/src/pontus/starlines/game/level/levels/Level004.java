package pontus.starlines.game.level.levels;

import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;
import pontus.starlines.game.level.Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Level004 extends Level {

	public Level004() {
		super(new Rectangle(-200, -400, 400, 100), new Circle(0, 350, 50));
		setName("Level - 4");

		addWall(new Line(-300, -50, 100, -50));
		addWall(new Line(-100, 50, 300, 50));
		addWall(new Line(-100, 50, -100, 0));
		addWall(new Line(100, -50, 100, 0));

	}

	float time = 0;

	@Override
	public void render(SpriteBatch sb, float delta) {
		super.render(sb, delta);
		time += delta;

		sb.begin();
		{
			
			displayText(-1, 4, "Well lets see how good you", 0, -100, sb, time);
			displayText(-1, 4, "really are..", 0, -130, sb, time);
			
		}
		sb.end();

	}

	public void reset() {
		time = 0;
		stars[0] = new Point(-200, 0);
		stars[1] = new Point(0, 0);
		stars[2] = new Point(200, 0);

	}

}
