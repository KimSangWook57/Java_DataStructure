package chap08;

// 단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계
// 코드 정비 필요.

import java.util.Scanner;

class Node {
	int data;
	Node link;

	public Node(int element) {
		link = null;
		data = element;
	}

}

class LinkedList {
	Node first;

	public LinkedList() {
		first = null;
	}

	public boolean Delete(int element) {
		// l.delete(element);
		Node p = first, q = null;

		while (p != null) {
			// 삭제할 값을 찾았다면, p가 연결하던 링크를 삭제하고,
			// 삭제한 값을 가리키고 있던 q의 링크를 p가 링크하고 있던 값에 연결해 준다.
			if (p.data == element) {
				// 지우려는 값이 가장 작은 값(처음 배열)이었다면,
				// 두번째 배열이 처음 값인 것으로 취급하면 된다.
				if (q == null) {
					first = p.link;
					return true;
				}
				// q의 링크를 p가 링크하고 있던 값에 연결해 준다.
				q.link = p.link;
				return true;
			} 
			// 값을 찾지 못했다면 한 칸 이동한다.
			else {
				q = p;
				p = p.link;
			}
		}
		// 삭제할 값을 찾지 못했다면, false를 반환한다.
		return false;
	}

	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node ptr = first;
		System.out.println();
		System.out.print("배열 값 정리 후 출력 : ");
		while (ptr != null) {
			System.out.print(" " + ptr.data);
			// 다음 칸으로 이동.
			ptr = ptr.link;	
		}
		System.out.println();
		System.out.println();
	}

	public void Add(int element) { // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
		// l.add(element);
		Node newNode = new Node(element);
		// this.first = newNode;
		// 기존의 리스트에 집어넣을 방법?
		Node p = first, q = null;
		if (p == null) {
			first = newNode;
			return;
		}
		while (p != null) {
			if (p.data > element) {
				// 연결 링크를 새롭게 만들어 준다.
				// 1. 새 노드의 링크를 p로 연결.
				newNode.link = p;
				// 넣으려는 값이 가장 작은 값이었다면, 첫 배열이 새 값인 것으로 취급하면 된다.
				if (q == null)
					first = newNode;
				else
					// 2. 아니라면 기존 작은 값의 링크 q를 새 노드에 연결.
					q.link = newNode;
				// 루프 종료.
				break;
			} else {
				// q에 p의 값을 저장하고 p는 이동한다.
				q = p;
				p = p.link;
				// 배열의 끝에 도달했다면, 끝 배열이 새 값을 가리키게 하고 루프를 끝내면 된다.
				if (p == null) {
					q.link = newNode;
					break;
				}
			}
		}
	}

	public boolean Search(int data) { // 전체 리스트를 순서대로 출력한다.
		// 데이터가 있으면 true.
		Node p = first, q = null;
		if (p.data == data) {
			return true;
		} else {
			q = p;
			p = p.link;
		}
		// 없으면 false.
		return false;
	}
}

public class Chap8_Test_SimpleList {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		System.out.println("Linked List");
		LinkedList l = new LinkedList();
		Scanner sc = new Scanner(System.in);
		int data = 0;
		System.out.println("inserted");
		// l.Show();
		do {
			switch (menu = SelectMenu()) {
			case Add: // 머리노드 삽입
				// 난수 생성.
				double d = Math.random();
				data = (int) (d * 50);
				l.Add(data);
				System.out.println();
				System.out.println("랜덤 데이터 추가");
				System.out.println();
				break;
			case Delete: // 머리 노드 삭제
				System.out.print("배열에서 번호 삭제 : ");
				int num = sc.nextInt();
				boolean value = l.Delete(num);
				// 지우려는 값이 있다면...
				if (value == true)
					System.out.println("삭제된 데이터는 " + num);
				else
					System.out.println("지울 데이터 없음.");
				break;
			case Show: // 전체 노드 출력
				l.Show();
				break;
			case Search: // 회원 번호 검색
				System.out.print("배열에서 번호 검색 : ");
				int n = sc.nextInt();
				boolean result = l.Search(n);
				if (result == true)
					System.out.println("검색 값 = " + n + "데이터가 존재합니다.");
				else
					System.out.println("검색 값 = " + n + "데이터가 없습니다.");
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
