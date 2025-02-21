import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static Map<Integer,int[]> map = new HashMap<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			int a = s.charAt(0) - 'A';
			int b = s.charAt(2) == '.' ? -1 : s.charAt(2) - 'A';
			int c = s.charAt(4) == '.' ? -1 : s.charAt(4) - 'A';
			map.put(a, new int[] {b,c});
		}
		
		case1(0);
		System.out.println();
		
		case2();
		System.out.println();
		
		case3();
	}
	
	static void case1(int a) {
		System.out.print((char) (a+'A'));
		int[] node = map.get(a);
		if(node[0] != -1) case1(node[0]);
		if(node[1] != -1) case1(node[1]);
	}
	
	static void case2() {
		Deque<Integer> stack = new ArrayDeque<>();
		stack.offer(0);
		boolean[] visit = new boolean[n];
		while(!stack.isEmpty()) {
			int a = stack.peekLast();
			if(map.get(a)[0] != -1 && !visit[map.get(a)[0]]) {
				visit[map.get(a)[0]] = true;
				stack.offer(map.get(a)[0]);
				continue;
			}
			System.out.print((char) (a+'A'));
			stack.pollLast();
			if(map.get(a)[1] != -1) {
				stack.offer(map.get(a)[1]);
			}
		}
	}
	
	static void case3() {
		Deque<Integer> stack = new ArrayDeque<>();
		stack.offer(0);
		boolean[] visit = new boolean[n];
		boolean check = true;
		while(!stack.isEmpty()) {
			int a = stack.peekLast();
			if(map.get(a)[1] != -1 && !visit[map.get(a)[1]]) {
				visit[map.get(a)[1]] = true;
				stack.offer(map.get(a)[1]);
				check = false;
			}
			if(map.get(a)[0] != -1 && !visit[map.get(a)[0]]) {
				visit[map.get(a)[0]] = true;
				stack.offer(map.get(a)[0]);
				check = false;
			}
			if(check) {
				System.out.print((char) (a+'A'));
				stack.pollLast();
			}
			check = true;
		}
	}
}
