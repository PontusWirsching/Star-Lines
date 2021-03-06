package pontus.starlines.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import pontus.starlines.JustDont;
import pontus.starlines.core.Input;
import pontus.starlines.core.graphics.Screen;
import pontus.starlines.core.graphics.ScreenManager;
import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Point;
import pontus.starlines.core.math.Util;
import pontus.starlines.core.sound.SoundEffects;
import pontus.starlines.game.level.LevelHandler;
import pontus.starlines.game.level.levels.Level001;
import pontus.starlines.game.level.levels.Level002;
import pontus.starlines.game.level.levels.Level003;
import pontus.starlines.game.level.levels.Level004;
import pontus.starlines.game.level.levels.Level005;
import pontus.starlines.game.level.levels.Level006;
import pontus.starlines.game.level.levels.Level007;
import pontus.starlines.game.particles.ParticleHandler;
import pontus.starlines.game.particles.StarSplash;
import pontus.starlines.game.particles.Trail;

public class Game extends Screen {

	public static BitmapFont font = new BitmapFont(Gdx.files.internal("test.bmf"), Gdx.files.internal("test_00.png"), false);

	
	
	public Game() {
		super("GAME");

		
		
		LevelHandler.addLevel(new Level001(), new Point(0, 0), 0);
		LevelHandler.addLevel(new Level002(), new Point(1, 0), 0);
		LevelHandler.addLevel(new Level003(), new Point(2, 0), 0);
		LevelHandler.addLevel(new Level004(), new Point(3, 0), 0);
		LevelHandler.addLevel(new Level005(), new Point(0, 1), 0);
		LevelHandler.addLevel(new Level006(), new Point(1, 1), 0);
		LevelHandler.addLevel(new Level007(), new Point(2, 1), 0);

		
//		Parser.parse("levels");

		LevelHandler.selected = LevelHandler.levels.size() - 1;
		
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

	public float fadeTimer = 1;

	boolean t = false;
	
	public boolean didTouchInRectangle = false;
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		if (Gdx.input.isKeyPressed(Keys.SPACE) && t) {
			t = false;
			
			LevelHandler.levels.set(LevelHandler.selected, new Level007());
			
		} else if (!Gdx.input.isKeyPressed(Keys.SPACE) && !t) {
			t = true;
		}

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClearColor(Colors.OUT_OF_SCREEN.r, Colors.OUT_OF_SCREEN.g, Colors.OUT_OF_SCREEN.b, Colors.OUT_OF_SCREEN.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

<<<<<<< HEAD
		sr.begin(ShapeType.Line);
=======
//		sr.begin(ShapeType.Filled);
//		sr.setColor(Color.BLACK);
//		sr.rect(-JustDont.WIDTH / 2, -JustDont.HEIGHT / 2, JustDont.WIDTH, JustDont.HEIGHT);
//		sr.end();
		
		sr.begin(ShapeType.Filled);
>>>>>>> origin/master
		{
			sr.setColor(Colors.BACKGROUND);
			sr.rect(-JustDont.WIDTH / 2, -JustDont.HEIGHT / 2, JustDont.WIDTH, JustDont.HEIGHT);

			ArrayList<Line> walls = LevelHandler.getSelected().walls;

			if (!dead) showTimer += delta;
			if (showTimer > showForSeconds) {
				showTimer = 0;
				show = false;
			}

			canMove = true;
			
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
					SoundEffects.PICKUP_STAR.play();
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

			if (!dead && canMove && !win) if (didTouchInRectangle && Input.isTouched()) {
				Vector2 v = new Vector2(ix - cursorX, iy - cursorY);
				float angle = v.angle();

				float s = (float) Util.getDistance(ix, iy, cursorX, cursorY) * 1;

				for (int i = 0; i < s; i++) {
					boolean collision = false;
					for (Line wall : walls) {

						sr.setColor(Color.YELLOW);
						
						double a = Util.getAngle(wall.getX1(), wall.getY1(), wall.getX2(), wall.getY2());
						
						float xm = (float) (Math.cos(Math.toRadians(a)) * 5);
						float ym = (float) (Math.sin(Math.toRadians(a)) * 5);

						float[] f = new float[] { (float) wall.getX1() + xm, (float) wall.getY1() - ym, (float) wall.getX1() - xm, (float) wall.getY1() + ym, (float) wall.getX2() - xm, (float) wall.getY2() + ym, (float) wall.getX2() + xm, (float) wall.getY2() - ym };
						Polygon p = new Polygon(f);
						sr.polygon(f);
						if (p.contains(cursorX, cursorY)) {
							collision = true;
						}

					}

					cursorX += Math.cos(Math.toRadians(angle));
					cursorY += Math.sin(Math.toRadians(angle));

					if (collision) {
						SoundEffects.HIT_WALL.play();
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
						
						if (stars == 0) {
							SoundEffects.NO_STARS.play();
						}
						if (stars > 0 && stars <= 2) {
							SoundEffects.ONE_TWO_STARS.play();
						}
						if (stars > 2) {
							SoundEffects.THREE_STARS.play();
						}
						
					}

					cursorX = LevelHandler.getSelected().cursorX;
					cursorY = LevelHandler.getSelected().cursorY;
				}
			}

			if (!canMove) {
				Input.downAtX = 0;
				Input.downAtY = 0;
//				System.out.println("SHIT");
			}
			
			if (!canMove && !dead && !win) if (!r.contains(Input.getTouchedDownX(), Input.getTouchedDownY())) {
				cursorX = LevelHandler.getSelected().cursorX;
				cursorY = LevelHandler.getSelected().cursorY;
			}
			
			if (!dead && canMove && !win && r.contains(Input.getTouchedDownX(), Input.getTouchedDownY())) {
				didTouchInRectangle = true;
			}

			ParticleHandler.spawn(new Trail(cursorX, cursorY));

			sr.end();
			
			LevelHandler.getSelected().render(sb, delta);
			
			sr.begin(ShapeType.Filled);
			
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

					// starY = camera.viewportHeight / 1.5f;
					float time = 1;
					float t = 10;

					float s = 100 * (time);
					float px = -s / 2.5f - starY + 200;
					float py = -200;
					if (s > 0) {
						setColor(Color.WHITE, Color.GRAY);
						line(px, py + s / 2, px + s, py, t);
						line(px, py + s / 2, px, py - s / 2, t);
						line(px, py - s / 2, px + s, py, t);
						sr.triangle(px, py + s / 2, px + s, py, px, py - s / 2);

					}
					
					float bw = 75;
					float bh = 75;

					float bx = starY - 200;
					float by = -200;
					t = 15;

					line(bx + bw / 2, by - bh / 2, bx - bw / 2, by - bh / 2, t);
					line(bx + bw / 2, by, bx - bw / 2, by, t);
					line(bx + bw / 2, by + bh / 2, bx - bw / 2, by + bh / 2, t);


					
					
					
					if (Input.isTouched()) {
						if (Util.getDistance(px + s / 4, py, Input.getX(), Input.getY()) < s) {
							back = true;
						}
						if (Util.getDistance(bx, by, Input.getX(), Input.getY()) < bw) {
							back = true;
							menu = true;
						}
					}

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
		
		sb.begin();
		
		{
			font.setColor(Color.WHITE);
			font.getData().setScale(1);
			GlyphLayout l = new GlyphLayout();
			l.setText(Game.font, LevelHandler.getSelected().name);
			font.draw(sb, LevelHandler.getSelected().name, -l.width / 2, 350 + starY - 100);
		}
		
		sb.end();
		

		if (back) {
			fadeTimer += delta;
			if (fadeTimer >= 1) {
				fadeTimer = 1;
				back = false;
				resetAll();
				win = false;
				stars = 0;
				showTimer = 0;
				show = true;
				wallAlpha = 1;
				cursorX = LevelHandler.getSelected().cursorX;
				cursorY = LevelHandler.getSelected().cursorY;
				starY = camera.viewportHeight / 1.5f;
				LevelHandler.getSelected().finnish.radius = LevelHandler.getSelected().originalFinnishRadius;
				canMove = false;
				if (LevelHandler.selected < LevelHandler.levels.size() - 1) LevelHandler.selected++;
				else ScreenManager.setSelected("MENU");
				
				System.out.println(menu);
				
				if (menu) ScreenManager.setSelected("LEVEL_SELECTION");
				menu = false;
			}
		} else {
			fadeTimer -= delta;
			if (fadeTimer <= 0) {
				fadeTimer = 0;
			}
		}


		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		sr.begin(ShapeType.Filled);
		sr.setColor(new Color(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, fadeTimer));
		sr.rect(-JustDont.WIDTH / 2, -JustDont.HEIGHT / 2, JustDont.WIDTH, JustDont.HEIGHT);
		sr.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);

	}

	public boolean back = false;

	public boolean menu = false;
	
	public void resetAll() {
		stars = 0;
		LevelHandler.getSelected().reset();
		didTouchInRectangle = false;
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
