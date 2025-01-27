import java.util.Scanner;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		permutation(arr, n, m, 0, "", sb);
		System.out.println(sb.toString());
	}
	
	static void permutation(int[] arr, int n, int r, int bit, String s, StringBuilder sb) {
		if(r == 0) {
			sb.append(s).append("\n");
			return;
		}
		for(int i = 0; i < n; i++) {
			if((bit & (1<<i)) == 0) {
				permutation(arr, n, r-1, bit | (1<<i), s + arr[i] + " ", sb);
			}
		}
	}
}