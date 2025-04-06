import java.io.*;
import java.util.*;
 
public class Solution {
    static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
     
    static boolean check(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test = 1; test <= T; test++) {
            String[] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            int[][] map = new int[n][n];
            List<int[]> highest = new ArrayList<>();
            int high = 0;
            for(int i = 0; i < n; i++) {
                str = br.readLine().split(" ");
                for(int j = 0; j < n; j++) {
                    int a = Integer.parseInt(str[j]);
                    // 가장 높은 산봉우리 찾기
                    if(a > high) {
                        high = a;
                        highest.clear();
                        highest.add(new int[] {i,j});
                    }
                    else if(a == high) {
                        highest.add(new int[] {i,j});
                    }
                    map[i][j] = a;
                }
            }
             
            int max = 0;
            boolean[][] visit = new boolean[n][n];
            for(int[] h : highest) {
                max = Math.max(max, dfs(h[0], h[1], 1, high, false, n, k, visit, map));
            }
            System.out.println("#" + test + " " + max);
        }
    }
     
    static int dfs(int hx, int hy, int len, int h, boolean dig, int n, int k, boolean[][] visit, int[][] map) {
        int max = len;
        // 방문한 곳 표시
        visit[hx][hy] = true;
        for(int[] d : dir) {
            int x = hx + d[0];
            int y = hy + d[1];
            // 아직 안 간 곳 방문
            if(check(x,y,n) && !visit[x][y]) {
                // 봉우리가 현재 위치보다 낮을 때
                if(map[x][y] < h) {
                    max = Math.max(max, dfs(x, y, len+1, map[x][y], dig, n, k, visit, map));
                }
                // 봉우리가 현재 위치보다 같거나 높을 때 산을 안 깎았다면
                else if(!dig){
                    // 산 깎기
                    for(int i = 1; i <= k; i++) {
                        if(map[x][y]-i < h) {
                            max = Math.max(max, dfs(x, y, len+1, map[x][y]-i, true, n, k, visit, map));
                        }
                    }
                }
            }
        }
        visit[hx][hy] = false;
         
        return max;
    }
}