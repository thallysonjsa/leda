package recursao;

public class Mainzao {

	public static void main(String[] args) {
		MetodosRecursivos mr = new MetodosRecursivos();
		Object[] array = {1, 2, 3, 4, 5, 6};
		System.out.println(mr.calcularFatorial(5));
		System.out.println(mr.countNotNull(array));
	}

}
