import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c, n;
		StringTokenizer st = new StringTokenizer(br.readLine());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		int[] cnt = new int[c+101];
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cnt[b] == 0) {
				cnt[b] = a;
			}
			else cnt[b] = Math.min(a, cnt[b]);
			if(b >= c) min = Math.min(min, a);
			
			for(int j = b+1; j < c+b; j++) {
				if(cnt[j-b] != 0) {
					if(cnt[j] == 0) cnt[j] = cnt[j-b] + a;
					else cnt[j] = Math.min(cnt[j-b]+a, cnt[j]);
					if(j >= c) min = Math.min(min, cnt[j]);
				}
			}
		}
		
		System.out.println(min);
	}
}