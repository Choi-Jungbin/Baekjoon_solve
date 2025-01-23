import java.io.*;
import java.util.Deque;
import java.util.ArrayDeque;

class Loc{
	public int x;
	public int y;
	
	public Loc(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static boolean check(int x, int y, int n, int m) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] num = br.readLine().split(" ");
		int n = Integer.parseInt(num[0]);
		int m = Integer.parseInt(num[1]);
		
		int[][] map = new int[n][m];
		int x = 0;
		int y = 0;
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				switch(s.charAt(j)) {
				case 'O':
					break;
				case 'P':
					map[i][j] = 1;
					break;
				case 'X':
					map[i][j] = -1;
					break;
				case 'I':
					x = i;
					y = j;
					break;
				}
			}
		}
		
		int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
		boolean[][] visit = new boolean[n][m];
		Deque<Loc> stack = new ArrayDeque<>();
		int count = 0;
		stack.offer(new Loc(x,y));
		while(!stack.isEmpty()) {
			Loc loc = stack.pollLast();
			for(int[] d : dir) {
				x = loc.x + d[0];
				y = loc.y + d[1];
				if(check(x,y,n,m)) {
					if(map[x][y] != -1 && !visit[x][y]) {
						visit[x][y] = true;
						count += map[x][y];
						stack.offer(new Loc(x,y));
					}
				}
			}
		}
		System.out.println(count == 0 ? "TT" : count);
	}
}