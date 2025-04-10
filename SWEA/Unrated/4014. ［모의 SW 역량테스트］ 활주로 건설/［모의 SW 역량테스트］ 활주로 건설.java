import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine().trim());
    	for(int test = 1; test <= T; test++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int n = Integer.parseInt(st.nextToken());
    		int x = Integer.parseInt(st.nextToken());
    		
    		int count = 0;
    		int[][] ground = new int[n][n];
    		// 현재 높이
    		int high = 0;
    		// 현재 높이가 지속된 길이
    		int len = 0;
    		// 높아졌다 낮아졌냐
    		boolean reverse = false;
    		// 이거 성공임?
    		boolean check = true;
    		for(int i = 0; i < n; i++) {
    			st = new StringTokenizer(br.readLine());
    			for(int j = 0; j < n; j++) {
    				ground[i][j] = Integer.parseInt(st.nextToken());
    				if(!check) continue;
    				if(j == 0) {
    					high = ground[i][j];
    					len = 1;
    				}
    				else {
    					// 높이가 같을 때
    					if(high == ground[i][j]) {
    						len++;
    						// 전 단계 경사로 세울 수 있을 때
    						if(reverse && len >= x) {
								reverse = false;
								len = 0;
    						}
    					}
    					// 낮은 곳에서 높은 곳으로 왔을 때
    					else if(high - ground[i][j] == -1) {
    						// 충분한 길이를 가지고 있지 않다면 탈락
    						if(reverse || len < x) {
    							check = false;
    							continue;
    						}
    						len = 1;
    						high = ground[i][j];
    					}
    					// 높은 곳에서 낮은 곳으로 왔을 때
    					else if(high - ground[i][j] == 1) {
    						// 아직 전 단계 경사로도 못 세웠을 때
    						if(reverse) {
    							check = false;
    							continue;
    						}
    						reverse = true;
    						high = ground[i][j];
    						len = 1;
    					}
    					else check = false;
    				}
    			}
    			if(!reverse && check) {
    				count++;
    			}
    			check = true;
    			reverse = false;
    		}
    		
    		for(int i = 0; i < n; i++) {
    			high = ground[0][i];
    			len = 0;
    			for(int j = 0; j < n; j++) {
    				if(high == ground[j][i]) {
    					len++;
    					if(reverse && len >= x) {
    						reverse = false;
    						len = 0;
    					}
    				}
    				else if(high - ground[j][i] == -1) {
    					if(reverse || len < x) {
    						check = false;
    						break;
    					}
    					len = 1;
    					high = ground[j][i];
    				}
    				else if(high - ground[j][i] == 1) {
    					if(reverse) {
    						check = false;
    						break;
    					}
    					reverse = true;
    					high = ground[j][i];
    					len = 1;
    				}
    				else {
    					check = false;
    					break;
    				}
    			}
    			if(!reverse && check) {
    				count++;
    			}
    			check = true;
    			reverse = false;
    		}
    		
    		System.out.println("#" + test + " " + count);
    	}
    }
}