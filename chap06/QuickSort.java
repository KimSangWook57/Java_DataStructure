package chap06;

//퀵 정렬

import java.util.Scanner;

class QuickSort {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// --- 퀵 정렬 ---//
	static void quickSort(int[] a, int left, int right) {
		int pl = left; // 왼쪽 커서
		int pr = right; // 오른쪽 커서
		int x = a[(pl + pr) / 2]; // 피벗(가운데 요소)

		do {
			while (a[pl] < x)
				pl++;
			while (a[pr] > x)
				pr--;
			if (pl <= pr)
				swap(a, pl++, pr--);
		} while (pl <= pr);
		
		System.out.println("피벗의 값은 " + x + "입니다.");

	     System.out.println("피벗 이하 그룹 ");
	     for (int i = 0; i <= pl - 1; i++)              // a[0] ～ a[pl - 1]
	         System.out.print(a[i] + " ");
	     System.out.println();

	     if (pl > pr + 1) {
	         System.out.println("피벗과 일치하는 그룹 ");
	         for (int i = pr + 1; i <= pl - 1; i++)    // a[pr + 1] ～ a[pl - 1]
	             System.out.print(a[i] + " ");
	         System.out.println();
	     }

	     System.out.println("피벗 이상 그룹 ");
	     for (int i = pr + 1; i <= right; i++)              // a[pr + 1] ～ a[n - 1]
	         System.out.print(a[i] + " ");
	     System.out.println();
	 
	
		// 왼쪽과 오른쪽을 다시 나누기 위한 재귀
		if (left < pr)	quickSort(a, left, pr);
		if (pl < right)	quickSort(a, pl, right);
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("퀵 정렬");
//		System.out.print("요솟수: ");
//		int nx = stdIn.nextInt();
//		int[] x = new int[nx];
//
//		for (int i = 0; i < nx; i++) {
//			System.out.print("x[" + i + "]: ");
//			x[i] = stdIn.nextInt();
//		}
		int nx = 9;
		int[] x = { 5, 8, 4, 2, 6, 1, 3, 9, 7 };
		quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.println("x[" + i + "]=" + x[i]);
	}
}
