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
	
	int find(int v) {
		if(parent[v] == v) return v;
		return parent[v] = find(parent[v]);
	}
	
	void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return;
		if(pa > pb) parent[pa] = pb;
		else parent[pb] = pa;
	}
}

public class Main {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Union u = new Union(n+1);
		int k, a, b;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if(k == 0) u.union(a, b);
			else {
				sb.append((u.find(a) == u.find(b)) ? "YES" : "NO");
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}