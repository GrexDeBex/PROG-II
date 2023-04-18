package Tehtud;

/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 7b
 * Teema: Rekursioon
 *
 * Autor: Gregor Rämmal
 *
 **********************************/


class Kodu7b {



	/**
	 * Leiab, mitu erinevat võimalust on võimalik paigutada 2*m x 2*n ruudustikule maksimaalne arv kuningaid
	 *
	 * @param m Ridade poolarv
	 * @param n Veergude poolarv
	 * @return Kõikide võimaluste arvu
	 */
	public static long kuningad(int m, int n) {
		int[][] algneRead = new int[(int) Math.pow(4, n) + 1][];    // Siia salvestatakse kõik võimalikud kombinatsioonid
		// mis ühe rea peal saavad olla

		algneRead[0] = new int[]{1};                    // Salvestatakse indeks esimesele kohale, mis peab järge, mitmes kombinatsioon on käsil
		algneRead[1] = new int[n];                        // Lisab esimese kombinatsiooni jaoks massiivi
		reaKombinatsioonid(algneRead, 0, n);        // Lisatakse kõik võimalikud rea kombinatsioonid massiivi

		int pikkus = algneRead.length;
		for (int i = 1; i < pikkus; i++)                // Loeb üle, mitu erinevat kombinatsiooni leiti
			if (algneRead[i][0] == 0) pikkus = i;
		pikkus--;                                        // Tulemusest lahutatakse maha indeksi massiiv

		int[][] koikRead = new int[pikkus][];            // Kantakse tulemused üle uude massiivi
		System.arraycopy(algneRead, 1, koikRead, 0, pikkus);


		int[][] kombinatsioonid = new int[(int) Math.pow(4, n)][];    // Kahe tasandiline massiiv, kus esimese tasandi indeksi määrab rea kombinatsioon
		kombinatsioonideOtsimine(kombinatsioonid, koikRead);                // ning sellega saadakse massiiv, mis sisaldab kõiki järgmise rea kombinatsioone

		if (m == 1 && n == 1) return pikkus;            // Tegeleb erandliku situatsiooniga

		long[][] ajalugu = new long[m - 1][(int) Math.pow(4, n)];    // Siia salvestatkse juba kontrollitud võimaluste arvud
		// Esimese indeksi määrab rida, mida muudetakse, teise indeksi määrab
		// rea kombinatsioon ning saadakse varem läbitud võimaluste arv

		long tulemus = 0;
		for (int[] rida : koikRead)                        // Katsetakse läbi kõik esimese rea võimalused
			tulemus += katsed(tetraToDec(rida), m - 1, ajalugu, kombinatsioonid);


		return tulemus;
	}



	/**
	 * Katsetab läbi kombinatsioonid kõikide võimalike ridadega, kus on ülesande tingimused täidetud
	 *
	 * @param eelmineRida     Eelimse rea kombinatsioon
	 * @param riduJaanud      Määramata ridade arv
	 * @param ajalugu         Kõigist eelnevatest kombinatsioonidest tulnud tulemuste massiiv
	 * @param kombinatsioonid Igale reale kõik võimalikud järgnevad read
	 * @return Tulemuste arv
	 */
	public static long katsed(int eelmineRida, int riduJaanud, long[][] ajalugu, int[][] kombinatsioonid) {
		if (riduJaanud == 0) return 1;        // Kui katsega saab kogu ruudustik täidetud

		long tulemus = 0;

		if (riduJaanud == 2) {                // Loetakse üle kõigi kombinatsioonide arv iga järgmise rea korral
			for (int kombinatsioon : kombinatsioonid[eelmineRida])
				tulemus += kombinatsioonid[kombinatsioon].length;

			return tulemus;
		}


		for (int kombinatsioon : kombinatsioonid[eelmineRida]) {        // Katsetatakse läbi iga kombinatsioon sellel real
			long ajalooTulemus = ajalugu[riduJaanud - 1][kombinatsioon];
																		// Kui see kombinatsioon on juba varem olnud sellel
			if (ajalooTulemus > 0)                                      // real, siis kasutatakse eelmist tulemust
				tulemus += ajalooTulemus;
			else {                                                        // Proovitakse läbi iga kombinatsioon sellel real
				long katseTulemus = katsed(kombinatsioon, riduJaanud - 1, ajalugu, kombinatsioonid);
				ajalugu[riduJaanud - 1][kombinatsioon] = katseTulemus;    // Salvestatkse tulemus tuleviku tarbeks
				tulemus += katseTulemus;
			}
		}

		return tulemus;
	}



