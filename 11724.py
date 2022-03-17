n, m = map(int, input().split())
line = [[0]*n for _ in range(n)]
for _ in range(m):
    a, b = map(int, input().split())
    line[a-1][b-1] = 1
    line[b-1][a-1] = 1
for i in line:
    print(i)

