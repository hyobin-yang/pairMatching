# [PairMatching]

### 페어 매칭 조건
- 미션을 함께 수행할 페어를 두 명씩 매칭한다.
- 페어 매칭 대상이 홀수인 경우 한 페어는 3인으로 구성한다.
- 같은 레벨에서 이미 페어를 맺은 크루와는 다시 페어로 매칭될 수 없다.


### 페어 매칭 구현 방법
- 크루들의 이름 목록을 List<String> 형태로 준비한다.
- 크루 목록의 순서를 랜덤으로 섞는다. 이 때 `camp.nextstep.edu.missionutils.Randoms`의 shuffle 메서드를 활용해야 한다.
- 랜덤으로 섞인 페어 목록에서 페어 매칭을 할 때 앞에서부터 순서대로 두 명씩 페어를 맺는다.
- 홀수인 경우 마지막 남은 크루는 마지막 페어에 포함시킨다.
  - 짝/홀 여부 검사
- 같은 레벨에서 이미 페어로 만난 적이 있는 크루끼리 다시 페어로 매칭 된다면 크루 목록의 순서를 다시 랜덤으로 섞어서 매칭을 시도한다.
  - 같은 레벨에서의 페어 매칭 정보 순회(각 미션에 레벨 정보가 포함되어 있어야 함)
  - 두(세) 크루가 한 리스트 안에 있는지 확인 contains
- 3회 시도까지 매칭이 되지 않거나 매칭을 할 수 있는 경우의 수가 없으면 에러 메시지를 출력한다.
  - 시도 횟수 count
  - 에러 메시지 출력 -> 재입력


### ERROR 메시지
- 잘못된 입력값: IllegalArgumentException 발생
- [ERROR] 매칭 이력이 없습니다.


### 입출력
1. 기능 종류 출력: "기능을 선택하세요."
2. 기능 선택 입력
   1) 페어 매칭 선택
        - a) 과정, 레벨, 미션 출력
        - b) "과정, 레벨, 미션을 선택하세요." 입력 요청
           - 페어 매칭 정보 X: 페어 매칭 -> 결과 출력
           - 페어 매칭 정보 O: "매칭 정보가 있습니다. 다시 매칭하시겠습니까?"
              - 네: 재매칭 후 결과 출력
              - 아니오: 재입력 요청
        - c) 1.로 회귀
   2) 페어 조회
       - a) 과정, 레벨, 미션 출력
       - b) "과정, 레벨, 미션을 선택하세요." 입력 요청
       - c) 페어 매칭 결과 출력
          - 매칭 정보 O: 결과 출력
          - 매칭 정보 X: "[ERROR] 매칭 이력이 없습니다."
       - d) 1.로 회귀
   3) 페어 초기화 -> 1.로 회귀
   4) 종료(Q): 프로그램 종료


### 입력값 검사
- 기능 선택: Option 클래스 이용, 공백 허용
- 과정, 레벨, 미션 선택: 구분자는 쉼표, 공백 허용(각 문자열 사이 공백 허용 X)
- 대답 입력: Answer 클래스 이용, 공백 허용(각 문자열 사이 공백 허용 X)


### 프로그램 요구 사항
- 입력: 주어지는 Console.readline
- 랜덤값: 주어지는 Randoms.shuffle()
- 크루 이름: 주어지는 파일 read
  - 파일 수정 가능
  - 크루 이름 중복 불가

    