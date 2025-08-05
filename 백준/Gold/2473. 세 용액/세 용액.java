import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] arr = new long[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr);
		
		long min = Long.MAX_VALUE;
		long[] result = new long[] {arr[0], arr[1], arr[2]};
		if(arr.length == 3) {
			System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
			return;
		}
		
		int left, right;
		for(int i = 0; i < n-2; i++) {
			left = i + 1;
			right = n - 1;
			long a = arr[i] + arr[left] + arr[right];
			while(left < right) {
				if(a == 0) {
					System.out.println(arr[i] + " " + arr[left] + " " + arr[right]);
					return;
				}
				if(min > Math.abs(a)) {
					result[0] = arr[i];
					result[1] = arr[left];
					result[2] = arr[right];
					min = Math.abs(a);
				}
				if(a > 0) right--;
				else left++;
				a = arr[i] + arr[left] + arr[right];
			}
		}
		System.out.println(result[0] + " " + result[1] + " " + result[2]);
	}
}