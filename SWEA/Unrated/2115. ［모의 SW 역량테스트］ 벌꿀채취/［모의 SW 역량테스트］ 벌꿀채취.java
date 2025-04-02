import java.io.*;
import java.util.*;

public class Solution {
	static int n, m, c;
	static int max;
	static int[][] bee;
	static int[][] honey;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	for(int test = 1; test <= T; test++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		n = Integer.parseInt(st.nextToken());
    		m = Integer.parseInt(st.nextToken());
    		c = Integer.parseInt(st.nextToken());
    		
    		bee = new int[n][n];
    		for(int i = 0; i < n; i++) {
    			st = new StringTokenizer(br.readLine());
    			for(int j = 0; j < n; j++) {
    				bee[i][j] = Integer.parseInt(st.nextToken());
    			}
    		}
    		
    		// 해당 위치에서 가능한 최대 꿀 채취 먼저 저장
    		// 크기 c에 맞게 따로 잘라야  함
    		honey = new int[n][n-m+1];
    		for(int i = 0; i < n; i++) {
    			for(int j = 0; j <= n-m; j++) {
    				honey[i][j] = knap(i, j);
    			}
    		}
    		
    		max = 0;
    		for(int i = 0; i < n; i++) {
    			for(int j = 0; j <= n-m; j++) {
    				calc(i,j);
    			}
    		}
    		
    		System.out.println("#" + test + " " + max);
    	}
    }
    
    // 범위 내에서 가장 많은 꿀통 채취
    static int knap(int x, int y) {
    	int[][] dp = new int[m+1][c+1];
    	for(int i = 1; i <= m; i++) {
    		int w = bee[x][y+i-1];
    		for(int j = c; j >= 0; j--) {
    			// 현재 무게가 들어갈 경우
    			if(j >= w) {
    				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w] + w*w);
    			}
    			// 들어가지 않을 경우
    			else {
    				dp[i][j] = dp[i-1][j];
    			}
    		}
    	}
    	return dp[m][c];
    }
    
    // 벌꿀 최대값 계산
    static void calc(int x, int y) {
    	int h = honey[x][y];
    	if(y+2*m <= n) {
	    	for(int i = y+m; i <= n-m; i++) {
	    		max = Math.max(max, h + honey[x][i]);
	    	}
    	}
    	for(int i = x+1; i < n; i++) {
    		for(int j = 0; j <= n-m; j++) {
    			max = Math.max(max, h + honey[i][j]);
    		}
    	}
    }
}