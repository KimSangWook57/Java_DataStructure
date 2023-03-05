package chap05_recursive;

public class MazeSolution {
	
	static void solveMaze(int[][] input, int m, int p) {
		// 스택 객체 생성.
		MazeStack s = new MazeStack(100);
		
		// 미로 생성.
		int maze[][] = new int[m + 2][p + 2];
		int mark[][] = new int[m + 2][p + 2];
		
		// 미로 초기화 코드.
		for (int i = 0; i < m + 2; i++) {
			for (int j = 0; j < p + 2; j++) {
				// 미로 바깥쪽은 1로 초기화.
				if ((i == 0) || (j == 0) || (i == m + 1) || (j == p + 1)) {
					maze[i][j] = 1;
					mark[i][j] = 1;
				}
				// 미리 설정된 값으로 미로 생성.
				// 추적 코드 0으로 초기화.
				else {
					maze[i][j] = input[i - 1][j - 1];
					mark[i][j] = 0;
				};
				
			}
			
		}
		
		// moves의 방향을 선언.
		int[][] moves = {
				{0,1},  	//E(0)
				{1,1},  	//SE(1)
				{1,0},  	//S(2)
				{1,-1}, 	//SW(3)
				{0,-1}, 	//W(4)
				{-1,-1}, 	//NW(5)
				{-1,0}, 	//N(6)
				{-1,1}, 	//NE(7)
		};
		
		// 시작점 (1,1)을 표시, 지나온 길 확인용(백트래킹)
		mark[1][1] = 1;
		// 시작점 확인. (출력시)
		maze[0][0] = 1;
		// 정답이 나올때까지 루프를 돌기 위한 조건.
		boolean check = false;
		// 스택과 포인터 생성.
		MazeStack stack = new MazeStack(1000);
		MazePoint p1 = new MazePoint (1, 1, 0); // 0 == E
		stack.push(p1);
		
		// check 코드 추가.
		// 스택이 비어 있지 않고, 아직 나오지 못했다면...
		while (!stack.isEmpty() && !check) { 
			// 임시 스택 설정.
			p1 = stack.pop(); 
			// 임시 스택의 값을 가져오는 임시 포인터 설정.
			int i = p1.getX();
			int j = p1.getY();
			int d = p1.getDir();
			// 방향 체크를 위한 루프 코드.
			while (d < 8) { 
				
				int g = i + moves[d][0];
				int h = j + moves[d][1];
				// System.out.println("i = " + i + ", j = " + j);
				// System.out.println("g = " + g + ", h = " + h);
				
				// 갈 수 있는 길이고, 아직 가보지 않았다면...
				if ((maze[g][h] == 0) && (mark[g][h] != 1)) { 
					// 새로운 곳으로 이동을 위해 현재 위치에 왔었음을 표시.
					mark[g][h] = 1;
					// 또한 현재 위치에서 방향을 체크한다.
					p1 = new MazePoint (i, j, d);
					stack.push(p1);
					// 표시를 출력할 코드.
					maze[i][j] = 2;
					// moves to (g,h), 0 == E
					i = g; 
					j = h; 
					d = 0; 
				}
				// 갈 수 없거나, 갈 수 있더라도 이미 갔던 길이라면 다음 방향 체크.
				else d++;
				
				// 출구에 도착하면, 경로를 출력한다.
				if ((i== m) && (j == p)) { 
					p1 = new MazePoint (i, j, d);
					s.push(p1);
					// 도착점 표시.
					maze[i][j] = 3;
					check = true;
	
				}
			}
		}
	
		// 답을 찾았다면, 정답을 출력하는 코드.
		if(check) {
			System.out.println("미로 문제 해결 완료.");
			System.out.println("■ 경로 시도를 거쳐 ▲에 도착했습니다.");
			System.out.println();
			for(int i=1; i<13; i++) {
				for(int j=1; j <16; j++) {
					if (maze[i][j] == 3) 		System.out.print("▲");
					else if (maze[i][j] == 2) 	System.out.print("■");
					else 						System.out.print(maze[i][j]);
				}
				System.out.println();
			}
			
		} 
		else System.out.println("탈출 경로를 찾지 못했습니다.");
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
		
		// 해결 알고리즘 실행.
		solveMaze(input, 12, 15);

	}
	
}