package pontus.starlines.game.level;

import java.util.ArrayList;

import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;
import pontus.starlines.game.Game;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Level {

	public String name = "UNDEFINED";
	
	public Rectangle start;
	
	public Circle finnish;
	
	public float originalFinnishRadius = 50;
	
	public float cursorX = 0, cursorY = 100;
	
	public ArrayList<Point> actualStars = new ArrayList<Point>();
	
	public Point[] stars = new Point[3];
	
	public ArrayList<Line> walls = new ArrayList<Line>();
	
	public void addWall(Line wall) {
		walls.add(wall);
	}
	float time = 0;

	public void reset() {
		time = 0;
		
		for (int i = 0; i < actualStars.size(); i++) {
			stars[i] = actualStars.get(i);
		}
		
	}
	
	public void render(SpriteBatch sb, float delta) {
		time += delta;

	}
	
	public void drawText(SpriteBatch sb, String text, float x, float y) {
		Game.font.getData().setScale(0.4f);
		GlyphLayout l = new GlyphLayout();
		l.setText(Game.font, text);
		Game.font.draw(sb, text, x - l.width / 2, y);
	}
	
	public void displayText(float startTime, float endTime, String text, float x, float y, SpriteBatch sb, float time) {
		if (time > startTime && time < endTime - 1) {
			Game.font.setColor(1, 1, 1, (time - startTime));
			drawText(sb, text, x, y);
		}
		if (time > endTime - 1 && time < endTime) {
			Game.font.setColor(1, 1, 1, 1 - (time - (endTime - 1)));
			drawText(sb, text, x, y);
		}
	}
	
	public Level(Rectangle start, Circle finnish) {
		this.start = start;
		this.finnish = finnish;
		originalFinnishRadius = finnish.radius;
		cursorX = start.width / 2 + start.x;
		cursorY = start.height / 2 + start.y;
		reset();
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
