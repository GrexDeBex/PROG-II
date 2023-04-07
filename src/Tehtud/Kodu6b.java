/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 6b
 * Teema: Rekursioon
 *
 * Autor: Gregor Rämmal
 *
 **********************************/

package Tehtud;
import java.util.Arrays;

class Kodu6b {


	/**
	 * Leiab parima viisi, kuidas arvude massiivi jagada rühmadeks nii, et rühmadest suurim summa ja väikseim summa
	 * on võimalikult väikse vahega.
	 *
	 * @param sisend Täisarvude massiiv
	 * @return Kõige parema jaotuse rühmade massiiv
	 */
	public static int[][] jaotusRühmadeks(int[] sisend) {
		int[][] jaotus;                                    // Salvestakse konkreetse rühmade arvuga parim jaotus

		int[][] parimTulemus = new int[1][1];				// Salvestab kõige parema tulemuse
		parimTulemus[0][0] = 2_000_000_000;					// Esimesele indeksile salvestatakse jaotuse vahe

		int[][] summadEelmine = new int[sisend.length - 2][2];			// Kasutatakse, et saada eelmiste jaotuste summad
		int[][][] jaotusedEelmine = new int[sisend.length - 2][][];		// Kasutatakse, et saada parimate summade jaotused

		// summadEelmine on maatriks, kus iga rida sisaldab kahte numbrid: suurimat summad, väikseimat summat
		// Read määratakse selle järgi, mitmes sisendi element asub eelmise jaotuse teise rea alguses
		// jaotusedEelmine sisaldab samadel indeksitel neid jaotusi, kust summad saadi


		for (int ruhmi = 2; ruhmi < sisend.length; ruhmi++) {				// Proovitakse järgi kõik rühmade arvud

			int[][] summadJargmine = new int[sisend.length - ruhmi][2];		// Tühjendatakse summade massiiv uute andmete sisestamiseks
			for (int[] rida : summadJargmine) {
				rida[0] = 1_000_000_000;
				rida[1] = -1_000_000_000;
			}
			int[][][] jaotusedJargmine = new int[sisend.length - ruhmi][][];// Tühjendatakse jaotused uute salvestamiseks


			if (ruhmi == 2) jaotus = leiaParimJaotusAlgus(sisend, summadJargmine, jaotusedJargmine);
			// Kuna esimesel jaotusel ei ole veel eelmisi, mille järgi kontrollida, siis siin ainult täidetakse andmed

			else jaotus = leiaParimJaotusJatk(sisend, summadEelmine, summadJargmine, jaotusedEelmine, jaotusedJargmine);
			// Siin kasutatakse vanu andmeid tulemuste saamiseks ning salvestatakse uued andmed

			parimTulemus = (jaotus[0][0] < parimTulemus[0][0]) ? jaotus : parimTulemus;	// Salvestab parimat tulemuse

			summadEelmine = summadJargmine;			// Uuendab andmeid
			jaotusedEelmine = jaotusedJargmine;
		}


		return Arrays.copyOfRange(parimTulemus, 1, parimTulemus.length);	// Tagastab tulemuse ilma esimese reata
	}









	/**
	 * Kasutatakse siis kui proovitakse kaheks rühmaks jaotamist. Katsetab läbi kõik jaotused ning
	 * leiab nendest parima jaotuse. Samal ajal salvestatakse teise rühma summad, et neid edaspidi kasutada.
	 *
	 * @param sisend           Täisarvude massiiv
	 * @param summadJargmine   Salvestatakse parimad summad alates teisest rühmast
	 * @param jaotusedJargmine Salvestatakse parimad summad andnud jaotused
	 * @return Kõige parem kaheks rühmaks jaotus
	 */
	public static int[][] leiaParimJaotusAlgus(int[] sisend, int[][] summadJargmine, int[][][] jaotusedJargmine) {

		int[][] parimTulemus = new int[sisend.length - jaotusedJargmine.length + 1][];	// Luuakse tühi jaotus
		parimTulemus[0] = new int[]{2_000_000_000};

		for (int piir = 1; piir < sisend.length; piir++) {	// Käiakse läbi kõik võimalikud sisendite jaotused kaheks
															// Piir määrab ära, kust algab teine rühm

			int sum1 = summa(sisend, 0, piir);			// Esimese rühma summa
			int sum2 = summa(sisend, piir, sisend.length);		// Teise rühma summa

			int sumMax = Math.max(sum1, sum2);					// Terve jaotuse suurim summa
			int sumMin = Math.min(sum1, sum2);					// Terve jaotuse vähim summa

			parimTulemus = uuenda(sisend, summadJargmine, jaotusedJargmine, piir, sisend.length, sumMax,
					sumMin, sum2, sum2, parimTulemus, parimTulemus);

			// Uuendab parimat tulemust, jaotuste andmeid või mõlemat

		}

		return parimTulemus;
	}









