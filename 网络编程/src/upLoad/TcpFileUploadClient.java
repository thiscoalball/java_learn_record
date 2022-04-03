package upLoad;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpFileUploadClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        System.out.println("客户端连接上服务器8888端口");

        //先把读取到文件才能发送
        String filePath = "C:\\java创建文件尝试\\bg.jpg";
        //创建用来读取磁盘文件的输入流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        //把文件读取到一个字节数组中
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //socket创建输出流准备发送bytes的数据
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        //将文件写入了数据通道
        bos.write(bytes);
        //设置写入数据的结束标记
        socket.shutdownOutput();

        //如果服务端成功接收到发送过去的图片，并返回信息，这里准备接收
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(br.readLine());

        br.close();
        bis.close();
        bos.close();
        socket.close();
        System.out.println("客户端关闭");
    }
}
