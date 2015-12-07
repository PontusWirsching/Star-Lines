package pontus.starlines.prepare;

import pontus.starlines.JustDont;
import pontus.starlines.core.Input;
import pontus.starlines.core.graphics.Screen;
import pontus.starlines.core.graphics.ScreenManager;
import pontus.starlines.core.math.Util;
import pontus.starlines.game.Colors;
import pontus.starlines.game.level.LevelHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class LevelSelection extends Screen {

	BitmapFont font = new BitmapFont(Gdx.files.internal("test.bmf"), Gdx.files.internal("test_00.png"), false);

	public LevelSelection() {
		super("LEVEL_SELECTION");
	}

	static int w = 4;
	static int h = 4;
	static int p = 1;
	public static int[][][] levels = new int[p][w][h];
	float space = 1.3f;
	float size = JustDont.WIDTH / w / space;

	float time = -1;

	public void resize(int width, int height) {
		super.resize(width, height);
	}

	boolean play = false;
	public static float playTime = 0;
	int sel = 0;

	@Override
	public void render(float delta) {
		super.render(delta);

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClearColor(Colors.OUT_OF_SCREEN.r, Colors.OUT_OF_SCREEN.g, Colors.OUT_OF_SCREEN.b, Colors.OUT_OF_SCREEN.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sr.begin(ShapeType.Filled);

		sr.setColor(Colors.BACKGROUND);
		sr.rect(-JustDont.WIDTH / 2, -JustDont.HEIGHT / 2, JustDont.WIDTH, JustDont.HEIGHT);

		space = 1.3f;
		size = JustDont.WIDTH / w / space;

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			time = -1;
		}
		
		int cc = 0;

		for (int y = h - 1; y >= 0; y--) {
			for (int x = 0; x < w; x++) {
				setColor(Colors.OBJECTS, Colors.OBJECTS_SHADOW);
				float xx = (x * size * space - (size * space * w) / 2 + ((size * space) - size) / 2) - camera.viewportWidth + camera.viewportWidth * time;
				float yy = (y * size * space - size * space * h / 2) * time;
				rect(xx, yy, size, size);

				

				if (levels[0][x][h - y - 1] == 0) {

					float r = 20;
					int segments = (int) (180 / 6);
					float tt = 9 * 2.1f / 2;
					sr.setColor(Color.GRAY);
					for (int i = 0; i < (segments); i++) {
						sr.circle((float) -Math.cos(i / (float) tt) * r + xx + size / 2, (float) Math.sin(i / (float) tt) * r + yy - 5 + size / 1.5f, tt);
					}
					sr.setColor(Color.WHITE);
					for (int i = 0; i < (segments); i++) {
						sr.circle((float) -Math.cos(i / (float) tt) * r + xx + size / 2, (float) Math.sin(i / (float) tt) * r + yy + size / 1.5f, tt);
					}
					setColor(Color.WHITE, Color.GRAY);
					rect(xx + size / 3 / 2, yy + size / 3 / 2, size / 1.5f, 50);
				}

				if (Input.isTouched()) {
					if (new Rectangle(xx, yy, size, size).contains(Input.getX(), Input.getY())) {
						if (levels[0][x][h - y - 1] == 0) continue;
						play = true;
						sel = cc;
					}
				}
				cc++;

			}
		}

		sr.end();

		sb.begin();

		int c = 0;
		font.setFixedWidthGlyphs("1234567890");
		for (int y = h - 1; y >= 0; y--) {
			for (int x = 0; x < w; x++) {
				c++;
				float xx = (x * size * space - (size * space * w) / 2 + ((size * space) - size) / 2) - camera.viewportWidth + camera.viewportWidth * time;
				float yy = (y * size * space - size * space * h / 2) * time;
				if (levels[0][x][h - y - 1] == 0) {
				} else {
					font.draw(sb, c + "", xx + size / 2 - font.getSpaceWidth() * Integer.toString(c).length(), yy + size / space);
				}
			}
		}
		sb.end();

		sr.begin(ShapeType.Filled);
		{

			float t = 20;
			float s = 200 * (time - 0.5f);
			float px = s / 2;
			float py = -380;
			if (s > 0) {
				setColor(Color.WHITE, Color.GRAY);
				line(px, py + s / 2, px - s, py, t);
				line(px, py + s / 2, px, py - s / 2, t);
				line(px, py - s / 2, px - s, py, t);
				sr.triangle(px, py + s / 2, px - s, py, px, py - s / 2);
			}
			if (Input.isTouched()) {
				if (Util.getDistance(px + s / 4, py, Input.getX(), Input.getY()) < s) {
					back = true;
				}
			}

		}
		sr.end();

		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		sr.begin(ShapeType.Filled);
		sr.setColor(new Color(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, playTime));
		sr.rect(-JustDont.WIDTH / 2, -JustDont.HEIGHT / 2, JustDont.WIDTH, JustDont.HEIGHT);
		sr.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		
		if (play) {
			playTime += delta * 2;
			if (playTime >= 1) {
				time = 0;
				back = false;
				play = false;
				System.out.println("Level: " + sel);
				playTime = 0;
				ScreenManager.setSelected("GAME");
				LevelHandler.selected = sel;
			}
		} else if (back) {
			time -= delta;
			if (time < -1f) {
				time = -1;
				ScreenManager.setSelected("MENU");
				back = false;
			}
		} else {
			time += delta;
			if (time >= 1) {
				time = 1;
			}
		}
		

	}

	public boolean back = false;

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
		sr.rectLine(x1, y1 - 10, x2, y2 - 10, width);
		sr.circle(x1, y1 - 10, width / 2);
		sr.circle(x2, y2 - 10, width / 2);
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
		sr.circle(x, y - 10, r);
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
