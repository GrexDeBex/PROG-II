package Tehtud;

/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 1a
 * Teema: Tsükklid, järjendid
 *
 * Autor: Gregor Rämmal
 *
 **********************************/


public class Kodu1a {

    /**
     * Väljastab 5 suurimat algarvu ringi teatud piirkonnas.
     * @param n Vaadeldava piirkonna ülempiir.
     */
    static void algarvuRingid5Suurimat(int n) {
        int valjastatud_ringid = 0;         // Muutuja loeb, mitu ringi on väljastatud
        String arv = "";                    // Muutuja, millest hakatakse genereerima algarvu ringi kandidaate

        for (int arvu_pikkus = Integer.toString(n).length(); arvu_pikkus > 1; arvu_pikkus--) {                        // Tsükkel kontrollib erineva kohalisi arve alustades suurimatest ja lõpetades kümnelistega
            valjastatud_ringid = suurimate_numbrite_loomine(arv, arvu_pikkus-1, n, valjastatud_ringid);     // Loeb kokku kõik arvuringid samakohalistes arvudes

            if (valjastatud_ringid == -1) {     // Kui ringide arv on -1, siis on 5 ringi väljastatud.
                break;
            }
        }
    }


    /**
     * Loob kõik võimalikud algarvu ringi kandidaadid, kontrollib neid ning väljastab need.
     * @param arv Osaliselt loodud algarvu ringi kandidaat.
     * @param arvu_pikkus Määrab, mitu numbrit on veel vaja lisada muutujale arv.
     * @param n Vaadeldava piirkonna ülempiir.
     * @param valjastatud_ringid Loetleb, mitu arvu on juba väljastatud.
     * @return Juba väljastatud ringide arv või väärtus -1, kui on väljastatud 5 ringi.
     */
    static int suurimate_numbrite_loomine(String arv, int arvu_pikkus, int n, int valjastatud_ringid){
        String[] numbrid = {"9", "7", "3", "1"};    // Kandidaat ei saa sisaldada paarisarve, sest siis üks ringi arvudest jagub 2-ga
                                                    // ning ei sa sisaldada 5 ja 0, sest siis jaguks üks arvudest 5-ga.
        for (String nr : numbrid) {           // Proovib järgi kõik 9, 7, 3 ja 1 kombinatsioonid
            if (arvu_pikkus == 0) {           // Kui arvu_pikkus on null siis on saavutatud piisava kohaline arv
                if (ringi_kontroll(arv+nr, n)) {    //Kontrollib, kas tegemist on algarvu ringiga
                    System.out.println(arv+nr);

                    valjastatud_ringid++;
                    if (valjastatud_ringid == 5) {      // Kui on väljastatud 5 arvu, siis lõpetatkse programm.
                        return -1;
                    }
                }
            }else {                                // Kui ei ole veel õige arvu pikkus, siis lisatakse järgmises tsüklis uus arv
                valjastatud_ringid = suurimate_numbrite_loomine(arv+nr, arvu_pikkus-1, n, valjastatud_ringid);
                if (valjastatud_ringid == -1){     // Kui on väljastatud 5 arvu, siis lõpetatkse programm.
                    return -1;
                }
            }
        }
        return valjastatud_ringid;              // Tagastatakse seni väljastatud ringide arv
    }


    /**
     * Loeb, mitu algarvu ringi on vaadeldavas piirkonnas
     * @param n Vaadeldava piirkonna ülempiir.
     * @return Kõigi algarvuringide arv vaadeldavad piirkonnas.
     */
    static int algarvuRingideArv(int n) {
        if (1111111 > n && n > 999999){    // Kiiruse eesmärgil, kui n väärtus on ligi 1 000000, siis taandatakse see
            n = 999999;                    // 999999 peale, kuna 1 111111 on esimene võimalik algarvu ringi kandidaat.
        }

        int ringide_arv = 0;        // Loetleb kõiki algarvu ringe
        for (int arvu_pikkus = Integer.toString(n).length(); arvu_pikkus > 1; arvu_pikkus--) {  // Tsükkel käib läbi kõik erineva kohalised algarvu kandidaadid
            // Selleks, et kandidaatide tekitamisel võetaks võimalikult vähe arve samast arvuringist, teeb kood järgmist.
            // Esimene number arvus peab olema võrdne kõige suurema numbriga arvus, sest muidu asetseb 100% tema arvuringis
            // Temast suurem arv, mis on enne juba läbi kontrollitud, kuna kontrollimine algab suurimatest numbritest

            ringide_arv = koigi_numbrite_loomine("9", arvu_pikkus-2, n, ringide_arv, new String[] {"9", "7", "3", "1"});
            ringide_arv = koigi_numbrite_loomine("7", arvu_pikkus-2, n, ringide_arv, new String[] {"7", "3", "1"});
            ringide_arv = koigi_numbrite_loomine("3", arvu_pikkus-2, n, ringide_arv, new String[] {"3", "1"});
            ringide_arv = koigi_numbrite_loomine("1", arvu_pikkus-2, n, ringide_arv, new String[] {"1"});
        }

        return ringide_arv;
    }