	/**
	 * Leiab parima viisi, kuidas arvude massiivi jagada rühmadeks nii, et rühmadest suurim summa ja väikseim summa
	 * on võimalikult väikse vahega.
	 *
	 * @param sisend           Täisarvude massiiv
	 * @param summadEelmine    Vaadatakse eelmistest jaotustest parimaid summasid
	 * @param summadJargmine   Salvestatakse parimad summad alates teisest rühmast
	 * @param jaotusedEelmine  Sisaldab parimate jaotuste summasid
	 * @param jaotusedJargmine Salvestatakse parimad summad andnud jaotused
	 * @return Kõige parem kaheks rühmaks jaotus
	 */
	public static int[][] leiaParimJaotusJatk(int[] sisend, int[][] summadEelmine, int[][] summadJargmine,
											  int[][][] jaotusedEelmine, int[][][] jaotusedJargmine) {

		int[][] parimTulemus = new int[sisend.length - jaotusedJargmine.length + 1][]; 	// Luuakse tühi jaotus
		parimTulemus[0] = new int[]{2_000_000_000};

		for (int i = 0; i < summadEelmine.length; i++) {	// Katsetab läbi kõik võimalused nii, et mingi sisendi väärtus
															// asub kolmanda rea esimesel kohal
			int lopp = i + 2;								// Määrab, millisest sisendi elemendist hakkab kolmas rida
			int max = summadEelmine[i][0];					// Suurim summa alates kolmandast reast
			int min = summadEelmine[i][1];					// Väikseim summa alates kolmandast reast
			int[][] jaotus = jaotusedEelmine[i];			// Jaotus alates kolmandast reast

			for (int piir = 1; piir < lopp; piir++) { 		// Käiakse läbi kõik võimalikud sisendite jaotused kaheks
															// Piir määrab ära, kust algab teine rühm
				int sum1 = summa(sisend, 0, piir);	// Esimese rühma summa
				int sum2 = summa(sisend, piir, lopp);		// Teise rühma summa

				int jaotusMax = Math.max(max, sum2);		// Suurim summa alates teisest reast
				int jaotusMin = Math.min(min, sum2);		// Väikseim summa alates teisest reast
				int sumMax = Math.max(sum1, jaotusMax);		// Suurim summa kogu jaotuses
				int sumMin = Math.min(sum1, jaotusMin);		// Väikseim summa kogu jaotuses

				parimTulemus = uuenda(sisend, summadJargmine, jaotusedJargmine, piir, lopp, sumMax,
						sumMin, jaotusMax, jaotusMin, parimTulemus, jaotus);

				// Uuendab parimat tulemust, jaotuste andmeid või mõlemat

			}
		}


		return parimTulemus;
	}









	/**
	 * Leiab sisendi elementide summa alates mingist elemendist kuni mingi elemendini
	 *
	 * @param sisend Täisarvude massiiv
	 * @param alates Algus indeks
	 * @param kuni   Lopp indeks
	 * @return Saadud summa
	 */
	public static int summa(int[] sisend, int alates, int kuni) {
		int sum = 0;
		for (int elem = alates; elem < kuni; elem++)
			sum += sisend[elem];

		return sum;
	}









