package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two selection sorts simultaneously. In a same
 * iteration, a selection sort pushes the greatest elements to the right and
 * another selection sort pushes the smallest elements to the left. At the end
 * of the first iteration, the smallest element is in the first position (index
 * 0) and the greatest element is the last position (index N-1). The next
 * iteration does the same from index 1 to index N-2. And so on. The execution
 * continues until the array is completely ordered.
 */
public class SimultaneousSelectionsort<T extends Comparable<T>> extends
		AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex > rightIndex || leftIndex > 0 || rightIndex > array.length || array == null)
			return;
		int menor;
		int maior;
		
		while (leftIndex < rightIndex) {
			menor = leftIndex;
			maior = rightIndex;
			
			for (int i = menor + 1; i <= rightIndex; i++) {
				if (array[i].compareTo(array[menor]) < 0) {
					menor = i;
				}
			}
			Util.swap(array, menor, leftIndex);
			
			for (int j = maior; j >= leftIndex; j--) {
				if (array[j].compareTo(array[maior]) > 0) {
					maior = j;
				}
			}
			Util.swap(array, maior, rightIndex);
			leftIndex++;
			rightIndex--;
		}
	}
}
