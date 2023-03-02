public class Kodu3 {

	public static int[] korduvadRead(int[][] a) {
		int[] korduvad = new int[3000];
		int loendur = 0;
		int pikkus = a.length;
		int kiire = Math.min(50, pikkus);

		for (int i = 0; i < kiire; i++) {
			quicksort(a[i], 0, pikkus - 1);
		}

		int jarjest = 1;
		double eelmine_elem = 0.5;
		int[] kandidaadid = a[0];

		for (int i = 1; i < kiire; i++) {
			for (int elem : kandidaadid) {
				if (elem == eelmine_elem) {
					jarjest++;
				} else {
					jarjest = 1;
				}

				if (binary(a[i], elem, jarjest)) {

					korduvad[loendur] = elem;
					loendur++;
				}
				eelmine_elem = elem;
			}

			kandidaadid = new int[loendur];
			System.arraycopy(korduvad, 0, kandidaadid, 0, loendur);
			loendur = 0;
			eelmine_elem = 0.5;
		}

		for (int i = kiire; i < pikkus; i++) {
			for (int elem : kandidaadid) {
				if (elem == eelmine_elem) {
					jarjest++;
				} else {
					jarjest = 1;
				}

				if (sisaldab(a[i], elem, jarjest)) {

					korduvad[loendur] = elem;
					loendur++;
				}
				eelmine_elem = elem;
			}

			kandidaadid = new int[loendur];
			System.arraycopy(korduvad, 0, kandidaadid, 0, loendur);
			loendur = 0;
			eelmine_elem = 0.5;
		}


		return kandidaadid;
	}

	private static void quicksort(int[] massiiv, int low, int high) {
		int i = low, j = high;
		int pivot = massiiv[low + (high - low) / 2];

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

	public static boolean sisaldab(int[] massiiv, int elem, int jarjest) {
		for (int j : massiiv) {
			if (j == elem) {
				if (jarjest == 1) {
					return true;
				} else {
					jarjest--;
				}
			}
		}
		return false;
	}

	public static boolean binary(int[] arr, int elem, int jarjest) {

		int first = 0;
		int last = arr.length - 1;
		int middle = (first + last) / 2;

		while (first <= last) {
			if (arr[middle] < elem) {
				first = middle + 1;
			} else if (arr[middle] == elem) {
				int temp = middle;
				while (jarjest > 1 && middle - 1 >= 0) {
					middle--;
					if (arr[middle] == elem) {
						jarjest--;
					} else {
						break;
					}
				}
				middle = temp;
				while (jarjest > 1 && middle + 1 <= arr.length - 1) {
					middle++;
					if (arr[middle] == elem) {
						jarjest--;
					} else {
						break;
					}
				}
				return jarjest == 1;
			} else {
				last = middle - 1;
			}
			middle = (first + last) / 2;
		}
		return false;
	}

	public static int[][][] hashing(int[][] a) {
		int[][][] massiiv = new int[2999][301][60];

		for (int i = 0; i < 2999; i++) {
			for (int j = 0; j < 60; j++) {
				massiiv[i][0][j] = 1;
			}
		}

		for (int i = 1; i < 3000; i++) {
			for (int j = 0; j < 3000; j++) {
				int elem = a[i][j];
				if (elem == 0) {
					int k = 0;
					while (massiiv[i - 1][0][k] == 0) {
						k++;
					}
					massiiv[i - 1][0][k] = 0;
					continue;
				}

				if (elem > 0) {
					int k = 0;
					while (massiiv[i - 1][(elem % 300) + 1][k] != 0) {
						k++;
					}
					massiiv[i - 1][(elem % 300) + 1][k] = elem;

				} else {
					int k = 0;
					while (massiiv[i - 1][(-elem % 300) + 1][k] != 0) {
						k++;
					}
					massiiv[i - 1][(-elem % 300) + 1][k] = elem;
				}

			}
		}
		return massiiv;
	}


	public static void main(String[] args) {

		long sum = 0;
		for (int j = 0; j < 10; j++) {
			int[][] a = new int[3000][3000];
			for (int k = 0; k < 3000; k++) {
				for (int l = 0; l < 3000; l++) {
					a[k][l] = (int) (Math.random() * 300);
				}
			}
			long start = System.currentTimeMillis();
			int[][][] mas = hashing(a);
			//int[] arr = korduvadRead(a);
			sum += System.currentTimeMillis() - start;
		}
		System.out.println(sum / 10);


	}
}