package upLoad;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileUploadServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器8888端口等待连接");
        Socket socket = serverSocket.accept();
        //读取客户端发送的数据
        //先得到一个输入流
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        //拿到流里传过来的数据并转换成一串字节数组
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //准备将得到的bytes数组写入到文件里
        String filePath = "src\\接收的图片.jpg";
        //创建一个流对象用于写入
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        //写入得到的字节数组
        bos.write(bytes);

        //完成接收和写入之后像客户端发送接收成功的信息
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("服务器数据接收完成");
        bw.newLine();
        bw.flush();

        bw.close();
        bis.close();
        bos.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端关闭");

    }
}
