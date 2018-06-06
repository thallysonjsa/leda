package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.getData() == null;
	}

	@Override
	public int size() {
		int size = 0;
		if (!this.isEmpty()) {
			size = 1 + this.getNext().size();
		}
		return size;
	}

	@Override
	public T search(T element) {
		T retorno = null;
		if (element != null) {
			if (this.isEmpty()) {
				retorno = null;
			} else {
				if (this.getData().equals(element)) {
					retorno = this.getData();
				} else {
					return this.getNext().search(element);
				}
			}
		}
		return retorno;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<>());
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!this.isEmpty()) {
				if (this.getData().equals(element)) {
					this.setData(this.getNext().getData());
					this.setNext(this.getNext().getNext());
				} else {
					this.getNext().remove(element);
				}
			} 
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		toArray(array, this, 0);
		return array;
	}
	
	private void toArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int index) {
		if (!node.isEmpty()) {
			array[index] = node.getData();
			toArray(array, node.getNext(), ++index);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
