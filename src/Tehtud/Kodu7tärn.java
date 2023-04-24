package Tehtud; /***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 7*
 * Teema: Rekursioon
 *
 * Autor: Gregor Rämmal
 *
 **********************************/


import java.util.Random;

class Kodu7tärn {

	/**
	 * Funktsioon loendab kõik viisid, kuidas saab etteantud raha eest kulutada kogu summa etteantud toodete ostmiseks.
	 * Kombinatsioonide arvu leiab abifunktsioon "funk", siin funktsioonis valmistatakse andmed ette.
	 *
	 * @param a Massiiv kõigi toodete hindadega
	 * @param p Summa, mida kulutatakse
	 * @return Kõikide võimaluste arv
	 */
	public static int ostmisViisid(double[] a, double p) {
		quicksort(a, 0, a.length - 1);	// Sorteerib toodete hinnad

//		int tulemus = Aeglane.ostmisViisid(a, p);
		int tulemus = funk(a, p, a.length - 1);		// Leiab kõik võimalikud kombinatsioonid

		if (tulemus == 0) 									// Kui pole ühtegi võimaliku kombinatsiooni midagi osta
			return 1;										// siis tagastatakse, et võimalus on mitte osta

		return tulemus;
	}

	/**
	 * Leiab kõigi kombinatsioonide arvu
	 *
	 * @param tooted Kõikide toodete hinnad mittekahanevad järjestuses
	 * @param jaak   Kulutada jäänud summa
	 * @param indeks Määrab ära, millise väärtusega saavad järgmised tooted olla
	 * @return Tagastab kõigi võimaluste arvu
	 */
	public static int funk(double[] tooted, double jaak, int indeks) {
		int tulemus = 1;									// Kui indeks jõuab 0-ni, siis on ainult üks võimalus
															// jada jätkata

		if (tooted.length > 1)								// Kui indeks jõuab 1-ni, siis siin arvutatakse alles jäänud
			tulemus += (int) (jaak / tooted[1]);			// kombinatsioonid

		if (tooted.length > 2) {							// Kui indeks jõuab 2-ni, siis siin arvutatakse alles jäänud
			int piir = (int) (jaak / tooted[2]);			// kombinatsioonid
			tulemus += piir;
			for (int i = 1; i <= piir; i++)
				tulemus += (int) ((jaak - tooted[2] * i) / tooted[1]);
		}

		for (; indeks > 2; indeks--) {						// Käiakse läbi kõik toodete kombinatsioonid
			double uusJaak = jaak - tooted[indeks];

			if (uusJaak < tooted[0])						// Kui jääk on lähemal kui kõige odavama toote hind, siis
				tulemus += (uusJaak < 0) ? 0 : 1;			// see on sobiv kombinatsioon, kui jääk on negatiivne, siis
			else											// see kombinatsioon ei sobi
				tulemus += funk(tooted, uusJaak, indeks);	// Järgmine toode ei saa olla kallim kui eelmine
		}



		return tulemus;
	}


	/**
	 * Quicksort algoritmiga mittekahanev massiivi sortimis funktsioon
	 *
	 * @param massiiv   Antud massiiv
	 * @param minIndeks Väikseim sorteerimata indeks massiivis
	 * @param maxIndeks Suurim sorteerimata indeks massiivis
	 */
	public static void quicksort(double[] massiiv, int minIndeks, int maxIndeks) {
		int i1 = minIndeks;
		int i2 = maxIndeks;
		double keskElem = massiiv[minIndeks + (maxIndeks - minIndeks) / 2];    // Leiab massiivi keskel oleva elemendi
		double x;

		while (i1 <= i2) {            // Sorteerib kuni väikseim ja suurim indeks mööduvad
			while (massiiv[i2] > keskElem) {    // Leiab massiivi lõpust keskmisest elemendist väiksema elemendi
				i2--;
			}
			while (massiiv[i1] < keskElem) {    // Leiab massiivi algusest keskmisest elemendist suurema elemendi
				i1++;
			}

			if (i1 <= i2) {                // Kui algusepoolne element on suurem kui lõpupoolne, siis nende kohad vahetatakse
				x = massiiv[i1];
				massiiv[i1] = massiiv[i2];
				massiiv[i2] = x;

				i2--;
				i1++;
			}
		}

		if (minIndeks < i2) {                    // Kui massiiv ei ole sorteeritud, siis minnakse uuele ringile
			quicksort(massiiv, minIndeks, i2);
		}
		if (i1 < maxIndeks) {
			quicksort(massiiv, i1, maxIndeks);
		}
	}

	public static void main(String[] args) {
		long s = System.currentTimeMillis();

		Random generator = new Random(15);

		double[] a1 = new double[25];
		for (int i = 0; i < a1.length; i++)
			a1[i] = generator.nextDouble() * 100;

		double[] a2 = new double[10];
		for (int i = 0; i < a2.length; i++)
			a2[i] = generator.nextDouble() * 100;

		double p = 500;


		for (int i = 0; i < 1; i++)
			System.out.println(ostmisViisid(a1, p));
		System.out.println(559126819);
		System.out.println();


		for (int i = 0; i < 1; i++)
			System.out.println(ostmisViisid(a2, p));
		System.out.println(6924935);
		System.out.println();


		System.out.println(System.currentTimeMillis() - s);
	}

}
