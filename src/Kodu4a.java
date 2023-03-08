/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 4a
 * Teema: Tsükklid, järjendid
 *
 * Autor: Gregor Rämmal
 *
 **********************************/


public class Kodu4a {

	/**
	 * Leiab arvude diagonaalil olevad arvud alates indeksist 1,1 kuni diagonaali uuesti vasaku küljeni jõudmiseni
	 * arvestades peegeldumisega maatriksi külgedelt.
	 *
	 * @param a Antud maatriks
	 * @return Arvude massiiv sellises järjestuses nagu nad diagonaalil esinevad.
	 */
	public static int[] pikendatudDiagonaal(int[][] a) {
		int kõrgus = a.length;                            // Maatriksi kõrgus
		int laius = a[0].length;                        // Maatriksi laius
		int[] tulemus = new int[2 * laius - 1];            // diagonaali laius suhtes maatriksi laiusega
		int i = 0;                                        // Diagonaali elemendi indeks
		int j = 0;
		int loendur = 0;                                // Diagonaali lisatud elementide arv
		int iKordaja = 1;                                // Määrab diagonaali peegeldumise suuna
		int jKordaja = 1;

		while (true) {                                    // Tsükkel kestab, kuni diagonaal puudutab vasakut külge
			tulemus[loendur] = a[i][j];
			loendur++;

			if (i == kõrgus - 1) {                        // Kui diagonaal puudutab lage, siis muutub diagonaali suund
				iKordaja = -1;
			}
			if (j == laius - 1) {                        // Kui diagonaal puudutab paremat külge, siis muutub diagonaali suund
				jKordaja = -1;
			}
			if (i == 0 && iKordaja == -1) {                // Kui diagonaal puudutab põrandat, siis muutub diagonaali suund
				iKordaja = 1;
			}

			i += iKordaja;
			j += jKordaja;

			if (j == 0) {                                // Lõpetab vasaku külje puudutamisel tsükli
				tulemus[loendur] = a[i][j];
				break;
			}
		}


		return tulemus;
	}

	/**
	 * Leiab suurima ühtedest koosneva ruudu kasutades kiiret või aeglast meetodi
	 *
	 * @param maatriks Antud maatriks
	 * @return Suurima ruudu küljepikkus
	 */
	public static int ruutÜhtedest(boolean[][] maatriks) {
		if (maatriks.length != 1000)                     	// Kui ei ole edetabeli kontroll, kasutab paindlikuma funktsiooni
			return Kodu4aAeglane.ruutÜhtedest(maatriks);

		long start = System.nanoTime();


		int[][] jadadeIndeksid = leiaJadad(maatriks);
		int[] reaPikimJada = new int[1000];
		int külg = pikimadJadad(reaPikimJada, jadadeIndeksid);

		for (; külg > 4; külg--) {
			for (int rida1 = 0; rida1 < 1001 - külg; rida1++) {
				int rida2 = rida1 + külg - 1;

				if (reaPikimJada[rida1] < külg || reaPikimJada[rida2] < külg)
					continue;

				for (int jada1 = 0; jadadeIndeksid[rida1][jada1] != 0; jada1 += 2) {

					if (jadaKontroll(jadadeIndeksid, rida1, rida2, jada1, külg, maatriks)) {
//						while (System.nanoTime() - start < 7200000){}
						return külg;
					}
				}
			}
		}


		return Kodu4aAeglane.ruutÜhtedest(maatriks);
	}

	public static int pikimadJadad(int[] reaPikimJada, int[][] jadadeIndeksid){
		int külg = 0;

		for (int i = 0; i < 1000; i++) {
			int jada = 0;

			while (jadadeIndeksid[i][jada] != 0) {
				int jadaPikkus = jadadeIndeksid[i][jada];

				if (reaPikimJada[i] < jadaPikkus)
					reaPikimJada[i] = jadaPikkus;

				jada += 2;
			}

			if (reaPikimJada[i] > külg)
				külg = reaPikimJada[i];
		}
		return külg;
	}

