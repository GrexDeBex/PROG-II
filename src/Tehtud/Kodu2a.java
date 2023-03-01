package Tehtud; /***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 2a
 * Teema: Tsükklid, järjendid
 *
 * Autor: Gregor Rämmal
 *
 **********************************/


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Kodu2a {

	// Kolm massiivi, mis täidetakse failist loetud andmetega
	static String[] kuupäev;
	static String[] kellaaeg;
	static double[] temperatuur;


	 public static void loeAndmed(String failitee) {
	 	//loeb andmed failist nimega "failitee"
		try {
			// Loeme failist kõik read, eeldame et faili kodeering on UTF-8
			List<String> read = Files.readAllLines(Path.of(failitee), StandardCharsets.UTF_8);

			// Määrame massiivide pikkuse
			kuupäev = new String[read.size()];
			kellaaeg = new String[read.size()];
			temperatuur = new double[read.size()];
			for (int i = 0; i < read.size(); i++) {
				// Hakime read tühikute põhjal
				String[] jupid = read.get(i).split(" ");
				// Määrame hakitud jupid vastavatesse massiividesse
				kuupäev[i] = jupid[0];
				kellaaeg[i] = jupid[1];
				temperatuur[i] = Double.parseDouble(jupid[2]);
			}
		} catch (IOException e) {
			// Faili ei leitud või lugemisel esines viga - viskame erindi ja lõpetame töö
			throw new RuntimeException("Faili " + failitee + " lugemisel tekkis viga", e);
		}
	}//loeAndmed

	/**
	 * Näidismeetod (mitte kasutamiseks): leiab antud ajal mõõdetud temperatuuri.
	 *
	 * @param päev Kuupäev kujul aaaa-kk-pp.
	 * @param kell Kellaaeg kujul tt:mm:ss.
	 * @return Temperatuur kuupäeval <b>päev</b> ajal <b>kell</b>, NaN kui andmetest puudu.
	 */
	static double temperatuurValitudPäevalJaKellal(String päev, String kell) {
		for (int i = 0; i < temperatuur.length; i++) {
			if (kuupäev[i].equals(päev) && kellaaeg[i].equals(kell))
				return temperatuur[i];
		}
		return Double.NaN;
	}

	public static void main(String[] args) {
		loeAndmed("ilmAegTemp_2022.txt");

		System.out.println(aastaKesk());
		System.out.println(Arrays.toString(aastaMinMax()));
		System.out.println(Arrays.toString(pikimKasvavKahanev()));
		System.out.println(Arrays.toString(kuudeKeskmised()));
	}

	// Õpilase poolt teostatavad meetodid:


	/**
	 * Aasta keskmise temperatuuri leidmine
	 * @return double tüüpi aasta keskmine temperatuur.
	 */
	public static double aastaKesk() {
		double sum = 0;
		for (int i = 0; i < temperatuur.length; i++) {		// Võtab iga temperatuuri failis ning liidab need kokku
			sum += temperatuur[i];
		}
		return sum / temperatuur.length;
	}


	/**
	 * Aasta madalaima ja kõrgeima temperatuuri leidmine
	 * @return Double tüüpi massiiv, kus esimene liige on madalaim temperatuur ja teine kõrgeim
	 */
	public static double[] aastaMinMax() {
		double min = 0;
		double max = 0;
		for (int i = 0; i < temperatuur.length; i++) {		// võrdleb igat temperatuuri failis hetke madalaima/kõrgeimaga
			if (temperatuur[i] > max){
				max = temperatuur[i];
			}
			if (temperatuur[i] < min){
				min = temperatuur[i];
			}
		}

		return new double[] {min, max};
	}


	/**
	 * Leiab pikima perioodi, mil temperatuur ühtlaselt ainult kasvas või ainult kahanes
	 * @return String tüüpi massiiv, kus esimene liige on algus hetk ning teine liige on lõpphetk.
	 */
	public static String[] pikimKasvavKahanev() {
		String[] periood = new String[2];
		String start;
		boolean kasvav, kahanev;
		int loendur;
		int max = 1;
		int i = 0;

		while (i < temperatuur.length-1){	// Käib läbi iga kahe temperatuuri vahe
			kasvav = temperatuur[i] - temperatuur[i+1] < 0; // Perioodi algus
			kahanev = temperatuur[i] - temperatuur[i+1] > 0;
			start = kuupäev[i] + " " +  kellaaeg[i];
			loendur = 1;
			i++;

			while (kasvav && i < temperatuur.length-1){		// Kui algus on kasvav, siis vaatab, kui kaua on kasvav.
				kasvav = temperatuur[i] - temperatuur[i+1] < 0;
				if (kasvav){
					i++;
					loendur++;
				}
			}

			while (kahanev && i < temperatuur.length-1){	// Analoogiline kasvamisele
				kahanev = temperatuur[i] - temperatuur[i+1] > 0;
				if (kahanev){
					i++;
					loendur++;
				}
			}

			if (loendur > max){		// Kui periood oli pikem kui eelmine periood, siis uuendatakse suurimat perioodi
				periood[0] = start;
				periood[1] = kuupäev[i] + " " +  kellaaeg[i];
				max = loendur;
			}

		}

		return periood;
	}


	/**
	 * Leiab iga kuu keskmise temperatuuri
	 * @return double tüüpi massiiv, kus iga liige on vastava kuu keskmine temperatuur.
	 */
	public static double[] kuudeKeskmised() {
		double[] keskmised = new double[12];

		double sum, loendur;
		for (int i = 0; i < 12; i++) {
			sum = 0;
			loendur = 0;
			for (int j = 0; j < kuupäev.length; j++) {
				if (Integer.parseInt(kuupäev[j].substring(5,7)) == i+1){
					sum += temperatuur[j];
					loendur++;
				}
			}
			keskmised[i] = sum/loendur;
		}

		return keskmised;
	}
}


