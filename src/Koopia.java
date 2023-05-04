import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Koopia {
	public static String eemaldaKordusgrupid(String a) {
		StringBuilder sb = new StringBuilder(a);


		if (a.length() < 2)
			return a;

		boolean jatkub = true;
		while (jatkub){

			StringBuilder sbKoopia = new StringBuilder();

			if (sb.charAt(0) != sb.charAt(1))
				sbKoopia.append(sb.charAt(0));

			for (int i = 1; i < sb.length() - 1; i++)
				if (sb.charAt(i-1) != sb.charAt(i) && sb.charAt(i+1) != sb.charAt(i))
					sbKoopia.append(sb.charAt(i));

			if (sb.charAt(sb.length()-1) != sb.charAt(sb.length()-2))
				sbKoopia.append(sb.charAt(sb.length()-1));

			if (sb.toString().equals(sbKoopia.toString()) || sbKoopia.length() < 2)
				jatkub = false;

			sb = sbKoopia;
		}

		return sb.toString();
	}

	public static int kaashäälikuÜhendeid(String a) {
		ArrayList<Character> kaasHaalikud = new ArrayList<>(List.of('b', 'c', 'd', 'f', 'g', 'h',
				'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 'š', 'z', 'ž', 't', 'v', 'w', 'x', 'y'));

		boolean uhend = false;
		boolean eelmineKaashaalik = false;
		int loendur = 0;
		char eelmine = 0;

		a = a.toLowerCase();

		for (int i = 0; i < a.length(); i++) {
			if (kaasHaalikud.contains(a.charAt(i))){
				if (eelmineKaashaalik && eelmine != a.charAt(i))
					uhend = true;

				eelmine = a.charAt(i);
				eelmineKaashaalik = true;

			}else{

				if (uhend)
					loendur++;

				eelmineKaashaalik = false;
				uhend = false;
			}
		}

		if (uhend)
			loendur++;

		return loendur;
	}




//	public static String[] kõikTulemused(String s){
//		return Aeglane.kõikTulemused(s);
//	}



	public static String[] kõikTulemused(String s){
		StringBuilder sb = new StringBuilder(s);
		HashSet<String> tulemused = new HashSet<>();
		HashSet<String> ajalugu = new HashSet<>();

		rek(tulemused, sb, ajalugu);

		return tulemused.toArray(new String[0]);
	}


	public static void rek(HashSet<String> tulemused, StringBuilder sb, HashSet<String> ajalugu){
		int algus = 0;
		char eelmine = sb.charAt(0);

		for (int i = 1; i < sb.length(); i++) {
			if (sb.charAt(i) != eelmine){
				areng(tulemused, sb, ajalugu, algus, i-1, eelmine);
				eelmine = sb.charAt(i);
				algus = i;
			}
		}

		areng(tulemused, sb, ajalugu, algus, sb.length()-1, eelmine);
		lopp(tulemused, sb);

	}

	public static void lopp(HashSet<String> tulemused, StringBuilder sb){
		int i = 1;
		for (; i < sb.length(); i++)
			if (sb.charAt(i) == sb.charAt(i-1))
				break;

		if (i == sb.length())
			tulemused.add(sb.toString());
	}

	public static void areng(HashSet<String> tulemused, StringBuilder sb, HashSet<String> ajalugu, int algus, int lopp, char eelmine){
		if (algus != lopp){
			sb.delete(algus, lopp+1);

			if (ajalugu.contains(sb.toString())){
				for (int j = algus-1; j < lopp; j++)
					sb.insert(algus, eelmine);
				return;
			}

			ajalugu.add(sb.toString());

			if (sb.length() == 0)
				tulemused.add(sb.toString());
			else
				rek(tulemused, sb, ajalugu);

			for (int j = algus-1; j < lopp; j++)
				sb.insert(algus, eelmine);
		}
	}







	public static void main(String[] args) {
//		String a = "abbbaaacabbbbac";
//		System.out.println(eemaldaKordusgrupid(a));
//		System.out.println();
//
//		a = "ccabbacca";
//		System.out.println(eemaldaKordusgrupid(a));
//		System.out.println();
//
//		a = "Mait Malmsten kehastus korstnapühkijaks.";
//		System.out.println(kaashäälikuÜhendeid(a));
//		System.out.println();
//
//		a = "MaitMalmstenkehastuskorstnapühkijaks";
//		System.out.println(kaashäälikuÜhendeid(a));
//		System.out.println();

		long s = System.currentTimeMillis();
		String a = "cabbacccacacabbacccacacabbacccacacabbacccacacabbacccacacabbacccaca";
		System.out.println(kõikTulemused(a).length);
//		System.out.println(Arrays.toString(kõikTulemused(a)));
		System.out.println(System.currentTimeMillis() - s);
	}
}
