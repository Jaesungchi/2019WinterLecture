import java.awt.*;
import javax.swing.*;

/*
 * View 역할을 하는 클래스
 */

public class NotePadFrame extends JFrame {
	MenuListener menuListener;
	JTextArea txt_Area;
	
	//생성자를 이용해 초기화 한다.
	NotePadFrame(){
		setFrame();
		
		setMenuBar();
	
		setToolbar();
		
		setTextArea();
		
		setVisible(true);
	}
	
	private void setFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("NotePad");
		setLayout(new FlowLayout());
		setSize(700,700);
	}
	
	private void setMenuBar() {
		menuListener = new MenuListener(this);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("파일");
		JMenu editMenu = new JMenu("보기");
		JMenu infoMenu = new JMenu("도움말");
		
		JMenuItem newItem = new JMenuItem(Constants.NEW);
		newItem.addActionListener(menuListener);
		JMenuItem openItem = new JMenuItem(Constants.OPEN);
		openItem.addActionListener(menuListener);
		JMenuItem saveItem = new JMenuItem(Constants.SAVE);
		saveItem.addActionListener(menuListener);
		JMenuItem saveAnotherItem = new JMenuItem(Constants.ANOTHERSAVE);
		saveAnotherItem.addActionListener(menuListener);
		JMenuItem closeItem = new JMenuItem(Constants.END);
		closeItem.addActionListener(menuListener);
		
		JMenuItem smallItem = new JMenuItem(Constants.SMALL);
		smallItem.addActionListener(menuListener);
		JMenuItem mediumItem = new JMenuItem(Constants.MEDIUM);
		mediumItem.addActionListener(menuListener);
		JMenuItem largeItem = new JMenuItem(Constants.LARGE);
		largeItem.addActionListener(menuListener);
		
		setJMenuBar(menuBar);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(infoMenu);
		
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAnotherItem);
		fileMenu.addSeparator();
		fileMenu.add(closeItem);
		
		editMenu.add(smallItem);
		editMenu.add(mediumItem);
		editMenu.add(largeItem);
	}
	
	private void setToolbar() {
		JToolBar toolBar = new JToolBar();
		
		JButton newItem = new JButton(Constants.NEW);
		JButton openItem = new JButton(Constants.OPEN);
		JButton saveItem = new JButton(Constants.SAVE);
		JButton saveAnotherItem = new JButton(Constants.ANOTHERSAVE);
		JButton exitItem = new JButton(Constants.END);

		JButton smallItem = new JButton(Constants.SMALL);
		JButton mediumItem = new JButton(Constants.MEDIUM);
		JButton largeItem = new JButton(Constants.LARGE);
		
		JButton infoItem = new JButton(Constants.INFO);
		
		newItem.addActionListener(menuListener);
		openItem.addActionListener(menuListener);
		saveItem.addActionListener(menuListener);
		saveAnotherItem.addActionListener(menuListener);
		exitItem.addActionListener(menuListener);
		smallItem.addActionListener(menuListener);
		mediumItem.addActionListener(menuListener);
		infoItem.addActionListener(menuListener);
		largeItem.addActionListener(menuListener);

		
		toolBar.add(newItem);
		toolBar.add(openItem);
		toolBar.add(saveItem);
		toolBar.add(saveAnotherItem);
		toolBar.add(exitItem);
		toolBar.addSeparator();
		toolBar.add(smallItem);
		toolBar.add(mediumItem);
		toolBar.add(largeItem);
		toolBar.addSeparator();
		toolBar.add(infoItem);
		
		add(toolBar,BorderLayout.NORTH);
	}
	
	private void setTextArea() {
		txt_Area = new JTextArea(35,60);
		add(new JScrollPane(txt_Area),BorderLayout.NORTH);
	}
	
	public void setFontSize(int size) {
		switch(size) {
		case Constants.SMALL_FONT:
			txt_Area.setFont(new Font("고딕",Font.PLAIN,12));
			break;
		case Constants.MEDIUM_FONT:
			txt_Area.setFont(new Font("고딕",Font.PLAIN,24));
			break;
		case Constants.LARGE_FONT:
			txt_Area.setFont(new Font("고딕",Font.PLAIN,36));
			break;
		default:
				break;
		}
	}
	
	public void resetArea() {
		txt_Area.setText("");
	}
	
	public void setTextArea(String data) {
		txt_Area.append(data);
	}
	
	public String getTextArea() {
		return txt_Area.getText();
	}
	
}
