import java.io.*;
import java.util.*;

public class Solution {
	static int n;
	static boolean[] visit;
	static List<List<Integer>> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			String[] s = br.readLine().split(" ");
			n = Integer.parseInt(s[0]);
			int m = Integer.parseInt(s[1]);
			list = new ArrayList<>(n);
			for(int i = 0; i < n; i++) {
				list.add(new ArrayList<Integer>());
			}
			
			for(int i = 0; i < m; i++) {
				s = br.readLine().split(" ");
				int a = Integer.parseInt(s[0]) - 1;
				int b = Integer.parseInt(s[1]) - 1;
				list.get(a).add(b);
				list.get(b).add(a);
			}
			
			visit = new boolean[n];
			System.out.println("#" + test + " " + calc(0));
		}
	}
	
	static int calc(int p) {
		if(p == n) return 1;
		int result = 0;
		boolean check = true;
		for(int a : list.get(p)) {
			if(visit[a]) {
				check = false;
				break;
			}
		}
		if(check) {
			visit[p] = true;
			result += calc(p+1);
			visit[p] = false;
		}
		result += calc(p+1);
		
		return result;
	}
}
