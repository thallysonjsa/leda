package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		if (isEmpty()) return 0;
		
		return blackHeight((RBNode<T>) this.root);
	}
	
	public int blackHeight(RBNode<T> node){
		if (node == null || node.isEmpty())
			return 1;
		
		if (node.equals(this.root) || node.getColour().equals(Colour.RED))
			return 0 + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
		
		return 1 + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		if (isEmpty())
			return true;
		return verifyChildrenOfRedNodes((RBNode<T>) this.root);
		
	}
	
	public boolean verifyChildrenOfRedNodes(RBNode<T> node){
		if (node.isEmpty()) 
			return true;
		if (node.getColour().equals(Colour.RED) &&  ((RBNode<T>)node.getLeft()).getColour().equals(Colour.RED))
			return false;
		else if (node.getColour().equals(Colour.RED) &&  ((RBNode<T>)node.getRight()).getColour().equals(Colour.RED))
			return false;
		else return verifyChildrenOfRedNodes((RBNode<T>) node.getLeft()) && verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
	}
	
	

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	public boolean verifyBlackHeight() {
		return verifyBlackHeight((RBNode<T>) this.root);
	}
	
	public boolean verifyBlackHeight(RBNode<T> node){
		if (node.isEmpty()) return true;
		
		if (blackHeight((RBNode<T>) node.getLeft()) == blackHeight((RBNode<T>) node.getRight())){
			return verifyBlackHeight((RBNode<T>) node.getLeft()) && verifyBlackHeight((RBNode<T>) node.getRight());
		}
		
		return false;
	}

	@Override
	public void insert(T value) {
		if (value == null) return;
		
		insert ((RBNode<T>) getRoot(), value);
	}
	
	public void insert(RBNode<T> node, T element){
		if (node.isEmpty()){
			node.setData(element);
			node.setColour(Colour.RED);
			node.setLeft(new RBNode<T>());
			node.setRight(new RBNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			fixUpCase1(node);
		}else{
			if (node.getData().compareTo(element) < 0)
				insert((RBNode<T>) node.getRight(), element);
			else if (node.getData().compareTo(element) > 0)
				insert((RBNode<T>) node.getLeft(), element);
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		RBNode<T>[] array = new RBNode[this.size()];
		rbPreOrder(array, 0, (RBNode<T>) this.root);
		return array;
	}
	
	public int rbPreOrder(RBNode<T>[] array, int index, RBNode<T> node){
		if (!node.isEmpty()) {
			array[index++] = node;
			index = rbPreOrder(array, index, (RBNode<T>) node.getLeft());
			index = rbPreOrder(array, index, (RBNode<T>) node.getRight());
		}
		return index;
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals((RBNode<T>)this.root)){
			node.setColour(Colour.BLACK);
		}else{
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (((RBNode<T>) node.getParent()).getColour().equals(Colour.RED)){
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();
		RBNode<T> uncle = (RBNode<T>) (isLeftChild(parent) ? grandParent.getRight() : grandParent.getLeft());
		
		if (uncle.getColour().equals(Colour.RED)){
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			grandParent.setColour(Colour.RED);
			fixUpCase1(grandParent);
		}else{
			fixUpCase4(node);
		}
	}
	
	public boolean isLeftChild(RBNode<T> node){
		return node.getParent().getLeft().equals(node);
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		
		if(!isLeftChild(node) && isLeftChild((RBNode<T>) node.getParent())){
			leftRotation((RBNode<T>) node.getParent());
			next = (RBNode<T>) node.getLeft();
		} else if(isLeftChild(node) && !isLeftChild((RBNode<T>) node.getParent())){
			rightRotation((RBNode<T>) node.getParent());
			next = (RBNode<T>) node.getRight();
		}
		fixUpCase5(next);
	}
	
	public void leftRotation(RBNode<T> node){
		RBNode<T> newNode = (RBNode<T>) Util.leftRotation(node);
		if (newNode.getParent() == null)
			this.root = newNode;
	}
	
	public void rightRotation(RBNode<T> node){
		RBNode<T> newNode = (RBNode<T>) Util.rightRotation(node);
		if (newNode.getParent() == null)
			this.root = newNode;
	}

	protected void fixUpCase5(RBNode<T> node) {
		((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
		((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);
		
		if (isLeftChild(node))
			rightRotation((RBNode<T>) node.getParent().getParent());
		else
			leftRotation((RBNode<T>) node.getParent().getParent());
	}
}
