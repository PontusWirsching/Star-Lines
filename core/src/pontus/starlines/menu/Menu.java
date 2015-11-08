package pontus.starlines.menu;

import pontus.starlines.JustDont;
import pontus.starlines.core.Input;
import pontus.starlines.core.graphics.Screen;
import pontus.starlines.core.graphics.ScreenManager;
import pontus.starlines.core.math.Util;
import pontus.starlines.game.Colors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Menu extends Screen {

	public Menu() {
		super("MENU");
	}

	public float time = 0;

	Texture googleplus = new Texture("googleplus.png");
	Texture facebook = new Texture("facebook.png");
	Texture gmail = new Texture("gmail.png");

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
		if (play) {
			time -= delta * 2;
			if (time <= 0) {
				time = 0;
				ScreenManager.setSelected("GAME");
			}
		} else {
			time += delta * 2;
			if (time >= 2) time = 2;
		}
		if (time > 0){
			float t = 25;

			setColor(Colors.OBJECTS, Colors.OBJECTS_SHADOW);

			float jx = -150;
			float jy = 200;
			line(jx, jy, jx, jy + 50 * time, t);
			float r = 40;
			int segments = (int) (180 / 3);
			float tt = 9 * 2.1f;
			sr.setColor(Colors.OBJECTS_SHADOW);
			for (int i = 0; i < (segments * time / 2); i++) {
				sr.circle((float) Math.cos(i / (float) tt) * r + jx - 40, (float) Math.sin(-i / (float) tt) * r + jy - 10, t / 2);
			}
			sr.setColor(Colors.OBJECTS);
			for (int i = 0; i < (segments * time / 2); i++) {
				sr.circle((float) Math.cos(i / (float) tt) * r + jx - 40, (float) Math.sin(-i / (float) tt) * r + jy, t / 2);
			}

			float ux = -35;
			float uy = 195;
			line(ux - 80f, uy, ux - 80f, uy + 25 * time, t);
			sr.setColor(Colors.OBJECTS_SHADOW);
			for (int i = 0; i < (segments * time / 2); i++) {
				sr.circle((float) -Math.cos(i / (float) tt) * r + ux - 40, (float) Math.sin(-i / (float) tt) * r + uy - 10, t / 2);
			}
			sr.setColor(Colors.OBJECTS);
			for (int i = 0; i < (segments * time / 2); i++) {
				sr.circle((float) -Math.cos(i / (float) tt) * r + ux - 40, (float) Math.sin(-i / (float) tt) * r + uy, t / 2);
			}
			line(ux - 1, uy - 45, ux - 1, uy - 45 + 48 * time, t);

			float sx = 70;
			float sy = 230;

			r = 25;
			sr.setColor(Colors.OBJECTS_SHADOW);
			for (int i = 0; i < ((segments + 20) * time / 2); i++) {
				sr.circle((float) -Math.cos(i / (float) tt - Math.toRadians(90)) * r + sx - 40, (float) Math.sin(-i / (float) tt - Math.toRadians(90)) * r + sy - 10, t / 2);
				sr.circle((float) -Math.cos(i / (float) tt + Math.toRadians(90)) * r + sx - 40, (float) Math.sin(-i / (float) tt + Math.toRadians(90)) * r + sy - r * 2 - 10, t / 2);
			}
			sr.setColor(Colors.OBJECTS);
			for (int i = 0; i < ((segments + 20) * time / 2); i++) {
				sr.circle((float) -Math.cos(i / (float) tt - Math.toRadians(90)) * r + sx - 40, (float) Math.sin(-i / (float) tt - Math.toRadians(90)) * r + sy, t / 2);
				sr.circle((float) -Math.cos(i / (float) tt + Math.toRadians(90)) * r + sx - 40, (float) Math.sin(-i / (float) tt + Math.toRadians(90)) * r + sy - r * 2, t / 2);
			}

			float tx = 125;
			float ty = 150;
			float th = 150;
			line(tx, ty + th, tx + 30 * time, ty + th, t);
			line(tx, ty + th, tx - 120 * time, ty + th, t);
			line(tx, ty, tx, ty + th / 2 * time, t);

			float dx = -225;
			float dy = -50;

			sr.setColor(Colors.OBJECTS_SHADOW);
			r = 75;
			for (int i = 0; i < ((segments + 0) * time / 2); i++) {
				sr.circle((float) (1.3f * -Math.cos(i / (float) tt + Math.toRadians(90)) * r + dx - 0), (float) (1.0f * Math.sin(-(i) / (float) tt + Math.toRadians(90)) * r + dy + 75) - 10, t / 2);
			}
			sr.setColor(Colors.OBJECTS);
			for (int i = 0; i < ((segments) * time / 2); i++) {
				sr.circle((float) (1.3f * -Math.cos(i / (float) tt + Math.toRadians(90)) * r + dx - 0), (float) (1.0f * Math.sin(-(i) / (float) tt + Math.toRadians(90)) * r + dy + 75), t / 2);
			}
			lineNoShadow(dx, dy, dx, dy + 75 * time, t);

			float ox = -5;
			float oy = 0;

			r = 50;
			sr.setColor(Colors.OBJECTS_SHADOW);
			for (int i = 0; i < ((segments * 2) * time / 2); i++) {
				sr.circle((float) -Math.cos(i / (float) tt - Math.toRadians(90)) * r + ox - 40, (float) Math.sin(-i / (float) tt - Math.toRadians(90)) * r + oy - 10, t / 2);
			}
			sr.setColor(Colors.OBJECTS);
			for (int i = 0; i < ((segments * 2) * time / 2); i++) {
				sr.circle((float) -Math.cos(i / (float) tt - Math.toRadians(90)) * r + ox - 40, (float) Math.sin(-i / (float) tt - Math.toRadians(90)) * r + oy, t / 2);
			}

			float nx = 120;
			float ny = 5;

			r = 40;
			sr.setColor(Colors.OBJECTS_SHADOW);
			for (int i = 0; i < (segments * time / 2); i++) {
				sr.circle((float) -Math.cos(i / (float) tt) * r + nx - 40, (float) Math.sin(i / (float) tt) * r + ny - 10, t / 2);
			}
			sr.setColor(Colors.OBJECTS);
			for (int i = 0; i < (segments * time / 2); i++) {
				sr.circle((float) -Math.cos(i / (float) tt) * r + nx - 40, (float) Math.sin(i / (float) tt) * r + ny, t / 2);
			}
			line(nx, ny - 60, nx, ny + 25 * time - 60, t);
			line(nx - 80f, ny - 60, nx - 80f, ny - 60 + 55 * time - 0, t);

			float ttx = 175;
			float tty = -55;
			th = 100;
			line(ttx, tty + th, ttx + 15 * time, tty + th, t);
			line(ttx, tty + th, ttx - 15 * time, tty + th, t);
			line(ttx, tty, ttx, tty + 150 / 2 * time, t);

			float s = 200 * (time - 1.5f);
			float px = -s / 4;
			float py = -200;
			if (s > 0) {
				setColor(Color.WHITE, Color.GRAY);
				line(px, py + s / 2, px + s, py, t);
				line(px, py + s / 2, px, py - s / 2, t);
				line(px, py - s / 2, px + s, py, t);
				sr.triangle(px, py + s / 2, px + s, py, px, py - s / 2);
				sr.setColor(Color.RED);

			}
			
			if (Input.isTouched()) {
				if (Util.getDistance(px + s / 4, py, Input.getX(), Input.getY()) < s) {
					play = true;
				}
			}

		}
		

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			time = 0;
		}

		sr.end();

		sb.begin();
		{

			float gpx = (camera.viewportWidth / 2 - 140) * (time - 1) - camera.viewportWidth / 2 - 50;
			float gpy = -400;
			float ss = 50;
			sb.draw(googleplus, gpx - ss, gpy - ss, ss * 2, ss * 2);

			float fbx = (camera.viewportWidth / 2 + 50) * (time - 1) - camera.viewportWidth / 2 - 50;
			float fby = -400;
			float fbs = 50;
			sb.draw(facebook, fbx - fbs, fby - fbs, fbs * 2, fbs * 2);

			float gmx = (camera.viewportWidth / 2 + 240) * (time - 1) - camera.viewportWidth / 2 - 50;
			float gmy = -400;
			float gms = 100;
			sb.draw(gmail, gmx - gms / 2, gmy - gms / 2, gms, gms);
		}
		sb.end();

	}

	private boolean play = false;

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
