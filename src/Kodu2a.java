import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
		// Näidis
		// Failitee jooksvas kaustas. IntelliJ puhul otsitakse faili eelkõige PROJEKTI kaustast, mitte SRC kaustast
	    // Kui fail asetsetb src kaustas siis peaks failitee olema "src/ilmAegTemp_2022.txt"
		loeAndmed("ilmAegTemp_2022.txt");//loeb andmed etteantud failist
		System.out.println("Kodutöö nr 2a");
		System.out.println();

		System.out.println("Kontrolliks, massiivide algused:");
		System.out.printf("%s\t%s\t %s%n", "kuupäev[]", "kellaaeg[]", "temperatuur[]");
		for (int i = 0; i < 10; i++)
			System.out.printf("%s\t%s\t%.11f%n", kuupäev[i], kellaaeg[i], temperatuur[i]);

		System.out.println("   ...   \t".repeat(3) + "\n");

		String sünnipäev = "2022-08-29";
		String kell = "12:00:00";
		double temp = temperatuurValitudPäevalJaKellal(sünnipäev, kell);

		System.out.printf("Minu sünnipäeval aastal 2022 (%s) oli keskpäevane temperatuur: ", sünnipäev);
		if (Double.isNaN(temp))
			System.out.printf("- %nAntud aega: %s %s ei ole andmestikus!%n%n", sünnipäev, kell);
		else
			System.out.printf("%.1f kraadi.%n%n", temp);

	}

	// Õpilase poolt teostatavad meetodid:

	public static double aastaKesk() {
		// Tagastuse näide: 3.4567832
		return -1.0;
	}

	public static double[] aastaMinMax() {
		// Tagastada kaheelemendiline järjend kus
		// 0. kohal on aasta miinimumtemperatuur
		// 1. kohal on aasta maksimumtemperatuur
		// Tagastuse näide: [-26.34938475, 32.564546]
		return null;
	}

	public static String[] pikimKasvavKahanev() {
		// Tagastada pikima sellise perioodi algus ja lõpp (kaasa arvatud)
		// kus temperatuur ainult kasvab või ainult kahaneb kaheelemendilise massiivi kujul kus
		// 0. kohal on perioodi alguse kuupäev ja aeg, eraldatud tühikuga
		// 1. kohal on perioodi lõpu kuupäev ja aeg, eraldatud tühikuga
		// Tagastuse näide: ["2022-08-02 00:15:00", "2022-08-02 10:35:00"]
		return null;
	}

	public static double[] kuudeKeskmised() {
		// Tagastada 12-elemendiline järjend kus
		// 0. kohal on jaanuari keskmine temperatuur
		// 1. kohal on veebruari keskmine temperatuur
		// ...
		// Tagastuse näide: [-3.23534509, ..., 1.4567456]
		return null;
	}
}


