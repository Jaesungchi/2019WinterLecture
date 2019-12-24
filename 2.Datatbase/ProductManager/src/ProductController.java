import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ProductController implements ActionListener {
    ProductView productView;
    ProductDAO connect;
    ArrayList<ProductDTO> dataList;
    boolean editmode = false;

    public ProductController(ProductView productView){
        this.productView = productView;
        connect = new ProductDAO();
        dataList = connect.selectData();
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

    private void newDataFunction(){
        ProductDTO product = new ProductDTO();
        product = productView.getProduct();
        if(editmode && !productView.getComboBox().equals("전체") ) {
            connect.UpdateData(product);
            productView.setMessege("상품을 수정했습니다.");
            editmode = false;
        } else {
            connect.addData(product);
            productView.setMessege("상품을 등록했습니다.");
        }
        refreshData();
    }

    private void refreshData(){
        productView.resetArea();
        productView.addTextArea("관리번호\t상품명\t\t단가\t제조사\n");
        dataList = connect.selectData();
        productView.resetComboBox(new DefaultComboBoxModel(connect.getItems()));
        editmode = false;
        if(dataList != null){
            for(ProductDTO p : dataList){
                StringBuffer sb = new StringBuffer();
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

    private void viewFunction(){
        String s = productView.getComboBox();
        if(!s.equals("전체")){
            productView.setProduct(connect.selectItemData(Integer.parseInt(s)));
            productView.setMessege("상품정보를 가져 왔습니다.");
            editmode = true;
        }
    }

    private void deleteFunction(){
        String s = productView.getComboBox();
        if(s.equals("전체"))
            productView.setMessege("전체는 삭제 되지 않습니다!");
        else{
            connect.deleteData(Integer.parseInt(s));
            productView.setMessege("상품이 삭제 되었습니다.");
        }
        refreshData();
    }
}
