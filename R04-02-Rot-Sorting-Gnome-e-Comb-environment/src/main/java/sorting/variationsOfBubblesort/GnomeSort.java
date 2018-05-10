package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int pivot = leftIndex + 1;
		
		for (int i = pivot; i < rightIndex - 1; i++) {
			if (array[i].compareTo(array[i + 1]) < 0) {
				pivot++;
			}
		}
	}
}
