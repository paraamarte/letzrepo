package com.letz.dev.socket;

import java.io.*;
import java.net.Socket;

public class SocketClient {
  public static void main(String args[]) throws IOException {
    SocketClient cm = new SocketClient();
    cm.ClientRun("Message Test+++Message Test+++Message Test");
  }

  public void ClientRun(String data) {
    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    Socket socket = null;
    try {
      String receiveData = "";
      while (true) {
        socket = new Socket("192.168.202.38", 4200);
        is = socket.getInputStream();
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        receiveData = "";
        receiveData = br.readLine();
        System.out.println((new StringBuilder("서버에서 전송 받은 데이터 : ")).append(receiveData).toString());
        if ("kill".equals(receiveData)) {
          System.out.println(
              (new StringBuilder("서버에서 전송 받은 데이터  ")).append(receiveData).toString());
          System.out.println("종료합니다.");
          Runtime.getRuntime().exec((new StringBuilder("MSG * ")).append(receiveData).toString());
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
        isr.close();
        is.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
