import java.io.*;
import java.util.*;

public class Solution {
	static int n;
	static int[][] board;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			board = new int[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 병합 전에 먼저 해당 방향으로 밀기
			// 합치기
			// 다시 해당 방향으로 밀기
			switch(c) {
			case 'u':
				merge(true, true);
				break;
			case 'd':
				merge(true, false);
				break;
			case 'l':
				merge(false, true);
				break;
			case 'r':
				merge(false, false);
				break;
			}
			
			StringBuilder sb = new StringBuilder("#" + test + "\n");
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					sb.append(board[i][j] + " ");
				}
				sb.append("\n");
			}
			System.out.print(sb.toString());
		}
	}

	// r이 true고 c가 true면 up
	// r이 true고 c가 false면 down
	// r이 false고 c가 true면 left
	// r이 false고 c가 false면 right
	static void merge(boolean r, boolean c) {
		push(r,c);
		for(int i = 0; i < n; i++) {
			if(c) {
				for(int j = 1; j < n; j++) {
					if(board[r ? j : i][r ? i : j] != 0 && board[r ? j-1 : i][r ? i : j-1] == board[r ? j : i][r ? i : j]) {
						board[r ? j-1 : i][r ? i : j-1] *= 2;
						board[r ? j : i][r ? i : j] = 0;
					}
				}
			}
			else {
				for(int j = n-2; j >= 0; j--) {
					if(board[r ? j : i][r ? i : j] != 0 && board[r ? j+1 : i][r ? i : j+1] == board[r ? j : i][r ? i : j]) {
						board[r ? j+1 : i][r ? i : j+1] *= 2;
						board[r ? j : i][r ? i : j] = 0;
					}
				}
			}
		}
		push(r,c);
	}
	
	// 보드 밀기 작업
	static void push(boolean r, boolean c) {
		for(int i = 0; i < n; i++) {
			int index;
			if(c) {
				index = 0;
				for(int j = 0; j < n; j++) {
					if(board[r ? j : i][r ? i : j] != 0) {
						if(index != j) {
							board[r ? index : i][r ? i : index] = board[r ? j : i][r ? i : j];
							board[r ? j : i][r ? i : j] = 0;
						}
						index++;
					}
				}
			}
			else {
				index = n-1;
				for(int j = n-1; j >= 0; j--) {
					if(board[r ? j : i][r ? i : j] != 0) {
						if(index != j) {
							board[r ? index : i][r ? i : index] = board[r ? j : i][r ? i : j];
							board[r ? j : i][r ? i : j] = 0;
						}
						index--;
					}
				}
			}
		}
	}
}
