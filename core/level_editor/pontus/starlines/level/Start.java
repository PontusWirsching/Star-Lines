package pontus.starlines.level;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.engine.LEngine;
import com.engine.graphics.Camera;
import com.engine.input.Keyboard;
import com.engine.input.Mouse;

import pontus.starlines.core.math.Line;

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

	@Override
	public void render() {

		if (Keyboard.isKeyPressed(KeyEvent.VK_1))
			selected = 0;
		if (Keyboard.isKeyPressed(KeyEvent.VK_2))
			selected = 1;
		if (Keyboard.isKeyPressed(KeyEvent.VK_3))
			selected = 2;
		if (Keyboard.isKeyPressed(KeyEvent.VK_4))
			selected = 3;

		camera.draw(g);

		
		if (Mouse.getButton() == Mouse.BUTTON_LEFT && t) {
			t = false;
			
			if (selected == 0) {
				first = new Point(camera.getMouseX(), camera.getMouseY());
			}
			
			
		} else if (Mouse.getButton() != Mouse.BUTTON_LEFT && !t) {
			t = true;
			if (selected == 0)walls.add(new Line(first.x, first.y, second.x, second.y));
		}
		
		if (Mouse.getButton() == Mouse.BUTTON_LEFT) {
			if (selected == 0)second = new Point(camera.getMouseX(), camera.getMouseY());
		}
		
		
		camera.setColor(Color.RED);
		camera.drawRect(0, 0, w * scale, h * scale);
		camera.setColor(Color.BLUE);
		camera.drawLine(first.x, first.y, second.x, second.y);
		camera.setColor(Color.GREEN);
		for (Line l : walls) {
			camera.drawLine((int)l.p1.x, (int)l.p1.y, (int)l.p2.x, (int)l.p2.y);
		}
	}

	public static void main(String[] args) {
		new Start();
	}

}
