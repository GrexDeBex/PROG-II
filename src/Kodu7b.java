import java.util.Arrays;

class Kodu7b {

	public static long loendur;

	public static long kuningad(int m, int n){
		int[][] laud = new int[2*m][2*n];
		int kuningaid = m*n;

		long tulemus = katsed(laud, kuningaid, 0, 0);



		return tulemus;
	}


	public static long katsed(int[][] laud, int kuningaid, int rida, int veerg){
		int rida1 = rida;
		int veerg1 = veerg;

		if (veerg+1 == laud[0].length){
			rida++;
			veerg = 0;
		}else {
			veerg++;
		}



		if (kuningaid == 0){
			for (int[] ints : laud) {
				System.out.println(Arrays.toString(ints));
			}
			System.out.println();
			return 1;
		}

		if (rida == laud.length){
			if (kuningaid == 1 && kontroll(laud, veerg1, rida1)){
				return 1;
			}

			return 0;
		}


		long tulemus1 = 0;
		if (kontroll(laud, veerg1, rida1)){
			laud[rida1][veerg1] = 1;
			tulemus1 = katsed(laud, kuningaid-1, rida, veerg);
			laud[rida1][veerg1] = 0;
		}

		long tulemus2 = katsed(laud, kuningaid, rida, veerg);

		return tulemus1+tulemus2;

	}

	public static boolean kontroll(int[][] laud, int rida, int veerg){
		if (veerg == 0 && rida == 0){
			return true;
		}

		try{

			if (veerg == 0){
				System.out.println(laud[rida - 1][veerg] != 1 && laud[rida - 1][veerg + 1] != 1);
			}else

			if (rida == 0){
				System.out.println( laud[rida][veerg - 1] != 1);
			}else

			if (veerg == laud[0].length-1){
				System.out.println(laud[rida - 1][veerg - 1] != 1 && laud[rida - 1][veerg] != 1 && laud[rida][veerg - 1] != 1);
			}else

				System.out.println( laud[rida - 1][veerg] != 1 && laud[rida][veerg - 1] != 1
					&& laud[rida - 1][veerg - 1] != 1 && laud[rida - 1][veerg + 1] != 1);

		}catch (Exception e){

		}

		if (veerg == 0){
			return laud[rida - 1][veerg] != 1 && laud[rida - 1][veerg + 1] != 1;
		}

		if (rida == 0){
			return laud[rida][veerg - 1] != 1;
		}

		if (veerg == laud[0].length-1){
			return laud[rida - 1][veerg - 1] != 1 && laud[rida - 1][veerg] != 1 && laud[rida][veerg - 1] != 1;
		}

		return laud[rida - 1][veerg] != 1 && laud[rida][veerg - 1] != 1
				&& laud[rida - 1][veerg - 1] != 1 && laud[rida - 1][veerg + 1] != 1;
	}

    public static void main(String[] args) {
		long s = System.currentTimeMillis();
		System.out.println(kuningad(3, 3));
		System.out.println(System.currentTimeMillis() - s);
//		System.out.println(loendur);

    }//peameetod
}//Kodu7b
