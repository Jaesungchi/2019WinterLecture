import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class ContactController implements ActionListener {
	
	String fname = Constants.SAVE_PATH; //저장될 곳
	ContactMainFrame mainFrame;
	
	//값을 편하게 저장하기 위한 static class 선언
	static class address{
		public address(String name, String age, String phone) {
			this.name = name;
			this.age = age;
			this.phone = phone;
		}
		String name;
		String age;
		String phone;
	}
	
	//생성자
	public ContactController(ContactMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String operator = ((JButton)e.getSource()).getLabel();
		switch(operator){ //각 버튼에 맞게 뿌려준다.
			case Constants.NEW:
				System.out.println("New");
				newContact();
				break;
			case Constants.VIEW:
				System.out.println("View");
				viewContact();
				break;
			case Constants.DELETES:
				System.out.println("Delete");
				deleteContact();
				break;
			case Constants.INFO:
				new JOptionPane().showMessageDialog(null, "연락처 입니다.");
				break;
			case Constants.EXIT: //프로그램 종료
				System.out.println("Exit");
				System.exit(0);
				break;
		}
	}
	
	//새로운 전화번호 추가시
	private void newContact() {
		address adr = new address("","",""); //address를 초기 화한다.
		String wstr = "";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fname,true)); //입력 버퍼를 연다.
			wstr = JOptionPane.showInputDialog("이름을 입력하세요");
			if(wstr == null) return; //입력하지 않거나 취소한 경우
			adr.name = wstr;
			wstr = JOptionPane.showInputDialog("나이를 입력하세요");
			if(wstr == null) return;
			adr.age = wstr;
			wstr = JOptionPane.showInputDialog("전화번호를 입력하세요");
			if(wstr == null) return;
			adr.phone = wstr;
			
			wstr = adr.name + "\t" + adr.age + "\t" + adr.phone; //wstr에 정리 한뒤
			bw.write(wstr); //글자를 적는다.
			bw.newLine();
			bw.close();
			viewContact();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//연락처를 조회하는 메소드
	private void viewContact() {
		String str = "";
		//처음에 fname 파일이 없으면 빈 파일 생성
		File f = new File(fname);
		try {
			if(!f.exists()) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fname));
				bw.close();
			}
			//읽기 버퍼를 연다.
			BufferedReader br = new BufferedReader(new FileReader(fname));
			int i;
			mainFrame.resetArea();
			for(i = 0;; i++) { //무한으로 확인 할 수 있게 한다.
				if(!br.ready()) break; //없다면 종료
				str = br.readLine(); 
				mainFrame.addTextArea(str);
			}
			if (i == 0) //1인 경우는 값이 하나도 없는 경우
				mainFrame.addTextArea("연락처가 하나도 없습니다.");
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//연락처를 삭제하는 메소드
	private void deleteContact() {
		String str = "";
		String[] read_str = new String[50]; //최대 연락처 갯수를 50개로 가정
		int del_line,i,count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			if(!br.ready()) { //하나도 없는경우
				mainFrame.resetArea();
				mainFrame.addTextArea("연락처가 하나도 없습니다.");
				return;
			}
			str = JOptionPane.showInputDialog("삭제할 행 번호는?");
			if(str == null) return;
			del_line = Integer.parseInt(str);
			
			for(i = 0 ; i < 50 ; i++) { //50까지 순환하면서 찾는다.
				if((str = br.readLine()) == null) break;
				if(i + 1 != del_line) { //사람은 1부터 숫자를 세기 때문에 1 추가해서 비교한다.
					read_str[count] = str;
					count++; //배열의 포인터 처럼 쓴다.
				}else { //삭제 된경우.
					JOptionPane.showMessageDialog(null, del_line+" 행이 삭제되었습니다.");
				}
			}
			br.close();
			//쓰기 Buffer를 열고 배열에 저장된 값을 쓴다.
			BufferedWriter bw = new BufferedWriter(new FileWriter(fname));
			for(i = 0; i < count ; i++) {
				bw.write(read_str[i]);
				bw.newLine();
			}
			bw.close();
			viewContact(); //초기화 해준다.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
