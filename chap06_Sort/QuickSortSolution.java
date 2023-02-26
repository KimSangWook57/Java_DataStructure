package chap06_Sort;

public class QuickSortSolution {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	// --- 퀵 정렬(비재귀 버전)---//
	// 배열 a, 왼쪽 끝 left, 오른쪽 끝 right
	static void quickSort(int[] a, int left, int right) {
		// 배열을 하나만 써서 값들을 정렬해야 한다.
		// 배열, 포인트 선언 후 값 넣기.
		QuickStack qs = new QuickStack();
		IntPoint ip = new IntPoint(left, right);
		qs.push(ip);

		// 스택이 비어 있지 않은 동안...
		while (qs.isEmpty() != true) {
			// 커서 생성 후 한꺼번에 팝.
			IntPoint temp = qs.pop();
			int pl = left = temp.getX();  // 왼쪽 커서
			int pr = right = temp.getY(); // 오른쪽 커서

			// p = s.pop
			// left = p.getX();
			// right = p.getY();

			// 피벗은 배열의 중간점으로 잡는다.
			int x = a[(left + right) / 2];

			do {
				// pl의 요소가 피벗보다 작으면 pl 포인터 오른쪽으로 이동.
				while (a[pl] < x)		pl++;
				// pr의 요소가 피벗보다 크면 pr 포인터 왼쪽으로 이동.
				while (a[pr] > x)		pr--;
				
				if (pl <= pr)			swap(a, pl++, pr--);
				
			} while (pl <= pr);

			// 정렬 결과 확인.
			for (int num : a)
				System.out.print(" " + num);
			System.out.println();
			
			// 왼쪽 정렬 후 한꺼번에 푸시.
			if (left < pr) 		qs.push(new IntPoint(left, pr));
			
			// 오른쪽 정렬 후 한꺼번에 푸시.
			if (pl < right) 	qs.push(new IntPoint(pl, right));
			
		}
		
	}

	public static void main(String[] args) {
		// 랜덤한 수 10개 입력
		int[] x = new int[10];
		// 10개의 수를 배열에 넣는다.
		for (int ix = 0; ix < 10; ix++) {
			double d = Math.random();
			x[ix] = (int) (d * 20);
		}

		int nx = x.length;

		System.out.println("정렬 전 배열: ");
		for (int num : x) {
			System.out.print(" " + num);
		}
		System.out.println();

		quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬
		
		System.out.println();
		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++) {
			System.out.print(" " + x[i]);
		}
		
	}

}

