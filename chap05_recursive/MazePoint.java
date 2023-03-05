package chap05_recursive;

//x, y, 방향을 정할 포인터.
public class MazePoint {
	private int ix;
	private int iy;
	private int dir;

	public MazePoint(int x, int y, int dir) {
		ix = x;
		iy = y;
		this.dir = dir;
	}

	public String toString() {
		return "<" + ix + ", " + iy + ", " + dir + ">";
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}
	
	public int getDir() {
		return dir;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}
	
	public void setDir(int dir) {
		this.dir = dir;
	}
}
