import java.awt.*;
import javax.swing.*;

public class ContactMainFrame extends JFrame {
	JTextArea txt_Area;
	ContactMainFrame(){
		setFrame();
		
		setToolbar();
		
		initTextArea();
		
		setVisible(true);
	}
	
	private void setFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("연락처");
		setSize(500,700);
	}
	
	private void setToolbar() {
		ContactController controller = new ContactController(this);
		JToolBar toolBar = new JToolBar();
		
		JButton newItem = new JButton(Constants.NEW);
		JButton viewItem = new JButton(Constants.VIEW);
		JButton deleteItem = new JButton(Constants.DELETES);
		JButton exitItem = new JButton(Constants.EXIT);

		JButton infoItem = new JButton(Constants.INFO);
		
		newItem.addActionListener(controller);
		viewItem.addActionListener(controller);
		deleteItem.addActionListener(controller);
		exitItem.addActionListener(controller);
		infoItem.addActionListener(controller);
		
		toolBar.add(newItem);
		toolBar.add(viewItem);
		toolBar.add(deleteItem);
		toolBar.add(exitItem);
		toolBar.addSeparator();
		toolBar.add(infoItem);
		
		add(toolBar,BorderLayout.NORTH);
	}
	
	//TextArea 초기화 메소드
	private void initTextArea() {
		txt_Area = new JTextArea();
		txt_Area.setFont(new Font("고딕",Font.PLAIN,24)); //폰트를 정한다
		add(new JScrollPane(txt_Area),BorderLayout.CENTER); //제일 위에 둔다
	}
	
	//TextArea를 초기화 하는 메소드
	public void resetArea() {
		txt_Area.setText("");
	}
	
	//TextArea에 한줄씩 추가하는 메소드
	public void addTextArea(String data) {
		txt_Area.append(data + "\r\n");
	}
}
