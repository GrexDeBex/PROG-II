import java.util.Arrays;
import java.util.Random;

class Koopia2 {

//	public static int loendur;

	public static int ostmisViisid(double[] a, double p){
		quicksort(a, 0, a.length-1);

		int i = 0;
		for (; i < a.length; i++)
			if (a[i] > p)
				break;

		double[] tooted = new double[i];
		System.arraycopy(a, 0, tooted, 0, tooted.length);

		if (tooted.length == 0)
			return 1;

		int[] kombinatsioon = new int[(int) (p /tooted[0])];
		int[] ajalugu = new int[kombinatsioon.length];

		int tulemus;
//		tulemus = Aeglane.ostmisViisid(a, p);

		System.out.println(funk(tooted, p, tooted.length-1));
		tulemus = funk(tooted, p, tooted.length-1 ,kombinatsioon, ajalugu, 0);


		if (tulemus == 0)
			return 1;


		return tulemus;
	}


	public static int funk(double[] tooted, double jaak, int indeks, int[] kombinatsioon, int[] ajalugu, int indeks2){
		int halb = -1;
		int tulemus = 1;
		for (; indeks > 0; indeks--) {
			double uusJaak = jaak - tooted[indeks];
			kombinatsioon[indeks2] = indeks;

			if (indeks2 == 0 && indeks % 2 == 1 && ajalugu[indeks2] != 0){
				tulemus += ajalugu[indeks2];
				continue;
			}
			ajalugu[indeks2] = 0;

			int voimalused;

			if (uusJaak < tooted[0]){
				voimalused = (uusJaak < 0) ? 0 : 1;
				if (indeks2 > 0)
					if (kombinatsioon[1] < kombinatsioon[0]){
						uusJaak = uusJaak + tooted[kombinatsioon[0]] - tooted[kombinatsioon[0]-1];
						if (uusJaak >= 0)
							ajalugu[0] += funk(tooted, uusJaak, indeks) - voimalused;

					}

			}else
				voimalused = funk(tooted, uusJaak, indeks, kombinatsioon, ajalugu, indeks2+1);



			tulemus += voimalused;

			halb = (halb == -1) ? voimalused : halb;

		}


		if (indeks2 > 0)
			ajalugu[indeks2-1] += tulemus - halb;


		return tulemus;
	}


	public static int funk(double[] tooted, double jaak, int indeks){
		int tulemus = 1;
		for (; indeks > 0; indeks--) {
			double uusJaak = jaak - tooted[indeks];


			int voimalused;
			if (uusJaak < 0){
				voimalused = 0;
			}else if (uusJaak < tooted[0]){
				voimalused = 1;
			}else{
				voimalused = funk(tooted, uusJaak, indeks);
			}
			tulemus += voimalused;
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

		Random generator = new Random(15);
		long s = System.currentTimeMillis();

		double[] a = new double[10];
		for (int i = 0; i < a.length; i++)
			a[i] = generator.nextDouble()*100;

		double p = 500;

		System.out.println(ostmisViisid(a, p));

		System.out.println(System.currentTimeMillis() - s);
	}

}



class Koopia3 {

//	public static int loendur;

	public static int ostmisViisid(double[] a, double p){
		quicksort(a, 0, a.length-1);

		int i = 0;
		for (; i < a.length; i++)
			if (a[i] > p)
				break;

		double[] tooted = new double[i];
		System.arraycopy(a, 0, tooted, 0, tooted.length);

		if (tooted.length == 0)
			return 1;

		int[] kombinatsioon = new int[(int) (p /tooted[0])];
		int[] ajalugu = new int[tooted.length];

		int tulemus;
//		tulemus = Aeglane.ostmisViisid(a, p);

//		System.out.println(funk(tooted, p, tooted.length-1));
		tulemus = funk(tooted, p, tooted.length-1 ,kombinatsioon, ajalugu, 0);


		if (tulemus == 0)
			return 1;


		return tulemus;
	}


	public static int funk(double[] tooted, double jaak, int indeks, int[] kombinatsioon, int[] ajalugu, int indeks2){
		int tulemus = 1;

		if (indeks2 > 1){
			for (int i = 2; i <= kombinatsioon[0]; i++) {
				if (i > kombinatsioon[1]){
					ajalugu[i] += 1;
				}
			}
		}


		if (indeks2 == 1){
			for (int i = 2; i <= kombinatsioon[0]; i++) {
				ajalugu[i] += 1;
			}
		}


		for (; indeks > 0; indeks--) {
			double uusJaak = jaak - tooted[indeks];
			kombinatsioon[indeks2] = indeks;

			if (indeks2 == 0 && ajalugu[ajalugu.length-1] != 0){
				for (int i = 2; i < ajalugu.length; i++) {
					tulemus += ajalugu[i];
				}

				break;
			}

			int voimalused;

			if (uusJaak < tooted[0]){
				voimalused = (uusJaak < 0) ? 0 : 1;
				if (indeks2 > 0){

					for (int i = 2; i <= kombinatsioon[0]; i++) {
						if (i > kombinatsioon[1]){
							ajalugu[i] += voimalused;
							double uus = uusJaak + tooted[kombinatsioon[0]] - tooted[i-1];
							if (uus >= 0)
								ajalugu[i] += funk(tooted, uus, indeks) - voimalused;
						}
					}

				}



			}else{
				voimalused = funk(tooted, uusJaak, indeks, kombinatsioon, ajalugu, indeks2+1);
			}
			tulemus += voimalused;

		}


		return tulemus;
	}


	public static int funk(double[] tooted, double jaak, int indeks){
		int tulemus = 1;
		for (; indeks > 0; indeks--) {
			double uusJaak = jaak - tooted[indeks];


			int voimalused;
			if (uusJaak < 0){
				voimalused = 0;
			}else if (uusJaak < tooted[0]){
				voimalused = 1;
			}else{
				voimalused = funk(tooted, uusJaak, indeks);
			}
			tulemus += voimalused;
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

		Random generator = new Random(15);
		long s = System.currentTimeMillis();

		double[] a = new double[25];
		for (int i = 0; i < a.length; i++)
			a[i] = generator.nextDouble()*100;

		double p = 500;

		System.out.println(ostmisViisid(a, p));

		System.out.println(System.currentTimeMillis() - s);
	}

}

