import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		long[][] cost = new long[n][n];
		
		// 갈 수 없는 거리는 최대 비용 * (도시 수-1) + 1 해야 함
		// 그치만 귀찮으니까 바로 long으로 바꾸고 MAX_VALUE 때리기
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				cost[i][j] = Integer.MAX_VALUE;
			}
		}
		for(int i = 0; i < n; i++) {
			cost[i][i] = 0;
		}
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			cost[a][b] = Math.min(cost[a][b], c);
		}
		
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				if(k == i) continue;
				for(int j = 0; j < n; j++) {
					cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(cost[i][j] == Integer.MAX_VALUE) System.out.print(0 + " ");
				else System.out.print(cost[i][j] + " ");
			}
			System.out.println();
		}
	}
}
