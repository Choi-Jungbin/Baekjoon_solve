import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 파란색은 true, 하얀색은 false
		boolean[][] map = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			String[] s = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				if(s[j].equals("1")) map[i][j] = true;
			}
		}
		
		int[] result = cut(map, n, 0, 0);
		System.out.println(result[0]);
		System.out.println(result[1]);
	}
	
	static int[] cut(boolean[][] map, int n, int x, int y) {
		boolean color = map[x][y];
		if(n == 1) {
			return color ? new int[] {0,1} : new int[] {1,0};
		}
		
		boolean s = color;
		for(int i = x; i < x+n; i++) {
			for(int j = y; j < y+n; j++) {
				if(s) color &= map[i][j];
				else color |= map[i][j];
				if(s != color) break;
			}
		}
		if(s == color) {
			return color ? new int[] {0,1} : new int[] {1,0};
		}
		
		n /= 2;
		int[] result = new int[2];
		int[] r1 = cut(map, n, x, y);
		int[] r2 = cut(map, n, x, y+n);
		int[] r3 = cut(map, n, x+n, y);
		int[] r4 = cut(map, n, x+n, y+n);
		result[0] = r1[0] + r2[0] + r3[0] + r4[0];
		result[1] = r1[1] + r2[1] + r3[1] + r4[1];
		return result;
	}
}