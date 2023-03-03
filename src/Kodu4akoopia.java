public class Kodu4akoopia {

	public static int[] pikendatudDiagonaal(int[][] a) {
		int kõrgus = a.length;
		int laius = a[0].length;
		int[] tulemus = new int[2 * laius - 1];
		int i = 0;
		int j = 0;
		int loendur = 0;
		int iKordaja = 1;
		int jKordaja = 1;

		while (true) {
			tulemus[loendur] = a[i][j];
			loendur++;

			if (i == kõrgus - 1) {
				iKordaja = -1;
			}
			if (j == laius - 1) {
				jKordaja = -1;
			}
			if (i == 0 && iKordaja == -1) {
				iKordaja = 1;
			}

			i += iKordaja;
			j += jKordaja;

			if (j == 0) {
				tulemus[loendur] = a[i][j];
				break;
			}
		}


		return tulemus;
	}

	public static int ruutÜhtedest(boolean[][] maatriks){
		int massiiviPikkus = maatriks.length;

		long s = System.currentTimeMillis();

		int ruuduPikkus = suurimRuut(maatriks);


		System.out.println(System.currentTimeMillis() - s);
		for (; ruuduPikkus > 0; ruuduPikkus--) {
			int võimalusi = massiiviPikkus-ruuduPikkus+1;

			for (int iNurk = 0; iNurk < võimalusi; iNurk++) {
				for (int jNurk = 0; jNurk < võimalusi; jNurk++) {

					if (kontrolliRuutu(ruuduPikkus, iNurk, jNurk, maatriks)){
						return ruuduPikkus;
					}
				}
			}
		}

		return 0;
	}

	public static int suurimRuut(boolean[][] maatriks){
		int pikkus = maatriks.length;
		int max1 = 0;
		int max2 = 0;
		int max3 = 0;
		int max4 = 0;

		if (pikkus == 1){
			if (maatriks[0][0]){
				return 1;
			}else {
				return 0;
			}
		}


		for (boolean[] rida : maatriks) {
			int loendur = 0;
			int max = 0;

			for (boolean elem : rida) {
				if (elem) {
					loendur++;

				} else {
					if (loendur > max) {
						max = loendur;
					}
					loendur = 0;
				}
			}

			if (loendur > max) {
				max = loendur;
			}

			if (max > max1) {
				max2 = max1;
				max1 = max;

			} else if (max > max2) {
				max2 = max;
			}
		}

		for (int i = 0; i < pikkus; i++) {
			int loendur = 0;
			int max = 0;

			for (int j = 0; j < pikkus; j++) {
				if (maatriks[j][i]) {
					loendur++;

				} else {
					if (loendur > max) {
						max = loendur;
					}
					loendur = 0;
				}
			}

			if (loendur > max) {
				max = loendur;
			}

			if (max > max3) {
				max4 = max3;
				max3 = max;
				if (max4 >= max2){
					break;
				}

			} else if (max > max4) {
				max4 = max;
				if (max4 >= max2){
					break;
				}
			}
		}
		return Math.min(max2, max4);
	}

	public static boolean kontrolliRuutu(int ruuduPikkus, int iNurk, int jNurk, boolean[][] maatriks){
		for (int k = 0; k < 2; k++) {
			for (int l = ruuduPikkus-1; l >= k; l--) {
				switch (k) {
					case 0:
						if (!maatriks[iNurk+l][jNurk]){
							return false;
						}
						break;
					case 1:
						if (!maatriks[iNurk][jNurk+l]){
							return false;
						}
						break;
				}
			}
		}

		iNurk = iNurk + ruuduPikkus - 1;
		jNurk = jNurk + ruuduPikkus - 1;
		for (int k = 0; k < 2; k++) {
			for (int l = ruuduPikkus-2; l >= k; l--) {
				switch (k) {
					case 0:
						if (!maatriks[iNurk-l][jNurk]){
							return false;
						}
						break;
					case 1:
						if (!maatriks[iNurk][jNurk-l]){
							return false;
						}
						break;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		boolean[][] arr = new boolean[1000][1000];

		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				double nr = Math.random();
				arr[i][j] = nr < 0.5;
			}
		}

		for (int i = 0; i < 1000; i++) {
			arr[0][i] = true;
			arr[i][0] = true;
			arr[1][i] = true;
			arr[i][1] = true;
		}


		long start = System.currentTimeMillis();

		int n = ruutÜhtedest(arr);

		System.out.println(System.currentTimeMillis() - start);
		System.out.println(n);
	}

}