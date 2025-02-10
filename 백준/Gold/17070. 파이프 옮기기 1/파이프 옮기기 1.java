import java.io.*;
import java.util.*;

class Loc{
	int x;
	int y;
	int dir;
	
	Loc(int x, int y, int dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

public class Main {

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[][] room = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			String[] s = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				if(s[j].equals("1")) {
					room[i][j] = true;
				}
			}
		}
		
		int[][] count = new int[n][n];
		Deque<Loc> que = new ArrayDeque<>();
		que.offer(new Loc(0,1,0));
		while(!que.isEmpty()) {
			Loc loc = que.poll();
			// 가로, 세로, 대각선
			boolean[] check = new boolean[3];
			if(loc.y+1 < n) check[0] = !room[loc.x][loc.y+1];
			if(loc.x+1 < n) check[1] = !room[loc.x+1][loc.y];
			if(loc.x+1 < n && loc.y+1 < n) check[2] = check[0] && check[1] &&!room[loc.x+1][loc.y+1];
			switch(loc.dir){
			case 0:
				if(check[0]) {
					que.offer(new Loc(loc.x, loc.y+1, 0));
					count[loc.x][loc.y+1] += 1;
				}
				if(check[2]) {
					que.offer(new Loc(loc.x+1, loc.y+1, 2));
					count[loc.x+1][loc.y+1] += 1;
				}
				break;
			case 1:
				if(check[1]) {
					que.offer(new Loc(loc.x+1, loc.y, 1));
					count[loc.x+1][loc.y] += 1;
				}
				if(check[2]) {
					que.offer(new Loc(loc.x+1, loc.y+1, 2));
					count[loc.x+1][loc.y+1] += 1;
				}
				break;
			case 2:
				if(check[0]) {
					que.offer(new Loc(loc.x, loc.y+1, 0));
					count[loc.x][loc.y+1] += 1;
				}
				if(check[1]) {
					que.offer(new Loc(loc.x+1, loc.y, 1));
					count[loc.x+1][loc.y] += 1;
				}
				if(check[2]) {
					que.offer(new Loc(loc.x+1, loc.y+1, 2));
					count[loc.x+1][loc.y+1] += 1;
				}
				break;
			}
		}
		System.out.println(count[n-1][n-1]);
	}
}