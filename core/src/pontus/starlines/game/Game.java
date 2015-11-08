package pontus.starlines.game;

import java.util.ArrayList;

import pontus.starlines.JustDont;
import pontus.starlines.core.Input;
import pontus.starlines.core.graphics.Screen;
import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;
import pontus.starlines.core.math.Util;
import pontus.starlines.game.level.LevelHandler;
import pontus.starlines.game.level.levels.Level001;
import pontus.starlines.game.particles.ParticleHandler;
import pontus.starlines.game.particles.StarSplash;
import pontus.starlines.game.particles.Trail;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Game extends Screen {

	public Game() {
		super("GAME");

		LevelHandler.addLevel(new Level001());

		cursorX = LevelHandler.getSelected().cursorX;
		cursorY = LevelHandler.getSelected().cursorY;

	}

	public float cursorX = 0, cursorY = 100;

	public boolean dead = false;
	public boolean deadToggle = false;

	public float showTimer = 0;
	public float showForSeconds = 2;
	public boolean show = true;
	public float wallAlpha = 1;

	public boolean canMove = false;

	public int stars = 0;

	public boolean win = false;

	@Override
	public void render(float delta) {
		super.render(delta);

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClearColor(Colors.OUT_OF_SCREEN.r, Colors.OUT_OF_SCREEN.g, Colors.OUT_OF_SCREEN.b, Colors.OUT_OF_SCREEN.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sr.begin(ShapeType.Filled);
		{
			sr.setColor(Colors.BACKGROUND);
			sr.rect(-JustDont.WIDTH / 2, -JustDont.HEIGHT / 2, JustDont.WIDTH, JustDont.HEIGHT);

			ArrayList<Line> walls = LevelHandler.getSelected().walls;

			if (!dead) showTimer += delta;
			if (showTimer > showForSeconds) {
				showTimer = 0;
				show = false;
			}

			if (!show) {
				wallAlpha -= delta * 2;
				if (wallAlpha <= 0) {
					wallAlpha = 0;
					canMove = true;
				}
			}

			setColor(Colors.WALL, Colors.WALL_SHADOW);
			for (Line wall : walls) {
				int w = 10;
				line((float) wall.getX1(), (float) wall.getY1(), (float) wall.getX2(), (float) wall.getY2(), w);
			}
			
			setColor(Colors.STAR, Colors.STAR_SHADOW);
			for (int i = 0; i < LevelHandler.getSelected().stars.length; i++) {
				Point p = LevelHandler.getSelected().stars[i];
				if (p == null) continue;
				star(p.x, p.y, 50);
				if (Util.getDistance(cursorX, cursorY, p.x, p.y) < 50) {
					LevelHandler.getSelected().stars[i] = null;
					stars++;
					for (int j = 0; j < 10; j++) {
						ParticleHandler.spawn(new StarSplash(p.x, p.y, this));
					}
				}
			}

			sr.setColor(new Color(Colors.BACKGROUND.r, Colors.BACKGROUND.g, Colors.BACKGROUND.b, 1 - wallAlpha));
			sr.rect(-JustDont.WIDTH / 2, -JustDont.HEIGHT / 2, JustDont.WIDTH, JustDont.HEIGHT);

			setColor(Colors.OBJECTS, Colors.OBJECTS_SHADOW);
			Rectangle r = LevelHandler.getSelected().start;
			rect(r.x, r.y, r.width, r.height);

			Circle c = LevelHandler.getSelected().finnish;

			if (Util.getDistance(c.x, c.y, cursorX, cursorY) < c.radius) {
				circle(c.x, c.y, c.radius + 10);
			} else {
				circle(c.x, c.y, c.radius);
			}
			
			
			
			ParticleHandler.render(sr, delta);

			float ix = Input.getX();
			float iy = Input.getY();

			if (!dead && canMove && !win) if (Input.isTouched()) {
				Vector2 v = new Vector2(ix - cursorX, iy - cursorY);
				float angle = v.angle();

				float s = (float) Util.getDistance(ix, iy, cursorX, cursorY) * 1;

				for (int i = 0; i < s; i++) {
					boolean collision = false;
					for (Line wall : walls) {

						sr.setColor(Color.YELLOW);
						float[] f = new float[] { (float) wall.getX1(), (float) wall.getY1() - 5, (float) wall.getX1(), (float) wall.getY1() + 5, (float) wall.getX2(), (float) wall.getY2() + 5, (float) wall.getX2(), (float) wall.getY2() - 5 };
						Polygon p = new Polygon(f);
						if (p.contains(cursorX, cursorY)) {
							collision = true;
						}

					}

					cursorX += Math.cos(Math.toRadians(angle));
					cursorY += Math.sin(Math.toRadians(angle));

					if (collision) {
						dead = true;
						cursorX -= Math.cos(Math.toRadians(angle));
						cursorY -= Math.sin(Math.toRadians(angle));
					}

					if (cursorX < -JustDont.WIDTH / 2 || cursorX > JustDont.WIDTH / 2) {
						cursorX -= Math.cos(Math.toRadians(angle));
					}
				}

			} else {
				if (!dead) {

					if (Util.getDistance(cursorX, cursorY, c.x, c.y) < c.radius) {
						win = true;
					}

					cursorX = LevelHandler.getSelected().cursorX;
					cursorY = LevelHandler.getSelected().cursorY;
				}
			}

			if (!dead && !win) if (!r.contains(Input.getTouchedDownX(), Input.getTouchedDownY())) {
				cursorX = LevelHandler.getSelected().cursorX;
				cursorY = LevelHandler.getSelected().cursorY;
			}

			ParticleHandler.spawn(new Trail(cursorX, cursorY));

			sr.setColor(Colors.OUT_OF_SCREEN);
			sr.rect(-camera.viewportWidth / 2, -camera.viewportHeight / 2, camera.viewportWidth / 2 - JustDont.WIDTH / 2, camera.viewportHeight);
			sr.rect(JustDont.WIDTH / 2, -camera.viewportHeight / 2, camera.viewportWidth / 2 - JustDont.WIDTH / 2, camera.viewportHeight);

			// win = true;

			if (win) {
				float rrr = 0;
				if (camera.viewportWidth > camera.viewportHeight) {
					rrr = camera.viewportWidth;
				} else {
					rrr = camera.viewportHeight;
				}

				setColor(Colors.OBJECTS, Colors.OBJECTS_SHADOW);
				circle(c.x, c.y, c.radius);

				if (c.radius <= rrr + 100) {
					c.radius += delta * rrr / 0.5;
				} else {

					for (int i = 0; i < 500; i++) {
						if (starY > 100) {
							starY -= delta;
						} else {
							starY = 100;
						}
					}
					if (stars > 0) setColor(Colors.STAR, Colors.STAR_SHADOW);
					else setColor(Colors.STAR_GRAY, Colors.STAR_GRAY_SHADOW);
					star(-150 - starY * 2 + 200, 100, 100);
					if (stars > 1) setColor(Colors.STAR, Colors.STAR_SHADOW);
					else setColor(Colors.STAR_GRAY, Colors.STAR_GRAY_SHADOW);
					star(0, starY, 100);
					if (stars > 2) setColor(Colors.STAR, Colors.STAR_SHADOW);
					else setColor(Colors.STAR_GRAY, Colors.STAR_GRAY_SHADOW);
					star(150 + starY * 2 - 200, 100, 100);

				}

			}

			if (dead && !deadToggle) {
				float rrr = 0;
				if (camera.viewportWidth > camera.viewportHeight) {
					rrr = camera.viewportWidth;
				} else {
					rrr = camera.viewportHeight;
				}
				sr.setColor(Colors.WALL);

				if (s <= rrr + 100) {
					s += delta * rrr / 0.5;
				} else {
					cursorX = LevelHandler.getSelected().cursorX;
					cursorY = LevelHandler.getSelected().cursorY;
					timer1 += delta;
					if (timer1 >= 1) {
						deadToggle = true;
						s = 1;
						timer1 = 0;
					}
					show = true;
					wallAlpha = 1;
					showTimer = 0;
					canMove = false;
					resetAll();
					sr.rect(-camera.viewportWidth / 2, -camera.viewportHeight / 2, camera.viewportWidth, camera.viewportHeight);

				}

				sr.rect(cursorX - s / 2, cursorY - s / 2, s, s);
			}

			if (deadToggle) {
				s -= delta * 1.75;
				if (s <= 0) {
					deadToggle = false;
					dead = false;
				}
				sr.setColor(new Color(Colors.WALL.r, Colors.WALL.g, Colors.WALL.b, s));
				sr.rect(-camera.viewportWidth / 2, -camera.viewportHeight / 2, camera.viewportWidth, camera.viewportHeight);
			}

		}
		sr.end();

	}

	public void resetAll() {
		stars = 0;
		LevelHandler.getSelected().reset();
	}
	
	float starY = camera.viewportHeight / 1.5f;

	private float s = 1;
	private float timer1 = 0;

	private Color color;
	private Color shadow;

	public void setColor(Color c, Color s) {
		color = c;
		shadow = s;
	}

	public void star(float x, float y, float size) {
		float w = size / 10;
		float off = -size / 25;
		sr.setColor(shadow);
		lineNoShadow(x - size / 3, y - size / 2 + off, x, y + size / 2 + off, w);
		lineNoShadow(x + size / 3, y - size / 2 + off, x - size / 2, y + size / 5 + off, w);
		lineNoShadow(x, y + size / 2 + off, x + size / 3, y - size / 2 + off, w);
		lineNoShadow(x - size / 2, y + size / 5 + off, x + size / 2, y + size / 5 + off, w);
		lineNoShadow(x + size / 2, y + size / 5 + off, x - size / 3, y - size / 2 + off, w);
		sr.triangle(x, y + size / 2 + off, x + size / 5.4f, y - size / 15 + off, x - size / 5.4f, y - size / 15 + off);
		sr.triangle(x - size / 2, y + size / 5 + off, x + size / 2, y + size / 5 + off, x, y - size / 4.5f + off);
		sr.triangle(x - size / 3, y - size / 2 + off, x - size / 5.4f, y - size / 16 + off, x, y - size / 4.5f + off);
		sr.triangle(x + size / 3, y - size / 2 + off, x + size / 5.4f, y - size / 16 + off, x, y - size / 4.5f + off);
		sr.setColor(color);
		lineNoShadow(x - size / 3, y - size / 2, x, y + size / 2, w);
		lineNoShadow(x + size / 3, y - size / 2, x - size / 2, y + size / 5, w);
		lineNoShadow(x, y + size / 2, x + size / 3, y - size / 2, w);
		lineNoShadow(x - size / 2, y + size / 5, x + size / 2, y + size / 5, w);
		lineNoShadow(x + size / 2, y + size / 5, x - size / 3, y - size / 2, w);
		sr.triangle(x, y + size / 2, x + size / 5.4f, y - size / 15, x - size / 5.4f, y - size / 15);
		sr.triangle(x - size / 2, y + size / 5, x + size / 2, y + size / 5, x, y - size / 4.5f);
		sr.triangle(x - size / 3, y - size / 2, x - size / 5.4f, y - size / 16, x, y - size / 4.5f);
		sr.triangle(x + size / 3, y - size / 2, x + size / 5.4f, y - size / 16, x, y - size / 4.5f);
	}

	public void line(float x1, float y1, float x2, float y2, float width) {
		sr.setColor(shadow);
		sr.rectLine(x1, y1 - 5, x2, y2 - 5, width);
		sr.circle(x1, y1 - 5, width / 2);
		sr.circle(x2, y2 - 5, width / 2);
		sr.setColor(color);
		sr.rectLine(x1, y1, x2, y2, width);
		sr.circle(x1, y1, width / 2);
		sr.circle(x2, y2, width / 2);
	}

	public void lineNoShadow(float x1, float y1, float x2, float y2, float width) {
		sr.rectLine(x1, y1, x2, y2, width);
		sr.circle(x1, y1, width / 2);
		sr.circle(x2, y2, width / 2);
	}

	public void circle(float x, float y, float r) {
		sr.setColor(shadow);
		sr.circle(x, y - 5, r);
		sr.setColor(color);
		sr.circle(x, y, r);
	}

	public void rect(float x, float y, float width, float height) {
		sr.setColor(shadow);
		sr.rect(x, y - 5, width, height);
		sr.setColor(color);
		sr.rect(x, y, width, height);
	}

}
