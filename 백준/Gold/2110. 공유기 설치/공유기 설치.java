import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] home = new int[n];
		for(int i = 0; i < n; i++) {
			home[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(home);
		
		long min = 1;
		long max = home[n-1] - home[0];
		long mid = (max+min) / 2;
		while(min <= max) {
			mid = (max+min) / 2;
			int loc = home[0];
			int cnt = 1;
			for(int i = 0; i < n; i++) {
				if(home[i] - loc >= mid) {
					cnt++;
					loc = home[i];
				}
			}
			
			if(cnt < c) {
				max = mid - 1;
			}
			else {
				min = mid + 1;
			}
		}
		System.out.println(max);
	}

}
