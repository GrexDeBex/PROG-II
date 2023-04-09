import java.math.BigInteger;

class Kodu7b {

	public static long kuningad(int m, int n){
		int[][] laud = new int[m][n];
		long[][] ajalugu = new long[0][];

		if (m > 1)
			ajalugu = new long[m-2][(int) Math.pow(4, n)];


		return katsed(laud, 0, 0, ajalugu);
	}


	public static long katsed(int[][] laud, int rida, int veerg, long[][] ajalugu){
		int ridaJargmine = rida;
		int veergJargmine = veerg;

		if (veergJargmine+1 == laud[0].length){
			ridaJargmine++;
			veergJargmine = 0;
		}else
			veergJargmine++;


		if (veerg == 0 && rida > 1){
			int indeks = tetraToDec(laud[rida-1]);
			long tulemus = ajalugu[rida-2][indeks];

			if (tulemus > 0)
				return tulemus;


			int[] koopia = new int[laud[0].length];
			System.arraycopy(laud[rida-1], 0 , koopia, 0, koopia.length);
			for (int i = 0; i < koopia.length; i++) {
				if (koopia[i] == 2){
					koopia[i] = 1;
					indeks = tetraToDec(koopia);
					tulemus = ajalugu[rida-2][indeks];

					if (tulemus > 0)
						return tulemus;
				}
			}
		}


		long tulemus1 = 0;
		long tulemus2 = 0;
		long tulemus3 = 0;
		long tulemus4;

		if (kontroll1(laud, rida, veerg)){
			if (ridaJargmine == laud.length){
				tulemus1 = 1;
			} else {
				laud[rida][veerg] = 1;
				tulemus1 = katsed(laud, ridaJargmine, veergJargmine, ajalugu);
			}
		}

		if (kontroll2(laud, rida, veerg)){
			if (ridaJargmine == laud.length){
				tulemus2 = 1;
			} else {
				laud[rida][veerg] = 2;
				tulemus2 = katsed(laud, ridaJargmine, veergJargmine, ajalugu);
			}
		}

		if (kontroll3(laud, rida, veerg)){
			if (ridaJargmine == laud.length){
				tulemus3 = 1;
			} else {
				laud[rida][veerg] = 3;
				tulemus3 = katsed(laud, ridaJargmine, veergJargmine, ajalugu);
			}
		}

		if (ridaJargmine == laud.length){
			tulemus4 = 1;
		} else {
			laud[rida][veerg] = 4;
			tulemus4 = katsed(laud, ridaJargmine, veergJargmine, ajalugu);
		}

		long tulemus = tulemus1 + tulemus2 + tulemus3 + tulemus4;

		if (veerg == 0 && rida > 1){
			int indeks = tetraToDec(laud[rida-1]);
			ajalugu[rida-2][indeks] = tulemus;
		}

		return tulemus1 + tulemus2 + tulemus3 + tulemus4;
	}



	public static boolean kontroll1(int[][] laud, int rida, int veerg){
		if (veerg > 0 && (laud[rida][veerg-1] == 2 || laud[rida][veerg-1] == 4))
			return false;


		if (veerg > 0 && rida > 0 && laud[rida-1][veerg-1] == 4)
			return false;


		if (rida > 0 && (laud[rida-1][veerg] == 3 || laud[rida-1][veerg] == 4))
			return false;


		return true;
	}

	public static boolean kontroll2(int[][] laud, int rida, int veerg){
		if (rida > 0 && (laud[rida-1][veerg] == 3 || laud[rida-1][veerg] == 4))
			return false;


		if (veerg+1 < laud[veerg].length && rida > 0 && laud[rida-1][veerg+1] == 3)
			return false;


		return true;
	}

	public static boolean kontroll3(int[][] laud, int rida, int veerg){
		if (veerg > 0 && (laud[rida][veerg-1] == 2 || laud[rida][veerg-1] == 4))
			return false;

		return true;
	}

	public static int tetraToDec(int[] tetra){
		int kordaja = (int) Math.pow(4, tetra.length-1);

		int sum = 0;
		for (int elem : tetra) {
			sum += kordaja * (elem-1);
			kordaja /= 4;
		}

		return sum;
	}


    public static void main(String[] args) {

		long s = System.currentTimeMillis();
		System.out.println(kuningad(10, 9));
		System.out.println(System.currentTimeMillis() - s);

    }//peameetod
}//Kodu7b
