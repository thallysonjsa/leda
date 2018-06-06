package adt.linkedList;

public class Main {

	public static void main(String[] args) {
		RecursiveDoubleLinkedListImpl<Integer> dll = new RecursiveDoubleLinkedListImpl<>();
		dll.insert(1);
		System.out.println(dll.size());
		dll.insertFirst(3);
		dll.size();
		System.out.println(dll.size());
//		System.out.println(dll.getData());
//		System.out.println(dll.getNext().getData());
//		System.out.println(((RecursiveDoubleLinkedListImpl<Integer>) dll.getNext()).getPrevious().getData());
//		System.out.println();
	}

}
