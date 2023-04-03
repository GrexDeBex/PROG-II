import java.util.Arrays;

class Kodu6b {
	public static int loendur;

	public static int[][] jaotusRühmadeks(int[] sisend) {
		int[][] parim_tulemus = new int[1][1];
		parim_tulemus[0][0] = Integer.MAX_VALUE;

		for (int i = 2; i < sisend.length; i++) {
			int[][] tulemus = new int[i + 1][1];
			tulemus[1] = new int[]{sisend[0]};

			tulemus = leiaParimJaotus(sisend, tulemus, i, 1, 1, 1, parim_tulemus[0][0]);

			if (tulemus[0][0] < parim_tulemus[0][0]) parim_tulemus = tulemus;
		}


		return Arrays.copyOfRange(parim_tulemus, 1, parim_tulemus.length);
	}

	public static int[][] leiaParimJaotus(int[] sisend, int[][] hetkeJaotus, int ruhmadeArv, int ruhm, int jarg, int sisendiJarg, int parim_vahe) {

		if (ruhmadeArv == 1) {
			int[] hetkeRuhm = Arrays.copyOf(hetkeJaotus[ruhm], jarg + sisend.length - sisendiJarg);
			System.arraycopy(sisend, sisendiJarg, hetkeRuhm, jarg, sisend.length - sisendiJarg);

			hetkeJaotus[ruhm] = hetkeRuhm;
			hetkeJaotus[0][0] = tulemus(hetkeJaotus, hetkeJaotus.length);


			return hetkeJaotus;
		}


		if (sisendiJarg == sisend.length || tulemus(hetkeJaotus, ruhm) >= parim_vahe || sisend.length - sisendiJarg < ruhmadeArv-1) {
			hetkeJaotus[0][0] = Integer.MAX_VALUE;
			return hetkeJaotus;
		}



		int[][] jargmineRuhm = new int[hetkeJaotus.length][];
		int[][] samaRuhm = new int[hetkeJaotus.length][];

		long s = System.currentTimeMillis();

		for (int i = 0; i < hetkeJaotus.length; i++) {
			jargmineRuhm[i] = Arrays.copyOf(hetkeJaotus[i], hetkeJaotus[i].length);
			samaRuhm[i] = Arrays.copyOf(hetkeJaotus[i], hetkeJaotus[i].length);
		}

		loendur += System.currentTimeMillis()-s;

		jargmineRuhm[ruhm + 1] = new int[]{sisend[sisendiJarg]};
		jargmineRuhm = leiaParimJaotus(sisend, jargmineRuhm, ruhmadeArv - 1, ruhm + 1, 1, sisendiJarg + 1, parim_vahe);


		samaRuhm[ruhm] = Arrays.copyOf(samaRuhm[ruhm], jarg + 1);
		samaRuhm[ruhm][jarg] = sisend[sisendiJarg];
		samaRuhm = leiaParimJaotus(sisend, samaRuhm, ruhmadeArv, ruhm, jarg + 1, sisendiJarg + 1, parim_vahe);


		if (jargmineRuhm[0][0] < samaRuhm[0][0])
			return jargmineRuhm;
		else
			return samaRuhm;
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


		return max - min;
	}


	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		int[] b = {1, 2, 3, 4, 5, 1, 2, 6, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 67, 4, 5, 1, 2};


		int[][] tulemus = jaotusRühmadeks(b);
		for (int[] elem : tulemus) {
			System.out.println(Arrays.toString(elem));
		}
		System.out.println(loendur);

	}//peameetod

}//Kodu6b
