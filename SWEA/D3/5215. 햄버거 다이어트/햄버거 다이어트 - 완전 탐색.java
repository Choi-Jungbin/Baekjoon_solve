import java.io.*;
import java.util.*;

class Ingredient implements Comparable<Ingredient>{
	int cal;
	int score;
	
	Ingredient(int cal, int score){
		this.cal = cal;
		this.score = score;
	}
	
	@Override
	public int compareTo(Ingredient o) {
		if(this.cal == o.cal) return Integer.compare(this.score, o.score);
		return Integer.compare(this.cal, o.cal);
	}
}

public class Solution {
	static int n;
	static int l;
	static int max;
	static List<Ingredient> list = new ArrayList<Ingredient>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			String[] s = br.readLine().split(" ");
			n = Integer.parseInt(s[0]);
			l = Integer.parseInt(s[1]);
			
			list.clear();
			for(int i = 0; i < n; i++) {
				s = br.readLine().split(" ");
				list.add(new Ingredient(Integer.parseInt(s[1]), Integer.parseInt(s[0])));
			}
			
			list.sort(null);
			max = 0;
			calc(0,0,0);
			System.out.println("#" + test + " " + max);
		}
	}
	
	static void calc(int start, int score, int sum) {
		if(score > max) max = score;
		if(start == n) return;
		for(int i = start; i < n; i++) {
			if(sum + list.get(i).cal > l) break;
			calc(i+1, score + list.get(i).score, sum + list.get(i).cal);
		}
	}
}
