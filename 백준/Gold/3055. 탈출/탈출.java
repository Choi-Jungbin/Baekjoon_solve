import java.io.*;
import java.util.*;

class Node{
	int x;
	int y;
	int t;
	
	Node(int x, int y, int t){
		this.x = x;
		this.y = y;
		this.t = t;
	}
}

public class Main {
	static int r, c;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	static boolean check(int x, int y) {
		return x >= 0 && x < r && y >= 0 && y < c;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[r][c];
		Deque<Node> water = new ArrayDeque<>();
		Deque<Node> loc = new ArrayDeque<>();
		int[] goal = new int[2];
		for(int i = 0; i < r; i++) {
			String s = br.readLine();
			for(int j = 0; j < c; j++) {
				switch(s.charAt(j)) {
				case 'D': 
					goal[0] = i;
					goal[1] = j;
					break;
				case 'S': 
					loc.offer(new Node(i,j,0));
					break;
				case '*':
					water.offer(new Node(i,j,0));
					break;
				case 'X':
					map[i][j] = true;
				}
			}
		}
		
		// bfs 탐색
		int time = 0;
		boolean[][] visit = new boolean[r][c];
		while(!water.isEmpty() || !loc.isEmpty()) {
			// 물 먼저 차오르기
			while(!water.isEmpty()) {
				if(water.peek().t > time) break;
				Node node = water.poll();
				if(map[node.x][node.y]) continue;
				map[node.x][node.y] = true;
				
				for(int[] d : dir) {
					int dx = node.x + d[0];
					int dy = node.y + d[1];
					if(check(dx,dy) && !map[dx][dy] && !(goal[0] == dx && goal[1] == dy)) {
						water.offer(new Node(dx,dy,time+1));
					}
				}
			}
			
			while(!loc.isEmpty()) {
				if(loc.peek().t > time) break;
				Node node = loc.poll();
				if(map[node.x][node.y]) continue;
				if(visit[node.x][node.y]) continue;
				visit[node.x][node.y] = true;
				
				for(int[] d : dir) {
					int dx = node.x + d[0];
					int dy = node.y + d[1];
					if(check(dx,dy) && !map[dx][dy]) {
						// 골이면 출력하고 종료
						if(goal[0] == dx && goal[1] == dy) {
							System.out.println(node.t+1);
							return;
						}
						loc.offer(new Node(dx,dy,time+1));
					}
				}
			}
			
			time++;
		}
		
		System.out.println("KAKTUS");
	}
}
