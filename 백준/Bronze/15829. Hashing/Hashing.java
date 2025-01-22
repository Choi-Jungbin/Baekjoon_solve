import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		long sum = 0;
		long r = 1;
		for(int i = 0; i < n; i++) {
			long c = s.charAt(i) - 96;
			c = c*r % 1234567891;
			sum = (sum + c) % 1234567891;
			r = (r*31) % 1234567891;
		}
		System.out.println(sum%1234567891);
	}
}