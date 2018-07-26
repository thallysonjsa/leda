package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return this.height(this.root);
	}

	protected int height(BSTNode<T> node) {
		int returnHeight = -1;
		if (!node.isEmpty()) {
			returnHeight = 1
					+ Math.max(this.height((BSTNode<T>) node.getRight()), this.height((BSTNode<T>) node.getLeft()));
		}

		return returnHeight;
	}

	@Override
	public BSTNode<T> search(T element) {
		return this.search(root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> resultado = new BSTNode<>();
		if (element != null && !node.isEmpty()) {
			if (node.getData().equals(element)) {
				resultado = node;
			} else if (node.getData().compareTo(element) > 0) {
				resultado = this.search((BSTNode<T>) node.getLeft(), element);
			} else if (node.getData().compareTo(element) < 0) {
				resultado = this.search((BSTNode<T>) node.getRight(), element);
			}
		}
		return resultado;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			this.insert(root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (element != null) {
			if (node.isEmpty()) {

				node.setData(element);
				node.setLeft(new BSTNode<>());
				node.setRight(new BSTNode<>());

				if (node.getParent() == null) {
					node.setParent(new BSTNode<>());

				}
				node.getLeft().setParent(node);
				node.getRight().setParent(node);
			} else {
				if (node.getData().compareTo(element) > 0) {
					this.insert((BSTNode<T>) node.getLeft(), element);
				} else if (node.getData().compareTo(element) < 0) {
					this.insert((BSTNode<T>) node.getRight(), element);
				}
			}
		}

	}

	@Override
	public BSTNode<T> maximum() {
		return this.maximum(this.getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> resultado = null;
		if (!node.isEmpty()) {
			if (node.getRight().isEmpty()) {
				resultado = (BSTNode<T>) node;
			} else {
				resultado = maximum((BSTNode<T>) node.getRight());
			}
		}

		return resultado;
	}

	@Override
	public BSTNode<T> minimum() {
		return this.minimum(this.getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> resultado = null;
		if (!node.isEmpty()) {
			if (node.getLeft().isEmpty()) {
				resultado = (BSTNode<T>) node;
			} else {
				resultado = minimum((BSTNode<T>) node.getLeft());
			}
		}

		return resultado;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty()) {
			return null;
		}
		if (!node.getRight().isEmpty()) {
			return minimum((BSTNode<T>) node.getRight());
		} else {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			while (!parent.isEmpty() && node.equals(parent.getRight())) {
				node = parent;
				parent = (BSTNode<T>) node.getParent();
			}
			if (parent.isEmpty()) {
				return null;
			}
			return parent;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty()) {
			return null;
		}
		if (!node.getLeft().isEmpty()) {
			return maximum((BSTNode<T>) node.getLeft());
		} else {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			while (!parent.isEmpty() && node.equals(parent.getLeft())) {
				node = parent;
				parent = (BSTNode<T>) node.getParent();
			}
			if (parent.isEmpty()) {
				return null;
			}
			return parent;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			if (node != null) {
				if (node.isLeaf()) {
					node.setData(null);
				} else if (node.getLeft().isEmpty()) {
					node.getParent().setLeft(node.getRight());
					node.getParent().getLeft().setParent(node.getParent());
				} else if (node.getRight().isEmpty()) {
					node.getParent().setRight(node.getLeft());
					node.getParent().getRight().setParent(node.getParent());
				} else {
					T auxiliar = sucessor(element).getData();
					sucessor(element).setData(node.getData());
					T aux = sucessor(element).getData();
					node.setData(auxiliar);
					remove(aux);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		T[] preOrd = (T[]) new Comparable[this.size()];
		if (this.isEmpty())
			return preOrd;
		preOrder(preOrd, root, 0);
		return preOrd;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int preOrder(T[] preOrd, BSTNode<T> node, int index) {
		preOrd[index++] = node.getData();

		if (!node.getLeft().isEmpty())
			index = preOrder(preOrd, (BSTNode) node.getLeft(), index);

		if (!node.getRight().isEmpty())
			index = preOrder(preOrd, (BSTNode) node.getRight(), index);

		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		T[] inOrd = (T[]) new Comparable[this.size()];
		if (this.isEmpty())
			return inOrd;
		order(inOrd, root, 0);
		return inOrd;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int order(T[] inOrd, BSTNode<T> node, int i) {
		if (!node.getLeft().isEmpty())
			i = order(inOrd, (BSTNode) node.getLeft(), i);

		inOrd[i++] = node.getData();

		if (!node.getRight().isEmpty())
			i = order(inOrd, (BSTNode) node.getRight(), i);
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		T[] posOrd = (T[]) new Comparable[this.size()];
		if (this.isEmpty())
			return posOrd;
		postOrder(posOrd, root, 0);
		return posOrd;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int postOrder(T[] posOrd, BSTNode<T> node, int index) {
		if (!node.getLeft().isEmpty())
			index = postOrder(posOrd, (BSTNode) node.getLeft(), index);

		if (!node.getRight().isEmpty())
			index = postOrder(posOrd, (BSTNode) node.getRight(), index);

		posOrd[index++] = node.getData();
		return index;
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

	public String elementsRight(T element) {
		BSTNode<T> node = search(element);
		return elementsRightAux((BSTNode<T>) node.getRight());

	}

	public String elementsRightAux(BSTNode<T> node) {
		String elements = "";
		if (node.isEmpty()) {
			return elements;
		}
		elements += node + " ";
		return elements + elementsRightAux((BSTNode<T>) node.getRight());
	}

	public String allElementsRight(T element) {
		BSTNode<T> node = search(element);
		return allElementsRightAux((BSTNode<T>) node.getRight());

	}

	public String allElementsRightAux(BSTNode<T> node) {
		String elements = "";
		if (node.equals(maximum())) {
			return elements;
		}
		if (!node.getLeft().isEmpty()) {
			return elements += node.getLeft() + " " + allElementsRightAux((BSTNode<T>) node.getLeft());
		}
		if (!node.getRight().isEmpty()) {
			return elements += node.getRight() + " " + allElementsRightAux((BSTNode<T>) node.getRight());
		}
		remove((T) node);
		node = (BSTNode<T>) node.getParent();
		return allElementsRightAux(node);
	}

	public T ceil(T element) {
		if (element.compareTo(maximum().getData()) > 0) {
			return null;
		}
		if (!search(element).isEmpty()) {
			return search(element).getData();
		}
		insert(element);
		T ceilAux = sucessor(element).getData();
		remove(element);
		return ceilAux;
	}

	public T floor(T element) {
		if (element.compareTo(minimum().getData()) < 0) {
			return null;
		}
		if (!search(element).isEmpty()) {
			return search(element).getData();
		}
		insert(element);
		T floorAux = predecessor(element).getData();
		remove(element);
		return floorAux;
	}

	public T orderIstatistics(int number) {
		BSTNode<T> node = minimum();
		for (int j = 1; j < number; j++) {
			node = sucessor(node.getData());
		}
		return node.getData();
	}

	public String tree() {
		String tree = "";
		BSTNode<T> node = maximum();
		while (!node.equals(minimum())) {
			if ((int) node.getData() % 2 == 0)
				tree += node.getData() + " ";
			node = predecessor(node.getData());
		}
		if ((int) minimum().getData() % 2 == 0)
			tree += (minimum().getData());
		return tree;
	}

}
