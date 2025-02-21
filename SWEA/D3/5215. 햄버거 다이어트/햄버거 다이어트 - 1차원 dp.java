import java.io.*;
import java.util.*;
 
class Ingredient{
    int cal;
    int score;
     
    Ingredient(int cal, int score){
        this.cal = cal;
        this.score = score;
    }
}
 
public class Solution {
    static int n;
    static int l;
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
             
            int[] dp = new int[l+1];
            for(Ingredient ing : list) {
                for(int j = l; j >= ing.cal; j--) {
                    dp[j] = Math.max(dp[j], dp[j-ing.cal] + ing.score);
                }
            }
             
            System.out.println("#" + test + " " + dp[l]);
        }
    }
}
