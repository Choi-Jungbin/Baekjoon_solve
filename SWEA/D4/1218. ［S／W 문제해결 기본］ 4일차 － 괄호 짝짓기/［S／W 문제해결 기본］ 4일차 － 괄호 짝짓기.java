import java.io.*;
import java.util.*;

public class Solution {
	static Map<Character,Integer> map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new HashMap<>();
		map.put('(', 0);
		map.put('[', 1);
		map.put('{', 2);
		map.put('<', 3);
		map.put(')', 0);
		map.put(']', 1);
		map.put('}', 2);
		map.put('>', 3);
		Deque<Integer> stack = new ArrayDeque<>();
		for(int test = 1; test <= 10; test++) {
			int n = Integer.parseInt(br.readLine());
			String s = br.readLine();
			stack.clear();
			boolean check = false;
			for(int i = 0; i < n; i++) {
				switch(s.charAt(i)) {
				case '(':
				case '[':
				case '{':
				case '<':
					stack.offer(map.get(s.charAt(i)));
					break;
				case ')':
				case ']':
				case '}':
				case '>':
					if(stack.pollLast() == map.get(s.charAt(i))) break;
				default:
					System.out.println("#" + test + " " + 0);
					check = true;
				}
				if(check) break;
			}
			if(!check) System.out.println("#" + test + " " + 1);
		}
	}
}
