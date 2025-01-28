import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		boolean[][] map = new boolean[n+1][n+1];
		
		int result = dfs(map, n, 1);
		System.out.println(result);
	}
	
	static int dfs(boolean[][] map, int n, int r) {
		if(n < r) {
			return 1;
		}
		int count = 0;
		for(int j = 1; j <= n; j++) {
			if(map[r][j] || map[0][j]) continue;
			boolean check = false;
			// 왼쪽 대각선 확인
			int x = r-1;
			int y = j-1;
			while(x > 0 && y > 0) {
				if(map[x][y]) {
					check = true;
					break;
				}
				x--;
				y--;
			}
			if(check) continue;
			// 오른쪽 대각선 확인
			x = r-1;
			y = j+1;
			while(x > 0 && y <= n) {
				if(map[x][y]) {
					check = true;
					break;
				}
				x--;
				y++;
			}
			if(check) continue;
			
			map[r][0] = true;
			map[0][j] = true;
			map[r][j] = true;
			count += dfs(map, n, r+1);
			// 다시 초기화
			map[r][0] = false;
			map[0][j] = false;
			map[r][j] = false;
		}
		return count;
	}
}