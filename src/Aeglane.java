class Aeglane {

	public static int ostmisViisid(double[] a, double p){
		Kodu7tärn.quicksort(a, 0, a.length-1);

		int i = 0;
		for (; i < a.length; i++)
			if (a[i] > p)
				break;

		double[] tooted = new double[i];
		System.arraycopy(a, 0, tooted, 0, tooted.length);

		int tulemus = funk(tooted, p, 0);
		if (tulemus == 0)
			return 1;

		return tulemus;
	}


	public static int funk(double[] tooted, double jaak, int indeks){
		int tulemus = 0;
		for (; indeks < tooted.length; indeks++) {
			double uusJaak = jaak - tooted[indeks];

			if (uusJaak >= 0){
				if (uusJaak >= tooted[0]){
					tulemus += funk(tooted, uusJaak, indeks);
				}else
					tulemus += 1;

			}else
				break;
		}

		return tulemus;
	}
}
