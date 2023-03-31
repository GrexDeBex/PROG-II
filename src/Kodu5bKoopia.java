import java.util.ArrayList;
import java.util.Arrays;

class Kodu5bKoopia {

	static int[][] summad(int n){
		ArrayList<int[]> T = new ArrayList<>();;

		for (int i = 2; i < 7 && n>0; i+=2) {
			if (n == i){
				T.add(new int[]{i});
				break;
			}

			for (int[] l : summad(n-i)) {
				if (l[0] != i){
					int[] a = Arrays.copyOf(l, l.length+1);
					System.arraycopy(l, 0, a, 1, l.length);
					a[0] = i;

					T.add(a);
				}
			}

		}

		return T.toArray(new int[0][]);
	}

	static String[] sonePoime(String[] a, String[] b){
		String[] T = C(new String[0], a, b, 1);

		return C(T, b, a, 2);
	}


	static String[] C(String[] T, String[] n, String[] m, int i){
		String[] N = new String[L(n)-1];
		System.arraycopy(n, 1, N, 0, L(N));

		if (L(N) == 0)
			return c(T, m, n);

		String[] P = (i == 1) ? sonePoime(N, m) : sonePoime(m, N);
		for (String l : P)
			T = c(T, l.split(" "), n);

		return T;
	}

	static String[] c(String[] T, String[] r, String[] n){
		String[] s = Arrays.copyOf(r, L(r)+1);
		System.arraycopy(r, 0, s, 1, L(r));
		s[0] = n[0];

		T = Arrays.copyOf(T, L(T)+1);
		T[L(T)-1] = String.join(" ", s);

		return T;
	}


	static int L (Object[] a){
		return a.length;
	}








	public static void main(String[] args) {
		String[] a = {"kas", "mina"};
		String[] b = {"olen", "siin"};
		System.out.println(Arrays.toString(sonePoime(a, b)));
		System.out.println("[kas mina olen siin, kas olen mina siin, kas olen siin mina, olen kas mina siin, olen kas siin mina, olen siin kas mina]");

		System.out.println(Arrays.deepToString(summad(8)));
	}

}
