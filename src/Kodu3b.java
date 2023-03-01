import java.util.Arrays;

public class Kodu3b{

	public static int[] korduvadRead(int[][] a){
		int[] korduvad = new int[3000];
		int loendur = 0;
		int pikkus = a.length;


		quicksort(a[0], 0, pikkus-1);


		boolean korduv;
		int elem;
		int jarjest = 1;
		int eelmine_elem = -1;


		for (int i = 0; i < pikkus; i++) {
			elem = a[0][i];
			korduv = true;

			if (elem == eelmine_elem){
				jarjest++;
			}else{
				jarjest = 1;
			}

			for (int j = 1; j < pikkus; j++) {
				if (eiSisalda(a[j], elem, jarjest)){
					korduv = false;
					break;
				}
			}


			if (korduv){
				korduvad[loendur] = elem;
				loendur++;
			}

			eelmine_elem = elem;
		}

		int[] tulemus = new int[loendur];

		System.arraycopy(korduvad, 0, tulemus, 0, loendur);

		return tulemus;
	}

	private static void quicksort(int[] massiiv, int low, int high) {
		int i = low, j = high;
		int pivot = massiiv[low + (high-low)/2];

		while (i <= j) {
			while (massiiv[i] < pivot) {
				i++;
			}

			while (massiiv[j] > pivot) {
				j--;
			}
			if (i <= j) {
				int temp = massiiv[i];
				massiiv[i] = massiiv[j];
				massiiv[j] = temp;
				i++;
				j--;
			}
		}
		if (low < j)
			quicksort(massiiv, low, j);
		if (i < high)
			quicksort(massiiv, i, high);
	}

	public static boolean eiSisalda(int[] massiiv, int elem, int jarjest){
		int pikkus = massiiv.length;
		for (int i = 0; i < pikkus; i++) {
			if (massiiv[i] == elem){
				if (jarjest == 1){
					return false;

				}else{
					jarjest--;
				}
			}
		}
		return true;
	}


	public static void main(String[] args) {
		int[][] a = new int[3000][3000];
		for (int i = 0; i < 3000; i++) {
			for (int j = 0; j < 3000; j++) {
				a[i][j] = (int) (Math.random()*300);
			}
		}
		long start = System.nanoTime();
		int[] arr = korduvadRead(a);
		System.out.println(System.nanoTime() - start);

	}
}