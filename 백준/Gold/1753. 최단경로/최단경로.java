import java.io.*;
import java.util.*;

class Node{
	int n;
	int w;
	
	Node(int n, int w){
		this.n = n;
		this.w = w;
	}
}

public class Main {

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int e = Integer.parseInt(s[1]);
		
		int k = Integer.parseInt(br.readLine()) - 1;
		int[] weight = new int[n];
		List<Map<Integer, Integer>> list = new ArrayList<>(n);
		for(int i = 0; i < n; i++) {
			list.add(new HashMap<>());
		}
		
		for(int i = 0; i < e; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]) - 1;
			int v = Integer.parseInt(s[1]) - 1;
			int w = Integer.parseInt(s[2]);
			if(list.get(u).containsKey(v)) {
				int mw = list.get(u).get(v);
				list.get(u).put(v, Math.min(mw, w));
			}
			else {
				list.get(u).put(v,w);
			}
		}
		
		PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.w, n2.w));
		list.get(k).forEach((key, v) -> {
			weight[key] = v;
			que.offer(new Node(key, v));
		});
		
		for(int i = 0; i < n; i++) {
			if(weight[i] == 0) weight[i] = Integer.MAX_VALUE;
		}
		weight[k] = 0;
		
		boolean[] visit = new boolean[n];
		visit[k] = true;
		while(!que.isEmpty()) {
			Node node = que.poll();
			if(visit[node.n]) continue;
			visit[node.n] = true;
			list.get(node.n).forEach((key, v) -> {
				int w = node.w + v;
				if(w < weight[key]) {
					weight[key] = w;
					que.offer(new Node(key, w));
				}
			});
		}
		
		for(int i : weight) {
			System.out.println(i == Integer.MAX_VALUE ? "INF" : i);
		}
	}
}