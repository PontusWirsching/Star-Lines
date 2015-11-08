package pontus.starlines.core;

import pontus.starlines.core.graphics.ScreenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Offsets the Gdx.input.getX(), to fit the camera.
 * 
 * @author Pontus Wirsching
 *
 */
public class Input implements InputProcessor {

	static float x, y;
	static float downAtX, downAtY;

	static boolean touched = false;

	public static void update() {

		Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		ScreenManager.getSelected().camera.unproject(input);

		touched = Gdx.input.isTouched();

		x = input.x;
		y = input.y;
		
		if (isTouched() && !t2) {
			t2 = true;
			downAtX = input.x;
			downAtY = input.y;
		} else if (!isTouched() && t2) {
			t2 = false;
		}

	}

	public static float getTouchedDownX() {
		return downAtX;
	}
	
	public static float getTouchedDownY() {
		return downAtY;
	}
	
	static boolean t = false;
	static boolean t2 = false;

	static float sx = 0;
	static float sy = 0;
	static long stouchTime = 0;
	static long sstart = 0;
	static long send = 0;

	public static Swipe getSwipe() {

		if (isTouched() && !t) {
			touchTime = 0;
			t = true;
			sstart = System.currentTimeMillis();
			sx = getX();
			sy = getY();
		} else if (!isTouched() && t) {
			t = false;
			float swipeXDist = getX() - sx;
			float swipeYDist = getY() - sy;
			send = System.currentTimeMillis();
			touchTime = send - sstart;
			return new Swipe(new Vector2(swipeXDist, swipeYDist), touchTime);
		}

		return new Swipe(new Vector2(), -1);
	}

	public static boolean isTouched() {
		return touched;
	}

	public static float getX() {
		return x;
	}

	public static float getY() {
		return y;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	static long touchTime = 0;
	long start = 0;
	long end = 0;

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchTime = 0;
		System.out.println("Touch down: " + pointer);
		start = System.currentTimeMillis();
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		end = System.currentTimeMillis();
		touchTime = end - start;
		return false;
	}

	public static boolean lastTouchWas(long ms) {
		if (touchTime > ms) {
			touchTime = 0;
			return true;
		} else return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
