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
			int[][] board = new int[n][n];
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 열에 대한 슬라이딩 윈도우
			int[][] window = new int[n][n-m+1];
			for(int i = 0; i < n; i++) {
				int a = 0;
				for(int j = 0; j < m; j++) {
					a += board[i][j];
				}
				window[i][0] = a;
				for(int j = 1; j <= n-m; j++) {
					a += board[i][j+m-1] - board[i][j-1];
					window[i][j] = a;
				}
			}
			
			// 행에 대한 슬라이딩 윈도우
			int max = 0;
			for(int j = 0; j <= n-m; j++) {
				int a = 0;
				for(int i = 0; i < m; i++) {
					a += window[i][j];
				}
				max = Math.max(max, a);
				for(int i = 1; i <= n-m; i++) {
					a += window[i+m-1][j] - window[i-1][j];
					max = Math.max(max, a);
				}
			}
			
			System.out.println("#" + test + " " + max);
		}
	}
}
