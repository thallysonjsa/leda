package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
			int gap = array.length;
			boolean trocou = true;
			while (gap > 1 || trocou) {
				gap = (int) (gap / 1.25);
				int i = leftIndex;
				trocou = false;
				while (i + gap < array.length) {
					if (array[i].compareTo(array[i + gap]) > 0) {
						Util.swap(array, i, i + gap);
						trocou = true;
					}
					i++;
				}
			}
		}
	}
}
