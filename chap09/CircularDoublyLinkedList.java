package chap09;

public class CircularDoublyLinkedList<T> {

	private Node<T> head;
	private int size;

	private static class Node<T> {
		private T data;
		private Node<T> prev;
		private Node<T> next;

		public Node(T data) {
			this.data = data;
		}
	}

	public CircularDoublyLinkedList() {
		head = null;
		size = 0;
	}

	public void add(T data) {
		Node<T> newNode = new Node<>(data);

		if (head == null) {
			head = newNode;
			head.next = head;
			head.prev = head;
		} else {
			Node<T> tail = head.prev;
			tail.next = newNode;
			newNode.prev = tail;
			newNode.next = head;
			head.prev = newNode;
		}

		size++;
	}

	public void remove(T data) {
		if (head == null) {
			return;
		}

		Node<T> current = head;
		while (current.data != data) {
			current = current.next;
			if (current == head) {
				return;
			}
		}

		if (size == 1) {
			head = null;
		} else {
			Node<T> prev = current.prev;
			Node<T> next = current.next;
			prev.next = next;
			next.prev = prev;
			if (current == head) {
				head = next;
			}
		}

		size--;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// Other methods for traversing, accessing, and manipulating the list

	public static void main(String[] args) {
		CircularDoublyLinkedList<String> list = new CircularDoublyLinkedList<>();

		list.add("apple");
		list.add("banana");
		list.add("cherry");

		System.out.println("List size: " + list.size());

		System.out.println("List contents:");
		Node<String> current = list.head;
		do {
			System.out.println(current.data);
			current = current.next;
		} while (current != list.head);

	}
}
