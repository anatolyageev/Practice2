package ua.nure.ageev.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

	public static void main(String[] args) {
		// test9
		// public static void test10() {

		Queue queue = new QueueImpl();
		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");

		Iterator it = queue.iterator();

		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		it.remove();
		System.out.println(queue);

		it = queue.iterator();

		System.out.println(it.next());
		it.remove();
		System.out.println(queue);

		it = queue.iterator();

		System.out.println(it.next());
		it.remove();
		System.out.println(queue);

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
		Node prev;

		// Constructor
		public Node(Object d) {
			data = d;
			next = null;
			prev = null;
		}
	}

	@Override
	public void clear() {
		for (Node x = head; x != null;) {
			Node next = x.next;
			x.data = null;
			x.next = null;
			x = next;
		}
		head = null;
		this.size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Iterator<Object> iterator() {

		return new QueueIterator();
	}

	Object unlink(Node x) {
		// assert x != null;
		final Object element = x.data;
		final Node next = x.next;
		final Node prev = x.prev;

		if (prev == null) {
			head = next;
		} else {
			prev.next = next;
			x.prev = null;
		}

//		if (next == null) {
//			last = prev;
//		} else {
//			next.prev = prev;
//			x.next = null;
//		}

		x.data = null;
		x = null;
		size--;
		if (size == 0) {
			head = null;
		}

		return element;
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
			temp.prev = tail;
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

		if (head != null) {
			Node temp = head;
			while (temp != null) {
				str += (String) (temp.data);
				str += temp.next != null ? ", " : "";
				temp = temp.next;
			}
		} else {
			str += "";
		}

		return str + "]";
	}

	public class QueueIterator implements Iterator<Object> {
		private Node nextNode;
		private Node lastReturnedNode;
		private Node previousNode;
		// private int removed = 0;

		public QueueIterator() {
			nextNode = head;
		}

		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public Object next() throws NoSuchElementException {
			if (!this.hasNext()) {
				throw new NoSuchElementException("end of the iteration");
			}
			previousNode = lastReturnedNode;
			lastReturnedNode = nextNode;
			nextNode = nextNode.next;
			return lastReturnedNode.data;
		}

		public void remove() throws NoSuchElementException {
			// removed++;
			if (lastReturnedNode == null) {
				throw new IllegalStateException("improper iterator state for remove operation");
			} else {

				Node lastNext = lastReturnedNode.next;
				unlink(lastReturnedNode);
				if (nextNode == lastReturnedNode) {
					nextNode = lastNext;
				} else {
					lastReturnedNode = null;
				}
			}
		}

	}
}
