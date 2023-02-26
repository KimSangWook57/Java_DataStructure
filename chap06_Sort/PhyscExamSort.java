package chap06_Sort;

//신체검사 데이터 배열의 정렬.
public class PhyscExamSort {
	// --- 신체검사 데이터 ---//
	public static class PhyscData implements Comparable<PhyscData> {
		public String name; // 이름
		public int height; // 키
		public double vision; // 시력

		// --- 생성자(constructor) ---//
		public PhyscData(String name, int height, double vision) {
			this.name = name;
			this.height = height;
			this.vision = vision;
		}

		// --- 신체검사 데이터를 문자열로 반환 --//
		public String toString() {
			return name + " " + height + " " + vision;
		}

		// 키의 오름차순 이후, 키가 같으면 이름으로 정리하도록 오버라이딩 해보기.
		@Override
		public int compareTo(PhyscData p) {

			// 밑에 있는 코드를 활용, 키순으로 먼저 정렬한다.
			if (this.height > p.height) 
				return 1;
			else if (this.height < p.height)
				return -1;
			// 키 값이 동일하다면...
			// 키 순서를 유지하면서 이름 순으로 정렬한다.
			else 
				return this.name.compareTo(p.name);
			

		}

		// --- 키의 오름차순용 comparator ---//
//		static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();
//
//		private static class HeightOrderComparator implements Comparator<PhyscData> {
//			public int compare(PhyscData d1, PhyscData d2) {
//				return (d1.height > d2.height) ? 1 : (d1.height < d2.height) ? -1 : 0;
//			}
//		}
	}

}
