package chap05_recursive;

public class EightQueenSolution {

	// 해결 알고리즘 파트.
	public static void SolveQueen(int row, int col, int[][] data) {
		// 배열의 시작 값을 0으로 설정.
		Point p = new Point(0, 0);
		// 값을 담을 스택.
		MyStack s = new MyStack();
		// 0 값을 x와 y에 넣어 준다.
		int x = p.getX();
		int y = p.getY();
		// 내 예시.
		// while문 2개로 행과 열을 빠져나가지 못하게 막는다.
		while (x < row) {
			while (y < col) {
				if (CheckMove(data, x, y)) {
					data[x][y] = 1;
					// SetX, SetY 쓸 수 있을까?
					s.Push(new Point(x, y));
					// 위치 저장 후 열 초기화.
					y = 0;
					// 다음 행 이동.
					x++;
					break;
				}
				y++;
			}
			// 답이 나오지 않으면...
			if (y >= col) {
				// 답이 나오지 못했던 곳의 위치를 저장.
				if (!s.isEmpty()) {
					p = s.Pop();
					x = p.getX();
					y = p.getY();
					data[x][y] = 0;
					// 다음 열 이동.
					y++;
				}
			}
			// 모든 행을 다 채웠다면...
			if (x == row) { 
				PrintQueen(data);
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

	// 가로, 세로, 슬래시, 역슬래시 방향 체크
	// currentRow에 대하여 queen을 (x,y)에 배치 가능하면 true.
	public static boolean CheckMove(int[][] data, int row, int col) {
		// 가로 체크
		if (!checkRow(data, row))
			return false;
		// 세로 체크
		if (!checkCol(data, col))
			return false;
		// 슬래시 체크
		// x++, y-- or x--, y++ where 0<= x,y <= 7
		if (!checkDiagSW(data, row, col))
			return false;
		// 역슬래시 체크
		// x++, y++ or x--, y--
		if (!checkDiagSE(data, row, col))
			return false;
		// 모든 조건이 맞다면...
		return true;
	}

	// 이미 1의 값이 있다는 것은, 놓을 수 없다는 뜻이다.
	// 가로 코드 구현.
	public static boolean checkRow(int[][] data, int x) {
		for (int i = 0; i < data[x].length; i++) {
			if (data[x][i] == 1)
				return false;
		}
		return true;
	}

	// 세로 코드 구현.
	public static boolean checkCol(int[][] data, int y) {
		for (int i = 0; i < data[y].length; i++) {
			if (data[i][y] == 1)
				return false;
		}
		return true;
	}

	// 슬래시 방향 체크.
	public static boolean checkDiagSW(int[][] data, int x, int y) {
		int dx = x;
		int dy = y;
		// 오른쪽 위 체크.
		while (dx >= 0 && dx < data[y].length && dy >= 0 && dy < data[x].length) {
			if (data[dx][dy] == 1)
				return false;
			dx++;
			dy--;
		}
		dx = x;
		dy = y;
		// 왼쪽 아래 체크.
		while (dx >= 0 && dx < data[y].length && dy >= 0 && dy < data[x].length) {
			if (data[dx][dy] == 1)
				return false;
			dx--;
			dy++;
		}
		return true;
	}

	// 역슬래시 방향 체크.
	public static boolean checkDiagSE(int[][] data, int x, int y) {
		int dx = x;
		int dy = y;
		// 오른쪽 아래 체크.
		while (dx >= 0 && dx < data[y].length && dy >= 0 && dy < data[x].length) {
			if (data[dx][dy] == 1)
				return false;
			dx++;
			dy++;
		}
		dx = x;
		dy = y;
		// 왼쪽 위 체크.
		while (dx >= 0 && dx < 8 && dy >= 0 && dy < 8) {
			if (data[dx][dy] == 1)
				return false;
			dx--;
			dy--;
		}
		return true;
	}
	
	// 카운트를 저장할 변수.
	static int num = 0;
	// 모든 결과를 계산 후 프린트할 코드.
	static void PrintQueen(int[][] data) {
		// 카운트 세기.
		System.out.println("Solution " + ++num);
		System.out.println();
		// 루프를 돌며 결과를 찍는다.
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
//	// current row에 대한 다음 배치 가능한 모든 column을 조사하고 move[]에 1로 설정.
//		void NextMove(int[][] move, int row) {
//			// 배치가 가능할 때만 move[]에 1을 넣는다.
//			for (int i = 0; i < 8; i++) {
//				if (move[row][i] == 0) {
//					move[row][i] = 1;
//					return;
//				}
//			}
//			return;
//		}
	
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
