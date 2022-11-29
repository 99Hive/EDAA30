package mountain;

public class Side {

	private Point p1, p2;

	public Side(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}

	@Override
	public boolean equals(Object a) {
		Side b = (Side) a;
		return (p1 == b.p1 && p2 == b.p2 || p1 == b.p2 && p2 == b.p1);
	}
}