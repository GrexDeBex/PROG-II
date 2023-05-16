import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Kodu10b {
	public static String[] lühimTuletus(String[] sonad, String ls, String ss) {
		ArrayList<String> luhim = new ArrayList<>(Collections.nCopies(1000, "0"));
		ArrayList<String> jada = new ArrayList<>();

		jada.add(ls);
		rek(sonad, ls, ss, luhim, jada);

		return luhim.toArray(new String[0]);
	}

	public static void rek(String[] sonad, String ls, String ss,
				ArrayList<String> luhim, ArrayList<String> jada){

		if (luhim.size() == jada.size())
			return;

		for (String sona : sonad) {
			if (onTuletis(ls, sona) && sona.equals(ss)) {
				jada.add(ss);
				if (luhim.size() > jada.size()){
					luhim.clear();
					luhim.addAll(jada);
				}
				jada.remove(ss);
				break;
			}

			if (onTuletis(ls, sona) && !jada.contains(sona)){
				jada.add(sona);
				rek(sonad, sona, ss, luhim, jada);
				jada.remove(sona);
			}
		}
	}



	public static boolean onTuletis(String s1, String s2){
		int loendur = 0;
		for (int i = 0; i < 4; i++)
			if (s1.charAt(i) == s2.charAt(i))
				loendur++;

		return loendur == 3;
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

	//BOONUSÜLESANNE
	public static String[] pikimLühimTuletus(String[] sõnad){
		throw new UnsupportedOperationException("Kirjuta oma lahendus selle rea asemele. Juhul kui ülesanne jääb lahendamata jäta see rida alles.");
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(lühimTuletus(sõnad("nimi.txt"), "kass", "kakk")));
	}
}