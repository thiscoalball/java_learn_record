package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class sockerTcp02Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端连接到服务器的9999端口");


        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello server".getBytes(StandardCharsets.UTF_8));
        //设置结束标记 不设置的话就会等待
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int len;

        while((len = inputStream.read(buf))!= -1){
            System.out.println(new String(buf,0,len));
        }

        System.out.println("客户端退出了");

        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
