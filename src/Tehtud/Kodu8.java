package Tehtud; /***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 8
 * Teema: Sõned
 *
 * Autor: Gregor Rämmal
 *
 **********************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Kodu8 {


	/**
	 * Eemaldab sõnest kõik järjest korduvad tähed ning kordab seda saadud uue sõnega kuni enam ei ole korduvaid tähti
	 *
	 * @param a	Antud sõne
	 * @return	Saadud sõne
	 */
	public static String eemaldaKordusgrupid(String a) {
		StringBuilder sb = new StringBuilder(a);


		if (a.length() < 2)			// Kui sõnes on vähem kui 2 tähte, siis ei ole seal korduvaid tähti
			return a;

		boolean jatkub = true;		// Määrab, kas sõnes on veel korduvaid tähti või tuleb protsessi jätkata
		while (jatkub) {
			StringBuilder sbKoopia = new StringBuilder();	// Siia lisatakse tähed mis ei kordu

			if (sb.charAt(0) != sb.charAt(1))				// Lisatakse esimene täht, kui see ei kordu
				sbKoopia.append(sb.charAt(0));

			for (int i = 1; i < sb.length() - 1; i++)		// Lisatakse täht, kui see ei ole korduv täht
				if (sb.charAt(i - 1) != sb.charAt(i) && sb.charAt(i + 1) != sb.charAt(i))
					sbKoopia.append(sb.charAt(i));

			if (sb.charAt(sb.length() - 1) != sb.charAt(sb.length() - 2))	// Lisatakse viimane täht kui see ei kordu
				sbKoopia.append(sb.charAt(sb.length() - 1));

			if (sb.toString().equals(sbKoopia.toString()) || sbKoopia.length() < 2) // Kui saadud sõne on sama, mis ennist juba oli
				jatkub = false;														// ehk korduvaid gruppe polnud, siis lõpetatakse töö

			sb = sbKoopia;				// Uuendab sõne
		}

		return sb.toString();
	}


	/**
	 * Leiab sõnest kõik kaashäälikuühendid
	 *
	 * @param a Antud sõne
	 * @return Ühendite arv
	 */
	public static int kaashäälikuÜhendeid(String a) {
		ArrayList<Character> kaasHaalikud = new ArrayList<>(List.of('b', 'c', 'd', 'f', 'g', 'h',
				'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 'š', 'z', 'ž', 't', 'v', 'w', 'x', 'y'));

		boolean uhend = false;					// Kas hetkel on kaashäälikuühend
		boolean eelmineKaashaalik = false;		// Kas eelmine täht oli kaashäälik
		int loendur = 0;						// Loeb kaashäälikuühendite arvu
		char eelmine = 0;						// Eelmine täht

		a = a.toLowerCase();

		for (int i = 0; i < a.length(); i++) {					// Käib läbi iga tähe sõnes
			if (kaasHaalikud.contains(a.charAt(i))) {			// Kui on kaashäälik
				if (eelmineKaashaalik && eelmine != a.charAt(i))// Kui on kaashäälikuühend
					uhend = true;

				eelmine = a.charAt(i);
				eelmineKaashaalik = true;

			} else {

				if (uhend)										// Loeb ühendeid
					loendur++;

				eelmineKaashaalik = false;
				uhend = false;
			}
		}

		if (uhend)
			loendur++;

		return loendur;
	}


	/**
	 * Eemaldab sõnest järjest korduvaid tähti, kuni rohkem korduvaid tähti ei ole
	 *
	 * @param s	Antud sõne
	 * @return Kõik võimalikud järelejäänud sõned
	 */
	public static String[] kõikTulemused(String s) {
		StringBuilder sb = new StringBuilder(s);						// Sõne kust hakatakse korduvaid tähti otsima
		HashSet<String> tulemused = new HashSet<>();					// Salvestatkse kõik lõplikud sõned
		HashSet<String> ajalugu = new HashSet<>(450000, 1f);	// Salvestatakse kõik funktsiooni sisenenud sõned

		rek(tulemused, sb, ajalugu);			// Hakkab sõnesid läbi käima



		return tulemused.toArray(new String[0]);
	}

	/**
	 * Rekursiivne funktsioon ilma korduvate tähtedeta sõnede otsimiseks
	 *
	 * @param tulemused Saadud sõned, kus ei ole enam korduvaid tähti
	 * @param sb Sõne antud hetkel
	 * @param ajalugu	Sõned, mis on juba sellesse funktsioon sisenenud
	 */
	public static void rek(HashSet<String> tulemused, StringBuilder sb, HashSet<String> ajalugu) {
		boolean viimane = true;					// Peab järge, kas tegemist on viimase sõnega, kus enam korduvaid tähti ei ole

		for (int i = 1; i < sb.length(); i++) {			// Võrreldakse igat tähte eelmisega
			if (sb.charAt(i) == sb.charAt(i - 1)) {		// Kui kaks kõrvuti olevat tähte on samad

				int algus = i - 1;						// Jätab meelde esimese tähe indeksi
				char c = sb.charAt(algus);
				i++;

				while (i < sb.length() && sb.charAt(i) == c)	// Leiab viimase korduva tähe indeksi
					i++;


				String st = sb.substring(algus, i);
				sb.delete(algus, i);					// Kustutab need tähed sõnest

				String str  = sb.toString();

				if (ajalugu.add(str)){					// Kontrollib, kas sõne on juba kontrollitud ja lisab selle vajadusel
					if (sb.length() < 2)				// Kui on vähem kui 2 tähte, siis seal ei ole korduvaid tähti
						tulemused.add(str);
					else
						rek(tulemused, sb, ajalugu);	// Jätkab otsinguid
				}


				sb.insert(algus, st);					// Taastab eelneva seisu

				viimane = false;						// Tegemist ei ole viimase sõnega jadas
			}
		}

		if (viimane)									// Lisab tulemuse kui korduvaid tähti ei leitud
			tulemused.add(sb.toString());

	}




	public static void main(String[] args) {
		String a = "abbbaaacabbbbac";
		System.out.println(eemaldaKordusgrupid(a));
		System.out.println();

		a = "ccabbacca";
		System.out.println(eemaldaKordusgrupid(a));
		System.out.println();

		a = "Mait Malmsten kehastus korstnapühkijaks.";
		System.out.println(kaashäälikuÜhendeid(a));
		System.out.println();

		a = "MaitMalmstenkehastuskorstnapühkijaks";
		System.out.println(kaashäälikuÜhendeid(a));
		System.out.println();

		long s = System.currentTimeMillis();
		a = "cabbacccacacabbacccacacabbacccacacabbacccacacabbacccacacabbacccaca";
		String b = "cabbacccaca";
		System.out.println(Arrays.toString(kõikTulemused(b)));
		System.out.println(Arrays.toString(kõikTulemused(a)));
		System.out.println(System.currentTimeMillis() - s);
	}
}