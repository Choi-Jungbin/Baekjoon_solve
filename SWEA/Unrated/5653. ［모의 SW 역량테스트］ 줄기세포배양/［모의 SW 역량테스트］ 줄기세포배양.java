import java.io.*;
import java.util.*;

class Cell{
	// 생명력
	int k;
	// 활성화 될 시간
	int time = 0;
	// x 좌표
	int x;
	// y 좌표
	int y;
	// 죽을 시간
	int dead;
	
	Cell(int x, int y, int k, int time){
		this.x = x;
		this.y = y;
		this.k = k;
		this.time = time + k;
		// 활성화 된 시간으로부터 k시간이 지나면 죽음
		dead = this.time + k;
	}

	@Override
	public String toString() {
		return "Cell [k=" + k + ", time=" + time + ", x=" + x + ", y=" + y + ", dead=" + dead + "]";
	}
}

public class Solution {
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	static String makeKey(int x, int y) {
		return x + "," + y;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			String[] s = br.readLine().split(" ");
			int n = Integer.parseInt(s[0]);
			int m = Integer.parseInt(s[1]);
			int k = Integer.parseInt(s[2]);
			
			PriorityQueue<Cell> que = new PriorityQueue<>(Comparator
					.comparingInt((Cell cell) -> cell.time)
					.thenComparing(cell -> cell.k, Comparator.reverseOrder()));
			// 세포가 자리하는 곳
			Set<String> visit = new HashSet<>();
			for(int i = 0; i < n; i++) {
				s = br.readLine().split(" ");
				for(int j = 0; j < m; j++) {
					int a = Integer.parseInt(s[j]);
					if(a > 0) {
						que.offer(new Cell(i,j,a,0));
						visit.add(makeKey(i,j));
					}
				}
			}
			
			// 경과 시간
			int time = 0;
			// 죽어가는 세포
			PriorityQueue<Cell> dead = new PriorityQueue<>(Comparator
					.comparingInt((Cell cell) -> cell.dead).thenComparingInt(cell -> cell.k));
			// 새로 태어난 세포
			Deque<Cell> birth = new ArrayDeque<>();
			
			while(time < k) {
				time++;
				// time-1 시간에 만들어진 세포 추가
				while(!birth.isEmpty()) {
					que.offer(birth.poll());
				}
				
				// 죽은 세포 없애기
				while(!dead.isEmpty() && dead.peek().dead <= time) {
					dead.poll();
				}
				
				// 아직 활성화된 세포가 없으면
				if(que.peek().time > time) continue;
				while(!que.isEmpty() && que.peek().time <= time) {
					Cell cell = que.poll();
					for(int[] d : dir) {
						int x = cell.x + d[0];
						int y = cell.y + d[1];
						// 세포 자리 차지가 아니면
						if(!visit.contains(makeKey(x,y))) {
							visit.add(makeKey(x,y));
							birth.offer(new Cell(x,y,cell.k,time+1));
						}
					}
					// 활성화 세포 넣기
					dead.offer(cell);
				}
			}
			int result = que.size() + dead.size();
			
			System.out.println("#" + test + " " + result);
		}
	}
}