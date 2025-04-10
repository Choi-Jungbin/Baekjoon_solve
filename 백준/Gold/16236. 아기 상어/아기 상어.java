import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int x, y, time;
	
	Node(int x, int y, int time){
		this.x = x;
		this.y = y;
		this.time = time;
	}

	// 가까운 순, 위에서부터, 왼쪽에서부터 정렬
	@Override
	public int compareTo(Node o) {
		if(time == o.time) {
			if(x == o.x) return Integer.compare(y, o.y);
			return Integer.compare(x, o.x);
		}
		return Integer.compare(time, o.time);
	}
}

public class Main {
	static int n;
	static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
	
	static boolean check(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n][n];
		int x = 0;
		int y = 0;
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					map[i][j] = 0;
					x = i;
					y = j;
				}
			}
		}
		
		int time = 0;
		// 상어 크기
		int size = 2;
		// 상어가 진화하기 위한 먹이 수
		int nyam = 2;
		boolean[][] visit = new boolean[n][n];
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(x, y, 0));
		while(!que.isEmpty()) {
			Node node = que.poll();
			if(visit[node.x][node.y]) continue;
			visit[node.x][node.y] = true;
			// 나보다 작은 물고기가 있으면!
			if(map[node.x][node.y] > 0 && map[node.x][node.y] < size) {
				// 진화 조건 완료
				if(--nyam == 0) nyam = ++size;
				time += node.time;
				map[node.x][node.y] = 0;
				// 초기화
				visit = new boolean[n][n];
				que.clear();
				que.offer(new Node(node.x, node.y, 0));
				continue;
			}
			// 사방 탐색
			for(int[] d : dir) {
				int dx = node.x + d[0];
				int dy = node.y + d[1];
				if(check(dx,dy) && !visit[dx][dy] && map[dx][dy] <= size) {
					que.offer(new Node(dx,dy,node.time+1));
				}
			}
		}
		
		System.out.println(time);
    }
}