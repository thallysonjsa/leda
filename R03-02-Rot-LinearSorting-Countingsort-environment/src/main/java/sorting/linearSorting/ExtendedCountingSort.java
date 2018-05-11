package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
			Integer menor = array[leftIndex];
			Integer maior = array[leftIndex];
			
			for (int i = leftIndex + 1; i <= rightIndex; i++) {
				if (array[i].compareTo(menor) < 0) {
					menor = array[i];
				}
				if (array[i].compareTo(maior) > 0) {
					maior = array[i];
				}
			}
			
			Integer[] helper = new Integer[maior - menor + 1];
			
			for (int i = 0; i < helper.length; i++) {
				helper[i] = 0;
			}
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				helper[array[i] - menor]++;
			}
			
			for (int i = 1; i < helper.length; i++) {
				helper[i] = helper[i] + helper[i - 1];
			}
			
			Integer[] retorno = new Integer[rightIndex - leftIndex + 1];
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				retorno[helper[array[i] - menor] - 1] = array[i];
				helper[array[i] - menor]--;
			}
			
			int j = 0;
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = retorno[j];
				j++;
			}
		}
	}

}
