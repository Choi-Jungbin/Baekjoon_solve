import java.io.*;
import java.util.*;

class Bead{
	// 빨간 구슬 위치
	int rx;
	int ry;
	// 파란 구슬 위치
	int bx;
	int by;
	// 빨간 구슬이 왔던 방향
	int rd = -1;
	int time = 0;
	boolean fail = false;
	boolean success = false;
	
	Bead(int rx, int ry, int bx, int by){
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
	}
	
	Bead(int rx, int ry, int bx, int by, int rd, int time){
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.rd = rd;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Bead [rx=" + rx + ", ry=" + ry + ", bx=" + bx + ", by=" + by + ", rd=" + rd + ", time=" + time
				+ ", fail=" + fail + ", success=" + success + "]";
	}
}

public class Main {
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	static int[] goal = new int[2];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		boolean[][] board = new boolean[n][m];
		int rx = 0;
		int ry = 0;
		int bx = 0;
		int by = 0;
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				if(s.charAt(j) != '#') {
					board[i][j] = true;
					switch(s.charAt(j)) {
					case 'R':
						rx = i;
						ry = j;
						break;
					case 'B':
						bx = i;
						by = j;
						break;
					case 'O':
						goal[0] = i;
						goal[1] = j;
					}
				}
			}
		}
		
		// 빨간 공이 지나갈 수 있는 길
		// rx 좌표, ry 좌표, bx 좌표, by 좌표, 방향, 움직인 숫자
		Deque<Bead> que = new ArrayDeque<>();
		// que 초기화
		List<Bead> list = search(new Bead(rx,ry,bx,by), board);
		for(Bead b : list) {
			que.offer(b);
		}
		while(!que.isEmpty()) {
			Bead bead = que.poll();
			bead = move(bead, board);
			// 10번 넘으면 탈락
			if(bead.time > 10) continue;
			if(!bead.fail && bead.success) {
				System.out.println(bead.time);
				return;
			}
			if(!bead.fail) {
				list = search(bead, board);
				for(Bead b : list) {
					que.offer(b);
				}
			}
		}
		System.out.println(-1);
	}
	
	// 주변에 갈 수 있는 방향 탐색
	static List<Bead> search(Bead b, boolean[][] board) {
		List<Bead> list = new ArrayList<>();
		for(int i = 0; i< 4; i++) {
			list.add(new Bead(b.rx, b.ry, b.bx, b.by, i, b.time));
		}
		return list;
	}
	
	static Bead move(Bead b, boolean[][] board) {
		switch(b.rd) {
		case 0:
			if(b.rx < b.bx) {
				b = rmove(b, board);
				if(!b.success) board[b.rx][b.ry] = false;
				b = bmove(b, board);
				board[b.rx][b.ry] = true;
			}
			else {
				b = bmove(b, board);
				board[b.bx][b.by] = false;
				b = rmove(b, board);
				board[b.bx][b.by] = true;
			}
			break;
		case 1:
			if(b.ry > b.by) {
				b = rmove(b, board);
				if(!b.success) board[b.rx][b.ry] = false;
				b = bmove(b, board);
				board[b.rx][b.ry] = true;
			}
			else {
				b = bmove(b, board);
				board[b.bx][b.by] = false;
				b = rmove(b, board);
				board[b.bx][b.by] = true;
			}
			break;
		case 2:
			if(b.rx > b.bx) {
				b = rmove(b, board);
				if(!b.success) board[b.rx][b.ry] = false;
				b = bmove(b, board);
				board[b.rx][b.ry] = true;
			}
			else {
				b = bmove(b, board);
				board[b.bx][b.by] = false;
				b = rmove(b, board);
				board[b.bx][b.by] = true;
			}
			break;
		case 3:
			if(b.ry < b.by) {
				b = rmove(b, board);
				if(!b.success) board[b.rx][b.ry] = false;
				b = bmove(b, board);
				board[b.rx][b.ry] = true;
			}
			else {
				b = bmove(b, board);
				board[b.bx][b.by] = false;
				b = rmove(b, board);
				board[b.bx][b.by] = true;
			}
			break;
		}
		b.time++;
		return b;
	}
	
	static Bead rmove(Bead b, boolean[][] board) {
		while(board[b.rx+dir[b.rd][0]][b.ry+dir[b.rd][1]]) {
			b.rx += dir[b.rd][0];
			b.ry += dir[b.rd][1];
			if(!b.fail && !b.success && b.rx == goal[0] && b.ry == goal[1]) {
				b.success = true;
				break;
			}
		}
		return b;
	}
	
	static Bead bmove(Bead b, boolean[][] board) {
		while(board[b.bx+dir[b.rd][0]][b.by+dir[b.rd][1]]) {
			b.bx += dir[b.rd][0];
			b.by += dir[b.rd][1];
			if(!b.fail && b.bx == goal[0] && b.by == goal[1]) {
				b.fail = true;
				break;
			}
		}
		return b;
	}
}