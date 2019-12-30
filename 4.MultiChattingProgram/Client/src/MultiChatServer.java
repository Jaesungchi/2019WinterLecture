import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MultiChatServer {

    public static void main(String[] args){
        MultiChatServer MCS = new MultiChatServer();
        MCS.start();
    }

    //서버 소켓 및 클라이언트 연결 소켓
    private ServerSocket ss = null;
    private Socket s = null;

    //연결된 클라이언트 스레드를 관리하는 ArrayList
    ArrayList<ChatThread> chatThreads = new ArrayList<ChatThread>();

    //로거 객체
    Logger logger;

    public void start(){
        logger = Logger.getLogger(this.getClass().getName());
        try{
            //서버 소켓 생성
            ss = new ServerSocket(8888);
            logger.info("MultiChatServer start");

            while(true){
                s = ss.accept();
                //연결된 클라이언트에 대해 쓰레드 클래스 생성
                ChatThread chat = new ChatThread();
                //클라이언트 리스트 추가
                chatThreads.add(chat);
                chat.start();
            }
        } catch (IOException e) {
            logger.info("[MultiChatServer]start() Exception 발생!!");
            e.printStackTrace();
            System.exit(0);
        }
    }
    class ChatThread extends Thread{
        public Messege m;
        boolean status;
        Gson gson;
        String msg;

        BufferedReader inMsg;
        PrintWriter outMsg;

        public ChatThread(){
            m = new Messege();
            status = false;
            gson = new Gson();

            //입출력 스트림 생성
            try {
                inMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
                outMsg = new PrintWriter(s.getOutputStream(),true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            super.run();
            status=true;
            while(status){
                //수신된 메세지를 msg변수에 저장
                try {
                    msg = inMsg.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
                m = gson.fromJson(msg,Messege.class);
                if(m.getType().equals("logout")){
                    chatThreads.remove(this);
                    msgSendAll(gson.toJson(new Messege(m.getId(),"","님이 종료했습니다.","server")));
                    //해당 클라이언트 스레드종료로 status를 false로 설정
                    status = false;
                } else if (m.getType().equals("login")){
                    msgSendAll(gson.toJson(new Messege(m.getId(),"","님이 로그인 했습니다","server")));
                } else if (m.getType().equals("googling")) {
                    msgWhisper(m.getMsg(),m.getGoogling(),m.getId());
                } else {
                    msgSendAll(msg);
                }
            }
            this.interrupt();
            logger.info(this.getName() + "종료됨!!");
        }

        void msgWhisper(String msg, String who, String sender){
            for(ChatThread ct :chatThreads){
                if(ct.m.getId().equals(who)){
                    ct.outMsg.println(gson.toJson(new Messege(m.getId()+"님의 귓속말","",msg,"googling",who)));
                }
                if(ct.m.getId().equals(sender)) {
                	ct.outMsg.println(gson.toJson(new Messege(who+"에게 귓속말","",msg,"msg")));
                }
            }
        }

        void msgSendAll(String msg){
            for(ChatThread ct :chatThreads){
                ct.outMsg.println(msg);
            }
        }

    }
}