	/**
	 * Leiab kõik võimalikud kombinatsioonid, mis võivad asetseda ühel real
	 *
	 * @param massiiv massiiv[0][0] peab järge, millist kombinatsiooni luuakse ning ülejäänud kohtadele salvestakse kombinatsioonid
	 * @param veerg   peab järge kombinatsiooni siseselt, kui palju veel on vaja positsioone lisada
	 * @param n       kombinatsiooni pikkus
	 */
	public static void reaKombinatsioonid(int[][] massiiv, int veerg, int n) {

		// massiiv[0][0] hoiab hetke kombinatsiooni indeksit
		if (veerg == n) {                                // Kui uus kombinatsioon on loodud
			if (massiiv[0][0] < massiiv.length - 1) {    // Siis uuendatakse kombinatsioonide arvu
				massiiv[0][0] += 1;                        // ja luuakse uue kombinatsiooni jaoks massiiv
				massiiv[massiiv[0][0]] = new int[n];
			}
			return;
		}

		int[] eelmised = new int[veerg];                // Kopeeritakse kõik eelnevad arvud selles kombinatsioonis
		System.arraycopy(massiiv[massiiv[0][0]], 0, eelmised, 0, veerg);

		/* Kasutades ülesande kirjelduse joonist, iga kuningas asub suures ruudus, mis koosneb neljast väiksest ruudust
		seega on igal kuningal 4 võimalust paiknemiseks, kus numbrid tähistavad kuninga positsiooni.
		[1, 2]
		[3, 4]
		*/

		boolean k = veerg > 0 && (massiiv[massiiv[0][0]][veerg - 1] == 2 || massiiv[massiiv[0][0]][veerg - 1] == 4);
		// k kontrollib, kas järgmine ruut saab olla 1 või 3.

		int arv = (k) ? 2 : 1;        // Olenevalt arvu väärtusest luuakse kombinatsioon kas 1,2,3,4 või 2,4 positsioonidega

		for (int i = arv; i < 5; i += arv) {
			massiiv[massiiv[0][0]][veerg] = i;                    // Määrab positsiooni
			reaKombinatsioonid(massiiv, veerg + 1, n);    // Jätkab järgmise veeru täitmist
			if (i != 4) System.arraycopy(eelmised, 0, massiiv[massiiv[0][0]], 0, veerg);
		}                                // Kopeerib kõik eelnevad positsioonid uuesti kombinatsiooni
	}



	/**
	 * Leiab igale võimalikule reale kõik järgnevad võimalikud read
	 *
	 * @param kombinatsioonid Kasutatakse järgnevate ridade salvestamiseks
	 * @param read            Kõik võimalikud kombinatsioonid ühel real
	 */
	public static void kombinatsioonideOtsimine(int[][] kombinatsioonid, int[][] read) {

		tsukel:
		for (int[] rida : read) {                    // Leiab igale võimalikule reale temale järgnevad kombinatsioonid

			int pikkus = rida.length;
			int[] koopia = new int[pikkus];            // Käes olevast reast tehakse koopia
			System.arraycopy(rida, 0, koopia, 0, pikkus);

			for (int i = 0; i < pikkus; i++)        // See, kas positsioon on 1 või 2, ei määra järgmiste ridade kombinatsioone
				if (koopia[i] == 2) {                // seega otsitakse, kas kombinatsioonid on juba varem leitud
					koopia[i] = 1;
					int indeks = tetraToDec(koopia);    // Kombinatsioon konverteeritakse indeksiks

					if (kombinatsioonid[indeks] != null) {
						kombinatsioonid[tetraToDec(rida)] = kombinatsioonid[indeks];
						continue tsukel;
					}
				}


			pikkus = read.length;                // Kui ülemisel real on mingi kuningas positsiooniga 3 või 4
			for (int elem : rida)                // siis maksimaalne kombinatsioonide arv on 2 korda väiksem iga ruudu kohta
				if (elem > 2) pikkus >>= 1;

			int[] tulemus = new int[pikkus];    // Salvestatkse kõik kombinatsioonid siia

			int indeks = 0;
			for (int[] rida1 : read)
				if (kontroll(rida, rida1)) {    // Kontrollitakse, kas read sobivad omavahel
					tulemus[indeks] = tetraToDec(rida1);    // Lisatakse sobiv kombinatsioon
					indeks++;
				}

			int[] tulemusKoopia = new int[indeks];        // Tulemuste massiivist kustutakse null väärtused
			System.arraycopy(tulemus, 0, tulemusKoopia, 0, indeks);

			indeks = tetraToDec(rida);                    // Lisatakse reale kõik võimalikud kombinatsioonid
			kombinatsioonid[indeks] = tulemusKoopia;
		}
	}



	/**
	 * Kontrollib, kas kaks rida saavad aseteseda üksteise kohal
	 *
	 * @param rida1 Ülemine rida
	 * @param rida2	Alumine rida
	 * @return	Kas need read saavad üksteise kohal olla
	 */
	public static boolean kontroll(int[] rida1, int[] rida2) {
		int pikkus = rida1.length;
		for (int i = 0; i < pikkus; i++) {
			int elem = rida1[i];

			if (elem == 1 || elem == 2) continue;        // Kontrollida tuleb ainult positsioone 3 ja 4

			if (rida2[i] == 1 || rida2[i] == 2) return false;    // Kontrollib alumist ruutu

			if (i != 0 && rida2[i - 1] == 2 && elem == 3) return false;    // Kontrollib alumist vasakut ruutu

			if (i != pikkus - 1 && rida2[i + 1] == 1 && elem == 4) return false;    // Kontrollib alumist paremat ruutu
		}

		return true;
	}

	/**
	 * Teisendab neljandkoodist kümnendkoodile, et muuta loodud rida temale vastavaks indeksiks,
	 * mida kasutatkse ajaloo ja järgnevate kombinatsioonide otsimiseks
	 *
	 * @param tetra Rida kuningate positsioonidega, kus igal positsioonil on neli võimalust
	 * @return Indeks kümnendkoodis
	 */
	public static int tetraToDec(int[] tetra) {
		int kordaja = 1;
		int sum = 0;
		for (int i = tetra.length - 1; i >= 0; i--) {
			sum += kordaja * (tetra[i] - 1);
			kordaja <<= 2;
		}

		return sum;
	}


	public static void main(String[] args) {

		long s = System.currentTimeMillis();

		for (int i = 0; i < 10; i++)
			System.out.println(kuningad(10, 10));


		System.out.println((System.currentTimeMillis() - s) / 10.0);


	}
}
