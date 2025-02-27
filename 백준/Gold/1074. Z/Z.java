import java.util.*;
import java.io.*;

public class Main {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		// r < Math.pow(2, n-1) and c < Math.pow(2, n-1)이면 Math.pow(2, 2*n-2)
		int result = 0;
        // 가장 작은 z가 될 때까지 반복
		while(n > 0) {
            //  왼쪽 위
			double a = Math.pow(2, n-1);
			if(r < a && c < a) {
				result += 0;
			}
            // 오른쪽 위
			else if(r < a && c >= a) {
				result += Math.pow(2, 2*n-2);
				c -= a;
			}
            // 왼쪽 아래
			else if(r >= a && c < a) {
				result += Math.pow(2, 2*n-2) * 2;
				r -= a;
			}
            // 오른쪽 아래
			else if(r >= a && c >= a){
				result += Math.pow(2, 2*n-2) * 3;
				r -= a;
				c -= a;
			}
			--n;
		}
		System.out.println(result);
	}
}
