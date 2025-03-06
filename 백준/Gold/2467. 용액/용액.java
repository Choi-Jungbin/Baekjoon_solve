import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 용액을 작은 수부터 정렬
		Arrays.sort(arr);
		
		int start = 0;
		int end = n-1;
		int mix = arr[start] + arr[end];
		int min = mix;
		int[] solution = {arr[start], arr[end]};
		while(start < end) {
			mix = arr[start] + arr[end];
			// 절대값으로 0과 가까운 용액 판별
			if(Math.abs(min) > Math.abs(mix)) {
				min = mix;
				solution[0] = arr[start];
				solution[1] = arr[end];
			}
			// 0이면 더 탐색하지 않고 나가기
			if(mix == 0) break;
			// 합이 0보다 크면 산성 용액 줄이기
			if(mix > 0) end--;
			// 합이 0보다 작으면 알카리 용액 줄이기
			else start++;
		}
		
		System.out.println(solution[0] + " " + solution[1]);
	}
}
