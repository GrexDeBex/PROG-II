import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class koopia {
	public static String[] lühimTuletus(String[] sonad, String ls, String ss) {
		return Kodu10bUL1.lühimTuletus(sonad, ls, ss);
	}


	public static String[] sõnad(String failinimi){
		ArrayList<String> sonad = new ArrayList<>();
		try (Scanner sc = new Scanner(new File(failinimi))) {
			while (sc.hasNextLine())
				sonad.add(sc.nextLine());

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		return sonad.toArray(new String[0]);
	}

	public static String[] luhim(HashMap<String, HashSet<String>> seosed, String ls, String ss, String[] pikim) {
		if (ls.equals(ss))
			return new String[]{ls};


		if (seosed.get(ss).size() == 0)
			return null;

		ArrayList<String> luhim = new ArrayList<>(Collections.nCopies(1000, "0"));
		ArrayList<String> jada = new ArrayList<>();


		jada.add(ls);

		if (rek(seosed, ls, ss, luhim, jada, pikim))
			return null;

		return luhim.toArray(new String[0]);
	}

	public static boolean rek(HashMap<String, HashSet<String>> seosed, String ls, String ss,
				ArrayList<String> luhim, ArrayList<String> jada, String[] pikim){

		if (luhim.size() == jada.size())
			return false;

		for (String sona : seosed.get(ls)) {

			if (sona.equals(ss)) {
				jada.add(ss);
				if (luhim.size() > jada.size()){
					luhim.clear();
					luhim.addAll(jada);

					if (luhim.size() <= pikim.length)
						return true;

				}
				jada.remove(ss);
				break;
			}

			if (!jada.contains(sona)){
				jada.add(sona);

				if (rek(seosed, sona, ss, luhim, jada, pikim))
					return true;

				jada.remove(sona);
			}
		}

		return luhim.size() == 1000;
	}




	public static boolean onTuletis(String s1, String s2){
		int loendur = 0;
		for (int i = 0; i < 4; i++)
			if (s1.charAt(i) == s2.charAt(i))
				loendur++;

		return loendur == 3;
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


	public static String[] pikimLühimTuletus(String[] sonad){
		String[] tulemus = new String[0];
		HashMap<String, HashSet<String>> seosed = seosed(sonad);
		for (int i = 0; i < sonad.length; i++) {
			for (int j = i; j < sonad.length; j++) {
				String[] tuletus = luhim(seosed, sonad[i], sonad[j], tulemus);

				if (tuletus != null && tuletus.length > tulemus.length)
					tulemus = tuletus;
			}
		}

		return tulemus;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(pikimLühimTuletus(sõnad("nimi.txt"))));
	}
}