import java.io.*;
import java.util.*;

public class Solution {
	static int h;
	static int w;
	static int x;
	static int y;
	static int d;
	static char[] tank = {'^','v','<','>'};
	static char[][] board;
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	static boolean check(int x, int y) {
		return x >= 0 && x < h && y >= 0 && y < w;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			// 초기화
			board = new char[h][w];
			x = -1;
			y = -1;
			for(int i = 0; i < h; i++) {
				String s = br.readLine();
				for(int j = 0; j < w; j++) {
					board[i][j] = s.charAt(j);
					if(x != -1) continue;
					for(int c = 0; c < 4; c++) {
						if(board[i][j] == tank[c]) {
							x = i;
							y = j;
							d = c;
						}
					}
				}
			}
			
			
			int len = Integer.parseInt(br.readLine());
			String command = br.readLine();
			for(int l = 0; l < len; l++) {
				switch(command.charAt(l)) {
				case 'U':
					d = 0;
					move();
					break;
				case 'D':
					d = 1;
					move();
					break;
				case 'L':
					d = 2;
					move();
					break;
				case 'R':
					d = 3;
					move();
					break;
				case 'S':
					int dx = x + dir[d][0];
					int dy = y + dir[d][1];
					// 필드 나가기 전까지 포탄 직진
					while(check(dx,dy)) {
						// 벽돌 벽 만나면 파괴
						if(board[dx][dy] == '*') {
							board[dx][dy] = '.';
							break;
						}
						// 강철 벽 만나면 포탄만 파괴
						if(board[dx][dy] == '#') break;
						dx += dir[d][0];
						dy += dir[d][1];
					}
					break;
				}
			}

			StringBuilder sb = new StringBuilder("#" + test + " ");
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
			
			System.out.print(sb.toString());
		}
	}

	// 평지일 때 이동
	// 아니면 방향만 바꾸기
	static void move() {
		int dx = x + dir[d][0];
		int dy = y + dir[d][1];
		if(check(dx,dy) && board[dx][dy] == '.') {
			board[x][y] = '.';
			x = dx;
			y = dy;
		}
		board[x][y] = tank[d];
	}
}
