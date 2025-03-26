import java.io.*;
import java.util.*;

class Node{
	// 원래 위치
	int x, y;
	// 변경 위치
	int dx, dy;
	// 원래 값
	int v;
	// 재귀 깊이
	int d;
	
	Node(int x, int y, int dx, int dy, int v, int d){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.v = v;
		this.d = d;
	}
}

public class Main {
	static int n, max = 0;
	static int[][] board;
	static Deque<Node> stack = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, board[i][j]);
			}
		}
		move(0);
		System.out.println(max);
	}
	
	static void move(int d) {
		// 최대 5번 움직이기
		if(d >= 5) {
			return;
		}
		// 위, 아래, 오른쪽, 왼쪽 하나씩 가보기
		up(d);
		move(d+1);
		rollback(d);
		
		down(d);
		move(d+1);
		rollback(d);
		
		left(d);
		move(d+1);
		rollback(d);
		
		right(d);
		move(d+1);
		rollback(d);
	}
	
	static void rollback(int d) {
		while(!stack.isEmpty() && stack.peekLast().d == d) {
			Node node = stack.pollLast();
			board[node.dx][node.dy] = 0;
			board[node.x][node.y] = node.v;
		}
	}
	
	static void up(int d) {
		for(int i = 0; i < n; i++) {
			int loc = 0;
			for(int j = 1; j < n; j++) {
				// 블럭이 있는 곳
				if(board[i][j] > 0) {
					while(loc < j) {
						// 옮길 곳이 0일 때
						if(board[i][loc] == 0) {
							board[i][loc] = board[i][j];
							stack.offer(new Node(i,j,i,loc,board[i][loc],d));
							board[i][j] = 0;
							break;
						}
						// 옮길 곳이 같은 블럭일 때
						else if(board[i][loc] == board[i][j]) {
							stack.offer(new Node(i,loc,i,loc,board[i][loc],d));
							stack.offer(new Node(i,j,i,loc,board[i][j],d));
							board[i][loc] *= 2;
							max = Math.max(max, board[i][loc]);
							board[i][j] = 0;
							loc++;
							break;
						}
						// 옮길 곳이 다른 블럭일 때
						loc++;
					}
				}
			}
		}
	}
	
	static void down(int d) {
		for(int i = 0; i < n; i++) {
			int loc = n-1;
			for(int j = n-2; j >= 0; j--) {
				// 블럭이 있는 곳
				if(board[i][j] > 0) {
					while(loc > j) {
						// 옮길 곳이 0일 때
						if(board[i][loc] == 0) {
							board[i][loc] = board[i][j];
							stack.offer(new Node(i,j,i,loc,board[i][loc],d));
							board[i][j] = 0;
							break;
						}
						// 옮길 곳이 같은 블럭일 때
						else if(board[i][loc] == board[i][j]) {
							stack.offer(new Node(i,loc,i,loc,board[i][loc],d));
							stack.offer(new Node(i,j,i,loc,board[i][j],d));
							board[i][loc] *= 2;
							max = Math.max(max, board[i][loc]);
							board[i][j] = 0;
							loc--;
							break;
						}
						// 옮길 곳이 다른 블럭일 때
						loc--;
					}
				}
			}
		}
	}
	
	static void left(int d) {
		for(int i = 0; i < n; i++) {
			int loc = 0;
			for(int j = 1; j < n; j++) {
				// 블럭이 있는 곳
				if(board[j][i] > 0) {
					while(loc < j) {
						// 옮길 곳이 0일 때
						if(board[loc][i] == 0) {
							board[loc][i] = board[j][i];
							stack.offer(new Node(j,i,loc,i,board[loc][i],d));
							board[j][i] = 0;
							break;
						}
						// 옮길 곳이 같은 블럭일 때
						else if(board[loc][i] == board[j][i]) {
							stack.offer(new Node(loc,i,loc,i,board[loc][i],d));
							stack.offer(new Node(j,i,loc,i,board[j][i],d));
							board[loc][i] *= 2;
							max = Math.max(max, board[loc][i]);
							board[j][i] = 0;
							loc++;
							break;
						}
						// 옮길 곳이 다른 블럭일 때
						loc++;
					}
				}
			}
		}
	}
	
	static void right(int d) {
		for(int i = 0; i < n; i++) {
			int loc = n-1;
			for(int j = n-2; j >= 0; j--) {
				// 블럭이 있는 곳
				if(board[j][i] > 0) {
					while(loc > j) {
						// 옮길 곳이 0일 때
						if(board[loc][i] == 0) {
							board[loc][i] = board[j][i];
							stack.offer(new Node(j,i,loc,i,board[loc][i],d));
							board[j][i] = 0;
							break;
						}
						// 옮길 곳이 같은 블럭일 때
						else if(board[loc][i] == board[j][i]) {
							stack.offer(new Node(loc,i,loc,i,board[loc][i],d));
							stack.offer(new Node(j,i,loc,i,board[j][i],d));
							board[loc][i] *= 2;
							max = Math.max(max, board[loc][i]);
							board[j][i] = 0;
							loc--;
							break;
						}
						// 옮길 곳이 다른 블럭일 때
						loc--;
					}
				}
			}
		}
	}
}
