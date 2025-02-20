import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// r은 0, g는 1, b는 2
		int[][] cost = new int[n][3];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] rgb = {{1,2},{0,2},{0,1}};
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < 3; j++) {
				// 각 색깔이 칠해지지 않은 전의 집 중 비용이 작은 놈
				cost[i][j] += Math.min(cost[i-1][rgb[j][0]], cost[i-1][rgb[j][1]]);
			}
		}
		
		System.out.println(Math.min(cost[n-1][0], Math.min(cost[n-1][1], cost[n-1][2])));
	}
}
