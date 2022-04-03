package HomeWork;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void main(String[] args) throws IOException {
        //准备接收端口
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器等待连接");
        Socket socket = serverSocket.accept();
        System.out.println("服务器连接成功");
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = br.readLine();
        String answer = "";
        if(s.equals("name")){
            answer = "我是nova";
        }else if(s.equals("hobby")){
            answer = "我喜欢爬山";
        }else {
            answer = "你说啥我听不懂";
        }

        BufferedWriter br1 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        br1.write(answer);
        br1.newLine();
        br1.flush();

        br1.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端退出");
    }
}
