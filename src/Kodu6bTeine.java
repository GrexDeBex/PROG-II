import java.util.Arrays;

class Kodu6bTeine {

	public static int[][] jaotusRühmadeks(int[] sisend) {
		int[][] tulemus = new int[1][1];                                        // Salvestab parima tulemuse
		tulemus[0][0] = 2_000_000_000;                                            // Lisab kõige halvema võimaliku tulemuse võrdluseks

		int[][] ajalugu1 = new int[sisend.length - 2][2];                        // Salvestab eelmiste jaotuste tulemusi

		for (int i = 2; i < sisend.length; i++) {                                // Katsetab erinevate rühmade arvuga jaotusi
			int[][] jaotus = new int[i + 1][1];                                    // Uus jaotus
			jaotus[0][0] = 0;                                                    // Baas väärtused võrdlemiseks
			jaotus[1] = new int[]{sisend[0]};                                    // Lisab esimese sisendi elemendi esimesse rühma


			int[][] ajalugu2 = new int[sisend.length - i][2];
			for (int[] rida : ajalugu2) {
				rida[0] = 1_000_000_000;
				rida[1] = -1_000_000_000;
			}

			jaotus = leiaParimJaotus(sisend, jaotus, 1, 1, 1, ajalugu1, ajalugu2, tulemus[0][0], -1);    // Leiab parima tulemuse selle rühmade arvuga

			ajalugu1 = ajalugu2;

			tulemus = (jaotus[0][0] < tulemus[0][0]) ? jaotus : tulemus;    // Vaatab, kas tulemus on parim


		}


		return Arrays.copyOfRange(tulemus, 1, tulemus.length);            // Tagastab tulemuse
	}


	public static int[][] leiaParimJaotus(int[] sisend, int[][] jaotus, int rida, int elem, int jarg, int[][] ajalugu1, int[][] ajalugu2, int parimVahe, int l) {
//		loendur++;

		if (rida == 2 && elem == 1 && jarg > 2) {
			l = jarg - 3;
		}


		if (jaotus.length - rida == 1) {
			jaotus[rida] = Kodu6b.viimaneRuhmKoopia(sisend, jarg, jaotus[rida][0]);
			tulemus(jaotus, ajalugu2, l);
			return jaotus;
		}

		if (jarg == sisend.length || sisend.length - jarg < jaotus.length - rida - 1) {
			jaotus[0][0] = Integer.MAX_VALUE;
			return jaotus;
		}

		if (rida == 3 && elem == 1) {
			int sum1 = Kodu6b.summa(jaotus[1]);
			int sum2 = Kodu6b.summa(jaotus[2]);

			int max = Math.max(sum1, sum2);
			int min = Math.min(sum1, sum2);

			max = Math.max(ajalugu1[jarg - 3][0], max);
			min = Math.min(ajalugu1[jarg - 3][1], min);

			if (parimVahe <= max - min) {
				tulemus2(jaotus, ajalugu1, ajalugu2, jarg, sum2, l);
				return jaotus;
			}
		}


		int[][] koopia = Kodu6b.koopia(jaotus);

		int[] ruhm = new int[jaotus[rida].length + 1];
		System.arraycopy(jaotus[rida], 0, ruhm, 0, jaotus[rida].length);
		ruhm[ruhm.length - 1] = sisend[jarg];
		jaotus[rida] = ruhm;

		koopia[rida + 1] = new int[]{sisend[jarg]};

		jaotus = leiaParimJaotus(sisend, jaotus, rida, elem + 1, jarg + 1, ajalugu1, ajalugu2, parimVahe, l);
		koopia = leiaParimJaotus(sisend, koopia, rida + 1, 1, jarg + 1, ajalugu1, ajalugu2, parimVahe, l);

		return (koopia[0][0] < jaotus[0][0]) ? koopia : jaotus;
	}


	public static void tulemus2(int[][] jaotus, int[][] ajalugu1, int[][] ajalugu2, int jarg, int sum2, int l) {
		int max = Math.max(sum2, ajalugu1[jarg - 3][0]);
		int min = Math.min(sum2, ajalugu1[jarg - 3][1]);

		if (l != -1 && ajalugu2[l][0] - ajalugu2[l][1] > max - min) {
			ajalugu2[l][0] = max;
			ajalugu2[l][1] = min;
		}

		jaotus[0][0] = Integer.MAX_VALUE;
	}


	public static void tulemus(int[][] jaotus, int[][] ajalugu2, int l) {
		int max = -1_000_000_000;
		int min = 1_000_000_000;

		for (int i = 2; i < jaotus.length; i++) {
			int sum = Kodu6b.summa(jaotus[i]);
			max = Math.max(sum, max);
			min = Math.min(sum, min);
		}


		if (l != -1 && ajalugu2[l][0] - ajalugu2[l][1] > max - min) {
			ajalugu2[l][0] = max;
			ajalugu2[l][1] = min;
		}

		int sum = Kodu6b.summa(jaotus[1]);

		max = Math.max(sum, max);
		min = Math.min(sum, min);


		jaotus[0][0] = max - min;
	}


	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5, 6, 7};
		int[] b = {1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 11117, 4, 5, 1, 2,
				1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 5, 1, 2, 3, 3, 5, 2, 4, 5, 1, 2, 2, 0, 1, 1};
		int[] c = {59594, 63030, 5190, 14138, 24643, 53044};
		int[] d = {1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 67, 4, 5, 1, 2};
		int[] f = {4, 2, -3, 8, -3, 2, -8, -1, -4, -2, -5, 9, -7, 2, -1};
		int[] g = {34629, 111600, 94026, 112119, 60632, 42020, 47532, 25681, 39103, 20688};

		int[] e = new int[250];
		for (int i = 0; i < e.length; i++) {
			int j = (Math.random() < 0.5) ? 1 : -1;
			e[i] = (int) (Math.random() * 100_000 * j);
		}


		long s = System.nanoTime();
		int[][] tulemus = jaotusRühmadeks(d);
		for (int[] elem : tulemus) {
			System.out.println(Arrays.toString(elem));
		}
		System.out.println(System.nanoTime() - s);


	}
}