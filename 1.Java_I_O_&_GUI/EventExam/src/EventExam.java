import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
 * GUI를 공부하고 적용해보기 위한 프로젝트
 * KeyEvent를 이용해 TextField에 입력받고 TextArea에 들어간다.
 */

public class EventExam {
	
	//Frame을 만들어 적용한다.
	static class MyGUI extends JFrame{
		MyGUI(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X버튼 클릭시 꺼지기 위한 선언
			setTitle("이벤트 처리 3");
			this.setLayout(new FlowLayout());
			
			JTextField txt = new JTextField(10); //텍스트 영역은 10 까지
			JTextArea area = new JTextArea(10,10); //텍스트 영역은 10 * 10 까지
			this.add(txt);
			this.add(area);
			txt.addKeyListener(new KeyAdapters(txt,area)); //KeyAdapter는 따로 클래스로 빼서 적는것이 효율적이다.
			setSize(200,200);
			setVisible(true);
		}	
	}
	
	public static void main(String[] args) {
		new MyGUI();
	}
	
	//현재 Frame 객체도 static이기 때문에 접근권한을 맞추기 위해 static으로 선언한다.
	static class KeyAdapters extends KeyAdapter{
		
		JTextField txt;
		JTextArea area;
		
		//생성자로 입력되는 txt 와 area를 받는다.
		KeyAdapters(JTextField txt, JTextArea area){
			this.txt = txt;
			this.area = area;
		}
		
		//Release 되었을때 실행되는 이벤트
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_ENTER) { //엔터키 입력시 텍스트를 넣는다.
				String str = txt.getText();
				area.setText(area.getText() + str + "\n");
				txt.setText("");
			}
			
			if(!(key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9)) { //숫자가 아닌경우 지운다.
				String str = txt.getText();
				int strlen = str.length();
				if(strlen != 0)
					txt.setText(str.substring(0,strlen-1));
			}
		}
	}

}
