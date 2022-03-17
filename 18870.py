n = int(input())
l = list(map(int, input().split()))
count = {s:i for i, s in enumerate(sorted(list(set(l))))}
print(" ".join(str(count[i]) for i in l))
