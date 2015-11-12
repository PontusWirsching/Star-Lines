package pontus.starlines.level;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import pontus.starlines.core.math.Line;
import pontus.starlines.core.math.Util;
import pontus.starlines.game.Colors;

import com.engine.LEngine;
import com.engine.graphics.Camera;
import com.engine.input.Keyboard;
import com.engine.input.Mouse;

public class Start extends LEngine {

	public Camera camera;

	public ArrayList<Line> walls = new ArrayList<Line>();

	public Start() {
		super(1280, 720, "JustDont - Level editor");
		frame.setResizable(true);

		camera = new Camera(0, 0, 1.0f);

		skipIntro();
		start();
	}

	@Override
	public void resize() {
		super.resize();
		camera.resize();
	}

	@Override
	public void update() {

	}

	int w = 1080;
	int h = 1920;
	double scale = 0.3f;

	int selected = 0;

	boolean t = true;

	Point first = new Point();
	Point second = new Point();

	boolean t2 = true;

	@Override
	public void render() {

		
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_1)) selected = 0;
		if (Keyboard.isKeyPressed(KeyEvent.VK_2)) selected = 1;
		if (Keyboard.isKeyPressed(KeyEvent.VK_3)) selected = 2;
		if (Keyboard.isKeyPressed(KeyEvent.VK_4)) selected = 3;

		camera.draw(g);
		camera.setColor(new Color(Colors.BACKGROUND.r, Colors.BACKGROUND.g, Colors.BACKGROUND.b, Colors.BACKGROUND.a));
		camera.fillRect(0, 0, w * scale, h * scale);
		if (Keyboard.isKeyPressed(KeyEvent.VK_CONTROL)) {
			if (Keyboard.isKeyPressed(KeyEvent.VK_Z) && t2) {
				t2 = false;
				if (walls.size() > 0) {
					walls.remove(walls.size() - 1);
				}
			} else if (!Keyboard.isKeyPressed(KeyEvent.VK_Z) && !t2) {
				t2 = true;
			}
		}

		int snapGrid = 10;

		if (Mouse.getButton() == Mouse.BUTTON_LEFT && t) {
			t = false;

			if (selected == 0) {
				first = new Point(camera.getMouseX(), camera.getMouseY());
				if (!Keyboard.isKeyPressed(KeyEvent.VK_SHIFT)) {
					first = new Point(camera.getMouseX() / snapGrid * snapGrid, camera.getMouseY() / snapGrid * snapGrid);
				}
			}

		} else if (Mouse.getButton() != Mouse.BUTTON_LEFT && !t) {
			t = true;
			if (selected == 0) walls.add(new Line(first.x, first.y, second.x, second.y));
			first = new Point();
			second = new Point();
		}

		if (Mouse.getButton() == Mouse.BUTTON_LEFT) {
			if (selected == 0) {

				second = new Point(camera.getMouseX(), camera.getMouseY());

				if (!Keyboard.isKeyPressed(KeyEvent.VK_SHIFT)) {
					second = new Point(camera.getMouseX() / snapGrid * snapGrid, camera.getMouseY() / snapGrid * snapGrid);
				}

				camera.setColor(Color.BLUE);
				camera.drawLine(first.x, first.y, second.x, second.y);
				camera.drawOval(first.x, first.y, 200, 200);

				double angle = Util.getAngle(first.x, first.y, second.x, second.y);
				camera.drawString("A: " + angle, second.x, second.y - 25);

			}
		}

		camera.setColor(Color.DARK_GRAY);
		for (int x = 0; x < second.x - first.x; x += snapGrid) {
			for (int y = 0; y < second.y - first.y; y += snapGrid) {
				camera.drawRect(x + snapGrid / 2 + first.x, y + snapGrid / 2 + first.y, snapGrid, snapGrid);
			}
		}
		camera.drawRect((camera.getMouseX() / 10 * 10) , (camera.getMouseY() / 10 * 10) , snapGrid, snapGrid);
		
		
		scale = 0.5;

		camera.setColor(Color.GREEN);
		for (Line l : walls) {
			double a = Util.getAngle((int) l.p1.x, (int) l.p1.y, (int) l.p2.x, (int) l.p2.y);
			camera.g.setStroke(new BasicStroke(10));

			camera.setColor(new Color(Colors.WALL_SHADOW.r, Colors.WALL_SHADOW.g, Colors.WALL_SHADOW.b, Colors.WALL_SHADOW.a));
			camera.drawLine((int) (l.p1.x - Math.sin(Math.toRadians(a)) * 5), (int) (l.p1.y - Math.cos(Math.toRadians(a)) * 5) + 5, (int) (l.p2.x + Math.sin(Math.toRadians(a)) * 5), (int) (l.p2.y + Math.cos(Math.toRadians(a)) * 5) + 5);
			camera.fillOval((int) l.p1.x, (int) l.p1.y + 5, 10, 10);
			camera.fillOval((int) l.p2.x, (int) l.p2.y + 5, 10, 10);

			camera.setColor(new Color(Colors.WALL.r, Colors.WALL.g, Colors.WALL.b, Colors.WALL.a));
			camera.drawLine((int) (l.p1.x - Math.sin(Math.toRadians(a)) * 5), (int) (l.p1.y - Math.cos(Math.toRadians(a)) * 5), (int) (l.p2.x + Math.sin(Math.toRadians(a)) * 5), (int) (l.p2.y + Math.cos(Math.toRadians(a)) * 5));
			camera.fillOval((int) l.p1.x, (int) l.p1.y, 10, 10);
			camera.fillOval((int) l.p2.x, (int) l.p2.y, 10, 10);

			camera.g.setStroke(new BasicStroke(1));
			camera.setColor(Color.GREEN);
			camera.drawLine((int) l.p1.x, (int) l.p1.y, (int) l.p2.x, (int) l.p2.y);
		}
	}

	public static void main(String[] args) {
		new Start();
	}

}
