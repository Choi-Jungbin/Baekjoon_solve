import java.io.*;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		int n = a.length();
		int m = b.length();
		int[][] map = new int[n+1][m+1];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(a.charAt(i) == b.charAt(j)) map[i+1][j+1] = map[i][j] + 1;
				else map[i+1][j+1] = Math.max(map[i][j+1], map[i+1][j]);
			}
		}
		System.out.println(map[n][m]);
	}
}