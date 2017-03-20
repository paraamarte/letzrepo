package com.letz.dev.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class SocketServer{

	private int pushCnt;
    public SocketServer(){
        pushCnt = 0;
    }

    public static void main(String args[])throws IOException{
        SocketServer ss = new SocketServer();
        ss.ServerRun();
    }

    public void ServerRun() throws IOException{
        ServerSocket server = null;
        int port = 4200;
        Socket socket = null;
        try
        {
            server = new ServerSocket(port);
            while(true){
                System.out.println((new StringBuilder("-------���� ��� ��------")).append(pushCnt).toString());
                socket = server.accept();
                System.out.println((new StringBuilder()).append(socket.getInetAddress()).append(" ���� �����߽��ϴ�.").toString());
                String data = null;
                receiveData(data, socket);
                System.out.println("****** �޽����� �����Ͽ����ϴ�. ****");
                Thread.sleep(5000L);
                setPushCnt(pushCnt++);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void receiveData(String data, Socket socket){
        OutputStream os;
        OutputStreamWriter osw;
        BufferedWriter bw;
        os = null;
        osw = null;
        bw = null;
        data = UUID.randomUUID().toString();
        try{
        	while(true){
	            if(pushCnt == 10) data = "kill";
	            os = socket.getOutputStream();
	            osw = new OutputStreamWriter(os);
	            bw = new BufferedWriter(osw);
	            bw.write(data);
	            System.out.println((new StringBuilder("Ŭ���̾�Ʈ�� ������ ���� :")).append(data).toString());
	            bw.flush();
	            break;
        	}
        }catch(Exception e1){
            e1.printStackTrace();
        }finally {
        	try{
	        	bw.close();
	            osw.close();
	            os.close();
	            socket.close();
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
    }

    public int getPushCnt(){
        return pushCnt;
    }

    public void setPushCnt(int pushCnt){
        this.pushCnt = pushCnt;
    }

}
