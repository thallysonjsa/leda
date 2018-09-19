package adt.btree;

import java.util.Collections;
import java.util.LinkedList;

public class BNode<T extends Comparable<T>> {
	protected LinkedList<T> elements; //PODERIA TRABALHAR COM ARRAY TAMBEM
	protected LinkedList<BNode<T>> children; //PODERIA TRABALHAR COM ARRAY TAMBEM
	protected BNode<T> parent;
	protected int order;
	
	public BNode(int order){
		this.order = order;
		this.elements = new LinkedList<T>();	
		this.children = new LinkedList<BNode<T>>();
	}
	@Override
	public String toString() {
		return this.elements.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean resp = false;
		if(obj != null){
			if(obj instanceof BNode){
				if(this.size() == ((BNode<T>) obj).size()){
					resp = true;
					int i = 0;
					while(i<this.size() && resp){
						resp = resp && this.getElementAt(i).equals(((BNode<T>) obj).getElementAt(i));
						i++;
					}
				}
			}
		}
		return resp;
	}
	public boolean isEmpty(){
		return this.size() == 0;
	}
	public int size(){
		return this.elements.size();
	}
	public boolean isLeaf(){
	  for (int i = 0; i < children.size(); i++) {
            if (!children.get(i).isEmpty())
                return false;
        }

       return true;
	}
	
	public boolean isFull(){
		return this.size()== order - 1;
	}
	public void addElement(T element){
		this.elements.add(element);
		Collections.sort(elements);
	}
	public void removeElement(T element){
		this.elements.remove(element);
	}
	public void removeElement(int position){
		this.elements.remove(position);
	}
	public void addChild(int position, BNode<T> child){
		this.children.add(position, child);
		child.parent = this;
	}
	public void removeChild(BNode<T> child){
		this.children.remove(child);
	}
	public int indexOfChild(BNode<T> child){
		return this.children.indexOf(child);
	}
	public T getElementAt(int index){
		return this.elements.get(index);
	}
	
	protected void split() {
        int mid = (size()) / 2;

        BNode<T> left = new BNode<T>(this.getMaxChildren());

        for (int i = 0; i < mid; i++) {
            left.addElement(this.elements.get(i));
        }

        for (int i = 0; i <= mid; i++) {
            left.addChild(i, this.children.get(i));
        }
        BNode<T> right = new BNode<T>(this.getMaxChildren());

        for (int i = mid + 1; i < elements.size(); i++) {
            right.addElement(this.elements.get(i));
        }

        for (int i = mid + 1; i < this.children.size(); i++) {
            right.addChild(i - mid - 1, this.children.get(i));
        }

        if (parent == null) {
            parent = new BNode<T>(getMaxChildren());
            parent.children.addFirst(this);
        }

        int index = this.parent.indexOfChild(this);
        this.parent.removeChild(this);

        this.parent.addChild(index, left);
        this.parent.addChild(index + 1, right);

        left.setParent(this.parent);
        right.setParent(this.parent);

        this.promote(mid);

        if (this.parent.size() >= getMaxChildren()) {
            this.parent.split();
        }
	}

	protected void promote(int mid) {
    	T element = elements.get(mid);
        this.parent.addElement(element);
	}

	    
	public LinkedList<T> getElements() {
		return elements;
	}
	public void setElements(LinkedList<T> elements) {
		this.elements = elements;
	}
	public LinkedList<BNode<T>> getChildren() {
		return children;
	}
	public void setChildren(LinkedList<BNode<T>> children) {
		this.children = children;
	}
	public BNode<T> copy(){
		BNode<T> result = new BNode<T>(order);
		result.parent = parent;
		for (int i = 0; i < this.elements.size(); i++) {
			result.addElement(this.elements.get(i));
		}
		for (int i = 0; i < this.children.size(); i++) {
			result.addChild(i,((BNode<T>)this.children.get(i)).copy());
		}
		
		return result;
	}
	public BNode<T> getParent() {
		return parent;
	}
	public void setParent(BNode<T> parent) {
		this.parent = parent;
	}
	public int getMaxKeys() {
		return order - 1;
	}
	public int getMaxChildren() {
		return order;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
}
