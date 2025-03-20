import java.io.*;
import java.util.*;

class Union{
	int n;
	int[] parent;
	
	Union(int n){
		this.n = n;
		parent = new int[n];
		for(int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}
	
	boolean isUnion(int a, int b) {
		return find(a) == find(b);
	}
	
	void union(int a, int b) {
		if(find(a) == find(b)) return;
		if(find(a) > find(b)) {
			parent[find(a)] = parent[b];
			find(a);
		}
		else {
			parent[find(b)] = parent[a];
			find(b);
		}
	}
	
	int find(int a) {
		int p = parent[a];
		if(a != p) {
			// 경로 압축
			p = find(p);
			parent[a] = p;
		}
		return p;
	}
}

class Edge implements Comparable<Edge>{
	int a;
	int b;
	int e;
	
	Edge(int a, int b, int e){
		this.a = a;
		this.b = b;
		this.e = e;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.e, o.e);
	}
}

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Edge> que = new PriorityQueue<>();
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			
			// 가중치가 작은 순으로 우선순위큐에 저장
			que.offer(new Edge(a,b,c));
		}
		
		Union union = new Union(v);
		// 가중치의 범위가 int_min에서 int_max라서 long으로 저장
		long result = 0;
		while(!que.isEmpty()) {
			Edge edge = que.poll();
			if(union.isUnion(edge.a, edge.b)) continue;
			result += edge.e;
			union.union(edge.a, edge.b);
		}
		
		System.out.println(result);
	}
}
