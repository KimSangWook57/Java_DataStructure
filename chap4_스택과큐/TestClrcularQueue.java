package chap4_스택과큐;

public class TestClrcularQueue {
	
	CircularQueue queue = new CircularQueue(5);

	queue.enqueue(1);
	queue.enqueue(2);
	queue.enqueue(3);
	queue.display(); // output: Elements in the queue are: 1 2 3

	queue.dequeue();
	queue.display(); // output: Elements in the queue are: 2 3

	queue.enqueue(4);
	queue.enqueue(5);
	queue.enqueue(6); // output: Queue is full, cannot enqueue 6

	
}
