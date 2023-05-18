import java.util.*;

public class Kodu10b {
	public static String[] lühimTuletus(String[] sonad, String ls, String ss) {
		return Kodu10bUL1.lühimTuletus(sonad, ls, ss);
	}
	public static String[] sõnad(String failinimi){
		return Kodu10bUL1.sõnad(failinimi);
	}





	public static String[] pikimLühimTuletus(String[] sonad){
		String[] tulemus = {sonad[0]};
		HashMap<String, HashSet<String>> seosed = seosed(sonad);

		for (int i = 0; i < sonad.length; i++) {
			for (int j = i+1; j < sonad.length; j++) {

//				int pikkus = minimaalneJadaPikkus(seosed, sonad[i], sonad[j]);
//				if (pikkus <= tulemus.length)
//					continue;
//
//				tulemus = luhim(seosed, sonad[i], sonad[j], pikkus);
			}
		}

		return tulemus;
	}

	public static HashMap<String, HashSet<String>> seosed(String[] sonad){
		HashMap<String, HashSet<String>> seosed = new HashMap<>();
		for (String sona1 : sonad) {
			HashSet<String> seos = new HashSet<>();

			for (String sona2 : sonad)
				if (onTuletis(sona1, sona2))
					seos.add(sona2);

			seosed.put(sona1, seos);
		}

		return seosed;
	}

	public static boolean onTuletis(String s1, String s2){
		int loendur = 0;
		for (int i = 0; i < 4; i++)
			if (s1.charAt(i) == s2.charAt(i))
				loendur++;

		return loendur == 3;
	}


	public static int minimaalneJadaPikkus(HashMap<String, HashSet<String>> seosed, String algus, String lopp){
		HashSet<String> koikSonad = new HashSet<>(seosed.get(algus));
		ArrayList<HashSet<String>> liikmed = new ArrayList<>();
		liikmed.add(new HashSet<>(Collections.singleton(algus)));
		liikmed.add(new HashSet<>(seosed.get(algus)));

		boolean loppLeitud = false;
		int loendur = 2;

		tsukkel:
		while (true){
			HashSet<String> liige = new HashSet<>();

			for (String sona : liikmed.get(loendur - 1)) {
				if (sona.equals(lopp)) {
					loppLeitud = true;
					break tsukkel;
				}

				liige.addAll(seosed.get(sona));
			}
			liige.removeAll(koikSonad);
			liikmed.add(liige);
			if (!koikSonad.addAll(liige))
				break;

			loendur++;
		}

		liikmed.set(loendur-1, new HashSet<>(Collections.singleton(lopp)));


		for (int i = liikmed.size()-1; i > 1; i--) {
			HashSet<String> liige1 = liikmed.get(i);
			HashSet<String> liige2 = liikmed.get(i-1);
			HashSet<String> uusLiige = new HashSet<>();

			for (String sona : liige1)
				uusLiige.addAll(seosed.get(sona));

			liige2.retainAll(uusLiige);
		}
		for (HashSet<String> strings : liikmed) {
			System.out.println(strings);
		}

		if (loppLeitud)
			return loendur;
		else
			return -1;
	}


	public static String[] luhim(HashMap<String, HashSet<String>> seosed, String ls, String ss, int pikkus) {
		ArrayList<String> luhim = new ArrayList<>();
		ArrayList<String> jada = new ArrayList<>();

		jada.add(ls);
		rek(seosed, ls, ss, luhim, jada, pikkus);

		return luhim.toArray(new String[0]);
	}

	public static void rek(HashMap<String, HashSet<String>> seosed, String ls, String ss,
							  ArrayList<String> luhim, ArrayList<String> jada, int pikkus){

		if (jada.size() == pikkus)
			return;

		for (String sona : seosed.get(ls)) {

			if (sona.equals(ss)) {
				luhim.addAll(jada);
				luhim.add(ss);
				return;
			}

			if (!jada.contains(sona)){
				jada.add(sona);
				rek(seosed, sona, ss, luhim, jada, pikkus);
				jada.remove(sona);
				if (luhim.size() == pikkus)
					return;
			}
		}
	}











	public static void main(String[] args) {
//		System.out.println(Arrays.toString(pikimLühimTuletus(sõnad("nimi.txt"))));


		HashMap<String, HashSet<String>> seosed = seosed(sõnad("nimi.txt"));

		System.out.println(minimaalneJadaPikkus(seosed, "heli", "haud"));
	}
}