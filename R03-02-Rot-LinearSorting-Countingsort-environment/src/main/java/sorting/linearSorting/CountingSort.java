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
		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex <= array.length) {
			Integer[] retorno = new Integer[rightIndex - leftIndex]; 
			int maior = array[leftIndex];
		
			for (int i = 0; i < array.length; i++) {
				if (array[i] > maior) {
					maior = array[i];
				}
			}
		
			Integer[] helper = new Integer[maior];
		
			for (int i = 0; i < helper.length; i++) {
				helper[i] = 0;
			}
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				helper[array[i]]++;
			}
		
			for (int i = leftIndex + 1; i <= rightIndex; i++) {
				helper[i] = helper[i] + helper[i - 1];
			}
		
			for (int i = rightIndex; i >= leftIndex; i--) {
				
			}
		}
	}

}
