import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			String[] str = br.readLine().split(" ");
			int n = Integer.parseInt(str[0]) / 4;
			int k = Integer.parseInt(str[1]);
			Set<Integer> set = new TreeSet<>();
			Deque<Integer> que = new ArrayDeque<>();
			for(int a : br.readLine().toCharArray()) {
				if(a <= '9') a -= '0';
				else a -= 'A' - 10;
				que.offer(a);
			}

			for(int rot = 0; rot < n; rot++) {
				for(int i = 0; i < 4; i++) {
					int num = 0;
					for(int j = 0; j < n; j++) {
						int c = que.pollLast();
						num += c * Math.pow(16, j);
						que.offerFirst(c);
					}
					set.add(-num);
				}
				que.offerFirst(que.pollLast());
			}
			int num = -set.stream().toArray(Integer[]::new)[k-1];
			System.out.println("#" + test + " " + num);
		}
	}
}