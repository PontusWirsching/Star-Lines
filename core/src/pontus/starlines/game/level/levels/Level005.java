package pontus.starlines.game.level.levels;

import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;
import pontus.starlines.game.level.Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Level005 extends Level {

	public Level005() {
		super(new Rectangle(50, -400, 200, 200), new Circle(-125, -300, 100));
		setName("Level - 5");

		addWall(new Line(0, 300, 200, 300));
		addWall(new Line(0, -500, 0, 300));
		addWall(new Line(200, 350, 200, 250));

		addWall(new Line(100, 175, 300, 175));
		addWall(new Line(100, 225, 100, 125));
		addWall(new Line(0, 50, 200, 50));
		addWall(new Line(200, 100, 200, 0));

		addWall(new Line(100, -75, 300, -75));
		addWall(new Line(100, -25, 100, -125));


	}

	float time = 0;

	@Override
	public void render(SpriteBatch sb, float delta) {
		super.render(sb, delta);
		time += delta;

		sb.begin();
		{
			displayText(-1, 4, "Ok, this is the first hard level..", 0, 400, sb, time);
			displayText(3, 6, "Good luck!", 0, 370, sb, time);
		}
		sb.end();

	}

	public void reset() {
		time = 0;
		 stars[0] = new Point(150, 225);
		 stars[1] = new Point(-125, 0);
		 stars[2] = new Point(-125, 300);

	}

}
