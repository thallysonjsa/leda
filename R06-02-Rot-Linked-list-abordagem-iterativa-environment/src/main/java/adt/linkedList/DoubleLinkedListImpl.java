package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<>(),
						new DoubleLinkedListNode<>());
				setHead(newHead);
				setLast(newHead);
			} else {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element,
						(DoubleLinkedListNode<T>) super.getHead(), new DoubleLinkedListNode<>());
				((DoubleLinkedListNode<T>) super.getHead()).setPrevious(newHead);
				setHead(newHead);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			setHead(super.getHead().getNext());
			if (this.size() == 1) {
				setLast((DoubleLinkedListNode<T>) super.getHead().getNext());
			}
			((DoubleLinkedListNode<T>) super.getHead().getNext()).setPrevious(new DoubleLinkedListNode<>());
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			getLast().getPrevious().setNext(new DoubleLinkedListNode<>());
			if (this.size() == 1) {
				setHead(getLast().getPrevious());
			}
			setLast(getLast().getPrevious());
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				insertFirst(element);
			} else {
				DoubleLinkedListNode<T> node = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<>(), getLast());
				getLast().setNext(node);
				setLast(node);
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if (element != null) {
			if (!isEmpty()) {
				if (((DoubleLinkedListNode<T>) super.getHead()).getData().equals(element)) {
					removeFirst();
				} else {
					DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) super.getHead();
					while (!aux.isNIL() && !aux.getData().equals(element)) {
						aux = (DoubleLinkedListNode<T>) aux.getNext();
					}
					aux.getPrevious().setNext(aux.getNext());
					((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux.getPrevious());
					if (aux.getNext().isNIL()) {
						setLast(aux.getPrevious());
					}
				}
			}
		}
	}
	
	public DoubleLinkedListNode<T> getHead() {
		return (DoubleLinkedListNode<T>) super.getHead();
	}
	
	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
