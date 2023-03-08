// 원형 큐 구현하기.
package chap4_스택과큐;

public class Chap4_Queue {
	private int[] data; // 큐용 배열
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	boolean isEmpty; // 큐가 비어있는지 확인

	// --- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyIntQueueException extends RuntimeException {
		public EmptyIntQueueException() {
		}
	}

	// --- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowIntQueueException extends RuntimeException {
		public OverflowIntQueueException() {
		}
	}

	// --- 생성자(constructor) ---//
	public Chap4_Queue(int maxlen) {
		try {
			data = new int[maxlen]; // 큐 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
		front = rear = 0;
		capacity = maxlen;
		isEmpty = true;
	}

	// --- 큐에 데이터를 푸시. ---//
	public void push(int x) throws OverflowIntQueueException {
		System.out.println("데이터 변경 전: front = " + front + " rear = " + rear);
		// 큐가 가득 찼다면...
		if (this.isFull())
			throw new OverflowIntQueueException();
		// 데이터를 먼저 삽입한다.
		data[rear] = x;
		// 다음 칸으로 이동.
		rear = (rear + 1) % capacity;
		// 데이터가 들어왔으므로, 큐는 비어있지 않다.
		isEmpty = false;
		System.out.println("데이터 변경 후: front = " + front + " rear = " + rear);
	}

	// --- 큐에서 데이터를 팝. ---//
	public int pop() throws EmptyIntQueueException {
		System.out.println("데이터 변경 전: front = " + front + " rear = " + rear);
		// 큐가 비었다면...
		if (this.isEmpty())
			throw new EmptyIntQueueException();
		// 데이터를 꺼내고 한 칸 이동.
		int result = data[front];
		front = (front + 1) % capacity;
		// 데이터가 1개만 남아 있는데 꺼내면,
		// front와 rear의 위치가 같아지고, 큐가 비었다는 뜻이다.
		if (front == rear)
			isEmpty = true;
		System.out.println("데이터 변경 후: front = " + front + " rear = " + rear);
		return result;
	}

	// --- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public int peek() throws EmptyIntQueueException {
		// 큐가 비었다면...
		if (this.isEmpty())
			throw new EmptyIntQueueException();
		// 데이터만 꺼낸다.
		System.out.println("데이터 위치 확인: front = " + front + " rear = " + rear);
		return data[front];
	}

	// --- 큐를 비움 ---//
	public void clear() {
		front = rear = 0;
	}

	// --- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
//    public int indexOf(int x) {
//        for (int i = 0; i < rear - front; i++) {
//            int idx = (i + front) % capacity;
//            if (data[idx] == x)                // 검색 성공
//                return idx;
//        }
//        return -1;                            // 검색 실패
//    }

	// --- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		if (front > rear)
			return capacity - front + rear;
		else if (front == rear) {
			if (isEmpty == true) {
				return 0;
			} else {
				return capacity;
			}
		}
		else 
			return rear - front;
	}

	// --- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return front == rear && isEmpty;
	}

	// --- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return front == rear && !isEmpty;
	}

	// --- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() throws EmptyIntQueueException {
		if (this.isEmpty()) {
			throw new EmptyIntQueueException();
		}
		System.out.print("데이터 리스트 : ");
		// 처음 값(front)
		int i = front;
		// 데이터가 다 차있을 때 아무것도 출력하지 않는 문제 해결.
		// 강제로 1칸 진행해서 나머지를 다 찍어내게 만들었다.
		// 끝 값과 나머지가 달라지는 순간(rear)까지 돈다.
		do {
			System.out.print(data[i] + " ");
			i = (i + 1) % capacity;
		} while (i != rear);
		System.out.println();
	}
}
