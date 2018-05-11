package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		 if (array != null && leftIndex < rightIndex && array.length > 0 
	    		 && rightIndex < array.length && leftIndex >= 0) {
		
			 if (leftIndex < rightIndex) {
				 int middle = (leftIndex + rightIndex) / 2;
				 sort(array, leftIndex, middle);
				 sort(array, middle + 1, rightIndex);
				 merge(array, leftIndex, middle, rightIndex);
			 }
		 }
	}
	
	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {
		T[] result = (T[]) new Comparable[array.length];

		for (int i = leftIndex; i <= rightIndex; i++) {
			result[i] = array[i];
		}
		
		int j = leftIndex;
		int k = middle + 1;
		int l = leftIndex;
		
		while (j <= middle && k <= rightIndex) {
			if (result[j].compareTo(result[k]) < 0) {
				array[l] = result[j];
				j++;
			} else {
				array[l] = result[k];
				k++;
			}
			l++;
		}
		
		while (j <= middle) {
			array[l] = result[j];
			l++;
			j++;
		}
		
		while (k <= rightIndex) {
			array[l] = result[k];
			l++;
			k++;
		}
	}
	
}
