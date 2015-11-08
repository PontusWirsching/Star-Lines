package pontus.starlines.game.level;

import java.util.ArrayList;

import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Level {

	public String name = "UNDEFINED";
	
	public Rectangle start;
	
	public Circle finnish;
	
	public float cursorX = 0, cursorY = 100;
	
	public Point[] stars = new Point[3];
	
	public ArrayList<Line> walls = new ArrayList<Line>();
	
	public void addWall(Line wall) {
		walls.add(wall);
	}
	
	public void reset() {
		
	}
	
	public Level(Rectangle start, Circle finnish) {
		this.start = start;
		this.finnish = finnish;
		cursorX = start.width / 2 + start.x;
		cursorY = start.height / 2 + start.y;
		reset();
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
