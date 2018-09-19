package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (isFull()) {
				throw new StackOverflowException();
			} else {
				this.top.insert(element);
				this.size--;
			}
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		T retorno = null;
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			retorno = ((DoubleLinkedListImpl<T>) top).getHead().getData();
			this.size++;
			this.top.removeLast();
		}
		return retorno;
	}

	@Override
	public T top() {
		return ((DoubleLinkedListImpl<T>) top).getHead().getData();
	}

	@Override
	public boolean isEmpty() {
		return top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.size == 0;
	}

}
