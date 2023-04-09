

class Kodu7b {

	public static long kuningad(int m, int n){
		int[][] laud = new int[m][n];

		return katsed(laud, 0, 0);
	}


	public static long katsed(int[][] laud, int rida, int veerg){
		int rida1 = rida;
		int veerg1 = veerg;

		if (veerg+1 == laud[0].length){
			rida++;
			veerg = 0;
		}else
			veerg++;


		long tulemus1 = 0;
		long tulemus2 = 0;
		long tulemus3 = 0;
		long tulemus4 = 0;

		if (kontroll1(laud, rida1, veerg1)){
			if (rida == laud.length){
				tulemus1 = 1;
			} else {
				laud[rida1][veerg1] = 1;
				tulemus1 = katsed(laud, rida, veerg);
			}
		}

		if (kontroll2(laud, rida1, veerg1)){
			if (rida == laud.length){
				tulemus2 = 1;
			} else {
				laud[rida1][veerg1] = 2;
				tulemus2 = katsed(laud, rida, veerg);
			}
		}

		if (kontroll3(laud, rida1, veerg1)){
			if (rida == laud.length){
				tulemus3 = 1;
			} else {
				laud[rida1][veerg1] = 3;
				tulemus3 = katsed(laud, rida, veerg);
			}
		}
		if (rida == laud.length){
			tulemus4 = 1;
		} else {
			laud[rida1][veerg1] = 4;
			tulemus4 = katsed(laud, rida, veerg);
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


    public static void main(String[] args) {
		long s = System.currentTimeMillis();
		System.out.println(kuningad(6, 4));
		System.out.println(System.currentTimeMillis() - s);

    }//peameetod
}//Kodu7b
