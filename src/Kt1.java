import java.util.Arrays;

/**
 * Õpilase nimi: Gregor Rämmal
 * Programmeerimine 2 - KT 1 15.03.2023. Palume mitte "package" märksõna lisada,
 * koodis meetodite signatuure muuta, ega (ka tühje) meetodeid kustutada!
 */

public class Kt1 {

    /**
     * Joonistab soovitud küljepikkusega plussidest kaheksanurga.
     * @param n Kahekanurga küljepikkus plussides.
     */
    public static void kaheksanurk(int n){
        for (int i = 0; i < n-1; i++) { // Väljastab ülemise külje ette tulevad tühikud
            System.out.print(" ");
        }
        for (int i = 0; i < n; i++) { // Väljastab ülemise külje
            System.out.print("+");
        }
        System.out.println();



        for (int i = 0; i < n-2; i++) { // Väljastab teise ja kolmanda ülemise külje

            for (int j = 0; j < n-i-2; j++) { // Väljastab tühikud enne külgi
                System.out.print(" ");
            }
            System.out.print("+");


            for (int j = 0; j < n+2*i; j++) {   // Väljastab tühikud külgede vahel
                System.out.print(" ");
            }
            System.out.println("+");
        }



        for (int i = 0; i < n; i++) {   // Väljastab kaks keskmist külge
            System.out.print("+");

            int tyhikuid = 3*n-4;
            for (int j = 0; j < tyhikuid; j++) {    // Väljastab tühikud külgede vahel
                System.out.print(" ");
            }
            System.out.println("+");
        }



        for (int i = n-2; i > 0; i--) {         // Väljastab teise ja kolmanda alumise külje
            int tyhikuid = n - i - 1;
            for (int j = 0; j < tyhikuid; j++) {// Väljastab tühikud enne külgi
                System.out.print(" ");
            }
            System.out.print("+");

            for (int j = 0; j < n+2*i-2; j++) { // Väljastab tühikud külgede vahel
                System.out.print(" ");
            }
            System.out.println("+");
        }



        for (int i = 0; i < n-1; i++) { // Väljastab tühikud enne alumist külge
            System.out.print(" ");
        }
        for (int i = 0; i < n; i++) {   // Väljastab alumise külje
            System.out.print("+");
        }

    }






    /**
     * Leiab antud järjendist kaks alam järjendit, mis ei kattu põhijärjendis, mille summad on võrdsed.
     * @param a Põhijärjend numbritega.
     * @return 4-liikmeline järjend, kus on järjest esimese alamjärjendi algus ja lõppindeks ning
     * teise järjendi algus ja lõpp indeks
     */
    public static int[] võrdseSummagaAlamjärjendid(int[] a) {
        for (int algus1 = 0; algus1 < a.length-1; algus1++) {           // Esimese alamjärjendi algus
            for (int lopp1 = algus1; lopp1 < a.length-1; lopp1++) {     // Esimese alamjärjendi lõpp
                for (int algus2 = lopp1+1; algus2 < a.length; algus2++) {   // Teise alamjärjendi algus
                    for (int lopp2 = algus2; lopp2 < a.length; lopp2++) {        // Teise alamjärjendi lõpp

                        int sum1 = 0;
                        for (int i = 0; i < lopp1-algus1+1; i++) {  // Esimese alamjärjendi summa
                            sum1 += a[algus1+i];
                        }


                        int sum2 = 0;
                        for (int i = 0; i < lopp2-algus2+1; i++) {  // Teise alamjärjendi summa
                            sum2 += a[algus2+i];
                        }


                        if (sum1 == sum2){
                            return new int[]{algus1, lopp1, algus2, lopp2};
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Viib iga elemendi maatriksis ühe võrra mööda diagonaali edasi. Kui element on viimane diagonaalis, siis
     * element viiakse esimesele kohale
     * @param a Antud maatriks.
     */
    public static void lükeDiagonaalis(int[][] a) {
        int temp1; // Element mida tõstma hakatakse
        int temp2; // Element, mis salvestatake järgmiseks tõstmiseks
        for (int diagonaal = 0; diagonaal < a[0].length; diagonaal++) { // Võtab läbi peadiagonaali ja sellest parempoolsed diagonaalid

            int veerg = diagonaal;
            int rida = 0;

            temp1 = a[rida][veerg];

            rida++;
            veerg++;
            for (; veerg < a[0].length && rida < a.length; veerg++) {   // Käib diagonaali elemendi läbi ning tõstab neid edasi
                temp2 = a[rida][veerg];
                a[rida][veerg] = temp1;
                temp1 = temp2;
                rida++;
            }
            a[0][diagonaal] = temp1; // Tõstab viimast elementi
        }

        for (int diagonaal = 1; diagonaal < a.length; diagonaal++) {    // Võtab läbi peadiagonaalist vasakpoolsed diagnoaalid

            int rida = diagonaal;
            int veerg = 0;

            temp1 = a[rida][veerg];

            rida++;
            veerg++;
            for (; rida < a.length && veerg < a[0].length; rida++) { // Käib diagonaali elemendid läbi ja tõstab neid edasi
                temp2 = a[rida][veerg];
                a[rida][veerg] = temp1;
                temp1 = temp2;
                veerg++;
            }
            a[diagonaal][0] = temp1; //Tõstab viimast elementi
        }



    }

    public static void main(String[] args) {
        // Siin võib vabas vormis oma lahendust testida.
        // Automaattestid tõid hinnates main meetodit ei käivita. (Samas peab siiski kompileeruma!)
//        System.out.println("kaheksanurk(5):");
//        kaheksanurk(8);
        int[][] arr =  {{11, 1, 22, 5, 0},
                {0, 43, 7, 6, 0},
                {3, 7, 8, 9, 0},
                {5, 6, 5, 61, 0}};
//        System.out.println(Arrays.toString(võrdseSummagaAlamjärjendid(arr)));
        lükeDiagonaalis(arr);
        System.out.println(Arrays.deepToString(arr));
    }
}