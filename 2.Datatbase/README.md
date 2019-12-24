## Database Programming:green_book:

![imge](https://img.shields.io/badge/ProjectType-Lecture-green)  ![imge](https://img.shields.io/badge/Language-Java-yellow)  ![imge](https://img.shields.io/badge/Tools-IntelliJ-blue)

![imge](https://img.shields.io/badge/Language-SQL-orange)![imge](https://img.shields.io/badge/Tools-MySQL-blue)

### 데이터베이스:books:

- 여러사람이 공유할 목적으로 방대한 데이터를 체계적으로 정리하여 저장한 것으로, 이를 이용하면 데이터를 효율적으로 관리하고 검색할 수 있다.

### DBMS (DataBase Management System) :computer:

- 데이터베이스를 구성하고 운영하는 소프트웨어 시스템으로 오라클,MS SQL 서버, MySQL등 데이터 베이스 제품을 말한다.

### 데이터베이스 테이블:bar_chart:

- 테이블 : 데이터를 공통 속성으로 묶고 분류하여 기록한 형태
  - Column or Field: 테이블에서 데이터를 구별하는 속성. (ex. 이름, 학교, 학번)
  - Row or Record : 한 줄 단위의 데이터 집합. (ex. 최재성, 세종대학교, 16013087)
- Column 이나 Row의 순서는 의미가 없다.
- Row의 데이터는 하나만 표시할 수 있고, 그룹이나 배열은 허용되지 않는다.
- 데이터베이스 키
  - 키 : 데이터베이스를 다른 데이터와 구분할 수 있는 고유정보.
  - 주 키 : 테이블 하나에 키가 여러 개일 수 있는데, 그 중 절대적인 구분되는 키(Primary Key)
  - 외래 키 : 테이블 간의 관계를 나타냄.

### SQL의 개요:page_facing_up:

- DDL : Data Definition Language (Create, Alter, Drop Table)
- DML : Data Manipulation Language (Select, Insert, Delete, Updata)

```sql
/*Create DATABASE 데이터베이스명 default CHARACTER SET UTF8*/
Create DATABASE school default CHARACTER SET UTF8; 

/*use 데이터베이스명 데이터베이스 선택*/
use school;
/*Create Table 테이블 이름()*/
/*컬럼이름 데이터형 옵션*/
CREATE TABLE MEMBER(
	id Int NOT NULL Primary Key,
    username VARCHAR(20), /*20글자 까지 가능*/
    dept VARCHAR(7),
    birth DATE
);
/*Drop TABLE 테이블 이름*/
DROP TABLE MEMBER;
/*INSERT INTO 테이블 이름(삽입할 컬럼 이름...) VALUES(컬럼에 넣을 값)*/
INSERT INTO MEMBER VALUES(00001,'최재성','컴퓨터공학과','1995-02-09');
/*SELECT 컬럼 이름 FROM 테이블 이름 WHERE 조건*/
SELECT * FROM member;
SELECT * FROM member WHERE id = 00001;
/*DELETE FROM 테이블 이름 WHERE 조건*/
DELETE FROM member WHERE id = 00001 AND username = '최재성';
/*UPDATE 테이블 이름 SET 컬럼이름 = 수정할 값, 컬럼이름 = 수정 값 .. WHERE 조건*/
UPDATE member SET id = 00001 WHERE id = 20001;


/*추가 기능*/
/*DESC 테이블 : 테이블 구조가 잘 만들어졌는지 확인*/
DESC MEMBER; 
```

- 옵션에 들어가는 종류는 이렇다
  - PRIMARY KEY : 하나의 테이블에 하나의 기본키만 정할 수 있다.
  - UNIQUE KEY : 식별하기 위한 키로 기본 키가 아니기 때문에 NULL을 넣을 수 있다.
  - NOT NULL : NULL 입력 금지.
  - CHECK : 입력할 수 있는 값의 범위를 지정한다. 논리식으로 지정한다.
  - FOREIGN KEY : 외래 키로 다른 테이블과의 관계를 본다.

### JDBC의 개념

- Java DataBase Connectivity의 약어로, 자바에서 데이터베이스 연동 프로그램을 개발하려고 만든 기술.
- JDBC 드라이버는 각 DBMS 제조업체 웹사이트에서 무료로 다운로드.
- MySQL에서는 JDBC Connector를 별도로 다운로드 할 수 있음.

### JDBC 프로그램 개발 절차

1. JDBC 드라이버 로드

   - System.setProperty();

   - Class.forName();

     ```java
     Class.forName("com.mysql.jdbc.Driver"); //클래스이름
     ```

2. 데이터베이스 연결

   - java.sql.Connection;

     ```java
     Connection conn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
     ```

3. Statement 생성

   - java.sql.Statement;

   - java.sql.PreparedStatement;

     ```java
     PreparedStatement pstmt = conn.prepareStatement("insert into test values(?,?)");
     pstmt.setString(1, getUsername());
     pstmt.setString(2, getEmail());
     pstmt.executeUpdate();
     ```

4. SQL문 전송

   - java.sql.Statement

     - executeQuery()

     - executeUpdate()

       ```java
       int count = pstmt.executeUpdate();
       pstmt.executeUpdate();
       ```

5. 결과 받기

   - java.sql.ResultSet

6. 연결 해제

   - java.sql.Connection -> close()

---

### IntelliJ 에서 MySQL 사용법

1. JDBC 설치

   [MySQL사이트](https://dev.mysql.com/downloads/connector/j/) 에 들어가서 "Looking for Previous GA versions?" 를 누른다.

   다음 **Platform Independent (Architecture Independent), Compressed TAR Archive**를 다운 받는다.

   압축 파일에서 **mysql-connector-java-5.1.47-bin.jar** 파일만 압축을 풀어준다.(버전이 다를 수 있음)

2. IntelliJ 연결

   Project Structure에 들어가 Library 항목에 추가한다.

---

참고한 곳

[제약조건 참고](https://hyeonstorage.tistory.com/291)