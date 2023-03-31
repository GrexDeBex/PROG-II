/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 5b
 * Teema: Rekursioon
 *
 * Autor: Gregor Rämmal
 *
 **********************************/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Kodu5b {

	/**
	 * Funktsioon võtab paarisarvu ning tagastab kõik võimalikud liidetavateks jaotamise viisid, kus liidetavad võivad olla
	 * 2, 4 ja 6.
	 *
	 * @param n Arv, mida joatatakse liidetavateks
	 * @return	Massiiv, kus on järjestaud kasvavas järjestuses liidetavate massiivid
	 */
	static int[][] summad(int n) {
		List<int[]> T = new ArrayList();						// Sobivate tulemuste massiiv

		for (int i = 2; i < 7 && n > 0; i += 2) {				// Proovitakse 2, 4 ja 6 liidetavaid
			if (n == i) {										// Kui i on viimane liidetav hakkab funktsioon tagasi liikuma
				T.add(new int[]{i});
				break;
			}

			for (int[] l : summad(n - i))					// Võetakse iga tulemus ja lisatakse algusesse i
				if (l[0] != i) {								// Kontrollib, et kaks sama numbrit ei oleks järjest
					int[] a = new int[l.length + 1];
					System.arraycopy(l, 0, a, 1, l.length);
					a[0] = i;									// Liidetavate massiiv, kuhu on hetke liidetav ette pandud
					T.add(a);									// Salvetab tulemuse
				}

		}

		return T.toArray(new int[0][]);							// Teisendab massiiviks
	}

	/**
	 * Võtab sisendiks kaks sõnade massiivi ning tagastab kõik laused, mis neist moodustada saab nii et, sisendi massiivides
	 * olev järjekord säiliks
	 *
	 * @param a Sõnade massiiv.
	 * @param b Sõnade massiiv
	 * @return	Lausete massiiv
	 */
	static String[] sonePoime(String[] a, String[] b) {
		List<String> t = new ArrayList();		// Listi salvestatakse sobivad laused

		O(a,b,t);				// Situatsioon, kus järgmine sõna tuleb a massiivist
		O(b,a,t);				// Situatsioon, kus järgmine sõna tuleb b massiivist

		return t.toArray(new String[0]);	// Teisendab tulemuse massiiviks
	}

	/**
	 * Moodustab sõnadest laused
	 *
	 * @param a Alles jäänud sõnad
	 * @param b Alles jäänud sõnad
	 * @param t Seni moodustatud laused
	 */
	static void O(String[] a, String[] b, List t){
		if (a.length == 1)						// Kui võetav sõna on oma massiivi viimane
			t.add(a[0] + " " + String.join(" ", b));	// Lisatakse kõik ülejäänud sõnad hetke sõna järele
		else for (String l : sonePoime(Arrays.copyOfRange(a, 1, a.length), b))		// Kui sõnad pole veel otsas, siis minnakse tagasi hargnemis funktsiooni
			t.add(a[0] + " " + l);				// Lisatakse igale seni saadud lausele ette sõna, millest hargnemine alguse sai
	}



	public static void main(String[] args) {

	}

}
