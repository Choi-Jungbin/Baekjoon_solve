import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;

class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Map<Integer,ArrayList<Integer>> map = new HashMap<>();
		int[] tree = new int[n+1];
		for(int i = 0; i < n-1; i++) {
			String[] s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			if(map.containsKey(a)) {
				map.get(a).add(b);
			}
			else {
				map.put(a, new ArrayList<>(Arrays.asList(b)));
			}
			if(map.containsKey(b)) {
				map.get(b).add(a);
			}
			else {
				map.put(b, new ArrayList<>(Arrays.asList(a)));
			}
		}
		
		Deque<Integer> que = new ArrayDeque<>();
		que.offer(1);
		tree[1] = 1;
		while(!que.isEmpty()) {
			int i = que.poll();
			for(int j : map.get(i)) {
				if(tree[j] == 0) {
					tree[j] = i;
					que.offer(j);
				}
			}
		}
		for(int i = 2; i < n+1; i++) {
			System.out.println(tree[i]);
		}
	}
}