import java.io.*;
import java.util.*;

class Fish{
	int x, y, dir;
	
	Fish(int x, int y, int dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	@Override
	public String toString() {
		return x + " " + y + " " + dir;
	}
}

public class Main {
	static int n = 4, max;
	static int[][] dir = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	static Fish[] fish;
	static int[][] map = new int[n][n];
	
	static boolean check(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
    	// 물고기 저장
		fish = new Fish[17];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				Fish f = new Fish(i,j,Integer.parseInt(st.nextToken())-1);
				fish[map[i][j]] = f;
			}
		}
		
		// 먹은 물고기 번호 합
		max = map[0][0];
		// 0은 상어 자리
		fish[0] = fish[map[0][0]];
		fish[map[0][0]] = null;
		map[0][0] = 0;
		dfs(max, 1);
		System.out.println(max);
    }
    
    static void dfs(int nyam, int p) {
    	max = Math.max(max, nyam);
    	// 현재 상태 저장
    	int[][] storeMap = new int[n][n];
    	for(int i = 0; i < n; i++) {
    		storeMap[i] = map[i].clone();
    	}
    	Fish[] storeFish = new Fish[17];
    	for(int i = 0; i < 17; i++) {
    		if(fish[i] != null) {
    			storeFish[i] = new Fish(fish[i].x, fish[i].y, fish[i].dir);
    		}
    	}
    	
    	// 1번 물고기부터 이동
    	for(int i = 1; i <= 16; i++) {
    		if(fish[i] == null) continue;
    		int x = fish[i].x;
    		int y = fish[i].y;
    		int d = fish[i].dir;
    		int move = 0;
    		while(move < 8) {
    			int dx = x + dir[d][0];
    			int dy = y + dir[d][1];
    			if(check(dx,dy) && map[dx][dy] != 0) {
    				fish[i].dir = d;
    				// 빈자리일 때
    				if(map[dx][dy] == -1) {
    					map[x][y] = -1;
    					map[dx][dy] = i;
    					fish[i].x = dx;
    					fish[i].y = dy;
    					break;
    				}
    				// 바뀔 물고기
    				int num = map[dx][dy];
    				Fish f = new Fish(fish[num].x, fish[num].y, fish[i].dir);
    				
    				fish[num].x = fish[i].x;
    				fish[num].y = fish[i].y;
    				fish[i] = f;
    				map[x][y] = num;
    				map[dx][dy] = i;
    				break;
    			}
    			move++;
    			d = (d+1) % 8;
    		}
    	}
    	
    	// 상어 이동
    	int x = fish[0].x;
    	int y = fish[0].y;
    	int d = fish[0].dir;
    	
    	int dx = x + dir[d][0];
    	int dy = y + dir[d][1];
    	while(check(dx, dy)) {
    		int num = map[dx][dy];
    		// 물고기가 있는 곳
    		if(num > 0) {
    			Fish shark = new Fish(fish[0].x, fish[0].y, fish[0].dir);
    			Fish pray = new Fish(fish[num].x, fish[num].y, fish[num].dir);
    			fish[0] = fish[num];
    			fish[num] = null;
    			map[dx][dy] = 0;
    			map[x][y] = -1;
    			dfs(nyam + num, p+1);
    			// 복구
    			fish[0] = shark;
    			fish[num] = pray;
    			map[dx][dy] = num;
    			map[x][y] = 0;
    		}
    		dx += dir[d][0];
    		dy += dir[d][1];
    	}
    	// 복구
    	for(int i = 0; i < n; i++) {
    		map[i] = storeMap[i].clone();
    	}
    	for(int i = 0; i < 17; i++) {
    		if(storeFish[i] != null) {
    			fish[i] = new Fish(storeFish[i].x, storeFish[i].y, storeFish[i].dir);
    		}
    		else fish[i] = null;
    	}
    }
    
    static void print() {
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			if(map[i][j] != -1) System.out.print(map[i][j] + " " + fish[map[i][j]].dir + " | ");
    			else System.out.print("-1   ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    }
}