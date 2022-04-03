package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketTcp03Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器9999端口等待连接");
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();

        //使用转换流将inputStream转换为字符流
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String s = br.readLine();
        System.out.println(s);

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write("hello client 字符流");
        bw.newLine(); // 要求对方要使用readLine来读，否则无法结束
        bw.flush();//需要使用这个手动刷新不然不会写入数据通道

        System.out.println("服务端退出了");

        br.close();
        bw.close();
        socket.close();
        serverSocket.close();
    }
}
