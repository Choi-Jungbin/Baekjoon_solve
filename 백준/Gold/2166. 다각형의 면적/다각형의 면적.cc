#include <iostream>
#include <algorithm>
#include <cstdlib>
#include <cmath>

using namespace std;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	int n;
	cin >> n;
	double x, y, x1, y1, prex, prey;
	cin >> x1 >> y1;
	prex = x1;
	prey = y1;
	
	double sum = 0;
	for(int i = 1; i < n; i++){
		cin >> x >> y;
		sum += (x - prex) * (y + prey) / 2;
		prex = x;
		prey = y;
	}
	sum += (x1 - prex) * (y1 + prey) / 2;
	if(sum < 0) sum = -sum;
	cout << fixed;
	cout.precision(1);
	cout << sum;
}