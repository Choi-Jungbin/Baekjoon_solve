import java.io.*;
import java.util.*;

public class Solution {
	static int n, m;
	
	static class Node{
		List<Integer> big = new ArrayList<>();
		List<Integer> small = new ArrayList<>();
		int b = 0;
		int s = 0;
		
		Node(){
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int test = 1; test <= T; test++) {
			n = Integer.parseInt(br.readLine().trim());
			m = Integer.parseInt(br.readLine().trim());
			
			List<Node> node = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				node.add(new Node());
			}
			
			for(int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				node.get(a).big.add(b);
				node.get(b).small.add(a);
			}
			
			int count = 0;
			Deque<Integer> que = new ArrayDeque<>();
			for(Node nd : node) {
				boolean[] visit = new boolean[n];
				for(int b : nd.big) {
					que.add(b);
				}
				while(!que.isEmpty()) {
					int q = que.poll();
					if(visit[q]) continue;
					visit[q] = true;
					nd.b++;
					for(int b : node.get(q).big) {
						que.add(b);
					}
				}
				
				for(int s: nd.small) {
					que.add(s);
				}
				while(!que.isEmpty()) {
					int q = que.poll();
					if(visit[q]) continue;
					visit[q] = true;
					nd.s++;
					for(int s : node.get(q).small) {
						que.add(s);
					}
				}
				
				if(nd.b + nd.s == n-1) count++;
			}
			
			System.out.println("#" + test + " " + count);
		}
	}
}