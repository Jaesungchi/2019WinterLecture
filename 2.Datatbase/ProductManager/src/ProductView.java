import javax.swing.*;
import java.awt.*;

public class ProductView extends JFrame {
    ProductController controller;
    TextArea textArea;
    JLabel messege;
    JComboBox prcodeBox;
    JTextField prnameBox;
    JTextField prpriceBox;
    JTextField prmanufactureBox;

    public ProductView(){
        setFrame();

        setMessegeBox();

        setOptionBox();

        setTextArea();

        setButtonBox();

        setVisible(true);
    }

    private void setFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("상품관리 프로그램");
        setSize(700,400);
        setLayout(null);
    }

    private void setMessegeBox(){
        JPanel messegeBox = new JPanel();
        messegeBox.setBounds(0,0,700,50);
        messegeBox.setBackground(Color.white);
        messege = new JLabel("안녕하세요 상품관리 프로그램입니다.");
        messege.setFont(new Font("고딕",Font.BOLD,30));
        messegeBox.add(messege);
        add(messegeBox);
    }

    private void setOptionBox(){
        JPanel messegeBox = new JPanel();
        messegeBox.setBounds(0,50,300,250);
        messegeBox.setBackground(Color.white);
        messegeBox.setLayout(null);

        JLabel prcodetxt = new JLabel("관리번호");
        prcodetxt.setFont(new Font("고딕",Font.PLAIN,15));
        prcodetxt.setBounds(0,0,80,55);
        messegeBox.add(prcodetxt);

        JLabel prnametxt = new JLabel("상품명");
        prnametxt.setFont(new Font("고딕",Font.PLAIN,15));
        prnametxt.setBounds(0,60,80,60);
        messegeBox.add(prnametxt);

        JLabel prpricetxt = new JLabel("단 가");
        prpricetxt.setFont(new Font("고딕",Font.PLAIN,15));
        prpricetxt.setBounds(0,125,80,60);
        messegeBox.add(prpricetxt);

        JLabel prmanuFacturetxt = new JLabel("제조사");
        prmanuFacturetxt.setFont(new Font("고딕",Font.PLAIN,15));
        prmanuFacturetxt.setBounds(0,190,80,60);
        messegeBox.add(prmanuFacturetxt);

        prcodeBox = new JComboBox();
        prcodeBox.setBounds(80,0,200,55);
        prcodeBox.setFont(new Font("고딕",Font.PLAIN,15));
        prcodeBox.addItem("신규");

        prnameBox = new JTextField();
        prnameBox.setBounds(80,60,200,60);

        prpriceBox = new JTextField();
        prpriceBox.setBounds(80,125,200,60);

        prmanufactureBox = new JTextField();
        prmanufactureBox.setBounds(80,190,200,60);


        messegeBox.add(prcodeBox);
        messegeBox.add(prnameBox);
        messegeBox.add(prpriceBox);
        messegeBox.add(prmanufactureBox);

        add(messegeBox);
    }

    private void setTextArea(){
        textArea = new TextArea();
        textArea.setFont(new Font("고딕",Font.PLAIN,12));
        textArea.setEnabled(false);
        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setBounds(300,50,380,250);
        add(jScrollPane);
    }

    private void setButtonBox(){
        JPanel buttonBox = new JPanel();
        buttonBox.setBounds(0,300,700,100);
        buttonBox.setBackground(Color.white);

        controller = new ProductController(this);

        JButton newItem = new JButton(Constants.NEWBTN);
        JButton viewItem = new JButton(Constants.VIEWBTN);
        JButton deleteItem = new JButton(Constants.DELBTN);

        newItem.addActionListener(controller);
        viewItem.addActionListener(controller);
        deleteItem.addActionListener(controller);

        buttonBox.add(newItem);
        buttonBox.add(viewItem);
        buttonBox.add(deleteItem);

        add(buttonBox);
    }

    //TextArea를 초기화 하는 메소드
    public void resetArea() {
        textArea.setText("");
    }

    public void resetComboBox(DefaultComboBoxModel DCBM){
        prcodeBox.setModel(DCBM);
        prcodeBox.insertItemAt("전체",0);
    }

    //TextArea에 한줄씩 추가하는 메소드
    public void addTextArea(String data) {
        textArea.append(data + "\r\n");
    }

    //Messege를 정한다.
    public void setMessege(String data){
        messege.setText(data);
    }

    //ComboBox의 선택값을 들고온다.
    public String getComboBox(){
        return String.valueOf(prcodeBox.getSelectedItem());
    }

    //현재 product를 돌려준다.
    public ProductDTO getProduct(){
        ProductDTO temps = new ProductDTO();
        temps.setManufacture(prmanufactureBox.getText());
        temps.setPrname(prnameBox.getText());
        temps.setPrice(Integer.parseInt(prpriceBox.getText()));
        if(!prcodeBox.getSelectedItem().equals("전체"))
            temps.setPrcode(Integer.parseInt(String.valueOf(prcodeBox.getSelectedItem())));
        else
            temps.setPrcode(0);
        return temps;
    }

    //선택한 prcode에 맞게 보여준다.
    public void setProduct(ProductDTO product){
        prnameBox.setText(product.getPrname());
        prpriceBox.setText(String.valueOf(product.getPrice()));
        prmanufactureBox.setText(product.getManufacture());
    }
}
