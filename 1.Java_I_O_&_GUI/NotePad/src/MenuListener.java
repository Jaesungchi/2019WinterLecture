import java.awt.event.*;
import java.io.*;
import javax.swing.*;
/*
 * Controller로 활용되는 클래스
 * 각종 동작및 계산을 처리한다.
 */

public class MenuListener implements ActionListener {
	NotePadFrame NPF; //View를 갖고 있는다.
	File file; //주소 저장용
	int flag = 0; //flag로 불러온 상태를 확인한다.
	JFileChooser dialog = new JFileChooser();
	
	public MenuListener(NotePadFrame NPF) {
		this.NPF = NPF;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String temp = "";
		//MenuItem 과 JButton을 구분하기 위한 if
		if(e.getSource().getClass().getName() == "javax.swing.JMenuItem")
			temp = ((JMenuItem)e.getSource()).getText();
		else
			temp = ((JButton) e.getSource()).getLabel();
		switch(temp) {
		case Constants.NEW:
			newFile();
			break;
		case Constants.OPEN:
			openFile();
			break;
		case Constants.SAVE:
			saveAnotherFile();
			break;
		case Constants.ANOTHERSAVE:
			flag = 0; //0으로 바꿔 바로 들어갈 수 있게한다.
			saveAnotherFile();
			break;
		case Constants.END:
			System.exit(0);
			break;
		case Constants.INFO:
			new JOptionPane().showMessageDialog(null, "메모장 입니다.");
			break;
		case Constants.SMALL:
			NPF.setFontSize(Constants.SMALL_FONT);
			break;
		case Constants.MEDIUM:
			NPF.setFontSize(Constants.MEDIUM_FONT);
			break;
		case Constants.LARGE:
			NPF.setFontSize(Constants.LARGE_FONT);
			break;
		default:
			break;
		}
	}
	
	//새로운 파일을 만들때 실행되는 메소드
	private void newFile() {
		int result = JOptionPane.showConfirmDialog(null, "정말 새 메모를 합니까?","확인",JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.CLOSED_OPTION) return; //종료한경우
		flag = 0; //flag 초기화
		NPF.setTitle("새 파일");
		NPF.resetArea();
	}
	
	//다른 이름 저장버튼 클릭시 실행되는 메소드
	private void saveAnotherFile() {
		if(flag == 0) { // 새로운 파일 이라면
			int isGood = dialog.showSaveDialog(null);
	        if(isGood != 0) return; //저장하지 않았다면
	        file = dialog.getSelectedFile();
		}
		saveData();
	}
	
	//저장 버튼 클릭시 실행되는 메소드
	private void saveData() {
        String str = NPF.getTextArea(); //현재 값을 저장해둔다.
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(str); //파일을 에 저장한다.
            NPF.setTitle(file.getName()); //타이틀을 바꾸고
            flag = 1;
            bw.close();
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}
	
	//파일을 여는 경우
	private void openFile() {
		int isGood = dialog.showOpenDialog(null); //저장 dialog를 연다
		if(isGood != 0) //파일 열기 선택 안한 경우
			return;
        file = dialog.getSelectedFile(); 
        try{
         String str="";
         BufferedReader br = new BufferedReader(new FileReader(file)); //BufferedReader를 연다
        
         NPF.resetArea();  // 일단 초기화
         while((str=br.readLine()) != null)  // str에 남아둔 내용이 null 이 아니면..       
             NPF.setTextArea(str + "\r\n");  // str의 내용을 추가
         
         br.close(); //닫는다.
         String Filename = file.getName();
         NPF.setTitle(Filename);
         flag = 1;
        } catch(Exception e){
        	e.printStackTrace();
        }
	}

}
