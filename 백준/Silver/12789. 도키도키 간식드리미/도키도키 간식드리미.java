import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Deque<Integer> line = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int last = 1;
		for(int i = 0; i < n; i++) {
			int s = Integer.parseInt(st.nextToken());
			while(!line.isEmpty() && line.peekLast() == last) {
				line.pollLast();
				last++;
			}
			if(s == last) {
				last++;
				continue;
			}
			line.offer(s);
		}
		while(!line.isEmpty() && line.peekLast() == last) {
			line.pollLast();
			last++;
		}
		System.out.println(line.isEmpty() ? "Nice" : "Sad");
	}
}