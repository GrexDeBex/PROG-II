import java.util.Random;

class Koopia {

	public static int ostmisViisid(double[] a, double p){
		quicksort(a, 0, a.length-1);

		int i = 0;
		for (; i < a.length; i++)
			if (a[i] > p)
				break;
		double[] tooted = new double[i];
		System.arraycopy(a, 0, tooted, 0, tooted.length);

//		int tulemus = Aeglane.ostmisViisid(a, p);
		int tulemus = funk(tooted, p, tooted.length-1);


		if (tulemus == 0)
			return 1;

		return tulemus;
	}


	public static int funk(double[] tooted, double jaak, int indeks){
		int tulemus = 1;
		for (; indeks > 1; indeks--) {
			double uusJaak = jaak - tooted[indeks];

			if (uusJaak < tooted[0])
				tulemus += (uusJaak < 0) ? 0 : 1;
			else
				tulemus += funk(tooted, uusJaak, indeks);

		}

		if (tooted.length > 1)
			tulemus += (int) (jaak / tooted[1]);


		return tulemus;
	}


	/**
	 * Quicksort algoritmiga mittekahanev massiivi sortimis funktsioon
	 *
	 * @param massiiv   Antud massiiv
	 * @param minIndeks Väikseim sorteerimata indeks massiivis
	 * @param maxIndeks Suurim sorteerimata indeks massiivis
	 */
	public static void quicksort(double[] massiiv, int minIndeks, int maxIndeks) {
		int i1 = minIndeks;
		int i2 = maxIndeks;
		double keskElem = massiiv[minIndeks + (maxIndeks - minIndeks) / 2];    // Leiab massiivi keskel oleva elemendi
		double x;

		while (i1 <= i2) {            // Sorteerib kuni väikseim ja suurim indeks mööduvad
			while (massiiv[i2] > keskElem) {    // Leiab massiivi lõpust keskmisest elemendist väiksema elemendi
				i2--;
			}
			while (massiiv[i1] < keskElem) {    // Leiab massiivi algusest keskmisest elemendist suurema elemendi
				i1++;
			}

			if (i1 <= i2) {                // Kui algusepoolne element on suurem kui lõpupoolne, siis nende kohad vahetatakse
				x = massiiv[i1];
				massiiv[i1] = massiiv[i2];
				massiiv[i2] = x;

				i2--;
				i1++;
			}
		}

		if (minIndeks < i2) {                    // Kui massiiv ei ole sorteeritud, siis minnakse uuele ringile
			quicksort(massiiv, minIndeks, i2);
		}
		if (i1 < maxIndeks) {
			quicksort(massiiv, i1, maxIndeks);
		}
	}

	public static void main(String[] args) {
		long s = System.currentTimeMillis();

		Random generator = new Random(15);

		double[] a = new double[25];
		for (int i = 0; i < a.length; i++)
			a[i] = generator.nextDouble()*100;

		double p = 500;


		for (int i = 0; i < 100; i++) {
			System.out.println(ostmisViisid(a, p));
		}
//		System.out.println(559126819);
		System.out.println();

		System.out.println(System.currentTimeMillis() - s);
	}

}
