package pontus.starlines.game.level.levels;

import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;
import pontus.starlines.game.level.Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Level007 extends Level {

	public Level007() {
		super(new Rectangle(-200, -450, 400, 100), new Circle(10, -100, 50));
		setName("Level - 7");

		
		addWall(new Line(-300, -300, -150, -300));
		addWall(new Line(300, -300, -75, -300));
		addWall(new Line(-290, -300, 60, 50));
		addWall(new Line(60, 50, 60 - 100, 50 + 100));
		addWall(new Line(-40, -50, -40 - 110, -50 + 110));
		addWall(new Line(-40, 40, -40 - 110, 40 + 110));
		addWall(new Line(-125, -50, -125 - 110, -50 + 110));
		addWall(new Line(-300, 0, 0, 300));
		
		addWall(new Line(-40 + 100, -50 + 100, -40 - 110 + 100, -50 + 110 + 100));
		
		addWall(new Line(10, 0, 110, -100));
		addWall(new Line(175, 25, 300, -100));
		
		addWall(new Line(150, 150, 300, 300));
		addWall(new Line(150, 150, 50, 250));



		
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
		 stars[0] = new Point(-212, -100);
		 stars[1] = new Point(240, 40);
		 stars[2] = new Point(-200, 225);

	}

}
