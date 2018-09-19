package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		T element = null;
		if (!isEmpty()) {
			element = array[top];
		}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == array.length - 1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (!isFull()) {
			if (element != null) {
				this.top++;
				this.array[top] = element;
			} else {
				throw new StackOverflowException();
			}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T element = null;
		if (!isEmpty()) {
			element = this.array[top];
			this.top--;
		} else {
			throw new StackUnderflowException();
		}
		return element;
	}

}
