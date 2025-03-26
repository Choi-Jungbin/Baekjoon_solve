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
			
			Map<Integer, List<Integer>> map = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < len/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				map.putIfAbsent(from, new ArrayList<Integer>());
				map.get(from).add(to);
			}
			
			// 방문자 체크
			boolean[] visited = new boolean[101];
			visited[n] = true;
			Deque<Num> que = new ArrayDeque<>();
			for(int m : map.get(n)) {
				que.offer(new Num(m, 1));
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
				if(map.containsKey(node.n)){
					for(int m : map.get(node.n)) {
						que.offer(new Num(m, dept+1));
					}
				}
			}
			
			System.out.println("#" + test + " " + ans);
		}
	}
}
