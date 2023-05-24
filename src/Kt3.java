import java.util.ArrayList;
import java.util.Arrays;

/**
 * Õpilase nimi:    Gregor Rämmal
 * Programmeerimine 2 - KT 3 24.05.2023. Palume mitte "package" märksõna lisada,
 * koodis meetodite signatuure muuta, ega (ka tühje) meetodeid kustutada!
 * Kui meetodi sisu ei realiseeri, siis jäta rida "throw new UnsupportedOperationException("Implementeerimata!");" alles
 * Kui realiseerid, siis kustuta see ära.
 */
public class Kt3 {

	/**
	 * Eemaldab lausest kõik ebavajalikud sümbolid
	 *
	 * @param s Lause
	 * @return  Lause, kust on sümbolid eemaldatud
	 */
	public static String tõlgiLause(String s){
		ArrayList<StringBuilder> sonad = new ArrayList<>();     // Siia lisatakse iga sõna StringBuilder
		StringBuilder tulemus = new StringBuilder();            // Siia koostatakse lõplikest sõnadest lause tagasi
		String[] jada = s.split(" ");                     // Eraldab sõnad

		for (String sona : jada)                                // Loob igale sõnale StringBuilderi
			sonad.add(new StringBuilder(sona));

		for (StringBuilder sb : sonad) {                        // Käib läbi iga sõna
			int i = sb.length()-1;                              // Viimane sümbol sõnas

			for (; i >= 0; i--) {                               // Käib läbi iga sümboli
				String sumbol = Character.toString(sb.charAt(i));   // Teisendab sümboli stringiks

				if (i == sb.length()-1 && ".,!?".contains(sumbol))  // Kontrollib, kas sona viimane sümbol võib alles jääda
					continue;

				if ("*.,!?".contains(sumbol))                   // Eemaldab iga sümboli sõna seest ja järelt
					sb.deleteCharAt(i);
			}

			tulemus.append(sb);                                 // Lisab sõna lausesse
			tulemus.append(" ");                                // Eraldab sõnad tühikuga
		}

		tulemus.deleteCharAt(tulemus.length()-1);         // Eemaldab viimase tühiku

		return tulemus.toString();
	}


	/**
	 * Leiab lausest kaks pikima ühise "suffiksiga" sõnad
	 *
	 * @param s Lause
	 * @return  Massiiv, mis sisaldab leitud kahte sõna
	 */
	public static String[] pikimÜhissufiks(String s) {
		String[] sonad = s.split("[:.,;!? ]");		// Eraldab sõnad tühikute ja märkidega

		int max = 0;										// Peab järge pikimal suffiksil
		String[] tulemus = new String[2];					// Salvestab pikima suffiksi sõnad

		for (int i = 0; i < sonad.length; i++){				// Käib läbi kõik sõnapaarid lauses
			for (int j = i+1; j < sonad.length; j++){
															// Kontrollib, et sõna ei võrreldaks iseendaga ning et poleks tühi sõne
				if (sonad[i].equals("") || sonad[j].equals("") || sonad[i].equals(sonad[j]))
					continue;

				int pikkus = leiaSuffiks(sonad[i], sonad[j]);	// Leiab suffiksi pikkuse

				if (pikkus > max){							// Uuendab pikimat suffiksit lauses
					max = pikkus;
					tulemus[0] = sonad[i];
					tulemus[1] = sonad[j];
				}
			}
		}

		if (max == 0)
			return null;

		return tulemus;
	}

	/**
	 * Leiab kahe sõna pikima ühise "suffiksi"
	 *
	 * @param s1    Esimene sõna
	 * @param s2    Teine sõna
	 * @return  Leitud suffiksi pikkus
	 */
	public static int leiaSuffiks(String s1, String s2){
		int loendur = 0;
		int i1 = s1.length()-1;						// Esimese sõne indeks
		int i2 = s2.length()-1;						// Teise sõne indeks

		for (; i1 >= 0 && i2 >= 0; i1--, i2--) {	// Käiakse läbi sõnad tagantpoolt
			if (s1.charAt(i1) == s2.charAt(i2))		// Leiab suffiksi pikkuse
				loendur++;
			else
				break;
		}

		return loendur;
	}

	/**
	 * Kaotab sõnast kõik tähepaarid, kus on kaks samasugust tähte kõrvuti, kuni tähepaare
	 * enam sõnas ei ole. Kui on mitu tähepaari, siis kustutakse enne väiksemat tähekoodi
	 * omav paar.
	 *
	 * @param s Antud sõna
	 * @return Sõne, kuhu on salvestatud iga etapp peale tähepaari kaotamist ning iga etapp eraldatud komaga
	 */
	public static String kaotaTähepaarid(String s){
		StringBuilder sb = new StringBuilder(s.toLowerCase());		// Teisendab lause väiketähtedeks

		ArrayList<String> tulemus = new ArrayList<>();				// Salvestatakse sõne igas etapis
		ArrayList<int[]> paarid = new ArrayList<>();				// Salvestatakse igas etapis võimalikud paarid, mida kustutatakse

		while (true){
			tulemus.add(sb.toString());								// Salvestab etapi

			for (int i = 1; i < sb.length(); i++)					// Käib läbi kõik kaks järjestikust tähte
				if (sb.charAt(i) == sb.charAt(i-1))
					paarid.add(new int[]{sb.charAt(i), i-1});		// Lisab paari

			if (paarid.size() == 0)
				break;

			int[] parimPaar = {Integer.MAX_VALUE, -1};
			for (int[] paar : paarid)								// Leiab väikseima tähekoodiga paari
				if (paar[0] < parimPaar[0])
					parimPaar = paar;

			paarid.clear();											// Kustutab ülejäänud paarid
			sb.delete(parimPaar[1], parimPaar[1]+2);				// Eemaldab sõnest paari
		}

		String[] teisendus = tulemus.toArray(new String[0]);

		return String.join(",", teisendus);
	}







	public static void main(String[] args) {
		// Siin võib vabas vormis oma lahendust testida.
		// Automaattestid tõid hinnates main meetodit ei käivita. (Samas peab siiski kompileeruma!)

		String[] sisendid1 = {
				"t*!?",
				"ku**ra!t",
				"Tere. ku*.ra!t!!",
				"kuk.u.pra**t*",
				"Tere lammas, mis sa va**hid?*"
		};

		String[] sisendid2 = {
				"Meil naabriõues: kasvas üks kuusk ja teine kask."
		};

		String[] sisendid3 = {
				"majapidamine",
				"terre",
				"terranoona",
				"taarakaanetaja",
				"uu"
		};

		for (String s : sisendid1)
			System.out.println(tõlgiLause(s));
		System.out.println();

		System.out.println(Arrays.toString(pikimÜhissufiks(sisendid2[0])));
		System.out.println();

		for (String s : sisendid3)
			System.out.println(kaotaTähepaarid(s));
	}
}