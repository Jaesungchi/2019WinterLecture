import jdk.net.NetworkPermission;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args){
        try{
            URL url = new URL("http://106.10.36.53:80");
            System.out.println("프로토콜: "+url.getProtocol());
            System.out.println("호스트: "+url.getHost());
            System.out.println("포트: "+url.getPort());
            System.out.println("HTML 시작..");
            InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while((msg = br.readLine())!= null){
                System.out.println(msg);
            }
            br.close();
            is.close();
            System.out.println("## 클라이언트 종료..");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
