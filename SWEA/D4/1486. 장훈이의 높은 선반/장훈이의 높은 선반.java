import java.util.*;
import java.io.*;

public class Solution {
	static int n;
	static int b;
	static int min;
	static int[] height;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			height = new int[n];
			for(int i = 0; i < n; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			min = Integer.MAX_VALUE;
			
			// 비트 연산으로 부분 집합 생성
			for(int i = 0; i < (1<<n); i++) {
				int sum = 0;
				for(int j = 0; j < n; j++) {
					// 포함 되는지 확인
					if((i&1<<j) != 0) {
						sum += height[j];
					}
				}
				if(sum >= b) min = Math.min(min, sum-b);
			}
			System.out.println("#" + test + " " + min);
		}
	}
}
