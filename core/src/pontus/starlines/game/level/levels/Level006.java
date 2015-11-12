package pontus.starlines.game.level.levels;

import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;
import pontus.starlines.game.level.Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Level006 extends Level {

	public Level006() {
		super(new Rectangle(-200, -450, 400, 100), new Circle(0, 350, 50));
		setName("Level - 6");

		addWall(new Line(-300, -250, -50, -250));
		addWall(new Line(50, -250, 300, -250));
		addWall(new Line(-50, -250, -150, -100));
		addWall(new Line(-150, -100, 0, 125));
		addWall(new Line(50, -250, -50, -100));
		addWall(new Line(-50, -100, 50, 50));
		addWall(new Line(50, 50, 150, -100));
		addWall(new Line(125, 125, 275, -100));
		addWall(new Line(125, 125, 275, 350));
		addWall(new Line(0, 125, -100, 250));
		addWall(new Line(-200, 125, -100, 250));

	}

	float time = 0;

	@Override
	public void render(SpriteBatch sb, float delta) {
		super.render(sb, delta);
		time += delta;

		sb.begin();
		{
			
		}
		sb.end();

	}

	public void reset() {
		time = 0;
		 stars[0] = new Point(-200, -200);
		 stars[1] = new Point(-100, 150);
		 stars[2] = new Point(50, -50);

	}

}
