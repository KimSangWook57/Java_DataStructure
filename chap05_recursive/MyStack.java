package chap05_recursive;

//스택 구현.
class MyStack {
	// 인덱스 top과 배열의 크기 data를 설정.
	private int top;
	private Point[] data;

	public MyStack() {
		// 0과 -1의 차이.
		// 인덱스 0 = 값을 넣고 top을 올린다.
		// 인덱스 -1 = top을 올리고 값을 넣는다. 
		top = 0;
		data = new Point[100];
	}

	// 배열이 비었는지 확인하는 코드.
	public boolean isEmpty() {
		if (top == 0)
			return true;
		else
			return false;
	}

	// Point의 값을 집어넣는 코드.
	public void Push(Point p) {
		data[top++] = p;
	}

	// Point의 값을 꺼내는 코드.
	public Point Pop() {
		return data[--top];
	}
}