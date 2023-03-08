package chap05_recursive;

public class EightQueenSolution2 {

	public static boolean checkMove(int x, int y, int[][] data) {
		// 가로 체크
		if (!checkRow(x, data))
			return false;
		// 세로 체크
		if (!checkCol(y, data))
			return false;
		// 슬래시 체크
		if (!checkDiagSW(x, y, data))
			return false;
		// 역슬래시 체크
		if (!checkDiagSE(x, y, data))
			return false;
		// 모든 조건이 맞다면...
		return true;
	}

	// 이미 1의 값이 있다는 것은, 놓을 수 없다는 뜻이다.
	// 가로 코드 구현.
	public static boolean checkRow(int x, int[][] data) {
		for (int i = 0; i < data[x].length; i++) {
			if (data[x][i] == 1)
				return false;
		}
		return true;
	}

	// 세로 코드 구현.
	public static boolean checkCol(int y, int[][] data) {
		for (int i = 0; i < data[y].length; i++) {
			if (data[i][y] == 1)
				return false;
		}
		return true;
	}

	// 슬래시 방향 체크.
	public static boolean checkDiagSW(int x, int y, int[][] data) {
		int dx = x;
		int dy = y;
		// 오른쪽 위 체크.
		while (dx >= 0 && dx < data[x].length && dy >= 0 && dy < data[y].length) {
			if (data[dx][dy] == 1)
				return false;
			dx++;
			dy--;
		}
		// 위치 체크가 끝났다면, 시작 위치로 돌아온다.
		dx = x;
		dy = y;
		// 왼쪽 아래 체크.
		while (dx >= 0 && dx < data[x].length && dy >= 0 && dy < data[y].length) {
			if (data[dx][dy] == 1)
				return false;
			dx--;
			dy++;
		}
		return true;
	}

	// 역슬래시 방향 체크.
	public static boolean checkDiagSE(int x, int y, int[][] data) {
		int dx = x;
		int dy = y;
		// 오른쪽 아래 체크.
		while (dx >= 0 && dx < data[y].length && dy >= 0 && dy < data[x].length) {
			if (data[dx][dy] == 1)
				return false;
			dx++;
			dy++;
		}
		// 위치 체크가 끝났다면, 시작 위치로 돌아온다.
		dx = x;
		dy = y;
		// 왼쪽 위 체크.
		while (dx >= 0 && dx < data[y].length && dy >= 0 && dy < data[x].length) {
			if (data[dx][dy] == 1)
				return false;
			dx--;
			dy--;
		}
		return true;
	}

	// 해결 알고리즘 파트.
	public static void SolveQueen(int row, int col, int[][] data) {
		// 배열의 시작 값을 0으로 설정.
		Point p = new Point(0, 0);
		// 값을 담을 스택.
		MyStack s = new MyStack();
		// 0 값을 x와 y에 넣어 준다.
		int x = p.getX();
		int y = p.getY();
		// 학우님 예시.

		while (x < row) {
			while (y < col) {
				if (checkMove(x, y, data)) {
					data[x][y] = 1;
					// SetX, SetY 쓸 수 있을까?
					s.Push(new Point(x, y));
					// 위치 저장 후 y 초기화.
					y = 0;
					// 다음 행 이동.
					x++;
					break;
				}
				// 세로열 체크.
				y++;
			}
			// 답이 나오지 못했던 곳의 위치를 저장.
			if (y >= col) {
				// 답이 나오지 않으면...
				if (!s.isEmpty()) {
					p = s.Pop();
					x = p.getX();
					y = p.getY();
					data[x][y] = 0;
					// 다음 열 이동.
					y++;
				}
			}
			if (x == row) { // 모든 행을 다 채운 경우
				PrintQueen(row, col, data);
				if (!s.isEmpty()) {
					p = s.Pop();
					x = p.getX();
					y = p.getY();
					data[x][y] = 0;
					y++;
				}
			}
		}
	}

	static int num = 0;

	public static void PrintQueen(int row, int col, int[][] data) {
		System.out.println(++num);
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				System.out.print(data[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	// 메인 페이지는 문제를 해결하는 코드만 남긴다.
	public static void main(String[] args) {
		int row = 8;
		int col = 8;
		int[][] data = new int[row][col];
		// 배열 초기화
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				data[i][j] = 0;
			}
		}
		// 배열 전달 및 출력: 8개의 queen 배치.
		// 92개의 해를 구해 0과 1로 보여주기.
		SolveQueen(row, col, data);

	}

}