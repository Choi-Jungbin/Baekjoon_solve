import java.io.*;
import java.util.*;

public class Solution {
	static int n;
	static int max;
	static int[][] map;
	static int[] start = new int[2];
	static boolean[] visit = new boolean[101];
	static int[][] dir = {{1,1},{1,-1},{-1,-1},{-1,1}};
	
	static boolean check(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 시작 위치 먼저 지정하기
			max = -1;
			for(int i = 0; i < n-2; i++) {
				for(int j = 1; j < n-1; j++) {
					start[0] = i;
					start[1] = j;
					move(i,j,0,0);
				}
			}
			System.out.println("#" + test + " " + max);
		}
	}
	
	// 시계방향으로 탐색
	static void move(int x, int y, int rot, int size) {
		// 돌다가 처음 위치로 오면 길이 갱신
		if(rot == 3 && start[0] == x && start[1] == y) {
			max = Math.max(max, size);
			return;
		}
		// 같은 종류를 만나면 나가기
		if(visit[map[x][y]]) return;
		visit[map[x][y]] = true;
		int dx = x + dir[rot][0];
		int dy = y + dir[rot][1];
		// 벽에 부딪히지 않으면 직진
		if(check(dx,dy)) move(dx,dy,rot, size+1);
		// 꺾어도 보기
		if(rot != 3) {
			rot++;
			dx = x + dir[rot][0];
			dy = y + dir[rot][1];
			if(check(dx,dy)) move(dx,dy,rot, size+1);
		}
		visit[map[x][y]] = false;
	}
}
