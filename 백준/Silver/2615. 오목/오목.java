import java.io.*;

public class Main {
	static boolean check(int x, int y, int n) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[19][19];
		for (int i = 0; i < 19; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
		// / 방향, - 방향, \ 방향, | 방향
		int[][] dir = {{-1,1},{0,1},{1,1},{1,0}};
		boolean[][][] visit = new boolean[4][19][19];
		for(int j = 0; j < 19; j++) {
			for(int i = 0; i < 19; i++) {
				int check = map[i][j];
				if(check != 0) {
					for(int m = 0; m < 4; m++) {
						int x = i;
						int y = j;
						int len = 0;
						while(check(x,y,19) && map[x][y] == check && !visit[m][x][y]) {
							visit[m][x][y] = true;
							len++;
							x += dir[m][0];
							y += dir[m][1];
						}
						if(len == 5) {
							System.out.println(check);
							System.out.println((i+1) + " " + (j+1));
							return;
						}
					}
				}
			}
		}
		System.out.println(0);
	}
}