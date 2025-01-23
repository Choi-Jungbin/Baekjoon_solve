import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] map = new int[100001];
		map[n] = 1;
		Deque<Integer> que = new ArrayDeque<>();
		que.offer(n);
		while(!que.isEmpty()) {
			int i = que.poll();
			if(i == k) break;
			int count = map[i] + 1;
			if(i > 0 && (map[i-1] == 0 || map[i-1] > count)) {
				map[i-1] = count;
				que.offer(i-1);
			}
			if(i < 100000 && (map[i+1] == 0 || map[i+1] > count)) {
				map[i+1] = count;
				que.offer(i+1);
			}
			if(2*i < 100001 && (map[2*i] == 0 || map[2*i] > count)) {
				map[2*i] = count;
				que.offer(2*i);
			}
		}
		System.out.println(map[k]-1);
	}
}