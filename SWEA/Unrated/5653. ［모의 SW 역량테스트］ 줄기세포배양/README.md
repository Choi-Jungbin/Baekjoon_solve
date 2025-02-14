# [Unrated] [모의 SW 역량테스트] 줄기세포배양 - 5653 

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRJ8EKe48DFAUo) 

### 성능 요약

메모리: 123,180 KB, 시간: 906 ms, 코드길이: 2,600 Bytes

### 제출 일자

2025-02-14 10:43



> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do


### 했던 실수
- 처음에 Cell 자체에 Comparable을 구현하면서 dead 우선순위 큐에서의 정렬을 신경 못 씀
- 배양 배지가 무한하니 세포는 항상 늘어나기만 하기 때문에 while문의 조건에 time < k 만 쓰면 되는데 현재 비활성화 된 세포가 있는지 체크하는 조건도 추가해버림
  => 죽지 않은 모든 세포가 활성화되는 경우가 있음
