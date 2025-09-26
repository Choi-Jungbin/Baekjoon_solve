import java.io.*;
import java.util.*;

public class Main {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n, k;
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		for(int i = 0; i < k; i++) {
			sum += arr[i];
		}
		
		int max = sum;
		for(int i = k; i < n; i++) {
			sum += arr[i] - arr[i-k];
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
}