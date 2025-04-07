import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] cost;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		cost = new int[n][3];

		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(Math.min(dp(0), Math.min(dp(1), dp(2))));
	}
	
	static int dp(int f) {
		int[][] sum = new int[n][3];
		for(int i = 0; i < 3; i++) {
			if(i == f) {
				sum[1][f] = Integer.MAX_VALUE;
				continue;
			}
			sum[1][i] = cost[0][f] + cost[1][i];
		}
		
		for(int i = 2; i < n; i++) {
			sum[i][0] = cost[i][0] + Math.min(sum[i-1][1], sum[i-1][2]);
			sum[i][1] = cost[i][1] + Math.min(sum[i-1][0], sum[i-1][2]);
			sum[i][2] = cost[i][2] + Math.min(sum[i-1][0], sum[i-1][1]);
		}
		
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			if(i == f) continue;
			result = Math.min(result, sum[n-1][i]);
		}
		
		return result;
	}
}
