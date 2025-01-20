import java.io.*;

class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] lv = new int[31];
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			lv[num] += 1;
		}
		int cut = (int) Math.round(n*0.15);
		n -= cut;
		int sum = 0;
		int count = 0;
		for(int i = 1; i <= 30; i++) {
			if(count < cut) {
				count += lv[i];
				if(count > cut) {
					sum += (count-cut)*i;
				}
			}
			else if(count < n) {
				count += lv[i];
				sum += lv[i]*i;
				if(count > n) {
					sum -= (count-n)*i;
				}
			}
		}
		System.out.println(Math.round(1.0*sum/(n-cut)));
	}
}