## Product Manager:green_book:

![imge](https://img.shields.io/badge/ProjectType-Lecture-green)  ![imge](https://img.shields.io/badge/Language-Java-yellow)  ![imge](https://img.shields.io/badge/Tools-IntelliJ-blue)

![imge](https://img.shields.io/badge/Language-SQL-orange) ![imge](https://img.shields.io/badge/Tools-MySQL-blue)

2019 - 12 - 24 (Merry Christmas! ) :christmas_tree:

![image](https://user-images.githubusercontent.com/37828448/71410869-54e72c00-268a-11ea-962c-ab18794c27f8.png)

### 소개:books:

- Mysql 과 Java의 연동을 공부하기 위해 만든 프로젝트.
- 간단하게 상품을 입력하고 조회하고 삭제할 수 있는 GUI Project.

### 사용기법 :construction_worker:

MVC 패턴을 적용했다.(View 와 Controller가 완벽히 분리됨.)

Model은 DAO를 통해 MySQL을 통해 전달받는다.

비즈니스 로직은 Controller에 있다.

데이터 구조는 ProductDTO를 통해 관리한다.

### 클래스 소개 :v:

- ProductMain : main함수를 갖고있다. ProductView를 호출한다.
- ProductView : GUI를 구성하는 클래스. 
- ProductController : 비즈니스로직을 갖고 있는 클래스, 계산 및 정보처리 담당.
- ProductDAO : MySQL과 통신을 하기 위한 클래스.
- ProductDTO : 데이터 구조를 저장하기 위한 클래스.
- Constants : 상수를 갖고 있는 클래스.

### 핵심 코드

#### MySQL 통신

- 통신하는 클래스는 한개만 객체가 만들어져 객체가 생성될때 MySQL과 연결되며 프로그램이 끝날때까지 닫지 않는다.

  ```java
  Connection conn = null; //DB 연결 상태를 갖는 객체.
  PreparedStatement pstm = null; //SQL문을 나타내는 객체
  //생성자를 통해 바로 연결을 유지한다.
  public ProductDAO(){
      try {
          Class.forName(Constants.DATABASECLASS); //클래스이름
          conn = DriverManager.getConnection(Constants.DATABASEURL, Constants.USER_NAME, Constants.PASSWORD);
          System.out.println("연결 성공");
      } catch (SQLException e) {
          e.printStackTrace();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
  }
  ```

- 통신 쿼리는 정해져 있기 때문에 상수 선언했다.

  ```java
  static final String INSERTQUARY = "INSERT INTO PRODUCT VALUES(0,?,?,?)"; 
  static final String ALLGETQUARY = "SELECT * FROM product";
  static final String SELECTITEMQUARY = "SELECT * FROM product WHERE prcode = ?";
  static final String DELETEQUARY = "DELETE FROM product WHERE prcode = ?";
  static final String UPDATEQUARY = "UPDATE product SET prname = ?, price = ?, manufacture = ? WHERE prcode = ?";
  ```
  