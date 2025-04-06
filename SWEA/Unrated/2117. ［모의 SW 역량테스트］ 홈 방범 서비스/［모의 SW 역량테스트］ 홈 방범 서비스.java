import java.io.*;
import java.util.*;

public class Solution {
	static int n, m, home;
	static int max;
	static boolean[][] map;
	
	static boolean check(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			map = new boolean[n][n];
			home = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = st.nextToken().equals("1");
					if(map[i][j]) home++;
				}
			}
			
			max = 0;
			for(int i = 0; i < n; i++) {
				// 모든 집을 다 포함할 때
				if(max == home) break;
				for(int j = 0; j < n; j++) {
					if(max == home) break;
					for(int k = 1; k < n+2; k++) {
						if(max == home) break;
						calc(i,j,k);
					}
				}
			}
			
			System.out.println("#" + test + " " + max);
		}
	}
	
	static void calc(int x, int y, int d) {
		int k = d*d + (d-1)*(d-1);
		int count = k;
		int h = 0;
		// 마름모 그리기
		for(int i = -d+1; i <= d-1; i++) {
			// 남은 곳에 다 집이 있어도 비용 감당이 안되면 패스
			if(k-count*m > 0) break;
			// 모든 집을 이미 돌았을 때
			if(h == home) break;
			for(int j = -d+Math.abs(i)+1; j <= d-Math.abs(i)-1; j++) {
				if(k-count*m > 0) break;
				if(h == home) break;
				if(check(x+i,y+j) && map[x+i][y+j]) {
					k -= m;
					h++;
				}
				count--;
			}
		}
		if(k <= 0) max = Math.max(max, h);
	}
}