class Kodu7tärn {

//	public static int loendur;

	public static int ostmisViisid(double[] a, double p){


		quicksort(a, 0, a.length-1);

		int i = 0;
		for (; i < a.length; i++)
			if (a[i] > p)
				break;

		double[] tooted = new double[i];
		System.arraycopy(a, 0, tooted, 0, tooted.length);

		int tulemus = funk(tooted, p, tooted.length-1);
		if (tulemus == 0){
			return 1;
		}

		return tulemus;
	}


	public static int funk(double[] tooted, double jaak, int indeks){
		int tulemus = 0;
		for (; indeks >= 0; indeks--) {
			double uusJaak = jaak - tooted[indeks];

			if (uusJaak >= 0){
				if (uusJaak >= tooted[0]){
					tulemus += funk(tooted, uusJaak, indeks);
				}else
					tulemus += 1;

			}
		}

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

		double[] a = new double[10];
		for (int i = 0; i < a.length; i++)
			a[i] = Math.random()*100;

		double p = 500;

		System.out.println(ostmisViisid(a, p));
//		System.out.println(loendur);
    }

}
