import java.util.*;
import java.io.*;

class Node{
	int x;
	int y;
	
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	static boolean check(int x, int y, int n) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			int n = Integer.parseInt(br.readLine());
			int[][] board = new int[n][n];
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int v = 0;
			int num = 0;
			
			int[][] count = new int[n][n];
			// 지나가는 길에 대해 같이 갱신
			Deque<Node> visited = new ArrayDeque<Node>();
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					int visit = 1;
					// 사방 탐색해서 현재 위치보다 1 큰 방이 있는지 확인
					Node node = new Node(i,j);
					visited.offer(node);
					int x = i;
					int y = j;
					while(x > -1 && y > -1) {
						for(int[] d : dir) {
							x = node.x + d[0];
							y = node.y + d[1];
							if(check(x,y,n) && board[x][y] == board[node.x][node.y]+1) {
								// 이미 전에 방문한 적이 있다면
								if(count[x][y] > 0) {
									visit += count[x][y];
									x = -1;
									y = -1;
									break;
								}
								visit++;
								node = new Node(x,y);
								visited.offer(node);
								break;
							}
							// 들를 수 있는 곳이 없을 때
							x = -1;
							y = -1;
						}
					}
					
					// 현재 있는 칸이 방문 최대 값인지 확인
					// 어차피 내 뒤에 있는 칸들은 나보다 방문하는 칸 수가 작음
					if(visit == v && num > board[i][j]) {
						num = board[i][j];
					}
					else if(visit > v) {
						v = visit;
						num = board[i][j];
					}
					
					// 이미 탐색 했는지 갱신
					while(!visited.isEmpty()) {
						node = visited.poll();
						count[node.x][node.y] = visit;
						visit--;
					}
				}
			}
			
			System.out.println("#" + test + " " + num + " " + v);
		}
	}
}
