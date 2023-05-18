import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Kodu10bUL1 {
	public static String[] lühimTuletus(String[] sonad, String ls, String ss) {
		if (ls.equals(ss))
			return new String[]{ls};

		boolean puudub = true;
		for (String sona : sonad)
			if (onTuletis(sona, ss))
				puudub = false;

		if (puudub)
			return null;

		ArrayList<String> luhim = new ArrayList<>(Collections.nCopies(1000, "0"));
		ArrayList<String> jada = new ArrayList<>();


		jada.add(ls);
		rek(sonad, ls, ss, luhim, jada);

		if (luhim.get(0).equals("0"))
			return null;

		return luhim.toArray(new String[0]);
	}

	public static void rek(String[] sonad, String ls, String ss,
						   ArrayList<String> luhim, ArrayList<String> jada){

		if (luhim.size() == jada.size())
			return;

		for (String sona : sonad) {
			boolean tuletis = onTuletis(ls, sona);

			if (tuletis && sona.equals(ss)) {
				jada.add(ss);
				if (luhim.size() > jada.size()){
					luhim.clear();
					luhim.addAll(jada);
				}
				jada.remove(ss);
				break;
			}

			if (tuletis && !jada.contains(sona) && heaValik(ls, sona, ss)){
				jada.add(sona);
				rek(sonad, sona, ss, luhim, jada);
				jada.remove(sona);
			}
		}
	}

	public static boolean heaValik(String ls, String sona, String ss){
		int loendur1 = 0;
		for (int i = 0; i < 4; i++)
			if (ls.charAt(i) == ss.charAt(i))
				loendur1++;

		int loendur2 = 0;
		for (int i = 0; i < 4; i++)
			if (sona.charAt(i) == ss.charAt(i))
				loendur2++;

		return loendur1 <= loendur2;
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
}
