package Tehtud;

/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 2 boonus
 * Teema: Tsükklid, järjendid
 *
 * Autor: Gregor Rämmal
 *
 **********************************/

public class Kodu2Boonus {

	/**
	 * Leiab massiivist kõige pikema aritmeetilise jada.
	 * @param sisend Antud massiiv.
	 * @return Pikima artimeetilise jada liikmete arv.
	 */
	static int pikimAritmeetilineJada(int[] sisend) {
		int suurim_jada = 2; 					// Kuna alati on jadas 2 liiget, siis suurim_jada on vähemalt 2
		int pikkus = sisend.length;

		int max_liige = sisend[0];
		int min_liige = sisend[0];

		for (int i = 1; i < pikkus; i++) {		// Leiab massiivi väikseima ja suurima liikme
			if (sisend[i] > max_liige){
				max_liige = sisend[i];
			}
			if (sisend[i] < min_liige){
				min_liige = sisend[i];
			}
		}

		int min_vahe, max_vahe, esimene_liige, vahe, jada;

		for (int a1 = 0; a1 < pikkus-1; a1++) {			// a1 on aritmeetilise jada esimese liikme indeks
			esimene_liige = sisend[a1];

			min_vahe = (min_liige - esimene_liige) / suurim_jada;	// Leiab antud massiivis suurima ja väiksema vahe
			max_vahe = (min_liige - esimene_liige) / suurim_jada;	// liikmete vahel antud esimese liikme korral

			for (int a2 = a1+1; a2 < pikkus; a2++) {				// a2 on jada teine liige
				vahe = sisend[a2] - esimene_liige;

				if (vahe > max_vahe || vahe < min_vahe){			// Kontrollib, et vahe ei oleks liiga suur või liiga väike
					continue;
				}

				jada = kontroll(a2, vahe, pikkus, sisend);			// Leiab antud jada liikmete arvu
				if (jada > suurim_jada){
					suurim_jada = jada;
				}
			}
		}
		return suurim_jada;
	}

	/**
	 * Leiab antud liimest alates aritmeetilise jada liikmete arvu.
	 * @param a2 Jada teise liikme indeks.
	 * @param vahe Aritmeetilise jada liikmete vahe.
	 * @param pikkus Antud massiivi pikkus.
	 * @param sisend Antud massiiv.
	 * @return Antud aritmeetilise jada liikmete arv.
	 */
	static int kontroll(int a2, int vahe, int pikkus, int[] sisend){
		int liikmed = 2;						// Kuna esimesed kaks liiget on juba võetud, siis on liikmeid 2
		int eelmine_liige = a2;
		for (int i = a2+1; i < pikkus; i++) {		// Leiab, kui pikaks antud aritmeetiline jada läheb
			if (sisend[i] - sisend[eelmine_liige] == vahe){
				liikmed++;
				eelmine_liige = i;
			}
		}
		return liikmed;
	}

	public static void main(String[] args) {

	}
}
