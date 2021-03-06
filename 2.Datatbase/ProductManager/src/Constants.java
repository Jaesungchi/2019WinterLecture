/*
상수를 저장해둔 클래스
 */
public class Constants {

    static final String SERVER = "localhost"; // MySQL 서버 주소
    static final String DATABASE = "school"; // MySQL DATABASE 이름
    static final String USER_NAME = "root"; //  MySQL 서버 아이디
    static final String PASSWORD = ""; // MySQL 서버 비밀번호
    static final String DATABASECLASS = "com.mysql.jdbc.Driver"; //DATABASE Library 연결 주소
    static final String DATABASEURL = "jdbc:mysql://" + Constants.SERVER + "/" + Constants.DATABASE + "?useSSL=false&characterEncoding = UTF-8&serverTimezone=UTC"; //Database URL

    //Quary를 정의한 상수
    static final String INSERTQUARY = "INSERT INTO PRODUCT VALUES(0,?,?,?)"; //
    static final String ALLGETQUARY = "SELECT * FROM product";
    static final String SELECTITEMQUARY = "SELECT * FROM product WHERE prcode = ?";
    static final String DELETEQUARY = "DELETE FROM product WHERE prcode = ?";
    static final String UPDATEQUARY = "UPDATE product SET prname = ?, price = ?, manufacture = ? WHERE prcode = ?";

    //문자로 들어가는 상수들
    static final String NEWBTN = "등록";
    static final String VIEWBTN = "조회";
    static final String DELBTN = "삭제";
    static final String ALL = "전체";
}
