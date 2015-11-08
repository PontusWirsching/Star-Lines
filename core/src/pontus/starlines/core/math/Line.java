package pontus.starlines.core.math;

public class Line {

	public Point p1;
	public Point p2;

	public Line(float x1, float y1, float x2, float y2) {
		p1 = new Point(x1, y1);
		p2 = new Point(x2, y2);
	}
	
	public float getX1() {
		return p1.x;
	}
	
	public float getY1() {
		return p1.y;
	}
	
	public float getX2() {
		return p2.x;
	}
	
	public float getY2() {
		return p2.y;
	}
	
}
