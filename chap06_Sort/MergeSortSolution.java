package chap06_Sort;

import chap06_Sort.PhyscExamSort.PhyscData;

public class MergeSortSolution {
	
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	// 좌우로 나누어진 배열들을 합치는 과정.
	static void MergeSort(PhyscData[] a) {
		int n = a.length;
		
		// 배열을 크기가 1(더 이상 나눌 수 없음)로 나눈 후, 
		// 원 배열의 크기에 도착할 때까지 한 쌍씩 병합하기.
        for (int size = 1; size < n; size *= 2) {
        	// 왼쪽 배열을 두 배씩 늘리는 코드.
            for (int left = 0; left < n - size; left += 2 * size) {
                int mid = left + size - 1;
                // 왼쪽 배열의 크기가 오른쪽 배열의 크기보다 커지면 인덱스를 벗어나 버린다.
                // 그래서 왼쪽 배열의 크기를 파악할 위 for문의 두 조건의 값을 비교해서,
                // 오른쪽 배열의 크기에 넣을 수 있도록 한다.
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(a, left, mid, right);
            }
        }
    }
		
	// --- 병합 정렬(재귀 버전)---//
//	static void MergeSort(PhyscData[] a, int left, int right) {
//		// 왼쪽과 오른쪽을 나누어서, 2개의 배열들로 정렬하면서 계속 나누고,
//		// 더 이상 나눌 수 없으면 정렬하면서 합쳐준다.
//		int mid = (left + right) / 2;
//		if (left == right)
//			return;
//		
//		MergeSort(a, left, mid);
//		MergeSort(a, mid + 1, right);
//		merge(a, left, mid, mid + 1, right);
//		return;
//	}
	
	// --- 병합 정렬(비재귀 버전)---//
	static void merge(PhyscData[] a, int left, int mid, int right) {
		int n1 = mid - left + 1;
        int n2 = right - mid;

        // 메인 데이터를 나눌 두 배열 생성.
        PhyscData[] L = new PhyscData[n1];
        PhyscData[] R = new PhyscData[n2];

        // 메인 데이터를 두 곳에 나누어 저장.
        // 왼쪽 배열에 저장.
        for (int i = 0; i < n1; i++) {
            L[i] = a[left + i];
        }
        // 오른쪽 배열에 저장.
        for (int j = 0; j < n2; j++) {
            R[j] = a[mid + 1 + j];
        }

        // 정렬된 두 배열 다시 합치기.
        // i, j, k는 각각 왼쪽 반, 오른쪽 반, 전체 배열의 가장 왼쪽을 가리킬 포인터다.
        int i = 0;
        int j = 0;
        int k = left;
        // 제일 작게 나눈 배열부터 차례차례 올라오며 체크한다.
        while (i < n1 && j < n2) {
        	// 왼쪽이 더 작음.
            if (L[i].compareTo(R[j]) <= 0) {
                a[k] = L[i];
                i++;
            } 
            // 오른쪽이 더 작음.
            else {
                a[k] = R[j];
                j++;
            }
            k++;
        }
        // 왼쪽 하위 배열에 남아있는 데이터 추가.
        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }
        // 오른쪽 하위 배열에서 남아 있는 데이터 추가.
        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }
	// 메인 페이지.
	public static void main(String[] args) {
		// 데이터 입력.
		PhyscData[] x = { 
				new PhyscData("강민하", 162, 0.3),
				new PhyscData("김찬우", 173, 0.7),
				new PhyscData("박준서", 171, 2.0), 
				new PhyscData("유서범", 171, 1.5), 
				new PhyscData("이수연", 168, 0.4),
				new PhyscData("장경오", 171, 1.2), 
				new PhyscData("황지안", 169, 0.8), 
				};

		MergeSort(x); // 배열 x를 병합 정렬.
		System.out.println("오름차순으로 정렬했습니다.");
		System.out.println("■ 신체검사 리스트 ■");
		System.out.println(" 이름     키  시력");
		System.out.println("------------------");
		for (int i = 0; i < x.length; i++)
			System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
