package sorting.linearSorting;

public class Main {

	public static void main(String[] args) {
		CountingSort count = new CountingSort();
		Integer[] array = {4, 4, 3, 2, 1};
		
		count.sort(array, 0, 4);

	}

}
