package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private int length;
	private Map<Side, Point> triangleMap = new HashMap<Side, Point>();

	public Mountain(int length) {
		super();
		this.length = length;
	}

	/**
	 * Returns the title.
	 * 
	 * @return the title
	 */
	@Override
	public String getTitle() {
		return "Mountain";
	}

	/**
	 * Draws the fractal.
	 * 
	 * @param turtle the turtle graphic object
	 */
	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(turtle.getWidth() / 2.0 - length / 2.0, turtle.getHeight() / 2.0 + Math.sqrt(3.0) * length / 4.0);
		// Point a = new Point((int) turtle.getX(), (int) turtle.getX());
		Point a = new Point(100, 500);
		Point b = new Point(800, 550);
		Point c = new Point(368, 20);
		fractalMountain(turtle, order, length, a, b, c, 50);
	}

	/*
	 * Reursive method: Draws a recursive line of the triangle.
	 */
	private void fractalMountain(TurtleGraphics turtle, int order, double length, Point a, Point b, Point c,
			double dev) {
		if (order == 0) {
			// "rita en linje med längden length och riktningen alpha"
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			int offset = (int) randFunc(dev);
			Point ab = createPoint(a, b, offset);
			Point bc = createPoint(b, c, offset);
			Point ca = createPoint(c, a, offset);
			fractalMountain(turtle, order - 1, length / 2, ab, bc, ca, dev / 2);
			fractalMountain(turtle, order - 1, length / 2, ab, bc, b, dev / 2);
			fractalMountain(turtle, order - 1, length / 2, c, bc, ca, dev / 2);
			fractalMountain(turtle, order - 1, length / 2, ab, a, ca, dev / 2);
		}

	}

	private Point createPoint(Point a, Point b, int offset) {
		Side temp = new Side(a, b);

		if (triangleMap.containsKey(temp)) {
			Point p = triangleMap.get(temp);
			triangleMap.remove(temp);
			return p;
		} else {
			int x = (a.getX() + b.getX()) / 2;
			int y = (a.getY() + b.getY()) / 2 + offset;
			Point p = new Point(x, y);
			triangleMap.put(temp, p);
			return p;
		}

	}

	public static double randFunc(double dev) {
		double t = dev * Math.sqrt(-2 * Math.log(Math.random()));
		if (Math.random() < 0.5) {
			t = -t;
		}
		return t;
	}
}