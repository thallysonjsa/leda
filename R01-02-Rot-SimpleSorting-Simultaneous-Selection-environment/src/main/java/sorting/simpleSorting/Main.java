package sorting.simpleSorting;

import java.util.Arrays;

import sorting.variationsOfSelectionsort.SimultaneousSelectionsort;

public class Main {

	public static void main(String[] args) {
		InsertionSort insertion = new InsertionSort();
		SimultaneousSelectionsort simu = new SimultaneousSelectionsort();
		SelectionSort selec = new SelectionSort();
		Integer[] array = {4, 2, 7, 132, 9, 11, 62, 1};
		
		simu.sort(array, 0, array.length - 1);
		
		System.out.println(Arrays.toString(array));
		
	}

}
