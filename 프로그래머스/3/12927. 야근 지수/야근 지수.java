import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> que = new PriorityQueue(Collections.reverseOrder());
        for(int i : works){
            que.offer(i);
        }
        
        while(n > 0 && !que.isEmpty()){
            int q = que.poll() - 1;
            if(q > 0) que.offer(q);
            n--;
        }
        
        if(que.isEmpty()) return 0;
        while(!que.isEmpty()){
            int q = que.poll();
            answer += q*q;
        }
        return answer;
    }
}