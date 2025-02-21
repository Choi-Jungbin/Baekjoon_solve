# [D3] 햄버거 다이어트 - 5215 

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT) 

### 성능 요약

메모리: 26,752 KB, 시간: 150 ms, 코드길이: 1,375 Bytes

### 제출 일자

2025-02-21 09:18


## 풀이 별
### 완전 탐색
- 메모리: 26,752 kb, 시간: 150 ms
- 그냥 무지성 재귀 돌리기
- n의 크기가 20 밖에 안되서 재귀를 돌려버렸다.

### 1차원 dp
- 메모리: 29,120 kb, 시간: 110 ms
- 메모리는 쪼끔 더 쓰지만 n의 크기가 더 커지면 완탐보다 더 빠를 것이다.
- 뒤에서부터 갱신해서 **중복을 방지**한다!


> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do
