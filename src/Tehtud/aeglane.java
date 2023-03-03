package Tehtud;

public class aeglane {

	/**
	 * Leiab kõik elemendid maatriksist, mis esinevad igal real
	 *
	 * @param a Antud maatriks
	 * @return Mittekahanev massiiv, kus on kõik korduvad arvud ning kui arvu on igal real mitu, siis on teda ka massiivis nii palju
	 */
	public static int[] korduvadRead(int[][] a) {
		int[] korduvad = new int[3000];		// Kasutatkse kandidaatide kontrollimisel puhvrina
		int loendur = 0;					// Loeb massiivis sobilike kandidaate
		int pikkus = a.length;

		Kodu3b.quicksort(a[0], 0, pikkus - 1);	// Sorteerib maatriksi esimese rea mittekahanevaks

		int jarjest = 1;	// Loeb mitu ühe väärtusega kandidaati on
		double eelmine_elem = 0.5;	// Kasutatakse võrdlemiseks, et kas mitu sama väärtusega elementi on järjest
		int[] kandidaadid = a[0];	// Sobivad kandidaadid

		for (int i = 1; i < pikkus; i++) { 	// Käiakse läbi kõik massiivid
			for (int elem : kandidaadid) {
				if (elem == eelmine_elem) {	// Loeb mitmest järjestust tuleb kontrollida
					jarjest++;
				} else {
					jarjest = 1;
				}

				if (sisaldab(a[i], elem, jarjest)) {	// Kui element on massiivis, siis kantakse puhvrisse

					korduvad[loendur] = elem;
					loendur++;
				}
				eelmine_elem = elem;
			}

			kandidaadid = new int[loendur];		// Võtab andmed puhvrist ning uuendab kandidaate
			System.arraycopy(korduvad, 0, kandidaadid, 0, loendur);
			loendur = 0;
			eelmine_elem = 0.5;
		}


		return kandidaadid;
	}

	/**
	 * Kontrollib, kas element või elementide hulk on massiivis
	 *
	 * @param massiiv Antud massiiv.
	 * @param elem    Antud element.
	 * @param jarjest Elemendi korduste arv.
	 * @return Kas element või elemntide hulk oli massiivis.
	 */
	public static boolean sisaldab(int[] massiiv, int elem, int jarjest) {
		for (int j : massiiv) {		// Käib läbi iga elemendi massiivis
			if (j == elem) {
				if (jarjest == 1) {	// Kui elemente peab olema rohkem kui 1, siis otsib edasi
					return true;
				} else {
					jarjest--;
				}
			}
		}
		return false;
	}


	public static void main(String[] args) {
	}
}