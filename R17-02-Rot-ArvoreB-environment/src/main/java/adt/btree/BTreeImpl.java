package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		if (this.isEmpty())
			return -1;
		return height(this.root);
	}

	private int height(BNode<T> node) {
		if (node.isLeaf())
			return 0;
		return 1 + height(node.getChildren().getFirst());
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		BNode<T>[] array = new BNode[this.countingNodes(this.root)];

		depthLeftOrder(array, 0, root);

		return array;
	}

	public int depthLeftOrder(BNode<T> array[], int index, BNode<T> node) {
		if (!node.isEmpty()) {
			array[index++] = node;

			for (int i = 0; i < node.children.size(); i++) {
				index = depthLeftOrder(array, index, node.children.get(i));
			}
		}
		return index;
	}

	private int countingNodes(BNode<T> node) {
		if (node.isEmpty())
			return 0;

		int ans = 1;

		for (int i = 0; i < node.children.size(); i++)
			ans += countingNodes(node.children.get(i));

		return ans;
	}

	@Override
	public int size() {
		return size(this.getRoot());
	}

	public int size(BNode<T> node) {
		if (node.isEmpty())
			return 0;

		int resposta = node.size();
		for (int i = 0; i < node.children.size(); i++) {
			resposta += size(node.children.get(i));
		}
		return resposta;
	}

	@Override
	public BNodePosition<T> search(T element) {
		if (element == null)
			return new BNodePosition<T>();

		return search(element, this.root);
	}

	public BNodePosition<T> search(T element, BNode<T> node) {
		int index = 0;

		while (index < node.getElements().size() && element.compareTo(node.getElementAt(index)) > 0) {
			index++;
		}

		if (index < node.getElements().size() && element.compareTo(node.getElementAt(index)) == 0) {
			return new BNodePosition<T>(node, index);
		}

		if (node.isLeaf())
			return new BNodePosition<>();

		return search(element, node.getChildren().get(index));
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;

		insert(element, this.root);
	}

	public void insert(T element, BNode<T> node) {
		int index = 0;

		while (index < node.getElements().size() && element.compareTo(node.getElementAt(index)) > 0) {
			index++;
		}

		if (node.isLeaf()) {
			node.addElement(element);
			node.addChild(index, new BNode<T>(this.order));

			if (node.getMaxKeys() < node.size()) {
				node.split();

				while (node.parent != null) {
					node = node.parent;
				}
				this.root = node;
			}
		} else {
			insert(element, node.getChildren().get(index));
		}
	}

	private void split(BNode<T> node) {
		node.split();
	}

	private void promote(BNode<T> node) {
		node.promote((this.size() - 1) / 2);
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
