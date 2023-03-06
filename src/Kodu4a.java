public class Kodu4a {

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

	public static int ruutÜhtedest(boolean[][] maatriks) {
		if (maatriks.length != 1000) {
			return Kodu4aAeglane.ruutÜhtedest(maatriks);
		}

//		for (int i = 0; i < 80000; i++) {
//			System.out.println(i);
//		}

//		int x1 = 0;
//		int x2 = 0;

		int[] reaPikkused = new int[1000];
		int külg = suurimadRead(maatriks, reaPikkused);

		for (; külg > 0; külg--) {

			for (int rida1 = 0; rida1 < 1001 - külg; rida1++) {

				int rida2 = rida1 + külg - 1;
				if (reaPikkused[rida1] < külg || reaPikkused[rida2] < külg) {
					continue;
				}

				reaLugemine:
				for (int elem = 0; elem < 1000 - külg; elem++) {

					while (!maatriks[rida1][elem] || !maatriks[rida2][elem]) {
						if (elem > 999 - külg) {
							break reaLugemine;
						}
						if (!maatriks[rida1][elem + külg] || !maatriks[rida2][elem + külg]) {
							elem += külg;

						} else { // Otsing liigub tagasi
							elem++;
						}

					}


					for (int i = elem + külg - 1; i > elem; i--) {
						if (!maatriks[rida1][i] || !maatriks[rida2][i]) { // jätkab otsinugt edasi
							elem = i;
							continue reaLugemine;
						}
					}

					if (kontrolliRuutu(külg, rida1, elem, maatriks)) {
//						System.out.println(x1);
//						System.out.println(x2);
						return külg;
					}
				}
			}
		}


		return 0;
	}


	public static int suurimadRead(boolean[][] maatriks, int[] reaPikkused) {
		int ridadeMax = 0;

		for (int rida = 0; rida < 1000; rida++) {
			int loendur = 0;
			int max = 0;

			for (int elem = 0; elem < 1000; elem++) {
				if (maatriks[rida][elem]) {    // Kontrollib tagurpidi
					loendur++;

				} else {
					if (loendur > max) {
						max = loendur;
					}
					if (elem > 998 - max) {
						break;
					}
					if (!maatriks[rida][elem + max + 1]) {
						elem += max + 1;
					}

					loendur = 0;
				}
			}
			if (loendur > max) {
				max = loendur;
			}
			reaPikkused[rida] = max;

			if (max > ridadeMax) {
				ridadeMax = max;
			}
		}

		return ridadeMax;
	}

	public static boolean kontrolliRuutu(int ruuduPikkus, int rida1, int elem1, boolean[][] maatriks) {
		int elem2 = elem1 + ruuduPikkus - 1;
		for (int l = 1; l < ruuduPikkus - 1; l++) {
			if (!maatriks[rida1 + l][elem1] || !maatriks[rida1 + l][elem2]) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int n = 0;
		long start = System.currentTimeMillis();
		for (int k = 0; k < 100; k++) {
			boolean[][] arr = new boolean[1000][1000];

			for (int i = 0; i < 1000; i++) {
				for (int j = 0; j < 1000; j++) {
					double nr = Math.random();
					arr[i][j] = nr < 0.5;
				}
			}

//			for (int i = 0; i < 1000; i++) {
//				arr[0][i] = true;
//				arr[i][0] = true;
//				arr[1][i] = true;
//				arr[i][1] = true;
//			}

			n = ruutÜhtedest(arr);
		}
		System.out.println(n);
		System.out.println((double) (System.currentTimeMillis()-start) / 100);







	}

}