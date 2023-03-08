package chap11;

//SimpleObject version으로 수정하기 
import java.util.Comparator;
import java.util.Scanner;

//오픈 주소법에 의한 해시

class SimpleObject2 {

	static Scanner stdIn = new Scanner(System.in);

	static final int NUM = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?

	String snum; // 회원번호
	String sname; // 이름

	// --- 키값은 회원번호. ---//
	public String keyCode() {
		return snum;
	}

	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + snum + ") " + sname;
	}

	// --- 문자열 데이터를 읽어 들임 ---//
	void scanSimpleObject2(String guide, int sw) {
		System.out.println(guide + "할 데이터를 입력하세요.");

		if ((sw & NUM) == NUM) {
			System.out.print("번호: ");
			snum = stdIn.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			sname = stdIn.next();
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject2> NUM_ORDER = new NumOrderComparator();

	private static class NumOrderComparator implements Comparator<SimpleObject2> {
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			return (d1.snum.compareTo(d2.snum) > 0) ? 1 : ((d1.snum.compareTo(d2.snum) < 0)) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject2> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject2> {
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			return (d1.sname.compareTo(d2.sname) > 0) ? 1 : ((d1.sname.compareTo(d2.sname) < 0)) ? -1 : 0;
		}
	}
}

@SuppressWarnings("hiding")
class OpenHash<SimpleObject2> {

	// --- 버킷의 상태 ---//
	enum Status {
		OCCUPIED, EMPTY, DELETED
	}; // {데이터 저장, 비어있음, 삭제 완료}

	// --- 버킷 ---//
	static class Bucket<SimpleObject2> {

		private SimpleObject2 data; // 데이터
		private Status stat; // 상태

		// --- 생성자(constructor) ---//
		Bucket() {
			stat = Status.EMPTY; // 버킷이 비어있음
		}

		// --- 모든 필드에 값을 설정 ---//
		void set(SimpleObject2 data, Status stat) {

			this.data = data; // 데이터
			this.stat = stat; // 상태
		}

		// --- 상태를 설정 ---//
		void setStat(Status stat) {
			this.stat = stat;
		}

//		// --- 키값을 반환 ---//
//		// data의 회원번호를 가져오는 방법?
//		SimpleObject2 getKey() {
//			// 임시 설정. data에서 keyCode를 분리하자.
//			return data;
//		}

		// --- 데이터를 반환 ---//
		SimpleObject2 getValue() {
			return data;
		}

		// --- 키의 해시값을 반환 ---//
		public int hashCode() {
			return data.hashCode();
		}
	}

	private int size; // 해시 테이블의 크기
	private Bucket<SimpleObject2>[] table; // 해시 테이블

	// --- 생성자(constructor) ---//
	public OpenHash(int size) {
		try {
			// size 만큼 배열을 만든다.
			table = new Bucket[size];
			for (int i = 0; i < size; i++)
				// 각각의 배열 칸에 버킷이 생성된다.
				table[i] = new Bucket<SimpleObject2>();
			this.size = size;
		} catch (OutOfMemoryError e) { // 테이블을 생성할 수 없음
			this.size = 0;
		}
	}

	// --- 해시값을 구함 ---//
	public int hashValue(SimpleObject2 key) {
		return key.hashCode() % size;
	}

	// --- 재해시값을 구함 ---//
	public int rehashValue(int hash) {
		return (hash + 1) % size;
	}

	// --- 키값 key를 갖는 버킷 검색 ---//
	private Bucket<SimpleObject2> searchNode(SimpleObject2 key) {
		int hash = hashValue(key); // 검색할 데이터의 해시값
		System.out.println("k" + key + "h" + hash);
		Bucket<SimpleObject2> p = table[hash]; // 주목 버킷

		for (int i = 0; p.stat != Status.EMPTY && i < size; i++) {
			// 객체와 객체 비교.
			if (p.stat == Status.OCCUPIED && p.data.equals(key))
				return p;
			hash = rehashValue(hash); // 재해시
			p = table[hash];
		}
		return null;
	}

	// --- 키값이 key인 요소를 검색(데이터를 반환)---//
	public SimpleObject2 search(SimpleObject2 key) {
		Bucket<SimpleObject2> p = searchNode(key);
		if (p != null)
			return p.getValue();
		else
			return null;
	}

	// --- 키값이 key인 데이터를 data의 요소로 추가 ---//
	// 데이터만 가지고 키를 지정해야 한다.
	public int add(SimpleObject2 key) {
		if (search(key) != null)
			return 1; // 키값이 이미 등록되어 있음
		
		int hash = hashValue(key); // 추가할 데이터의 해시값
		Bucket<SimpleObject2> p = table[hash]; // 주목 버킷
		for (int i = 0; i < size; i++) {
			if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
				// data가 들어가야 하는데...
				p.set(key, Status.OCCUPIED);
				return 0;
			}
			hash = rehashValue(hash); // 재해시
			p = table[hash];
		}
		return 2; // 해시 테이블이 가득 참
	}

	// --- 키값이 key인 요소를 삭제 ---//
	public int remove(SimpleObject2 key) {
		Bucket<SimpleObject2> p = searchNode(key); // 주목 버킷
		if (p == null)
			return 1; // 이 키값은 등록되어 있지 않음

		p.setStat(Status.DELETED);
		return 0;
	}

	// --- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < size; i++) {
			System.out.printf("%02d ", i);
			switch (table[i].stat) {
			case OCCUPIED:
				System.out.printf("%s \n", table[i].getValue());
				break;

			case EMPTY:
				System.out.println("-- 비어있음 --");
				break;

			case DELETED:
				System.out.println("-- 삭제 완료 --");
				break;
			}
		}
	}
}

public class Chap11_Test_OpenHash {

	static Scanner stdIn = new Scanner(System.in);

	//--- 메뉴 ---//
	enum Menu {
		ADD("추가"), REMOVE("삭제"), SEARCH("검색"), DUMP("표시"), TERMINATE("종료");

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

	//--- 메뉴 선택 ---//
	static Menu SelectMenu() {
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		SimpleObject2 data; // 추가용 데이터 참조
		SimpleObject2 temp = new SimpleObject2(); // 읽어 들일 데이터

		OpenHash<SimpleObject2> hash = new OpenHash<SimpleObject2>(13);
		do {
			switch (menu = SelectMenu()) {
			case ADD: // 추가
				data = new SimpleObject2();
				data.scanSimpleObject2("추가", SimpleObject2.NUM | SimpleObject2.NAME);
				int k = hash.add(data);
				switch (k) {
				case 1:
					System.out.println("그 키값은 이미 등록되어 있습니다.");
					break;
				case 2:
					System.out.println("해시 테이블이 가득 찼습니다.");
					break;
				}
				break;

			case REMOVE: // 삭제
				temp.scanSimpleObject2("삭제", SimpleObject2.NUM);
				hash.remove(temp);
				break;

			case SEARCH: // 검색
				temp.scanSimpleObject2("검색", SimpleObject2.NUM);
				System.out.println(temp.snum); 
				SimpleObject2 t = hash.search(temp);
				if (t != null)
					System.out.println("그 키를 갖는 데이터는 " + t + "입니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case DUMP: // 표시
				hash.dump();
				break;
			}
		} while (menu != Menu.TERMINATE);
	}
}
