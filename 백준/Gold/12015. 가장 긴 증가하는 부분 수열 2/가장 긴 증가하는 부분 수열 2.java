import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int a = Integer.parseInt(st.nextToken());
			if(list.isEmpty()) list.add(a);
			else {
				int high = list.size() - 1;
				int low = 0;
				int mid;
				while(high >= low) {
					mid = (high+low) / 2;
					if(a > list.get(mid)) {
						low = mid + 1;
					}
					else {
						high = mid - 1;
					}
				}
				if(low >= 0) {
					if(low == list.size()) list.add(a);
					else list.set(low, a);
				}
			}
		}
		System.out.println(list.size());
	}
}