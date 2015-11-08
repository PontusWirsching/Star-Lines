package pontus.starlines.core.math;

/** 
 * 
 * @author Pontus Wirsching
 * @since 2015-11-04
 */
public class Point {

	public float x = 0, y = 0;
	
	public Point() {

	}
	
	public Point(float x, float y) {
		set(x, y);
	}
	
	public void set(float x, float y) {
		setX(x);
		setY(y);
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
