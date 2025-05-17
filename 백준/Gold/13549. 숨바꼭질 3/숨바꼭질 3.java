import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int point;
	int time;
	
	Node(int point, int time){
		this.point = point;
		this.time = time;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.time, o.time);
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[100001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(n,1));
		while(!que.isEmpty()) {
			Node q = que.poll();
			if(arr[q.point] != 0 && q.time >= arr[q.point]) continue;
			arr[q.point] = q.time;
			if(q.point == k) {
				System.out.println(q.time-1);
				return;
			}
			if(q.point*2 <= 100000) que.offer(new Node(q.point*2, q.time));
			if(q.point-1 >= 0) que.offer(new Node(q.point-1, q.time+1));
			if(q.point+1 <= 100000) que.offer(new Node(q.point+1, q.time+1));
		}
	}
}