    /**
     * Loob kõik võimalikud algarvu ringi kandidaadid, kontrollib neid ning loelteb neid.
     * @param arv Osaliselt loodud algarvu ringi kandidaat.
     * @param arvu_pikkus Määrab, mitu numbrit on veel vaja lisada muutujale arv.
     * @param n Vaadeldava piirkonna ülempiir.
     * @param ringide_arv Loetleb algarvu ringe.
     * @param numbrid Massiiv võimalikest numbritest, mida kandidaadile lisada.
     * @return Saadud ringide arv.
     */
    static int koigi_numbrite_loomine(String arv, int arvu_pikkus, int n, int ringide_arv, String[] numbrid){
        for (String nr : numbrid) {     // Tsükkel kontrollib läbi kõik võimalikud kombinatsioonid
            if (arvu_pikkus == 0) {     // Kui arvu_pikkus on 0, siis on arv piisava kohaline
				if ( !nr.equals(arv.substring(0,1)) || nr.equals("1") ) { // Esimene ja viimane number arvus ei saa olla samad,
					if (ringi_kontroll(arv+nr, n)) {				//sest siis leidub arvuringis suurem arv, mida on kontrollitud
						// Ning peab kehtima algarvu ring
						ringide_arv++;
					}
				}
            }else {         // Kui arvu kohtade arv pole veel piisav, siis läheb arv järgmisele tsüklile
                ringide_arv = koigi_numbrite_loomine(arv+nr, arvu_pikkus-1, n, ringide_arv, numbrid);
            }
        }
        return ringide_arv;
    }


    /**
     * Kontrollib, kas loodud arv on algarvu ring.
     * @param arv Algarvu ringi kandidaat.
     * @param n Vaadeldava piirkonna ülempiir.
     * @return Kui kandidaat sobib, siis "true", vastasel juhul "false"
     */
    static boolean ringi_kontroll(String arv, int n){
        int int_arv = Integer.parseInt(arv);    // arvu int väärtus
        int arvu_pikkus = arv.length();         // arvu kohtade arv
        int max_arv = int_arv;                  // Suurim arv ringis peab olema esialgne arv

        for (; arvu_pikkus > 0 ; arvu_pikkus--) {   // Arvu kohtade arv on sama, mis on erinevate arvude arv ringis
            // Arv ei tohi olla suurem ülempiirist ja esimeses tsükklis kontrollitakse ainult väikeste algarvudega kogu ring läbi
            if (n <= int_arv || !algarvu_kontroll_1(int_arv)){ // ja alles hiljem kõigi ülejäänud algarvudega, sest
                return false;                       // kiirem on leida tervest ringist üks väikse algarvuga jaguv arv
            }                                       // kui kontrollida ühe arvu jaguvust kõigi algarvudega
            arv = arv.substring(1) + arv.charAt(0); // Arvu numbreid nihutatakse ühe võrra
            int_arv = Integer.parseInt(arv);                  // Arv teisedatakse uuesti int tüübiks

            if (max_arv < int_arv){return false;}       // Kui mingi arv ringilt on suurem kui esialgne arv, siis seda
        }												// on juba kontrollitud

        for (arvu_pikkus = arv.length(); arvu_pikkus > 0 ; arvu_pikkus--) {	// Kui üldisel kontrollil ei leitud mittealgarve
            if (!algarvu_kontroll_2(int_arv)){								// siis kontrollitakse uuesti ülejäänud algarvudega jaguvust
                return false;
            }
            arv = arv.substring(1) + arv.charAt(0);
            int_arv = Integer.parseInt(arv);
        }
        return true;
    }


    /**
     * Kontrollib, kas konkreetne arv on algarv, kontrollides populaarsemate algarvudega jaguvust.
     * @param arv Arv, mida hakatakse kontrollima.
     * @return Tagastab, kas arv on algarv.
     */
    static boolean algarvu_kontroll_1(int arv){
        if (arv % 3 == 0){ 	// Kuna järgnevast tsüklist jääb 3 välja, siis kontrollitakse 3-ga jaguvust manuaalselt
            return false;
        }
		// Tsükkel kontrollib, kas arv jagub mõne algarvuga
        for (int i = 1; i < 15 && i*6+1 < Math.sqrt(arv); i++) {  	// Selleks, et arv oleks algarv ei tohi ta jaguda 2 või 3-ga
            if (arv % (i*6+1) == 0 || arv % (i*6+5) == 0){		// nii tekib 6-liikmeline tsükkel 0, 1, 2, 3, 4, 5, kus ainult teine ja kuues
                return false;									// liige ei jagu 2 või 3-ga ehk i*6+1 või i*6+5 ning ainult need saavad olla algarvud
            }													// Samuti ei saa olla arvu väikseim tegur suurem kui arvu ruutjuur
        }														// Tsükkel kontrollib kiiruse nimel ainult esimesi võimalike algarve
        return true;
    }


    /**
     * Kontrollib, kas konkreetne arv on algarv, kontrollides kõigi ülejäänud algarvudega jaguvust.
     * @param arv Arv, mida hakatakse kontrollima.
     * @return Tagastab, kas arv on algarv.
     */
    static boolean algarvu_kontroll_2(int arv){
        for (int i = 15; i*6+1 < Math.sqrt(arv) ; i++) {	//Tsükkel kontrollib kõik ülejäänud algarvud läbi, mida enne ei
            if (arv % (i*6+1) == 0 || arv % (i*6+5) == 0){	// kontrollitud
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
        System.out.println("5 suurimat algarvu ringi kui n = 1 000 000");
        algarvuRingid5Suurimat(1000000);
        System.out.println("\n5 suurimat algarvu ringi kui n = 100 000");
        algarvuRingid5Suurimat(100000);

        System.out.println("\nKogu algarvu ringide arv kui n = 1 000 000");
        System.out.println(algarvuRingideArv(1000000));
        System.out.println("\nKogu algarvu ringide arv kui n = 100 000");
        System.out.println(algarvuRingideArv(100000));
    }
}