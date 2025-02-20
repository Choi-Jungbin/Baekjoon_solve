import java.io.*;
import java.util.*;
 
public class Solution {
    static int size = 100;
    static boolean check(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int test = 1; test <= 10; test++) {
            br.readLine();
            boolean[][] map = new boolean[size][size];
            int x = size-1;
            int y = 0;
            for(int i = 0; i < size; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < size; j++) {
                    int a = Integer.parseInt(st.nextToken());
                    if(a > 0) {
                        map[i][j] = true;
                        if(a == 2) y = j;
                    }
                }
            }
             
            while(x > 0) {
                x--;
                if(check(x,y-1) && map[x][y-1]) {
                    while(check(x,--y) && map[x][y]);
                    y++;
                }
                else if(check(x,y+1) && map[x][y+1]) {
                    while(check(x,++y) && map[x][y]);
                    y--;
                }
            }
             
            System.out.println("#" + test + " " + y);
        }
    }
}