import java.sql.*;

public class DatabaseMain {
    public static void main(String arg[]){
        Connection con = null;

        final String server = "localhost"; // MySQL 서버 주소
        final String database = "school"; // MySQL DATABASE 이름
        final String user_name = "root"; //  MySQL 서버 아이디
        final String password = ""; // MySQL 서버 비밀번호

        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2.연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("연결성공");
            //Statement 생성
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM member;");
            //SQL문 전송 및 결과 받기
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) { //데이터가 있는 만큼 받는다.
                String name = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
                System.out.println(name);
            }
            //연결 해제
            rs.close();
            con.close();
            System.out.println("연결해제");
        } catch(SQLException e) {
            System.out.println("연결실패 : " + e.getErrorCode());
            e.printStackTrace();
        }
    }
}
