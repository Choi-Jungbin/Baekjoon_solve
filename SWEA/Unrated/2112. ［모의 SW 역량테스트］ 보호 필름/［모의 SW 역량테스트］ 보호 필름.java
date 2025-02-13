import java.io.*;
import java.util.*;

public class Solution {	
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			String[] s = br.readLine().split(" ");
			int d = Integer.parseInt(s[0]);
			int w = Integer.parseInt(s[1]);
			int k = Integer.parseInt(s[2]);
			// 0은 A, 1은 B
			int[] map = new int[d];
			for(int i = 0; i < d; i++) {
				s = br.readLine().split(" ");
				for(int j = 0; j < w; j++) {
					map[i] <<= 1;
					if(s[j].equals("1")) {
						map[i] += 1;
					}
				}
			}
			
			// 바꾸지 않고 검사 통과 가능한지
			if(check(d,w,k,map)) {
				System.out.println("#" + test + " " + 0);
				continue;
			}
			
			// 최대값
			min = k;
			drug(d, w, k, map, 0, 0);
			
			System.out.println("#" + test + " " + min);
		}
	}
	
	// 모든 열이 검사를 통과하는지
	static boolean check(int d, int w, int k, int[] map) {
		for(int j = 0; j < w; j++) {
			int check = (map[0] >> j) & 1;
			int count = 1;
			for(int i = 1; i < d; i++) {
				if((check ^ ((map[i] >> j) & 1)) == 0) {
					count++;
				}
				else {
					check = (map[i] >> j) & 1;
					if(count >= k) break;
					count = 1;
				}
			}
			if(count < k) return false;
		}
		return true;
	}
	
	static void drug(int d, int w, int k, int[] map, int change, int start) {
		int store = 0;
		change++;
		if(change >= min) return;
		for(int i = start; i < d; i++) {
			store = map[i];
			// true로 바꾸기
			map[i] = 0;
			if(check(d,w,k,map)) {
				map[i] = store;
				min = Math.min(min, change);
				return;
			}
			else {
				drug(d, w, k, map, change, i+1);
			}
			// false로 바꾸기
			map[i] = (1 << w) - 1;
			if(check(d,w,k,map)) {
				map[i] = store;
				min = Math.min(min, change);
				return;
			}
			else {
				drug(d, w, k, map, change, i+1);
			}
			map[i] = store;
		}
	}
}