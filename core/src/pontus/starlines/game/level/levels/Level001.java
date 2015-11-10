package pontus.starlines.game.level.levels;

import pontus.starlines.core.math.Point;
import pontus.starlines.game.level.Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Level001 extends Level {

	public Level001() {
		super(new Rectangle(-200, -400, 400, 100), new Circle(0, 350, 50));
		setName("Level - 1");

	}

	float time = 0;

	@Override
	public void render(SpriteBatch sb, float delta) {
		super.render(sb, delta);
		time += delta;

		sb.begin();
		{
			
			displayText(-1, 3, "Welcome to Just Don't!", 0, 0, sb, time);
			displayText(1.5f, 6, "Remember where those stars are?", 0, -30, sb, time);
			displayText(5, 10, "Pull the green 'blob' from the", 0, -60, sb, time);
			displayText(5, 10, "blue rectangle towards the circle", 0, -90, sb, time);
			displayText(9, 13, "Try to get all the stars!", 0, -120, sb, time);

		}
		sb.end();

	}
	
	

	public void reset() {
		time = 0;
		stars[0] = new Point(-225, 50);
		stars[1] = new Point(225, 50);
		stars[2] = new Point(0, 50);

	}

}
