import java.util.Arrays;

public class koopia{

	public static int[] korduvadRead(int[][] a){
		int pikkus = a.length;
		quicksort(a[0], 0, pikkus-1);

		int eelmine_elem = -1;
		int indeks = 0;
		int[] temp = new int[pikkus];

		for (int i = 0; i < pikkus; i++) {
			int elem = a[0][i];
			if (elem != eelmine_elem){
				temp[indeks] = elem;
				eelmine_elem = elem;
				indeks++;
			}
		}

		int[] arr = new int[indeks];
		System.arraycopy(temp, 0, arr, 0, indeks);

		for (int i = 1; i < pikkus; i++) {
			temp = new int[indeks];


			for (int j = 0; j < pikkus; j++) {
				int elem = a[i][j];

				if (sisaldab(arr, elem) && !sisaldab_1(temp, elem)){
					temp[indeks-1] = elem;
					indeks--;
				}
			}
			System.out.println(Arrays.toString(arr));

			int numbreid = temp.length - indeks;
			arr = new int[numbreid];
			System.arraycopy(temp, indeks, arr, 0, numbreid);
			quicksort(arr, 0, numbreid-1);
			indeks = numbreid;
		}



		return arr;
	}

	private static void quicksort(int[] massiiv, int low, int high) {
		int i = low, j = high;
		int kesk = massiiv[low + (high-low)/2];

		while (i <= j) {

			while (massiiv[i] < kesk) {i++;}

			while (massiiv[j] > kesk) {j--;}

			if (i <= j) {
				int temp = massiiv[i];
				massiiv[i] = massiiv[j];
				massiiv[j] = temp;
				i++;
				j--;
			}
		}

		if (low < j){
			quicksort(massiiv, low, j);
		}
		if (i < high){
			quicksort(massiiv, i, high);
		}
	}



	public static boolean sisaldab_1(int[] arr, int elem){
		for (int i = arr.length-1; i >= 0; i--) {
			if (elem == arr[i]){
				return true;
			}
		}
		return false;
	}


	public static boolean sisaldab(int[] arr, int elem){

		int first = 0;
		int last = arr.length - 1;
		int middle = (first + last) / 2;

		while (first <= last) {
			if (arr[middle] < elem) {
				first = middle + 1;
			} else if (arr[middle] == elem) {

				return true;
			} else {
				last = middle - 1;
			}
			middle = (first + last) / 2;
		}
		return false;
	}



	public static void main(String[] args) {
		int[][] a = new int[3000][3000];
		for (int i = 0; i < 3000; i++) {
			for (int j = 0; j < 3000; j++) {
				a[i][j] = (int) (Math.random()*300+1);
			}
		}

		long start = System.nanoTime();

		int[] arr = korduvadRead(a);
		System.out.println((System.nanoTime() - start)/1000000);
		System.out.println();
	}
}