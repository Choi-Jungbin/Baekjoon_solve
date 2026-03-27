import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int p = 0;
			int[] array = new int[n];
			for(int i = 0; i < n; i++) {
				int a = Integer.parseInt(st.nextToken());
				if(p == 0) {
					array[p++] = a;
					continue;
				}
				int high = p;
				int low = 0;
				int mid = 0;
				while(low < high) {
					mid = (low+high) / 2;
					if(a <= array[mid]) high = mid;
					else low = mid + 1;
				}
				array[low] = a;
				if(low == p) p++;
				if(p >= k) break;
			}
			sb.append("Case #" + test + "\n" + ((p>=k) ? 1 : 0) + "\n");
		}
		System.out.println(sb.toString());
	}
}