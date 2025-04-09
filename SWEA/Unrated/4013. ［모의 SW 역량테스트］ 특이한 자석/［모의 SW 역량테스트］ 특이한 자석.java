import java.io.*;
import java.util.*;

public class Solution {
	static int[] join = {5,1};
	static int[] magnet = new int[4];
	
	// 같이 돌아가는 자석 찾기
	static boolean check(int m) {
		return (((magnet[m] >> join[0])^(magnet[m+1] >> join[1])) & 1) == 1;
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine().trim());
    	for(int test = 1; test <= T; test++) {
    		int k = Integer.parseInt(br.readLine());
    		for(int i = 0; i < 4; i++) {
    			StringTokenizer st = new StringTokenizer(br.readLine());
    			int n = 0;
    			for(int j = 0; j < 8; j++) {
    				n <<= 1;
    				n += Integer.parseInt(st.nextToken());
    			}
    			magnet[i] = n;
    		}
			
    		for(int i = 0; i < k; i++) {
    			StringTokenizer st = new StringTokenizer(br.readLine());
    			int m = Integer.parseInt(st.nextToken()) - 1;
    			// dir이 true면 시계 방향
    			boolean dir = st.nextToken().equals("1");
    			// 왼쪽 먼저 체크
    			left(m, dir);
    			// 오른쪽에서도 돌리기 때문에 한 번 되돌리기
    			turn(m, !dir);
    			right(m, dir);
    		}
    		
    		int sum = 0;
    		for(int i = 0; i < 4; i++) {
    			if(((magnet[i]>>7) & 1) == 1) sum += 1 << i;
    		}
    		
    		System.out.println("#" + test + " " + sum);
    	}
    }
    
    // 왼쪽으로 전파
    static void left(int m, boolean dir) {
    	if(m > 0 && check(m-1)) {
    		left(m-1, !dir);
    	}
    	turn(m, dir);
    }
    
    // 오른쪽으로 전파
    static void right(int m, boolean dir) {
    	if(m < 3 && check(m)) {
    		right(m+1, !dir);
    	}
    	turn(m, dir);
    }
    
    // 자석 돌리기
    static void turn(int m, boolean dir) {
    	if(dir) {
    		magnet[m] |= (magnet[m] & 1) << 8;
    		magnet[m] >>= 1;
    	}
    	else {
    		int b = (magnet[m] >> 7) & 1;
    		magnet[m] <<= 1;
    		magnet[m] &= (1 << 8) - 1;
    		magnet[m] |= b;
    	}
    }
}