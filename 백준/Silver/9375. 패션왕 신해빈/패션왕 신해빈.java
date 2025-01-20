import java.io.*;
import java.util.Map;
import java.util.HashMap;

class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			Map<String,Integer> map = new HashMap<>();
			for(int i = 0; i < n; i++) {
				String[] s = br.readLine().split(" ");
				if(map.containsKey(s[1])) map.put(s[1], map.get(s[1])+1);
				else map.put(s[1], 1);
			}
			int num = 1;
			for(String s : map.keySet()) {
				num *= map.get(s)+1;
			}
			System.out.println(num-1);
		}
	}
}