# kdh 도서 관리 시스템

콘솔에서 도서를 **등록·조회·수정·삭제**하고, 제목으로 **검색**하거나 분류로 **필터링**하는
자바 프로그램입니다. 자바 17로 작업했습니다.

소스 5개 파일 / 440줄.

---

## 1. 실행 방법

### IntelliJ

1. 이 폴더를 IntelliJ 로 엽니다.
2. `src/src/main/java` 를 오른쪽 클릭 → **Mark Directory as → Sources Root**.
3. `com.kdh.library.MainApplication` 의 `main` 왼쪽 실행 단추(▶)를 누릅니다.

`src/src/main/java` 를 Sources Root 로 지정하지 않으면 IntelliJ 가 `package com.kdh.library`
선언과 폴더 위치가 어긋난다고 보고 실행 단추를 띄우지 않습니다.

### 명령줄

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java  -Dfile.encoding=UTF-8 -cp out com.kdh.library.MainApplication
```

---

## 2. 메뉴

```
===== kdh 도서 관리 =====
1. 등록
2. 전체 조회
3. 제목 검색
4. 분류 조회
5. 수정
6. 삭제
7. 종료
```

실행하면 도서 6권이 미리 등록된 상태로 시작합니다. 켜자마자 조회와 검색을 확인할 수 있습니다.

---

## 3. 구조

```
src/src/main/java/com/kdh/library/
├── MainApplication.java          메인 어플리케이션
├── entity/
│   ├── Book.java                 서적 정보
│   └── Category.java             서적 분류 (문학·인문·사회·과학기술·예술·실용)
├── repository/
│   └── BookStore.java            서적 조회
└── view/
    └── ConsoleUI.java            입력/출력
```

각 기능별로 클래스를 나누었습니다.

| 클래스 | 하는 일 | 하지 않는 일 |
|---|---|---|
| `Book` | 책 한 권의 값을 담는다 | 화면 출력, 저장 |
| `Category` | 정해진 분류 6개를 표현하고 번호를 분류로 바꿔 준다 | 그 외 전부 |
| `BookStore` | 담아두고 찾고 지운다. 기본 장서가 무엇인지도 안다 | 화면 출력, 키보드 입력 |
| `ConsoleUI` | 찍고 입력받는다 | 데이터를 고치는 일 |
| `MainApplication` | 객체를 만들어 잇고 메뉴를 돌린다 | 직접 출력, 직접 목록을 뒤지는 일, 어떤 데이터로 시작할지 정하는 일 |

예를 들어 "도서 등록"을 골랐을 때 어떻게 작동하는지 보면 이렇습니다.

```
사용자 ──"1"──▶ MainApplication
                     │
                     ├─▶ ConsoleUI.inputRequiredText      ─── 제목을 받는다
                     ├─▶ ConsoleUI.selectCategory(...)      ─── 분류를 받는다
                     │      └ 숫자가 아니거나 범위 밖이면 여기서 다시 물어본다
                     ├─▶ BookStore.add(제목, 분류)          ─── 번호를 매겨 목록에 넣는다
                     └─▶ ConsoleUI.showMessage("등록되었습니다.")
```
