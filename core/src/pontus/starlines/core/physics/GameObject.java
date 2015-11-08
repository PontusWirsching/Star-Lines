package pontus.starlines.core.physics;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class GameObject {

	/**
	 * Object ID, used to identify object in an array.
	 */
	public String ID = "UNDEFINED";
	
	/**
	 * Object velocity.
	 */
	public Vector2 velocity = new Vector2(0, 0);
	
	/**
	 * Object position.
	 */
	public Vector2 position = new Vector2(0, 0);
	
	/**
	 * Object size.
	 */
	public Vector2 size = new Vector2(0, 0);
	
	/**
	 * All strings in here will be ids of objects and this object will not collide with those objects.
	 */
	public ArrayList<String> clip = new ArrayList<String>();
	
	public GameObject(int x, int y, int width, int height) {
		clip.add("bullet");
		position.set(x, y);
		size.set(width, height);
	}
	
	/**
	 * Applies a force, adds "xv" to velocity.x and "yv" to velocity.y.
	 */
	public void applyForce(float xv, float yv) {
		velocity = velocity.add(new Vector2(xv, yv));
	}
	
	/**
	 * Applies a force.
	 */
	public void applyForce(Vector2 v) {
		velocity = velocity.add(v);
	}
	
}
