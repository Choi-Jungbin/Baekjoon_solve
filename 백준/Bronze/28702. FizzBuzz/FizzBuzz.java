import java.io.*;

class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = 0;
		for(int i = 0; i < 3; i++) {
			String s = br.readLine();
			if(!s.contains("Fizz") && !s.contains("Buzz")) {
				n = Integer.parseInt(s) + 3-i;
			}
		}
		if(n%15 == 0) bw.write("FizzBuzz");
		else if(n%5 == 0) bw.write("Buzz");
		else if(n%3 == 0) bw.write("Fizz");
		else bw.write(Integer.toString(n));
		bw.flush();
		bw.close();
	}
}