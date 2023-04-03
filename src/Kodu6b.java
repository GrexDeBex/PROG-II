import java.util.Arrays;

class Kodu6b {

	public static int[][] jaotusRühmadeks(int[] sisend){
		int[][] parim_tulemus = new int[1][1];
		parim_tulemus[0][0] = Integer.MAX_VALUE;

		for (int i = 2; i < sisend.length; i++) {
			int[][] tulemus = new int[i+1][1];
			tulemus[1] = new int[]{sisend[0]};

			tulemus = leiaParimJaotus(sisend, tulemus, i, 1, 1, 1);

			if (tulemus[0][0] < parim_tulemus[0][0])
				parim_tulemus = tulemus;
		}



		return Arrays.copyOfRange(parim_tulemus, 1, parim_tulemus.length);
	}

	public static int[][] leiaParimJaotus(int[] sisend, int[][] hetkeJaotus, int ruhmadeArv, int ruhm, int jarg, int sisendiJarg){

		if (ruhmadeArv == 1){
			int [] lopp = Arrays.copyOf(hetkeJaotus[ruhm], jarg+sisend.length-sisendiJarg);
			for (int i = sisendiJarg; i < sisend.length; i++) {
				lopp[jarg+i-sisendiJarg] = sisend[i];
			}
			hetkeJaotus[ruhm] = lopp;
			tulemus(hetkeJaotus);


			return hetkeJaotus;
		}

		if (sisendiJarg == sisend.length){
			hetkeJaotus[0][0] = Integer.MAX_VALUE;
			return hetkeJaotus;
		}



		int[][] jargmineRuhm = new int[hetkeJaotus.length][];
		int[][] samaRuhm = new int[hetkeJaotus.length][];

		for (int i = 0; i < hetkeJaotus.length; i++) {
			jargmineRuhm[i] = Arrays.copyOf(hetkeJaotus[i], hetkeJaotus[i].length);
			samaRuhm[i] = Arrays.copyOf(hetkeJaotus[i], hetkeJaotus[i].length);
		}

		jargmineRuhm[ruhm+1] = new int[]{sisend[sisendiJarg]};

		jargmineRuhm = leiaParimJaotus(sisend, jargmineRuhm, ruhmadeArv-1, ruhm+1, 1, sisendiJarg+1);


		samaRuhm[ruhm]= Arrays.copyOf(samaRuhm[ruhm], jarg+1);
		samaRuhm[ruhm][jarg] = sisend[sisendiJarg];

		samaRuhm = leiaParimJaotus(sisend, samaRuhm, ruhmadeArv, ruhm, jarg+1, sisendiJarg+1);



		if (jargmineRuhm[0][0] < samaRuhm[0][0]){
			return jargmineRuhm;
		}

		else{
			return samaRuhm;
		}

	}

	public static void tulemus(int[][] jaotus){
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 1; i < jaotus.length; i++){
			int sum = 0;
			for (int elem : jaotus[i])
				sum += elem;

			if (min > sum)
				min = sum;

			if (max < sum)
				max = sum;

		}

		jaotus[0] = new int[]{max - min};
	}


    public static void main(String[] args) {
        int[] a={1,2,3,4,5};

		int[][] tulemus = jaotusRühmadeks(a);
		for (int[] elem : tulemus) {
			System.out.println(Arrays.toString(elem));
		}

    }//peameetod

}//Kodu6b
