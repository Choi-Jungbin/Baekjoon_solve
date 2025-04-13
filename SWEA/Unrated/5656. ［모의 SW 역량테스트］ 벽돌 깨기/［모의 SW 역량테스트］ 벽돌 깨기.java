import java.io.*;
import java.util.*;

class Node{
	int x, y, size;
	
	Node(int x, int y, int size){
		this.x = x;
		this.y = y;
		this.size = size;
	}
}

public class Solution {
	static int n, w, h, min;
	static int[][] board;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	static boolean check(int x, int y) {
		return x >= 0 && x < h && y >= 0 && y < w;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			board = new int[h][w];
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = w * h;
			bomb(0);
			System.out.println("#" + test + " " + min);
		}
	}
	
	static void bomb(int p) {
		if(min == 0) return;
		int sum = 0;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				sum += board[i][j] > 0 ? 1 : 0;
			}
		}
		min = Math.min(min, sum);
		if(p == n) return;
		
		int[][] copy = new int[h][w];
		for(int i = 0; i < h; i++) {
			copy[i] = board[i].clone();
		}
		
		for(int j = 0; j < w; j++) {
			for(int i = 0; i < h; i++) {
				if(board[i][j] > 0) {
					Deque<Node> que = new ArrayDeque<>();
					que.offer(new Node(i,j, board[i][j]));
					while(!que.isEmpty()) {
						Node node = que.poll();
						int x = node.x;
						int y = node.y;
						if(board[x][y] == 0) continue;
						board[x][y] = 0;
						if(node.size == 1) {
							board[x][y] = 0;
							continue;
						}
						for(int[] d : dir) {
							for(int k = 1; k < node.size; k++) {
								int dx = x + d[0]*k;
								int dy = y + d[1]*k;
								if(check(dx,dy) && board[dx][dy] > 0) que.offer(new Node(dx,dy,board[dx][dy]));
							}
						}
					}
					arrange();
					bomb(p+1);
					// 복구
					for(int c = 0; c < h; c++) {
						board[c] = copy[c].clone();
					}
					break;
				}
			}
		}
	}
	
	static void arrange() {
		for(int j = 0; j < w; j++) {
			int loc = h-1;
			for(int i = h-1; i >= 0; i--) {
				if(board[i][j] > 0) {
					int temp = board[i][j];
					board[i][j] = 0;
					board[loc][j] = temp;
					loc--;
				}
			}
		}
	}
}