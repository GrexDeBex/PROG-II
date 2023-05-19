import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class LeiaSobivJada {


	/**
	 * Koostab kõige lühema tuletusete jada kahe sõna vahel
	 *
	 * @param seosed	Sõnastik tuletatavate sõnadega
	 * @param algus		Algsõna
	 * @param lopp		Lõppsõna
	 * @return			Massiiv tuletusjadaga
	 */
	public static String[] koostaJada(HashMap<String, HashSet<String>> seosed, String algus, String lopp){

		ArrayList<HashSet<String>> liikmed = voimalikudLiikmed(seosed, algus, lopp);
														// Luuakse arraylist, kus iga element sisaldab kõiki kandidaate
														// tuletusjadas, mis sobiksid sama elemendi kohale

		puhastaLiikmeid(seosed, liikmed);				// Eemaldab ebavajalikud kandidaadid

		String[] tulemus = new String[liikmed.size()];	// Loob lõpliku jada jaoks massiivi
		tulemus[0] = algus;
		tulemus[tulemus.length-1] = lopp;

		teisenda(tulemus, liikmed, 1);			// Leitakse õiged kandidaadid

		return tulemus;
	}

	/**
	 * Luuakse arraylist, kus iga element sisaldab kõiki kandidaate tuletusjadas, mis sobiksid sama elemendi kohale
	 *
	 * @param seosed Sõnastik tuletatavate sõnadega
	 * @param algus	Algsõna
	 * @param lopp Lõppsõna
	 * @return Kandidaadid
	 */
	public static ArrayList<HashSet<String>> voimalikudLiikmed(HashMap<String, HashSet<String>> seosed,
															   String algus, String lopp){
		HashSet<String> koikSonad = new HashSet<>(seosed.get(algus));	// Kõik sõnad mis on harudest juba välja ilmunud
		koikSonad.add(algus);

		ArrayList<HashSet<String>> liikmed = new ArrayList<>();			// Luuakse list, kuhu hakatakse kandidaate lisama
		liikmed.add(new HashSet<>(Collections.singleton(algus)));
		liikmed.add(new HashSet<>(seosed.get(algus)));

		tsukkel:
		while (true){
			HashSet<String> liige = new HashSet<>();					// Kandidaadid järgmisel positsioonil

			for (String sona : liikmed.get(liikmed.size()-1)) {			// Eelmiselt positsioonilt leitakse kõik järgmise
				if (sona.equals(lopp))									// positsiooni kandidaadid, kuni leitakse
					break tsukkel;										// lõppsõna või rohkem kandidaate pole
				liige.addAll(seosed.get(sona));
			}

			liige.removeAll(koikSonad);									// Eemaldab juba olnud sõnad
			if (liige.size() == 0)										// Kontrollib, kas uusi sõnu oli
				break;

			liikmed.add(liige);
			koikSonad.addAll(liige);
		}

		liikmed.set(liikmed.size()-1, new HashSet<>(Collections.singleton(lopp))); // Lisab viimaseks liikmeks lõppsõna

		return liikmed;
	}


	/**
	 * Eemaldab üleliigset kandidaadid tuletades nüüd jada tagurpidi ning jättes alles ainult uuesti tuletatavad
	 * sõnad
	 *
	 * @param seosed Sõnastik tuletatavate sõnadega
	 * @param kandidaadid List koos igal positsioonil sobivate kandidaatidega
	 */
	public static void puhastaLiikmeid(HashMap<String, HashSet<String>> seosed, ArrayList<HashSet<String>> kandidaadid){
		HashSet<String> uusLiige = new HashSet<>();

		for (int i = kandidaadid.size()-1; i > 1; i--) {
			HashSet<String> liige1 = kandidaadid.get(i);
			HashSet<String> liige2 = kandidaadid.get(i-1);

			for (String sona : liige1)
				uusLiige.addAll(seosed.get(sona));		// Leiab kõik eelmisetest sõnadest tuletatavad sõnad

			liige2.retainAll(uusLiige);					// Eemaldab kõik võimatud kandidaadid
			uusLiige.clear();
		}
	}

	/**
	 * Leiab tuletus jada algsõnast lõppsõnani
	 *
	 * @param jada	Koht kuhu tulemus salvestatkse
	 * @param liikmed	Kõik võimalikud kandidaadid positsioonidel
	 * @param indeks	Positsioon, kus sobivat kandidaati hetkel otsitakse
	 * @return			Kas leiti sobilik jada
	 */
	public static boolean teisenda(String[] jada, ArrayList<HashSet<String>> liikmed, int indeks){
		if (indeks == jada.length-1)	// Kui jõuti lõppsõnani, siis on sobiv jada
			return true;

		for (String sona : liikmed.get(indeks)) {
			if (Kodu10b.onTuletis(jada[indeks-1], sona)){		// Leiab kõik tuletatavad jadad ja arendab neid
				jada[indeks] = sona;
				if (teisenda(jada, liikmed, indeks+1))
					return true;
			}
		}

		return false;
	}
}
