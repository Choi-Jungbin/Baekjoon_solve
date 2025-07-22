#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    string str1, str2;
    cin >> str1 >> str2;
    
    int n = str1.length();
    int m = str2.length();
    
    int arr[n+1][m+1] = {};
    
    for(int i = 0; i < n; i++){
    	for(int j = 0; j < m; j++){
    		if(str1.at(i) == str2.at(j)) arr[i+1][j+1] = arr[i][j] + 1;
    		else arr[i+1][j+1] = max(arr[i][j+1], arr[i+1][j]);
		}
	}
	
	cout << arr[n][m] << "\n";
	if(arr[n][m] != 0){
		string s = "";
		int cnt = arr[n][m];
		int i = n;
		int j = m;
		while(cnt > 0){
			if(cnt == arr[i-1][j]) --i;
			else if(cnt == arr[i][j-1]) --j;
			else{
				--i;
				--j;
				s.push_back(str1.at(i));
				--cnt;
			}
		}
		string r(s.rbegin(), s.rend());
		cout << r;
	}
}