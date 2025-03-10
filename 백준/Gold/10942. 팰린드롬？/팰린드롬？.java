import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] arr;
	// 팰린드롬 저장
	static boolean[][] pal;
	
	static boolean check(int s, int e) {
		return s >= 0 && e < n;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		pal = new boolean[n][n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			// 길이가 1이라면 팰린드롬
			pal[i][i] = true;
		}
		
		// 팰린드롬 먼저 저장
		plnCheck();
		
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			sb.append(pal[s-1][e-1] ? 1 : 0);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void plnCheck() {
		// 길이가 2인 팰린드롬
		for(int i = 0; i < n-1; i++) {
			pal[i][i+1] = arr[i] == arr[i+1];
		}
		
		// 길이가 3 이상인 팰린드롬
		for(int l = 2; l < n; l++) {
			for(int i = 0; i < n-l; i++) {
				if((arr[i] == arr[i+l]) && pal[i+1][i+l-1]) {
					pal[i][i+l] = true;
				}
			}
		}
	}
}
