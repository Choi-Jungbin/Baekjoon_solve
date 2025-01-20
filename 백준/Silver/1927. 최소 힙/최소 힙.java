import java.io.*;

class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int e = 0;
		int[] heap = new int[100000];
		int r = Integer.parseInt(br.readLine());
		for(int i = 0; i < r; i++) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) {
				if(e == 0) System.out.println(0);
				else {
					System.out.println(heap[0]);
					heap[0] = heap[e-1];
					heap[e-1] = 0;
					e--;
					int p = 1;
					while(2*p <= e) {
						if(2*p == e) {
							if(heap[2*p-1] < heap[p-1]) {
								int swap = heap[p-1];
								heap[p-1] = heap[2*p-1];
								heap[2*p-1] = swap;
							}
							break;
						}
						if(heap[2*p-1] > heap[2*p]) {
							if(heap[2*p] < heap[p-1]) {
								int swap = heap[p-1];
								heap[p-1] = heap[2*p];
								heap[2*p] = swap;
								p = 2*p + 1;
							}
							else break;
						}
						else {
							if(heap[2*p-1] < heap[p-1]) {
								int swap = heap[p-1];
								heap[p-1] = heap[2*p-1];
								heap[2*p-1] = swap;
								p *= 2;
							}
							else break;
						}
					}
//					System.out.println("sort");
//					for(int j = 0; j < 5; j++) {
//						System.out.println("heap: " + heap[j]);
//					}
				}
			}
			else {
				int p = ++e;
				heap[p-1] = n;
				while(p > 1) {
					if(heap[p-1] < heap[p/2-1]) {
						int swap = heap[p-1];
						heap[p-1] = heap[p/2-1];
						heap[p/2-1] = swap;
						p /= 2;
					}
					else break;
				}
//				System.out.println("intput: " + n);
//				for(int j = 0; j < 5; j++) {
//					System.out.println("heap: " + heap[j]);
//				}
			}
		}
	}
}