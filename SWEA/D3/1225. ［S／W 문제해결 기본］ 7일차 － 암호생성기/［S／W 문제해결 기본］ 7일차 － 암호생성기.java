import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		Deque<Integer> que = new ArrayDeque<>();
		for(int test = 1; test <= T; test++) {
			br.readLine();
			que.clear();
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				que.offer(Integer.parseInt(st.nextToken()));
			}
			
			int i = 1;
			while(true) {
				int n = que.poll() - i;
				if(n > 0) {
					que.offer(n);
					if(i == 5) i = 1;
					else i++;
				}
				else {
					que.offer(0);
					break;
				}
			}
			
			System.out.print("#" + test);
			while(!que.isEmpty()) {
				System.out.print(" " + que.poll());
			}
			System.out.println();
		}
	}
}
