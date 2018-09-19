package adt.queue;

public class Main {

	public static void main(String[] args) throws QueueOverflowException, QueueUnderflowException {
		QueueImpl queue = new QueueImpl<>(10);
		queue.enqueue(4);
		queue.enqueue(2);
		queue.enqueue(1);
		queue.enqueue(5);
		queue.head();
		queue.enqueue(9);
		queue.enqueue(7);
		queue.dequeue();
		queue.dequeue();
		queue.head();
		queue.isEmpty();
		queue.isFull();
	}

}
