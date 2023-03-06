package chap10_Hash;

import java.util.Comparator;

// comparator 사용 버전.
// v만 SimpleObject로 사용하고, k는 사용하지 않기.
// add(), remove(), dump()를 직접 작성하기.

public class ChainHash<SimpleObject> {

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
	
	class Node<V> {
		private SimpleObject data; 		// 데이터(이름)
		private Node<SimpleObject> next; 	// 뒤쪽 포인터(뒤쪽 노드에 대한 참조)
		
		// --- 생성자(constructor) ---//
		Node(SimpleObject data, Node<SimpleObject> next) {
			this.data = data;
			this.next = next;
		}

		// --- 데이터를 반환 ---//
		SimpleObject getValue() {
			return data;
		}
		
		// --- 키의 해시값을 반환 ---//
		public int hashCode() {
			
			SimpleObject so = this.data;
			// case 1, 자바 내장 코드 사용.
			// so.hashCode();
			// return hashValue(so.hashCode());
			
			// case 2, 직접 번호 사용(Integer.parseInt(str)).
			int num = Integer.parseInt(so.num);
			return hashValue(num);
			
		}
		
		
		// --- 해시값을 구함 ---//
		// size로 나눈 나머지값이 해시값이 된다.
		public int hashValue(int num) {
			return num % size;
		}
		
		private int size; 			// 해시 테이블의 크기
		private Node<SimpleObject>[] table; 	// 해시 테이블

		// --- 테이블 생성자(constructor) ---//
		@SuppressWarnings("unchecked")
		public ChainHash(int capacity) {
			try {
				table = new Node[capacity];
				this.size = capacity;
			} catch (OutOfMemoryError e) { // 테이블을 생성할 수 없음
				this.size = 0;
			}
		}

		// --- 번호로 요소를 검색(데이터를 반환) ---//
		public SimpleObject search(SimpleObject so) {
			int hash = hashValue(key); // 검색할 데이터의 번호값
			Node<SimpleObject> p = table[hash]; // 선택 노드

			while (p != null) {
				// if (p.data.getNum().compareTo(element) == 0)
				if (p.getKey().equals(key))
					return p.getValue(); // 검색 성공
				p = p.next; // 다음 노드를 선택
			}
			return null; // 검색 실패
		}

		// --- 데이터를 data의 요소로 추가 ---//
		public int add(SimpleObject data) {
			int hash = hashValue(key); // 추가할 데이터의 해시값
			Node<SimpleObject> p = table[hash]; // 선택 노드

			while (p != null) {
				// if (p.data.getNum().compareTo(element) == 0)
				if (p.getKey().equals(key)) // 키값이 이미 등록됨
					return 1;
				p = p.next; // 다음 노드를 선택
			}
			Node<SimpleObject> temp = new Node<SimpleObject>(data, table[hash]);
			table[hash] = temp; // 노드 삽입
			return 0;
		}

		// --- 값이 value인 요소를 삭제 ---//
		public int remove(SimpleObject data) {
			int hash = hashValue(key); // 삭제할 데이터의 해시값
			Node<SimpleObject> p = table[hash]; // 선택 노드
			Node<SimpleObject> pp = null; // 바로 앞의 선택 노드

			while (p != null) {
				// value랑 직접 비교해서 값을 찾았다면...
				// if (p.data.getNum().compareTo(element) == 0)
				// else if (p.data.getName().compareTo(element) == 0)
				if (p.getKey().equals(key)) { 
					
					if (pp == null)
						table[hash] = p.next;
					else
						pp.next = p.next;
					return 0;
				}
				pp = p;
				p = p.next; // 다음 노드를 선택
			}
			return 1; // 찾는 키값이 없음
		}

		// --- 해시 테이블을 덤프(dump) ---//
		public void dump() {
			for (int i = 0; i < size; i++) {
				Node<SimpleObject> p = table[i];
				System.out.printf("%02d  ", i);
				while (p != null) {
					System.out.printf("→ %s   ", p.getValue());
					p = p.next;
				}
				System.out.println();
			}
		}
	}

}
