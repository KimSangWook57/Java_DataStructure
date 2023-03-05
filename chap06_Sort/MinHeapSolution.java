package chap06_Sort;

import java.util.Scanner;

// 힙 정렬 클래스 구현.
// DeleteMax를 DeleteMin으로 바꿔서 정렬을 누르면 오름차순(작은 숫자부터)으로 출력하도록 하기.

class Element {
	public int key;

	public int getElement() {
		return key;
	}

	public Element(int key) {
		this.key = key;
	}
}

interface MinPQ {

	public void Insert(Element x);

	public Element DeleteMin(Element x);
}

class MinHeap implements MinPQ {
	
	final int heapSize = 1000;
	
	private Element[] heap;
	private int n; // current size of MaxHeap
	private int MaxSize; // Maximum allowable size of MaxHeap
	
	public MinHeap(int sz) {
		MaxSize = sz;
		n = 0;
		heap = new Element[MaxSize + 1]; // Don't want to use heap[0]
	}

	public void display() {
		int i;
		System.out.println();
		for (i = 1; i <= n; i++)
			System.out.print("  (" + i + ", " + heap[i].key + ") ");
		System.out.println();
		System.out.println();
	}

	public void Insert(Element x) {
		int i;
		// 힙이 가득 찼음.
		if (n == MaxSize) {
			HeapFull();
			return;
		}
		// 힙의 0번째 인덱스를 쓰지 않기 위한 코드.
		// n++은 힙의 1번째 인덱스부터 보는 코드이자, 한칸씩 이동하기 위한 코드다.
		n++;
		for (i = n; i >= 1; i /= 2) {
			// i = n = 1은 루트에 도달했다는 뜻이다.
			if (i == 1)
				break;
			// 부모(x)의 값이 자식의(heap[i/2]) 값보다 크다면 루프를 그만두고 값을 넣어야 한다.
			if (x.key >= heap[i / 2].key)
				break;
			// 아니라면 부모의 값과 자식의 값을 바꿔주고 이동한다.
			heap[i] = heap[i / 2];
		}
		// 루프를 끝냈을 때 정해진 위치에 값을 넣는다.
		heap[i] = x;

	}
	// 가장 작은 값을 루트에서 꺼내야 한다.
	// 제거 후에는 배열의 제일 마지막 요소를 루트에 넣은 뒤
	// 다시 정렬한다.
	public Element DeleteMin(Element x) {

		int i, j;
		// n이 0이라는 것은 힙이 비었다는 것이다.
		if (n == 0) {
			HeapEmpty();
			// 새 요소의 값은 0으로 초기화.
			Element elm = new Element(0);
			return elm;
		}
		// heap의 처음값(root)은 x가 된다.(빠질 값)
		x = heap[1];
		// heap의 끝값은 k가 된다.(
		Element k = heap[n];
		// 한 칸 이동.
		n--;

		for (i = 1, j = 2; j <= n; i = j, j *= 2) {
			if (j < n)
				// 두 자식 노드 중 작은 값으로 내려간다.
				if (heap[j].key > heap[j + 1].key)
					j++;
			// heap의 끝값이었던 값이 j가 가리키는 값보다 작다면, 그 자리에 들어가야 할 것이다.
			if (k.key <= heap[j].key)
				break;
			// j에 있던 값이 root에 가게 된다.
			heap[i] = heap[j];
		}
		// heap의 i 위치에 있었던 값을 root에 올렸기 때문에, k값은 i의 위치에 넣어 준다.
		heap[i] = k;
		// 미리 옮겨놨던 처음 값을 표시한 후 빼준다. 
		return x;
	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}

public class MinHeapSolution {

	public static void main(String[] args) {
		int select = 0; 						// 메뉴 선택 코드.
		int maxnum = 20; 						// 힙의 크기 설정 코드.
		@SuppressWarnings("resource")
		Scanner stdIn = new Scanner(System.in);
		MinHeap heap = new MinHeap(maxnum); 		// 힙 사이즈 생성자.
		Element ele = null;
		final int count = 5; 					// 한 번에 얼만큼 넣고 뺄 것인가? 
		int[] x = new int[count]; 				// 힙에다 요소를 넣을 배열.

		Element deletedEle = null;

		do {
			System.out.print("Min Tree. Select: insert(1), display(2), delete(3), sort(4), exit(5) => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1:
				// count로 설정한 개수만큼 랜덤한 수를 넣는다.
				for (int i = 0; i < count; i++) {
					x[i] = (int) (Math.random() * 30);
					heap.Insert(new Element(x[i]));
				}
				System.out.println();
				System.out.println("랜덤 숫자 " + count + "개 삽입 완료.");
				System.out.println();
				break;
			case 2:
				// 배열에 남은 코드를 표시.
				heap.display();
				break;
			case 3:
				// 첫 값을 지우는 코드.
				deletedEle = heap.DeleteMin(ele);
				if (deletedEle != null) {
					System.out.println("deleted element: " + deletedEle.key);
				}
				System.out.print("current min heap: ");
				heap.display();
				break;
			case 4:
				// 값을 빼서 표시 및 정렬해주는 코드.
				for (int j = 0; j < count; j++) {
					deletedEle = heap.DeleteMin(ele);
					x[j] = deletedEle.getElement();
				}
				System.out.println(count + "개 정렬 및 추출 완료.");
				for (int num : x)
					System.out.println(" " + num);
				break;
			case 5:
				return;

			}

		} while (select < 5);

		return;
	}
}
