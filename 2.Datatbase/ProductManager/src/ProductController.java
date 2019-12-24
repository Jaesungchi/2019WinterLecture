import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/*
ActionListener를 상속받아 사용하는 Controller
 */

public class ProductController implements ActionListener {
    ProductView productView; //View를 갖고 있는다.
    ProductDAO connect; //DAO 통신을 위해 갖고 있는다.
    boolean editmode = false; //이것이 True면 불러온 데이터.

    public ProductController(ProductView productView){
        this.productView = productView;
        connect = new ProductDAO();
        refreshData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operator = ((JButton)e.getSource()).getLabel();
        switch(operator){
            case Constants.NEWBTN:
                newDataFunction();
                break;
            case Constants.VIEWBTN:
                viewFunction();
                break;
            case Constants.DELBTN:
                deleteFunction();
                break;
            default:
                break;
        }
    }

    //등록 버튼을 눌렀을시 시작되는 메소드
    private void newDataFunction(){
        if(!productView.checkNotNullData()) return; //값이 없는게 있다면 넘긴다.
        ProductDTO product = productView.getProduct();
        if(editmode && !productView.getComboBox().equals(Constants.ALL)) {
            connect.UpdateData(product);
            productView.setMessege("상품을 수정했습니다.");
            editmode = false;
        } else {
            connect.addData(product);
            productView.setMessege("상품을 등록했습니다.");
        }
        refreshData();
    }

    //데이터를 새로고침하는 메소드
    private void refreshData(){
        ArrayList<ProductDTO> dataList = connect.selectData(); //받은 데이터를 저장하기 위한 변수
        productView.resetArea();
        productView.addTextArea("관리번호\t상품명\t\t단가\t제조사\n");
        productView.resetComboBox(new DefaultComboBoxModel(connect.getItems()));
        editmode = false; //새로고침 되는 경우는 새로고침 되는 경우.
        if(dataList != null){
            for(ProductDTO p : dataList){ //ArrayList로 받아온 데이터를 TextArea에 넣는다.
                StringBuffer sb = new StringBuffer(); //버퍼에 Product Data를 쌓아 넣는다.
                sb.append(p.getPrcode() + "\t\t");
                sb.append(p.getPrname() + "\t\t");
                sb.append(p.getPrice() + "\t");
                sb.append(p.getManufacture() + "\n");
                productView.addTextArea(sb.toString());
            }
        }
        else
            productView.addTextArea("등록된 상품이 없습니다. !!\n상품을 등록해 주새요 !!");
    }

    //조회 버튼 클릭 메소드
    private void viewFunction(){
        String s = productView.getComboBox();
        if(!s.equals(Constants.ALL)){
            productView.setProduct(connect.selectItemData(Integer.parseInt(s)));
            productView.setMessege("상품정보를 가져 왔습니다.");
            editmode = true;
        }
    }

    //삭제 버튼 클릭 메소드
    private void deleteFunction(){
        String s = productView.getComboBox();
        if(s.equals(Constants.ALL))
            productView.setMessege("전체는 삭제 되지 않습니다!");
        else{
            connect.deleteData(Integer.parseInt(s));
            productView.setMessege("상품이 삭제 되었습니다.");
        }
        refreshData();
    }
}
