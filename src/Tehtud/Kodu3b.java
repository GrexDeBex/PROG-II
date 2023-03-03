package Tehtud;

import Tehtud.aeglane;

/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 3b
 * Teema: Tsükklid, järjendid
 *
 * Autor: Gregor Rämmal
 *
 **********************************/

public class Kodu3b {

	/**
	 * Leiab kõik elemendid maatriksist, mis esinevad igal real, kasutades kiiret või aeglast meetodid.
	 *
	 * @param a Antud maatriks
	 * @return Mittekahanev massiiv, kus on kõik korduvad arvud ning kui arvu on igal real mitu, siis on teda ka massiivis nii palju
	 */
	public static int[] korduvadRead(int[][] a) {
		if (a.length == 3000) {            // Kui n = 3000 siis kasutadakse edetabeli jaoks kiiremat funktsiooni
			return kiire(a);
		} else {                        // Kui n < 3000, siis kasutatakse aeglasemat, aga paindlikuma funktsiooni
			return aeglane.korduvadRead(a);
		}
	}

	/**
	 * Leiab kõik elemendid maatriksist, mis esinevad igal real, kui n = 3000
	 *
	 * @param a Antud maatriks
	 * @return Mittekahanev massiiv, kus on kõik korduvad arvud ning kui arvu on igal real mitu, siis on teda ka massiivis nii palju
	 */
	public static int[] kiire(int[][] a) {
		int[][] kandidaadid = hashing(a[0]);        // Teeb maatriksi esimsest reast võimalike kandidaatide hash tabeli
		int[][] salvesti = new int[301][30];        // Kasutatakse puhvrina sobivate kandidaatide salvestamiseks
		int[][] x;                                    // Ajutine tabel muutuja vahetamiseks

		for (int i = 0; i < 30; i++) {                // Viib 0 väärtuse massiivis kõik väärtused 1-ks, et saaks sinna
			salvesti[0][i] = 1;                        // salvestada nulle
		}

		for (int i = 1; i < 3000; i++) {            // Kontrollib kandidaatide kõlblikust ülejäänud 2999 massiiviga
			for (int elem : a[i]) {
				if (elem == 0) {
					nulliKontroll(kandidaadid, salvesti);    // Kontrollib, kas 0 on kandidaatide hulgas
				} else {
					elemendiKontroll(kandidaadid, salvesti, elem);        // Kontrollib, kas element on kandidaatide hulgas
				}
			}
			x = kandidaadid;            // Kannab uued kandidaadid "salvestist" "kandidaatidesse"
			kandidaadid = salvesti;
			salvesti = x;

			tühjendaSalvesti(salvesti);        // Tühjendab puhvri väärtused
		}

		int[] ajutine_tulemus = new int[3000];    // Hash tabeli teisendamiseks ajutine massiiv

		int loendur = 0;
		for (; kandidaadid[0][loendur] == 0; loendur++) {    // Kantakse kõik 0 väärtused tulemustesse
			ajutine_tulemus[loendur] = 0;
		}

		for (int i = 1; i < 301; i++) {                // Kantakse kõik ülejäänud väärtused tulemustesse
			int j = 0;
			while (kandidaadid[i][j] != 0) {
				ajutine_tulemus[loendur] = kandidaadid[i][j];
				loendur++;
				j++;
			}
		}

		int[] tulemus = new int[loendur];            // Tehakse uus tulemuste massiiv, mis on õige pikkusega nüüd
		System.arraycopy(ajutine_tulemus, 0, tulemus, 0, loendur);
		quicksort(tulemus, 0, loendur - 1);        // Sorteeritakse tulemused mittekasvavalt


		return tulemus;
	}

	/**
	 * Kontrollib, kas 0 on kordumise kandidaat ning uuendab kandidaatide tabelit.
	 *
	 * @param kandidaadid Kandidaatide tabel
	 * @param salvesti    Uuendatud kandidaatide tabel
	 */
	public static void nulliKontroll(int[][] kandidaadid, int[][] salvesti) {
		if (kandidaadid[0][0] == 1) {    // Kontrollib, kas on 0 kandidaate
			return;
		}

		int k = 0;                                // Kui on 0 kandidaate, siis eemaldatakse tabelist kõige viimane
		while (kandidaadid[0][k] != 1) {
			k++;
		}
		kandidaadid[0][k - 1] = 1;

		k = 0;
		while (salvesti[0][k] == 0) {        // Kandidaat kantakse puhvrisse esimesele vabale kohale
			k++;
		}
		salvesti[0][k] = 0;
	}

