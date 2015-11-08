package pontus.starlines.game.level.levels;

import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;
import pontus.starlines.game.level.Level;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Level001 extends Level {

	public Level001() {
		super(new Rectangle(-200, -400, 400, 100),  new Circle(0, 350, 50)); 
		setName("01");
		
		
		addWall(new Line(-300, 100, -175, 100));
		addWall(new Line(-100, 100, 300, 100));
		
		addWall(new Line(-300, 0, 100, 0));
		addWall(new Line(175, 0, 300, 0));

		addWall(new Line(-300, -100, -175, -100));
		addWall(new Line(-100, -100, 300, -100));
	}
	
	public void reset() {
		stars[0] = new Point(-225, -50);
		stars[1] = new Point(225, 50);
		stars[2] = new Point(100, -250);

	}

}
