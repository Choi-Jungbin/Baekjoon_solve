import java.io.*;

class Union{
	int size;
	int[] parent;
	
	Union(int size){
		this.size = size;
		parent = new int[size+1];
		for(int i = 0; i <= size; i++) {
			parent[i] = i;
		}
	}
	
	int union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa != pb) {
			if(pa > pb) {
				parent[pa] = pb;
				return pb;
			}
			else {
				parent[pb] = pa;
				return pa;
			}
		}
		return pa;
	}
	
	int find(int n) {
		int p = parent[n];
		if(p != n) {
			p = find(p);
			parent[n] = p;
		}
		return p;
	}
	
	void print() {
		for(int p : parent) {
			System.out.print(p + " ");
		}
		System.out.println();
	}
}

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		
		Union u = new Union(n);
		
		// 진실을 아는 사람들
		s = br.readLine().split(" ");
		int k = Integer.parseInt(s[0]);
		if(k == 0) {
			System.out.println(m);
			return;
		}
		for(int i = 1; i <= k; i++) {
			u.union(0, Integer.parseInt(s[i]));
		}
		
		// 파티에 오는 사람들
		int[][] party = new int[m][];
		for(int i = 0; i < m; i++) {
			s = br.readLine().split(" ");
			k = Integer.parseInt(s[0]);
			int[] pty = new int[k];
			int p = Integer.parseInt(s[1]);
			for(int j = 1; j <= k; j++) {
				int num = Integer.parseInt(s[j]);
				pty[j-1] = num;
				// 같은 파티 갔던 사람 찾기
				p = u.union(p, num);
			}
			party[i] = pty;
		}
		
		int count = 0;
		for(int[] pty : party) {
			boolean check = true;
			for(int p : pty) {
				if(u.find(p) == 0) {
					check = false;
					break;
				}
			}
			if(check) count++;
		}
		System.out.println(count);
	}
}