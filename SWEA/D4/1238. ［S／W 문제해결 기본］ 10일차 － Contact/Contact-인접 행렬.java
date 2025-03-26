import java.io.*;
import java.util.*;

class Num{
	int n;
	int d;
	
	Num(int n, int d){
		this.n = n;
		this.d = d;
	}
}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int test = 1; test <= 10; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			boolean[][] map = new boolean[101][101];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < len/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				map[from][to] = true;
			}
			
			// 방문자 체크
			boolean[] visited = new boolean[101];
			visited[n] = true;
			Deque<Num> que = new ArrayDeque<>();
			for(int i = 1; i < 101; i++) {
				if(map[n][i]) que.offer(new Num(i, 1));
			}
			
			int dept = 0;
			int ans = 0;
			while(!que.isEmpty()) {
				Num node = que.poll();
				if(visited[node.n]) continue;
				visited[node.n] = true;
				// 깊이가 바뀐 경우 초기화
				if(dept < node.d) {
					dept++;
					ans = 0;
				}
				// 가장 번호가 큰 사람
				ans = Math.max(ans, node.n);
				for(int i = 1; i < 101; i++) {
					if(map[node.n][i]) que.offer(new Num(i, dept+1));
				}
			}
			
			System.out.println("#" + test + " " + ans);
		}
	}
}
