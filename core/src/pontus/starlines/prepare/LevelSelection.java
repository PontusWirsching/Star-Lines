package pontus.starlines.prepare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import pontus.starlines.JustDont;
import pontus.starlines.core.graphics.Screen;
import pontus.starlines.game.Colors;

public class LevelSelection extends Screen {

	public BitmapFont font = new BitmapFont(Gdx.files.internal("Montserrat-Regular.ttf"));
	
	
	public LevelSelection() {
		super("LEVEL_SELECTION");
	}

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

			int w = 4;
			int h = 6;
			int[][] levels = new int[w][h];
			float space = 1.3f;
			float size = JustDont.WIDTH / w / space;
			sb.begin();
			setColor(Colors.OBJECTS, Colors.OBJECTS_SHADOW);
			for (int x  = 0; x < w; x++) {
				for (int y  = 0; y < h; y++) {
					rect(x * size * space - (size * space * w) / 2 + ((size * space) - size) / 2, y * size * space - size * space * h / 2, size, size);
					font.draw(sb, "1", x, y);
				}
			}
			sb.end();
			
			
		}
		sr.end();
	}

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
		sr.rect(x, y - 10, width, height);
		sr.setColor(color);
		sr.rect(x, y, width, height);

	}
}
