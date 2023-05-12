package Tehtud; /***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 * <p>
 * Kodutöö nr 9a
 * Teema: Sõned
 * <p>
 * Autor: Gregor Rämmal
 *
 **********************************/

import Tehtud.Voistleja;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Kodu9a {

	/**
	 * Võtab failist iga rea ning loob nende põhjal võistleja objekti. Igale objektile lisatakse atribuutitena nimi,
	 * aeg igas vahepunktis ning finišis ning koht igas vahepunktis ning finišis.
	 *
	 * @param failinimi Fail võistlejate tulemustega
	 * @return	List kõigist võistlejatest
	 */
	public static ArrayList<Voistleja> loeVoistlejadSisse(String failinimi) {
		ArrayList<Voistleja> voistlejad = new ArrayList<>();	// Siia lisatakse iga loodud võistleja

		try (Scanner sc = new Scanner(new File(failinimi))){
			while (sc.hasNextLine()){
				String[] rida = sc.nextLine().split("\\t");	// Võistleja andmed

				String nimi = rida[0];
				int[] ajad = new int[7];
				for (int i = 0; i < 7; i++) {	// Teisendab iga aja sekunditeks
					String[] aeg = rida[i+1].split(":");
					ajad[i] = Integer.parseInt(aeg[0])*36000 + Integer.parseInt(aeg[1])*600 + (int)(10*Double.parseDouble(aeg[2]));
				}

				voistlejad.add(new Voistleja(nimi, ajad));	// Loob võistleja objekti, koos tema aegadega
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}


		int aeg = 0;
		int koht = 0;
		voistlejad.sort(Voistleja.aeg1Comparator());		// Leiab iga võistleja koha vahepunktis 1
		for (int i = 0; i < voistlejad.size(); i++){
			Voistleja voistleja = voistlejad.get(i);
			if (voistleja.getAeg1() == aeg)
				voistleja.setKoht1(koht);
			else {
				voistleja.setKoht1(i+1);
				aeg = voistleja.getAeg1();
				koht = i+1;
			}
		}

		aeg = 0;
		koht = 0;
		voistlejad.sort(Voistleja.aeg2Comparator());		// Leiab iga võistleja koha vahepunktis 2
		for (int i = 0; i < voistlejad.size(); i++){
			Voistleja voistleja = voistlejad.get(i);
			if (voistleja.getAeg2() == aeg)
				voistleja.setKoht2(koht);
			else {
				voistleja.setKoht2(i+1);
				aeg = voistleja.getAeg2();
				koht = i+1;
			}
		}

		aeg = 0;
		koht = 0;
		voistlejad.sort(Voistleja.aeg3Comparator());		// Leiab iga võistleja koha vahepunktis 3
		for (int i = 0; i < voistlejad.size(); i++){
			Voistleja voistleja = voistlejad.get(i);
			if (voistleja.getAeg3() == aeg)
				voistleja.setKoht3(koht);
			else {
				voistleja.setKoht3(i+1);
				aeg = voistleja.getAeg3();
				koht = i+1;
			}
		}

		aeg = 0;
		koht = 0;
		voistlejad.sort(Voistleja.aeg4Comparator());		// Leiab iga võistleja koha vahepunktis 4
		for (int i = 0; i < voistlejad.size(); i++){
			Voistleja voistleja = voistlejad.get(i);
			if (voistleja.getAeg4() == aeg)
				voistleja.setKoht4(koht);
			else {
				voistleja.setKoht4(i+1);
				aeg = voistleja.getAeg4();
				koht = i+1;
			}
		}

		aeg = 0;
		koht = 0;
		voistlejad.sort(Voistleja.aeg5Comparator()); 		// Leiab iga võistleja koha vahepunktis 5
		for (int i = 0; i < voistlejad.size(); i++){
			Voistleja voistleja = voistlejad.get(i);
			if (voistleja.getAeg5() == aeg)
				voistleja.setKoht5(koht);
			else {
				voistleja.setKoht5(i+1);
				aeg = voistleja.getAeg5();
				koht = i+1;
			}
		}

		aeg = 0;
		koht = 0;
		voistlejad.sort(Voistleja.aeg6Comparator());		// Leiab iga võistleja koha vahepunktis 6
		for (int i = 0; i < voistlejad.size(); i++){
			Voistleja voistleja = voistlejad.get(i);
			if (voistleja.getAeg6() == aeg)
				voistleja.setKoht6(koht);
			else {
				voistleja.setKoht6(i+1);
				aeg = voistleja.getAeg6();
				koht = i+1;
			}
		}

		voistlejad.sort(Voistleja.aegFinaalComparator());		// Leiab iga võistleja koha finišis
		for (int i = 0; i < voistlejad.size(); i++)
			voistlejad.get(i).setKohtFinaal(i+1);

		return voistlejad;
	}

	/**
	 * Leiab võistleja tulemused tema nime järgi
	 *
	 * @param x	Võistleja nimi
	 * @param failinimi Fail võistlejate aegadega
	 * @return	String, kus on ees võistleja nimi ning järgi iga saadud koht vahepunktides ja finišis
	 */
	public static String võistlejaDünaamika(String x, String failinimi) {

		ArrayList<Voistleja> voistlejad = loeVoistlejadSisse(failinimi);	// Kõik võistlejad

		Voistleja otsitavIsik = null;
		for (Voistleja voistleja : voistlejad) {
			if (voistleja.getNimi().equals(x))		// Otsib, kas sellist isikut leidub
				otsitavIsik = voistleja;
		}

		if (otsitavIsik == null)
			return null;

		return otsitavIsik.toString();
	}


	/**
	 * Leiab võistleja tema lõpu positisiooni järgi.
	 *
	 * @param lõpp	Positsioon, mille võistlejat otsitakse
	 * @param failinimi Fail võistlejate aegadega
	 * @return	String, kus on ees võistleja nimi ning järgi iga saadud koht vahepunktides ja finišis
	 */
	public static String lõpetajaDünaamika(int lõpp, String failinimi) {

		ArrayList<Voistleja> voistlejad = loeVoistlejadSisse(failinimi);	// Kõik võistlejad

		Voistleja otsitavIsik = null;
		for (Voistleja voistleja : voistlejad) {
			if (voistleja.getKohtFinaal() == lõpp)		// Otsib, kas sellist isikut leidub
				otsitavIsik = voistleja;
		}

		if (otsitavIsik == null)
			return null;

		return otsitavIsik.toString();
	}

	/**
	 * Leiab võistleja, kes erinevate vahepunktide ja finiši vahel suutis kõige rohkem positsiooni suurendada. Kui kaks
	 * võistlejat suurendasid võrdselt positsiooni, tagastatakse varem lõpetanud võistleja.
	 *
	 * @param failinimi Fail võistlejate aegadega
	 * @return	String, kus on ees võistleja nimi ning järgi iga saadud koht vahepunktides ja finišis
	 */
	public static String suurimÜlesDünaamika(String failinimi) {
		ArrayList<Voistleja> voistlejad = loeVoistlejadSisse(failinimi);
		
		Voistleja parimVoistleja = voistlejad.get(0);		// Peab järgi suurima tõusuga võistlejal
		int parimTous = 0;									// ja tema tõusul
		for (Voistleja voistleja : voistlejad) {
			int tous = 0;
			int[] ajad = voistleja.koikKohad();				// Võistleja kõik kohad
			for (int i = 0; i < 7; i++)						// Leiab suurima tõusu
				for (int j = i+1; j < 7; j++)
					if (tous < ajad[i] - ajad[j])
						tous = ajad[i] - ajad[j];
														// Võrdleb suurima tõusuga
			if (parimTous < tous || (parimTous == tous && parimVoistleja.getKohtFinaal() > voistleja.getKohtFinaal())){
				parimTous = tous;
				parimVoistleja = voistleja;
			}

		}

		return parimVoistleja.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(võistlejaDünaamika("Koskela Niko", "andmed.txt"));
		System.out.println(lõpetajaDünaamika(1, "andmed.txt"));
		System.out.println(suurimÜlesDünaamika("andmed.txt"));
	}
}