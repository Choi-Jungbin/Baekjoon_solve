#include <iostream>
#include <algorithm>
#include <queue>
#include <unordered_map>
#include <bits/stdc++.h>

using namespace std;

int main() {
	ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    ostringstream sb;
    
    int t;
    cin >> t;
    
    unordered_map<long long, int> m;
    priority_queue<long long> big;
    priority_queue<long long> small;
    for(int test = 0; test < t; ++test){
    	int k;
    	cin >> k;
    	
    	m.clear();
    	m.reserve(k * 2);
    	big = priority_queue<long long>();
    	small = priority_queue<long long>();
    	
    	long long n;
		char c; 
    	for(int i = 0; i < k; ++i){
    		cin >> c >> n;
    		if(c == 'I'){
    			big.push(n);
    			small.push(-n);
    			++m[n];
			}
			else{
				if(n > 0){
					while(!big.empty()){
						if(m.find(big.top()) != m.end() && m[big.top()] > 0) break;
						big.pop();
					}
					if(!big.empty()){
						if(--m[big.top()] == 0) m.erase(big.top());
						big.pop();
					}
				}
				else{
					while(!small.empty()){
						if(m.find(-small.top()) != m.end() && m[-small.top()] > 0) break;
						small.pop();
					}
					if(!small.empty()){
						if(--m[-small.top()] == 0) m.erase(-small.top());
						small.pop();
					}
				}
			}
		}
		
		while (!big.empty() && m[big.top()] == 0) big.pop();
    	while (!small.empty() && m[-small.top()] == 0) small.pop();
    
		if(big.empty()){
			sb << "EMPTY\n";
		}
		else{
			sb << big.top() << " " << -small.top() << "\n";
		}
	}
	
	cout << sb.str();
}