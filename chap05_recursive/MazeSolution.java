package chap05_recursive;

import java.util.Stack;

class items {
	int x;
	int y;
	int dir;

	public items(int a, int b, int c) {
		x = a;
		y = b;
		dir = c;
	}
}

// 방향을 정하는 클래스.
// {N, NE, E, SE, S, SW, W, NW};
// {0, 1, 2, 3, 4, 5, 6, 7}

class offsets {
	
	int a, b;
}

public class MazeSolution {
	// 전역 변수 선언.
	static int row = 12;
	static int col = 15;
	
	static offsets[] moves = new offsets[8];
	static int maze[][] = new int[row + 2][col + 2];
	static int mark[][] = new int[row + 2][col + 2];
	
	static void solveMaze(int m, int p) {
	// row = m, col = p
	// 시작점 (1,1)을 표시.
		mark[1][1] = 1;
		Stack<items> stack = new Stack<items>();
		items temp = new items(0, 0, 0);
		temp.x = 1; 
		temp.y = 1; 
		temp.dir = 2; // 2 == E
		stack.push(temp);
		
		// check 코드 추가.
		while (!stack.isEmpty()) { // stack not empty...
			
			temp = stack.pop(); // unstack
			int i = temp.x;
			int j = temp.y;
			int d = temp.dir;
			// pop 횟수를 세는 코드.
			System.out.println();
			
			while (d < 8) { // 방향 루프
				
				int g = i + moves[d].a;
				int h = j + moves[d].b;
				System.out.println("i = " + i + ", j = " + j);
				System.out.println("g = " + g + ", h = " + h);
				if ((g == m) && (h == p)) { // reached exit
											// output solveMaze
					System.out.println(stack);
					System.out.println("the term near the exit: " + i + " " + j);
					System.out.println("exit: " + m + " " + p);
					return;	
				}
				if ((maze[g][h] == 0) && (mark[g][h] == 0)) { // new position
					mark[g][h] = 1;
					// push the old temp to the stack, but the direction changes.
					// Because the neighbor in the direction of d has been checked.
					temp.x = i;  
					temp.y = j; 
					temp.dir = d + 1;
					stack.push(temp); // stack it
					i = g; j = h; d = 0; // moves to (g,h), 0 == N
				}
				else d++; // 다음 방향 체크.
			}
			// 여기에 mark 위치 초기화?
		}
		System.out.println("no solveMaze in maze ");
	}

	public static void main(String[] args) {
		// 주어진 미로.
		int input[][] = new int[][] 
		{
			{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
			{ 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
			{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
			{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
			{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
			{ 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 },
		};
		// moves의 방향을 선언.
		for(int i = 0; i < 8; i++) {
			moves[i] = new offsets();
		}
		
		moves[0].a = -1; 	moves[0].b = 0;
		moves[1].a = -1; 	moves[1].b = 1;
		moves[2].a = 0; 	moves[2].b = 1;
		moves[3].a = 1; 	moves[3].b = 1;
		moves[4].a = 1; 	moves[4].b = 0;
		moves[5].a = 1; 	moves[5].b = -1;
		moves[6].a = 0; 	moves[6].b = -1;
		moves[7].a = -1; 	moves[7].b = -1;

		// 미로 초기화 코드.
		for (int i = 0; i < row + 2; i++) {
			for (int j = 0; j < col + 2; j++) {
				// 미로 바깥쪽은 1로 초기화.
				if ((i == 0) || (j == 0) || (i == row +1) || (j == col +1))
					maze[i][j] = 1;
				// 미리 설정된 값으로 미로 생성.
				else {
					maze[i][j] = input[i - 1][j - 1];
				};
				// 추적 코드 0으로 초기화.
				mark[i][j] = 0;
			}
		}
		// 미로를 찍는 코드.
		for (int i = 0; i <= row + 1; i++) {
			for (int j = 0; j <= col + 1; j++)
				System.out.print(maze[i][j] + " ");
			System.out.println();
		}
		
		solveMaze(row, col);

	}
}
