import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int rest = 0;
		for(int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			// 자리 다 찼는데 버팅기는 경우
			// 최대값이 갑자기 작아지는 경우
			// 1씩 증가해야 하는데 점프하는 경우
			if(i/p >= n || rest > n || rest+1 < n) {
				System.out.println("NO");
				return;
			}
			rest = n;
		}
		System.out.println((N-1)/p+1 == rest ? "YES" : "NO");
	}
}
