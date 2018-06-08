package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (this.isFull()) {
				throw new StackOverflowException();
			}
			this.top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T top = null;
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		}
		top = this.top();
		this.top.removeLast();
		return top;
	}

	@Override
	public T top() {
		T top = null;
		if (!isEmpty()) {
			top = ((RecursiveDoubleLinkedListImpl<T>) this.top).getLast().getData();
		}
		return top;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}

}
