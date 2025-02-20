import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			String[] s = br.readLine().split(" ");
			int n = Integer.parseInt(s[0]);
			int m = Integer.parseInt(s[1]);
			int[][] board = new int[n+1][n+1];
			for(int i = 1; i <= n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= n; j++) {
					int a = Integer.parseInt(st.nextToken());
					board[i][j] = a + board[i-1][j] + board[i][j-1] - board[i-1][j-1];
				}
			}
			
			int max = 0;
			for(int i = m; i <= n; i++) {
				for(int j = m; j <= n; j++) {
					max = Math.max(max, board[i][j] - board[i-m][j] - board[i][j-m] + board[i-m][j-m]);
				}
			}
			
			System.out.println("#" + test + " " + max);
		}
	}
}
