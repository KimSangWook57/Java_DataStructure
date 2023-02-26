package chap06_Sort;

class QuickStack {

	private int top; // 스택 포인터
	private IntPoint data[]; // 스택용 배열

	public QuickStack() {
		top = 0;
		data = new IntPoint[100]; // 배열 크기 설정.
	}

	public void push(IntPoint p) {
		data[top++] = p;
	}

	public IntPoint pop() {
		return data[--top];
	}

	public boolean isEmpty() {
		if (top == 0)
			return true;
		else
			return false;

	}
}