package chap4_스택과큐;

public class CircularQueue {
    private int[] arr; // array to store the elements in the queue
    private int front; // front position of the queue
    private int rear; // rear position of the queue
    private int capacity; // maximum capacity of the queue

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.front = -1;
        this.rear = -1;
    }

    // check if the queue is empty
    public boolean isEmpty() {
        return front == -1;
    }

    // check if the queue is full
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }

    // add an element to the rear of the queue
    public void enqueue(int value) {
        if (isFull()) { // if the queue is full, print an error message
            System.out.println("Queue is full, cannot enqueue " + value);
        } else {
            if (isEmpty()) { // if the queue is empty, set the front and rear pointers to 0
                front = 0;
            }
            rear = (rear + 1) % capacity; // increment the rear pointer (circularly) and add the element to the array
            arr[rear] = value;
            System.out.println(value + " enqueued to the queue");
        }
    }

    // remove an element from the front of the queue
    public void dequeue() {
        if (isEmpty()) { // if the queue is empty, print an error message
            System.out.println("Queue is empty, cannot dequeue");
        } else {
            int value = arr[front]; // remove the element at the front position of the array
            if (front == rear) { // if there is only one element in the queue, set the front and rear pointers to -1
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % capacity; // otherwise, increment the front pointer (circularly)
            }
            System.out.println(value + " dequeued from the queue");
        }
    }

    // print the elements in the queue
    public void display() {
        if (isEmpty()) { // if the queue is empty, print a message
            System.out.println("Queue is empty");
        } else {
            System.out.print("Elements in the queue are: ");
            for (int i = front; i != rear; i = (i + 1) % capacity) { // iterate over the elements in the array using the front and rear pointers
                System.out.print(arr[i] + " ");
            }
            System.out.println(arr[rear]); // print the last element in the array
        }
    }
}
