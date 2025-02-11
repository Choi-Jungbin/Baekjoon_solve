import java.io.*;
import java.util.*;

class Pair{
	int x;
	int y;
	
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null || obj.getClass() != this.getClass()) return false;
		Pair p = (Pair) obj;
		return p.x == this.x && p.y == this.y;
	}
}

public class Main {
	static boolean check(int x, int y, int n) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[][] board = new boolean[n][n];
		int k = Integer.parseInt(br.readLine());
		String[] s;
		for(int i = 0; i < k; i++) {
			s = br.readLine().split(" ");
			// 사과 자리는 true
			board[Integer.parseInt(s[0])-1][Integer.parseInt(s[1])-1] = true;
		}
		// 도는 자리
		int l = Integer.parseInt(br.readLine());
		Deque<int[]> lot = new ArrayDeque<>();
		for(int i = 0; i < l; i++) {
			s = br.readLine().split(" ");
			int t = Integer.parseInt(s[0]);
			int lt = s[1].equals("D") ? 1 : -1;
			lot.offer(new int[] {t,lt});
		}
		
		// 왼쪽, 아래, 오른쪽, 위
		int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
		// 뱀 몸통의 현재 위치
		Deque<Pair> que = new ArrayDeque<>();
		int x = 0;
		int y = 0;
		int d = 0;
		int time = 0;
		int lt = lot.peek()[0];
		// 처음 위치 포함
		que.offer(new Pair(x,y));
		while(true) {
			time++;
			x += dir[d][0];
			y += dir[d][1];
			Pair p = new Pair(x,y);
			// 벽에 부딪히거나 몸통에 부딪히거나
			if(!check(x,y,n) || que.contains(p)) {
				System.out.println(time);
				return;
			}
			que.offer(p);
			// 사과 먹으면 몸 길어지기
			if(board[x][y]) board[x][y] = false;
			else que.poll();
			
			// 돌 시간이 됐으면
			if(time == lt) {
				d = (d+lot.poll()[1]+4) % 4;
				// 만약 돌 일이 더 남았다면
				if(!lot.isEmpty()) lt = lot.peek()[0];
			}
		}
	}
}