	/**
	 * Uuendab kas parimat tulemust selle rühmade arvuga, järgmiste jaotuste jaoks jaotuste tulemusi või mõlemat.
	 *
	 * @param sisend           Täisarvude massiiv
	 * @param summadJargmine   Salvestatakse parimad summad alates teisest rühmast
	 * @param jaotusedJargmine Salvestatakse parimad summad andnud jaotused
	 * @param piir             Piiri indeks sisendi massiivis, mis jagab kahte esimest rühma pooleks
	 * @param lopp             Lopu indeks sisendi massiivis, kust algab kolmas rühm
	 * @param sumMax           Terve jaotuse suurim summa
	 * @param sumMin           Terve jaotuse väikseim summa
	 * @param jaotusMax        Terve jaotuse suurim summa, välja arvatud esimene rida
	 * @param jaotusMin        Terve jaotuse väikseim summa, välja arvatud esimene rida
	 * @param parimTulemus     Konkreetse rühmade arvuga hetke parim jaotus
	 * @param jaotus           Eelnevatelt rühmadelt saadud jaotus
	 * @return Kõige parem kaheks rühmaks jaotus
	 */
	public static int[][] uuenda(int[] sisend, int[][] summadJargmine, int[][][] jaotusedJargmine, int piir, int lopp,
								 int sumMax, int sumMin, int jaotusMax, int jaotusMin, int[][] parimTulemus, int[][] jaotus) {

		if (parimTulemus[0][0] < (sumMax - sumMin) && ((piir > 1) &&
				(summadJargmine[piir - 2][0] - summadJargmine[piir - 2][1]) <= (jaotusMax - jaotusMin)))
			return parimTulemus;

		// Kontrollib, kas uuel jaotusel on parem tulemus või kas on mõtet salvestada järgmise jaotuse kontrollimiseks
		// Kui ei ole, siis ei muudeta midagi


		int[][] uusJaotus = new int[sisend.length - jaotusedJargmine.length + 1][];		// Luuakse käes olev jaotus täis kujul
		int[] rida1 = new int[piir];													// Uue jaotuse esimene rida
		int[] rida2 = new int[lopp - piir];												// Uue jaotuse teine rida

		int indeksSisend = 0;
		for (; indeksSisend < piir; indeksSisend++)					// Lisatakse esimesse ritta sisendi väärtused
			rida1[indeksSisend] = sisend[indeksSisend];

		for (int l = 0; indeksSisend < lopp; l++, indeksSisend++)	// Lisatakse teise ritta sisendi väärtused
			rida2[l] = sisend[indeksSisend];


		uusJaotus[1] = rida1;										// Lisatakse esimene rida
		uusJaotus[2] = rida2;										// Lisatakse teine rida

		System.arraycopy(jaotus, 2, uusJaotus, 3, uusJaotus.length - 3);
																	// Kopeerib kõik ülejäänud read eelmisest jaotusest

				// Kui sellise jaotuse kuju alates teisest reast on parim, siis see salvestatakse
		if (((piir > 1) && (summadJargmine[piir - 2][0] - summadJargmine[piir - 2][1]) > (jaotusMax - jaotusMin))) {
			summadJargmine[piir - 2][0] = jaotusMax;	// Suurim väärtus
			summadJargmine[piir - 2][1] = jaotusMin;	// Väikseim väärtus
			jaotusedJargmine[piir - 2] = uusJaotus;
		}

									// Kui antud jaotus on parim nende rühmade korral, siis seda uuendatakse
		if (parimTulemus[0][0] >= (sumMax - sumMin)) {
			uusJaotus[0] = new int[]{sumMax - sumMin};
			return uusJaotus;
		}

		return parimTulemus;
	}










	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		int[] b = {1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 11117, 4, 5, 1, 2, 1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 5, 1, 2, 3, 3, 5, 2, 4, 5, 1, 2, 2, 0, 1, 1};
		int[] c = {59594, 63030, 5190, 14138, 24643, 53044};
		int[] d = {1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 67, 4, 5, 1, 2};
		int[] e = {4, 2, -3, 8, -3, 2, -8};
		int[] f = {34629, 111600, 94026, 112119, 60632, 42020, 47532, 25681, 39103, 20688};

		int[] g = new int[250];
		for (int i = 0; i < g.length; i++) {
			int j = (Math.random() < 0.5) ? 1 : -1;
			g[i] = (int) (Math.random() * 100_000 * j);
		}

		int[][] sisendid = new int[][]{a, b, c, d, e, f, g};

		for (int[] sisend : sisendid) {
			int[][] tulemus = jaotusRühmadeks(sisend);

			for (int[] elem : tulemus) {
				System.out.println(Arrays.toString(elem));
			}

			System.out.println();
			System.out.println();
		}


	}
}