package Tehtud;

import java.util.Arrays;

/**
 * Õpilase nimi: Gregor Rämmal
 * Programmeerimine 2 - KT 2 19.04.2023. Palume mitte "package" märksõna lisada,
 * koodis meetodite signatuure muuta, ega (ka tühje) meetodeid kustutada!
 * Kui meetodi sisu ei realiseeri, siis jäta rida "throw new UnsupportedOperationException("Implementeerimata!");" alles
 * Kui realiseerid, siis kustuta see ära. 
 */


public class Kt2 {

	/**
	 * Funktsioon saab parameetriks täisarvude massiivi ning tagastab vaheldumisi liites ja lahutades
	 * saadud nende arvude summa.
	 *
	 * @param a Täisarvude massiiv, millest saadakse summa
	 * @return  Saadud summa
	 */
	public static int vahelduvSumma(int [] a){
		return rek1(a, 0, 1);
	}


	/**
	 * Esimese ülesande unaarne rekursiivne funktsioon. Igas hargnemis punktis muudetakse
	 * järgmise liidetava märki ning suurendatakse arvude järge ühe võrra.
	 *
	 * @param a Liidetavate massiiv
	 * @param indeks Peab järge, millist arvu tuleb liita
	 * @param mark  Peab järge, kas arvu tuleb liita või lahutada
	 * @return  Käes oleva arvu ja kõigi järgmiste arvude summa.
	 */
	public static int rek1(int[] a, int indeks, int mark){
		if (indeks == a.length)                      // Kui liidetavaid enam pole, siis saadetakse tulemused tagasi
			return 0;

		int sum = rek1(a, indeks+1, mark*-1);   // Leiab kõigi järgnevate elementide summa

		return sum + a[indeks]*mark;        // Saadab tagasi hetke elemendi ja järgnevate summa
	}

	/**
	 * Leiab kõik kombinatsioonid etteantud arvudest, kus kõik arvud kombinatsioonis ei asu kõrvuti ning
	 * väljastab need.
	 *
	 * @param a Etteantud massiiv, kust kombinatsioone hakkatakse otsima.
	 * @return Kõigi kombinatsioonide arv
	 */
	public static int mitteKõrvuti(int[] a) {
		int[] salvesti = new int[a.length/2 + 1];       // Massiiv, kus salvestatakse hetkel käes olev kombinatsioon
		Arrays.fill(salvesti, -1);                  // -1 tähistab, et kombinatsioonis pole elementi sellel positsioonil

		System.out.println(Arrays.toString(new int[0]));    // Väljastab tühja massiivi

		return 1 + rek2(a, 0, salvesti);    // Tagastab kõigi kombinatsioonide summa koos tühja massiiviga
	}


	/**
	 * Igas hargnemispunktis kas lisatakse võimalusel kombinatsiooni käesolev arv või
	 * võetakse ette järgmine arv. Arvu lisamisel väljastatakse kombinatsioon.
	 *
	 * @param a Massiiv, kust kombinatsioone otsitakse
	 * @param indeks    Peab järge, mitmenda arvuga tegeletakse
	 * @param salvesti1 Salvestab kõik eelnevad arvud kombinatsioonis
	 * @return Kombinatsioonide arv
	 */
	public static int rek2(int[] a, int indeks, int[] salvesti1){

		if (indeks == a.length)         // Kui rohkem arve pole kombinatsiooni lisada, siis minnakse harus tagasi
			return 0;


		int tulemus = 0;                // Salvestab kombinatsioonide arvu

		int[] salvesti2 = new int[salvesti1.length];    // Tehakse koopia hetke seisust, kus hetke elementi ei lisata kombinatsiooni
		System.arraycopy(salvesti1, 0, salvesti2, 0, salvesti2.length);

		int i = 0;
		for (; i < salvesti1.length; i++)       // Leiab, mitu elementi on hetkel kombinatsioonis
			if (salvesti1[i] == -1)
				break;



		if (i > 0 && salvesti1[i-1] + 1 < indeks || i == 0){    // Kui eelmine element ei ole selle elemendi kõrval
			salvesti1[i] = indeks;                              // Lisab kombinatsiooni selle elemendi indeksi
			int[] kuva = new int[i+1];                          // Loob kuvamiseks massiivi, kus pole üleliigseid elemente
			for (int j = 0; j < kuva.length; j++)               // ning indeksite asemel on elementide väärtused
				kuva[j] = a[salvesti1[j]];

			System.out.println(Arrays.toString(kuva));          // Kuvab kombinatsiooni
			tulemus += 1;                                       // Loeb selle kombinatsiooni

			tulemus += rek2(a, indeks+1, salvesti1);      // Leiab järgmised kombinatsioonid


		}


		tulemus += rek2(a, indeks+1, salvesti2);			// Leiab järgmised kombinatsioonid

		return tulemus;
	}


	/**
	 * Võtab kahe mittekahaneva massiivi elemendid ning väljastab need kasvavalt ning ilma kordudusteta
	 *
	 * @param a Esimene massiiv
	 * @param b Teine massiiv
	 */
	public static void põimi(int[] a, int[] b){
		int eelmine = Integer.MIN_VALUE;			// Muutuja peab meeles eelmist elementi. mis kuvati
		rek3(a, b, 0, 0, eelmine);	// Väljastab elemendid
	}

	/**
	 * Funktsioon tegeleb kahe massiivi kõige väiksema elemendiga, mida pole veel väljastatud
	 * ning võimalusel väljastab selle
	 *
	 * @param a Esimene massiiv
	 * @param b Teine massiiv
	 * @param indeks1 Esimese massiivi järg
	 * @param indeks2 Teise massiivi järg
	 * @param eelmine Eelmise väljastatud elemendi väärtus
	 */
	public static void rek3(int[] a, int[] b, int indeks1, int indeks2, int eelmine){

		if (indeks1 == a.length){						// Kui a massiiv sai otsa, kuvatakse kõik alles jäänud b elemendid
			for (; indeks2 < b.length; indeks2++) {
				if (eelmine != b[indeks2]){				// Kontrollib, kas elementi saab kuvada
					System.out.println(b[indeks2]);
					eelmine = b[indeks2];
				}
			}

			return;
		}

		if (indeks2 == b.length){						// Kui b massiiv sai otsa, kuvatakse kõik alles jäänud a elemendid
			for (; indeks1 < a.length; indeks1++) {
				if (eelmine != a[indeks1]){				// Kontrollib, kas elementi saab kuvada
					System.out.println(a[indeks1]);
					eelmine = a[indeks1];
				}
			}

			return;
		}


		int elem1 = a[indeks1];
		int elem2 = b[indeks2];

		if (elem1 < elem2){			// Kui järgmine element on a oma
			if (eelmine != elem1){	// Võrdleb eelmise elenediga
				System.out.println(elem1);

				eelmine = elem1;
			}
			indeks1++;				// Võtab a massiivist järgmise arvu
		}else {						// Kui järgmine element on b oma
			if (eelmine != elem2){	// Võrdleb eelmise elenediga
				System.out.println(elem2);

				eelmine = elem2;
			}
			indeks2++;				// Võtab b massiivist järgmise arvu
		}

		rek3(a, b, indeks1, indeks2, eelmine);	// Kuvab järgmised arvud
	}

	public static void main(String[] args) {
		System.out.println(mitteKõrvuti(new int[5]));
	}
}