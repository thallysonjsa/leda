package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
			int maior = array[leftIndex];
			for (int i = leftIndex + 1; i <= rightIndex; i++) {
				if (array[i].compareTo(maior) > 0) {
					maior = array[i];
				}
			}
		
			Integer[] helper = new Integer[maior + 1];
		
			for (int i = 0; i < helper.length; i++) {
				helper[i] = 0;
			}
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				helper[array[i]]++;
			}
		
			for (int i = 1; i < helper.length; i++) {
				helper[i] = helper[i] + helper[i - 1];
			}
		
			Integer[] retorno = new Integer[rightIndex - leftIndex + 1];
			
			for (int i = rightIndex; i >= leftIndex; i--) {
				retorno[helper[array[i]] - 1] = array[i];
				helper[array[i]]--;
			}
			
			int j = 0;
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = retorno[j];
				j++;
			}
		}
	}

}
