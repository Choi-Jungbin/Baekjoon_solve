import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if(n == 1) {
			System.out.println("CY");
			return;
		}
		boolean[] turn = new boolean[n];
		turn[1] = true;
		for(int i = 3; i < n; i++) {
			if(i%2 == 0) {
				if(!turn[i-1] && !turn[i-3]) turn[i] = true;
			}
			else {
				if(!turn[i-1] || !turn[i-3]) turn[i] = true;
			}
		}
		
		System.out.println(turn[n-1] ? "SK" : "CY");
	}
}