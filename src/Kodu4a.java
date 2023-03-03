import java.util.Arrays;

public class Kodu4a{

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
		if (maatriks.length != 1000){
			return Kodu4akoopia.ruutÜhtedest(maatriks);
		}

		int[] reaPikkused = new int[1000];

		int ruuduPikkus = suurimRuut(maatriks, reaPikkused);
		for (; ruuduPikkus > 0; ruuduPikkus--) {

			for (int rida = 0; rida < 1001 - ruuduPikkus; rida++) {
				if (reaPikkused[rida] < ruuduPikkus){
					continue;
				}
				int loendur = 0;

				for (int elem = 0; elem < 1000; elem++) {
					if (maatriks[rida][elem]){
						loendur++;
					}else {
						loendur = 0;
					}
					if (loendur == ruuduPikkus){
						if (kontrolliRuutu(ruuduPikkus, rida, elem-ruuduPikkus+1, maatriks)){
							return ruuduPikkus;
						}
						loendur--;
					}
				}
			}
		}



		return 0;
	}

	public static int suurimRuut(boolean[][] maatriks, int[] reaPikkused){
		int max1 = 0;
		int max2 = 0;
		int max3 = 0;
		int max4 = 0;


		for (int i = 0; i < 1000; i++) {
			int loendur = 0;
			int max = 0;

			for (boolean elem : maatriks[i]) {
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

			reaPikkused[i] = max;

			if (max > max1) {
				max2 = max1;
				max1 = max;

			} else if (max > max2) {
				max2 = max;
			}
		}

		for (int i = 0; i < 1000; i++) {
			int loendur = 0;
			int max = 0;

			for (int j = 0; j < 1000; j++) {
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

		for (int l = 1; l < ruuduPikkus ; l++) {
			if (!maatriks[iNurk+l][jNurk]){
				return false;
			}
		}

		iNurk = iNurk + ruuduPikkus - 1;
		jNurk = jNurk + ruuduPikkus - 1;

		if (!maatriks[iNurk][jNurk]){
			return false;
		}

		for (int l = 1; l < ruuduPikkus-1 ; l++) {
			if (!maatriks[iNurk-l][jNurk] || !maatriks[iNurk][jNurk-l]){
				return false;
			}
		}


		return true;
	}

	public static void main(String[] args) {
		boolean[][] arr = new boolean[1000][1000];

		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				arr[i][j] = false;
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