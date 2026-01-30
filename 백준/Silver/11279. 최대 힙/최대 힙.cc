#include <iostream>

using namespace std;

int main(){
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	
	int arr[100001] = {0};
	int cnt = 0;
	int n, a;
	string s;
	s.reserve(2000000);
	cin >> n;
	
	for(int i = 0; i < n; i++){
		cin >> a;
		if(a == 0){
			if(cnt == 0) s += "0\n";
			else{
				// 제일 큰 수 출력 & 정렬 
				s += to_string(arr[1]);
				s += '\n';
				arr[1] = arr[cnt--];
				int c = 1;
				while(c*2 <= cnt){
					int max = 0;
					if(c*2+1 <= cnt){
						max = (arr[c*2] < arr[c*2+1]) ? c*2+1 : c*2;
					}
					else max = c*2;
					if(max != 0 && arr[c] < arr[max]){
						int tmp = arr[c];
						arr[c] = arr[max];
						arr[max] = tmp;
						c = max;
					}
					else break;
				}
			}
		}
		else{
			// 새로운 수 삽입
			arr[++cnt] = a;
			int c = cnt;
			while(c > 1){
				if(arr[c] > arr[c/2]){
					int tmp = arr[c];
					arr[c] = arr[c/2];
					arr[c/2] = tmp;
					c = c/2;
				}
				else break;
			}
		}
	}
	
	cout << s;
	
	return 0;
}