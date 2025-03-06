import java.io.*;
import java.util.*;

class Node{
	int x;
	int y;
	
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	static int n;
	static int[][] dir = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	
	static boolean check(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			n = Integer.parseInt(br.readLine());
			// 초기화
			int[][] board = new int[n][n];
			Deque<Node> que = new ArrayDeque<>();
			for(int i = 0; i < n; i++) {
				String s = br.readLine();
				for(int j = 0; j < n; j++) {
					// 지뢰 있는 곳은 -1
					if(s.charAt(j) == '*') {
						board[i][j] = -1;
						que.offer(new Node(i,j));
					}
				}
			}
			
			// 전체 칸 수
			int result = n*n;
			boolean[][] visit = new boolean[n][n];
			// 지뢰 주변 표시하기
			while(!que.isEmpty()) {
				Node node = que.poll();
				visit[node.x][node.y] = true;
				result--;
				for(int[] d : dir) {
					int x = node.x + d[0];
					int y = node.y + d[1];
					if(check(x,y) && board[x][y] > -1) {
						board[x][y]++;
					}
				}
			}
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					// 같이 눌리는 칸 찾기 위해 0일 때 탐색
					if(!visit[i][j] && board[i][j] == 0) {
						result++;
						que.offer(new Node(i,j));
						while(!que.isEmpty()) {
							Node node = que.poll();
							if(visit[node.x][node.y]) continue;
							// 0과 맞닿아 있어 한번에 눌리기 때문에
							result--;
							visit[node.x][node.y] = true;
							// 0일 때만 주변 같이 클릭
							if(board[node.x][node.y] != 0) continue;
							for(int[] d : dir) {
								int x = node.x + d[0];
								int y = node.y + d[1];
								if(check(x,y) && !visit[x][y]) {
									que.offer(new Node(x,y));
								}
							}
						}
					}
				}
			}
			
			// 최소 클릭 수 = 전체 칸 수 - 지뢰 수 - 하나의 0과 맞닿아 있는 모든 칸
			// 0과 맞닿지 않았다면 클릭해도 다른 칸이 같이 눌리지 않음
			System.out.println("#" + test + " " + result);
		}
	}
}
