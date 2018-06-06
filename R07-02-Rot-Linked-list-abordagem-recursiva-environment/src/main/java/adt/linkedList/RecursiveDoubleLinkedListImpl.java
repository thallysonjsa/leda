package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		RecursiveDoubleLinkedListImpl<T> nil = new RecursiveDoubleLinkedListImpl<>();
		if (element != null) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
				this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
			} else {
				RecursiveDoubleLinkedListImpl<T> newHead = new RecursiveDoubleLinkedListImpl<>(element, this, nil);
				RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<T>(this.getData(), this.getNext(), newHead);
				this.setData(element);
				this.setNext(aux);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			if (this.size() == 1) {
				this.setData(null);
			} else {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			}
		}
	}

	@Override
	public void removeLast() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
			} else {
				if (this.getNext().isEmpty()) {
					this.getNext().setData(element);
					this.getNext().setNext(new RecursiveDoubleLinkedListImpl<>());
					((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
				} else {
					this.getNext().insert(element);
				}
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if (element != null) {
			if (!this.isEmpty()) {
				if (this.getData().equals(element)) {
					
				}
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
