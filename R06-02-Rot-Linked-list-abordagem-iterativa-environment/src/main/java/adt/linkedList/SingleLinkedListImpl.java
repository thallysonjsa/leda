package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	public void newHead(int index) {
		int elementIndex = 0;
		SingleLinkedListNode<T> oldHead = this.head;
		SingleLinkedListNode<T> newHead = null;
		SingleLinkedListNode<T> prevNewHead = null;
		if (!isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.isNIL() && (index - 1) != elementIndex) {
				aux = aux.getNext();
				elementIndex++;
			}
			prevNewHead = aux;
			while (!aux.isNIL() && index != elementIndex) {
				aux = aux.getNext();
				elementIndex++;
			}
			newHead = aux;
			this.setHead(newHead);
			while (!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}
			aux.setNext(oldHead);
			prevNewHead.setNext(new SingleLinkedListNode<>());
		}
	}
	
	@Override
	public int size() {
		int size = 0;
		if (!isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.isNIL()) {
				size++;
				aux = aux.getNext();
			}
		}
		return size;
	}

	@Override
	public T search(T element) {
		T retorno = null;
		if (!isEmpty()) {
			if (element != null) {
				SingleLinkedListNode<T> aux = this.head;
				while (!aux.isNIL()) {
					if (aux.getData().equals(element)) {
						retorno = element;
					}
					aux = aux.getNext();
				}
			}
		}
		return retorno;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.head.setData(element);
				this.head.setNext(new SingleLinkedListNode<T>());
			} else {
				SingleLinkedListNode<T> aux = this.head;
				while (!aux.isNIL()) {
					aux = aux.getNext();
				}
				aux.setData(element);
				aux.setNext(new SingleLinkedListNode<T>());
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null){
			if (element.equals(getHead().getData())){
				setHead(getHead().getNext());
			} else {
				SingleLinkedListNode<T> aux = getHead();
				while (!aux.isNIL() && !aux.getNext().getData().equals(element)) {
					aux = aux.getNext();
				}
				aux.setNext(aux.getNext().getNext());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		int index = 0;
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL()) {
			array[index] = aux.getData();
			aux = aux.getNext();
			index++;
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
