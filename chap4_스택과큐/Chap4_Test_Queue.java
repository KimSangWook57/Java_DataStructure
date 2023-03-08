package chap4_스택과큐;

import java.util.Scanner;

class Chap4_Test_Queue {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Chap4_Queue s = new Chap4_Queue(4);    // 최대 64개를 인큐할 수 있는 큐

        while (true) {
        	System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
            System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
            System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(0)종료: ");

            int menu = stdIn.nextInt();
            if (menu == 0) {
            	System.out.println("프로그램을 종료합니다.");
            	break;
            }

            int x;
            switch (menu) {
             case 1:                                // 푸시
                System.out.print("추가할 데이터: ");
                x = stdIn.nextInt();
                try {
                    s.push(x);
                    System.out.println("push한 데이터는 " + x + "입니다.");
                 } catch (Chap4_Queue.OverflowIntQueueException e) {
                    System.out.println("큐가 가득 찼습니다.");
                }
                break;

             case 2:                                // 팝
                try {
                     x = s.pop();
                    System.out.println("pop한 데이터는 " + x + "입니다.");
                 } catch (Chap4_Queue.EmptyIntQueueException e) {
                    System.out.println("큐가 비어 있습니다.");
                }
                break;

             case 3:                                // 피크
                try {
                     x = s.peek();
                    System.out.println("peek한 데이터는 " + x + "입니다.");
                 } catch (Chap4_Queue.EmptyIntQueueException e) {
                    System.out.println("큐가 비어 있습니다.");
                }
                break;

             case 4:								// 덤프
            	try {
            		s.dump();
            	} catch (Chap4_Queue.EmptyIntQueueException e) {
                    System.out.println("큐가 비어 있습니다.");
                }
                break;
            }
        }
    }
}