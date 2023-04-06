import java.util.Arrays;

class Kodu6bJaak {

	public static int[][] jaotusRühmadeks(int[] sisend) {
		int[][] tulemus = new int[1][];                                            // Salvestab parima tulemuse
		tulemus[0] = new int[]{2_000_000_000, 1_000_000_000, -1_000_000_000};    // Lisab kõige halvema võimaliku tulemuse võrdluseks

		int jaak = summa(sisend) - sisend[0];                                    // Kui palju sisendi summast on veel järel

		for (int i = 2; i < sisend.length; i++) {                                // Katsetab erinevate rühmade arvuga jaotusi


			int[][] jaotus = new int[i + 1][3];                                    // Uus jaotus
			jaotus[0] = new int[]{0, -1_000_000_000, 1_000_000_000};            // Baas väärtused võrdlemiseks
			jaotus[1] = new int[]{sisend[0]};                                    // Lisab esimese sisendi elemendi esimesse rühma

			jaotus = leiaParimJaotus(sisend, jaotus, 1, 1, 1, jaak, tulemus[0][0]);    // Leiab parima tulemuse selle rühmade arvuga

			tulemus = (jaotus[0][0] < tulemus[0][0]) ? jaotus : tulemus;    // Vaatab, kas tulemus on parim


		}


		return Arrays.copyOfRange(tulemus, 1, tulemus.length);            // Tagastab tulemuse
	}

	public static int[][] leiaParimJaotus(int[] sisend, int[][] jaotus, int rida, int elem, int jarg, int jaak, int parimVahe) {

		if (jaotus.length - rida == 1) {
			jaotus[rida] = viimaneRuhmKoopia(sisend, jarg, jaotus[rida][0]);
			tulemus(jaotus, rida);

			return jaotus;
		}

		boolean sisenditeHulk = jarg == sisend.length || sisend.length - jarg < jaotus.length - rida - 1;
		boolean jaagiSobilikus = jaagiKontroll(jaotus, rida, jaak, jaotus.length - rida, parimVahe);

		if (sisenditeHulk || jaagiSobilikus) {
			jaotus[0][0] = 2_000_000_000;
			return jaotus;
		}

		int[][] koopia = koopia(jaotus);

		int[] ruhm = new int[jaotus[rida].length + 1];
		System.arraycopy(jaotus[rida], 0, ruhm, 0, jaotus[rida].length);
		ruhm[ruhm.length - 1] = sisend[jarg];
		jaotus[rida] = ruhm;

		koopia[rida + 1] = new int[]{sisend[jarg]};
		tulemus(koopia, rida);


		jaotus = leiaParimJaotus(sisend, jaotus, rida, elem + 1, jarg + 1, jaak - sisend[jarg], parimVahe);
		koopia = leiaParimJaotus(sisend, koopia, rida + 1, 1, jarg + 1, jaak - sisend[jarg], parimVahe);

		return (koopia[0][0] < jaotus[0][0]) ? koopia : jaotus;
	}


	public static boolean jaagiKontroll(int[][] jaotus, int ruhm, int jaak, int ruhmiJaanud, int parimVahe) {
		for (int elem : jaotus[ruhm])
			jaak += elem;

		int min = jaak / ruhmiJaanud;
		int max = (min == (jaak - 1) / (ruhmiJaanud)) ? min + 1 : min;
		min = Math.min(jaotus[0][2], min);
		max = Math.max(jaotus[0][1], max);

		return parimVahe <= max - min;
	}

	public static void tulemus(int[][] jaotus, int ruhm) {
		int sum = summa(jaotus[ruhm]);

		jaotus[0][1] = Math.max(sum, jaotus[0][1]);
		jaotus[0][2] = Math.min(sum, jaotus[0][2]);

		jaotus[0][0] = jaotus[0][1] - jaotus[0][2];
	}


	public static int[][] koopia(int[][] originaal) {
		int[][] koopia = new int[originaal.length][];

		for (int i = 0; i < originaal.length; i++) {
			int[] rida = new int[originaal[i].length];
			System.arraycopy(originaal[i], 0, rida, 0, rida.length);
			koopia[i] = rida;
		}

		return koopia;
	}

	public static int summa(int[] massiiv) {
		int sum = 0;
		for (int elem : massiiv)
			sum += elem;

		return sum;
	}


	public static int[] viimaneRuhmKoopia(int[] sisend, int sisendiJarg, int esimeneLiige) {
		int[] koopia = new int[sisend.length - sisendiJarg + 1];

		koopia[0] = esimeneLiige;

		for (int i = 1; i < koopia.length; i++, sisendiJarg++)
			koopia[i] = sisend[sisendiJarg];

		return koopia;
	}


	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		int[] b = {1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 11117, 4, 5, 1, 2, 1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 5, 1, 2, 3, 3, 5, 2, 4, 5, 1, 2, 2, 0, 1, 1};
		int[] c = {59594, 63030, 5190, 14138, 24643, 53044};
		int[] d = {1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 67, 4, 5, 1, 2};

		int[] e = new int[250];

		for (int i = 0; i < e.length; i++) {
			int j = (Math.random() < 0.5) ? 1 : -1;
			e[i] = (int) (Math.random() * 100_000 * j);
		}

		long s = System.nanoTime();

		int[][] tulemus = jaotusRühmadeks(e);
		for (int[] elem : tulemus) {
			System.out.println(Arrays.toString(elem));
		}

		System.out.println(System.nanoTime() - s);

	}//peameetod

}//Kodu6b