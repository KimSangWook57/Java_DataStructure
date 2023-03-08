package chap05_recursive;

//Point 클래스 구현.
public class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	public String toString() {
		return "<" + ix + ", " + iy + ">";
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}
}
/*
class IntPoint {
	private int ix;
	private int iy;
	
	public IntPoint(int ix, int iy) {
		super();
		this.ix = ix;
		this.iy = iy;
	}

	public int getIx() {
		return ix;
	}

	public void setIx(int ix) {
		this.ix = ix;
	}

	public int getIy() {
		return iy;
	}

	public void setIy(int iy) {
		this.iy = iy;
	}

	@Override
	public String toString() {
		return "Point [ix=" + ix + ", iy=" + iy + "]";
	}

}
*/