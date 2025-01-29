import java.io.*;
import java.util.Deque;
import java.util.ArrayDeque;

class Loc{
	int x;
	int y;
	int z;
	int time;
	
	Loc(int x, int y, int z, int time){
		this.x = x;
		this.y = y;
		this.z = z;
		this.time = time;
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int m = Integer.parseInt(s[0]);
		int n = Integer.parseInt(s[1]);
		int h = Integer.parseInt(s[2]);
		int[][][] box = new int[h][n][m];
		
		// 전체 토마토 수
		int total = 0;
		// 익은 토마토 위치 저장
		Deque<Loc> que = new ArrayDeque<>();
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < n; j++) {
				s = br.readLine().split(" ");
				for(int k = 0; k < m; k++) {
					int t = Integer.parseInt(s[k]);
					if(t >= 0) {
						if(t > 0) que.offer(new Loc(j,k,i,0));
						total++;
					}
					box[i][j][k] = t;
				}
			}
		}
		
		// 토마토가 익는 방향
		int[][] dir = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
		int time = 0;
		// 현재 익은 토마토 수
		int count = 0;
		while(!que.isEmpty()) {
			Loc loc = que.poll();
			time = Math.max(time, loc.time);
			count++;
			for(int[] d : dir) {
				int x = loc.x + d[0];
				int y = loc.y + d[1];
				int z = loc.z + d[2];
				if(check(x,y,z,n,m,h) && box[z][x][y] == 0) {
					que.offer(new Loc(x,y,z,loc.time+1));
					box[z][x][y] = 1;
				}
			}
		}
		
		System.out.println((total == count) ? time : -1);
	}
	
	// 토마토 범위 체크
	static boolean check(int x, int y, int z, int n, int m, int h) {
		return x >= 0 && x < n && y >= 0 && y < m && z >= 0 && z < h;
	}
}