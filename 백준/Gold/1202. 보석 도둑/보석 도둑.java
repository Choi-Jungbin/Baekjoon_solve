import java.io.*;
import java.util.*;

class Ston{
	int m, v;
	
	Ston(int m, int v){
		this.m = m;
		this.v = v;
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 보석의 무게 순으로 넣기
		PriorityQueue<Ston> ston = new PriorityQueue<>(new Comparator<Ston>() {
			@Override
			public int compare(Ston o1, Ston o2) {
				return Integer.compare(o1.m, o2.m);
			}
		});
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			ston.offer(new Ston(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		PriorityQueue<Integer> bag = new PriorityQueue<>();
		for(int i = 0; i < k; i++) {
			bag.add(Integer.parseInt(br.readLine()));
		}
		
		// 현재 담은 가치
		long sum = 0;
		// 가치 역순 정렬
		PriorityQueue<Ston> value = new PriorityQueue<>(new Comparator<Ston>() {
			@Override
			public int compare(Ston o1, Ston o2) {
				return Integer.compare(o2.v, o1.v);
			}
		});
		while(!bag.isEmpty()) {
			int b = bag.poll();
			// 가방에 들어갈 수 있는 보석이 있으면 빼기
			while(!ston.isEmpty() && ston.peek().m <= b) {
				value.offer(ston.poll());
			}
			if(!value.isEmpty()) sum += value.poll().v;
		}
		
		System.out.println(sum);
	}
}
