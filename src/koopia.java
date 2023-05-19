import java.util.*;

public class koopia {
	public static String[] lühimTuletus(String[] sonad, String ls, String ss) {
		return Kodu10bUL1.lühimTuletus(sonad, ls, ss);
	}
	public static String[] sõnad(String failinimi){
		return Kodu10bUL1.sõnad(failinimi);
	}





	public static String[] pikimLühimTuletus(String[] sonad){
		String[] tulemus = {sonad[0]};
		HashMap<String, HashSet<String>> seosed = Kodu10bUL1.seosed(sonad);

		for (String sona : sonad) {
			String[] tuletus = minimaalneJadaPikkus(seosed, sona, tulemus.length);
			if (tuletus.length > tulemus.length)
				tulemus = tuletus;
		}

		return tulemus;
	}



	public static String[] minimaalneJadaPikkus(HashMap<String, HashSet<String>> seosed, String algus, int pikkus){
		HashSet<String> koikSonad = new HashSet<>(seosed.get(algus));

		ArrayList<HashSet<String>> liikmed = new ArrayList<>();
		liikmed.add(new HashSet<>(Collections.singleton(algus)));
		liikmed.add(new HashSet<>(seosed.get(algus)));


		while (true){
			HashSet<String> liige = new HashSet<>();

			for (String sona : liikmed.get(liikmed.size()-1))
				liige.addAll(seosed.get(sona));

			liige.removeAll(koikSonad);
			if (liige.size() == 0)
				break;

			liikmed.add(liige);
			koikSonad.addAll(liige);
		}

		if (liikmed.size() < pikkus)
			return new String[0];

		String lopp = "";
		for (String s : liikmed.get(liikmed.size() - 1)) {
			lopp = s;
			break;
		}


		liikmed.set(liikmed.size()-1, new HashSet<>(Collections.singleton(lopp)));
		HashSet<String> uusLiige = new HashSet<>();

		for (int i = liikmed.size()-1; i > 1; i--) {
			uusLiige.clear();
			HashSet<String> liige1 = liikmed.get(i);
			HashSet<String> liige2 = liikmed.get(i-1);

			for (String sona : liige1)
				uusLiige.addAll(seosed.get(sona));

			liige2.retainAll(uusLiige);
		}

		String[] tulemus = new String[liikmed.size()];
		tulemus[0] = algus;
		tulemus[tulemus.length-1] = lopp;

		leiaJada(tulemus, liikmed, 1);

		return tulemus;
	}


	public static boolean leiaJada(String[] jada, ArrayList<HashSet<String>> liikmed, int indeks){
		if (indeks == jada.length-1)
			return true;

		for (String sona : liikmed.get(indeks)) {
			if (Kodu10bUL1.onTuletis(jada[indeks-1], sona)){
				jada[indeks] = sona;
				if (leiaJada(jada, liikmed, indeks+1))
					return true;
			}
		}

		return false;
	}


	public static HashMap<String, HashSet<String>> seosed(String[] sonad){
		HashMap<String, HashSet<String>> seosed = new HashMap<>();
		for (String sona1 : sonad) {
			HashSet<String> seos = new HashSet<>();
			for (String sona2 : sonad)
				if (Kodu10bUL1.onTuletis(sona1, sona2))
					seos.add(sona2);

			seosed.put(sona1, seos);
		}

		return seosed;
	}






	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		System.out.println(Arrays.toString(pikimLühimTuletus(sõnad("nimi.txt"))));
//		HashMap<String, HashSet<String>> seosed = Kodu10bUL1.seosed(sõnad("nimi.txt"));
		System.out.println(System.currentTimeMillis() - s);

	}
}