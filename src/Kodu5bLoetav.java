import java.util.ArrayList;
import java.util.Arrays;

class Kodu5bLoetav {

	int[][] summad(int n){
		ArrayList<ArrayList<Integer>> tulemused = new ArrayList<>();
		ArrayList<Integer> variant = new ArrayList<>();
		summaRek(n, tulemused, variant);

		int[][] massiivid = new int[tulemused.size()][];

		for (int i = 0; i < tulemused.size(); i++) {
			ArrayList<Integer> tulemus = tulemused.get(i);
			int[] massiiv = new int[tulemus.size()];

			for (int j = 0; j < tulemus.size(); j++) {
				massiiv[j] = tulemus.get(j);
			}
			massiivid[i] = massiiv;
		}

		return massiivid;

	}

	void summaRek(int n, ArrayList<ArrayList<Integer>> tulemused, ArrayList<Integer> variant){
		for (int i = 2; i < 7; i+=2) {
			if (variant.size() > 0)
				if (variant.get(variant.size()-1) == i)
					continue;


			if (n-i == 0){
				variant.add(i);
				tulemused.add(new ArrayList<>(variant));
				variant.remove(variant.size()-1);
				break;
			}else if (n-i > 0){
				variant.add(i);
				summaRek(n-i, tulemused, variant);
				variant.remove(variant.size()-1);
			}
		}


	}

	static String[] sonePoime(String[] a, String[] b){
		ArrayList<ArrayList<String>> tulemused = new ArrayList<>();
		ArrayList<String> variant = new ArrayList<>();

		sonePoimeRek(a, b, 0, 0, tulemused, variant);

		String[] laused = new String[tulemused.size()];

		for (int i = 0; i < tulemused.size(); i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(tulemused.get(i).get(0));

			for (int j = 1; j < tulemused.get(i).size(); j++) {
				sb.append(" ");
				sb.append(tulemused.get(i).get(j));
			}
			laused[i] = sb.toString();
		}

		return laused;

	}

	static void sonePoimeRek(String[] a, String[] b, int i1, int i2, ArrayList<ArrayList<String>> tulemused, ArrayList<String> variant){
		if (i1 < a.length){
			variant.add(a[i1]);
			if (i1+1 == a.length && i2 == b.length){
				tulemused.add(new ArrayList<>(variant));
			}else {
				sonePoimeRek(a, b, i1+1, i2, tulemused, variant);
			}

			variant.remove(variant.size()-1);
		}

		if (i2 < b.length){
			variant.add(b[i2]);
			i2++;
			if (i1 == a.length && i2 == b.length){
				tulemused.add(new ArrayList<>(variant));
			}else {
				sonePoimeRek(a, b, i1, i2, tulemused, variant);
			}
			variant.remove(variant.size()-1);
		}
	}

	public static void main(String[] args) {
		String[] a = {"kas", "mina"};
		String[] b = {"olen", "siin"};
		System.out.println(Arrays.toString(sonePoime(a, b)));
		System.out.println("[kas mina olen siin, kas olen mina siin, kas olen siin mina, olen kas mina siin, olen kas siin mina, olen siin kas mina]");
    }//peameetod


}//Kodu5b
