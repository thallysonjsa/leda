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
public class SimultaneousSelectionsort<T extends Comparable<T>> extends AbstractSorting<T> {
   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (array != null && leftIndex < rightIndex && array.length > 0 
    		 && rightIndex < array.length && leftIndex >= 0) {

         int iterador = rightIndex;

         for (int i = leftIndex; i < rightIndex; i++) {
            int menor = i;
            for (int j = i + 1; j <= rightIndex; j++) {
               if (array[j].compareTo(array[menor]) < 0)
                  menor = j;
            }
            Util.swap(array, menor, i);

            int maior = iterador;
            for (int k = iterador; k >= leftIndex; k--) {
               if (array[k].compareTo(array[maior]) > 0) {
                  maior = k;
               }
            }
            Util.swap(array, iterador, maior);
            iterador--;
         }
      }
   }
}
