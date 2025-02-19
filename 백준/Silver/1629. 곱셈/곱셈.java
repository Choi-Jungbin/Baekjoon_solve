import java.io.*;

public class Main {
	static int a;
	static int c;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		a = Integer.parseInt(s[0]);
		int b = Integer.parseInt(s[1]);
		c = Integer.parseInt(s[2]);
		
		System.out.println((int) calc(b));
	}
	
    // double로 하면 부동 소수점 때문에 틀림
	static long calc(int b) {
		if(b == 1) return a % c;
		long result = calc(b/2);
		result *= result;
		result %= c;
		if(b%2 == 1) result *= calc(1);
		return result % c;
	}
}
