package chap08;

// 단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계
// 문자열을 넣는 코드!!!

import java.util.Comparator;
import java.util.Scanner;


// import Chap8_List.SimpleLinkedList.Menu;

class SimpleObject {
//	static final int NO = 1; // 번호를 읽어 들일까요?
//	static final int NAME = 2; // 이름을 읽어 들일까요?

	private String no; // 회원번호
	private String name; // 이름

	public SimpleObject (String s1, String s2) {
		this.no = s1;
		this.name = s2;
	}
	
	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + no + ") " + name;
	}

//	// --- 데이터를 읽어 들임 ---//
//	void scanData(String guide, int sw) {
//		System.out.println(guide + "할 데이터를 입력하세요." + sw);
//
//		if ((sw & NO) == NO) { // & 는 bit 연산자임
//			System.out.print("번호: ");
//			no = stdIn.nextInt();
//		}
//		if ((sw & NAME) == NAME) {
//			System.out.print("이름: ");
//			name = stdIn.next();
//		}
//	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
			return d1.no.compareTo(d2.no);
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
			return d1.name.compareTo(d2.name);
		}
	}
}

class Node {
	SimpleObject data;
	Node link;

	public Node(SimpleObject element) {
		link = null;
		data = element;
	}
}

class LinkedList {
	Node first;

	public LinkedList() {
		first = null;
	}

	public boolean Delete(SimpleObject element) {
	
		return true;
	}

	public void Show() { 
		// 전체 리스트를 순서대로 출력한다.

	}

	public void Add(SimpleObject element) {
		// 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다.
		// 번호 우선, 번호가 같으면 이름순으로.

	}

	public boolean Search(SimpleObject data) { // 전체 리스트를 순서대로 출력한다.
		
		return true;
	}
}

public class Test8_Test_SimpleObjectList {

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
		SimpleObject data = null;
		System.out.println("inserted");
		l.Show();
		do {
			switch (menu = SelectMenu()) {
			case Add:
				System.out.println("추가할 데이터 입력");
				System.out.println("번호");
				String a1 = sc.next();
				System.out.println("이름");
				String a2 = sc.next();
				// SimpleObject인 data를 만들어서, 그 값이 들어가게 해야 한다.
				data = new SimpleObject(a1, a2);
				l.Add(data);
				break;
			case Delete: // 머리 노드 삭제
				// n이 숫자로 들어오면, 그 값을 오브젝트 리스트의 숫자값을 꺼내 비교해주어야 한다.
				// n을 입력받은 뒤, n의 값을 가지는 임시 배열을 생성한다?
				System.out.println("삭제할 데이터 입력");
				System.out.println("번호");
				String d1 = sc.next();
				System.out.println("이름");
				String d2 = sc.next();
				SimpleObject newV = new SimpleObject(d1, d2);
				boolean value = l.Delete(newV);
				if (value == false)
					System.out.println(d1 + "의 번호와" + d2 + "의 이름을 가진 데이터가 없습니다.");
				else
					System.out.println(d1 + "의 번호와" + d2 + "의 이름 정보 삭제됨.");
				break;
			case Show: // 꼬리 노드 삭제
				l.Show();
				break;
			case Search: // 회원 번호 검색
				// n이 숫자로 들어오면, 그 값을 오브젝트 리스트의 숫자값을 꺼내 비교해주어야 한다.
				// n을 입력받은 뒤, n의 값을 가지는 임시 배열을 생성한다?
				System.out.println("검색할 데이터 입력");
				System.out.println("번호");
				String s1 = sc.next();
				System.out.println("이름");
				String s2 = sc.next();
				SimpleObject newR = new SimpleObject(s1, s2);
				boolean result = l.Search(newR);
				if (result == false)
					System.out.println(s1 + "의 번호와" + s2 + "의 이름을 가진 데이터가 없습니다.");
				else
					System.out.println(s1 + "의 번호와" + s2 + "의 이름을 가진 데이터가 있습니다.");
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