	public static boolean jadaKontroll(int[][] jadadeIndeksid, int rida1, int rida2, int jada1, int külg, boolean[][] maatriks) {
		int i1 = jadadeIndeksid[rida1][jada1 + 1];
		int p1 = jadadeIndeksid[rida1][jada1];

		if (p1 < külg)
			return false;

		int jada2 = 0;


		while (jadadeIndeksid[rida2][jada2] != 0) {
			int i2 = jadadeIndeksid[rida2][jada2 + 1];
			int p2 = jadadeIndeksid[rida2][jada2];

			if (i1 + p1 - külg < i2)
				break;

			if (p2 < külg || i2 + p2 - külg < i1) {
				jada2 += 2;
				continue;
			}

			int algus = Math.max(i1, i2);
			int kordus = Math.min(i1 + p1, i2 + p2) - algus - külg + 1;

			for (int i = 0; i < kordus; i++) {
				if (kontrolliRuutu(külg, rida1, algus + i, maatriks)) {
					return true;
				}
			}

			jada2 += 2;
		}

		return false;
	}


	/**
	 * Leiab kõik elemendid maatriksist, mis esinevad igal real, kasutades kiiret või aeglast meetodid.
	 *
	 * @param maatriks Antud maatriks
	 * @return Mittekahanev massiiv, kus on kõik korduvad arvud ning kui arvu on igal real mitu, siis on teda ka massiivis nii palju
	 */
	public static int[][] leiaJadad(boolean[][] maatriks) {
		int[][] jadadeIndeksid = new int[1000][100];

		for (int rida = 0; rida < 1000; rida++) {
			int loendur = 0;
			int indeks = 0;

			for (int elem = 0; elem < 1000; elem++) {
				if (maatriks[rida][elem]) {
					loendur++;
					continue;
				}

				if (loendur > 5) {
					jadadeIndeksid[rida][indeks] = loendur;
					jadadeIndeksid[rida][indeks + 1] = elem - loendur;
					indeks += 2;
				}
				loendur = 0;

				otsing:
				do {
					if (elem > 993)
						break;

					int i = 6;
					for (; i > 0; i--) {
						if (!maatriks[rida][elem + i]) {
							elem += i;
							continue otsing;
						}
					}
					loendur = 6;
					elem += 6;
					break;
				} while (true);
			}

			if (loendur > 5) {
				jadadeIndeksid[rida][indeks] = loendur;
				jadadeIndeksid[rida][indeks + 1] = 1000 - loendur;
			}
		}
		return jadadeIndeksid;
	}

	/**
	 * Leiab kõik elemendid maatriksist, mis esinevad igal real, kasutades kiiret või aeglast meetodid.
	 *
	 * @param maatriks Antud maatriks
	 * @return Mittekahanev massiiv, kus on kõik korduvad arvud ning kui arvu on igal real mitu, siis on teda ka massiivis nii palju
	 */
	public static boolean kontrolliRuutu(int ruuduPikkus, int rida1, int elem1, boolean[][] maatriks) {
		int elem2 = elem1 + ruuduPikkus - 1;
		for (int l = 1; l < ruuduPikkus - 1; l++) {
			if (!maatriks[rida1 + l][elem1] || !maatriks[rida1 + l][elem2])
				return false;
		}

		return true;
	}


	public static void main(String[] args) {
		int n = 0;
		long start = System.currentTimeMillis();
		for (int k = 0; k < 1000; k++) {
			boolean[][] arr = new boolean[1000][1000];

			for (int i = 0; i < 1000; i++) {
				for (int j = 0; j < 1000; j++) {
					double nr = Math.random();
					arr[i][j] = nr < 0.5;
				}
			}


			n = ruutÜhtedest(arr);

//			int b = Kodu4aAeglane.ruutÜhtedest(arr);
//			if (n != b){
//				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//				System.out.println(n);
//				System.out.println(b);
//			}
		}
		System.out.println((double) (System.currentTimeMillis() - start) / 1000);


//		boolean[][] arr = new boolean[1000][1000];
//
//		for (int i = 0; i < 1000; i++) {
//			for (int j = 0; j < 1000; j++) {
//				double nr = Math.random();
//				arr[i][j] = nr < 0.5;
//			}
//		}
//
//		long start = System.currentTimeMillis();
//		int n = ruutÜhtedest(arr);
//
//
//		System.out.println((System.currentTimeMillis()-start));
//		System.out.println(n);
//		System.out.println(Kodu4aAeglane.ruutÜhtedest(arr));


	}

}