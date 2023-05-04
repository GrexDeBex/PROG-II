import java.util.ArrayList;

public class Aeglane {



	public static String[] kõikTulemused(String s){
		StringBuilder sb = new StringBuilder(s);
		ArrayList<String> tulemused = new ArrayList<>();

		rek(tulemused, sb);



		return tulemused.toArray(new String[0]);
	}


	public static void rek(ArrayList<String> tulemused, StringBuilder sb){

		if (sb.length() == 0){
			if (!tulemused.contains(sb.toString()))
				tulemused.add(sb.toString());
			return;
		}


		boolean viimane = true;
		int algus = 0;
		int lopp = 0;
		char eelmine = sb.charAt(0);

		for (int i = 1; i < sb.length(); i++) {
			if (sb.charAt(i) != eelmine){
				if (algus != lopp){
					sb.delete(algus, lopp+1);
					rek(tulemused, sb);
					for (int j = algus-1; j < lopp; j++)
						sb.insert(algus, eelmine);
					viimane = false;
				}

				algus = i;
				eelmine = sb.charAt(i);
			}

			lopp = i;
		}

		if (algus != lopp){
			sb.delete(algus, lopp+1);
			rek(tulemused, sb);
			for (int j = algus-1; j < lopp; j++)
				sb.insert(algus, eelmine);
			viimane = false;
		}

		if (viimane && !tulemused.contains(sb.toString()))
			tulemused.add(sb.toString());

	}
}