	/**
	 * Kontrollib, kas element on kordumise kandidaat ning uuendab kandidaatide tabelit.
	 *
	 * @param kandidaadid Kandidaatide tabel
	 * @param salvesti    Uuendatud kandidaatide tabel
	 * @param elem        Antud element.
	 */
	public static void elemendiKontroll(int[][] kandidaadid, int[][] salvesti, int elem) {
		int hashElem = elem;    // Hooliseb selle eest, et hash tabeli indeks tuleks alati positiivne
		if (elem < 0) {
			hashElem = -hashElem;
		}

		int i = 0;
		int k = 0;
		int indeks = (hashElem % 300) + 1;        // Hash tabeli indeks

		while (kandidaadid[indeks][i] != 0) {    // Kontrollib kas element asub indeksil
			if (kandidaadid[indeks][i] == elem) {

				int j = i;        // Kui element leitakse, siis tuuakse kõige viimane arv hash indeksil selle elemendi asemele
				while (kandidaadid[indeks][i + 1] != 0) {
					i++;
				}
				kandidaadid[indeks][j] = kandidaadid[indeks][i];    // Leitud element kustutatakse
				kandidaadid[indeks][i] = 0;

				while (salvesti[indeks][k] != 0) {        // Kandidaat kantakse puhvrisse esimesele vabale kohale
					k++;
				}
				salvesti[indeks][k] = elem;

				return;
			}
			i++;
		}
	}


	/**
	 * Tühjendab kandidaatide puhvri.
	 *
	 * @param salvesti Uuendatud kandidaatide tabel.
	 */
	public static void tühjendaSalvesti(int[][] salvesti) {
		int j = 0;
		while (salvesti[0][j] == 0) {    // Viib hash tabeli 0 indeksi väärtused 1-ks, et saaks 0 salvestada
			salvesti[0][j] = 1;
			j++;
		}

		for (int i = 1; i < 301; i++) {    // Muudab kõik ülejäänud väärtused 0-ks
			j = 0;
			while (salvesti[i][j] != 0) {
				salvesti[i][j] = 0;
				j++;
			}
		}
	}

	/**
	 * Kannab kõik võimalikud kandidaadid hash tabelisse.
	 *
	 * @param a Kandidaatide massiiv.
	 * @return Hash tabel kõigist kandidaatidest.
	 */
	public static int[][] hashing(int[] a) {
		int[][] massiiv = new int[301][30];        // Loob hash tabeli

		for (int i = 0; i < 30; i++) {            // Viib hash tabeli 0 indeksi väärtused 1-ks, et saaks 0 salvestada
			massiiv[0][i] = 1;
		}

		for (int i = 0; i < 3000; i++) {        // Kannab kõik 3000 kandidaati hash tabelisse
			int elem = a[i];
			int k = 0;

			if (elem == 0) {                    // Kannab 0 elemendi tabelisse
				while (massiiv[0][k] == 0) {
					k++;
				}

				massiiv[0][k] = 0;

			} else {
				int indeks;
				if (elem > 0) {                        // Hoolitseb selle eest, et indeks tuleks positiivne
					indeks = (elem % 300) + 1;
				} else {
					indeks = (-elem % 300) + 1;
				}

				while (massiiv[indeks][k] != 0) {    // Kannab elemndi esimesele vabale kohale
					k++;
				}

				massiiv[indeks][k] = elem;
			}
		}

		return massiiv;

	}

	/**
	 * Quicksort algoritmiga mittekahanev massiivi sortimis funktsioon
	 *
	 * @param massiiv   Antud massiiv
	 * @param minIndeks Väikseim sorteerimata indeks massiivis
	 * @param maxIndeks Suurim sorteerimata indeks massiivis
	 */
	public static void quicksort(int[] massiiv, int minIndeks, int maxIndeks) {
		int i1 = minIndeks;
		int i2 = maxIndeks;
		int keskElem = massiiv[minIndeks + (maxIndeks - minIndeks) / 2];    // Leiab massiivi keskel oleva elemendi
		int x;

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
	}
}