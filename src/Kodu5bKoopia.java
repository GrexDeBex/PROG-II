import java.util.ArrayList;
import java.util.Arrays;

class Kodu5bKoopia {

	static int[][] summad(int n){
		ArrayList<ArrayList> T = new ArrayList<>();
		S(n, T, new ArrayList());

		int[][] M = new int[T.size()][];

		for (int i = 0; i < T.size(); i++) {
			ArrayList<Integer> t = T.get(i);
			int[] m = new int[t.size()];

			for (int j = 0; j < t.size(); j++)
				m[j] = t.get(j);

			M[i] = m;
		}

		return M;

	}

	static void S(int n, ArrayList<ArrayList> T, ArrayList<Integer> v){

		for (int i = 2; i < 7; i+=2) {
			if (v.size() > 0)
				if (v.get(v.size()-1) == i)
					continue;

			v.add(i);
			if (n-i == 0)
				T.add(new ArrayList(v));
			if (n-i > 0)
				S(n-i, T, v);

			v.remove(v.size()-1);
		}


	}

	static String[] sonePoime(String[] a, String[] b){
		ArrayList<ArrayList> T = new ArrayList<>();

		P(a, b, 0, 0, T, new ArrayList());

		String[] L = new String[T.size()];

		for (int i = 0; i < T.size(); i++) {
			String s = "";
			s += T.get(i).get(0);

			for (int j = 1; j < T.get(i).size(); j++) {

				s += " " + T.get(i).get(j);
			}
			L[i] = s;
		}

		return L;

	}

	static void P(String[] a, String[] b, int i, int j, ArrayList<ArrayList> T, ArrayList v){
		if (i < a.length){
			v.add(a[i]);
			if (i+1 == a.length && j == b.length)
				T.add(new ArrayList<>(v));
			else
				P(a, b, i+1, j, T, v);

			v.remove(v.size()-1);
		}

		if (j < b.length){
			v.add(b[j]);

			if (i == a.length && j+1 == b.length)
				T.add(new ArrayList(v));
			else
				P(a, b, i, j+1, T, v);

			v.remove(v.size()-1);
		}
	}














	public static void main(String[] args) {
		String[] a = {"kas", "mina"};
		String[] b = {"olen", "siin"};
		System.out.println(Arrays.toString(sonePoime(a, b)));
		System.out.println("[kas mina olen siin, kas olen mina siin, kas olen siin mina, olen kas mina siin, olen kas siin mina, olen siin kas mina]");
    }

}
