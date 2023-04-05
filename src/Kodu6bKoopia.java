import java.util.Arrays;

class Kodu6bKoopia {
//	public static long loendur;

	public static int[][] jaotusRühmadeks(int[] sisend) {
		int[][] parim_tulemus = new int[1][2];
		parim_tulemus[0][0] = 1_000_000_000;
		parim_tulemus[0][1] = -1_000_000_000;

		for (int i = 2; i < sisend.length; i++) {
			int[][] tulemus = new int[i + 1][2];
			tulemus[1] = new int[]{sisend[0]};
			tulemus[0][0] = -1_000_000_000;
			tulemus[0][1] = 1_000_000_000;

			tulemus = leiaParimJaotus(sisend, tulemus, 1, 1, 1, parim_tulemus[0][0]);

			if (tulemus[0][0] < parim_tulemus[0][0])
				parim_tulemus = tulemus;
		}


		return Arrays.copyOfRange(parim_tulemus, 1, parim_tulemus.length);
	}

	public static int[][] leiaParimJaotus(int[] sisend, int[][] hetkeJaotus, int ruhm, int jarg, int sisendiJarg, int parimVahe) {

		if (ruhm == hetkeJaotus.length-1) {
			hetkeJaotus[ruhm] = viimaneRuhmKoopia(sisend, sisendiJarg, hetkeJaotus[ruhm][0]);
			hetkeJaotus[0][0] = tulemus(hetkeJaotus, hetkeJaotus.length);

			return hetkeJaotus;
		}


		if (sisendiJarg == sisend.length || sisend.length - sisendiJarg < hetkeJaotus.length-ruhm-1 || tulemus(hetkeJaotus, ruhm) >= parimVahe) {
			hetkeJaotus[0][0] = 1_000_000_000;
			return hetkeJaotus;
		}

		int[][] jargmineRuhm = koopia(hetkeJaotus);

		hetkeJaotus[ruhm] = Arrays.copyOf(hetkeJaotus[ruhm], jarg + 1);
		hetkeJaotus[ruhm][jarg] = sisend[sisendiJarg];
		hetkeJaotus = leiaParimJaotus(sisend, hetkeJaotus, ruhm, jarg + 1, sisendiJarg + 1, parimVahe);

		jargmineRuhm[ruhm + 1] = new int[]{sisend[sisendiJarg]};
		jargmineRuhm = leiaParimJaotus(sisend, jargmineRuhm, ruhm + 1, 1, sisendiJarg + 1, parimVahe);



		if (jargmineRuhm[0][0]  < hetkeJaotus[0][0])
			return jargmineRuhm;
		else
			return hetkeJaotus;
	}

	public static boolean tulemus(int[][] jaotus, int ruhm, int parimVahe) {
		int sum = 0;

		for (int elem : jaotus[ruhm]) {
			sum += elem;
		}
		if (sum > jaotus[0][0])
			jaotus[0][0] = sum;

		if (sum < jaotus[0][1])
			jaotus[0][1] = sum;

		return parimVahe > jaotus[0][0] - jaotus[0][1];
	}

	public static int tulemus(int[][] jaotus, int kaugus) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 1; i < kaugus; i++) {
			int sum = 0;
			for (int elem : jaotus[i])
				sum += elem;

			if (min > sum) min = sum;

			if (max < sum) max = sum;

		}

		jaotus[0] = new int[]{max - min};
		return max - min;
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
		int[] b = {1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 67, 4, 5, 1, 2};

		long s = System.nanoTime();

		int[][] tulemus = jaotusRühmadeks(b);
		for (int[] elem : tulemus) {
			System.out.println(Arrays.toString(elem));
		}
//		System.out.println(loendur);

		System.out.println(System.nanoTime() - s);

	}//peameetod

}//Kodu6b
