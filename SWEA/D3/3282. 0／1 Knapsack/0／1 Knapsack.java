import java.io.*;
import java.util.*;

public class Solution {	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine().trim());
    	for(int test = 1; test <= T; test++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int n = Integer.parseInt(st.nextToken());
    		int k = Integer.parseInt(st.nextToken());
    		
    		int[][] bag = new int[n+1][k+1];
    		for(int i = 1; i <= n; i++) {
    			st = new StringTokenizer(br.readLine());
    			int v = Integer.parseInt(st.nextToken());
    			int c = Integer.parseInt(st.nextToken());
    			
    			for(int j = 0; j <= k-v; j++) {
    				bag[i][j] = Math.max(bag[i-1][j], bag[i-1][j+v] + c);
    			}
    			for(int j = k-v+1; j <= k; j++) {
    				bag[i][j] = bag[i-1][j];
    			}
    		}
    		
    		System.out.println("#" + test + " " + bag[n][0]);
    	}
    }
}