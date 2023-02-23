public class Kodu1b {
	static void ruut(int n) {
		for (int i = 0; i < n-1; i++) {
			System.out.print("# ");
		}
		System.out.print("#");
		for (int i = 0; i < n-2; i++) {
			System.out.println();
			System.out.print("# ");
			for (int j = 0; j < n-2; j++) {
				System.out.print("  ");
			}
			System.out.print("#");
		}
		if (n > 1){
			System.out.println();
			for (int i = 0; i < n-1; i++) {
				System.out.print("# ");
			}
			System.out.print("#");
		}
	}

	static void romb(int n) {
		for (int i = 0; i < n-1; i++) {
			System.out.print(" ");
		}
		System.out.print("#");
		for (int i = 0; i < n-1; i++) {
			System.out.println();
			for (int j = n-2-i; j > 0; j--) {
				System.out.print(" ");
			}
			System.out.print("#");
			for (int j = 0; j < i*2+1; j++) {
				System.out.print(" ");
			}
			System.out.print("#");
		}
		for (int i = n-2; i > 0; i--) {
			System.out.println();
			for (int j = n-1-i; j > 0; j--) {
				System.out.print(" ");
			}
			System.out.print("#");
			for (int j = 0; j < i*2-1; j++) {
				System.out.print(" ");
			}
			System.out.print("#");
		}
		if (n > 1){
			System.out.println();
			for (int i = 0; i < n-1; i++) {
				System.out.print(" ");
			}
			System.out.print("#");
		}
	}

	static void telk(int n) {
		for (int i = 0; i < 2*n; i++) {
			System.out.print(" ");
		}
		System.out.print("#");

		for (int i = 0; i < n-1; i++) {
			System.out.println();
			for (int j = 0; j < 2*(n-1-i); j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < i+2; j++) {
				System.out.print("#");
			}
			for (int j = 0; j < i*2+1; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < i+2; j++) {
				System.out.print("#");
			}

		}
		System.out.println();
		for (int i = 0; i < n*4+1; i++) {
			System.out.print("=");
		}
	}

	static void spiraal(int n) {
		for (int i = 0; i < n-2; i++) {
			System.out.print("# ");
		}
		System.out.print("#");
		System.out.println();
		for (int i = 0; i < n-2; i++) {
			System.out.print("  ");
		}
		System.out.print("#");

		for (int i = 0; i < (n-2)/2 + 1; i++) {
			System.out.println();
			System.out.print("# ");
			if (i % 2 == 0) {
				for (int j = 0; j < i/2; j++) {
					System.out.print("  # ");
				}
				for (int j = 0; j < n-5-2*i; j++) {
					System.out.print("# ");
				}
				System.out.print("#");
				if (i != (n-2)/2){
					for (int j = 0; j < i/2+1; j++) {
						System.out.print("   #");
					}
				} else {
					for (int j = 0; j < i/2; j++) {
						System.out.print("   #");
					}
				}
			} else {
				for (int j = 0; j < i/2; j++) {
					System.out.print("  # ");
				}
				for (int j = 0; j < n-3-2*i; j++) {
					System.out.print("  ");
				}
				System.out.print("#");
				for (int j = 0; j < i/2+1; j++) {
					System.out.print("   #");
				}
			}
		}
//		for (int i = 0; i < n-2; i++) {
//			System.out.print("# ");
//		}
//		System.out.print("#");
	}

	public static void main(String[] args) {
		spiraal(21);
	}
}