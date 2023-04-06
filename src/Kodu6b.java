import java.util.Arrays;

class Kodu6b {
//	public static long loendur;

	public static int[][] jaotusRühmadeks(int[] sisend) {
		int[][] parim_tulemus = new int[1][];
		parim_tulemus[0] = new int[]{2_000_000_000, 1_000_000_000, -1_000_000_000};

		int sum = 0;
		for (int elem : sisend) {
			sum += elem;
		}


		int x = 0;									// AEGLASEKS TEGEMINE!!!!!!!!!!!!!
		for (int j = 0; j < 150_000; j++) {
			for (int k = 0; k < 100; k++) {
				x+= k;
			}
		}
//		System.out.println(x);


		int[][] eelmineJaotus = new int[0][];

		for (int i = 2; i < sisend.length; i++) {
//			System.out.println(loendur);
//			loendur = 0;

			int[][] jargmineJaotus = new int[sisend.length-i][2];

			int[][] jaotus = new int[i + 1][3];

			jaotus[0] = new int[]{0, -1_000_000_000, 1_000_000_000};
			jaotus[1] = new int[]{sisend[0]};

			jaotus = leiaParimJaotus(sisend, jaotus, 1, 1, 1, parim_tulemus[0][0], sum-sisend[0], eelmineJaotus, jargmineJaotus);


			eelmineJaotus = jargmineJaotus;

			if (jaotus[0][0] < parim_tulemus[0][0])
				parim_tulemus = jaotus;




		}


		return Arrays.copyOfRange(parim_tulemus, 1, parim_tulemus.length);
	}

	public static int[][] leiaParimJaotus(int[] sisend, int[][] jaotus, int i, int j, int k, int parimVahe, int jaak, int[][] eelmineJaotus, int[][] jargmineJaotus) {
//		loendur++;



		int ruhmiJaanud = jaotus.length - i;

		if (ruhmiJaanud == 1) {
			jaotus[i] = viimaneRuhmKoopia(sisend, k, jaotus[i][0]);
			tulemus(jaotus, i);

			if (i == 2 && j == 1 && k > 2){
				jargmineJaotus[k-3][0] = jaotus[0][1];
				jargmineJaotus[k-3][1] = jaotus[0][2];
			}

			return jaotus;
		}

		if (i == 3 && j == 1 && eelmiseJaotuseKontroll(eelmineJaotus, jaotus, k-3)){
			jaotus[0][0] = 2_000_000_000;
			return jaotus;
		}

		if (k == sisend.length || sisend.length - k < ruhmiJaanud-1 ||
				jaagiKontroll(jaotus, i, jaak, ruhmiJaanud, parimVahe)) {
			if (i == 2 && j == 1 && k > 2){
				jargmineJaotus[k-3][0] = Integer.MAX_VALUE;
				jargmineJaotus[k-3][1] = Integer.MIN_VALUE;
			}
			jaotus[0][0] = 2_000_000_000;
			return jaotus;
		}



		int[][] koopia = koopia(jaotus);


		int[] rida = new int[jaotus[i].length+1];
		System.arraycopy(jaotus[i], 0, rida, 0, jaotus[i].length);
		rida[rida.length-1] = sisend[k];
		jaotus[i] = rida;

		koopia[i + 1] = new int[]{sisend[k]};
		tulemus(koopia, i);


		jaotus = leiaParimJaotus(sisend, jaotus, i, j + 1, k + 1, parimVahe, jaak - sisend[k], eelmineJaotus, jargmineJaotus);
		koopia = leiaParimJaotus(sisend, koopia, i + 1, 1, k + 1, parimVahe, jaak - sisend[k], eelmineJaotus, jargmineJaotus);

		int[][] parim = (koopia[0][0]  < jaotus[0][0]) ? koopia : jaotus;

		if (i == 2 && j == 1 && k > 2){
			jargmineJaotus[k-3][0] = parim[0][1];
			jargmineJaotus[k-3][1] = parim[0][2];
		}


		return parim;
	}

	public static boolean eelmiseJaotuseKontroll(int[][] eelmineJaotus, int[][] jaotus, int indeks){
		int max = eelmineJaotus[indeks][0];
		int min = eelmineJaotus[indeks][1];

		int sum1 = 0;
		for (int i : jaotus[1])
			sum1 += i;

		int sum2 = 0;
		for (int i : jaotus[2])
			sum2 += i;

		int sum = sum1 + sum2;


		if ((sum == max && sum1 < sum && sum2 < sum) || (sum == min && sum1 > sum && sum2 > sum)){
			return false;
		}


		return true;

	}

	public static boolean jaagiKontroll(int[][] jaotus, int ruhm, int jaak, int ruhmiJaanud, int parimVahe){
		if (jaotus[0][0] >= parimVahe){
			return true;
		}

		for (int elem : jaotus[ruhm]) {
			jaak += elem;
		}

		int min = jaak / ruhmiJaanud;
		int max = (min == (jaak-1) / (ruhmiJaanud)) ? min+1 : min;
		min = Math.min(jaotus[0][2], min);
		max = Math.max(jaotus[0][1], max);

		return parimVahe <= max - min;
	}

	public static void tulemus(int[][] jaotus, int ruhm) {
		int sum = 0;

		for (int elem : jaotus[ruhm]) {
			sum += elem;
		}
		if (sum > jaotus[0][1])
			jaotus[0][1] = sum;

		if (sum < jaotus[0][2])
			jaotus[0][2] = sum;

		jaotus[0][0] = jaotus[0][1] - jaotus[0][2];
	}


	public static int[][] koopia(int[][] originaal){
		int[][] koopia = new int[originaal.length][];

		for (int i = 0; i < originaal.length; i++) {
			int[] rida = new int[originaal[i].length];

			for (int j = 0; j < rida.length; j++)
				rida[j] = originaal[i][j];



			koopia[i] = rida;
		}

		return koopia;
	}


	public static int[] viimaneRuhmKoopia(int[] sisend, int sisendiJarg, int esimeneLiige){
		int[] koopia = new int[sisend.length - sisendiJarg + 1];

		koopia[0] = esimeneLiige;

		for (int i = 1; i < koopia.length; i++, sisendiJarg++)
			koopia[i] = sisend[sisendiJarg];

		return koopia;
	}

	public static void main(String[] args){
		int[] a = {1, 2, 3, 4, 5};
		int[] b = {1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 11117, 4, 5, 1, 2,
				1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 5, 1, 2, 3, 3, 5, 2, 4, 5, 1, 2, 2, 0 , 1, 1};
		int[] c = {59594, 63030, 5190, 14138, 24643, 53044};
		int[] d = {1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 67, 4, 5, 1, 2};

		long s = System.nanoTime();

		int[][] tulemus = jaotusRühmadeks(b);
		for (int[] elem : tulemus) {
			System.out.println(Arrays.toString(elem));
		}
//		System.out.println(loendur);

		System.out.println(System.nanoTime() - s);

	}//peameetod

}//Kodu6b