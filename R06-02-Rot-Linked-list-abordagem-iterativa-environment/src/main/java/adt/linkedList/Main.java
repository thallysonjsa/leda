package adt.linkedList;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		SingleLinkedListImpl<Integer> list = new SingleLinkedListImpl<Integer>();
		list.insert(3545);
		list.insert(2435);
		list.insert(123092109);
		list.insert(84244);
		System.out.println(Arrays.toString(list.toArray()));
		list.newHead(3);
		System.out.println(Arrays.toString(list.toArray()));
	}

}
