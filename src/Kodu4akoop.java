public class Kodu4akoop {

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
			return Kodu4aAeglane.ruutÜhtedest(maatriks);
		}


		int[] reaPikkused = suurimadRead(maatriks);

		int max = 0;
		for (int i = 0; i < 1000; i++) {
			if (reaPikkused[i] > max){
				max = reaPikkused[i];
			}
		}

		for (int külg = max; külg > 0; külg--) {

			for (int rida1 = 0; rida1 < 1001 - külg; rida1++) {
				if (reaPikkused[rida1] < külg || reaPikkused[rida1 + külg-1] < külg){
					continue;
				}
				int loendur = 0;

				for (int elem = 0; elem < 1000; elem++) {
					if (maatriks[rida1][elem]){
						loendur++;
					}else {
						if (elem > 1000-külg){
							break;
						}
						loendur = 0;
					}
					if (loendur == külg){
						if (kontrolliRuutu(külg, rida1, elem-külg+1, maatriks)){
							return külg;
						}

						loendur--;
					}
				}
			}
		}



		return 0;
	}

	public static int[] suurimadRead(boolean[][] maatriks){
		int[] reaPikkused = new int[1000];

		for (int i = 0; i < 1000; i++) {
			int loendur = 0;
			int max = 0;

			for (int j = 0; j < 1000; j++) {
				if (maatriks[i][j]) {
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
		}
		return reaPikkused;
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