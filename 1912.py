n = int(input())
num = list(map(int, input().split()))

series = []
for i in range(n):
    a = num[i]
    if i:
        c = series[i-1] + a
        series.append(c if c > a else a)
    else:
        series.append(a)
print(max(series))
