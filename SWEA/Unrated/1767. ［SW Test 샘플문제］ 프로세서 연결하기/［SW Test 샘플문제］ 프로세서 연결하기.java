import java.io.*;
import java.util.*;

public class Solution {
	static int n, size;
	static int min, count;
	static boolean[][] core;
	static List<int[]> loc = new ArrayList<>();
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	static class Node{
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean check(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine().trim());
    	for(int test = 1; test <= T; test++) {
    		n = Integer.parseInt(br.readLine().trim());
    		core = new boolean[n][n];
    		loc.clear();
    		for(int i = 0; i < n; i++) {
    			StringTokenizer st = new StringTokenizer(br.readLine());
    			for(int j = 0; j < n; j++) {
    				core[i][j] = st.nextToken().equals("1");
    				if(core[i][j]) {
    					loc.add(new int[] {i,j});
    				}
    			}
    		}
    		size = loc.size();
    		// 전선 길이는 아무리 많아도 프로세서 크기를 넘지 않음
    		min = n*n;
    		count = 0;
    		calc(0, 0, 0);
    		
    		System.out.println("#" + test + " " + min);
    	}
    }
    
    static void calc(int p, int c, int len) {
    	if(p == size) {
    		// 연결된 코어가 제일 많을 때
    		if(c > count) {
    			min = len;
    			count = c;
    		}
    		// 연결된 코어의 수가 같을 때
    		else if(c == count) {
    			min = Math.min(min, len);
    		}
    		return;
    	}
    	
    	int x = loc.get(p)[0];
    	int y = loc.get(p)[1];
    	// 코어가 가장자리와 닿아있는 경우
    	if(x == 0 || x == n-1 || y == 0 || y == n-1) {
    		calc(p+1, c+1, len);
    		return;
    	}
    	
    	List<Node> list = new ArrayList<>();
    	int l = 0;
    	for(int[] d : dir) {
    		int dx = x + d[0];
    		int dy = y + d[1];
    		while(check(dx, dy)) {
    			// 이미 자리 선점
    			if(core[dx][dy]) break;
    			list.add(new Node(dx,dy));
    			dx += d[0];
    			dy += d[1];
    		}
    		
    		// 전선이 연결이 되는 경우
    		if(!check(dx,dy)) {
    			for(Node node : list) {
    				core[node.x][node.y] = true;
    				l++;
    			}
    			calc(p+1, c+1, len+l);
    			for(Node node : list) {
    				core[node.x][node.y] = false;
    			}
    		}
    		// 전선이 연결되지 않은 경우
    		else {
    			calc(p+1, c, len);
    		}
    		
    		list.clear();
    		l = 0;
    	}
    }
}