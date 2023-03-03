package chap08_List;

// 단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계
// 문자열을 넣는 코드!!!

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject {

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private String num; // 회원번호
	private String name; // 이름

	public SimpleObject(String s1, String s2) {
		this.num = s1;
		this.name = s2;
	}

	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + num + ") " + name;
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NUM_ORDER = new NumOrderComparator();

	private static class NumOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
			return d1.num.compareTo(d2.num);
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

class ObjectNode {
	SimpleObject data;
	ObjectNode link;

	public ObjectNode(SimpleObject element) {
		link = null;
		data = element;
	}
}

class LinkedList {
	ObjectNode first;

	public LinkedList() {
		first = null;
	}

	public boolean Delete(String element, Comparator<SimpleObject> comparator) {
		// 포인터 설정.
		ObjectNode p = first, q = null;
		// 삭제할 값을 찾았다면, p가 연결하던 링크를 삭제하고,
		// 삭제한 값을 가리키고 있던 q의 링크를 p가 링크하고 있던 값에 연결해 준다.
		while (p != null) {
			// 회원번호가 일치한다면...
			if (p.data.getNum().compareTo(element) == 0) {
				if (q == null) {
					first = p.link;
					return true;
				}
				// q의 링크를 p가 링크하고 있던 값에 연결해 준다.
				q.link = p.link;
				return true;
			}
			// 이름이 일치한다면...
			else if (p.data.getName().compareTo(element) == 0) {
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

	public void Show() {
		// 전체 리스트를 순서대로 출력한다.
		ObjectNode ptr = first;
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

	public void Add(SimpleObject element, Comparator<SimpleObject> comparator) {
		// 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다.
		// 번호 우선, 번호가 같으면 이름순으로.
		ObjectNode newObjNode = new ObjectNode(element);
		// 포인터 설정.
		ObjectNode p = first, q = null;
		// 값이 없다면, 새로운 노드를 만든다.
		if (p == null) {
			first = newObjNode;
			return;
		}
		// compareTo 사용을 위해서, data와 element의 이름과 번호를 추출해야 한다.
		while (p != null) {
			// 번호순으로 먼저 정렬한다.
			if (p.data.getNum().compareTo(element.getNum()) > 0) {
				// 연결 링크를 새롭게 만들어 준다.
				// 1. 새 노드의 링크를 p로 연결.
				newObjNode.link = p;
				// 넣으려는 값이 가장 작은 값이었다면, 첫 배열이 새 값인 것으로 취급하면 된다.
				if (q == null)
					first = newObjNode;
				else
					// 2. 아니라면 기존 작은 값의 링크 q를 새 노드에 연결.
					q.link = newObjNode;
				// 루프 종료.
				break;
			}
			else if (p.data.getName().compareTo(element.getName()) > 0) {
				// 연결 링크를 새롭게 만들어 준다.
				// 1. 새 노드의 링크를 p로 연결.
				newObjNode.link = p;
				// 넣으려는 값이 가장 작은 값이었다면, 첫 배열이 새 값인 것으로 취급하면 된다.
				if (q == null)
					first = newObjNode;
				else
					// 2. 아니라면 기존 작은 값의 링크 q를 새 노드에 연결.
					q.link = newObjNode;
				// 루프 종료.
				break;
			} 
			else {
				// q에 p의 값을 저장하고 p는 이동한다.
				q = p;
				p = p.link;
				// 배열의 끝에 도달했다면, 끝 배열이 새 값을 가리키게 하고 루프를 끝내면 된다.
				if (p == null) {
					q.link = newObjNode;
					break;
				}
			}
		}
	}

	public boolean Search(String data, Comparator<SimpleObject> comparator) { 
		// 전체 리스트를 순서대로 출력한다.
		// 번호와 이름을 확인 후 데이터가 있으면 true. 
		ObjectNode p = first;
		while (p != null) {
			if (p.data.getNum().compareTo(data) == 0)				return true;
			else if ((p.data.getName().compareTo(data) == 0))		return true;
			else													p = p.link;
		}
		// 없으면 false.
		return false;
	}
}

public class SimpleObjectListSolution {

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
		// 정렬을 위한 코드.
		Comparator<SimpleObject> comparator = null;
		System.out.println("inserted");

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
				l.Add(data, comparator);
				break;
			case Delete:
				System.out.println("삭제할 데이터 입력(번호 = 1, 이름 = 2)");
				int target = sc.nextInt();
				if (target == 1) {
					System.out.println("번호");
					String d1 = sc.next();
					comparator = SimpleObject.NUM_ORDER;
					boolean value = l.Delete(d1, comparator);
					if (value == false)
						System.out.println(d1 + "번 데이터가 없습니다.");
					else
						System.out.println(d1 + "번 데이터 삭제됨.");
					break;
				}
				else if (target == 2){
					System.out.println("이름");
					String d2 = sc.next();
					comparator = SimpleObject.NAME_ORDER;
					boolean result2 = l.Delete(d2, comparator);
					if (result2 == false)
						System.out.println(d2 + "의 이름을 가진 데이터가 없습니다.");
					else
						System.out.println(d2 + "의 이름을 가진 데이터 삭제됨.");
					break;
				}
				else {
					System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
					break;
				}
			case Show:
				l.Show();
				break;
			case Search:
				// 회원 검색
				System.out.println("검색할 데이터 입력(번호 = 1, 이름 = 2)");
				int number = sc.nextInt();
				if (number == 1) {
					System.out.println("번호");
					String s1 = sc.next();
					comparator = SimpleObject.NUM_ORDER;
					boolean result1 = l.Search(s1, comparator);
					if (result1 == false)
						System.out.println(s1 + "번 데이터가 없습니다.");
					else
						System.out.println(s1 + "번 데이터가 있습니다.");
					break;
				} else if(number == 2) {
					System.out.println("이름");
					String s2 = sc.next();
					comparator = SimpleObject.NAME_ORDER;
					boolean result2 = l.Search(s2, comparator);
					if (result2 == false)
						System.out.println(s2 + "의 이름을 가진 데이터가 없습니다.");
					else
						System.out.println(s2 + "의 이름을 가진 데이터가 있습니다.");
					break;
				} 
				else {
					System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
					break;
				}
			case Exit:
				break;
			}
		} while (menu != Menu.Exit);

	}
}
