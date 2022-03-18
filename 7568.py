n = int(input())
l = [list(map(int, input().split())) for _ in range(n)]
count = [1]*n
for i in range(n-1):
    for j in range(i+1, n):
        a = l[i]
        b = l[j]
        if a[0] > b[0] and a[1] > b[1]:
            count[j] += 1
        elif a[0] < b[0] and a[1] < b[1]:
            count[i] += 1
print(" ".join(str(i) for i in count))
