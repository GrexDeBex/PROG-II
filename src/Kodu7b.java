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
			return kontroll(laud);
		}

		if (rida == laud.length){
			if (kuningaid == 1){
				laud[rida1][veerg1] = 1;
				int tulemus = kontroll(laud);
				laud[rida1][veerg1] = 0;
				return tulemus;
			}

			return 0;
		}

		laud[rida1][veerg1] = 1;
		long tulemus1 = katsed(laud, kuningaid-1, rida, veerg);

		laud[rida1][veerg1] = 0;
		long tulemus2 = katsed(laud, kuningaid, rida, veerg);

		return tulemus1+tulemus2;

	}

	public static int kontroll(int[][] laud){


		for (int i = 0; i < laud.length; i++) {
			for (int j = 0; j < laud[i].length; j++) {
				if (laud[i][j] == 1){
					for (int k = -1; k < 2; k++) {
						for (int l = -1; l < 2; l++) {
							if (!(l == 0 && k == 0) && (k+i < laud.length && l+j < laud[i].length) &&
									(k+i > -1 && l+j > -1)){
								if (laud[k+i][l+j] == 1){
									return 0;
								}
							}
						}
					}
				}
			}
		}
		loendur++;
		return 1;
	}

    public static void main(String[] args) {
		System.out.println(kuningad(3, 3));
//		System.out.println(loendur);

    }//peameetod
}//Kodu7b
