class Kodu7bKoopia {

	public static long kuningad(int m, int n) {
		int[][] laud = new int[m][n];
		long[][] ajalugu = new long[0][];

		if (m > 1) ajalugu = new long[m - 2][(int) Math.pow(4, n)];


		return katsed(laud, 0, 0, ajalugu);
	}


	public static long katsed(int[][] laud, int rida, int veerg, long[][] ajalugu) {
//		long s = System.nanoTime();
//		while (System.nanoTime() - s < 24000){
//			System.out.print(0);
//		}

		int n = laud[0].length;

		int ridaJargmine = rida;
		int veergJargmine = veerg;

		if (veergJargmine + 1 == n) {
			ridaJargmine++;
			veergJargmine = 0;
		} else
			veergJargmine++;



		if (veerg == 0 && rida > 1) {
			int indeks = tetraToDec(laud[rida - 1]);
			long tulemus = ajalugu[rida - 2][indeks];

			if (tulemus > 0) return tulemus;


			int[] koopia = new int[n];
			for (int i = 0; i < n; i++)
				koopia[i] = laud[rida - 1][i];

			for (int i = 0; i < n; i++) {
				if (koopia[i] == 2) {
					koopia[i] = 1;
					indeks = tetraToDec(koopia);
					tulemus = ajalugu[rida - 2][indeks];

					if (tulemus > 0) return tulemus;
				}
			}
		}


		long tulemus1 = 0;
		long tulemus2 = 0;
		long tulemus3 = 0;
		long tulemus4;

		boolean v = veerg > 0;
		boolean r = rida > 0;

		boolean k1_1 = v && (laud[rida][veerg - 1] == 2 || laud[rida][veerg - 1] == 4);
		boolean k1_2 = v && r && laud[rida - 1][veerg - 1] == 4;
		boolean k1_3 = r && (laud[rida - 1][veerg] == 3 || laud[rida - 1][veerg] == 4);
		boolean k2_2 = veerg + 1 < n && r && laud[rida - 1][veerg + 1] == 3;

		boolean k1 = !k1_1 && !k1_2 && !k1_3;
		boolean k2 = !k1_3 && !k2_2;
		boolean k3 = !k1_1;

		boolean lopp = ridaJargmine == laud.length;


		if (k1) {
			if (lopp) tulemus1 = 1;

			else {
				laud[rida][veerg] = 1;
				tulemus1 = katsed(laud, ridaJargmine, veergJargmine, ajalugu);
			}
		}


		if (k2) {
			if (lopp) tulemus2 = 1;

			else {
				laud[rida][veerg] = 2;
				tulemus2 = katsed(laud, ridaJargmine, veergJargmine, ajalugu);
			}
		}


		if (k3) {
			if (lopp) tulemus3 = 1;

			else {
				laud[rida][veerg] = 3;
				tulemus3 = katsed(laud, ridaJargmine, veergJargmine, ajalugu);
			}
		}


		if (lopp) tulemus4 = 1;

		else {
			laud[rida][veerg] = 4;
			tulemus4 = katsed(laud, ridaJargmine, veergJargmine, ajalugu);
		}

		long tulemus = tulemus1 + tulemus2 + tulemus3 + tulemus4;

		if (veerg == 0 && rida > 1) {
			int indeks = tetraToDec(laud[rida - 1]);
			ajalugu[rida - 2][indeks] = tulemus;
		}

		return tulemus;
	}


	public static int tetraToDec(int[] tetra) {
		int kordaja = 4;
		for (int i = 2; i < tetra.length; i++)
			kordaja = kordaja << 2;

		int sum = 0;
		for (int elem : tetra) {
			sum += kordaja * (elem - 1);
			kordaja = kordaja >> 2;
		}

		return sum;
	}


	public static void main(String[] args) {

		long s = System.currentTimeMillis();

		for (int i = 0; i < 50; i++) {
			System.out.println(kuningad(10, 9));
		}

		System.out.println((System.currentTimeMillis() - s) / 50.0);

	}//peameetod
}//Kodu7b
