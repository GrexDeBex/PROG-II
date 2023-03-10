import java.util.Arrays;

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


/*
 * Ül 2 programmi tööpõhimõte:
 *
 * On eraldi edetabeli funktsioon, mis võtab sisendeid, kus maatriksi ridu on 1000, ning aeglasem funktsioon teiste
 * sisendite jaoks. leiaJadad() funktsioon leiab igast reast ühtede jada mis on suurem kui 5, sest statistiliselt on
 * juhuslikus massiivis suurim ruut vähemalt 5 ning see asub jada sees, mis on vähemalt 6 pikk. Kui maatriksi suurim ruut
 * on väikseim kui 5 või selle üks ülemisest/alumisest küljest on jadas, mis on täpselt 5 ühte pikk, siis kasutatakse jällegi
 * aeglast fuktsiooni. leiaJadad() tagastab kahe tasandilise massiivi, kus esimesel tasemel on iga maatriksi rida ning iga rea sees
 * tuleb järjest leitud >5 jada pikkus ning esimese ühe indeks seal jadas. Seega paarisarvuline indeks on pikkus ja paaritu on indeks.
 *
 * See järel leitakse terve massiivi pikim ühtede jada, millest allapoole hakatakse ruute hiljem kontrollima, ning
 * iga rea pikim jada, et välistada ennetavalt ridasid.
 *
 * Käiakse läbi iga rea sobilikud jadad ning võrreldakse seda teise rea jadadega, kuni leitakse kaks ühtede jada
 * mis omavahel piisavalt kattuvad. See järel kontrollitakse veerge ja kui pole ruut, siis jätkatakse otsingut.
 * */

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
		if (maatriks.length != 1000)                        // Kui ei ole edetabeli sisend, kasutab paindlikuma funktsiooni
			return Kodu4aAeglane.ruutÜhtedest(maatriks);


		int[][] jadadeIndeksid = leiaJadad(maatriks);        // Leiab iga rea ühtede jadad, mis on >5
		int[] reaPikimJada = new int[1000];
		int külg = pikimadJadad(reaPikimJada, jadadeIndeksid);    // Leiab kõige suurema võimaliku ruudu pikkuse
		// ning iga rea kõige pikema ühtede jada

		for (; külg > 4; külg--) {                                // Kontrollib iga võimalikku küljepikkust
			for (int rida1 = 0; rida1 < 1001 - külg; rida1++) {    // Valib ruudu ülemise külje rea
				int rida2 = rida1 + külg - 1;                    // Valib ruudu alumise külje rea

				if (reaPikimJada[rida1] < külg || reaPikimJada[rida2] < külg)    // Kontrollib, kas nendel ridadel
					continue;                                                    // võib antud ruut eksisteerida

				for (int jada1 = 0; jadadeIndeksid[rida1][jada1] != 0; jada1 += 2) {    // Võrdleb kahe ruudu ühtede jadasid
					if (jadaKontroll(jadadeIndeksid, rida1, rida2, jada1, külg, maatriks))
						return külg;
				}
			}
		}


		return Kodu4aAeglane.ruutÜhtedest(maatriks);        // Kui tulemust ei leitud, kasutatakse aeglast meetodi
	}


	/**
	 * Leiab igast reast pikima ühtede jada ning salvestab selle.
	 *
	 * @param reaPikimJada   Massiiv, kuhu ridade pikimad jadad salvestatakse
	 * @param jadadeIndeksid Jadade indekseid ja nende pikkuseid sisaldav massiv
	 * @return Suurim rea jadapikkus.
	 */
	public static int pikimadJadad(int[] reaPikimJada, int[][] jadadeIndeksid) {
		int külg = 0;                                        // Leiab pikima ühtede jada maatriksis

		for (int i = 0; i < 1000; i++) {                    // Käib läbi iga rea
			int jada = 0;

			while (jadadeIndeksid[i][jada] != 0) {            // Käib läbi iga jada reas
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


	/**
	 * Leiab igast reast pikima ühtede jada ning salvestab selle.
	 *
	 * @param jadadeIndeksid Jadade indekseid ja nende pikkuseid sisaldav massiv
	 * @param rida1          Kontrollitava ruudu ülemise külje rida
	 * @param rida2          Kontrollitava ruudu alumise külje rida
	 * @param jada1          Kontrollitav jada
	 * @param külg           Kontrollitava ruudu pikkus
	 * @param maatriks       Antud maatriks
	 * @return True, kui leiti ruut.
	 */
	public static boolean jadaKontroll(int[][] jadadeIndeksid, int rida1, int rida2, int jada1, int külg, boolean[][] maatriks) {
		int i1 = jadadeIndeksid[rida1][jada1 + 1];        // Ülemise jada esimese ühe indeks
		int p1 = jadadeIndeksid[rida1][jada1];            // Ülemise jada pikkus

		if (p1 < külg)                                    // Pikkus ei tohi olla väiksem kui otsitav ruut
			return false;

		int jada2 = 0;

		while (jadadeIndeksid[rida2][jada2] != 0) {        // Käib läbi kõik alumise rea jadad
			int i2 = jadadeIndeksid[rida2][jada2 + 1];    // Alumise jada esimese ühe indeks
			int p2 = jadadeIndeksid[rida2][jada2];        // Alumise jada pikkus

			if (i1 + p1 - külg < i2)                    // Kui alumine jada möödub ülemisest jadast, siis tuleb vahetada
				break;                                    // ülemist jada

			if (p2 < külg || i2 + p2 - külg < i1) {        // Kui almine jada ei ole veel ülemiseni jõudnud, siis tuleb võtta järgmine
				jada2 += 2;
				continue;
			}

			int algus = Math.max(i1, i2);                // Jadade kattumise esimene sobilik võimaliku ruudu algus
			int kordus = Math.min(i1 + p1, i2 + p2) - algus - külg + 1;        // Arvutab, mitu ruutu võib nende jadade sisse mahtuda

			for (int i = 0; i < kordus; i++) {                    // Kontrollib iga võimalikku ruutu nende jadada sees
				if (kontrolliRuutu(külg, rida1, algus + i, maatriks)) {
					return true;
				}
			}

			jada2 += 2;
		}

		return false;
	}


	/**
	 * Leiab igast reast jadad, mis on pikemad kui 5 ühte ning salvestab nende pikkused ja indeksid.
	 *
	 * @param maatriks Antud maatriks
	 * @return Kahetasandiline maatriks, kus on esimesel tasandil iga maatriksi rida ning teisel tasandil on vaheldumisi
	 * ühtede jada pikkus ja selle jada esimese ühe indeks.
	 */
	public static int[][] leiaJadad(boolean[][] maatriks) {
		int[][] jadadeIndeksid = new int[1000][100];        // Kahetasandliline maatriks, 100 on valitud puhvriks

		for (int rida = 0; rida < 1000; rida++) {            // Käib läbi iga rea
			int loendur = 0;
			int indeks = 0;

			for (int elem = 0; elem < 1000; elem++) {    // Loeb üle kõik elemendid

				if (maatriks[rida][elem]) {        // Kui leiab ühe, siis loendab, mitu ühte järjest on
					loendur++;
					continue;
				}

				if (loendur > 5) {                // Kui jada on vähemalt 6 pikk, siis salvestab selle
					jadadeIndeksid[rida][indeks] = loendur;
					jadadeIndeksid[rida][indeks + 1] = elem - loendur;
					indeks += 2;
				}

				loendur = 0;        // Hakkab järgmise ühe otsing
				otsing:
				do {
					if (elem > 993)        // Kui enam ei saa reas olla 6 pikkust jada, siis lõpetab rea
						break;

					for (int i = 6; i > 0; i--) {                // Hüppab 6 kohta edasi ning hakkab tagurpidi vaatama, kas
						if (!maatriks[rida][elem + i]) {        // eksisteerib mõni 0
							elem += i;
							continue otsing;
						}
					}

					loendur = 6;
					elem += 6;
					break;

				} while (true);
			}

			if (loendur > 5) {                // Kontrollib kas viimane jada reas lõppes ära
				jadadeIndeksid[rida][indeks] = loendur;
				jadadeIndeksid[rida][indeks + 1] = 1000 - loendur;
			}
		}
		return jadadeIndeksid;
	}

	/**
	 * Kontrollib, kas võimaliku ruudu veerud klapivad
	 *
	 * @param külg     Otsitava ruudu küljepikkus
	 * @param rida1    Ruudu vasaku ülemise nurga rida
	 * @param elem1    Ruudu vasaku ülemise nurga veerg
	 * @param maatriks Antud maatriks
	 * @return True, kui leiti ruut.
	 */
	public static boolean kontrolliRuutu(int külg, int rida1, int elem1, boolean[][] maatriks) {
		int elem2 = elem1 + külg - 1;    // Leiab ruudu parema ülemise nurga
		for (int l = 1; l < külg - 1; l++) {        // Kontrollib vasakut/paremat külge
			if (!maatriks[rida1 + l][elem1] || !maatriks[rida1 + l][elem2])
				return false;
		}

		return true;
	}


	public static void main(String[] args) {
		int[][] a = {
				{8, 8, 7, 9, 1},
				{2, 1, 8, 7, 6},
				{4, 9, 5, 3, 2},
				{9, 5, 8, 4, 5},
				{6, 2, 7, 6, 8},
				{3, 8, 8, 7, 1},
				{7, 3, 3, 4, 9},
				{8, 0, 0, 9, 7},
		};

		boolean[][] b = {
				{true, true, true},
				{true, false, true},
				{true, true, true},
		};

		int[] c = pikendatudDiagonaal(a);
		System.out.println(Arrays.toString(c));
		System.out.println();

		long start = System.currentTimeMillis(); //AEG KÄIMA

		int parimN = ruutÜhtedest(b);

		long stop = System.currentTimeMillis();//AEG KINNI
		System.out.println("Aega kulus " + (stop - start)
				+ " milliskundit");
		System.out.println("Parim n=" + parimN);

	}

}