public class Kodu3b {

	public static int[] korduvadRead(int[][] a){
		if (a.length == 3000){
			return kiirus(a);
		}else {
			return Kodu3.korduvadRead(a);
		}
	}

	public static int[] kiirus(int[][] a){
		int[][] kandidaadid = hashing(a[0]);
		int[][] salvesti = new int[201][60];

		for (int i = 0; i < 60; i++) {
			salvesti[0][i] = 1;
		}

		for (int i = 1; i < 3000; i++) {
			for (int elem : a[i]) {
				int k = 0;

				if (elem == 0){
					if (kandidaadid[0][0] == 1){
						continue;
					}
					while (kandidaadid[0][k] != 1){k++;}
					kandidaadid[0][k-1] = 1;

					k = 0;
					while (salvesti[0][k] != 1){k++;}
					salvesti[0][k] = 0;

					continue;
				}

				if (elem > 0){
					for (int j = 0; j < 60; j++) {
						if (kandidaadid[(elem % 200) + 1][j] == elem){
							kandidaadid[(elem % 200) + 1][j] = 0;
							while (salvesti[(elem % 200) + 1][k] != 0){k++;}
							salvesti[(elem % 200) + 1][k] = elem;
							break;
						}
					}

				} else {
					for (int j = 0; j < 60; j++) {
						if (kandidaadid[(-elem % 200) + 1][j] == elem){
							kandidaadid[(-elem % 200) + 1][j] = 0;
							while (salvesti[(elem % 200) + 1][k] != 0){k++;}
							salvesti[(elem % 200) + 1][k] = elem;
							break;
						}
					}

				}
			}
			int[][] temp = kandidaadid;
			kandidaadid = salvesti;
			salvesti = temp;

			for (int j = 0; j < 60; j++) {
				salvesti[0][j] = 1;
			}
			for (int j = 1; j < 201; j++) {
				for (int k = 0; k < 60; k++) {
					salvesti[j][k] = 0;
				}
			}

		}


		int[] temp = new int[3000];
		int loendur = 0;

		for (int i = 0; i < 60; i++) {
			if (kandidaadid[0][i] != 0){
				break;
			}
			temp[loendur] = 0;
			loendur++;
		}

		for (int i = 1; i < 201; i++) {
			for (int j = 0; j < 60; j++) {
				if (kandidaadid[i][j] != 0){
					temp[loendur] = kandidaadid[i][j];
					loendur++;
				}
			}
		}

		int[] tulemus = new int[loendur];
		System.arraycopy(temp, 0, tulemus, 0, loendur);
		quicksort(tulemus, 0, loendur-1);
		return tulemus;

	}

	public static int[][] hashing(int[] a){
		int[][] massiiv = new int[201][60];

		for (int i = 0; i < 60; i++) {
			massiiv[0][i] = 1;
		}


		for (int i = 0; i < 3000; i++) {
			int elem = a[i];
			int k = 0;

			if (elem == 0){
				while (massiiv[0][k] == 0){
					k++;
				}
				massiiv[0][k] = 0;
				continue;
			}

			if (elem > 0){
				while (massiiv[(elem % 200)+1][k] != 0){
					k++;
				}
				massiiv[(elem % 200)+1][k] = elem;

			} else {
				while (massiiv[(-elem % 200)+1][k] != 0){
					k++;
				}
				massiiv[(-elem % 200)+1][k] = elem;
			}

		}

		return massiiv;

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
		System.out.println(arr.length);
		System.out.println(Kodu3b.korduvadRead(a).length);
	}
}