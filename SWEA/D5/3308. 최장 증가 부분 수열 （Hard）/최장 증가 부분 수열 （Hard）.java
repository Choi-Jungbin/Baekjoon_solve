import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine().trim());
    	for(int test = 1; test <= T; test++) {
    		int n = Integer.parseInt(br.readLine());
    		int[] arr = new int[n];
    		int len = 0;
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for(int i = 0; i < n; i++) {
    			int k = Integer.parseInt(st.nextToken());
    			
    			int high = len;
    			int low = 0;
    			int mid;
    			while(low < high) {
    				mid = (high+low) / 2;
    				if(arr[mid] > k) {
    					high = mid;
    				}
    				else {
    					low = mid + 1;
    				}
    			}
    			arr[low] = k;
    			if(low == len) len++;
    		}
    		
    		System.out.println("#" + test + " " + len);
    	}
    }
}