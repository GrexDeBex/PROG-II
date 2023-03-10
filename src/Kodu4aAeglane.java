
/*
 * Klass on aeglane versioon suurima ühtedest moodustatud ruudu leidmiseks.
 *
 * Algselt leitakse maatriksist suurim võimalik ruut, mis võiks eksisteerida, ning võetakse tema väikseim külg parameetriks,
 * kust alates hakatakse ruute otsima. Kui maatriksi element on ruudu vasak ülemine nurk ning otsitaks hektel n küljepikkusega
 * ruutu, siis see koordinaat saab olla ainult ühe ruudu nurk. Käiakse läbi iga selline võimalik kordinaat ning vaadatakse
 * kas seal on ruut või mitte.
 * */


public class Kodu4aAeglane {

	/**
	 * Leiab suurima ühtedest koosneva ruudu
	 *
	 * @param maatriks Antud maatriks
	 * @return Suurima ruudu küljepikkus.
	 */
	public static int ruutÜhtedest(boolean[][] maatriks) {
		int massiiviPikkus = maatriks.length;

		if (massiiviPikkus == 1) {    // Erandi kontroll, kui maatriks on 1x1
			if (maatriks[0][0]) {
				return 1;
			} else {
				return 0;
			}
		}


		int ruuduPikkus = suurimRuut(maatriks);            // Leiab suurima võimaliku ruudu küljepikkuse


		for (; ruuduPikkus > 1; ruuduPikkus--) {                    // Käib läbi iga võimaliku küljepikkuse alates suurimast
			int võimalusi = massiiviPikkus - ruuduPikkus + 1;        // Leiab, mitu erinevat kindla küljepikkusega ruutu mahub maatriksi

			for (int iNurk = 0; iNurk < võimalusi; iNurk++) {        // Käib läbi iga võimaliku ruudu leides vasaku ülemise nurga koordinaadid
				for (int jNurk = 0; jNurk < võimalusi; jNurk++) {

					if (kontrolliRuutu(ruuduPikkus, iNurk, jNurk, maatriks)) {
						return ruuduPikkus;
					}
				}
			}
		}

		return 0;
	}

	/**
	 * Leiab maatriksist suurima võimaliku küljepikkuse, mis võib ruudul olla
	 *
	 * @param maatriks Antud maatriks
	 * @return Suurima võimaliku ruudu küljepikkus.
	 */
	public static int suurimRuut(boolean[][] maatriks) {
		int pikkus = maatriks.length;
		int max1 = 0;        // Pikima rea pikkus
		int max2 = 0;        // Teise pikima rea pikkus
		int max3 = 0;        // Pikima veeru pikkus
		int max4 = 0;        // Teise pikima veeru pikkus


		for (boolean[] rida : maatriks) {        // Leiab kaks pikimat ühtede jada ridades
			int loendur = 0;
			int max = 0;

			for (boolean elem : rida) {            // Leiab rea pikima ühtede jada
				if (elem) {
					loendur++;

				} else {
					if (loendur > max) {
						max = loendur;
					}
					loendur = 0;
				}
			}

			if (loendur > max) {                // Uuendakse vastavalt andmeid
				max = loendur;
			}

			if (max > max1) {
				max2 = max1;
				max1 = max;

			} else if (max > max2) {
				max2 = max;
			}
		}


		for (int i = 0; i < pikkus; i++) {            // Leiab kaks pikimat ühtede jada veerus
			int loendur = 0;
			int max = 0;

			for (boolean[] rida : maatriks) {        // Leiab rea pikima ühtede jada
				if (rida[i]) {
					loendur++;

				} else {
					if (loendur > max) {
						max = loendur;
					}
					loendur = 0;
				}
			}

			if (loendur > max) {                    // Uuendakse vastavalt andmeid
				max = loendur;
			}

			if (max > max3) {
				max4 = max3;
				max3 = max;
				if (max4 >= max2) {
					break;
				}

			} else if (max > max4) {
				max4 = max;
				if (max4 >= max2) {
					break;
				}
			}
		}
		return Math.min(max2, max4);        // Tagastab kõige suurema ruudu kõige väiksema küljepikkuse
	}


	/**
	 * Kontrollib, kas antud kohas asub ühtedest ruut
	 *
	 * @param ruuduPikkus Kontrollitav küljepikkus
	 * @param iNurk       Ruudu vasaku ülemise nurga rida
	 * @param jNurk       Ruudu vasaku ülemise nurga veerg
	 * @param maatriks    Antud maatriks
	 * @return True, kui selles kohas asus ruut.
	 */
	public static boolean kontrolliRuutu(int ruuduPikkus, int iNurk, int jNurk, boolean[][] maatriks) {

		for (int l = ruuduPikkus - 1; l >= 0; l--) {        // Kontrollib kas read moodustavad ühtedest küljed
			if (!maatriks[iNurk + l][jNurk] || !maatriks[iNurk][jNurk + l]) {
				return false;
			}
		}


		iNurk = iNurk + ruuduPikkus - 1;        // Leiab parema alumise nurga koordinaadid
		jNurk = jNurk + ruuduPikkus - 1;
		for (int l = ruuduPikkus - 2; l >= 0; l--) {            // Kontrollib, kas veerud moodustavad ühtedest küljed
			if (!maatriks[iNurk - l][jNurk] || !maatriks[iNurk][jNurk - l]) {
				return false;
			}
		}

		return true;
	}

}