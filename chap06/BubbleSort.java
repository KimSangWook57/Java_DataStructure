package chap06;

//버블 정렬(단순 교환 정렬) (버전 1)

import java.util.Scanner;

class BubbleSort {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// --- 버블 정렬 ---//
	static void bubbleSort(int[] a, int n) {
		// 루프를 돌면서...
		// 루프한 횟수를 저장.
		int cnt = 0;
		// int k = 0;
		for (int i = 0; i < n - 1; i++) {
			// 교환한 횟수를 저장
			int excg = 0;
			// 인접한 두 값을 뒤에서부터 비교.
			for (int j = n - 1; j > i; j--)  {
				// 앞의 값이 뒤의 값보다 크면 위치를 바꾼다.
				if (a[j - 1] > a[j]) {
					swap(a, j - 1, j);
					excg++;
				}
				cnt++;	
			} 
			if (excg == 0) break;
		}
		System.out.println("cnt = " + cnt);
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("버블 정렬(버전 1)");
		System.out.print("요솟수: ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; i++) {
			Double d = Math.random();
			x[i] = (int) (d * 20);
			// System.out.print("x[" + i + "]: ");
			// x[i] = stdIn.nextInt();
		}

		for (int num : x)
			System.out.print(" " + num);
		System.out.println();

		bubbleSort(x, nx); // 배열 x를 단순교환정렬

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.println("x[" + i + "]=" + x[i]);
	}
}