import java.io.*;
import java.util.*;

public class Solution {
	// 위, 오른쪽, 아래, 왼쪽
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	// 블록마다 이동한 방향에 따른 결과 방향과 이동 좌표
	static int[][] block = {{2,3,1,0},	// 블록 1
			{1,3,0,2},	// 블록 2
			{3,2,0,1},		// 블록 3
			{2,0,3,1}};		// 블록 4
	
	static boolean check(int x, int y, int n) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int test = 1; test <= T; test++) {
			int n = Integer.parseInt(br.readLine().trim());
			int[][] board = new int[n][n];
			int[][][] wormhole = new int[5][2][2];
			boolean[] isWh = new boolean[5];
			for(int i = 0; i < n; i++) {
				String[] s = br.readLine().trim().split(" ");
				for(int j = 0; j < n; j++) {
					int a = Integer.parseInt(s[j]);
					board[i][j] = a;
					if(a >= 6) {
						if(isWh[a-6]) {
							wormhole[a-6][1][0] = i;
							wormhole[a-6][1][1] = j;
						}
						else {
							wormhole[a-6][0][0] = i;
							wormhole[a-6][0][1] = j;
							isWh[a-6] = true;
						}
					}
				}
			}
			
			int count = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(board[i][j] != 0) continue;
					for(int k = 0; k < 4; k++) {
						count = Math.max(count, calc(i,j,k,n,board,wormhole));
					}
				}
			}
			
			System.out.println("#" + test + " " + count);
		}
	}
	
	static int calc(int i, int j, int k, int n, int[][] board, int[][][] wormhole) {
		int count = 0;
		int x = i;
		int y = j;
		int d = k;
		x += dir[d][0];
		y += dir[d][1];
		int b;
		while(true) {
			if(check(x, y, n)) {
				b = board[x][y];
				switch(b) {
				case 0:
					x += dir[d][0];
					y += dir[d][1];
					break;
				case 1:
				case 2:
				case 3:
				case 4:
					count++;
					x += dir[block[b-1][d]][0];
					y += dir[block[b-1][d]][1];
					d = block[b-1][d];
					break;
				case 5:
					count++;
					x -= dir[d][0];
					y -= dir[d][1];
					d = (d+2) % 4;
					break;
				case -1:
					return count;
				default:
					if(wormhole[b-6][0][0] == x && wormhole[b-6][0][1] == y) {
						x = wormhole[b-6][1][0];
						y = wormhole[b-6][1][1];
						x += dir[d][0];
						y += dir[d][1];
					}
					else {
						x = wormhole[b-6][0][0];
						y = wormhole[b-6][0][1];
						x += dir[d][0];
						y += dir[d][1];
					}
				}
			}
			else {
				count++;
				x -= dir[d][0];
				y -= dir[d][1];
				d = (d+2) % 4;
			}
			if(x == i && y == j) {
				return count;
			}
		}
	}
}