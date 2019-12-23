import java.io.*;
import java.util.*;
public class SimpleIO {
	/*
	 * Stream을 이용해 입력하고 출력하는 예제
	 * Path에 .txt 파일로 입력한 file명의 텍스트 파일이 생긴다.
	 */

	static final String PATH = "C:\\Users\\cjs60\\Desktop\\객체지향설계기술\\1.Java_I_O_&_GUI/";
	
	public static void main(String[] args) {
		System.out.println("## 간단 메모장 v1.0 ##");
		System.out.println("## 저장할 파일명 : ");
		Scanner scanner = new Scanner(System.in); //입력을 받기위해 Scanner를 연다.
		String filename = scanner.next(); //filename에 입력 받는다.
		System.out.println("## 저장은 마지막 라인에 q 입력\n");
		try { //파일에 문자게 생길 수가 있어 try catch로 에러를 잡는다.
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //StreamReader를 BufferredReader에 연결해 연다.
			//FileWriter를 이용한 BufferedWriter 객체를 만든다.
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + filename + ".txt"));
			String s; //입력 받기 위한 String 변수
			while(!(s = reader.readLine()).equals("q")) { //q가 나올때까지 입력 받는다.
				writer.write(s + "\r\n"); //바로 추가한다.
			}
			reader.close(); //reader를 닫는다.
			writer.close(); //writer를 닫는다.
			scanner.close(); //scanner를 닫는다.
		} catch(Exception e) {
			e.printStackTrace();
		}
		//종료
		System.out.println("## 프로그램을 종료합니다.");
		System.out.println(filename + " 저장되었습니다.!!");
	}

}
