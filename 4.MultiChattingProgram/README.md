# M​u​l​ti​ ​C​ha​tt​in​g ​P​ro​g​ra​m:green_book:

![imge](https://img.shields.io/badge/ProjectType-Lecture-green)  ![imge](https://img.shields.io/badge/Language-Java-yellow)  ![imge](https://img.shields.io/badge/Tools-Eclipse-blue)

## 주요 기술 요소 :computer:

### 객체지향의 개념 적용 :egg:

- 클라이언트와 서버로 메인 프로그램을 분리하고, 프로그램을 구성하는 클래스는 객
  체지향 프로그래밍 기법에 따라 세분화시키고 계층화한다.
-  인터페이스, 추상 클래스, 상속, 메서드 오버로딩, 메서드 오버라이딩 등을 직간접적
  으로 적용하고 구현한다.

### MVC 패턴 도입 

- GUI 프로그램의 기본 프로그래밍 모델인 MVC 패턴을 적용한다.
- MVC 패턴은 안드로이드, iOS 등 스마트폰 프로그래밍에도 적용된다.

### 다양한 프로그래밍 기술 적용 :palm_tree:

- Swing을 이용하여 클라이언트 UI를 구성한다.
- 동시에 여러 사용자가 접속할 수 있도록 멀티 스레드 네트워크 서버를 구현한다.
- 메시지 입력과 수신을 동시에 처리해야 하므로 스레드 기반 클라이언트로 구현한다.
- TCP/IP 소켓을 이용하여 네트워크 프로그래밍을 구현한다.

### 스마트폰 등 모바일 클라이언트 고려

- JSON을 기반으로 메시지를 사용하고, Google Gson parser를 적용한다.
- 클라이언트 프로그램을 안드로이드 버전으로 손쉽게 바꾼 수 있는 구조로 설계한다.

## 기능 정의 :notebook_with_decorative_cover:

- **클라이언트**
  - 로그인 및 로그아웃
  - 대화명 입력 및 표시
  - 채팅 메세지 출력
  - 프로그램 종료
- **서버**
  - 클라이언트 대기 및 연결
  - 다중 클라이언트 채팅 지원
  - 연결된 클라이언트 목록 관리
  - 채팅 메세지 수신 및 브로드캐스팅
  - 로그 출력

## UML 클래스 다이어그램

![image](https://user-images.githubusercontent.com/37828448/71505801-ebe9f900-28c1-11ea-9f6f-d1d109644365.png)

