import java.io.*;
import java.util.*;

class Person{
	int x;
	int y;
	// 계단과의 거리
	int[] dst = {0,0};
	
	Person(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	static int n, plen, min;
	static int[][] stair;
	static List<Person> person = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			n = Integer.parseInt(br.readLine());
			// 방 구조
			int[][] map = new int[n][n];
			// 사람들
			person.clear();
			// 계단 정보 [x위치, y위치, 내려가는 시간]
			stair = new int[2][3];
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) {
						if(map[i][j] == 1) {
							person.add(new Person(i,j));
						}
						else {
							if(stair[0][2] == 0) stair[0] = new int[]{i, j, map[i][j]};
							else stair[1] = new int[]{i, j, map[i][j]};
						}
					}
				}
			}
			
			plen = person.size();
			// 사람과 계단 간 거리 초기화
			for(Person i : person) {
				i.dst[0] = Math.abs(i.x - stair[0][0]) + Math.abs(i.y - stair[0][1]);
				i.dst[1] = Math.abs(i.x - stair[1][0]) + Math.abs(i.y - stair[1][1]);
			}
			
			min = Integer.MAX_VALUE;
			combi(0,0);
			
			System.out.println("#" + test + " " + min);
		}
	}
	
	// 어느 계단으로 갈지 확인
	static void combi(int bit, int len) {
		if(len == plen) {
			calc(bit);
			return;
		}
		combi(bit, len+1);
		combi(bit | (1<<len), len+1);
	}
	
	static void calc(int bit) {
		List<Integer> stair0 = new ArrayList<>();
		List<Integer> stair1 = new ArrayList<>();
		
		for(int i = 0; i < plen; i++) {
			Person p = person.get(i);
			// 0이면 1번 계단 1이면 2번 계단
			if(((bit>>i) & 1) == 0) {
				stair0.add(p.dst[0]);
			}
			else {
				stair1.add(p.dst[1]);
			}
		}
		
		int t0 = simulate(stair0, stair[0][2]);
		int t1 = simulate(stair1, stair[1][2]);

		min = Math.min(min, Math.max(t0, t1));
	}

	static int simulate(List<Integer> arrival, int len) {
		if(arrival.isEmpty()) return 0;
		Collections.sort(arrival);
		Deque<Integer> que = new ArrayDeque<>();
		int time = 0;

		PriorityQueue<Integer> waiting = new PriorityQueue<>(arrival);

		time = waiting.peek();
		while(!waiting.isEmpty() || !que.isEmpty()) {
			while(!que.isEmpty() && que.peek() <= time) {
				que.poll();
			}
			while(que.size() < 3 && !waiting.isEmpty() && waiting.peek() <= time) {
				que.offer(time + len);
				waiting.poll();
			}
			time++;
		}
		return time;
	}
}