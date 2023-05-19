/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 * <p>
 * Kodutöö nr 10b
 * Teema: Sõned
 * <p>
 * Autor: Gregor Rämmal
 *
 **********************************/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Kodu10b {

	/**
	 * Leiab kahe sõne vahel kõike lühema tuletusjada, nii et ühest sõnast saab teist tuletada siis,
	 * kui kõik tähed peale ühe on samad.
	 *
	 * @param sonad Kõik saadaval olevad sõnad
	 * @param ls	Lähtesõna
	 * @param ss	Sihtsõna
	 * @return	Massiiv, kus on saadud tuletusjada
	 */
	public static String[] lühimTuletus(String[] sonad, String ls, String ss) {
		HashMap<String, HashSet<String>> seosed = seosed(sonad);
		String[] tulemus =  LeiaSobivJada.koostaJada(seosed, ls, ss);	// Leiab kõige lühema jada kahe sõna vahel

		if (ls.equals(ss))				// Kui tuletada ei ole vaja
			return new String[]{ss};

		if (seosed.get(ls).size() == 0 || tulemus[1] == null)			// Kui tuletust ei leidu
			return null;

		return tulemus;
	}

	/**
	 * Laeb tekstifailis olevad sõnad massiivi
	 *
	 * @param failinimi Faili nimi
	 * @return	Massiiv koos kõigi sõnadega
	 */
	public static String[] sõnad(String failinimi){
		ArrayList<String> sonad = new ArrayList<>();			// Siia lisatakse kõik sõnad

		try (Scanner sc = new Scanner(new File(failinimi))) {	// Avatakse fail
			while (sc.hasNextLine())
				sonad.add(sc.nextLine());						// Lisatakse iga sõna

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		return sonad.toArray(new String[0]);
	}



//	====================================================================================================================
//	====================================================================================================================
//	====================================================================================================================


	/**
	 * Loob sõnastiku, kus võtmeks on sõna ning sellekaudu saab sõnade hulga, mis on võtmesõnast tuletatavad
	 *
	 * @param sonad	Kõik sõnad
	 * @return	Sõnastik
	 */
	public static HashMap<String, HashSet<String>> seosed(String[] sonad){
		HashMap<String, HashSet<String>> seosed = new HashMap<>(sonad.length+1, 1);
															// Sõnastik

		for (String sona1 : sonad) {						// Käib läbi iga sõna
			HashSet<String> seos = new HashSet<>();			// Lisatakse siia iga sobiv tuletis
			for (String sona2 : sonad)
				if (onTuletis(sona1, sona2))				// lisab tuletised
					seos.add(sona2);

			seosed.put(sona1, seos);
		}

		return seosed;
	}

	/**
	 * Kontrollib, kas üks sõna on teisest tuletatav
	 *
	 * @param s1 esimene sõna
	 * @param s2 teine sõna
	 * @return	Kas on tuletatav
	 */
	public static boolean onTuletis(String s1, String s2){
		int loendur = 0;
		for (int i = 0; i < 4; i++)
			if (s1.charAt(i) == s2.charAt(i))
				loendur++;

		return loendur == 3;
	}


//	====================================================================================================================
//	====================================================================================================================
//	====================================================================================================================


	/**
	 * Leiab tuletusjada, mis on kahe äärmise sõna vahel kõige lühem, ning kõige pikem võimalik jada antud sõnadest
	 *
	 * @param sonad	Antud sõnad
	 * @return	Pikim jada
	 */
	public static String[] pikimLühimTuletus(String[] sonad){
		HashMap<String, HashSet<String>> seosed = seosed(sonad);	// Sõnastik, kus võtmeks on sõna ja väärtuseks
																	// on sõnade hulk, mis on võtmest tuletatavad
		HashSet<String> kasutatud = new HashSet<>(sonad.length+1, 1);	// Juba kontrollitud sõnad
		HashSet<String> sonadHulgas = new HashSet<>(sonad.length, 1);				// Leiab kõik sõnad ühes sõnade hulgas

		int pikim = 0;				// Pikim jada hetkel
		String algus = "";			// Selle jada algus
		String lopp = "";			// Selle jada lõpp

		for (String sona : sonad) {
			if (seosed.get(sona).size() == 0 || kasutatud.contains(sona))					// Kontrollib, kas sõna on juba kontrollitud
				continue;

			HashSet<String> lopud = new HashSet<>();						// Siia salvestatakse lõppsõnad
			int hetkePikkus = leiaLopud(seosed, sona, lopud, sonadHulgas);	// Leiab kõik võimalikud pikima jada lõppsõnad

			String[] pikimHulgas = otsiHulgas(seosed, sona, sonadHulgas, lopud, hetkePikkus);	// Leiab pikima jada sõnade hulgas
			kasutatud.addAll(sonadHulgas);									// uuendab kasutatud sõnu

			int pikkus = Integer.parseInt(pikimHulgas[1]);					// Hulga pikima jada pikkus

			if (pikim < pikkus){					// Uuendab kõige pikemat jada vajadusel
				pikim = pikkus;
				algus = pikimHulgas[0];
				lopp = pikimHulgas[2];
			}
		}

		return LeiaSobivJada.koostaJada(seosed, algus, lopp);		// Leiab lühima tuletusjada kahesõna vahel
	}


	/**
	 * Sõnade hulk on hulk, kus kõik sõnad on omavahel kuidagi üheduses. Kaks sõna on eri hulkades, kui need ei ole
	 * omavahel tuletatavad. Funktsioon leiab pikim tuletusjada antud hulgas
	 *
	 * @param seosed	Sõnastik tuletatavate sõnadegav
	 * @param algus	Algsõna, mis asub antud sõnade hulgas ning millest otsitakse järgmist pikimat jada
	 * @param sonadHulgas	Siia salvestatkse, mis sõnad on antud hulgas
	 * @return	Massiiv, kus esimene liige on pikima jada algsõna, teine pikkus ja kolmas lõppsõna
	 */
	public static String[] otsiHulgas(HashMap<String, HashSet<String>> seosed, String algus,
									  HashSet<String> sonadHulgas, HashSet<String> lopud, int pikkus){

		String lopp = "";			// Kirjutatakse hiljem üle

		for (String uusAlgus : lopud) {								// Kontrollitakse, kas mõnest lõpust uut
			lopp = uusAlgus;										// jada otsides leidub pikem jada
			HashSet<String> uuedLopud = new HashSet<>();
			int uusPikkus = leiaLopud(seosed, uusAlgus, uuedLopud, sonadHulgas);

			if (uusPikkus > pikkus)
				return otsiHulgas(seosed, uusAlgus, sonadHulgas, uuedLopud, uusPikkus);

		}
					// Kui antud jada oli kõige pikem, siis see tagastatakse
		return new String[]{algus, Integer.toString(pikkus), lopp};
	}


	/**
	 * Leiab kõige pikemat tuletusjada omavat lõppsõnad
	 *
	 * @param seosed Sõnastik tuletatavate sõnadega
	 * @param algus	Algsõna
	 * @param viimatiLisatud Salvestab kõik lõpud
	 * @param sonadHulgas	Salvestab kõik sõnad, mis asuvad samas hulgas
	 * @return	Kõige pikema jada pikkus
	 */
	public static int leiaLopud(HashMap<String, HashSet<String>> seosed, String algus,
								HashSet<String> viimatiLisatud, HashSet<String> sonadHulgas){
		sonadHulgas.clear();
		sonadHulgas.addAll(seosed.get(algus));
		sonadHulgas.add(algus);

		viimatiLisatud.addAll(seosed.get(algus));
		HashSet<String> uuedSonad = new HashSet<>(100);

		int loendur = 2;

		while (true){
			uuedSonad.clear();
			for (String sona : viimatiLisatud)
				uuedSonad.addAll(seosed.get(sona));

			uuedSonad.removeAll(sonadHulgas);
			if (!sonadHulgas.addAll(uuedSonad))
				break;

			viimatiLisatud.clear();
			viimatiLisatud.addAll(uuedSonad);
			loendur++;
		}

		return loendur;
	}






//	====================================================================================================================
//	====================================================================================================================
//	====================================================================================================================





	public static void main(String[] args) {
		System.out.println(Arrays.toString(lühimTuletus(sõnad("nimi.txt"), "kood", "tool")));
		System.out.println(Arrays.toString(lühimTuletus(sõnad("nimi.txt"), "kass", "kakk")));


		long s = System.currentTimeMillis();
		System.out.println(Arrays.toString(pikimLühimTuletus(sõnad("nimi.txt"))));
		System.out.println(System.currentTimeMillis() - s);
	}
}