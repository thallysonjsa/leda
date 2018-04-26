package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		int result = 0;
		// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR A SOMA
		// DOS EMENTOS DE UM ARRAY
		if (array.length == 1)
			result += array[0];
		else {
			result += calcularSomaArray(array, 0);
		}
		return result;
	}
	
	private int calcularSomaArray(int[] array, int indice) {
		int soma = 0;
		if (indice == array.length) {
			return soma;
		} else {
			soma += array[indice];
			indice++;
			soma = soma + calcularSomaArray(array, indice);
		}
		return soma;
	}
	
	public long calcularFatorial(int n) {
		long result = 1;
		// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR E IMPRIMIR
		// O FATORIAL DO PARAMETRO
		// DE ACORDO COM O QUE FOI MOSTRADO NO EXERCCICIO. OBSERVE QUE SENDO O
		// METODO
		// RECURSIVO, O FATORIAL DOS NUMEROS ANTERIORES TAMBEM VAO SER IMPRESSOS
		if (n == 0 || n == 1)
			result = 1;
		else {
			result = (n * calcularFatorial(n - 1));
		}
		
		return result;
	}

	public int calcularFibonacci(int n) {
		int result = 1;
		// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR E IMPRIMIR
		// O N-ESIMO TERMO
		// DA SEQUENCIA DE FIBONACCI, QUE TEM A SEGUINTE LEI DE FORMACAO: O
		// PRIMEIRO E SEGUNDO NUMEROS
		// DA SEQUENCIA SÃO 1. A PARTIR DO TERCEIRO TERMO, CADA TERMO DA
		// SEQUENCIA É DADO
		// PELA SOMA DOS OUTROS DOIS ANTERIORES. OBSERVE QUE SENDO O METODO
		// RECURSIVO, O FIBONACCI DOS NUMEROS ANTERIORES TAMBEM VAO SER
		// IMPRESSOS
		return result;
	}

	public int countNotNull(Object[] array) {
		int result = 0;
		// TODO IMPLEMENTE AQUI O CODIGO QUE CONTA (USANDO RECURSAO) A
		// QUANTIDADE DE ELEMENTOS NAO NULOS
		// DE UM ARRAY DE OBJETOS RECEBIDO COMO PARAMETRO
		if (array.length == 1) {
			result++;
		} else {
			result = countNotNull(array, 0);
		}
		
		return result;
	}
	
	private int countNotNull(Object[] array, int indice) {
		int result = 0;
		if (array[indice] != null)
			result++;
		if (indice < array.length - 1)
			result = result + countNotNull(array, ++indice);
		
		return result;
	}

	public long potenciaDe2(int expoente) {
		int result = 1;
		// TODO IMPLEMENTE (USANDO RECURSAO) O CODIGO PARA PRODUZIR A N-ESIMA
		// POTENCIA
		// DE 2
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = 0;
		// TODO IMPLEMENTE SEU CODIGO (USANDO RECURSAO) DE ENCONTRAR O n-ESIMO
		// TERMO
		// DA PROGRESSAO ARITMETICA, DADO O TERMO INICIAL E A RAZAO
		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = 1;
		// TODO IMPLEMENTE SEU CODIGO (USANDO RECURSAO) DE ENCONTRAR O n-ESIMO
		// TERMO
		// DA PROGRESSAO GEOMETRICA, DADO O TERMO INICIAL E A RAZAO
		return result;
	}
	
	
}
