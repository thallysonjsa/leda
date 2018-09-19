package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (stack1.isFull()) {
			throw new QueueOverflowException();
		} else {
			if (element != null) {
				try {
					stack1.push(element);
				} catch (StackOverflowException e) {
					throw new QueueOverflowException();
				}
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T elemento = null;
		try {
			while (!this.stack1.isEmpty()) {
				this.stack2.push(this.stack1.pop());
			}
			elemento = this.stack2.pop();
			while (!this.stack2.isEmpty()) {
				this.stack1.push(this.stack2.pop());
			}
		} 
		catch (StackOverflowException e) {
			// faz nada
		}
		catch (StackUnderflowException e) {
			throw new QueueUnderflowException();
		}
		return elemento;
	}

	@Override
	public T head() {
		T elemento = null;
		try {
			while (!this.stack1.isEmpty()) {
				this.stack2.push(this.stack1.pop());
			}
			elemento = this.stack2.top();
			while (!this.stack2.isEmpty()) {
				this.stack1.push(this.stack2.pop());
			}
		}
		catch (StackOverflowException e) {
			// faz nada
		}
		catch (StackUnderflowException e) {
			// faz nada
		}
		return elemento;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}
	
}
