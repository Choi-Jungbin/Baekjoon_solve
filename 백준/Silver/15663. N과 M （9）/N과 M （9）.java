import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.LinkedHashSet;

public class Main {
	static int n;
	static int[] arr;
	static Set<String> set = new LinkedHashSet<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = sc.nextInt();
		arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		boolean[] visit = new boolean[n];
		StringBuilder sb = new StringBuilder();
		dfs(visit, m, sb);
		for(String s : set) {
			System.out.println(s);
		}
	}
	
	static void dfs(boolean[] visit, int r, StringBuilder sb) {
		if(r == 0) {
			set.add(sb.toString());
		}
		for(int i = 0; i < n; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			int len = sb.length();
			sb.append(arr[i] + " ");
			dfs(visit, r-1, sb);
			visit[i] = false;
			sb.setLength(len);
		}
	}
}