import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class ProductDAO {
    Connection conn = null; //DB 연결 상태를 갖는 객체.
    String quary = ""; //쿼리문
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
    //데이터를 ADD 하는 메소드
    public void addData(ProductDTO product){
        try {
            quary = Constants.INSERTQUARY;
            pstm = conn.prepareStatement(quary);

            pstm.setString(1,product.getPrname());
            pstm.setString(2, String.valueOf(product.getPrice()));
            pstm.setString(3,product.getManufacture());

            int sucess = pstm.executeUpdate();
            if(sucess > 0)
                System.out.println(product.getPrname() + " 입력 성공");
            else
                System.out.println(product.getPrname() + "입력 실패");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //데이터를 지우는 메소드
    public void deleteData(int prcode){
        try {
            quary = Constants.DELETEQUARY;
            pstm = conn.prepareStatement(quary);
            pstm.setString(1, String.valueOf(prcode));

            int sucess = pstm.executeUpdate();
            if(sucess > 0)
                System.out.println(prcode + " 삭제 성공");
            else
                System.out.println(prcode + "삭제 실패");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //데이터를 받는 메소드
    public ArrayList<ProductDTO> selectData(){
        try {
            ArrayList<ProductDTO> arr = new ArrayList<ProductDTO>();
            quary = Constants.SELECTQUARY;
            pstm = conn.prepareStatement(quary);

            ResultSet sucess = pstm.executeQuery();
            while(sucess.next()){
                ProductDTO temp = new ProductDTO();
                temp.setPrcode(Integer.parseInt(sucess.getString("prcode")));
                temp.setPrname(sucess.getString("prname"));
                temp.setPrice(Integer.parseInt(sucess.getString("price")));
                temp.setManufacture(sucess.getString("manufacture"));
                arr.add(temp);
            }
            return arr;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //데이터를 받는 메소드
    public ProductDTO selectItemData(int prcode){
        try {
            quary = Constants.SELECTITEMQUARY;
            pstm = conn.prepareStatement(quary);

            pstm.setString(1, String.valueOf(prcode));

            ResultSet sucess = pstm.executeQuery();
            sucess.next();
            ProductDTO temp = new ProductDTO();
            temp.setPrcode(Integer.parseInt(sucess.getString("prcode")));
            temp.setPrname(sucess.getString("prname"));
            temp.setPrice(Integer.parseInt(sucess.getString("price")));
            temp.setManufacture(sucess.getString("manufacture"));
            return temp;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //데이터를 Update 하는 메소드
    public void UpdateData(ProductDTO product){
        quary = Constants.UPDATEQUARY;
        try {
            pstm = conn.prepareStatement(quary);
            pstm.setString(1, product.getPrname());
            pstm.setString(2, String.valueOf(product.getPrice()));
            pstm.setString(3, product.getManufacture());
            pstm.setString(4, String.valueOf(product.getPrcode()));
            int sucess = pstm.executeUpdate();
            if(sucess > 0)
                System.out.println(product.getPrname() + " 수정 성공");
            else
                System.out.println(product.getPrname() + " 수정 실패");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<String> getItems(){
        Vector<String> arr = new Vector<String>();
        try {
            quary = Constants.SELECTQUARY;
            pstm = conn.prepareStatement(quary);

            ResultSet sucess = pstm.executeQuery();
            while (sucess.next()) {
                arr.add(sucess.getString("prcode"));
            }
            return arr;
        }catch(SQLException e){}
        return null;
    }
}
