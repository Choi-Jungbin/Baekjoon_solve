import java.io.*;
import java.util.*;

class Node{
	// 세로, 가로
	boolean dir;
	int x;
	int y;
	int len;
	
	Node(boolean dir, int x, int y, int len){
		this.dir = dir;
		this.x = x;
		this.y = y;
		this.len = len;
	}
}

public class Main {
	static int n;
	// 가로, 세로 각각 방문 했는지 확인
	static boolean[][][] visited;
	// true면 이동 가능한 곳
	static boolean[][] map;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
	
	// 위, 아래, 오른쪽, 왼쪽 이동
	static boolean move(boolean dir, int x, int y) {
		if(!dir) {
			if(x-1 >= 0 && x+1 < n && y >= 0 && y < n) {
				if(map[x-1][y] && map[x][y] && map[x+1][y]) return true;
			}
		}
		else {
			if(x >= 0 && x < n && y-1 >= 0 && y+1 < n) {
				if(map[x][y-1] && map[x][y] && map[x][y+1]) return true;
			}
		}
		return false;
	}
	
	// 회전
	static boolean turn(int x, int y) {
		if(x <= 0 || x >= n-1 || y <= 0 || y >= n-1) return false;
		for(int[] d : dir) {
			int dx = x + d[0];
			int dy = y + d[1];
			if(!map[dx][dy]) return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n][n][2];
		
		map = new boolean[n][n];
		int x = 0;
		int y = 0;
		int checkb = 0;
		boolean b = false;
		int ex = 0;
		int ey = 0;
		int checke = 0;
		boolean e = false;
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < n; j++) {
				// 시작점 탐색
				if(s.charAt(j) == 'B') {
					x += i;
					y += j;
					if(checkb == 0) {
						checkb += i;
					}
					else if(checkb > 0) {
						b = (checkb == i);
					}
					map[i][j] = true;
					continue;
				}
				// 도착점 탐색
				if(s.charAt(j) == 'E') {
					ex += i;
					ey += j;
					if(checke == 0) {
						checke += i;
					}
					else if(checke > 0) {
						e = (checke == i);
					}
					map[i][j] = true;
					continue;
				}
				map[i][j] = (s.charAt(j) == '0');
			}
		}
		x /= 3;
		y /= 3;
		ex /= 3;
		ey /= 3;
		
		Deque<Node> que = new ArrayDeque<>();
		que.offer(new Node(b, x, y, 0));
		while(!que.isEmpty()) {
			Node node = que.poll();
			if(visited[node.x][node.y][node.dir ? 0 : 1]) continue;
			visited[node.x][node.y][node.dir ? 0 : 1] = true;
			// 위, 아래, 오른쪽, 왼쪽 탐색
			for(int i = 0; i < 4; i++) {
				int dx = node.x + dir[i][0];
				int dy = node.y + dir[i][1];
				if(move(node.dir,dx,dy)) {
					if(dx == ex && dy == ey && (node.dir == e)) {
						System.out.println(node.len + 1);
						return;
					}
					if(!visited[dx][dy][node.dir ? 0 : 1]) {
						que.offer(new Node(node.dir,dx,dy,node.len+1));
					}
				}
			}
			// 회전 탐색
			if(turn(node.x,node.y)) {
				if(node.x == ex && node.y == ey && (!node.dir != e)) {
					System.out.println(node.len + 1);
					return;
				}
				if(!visited[node.x][node.y][node.dir ? 1 : 0]) {
					que.offer(new Node(!node.dir,node.x,node.y,node.len+1));
				}
			}
		}
		System.out.println(0);
	}
}
