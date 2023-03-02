

public class koopia2 {

	public static int[] korduvadRead(int[][] a){
		int pikkus = a.length;

		int max = 0;
		for (int i = 0; i < pikkus; i++) {
			if (max < a[0][i]) {
				max = a[0][i];
			}
		}

		int[] arr = new int[max];

//		for (int i = 0; i < pikkus; i++) {
//			for (int j = 0; j < pikkus; j++) {
//				arr[a[i][j]-1]++;
//			}
//		}
//

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
	}
}