package ua.nure.ageev.practice2;

import java.util.Iterator;

public class QueueImpl implements Queue {

	public static void main(String[] args) {

		Queue queue = new QueueImpl();
		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");

		for (Object element : queue) {
			System.out.print(element);
		}

//		QueueImpl q = new QueueImpl();
//		q.enqueue("A");
//		q.enqueue("B");
//		q.enqueue("C");
//		System.out.println(q);
//		System.out.println(q.toString());
//		System.out.println("deque works----------------------------");
//		System.out.println(q.dequeue());
//		System.out.println("after deque----------------------------");
//		System.out.println(q.toString());
//		System.out.println("top works----------------------------");
//		System.out.println(q.top());
//		System.out.println("clear works----------------------------");
//		q.clear();
//		System.out.println(q.toString());
	}

	private Node head;
	private int size = 0;

	static class Node {

		Object data;
		Node next;

		// Constructor
		public Node(Object d) {
			data = d;
			next = null;
		}
	}

	@Override
	public void clear() {
		head = null;
		this.size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Iterator<Object> iterator() {

		return (Iterator<Object>) this;
	}

	@Override
	public void enqueue(Object element) {
		Node temp = new Node(element);
		if (head == null) {
			head = temp;
			size++;
		} else {
			Node tail = head;
			while (tail.next != null) {
				tail = tail.next;
			}
			tail.next = temp;
			size++;
		}
	}

	@Override
	public Object dequeue() {
		Node tmp = head;
		head = head.next;
		size--;
		return tmp.data;
	}

	@Override
	public Object top() {
		return head.data;
	}

	@Override
	public String toString() {
		String str = "[";
		Node temp = head;
		while (temp != null) {
			str += (String) (temp.data) + ", ";
			temp = temp.next;
		}
		return str + "]";
	}
}
