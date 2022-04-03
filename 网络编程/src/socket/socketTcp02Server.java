package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class socketTcp02Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器9999端口等待连接");
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();

        int len = 0;
        byte[] buf = new byte[1024];

        while((len = inputStream.read(buf))!=-1){
            System.out.println(new String(buf,0,len));
        }

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello client".getBytes(StandardCharsets.UTF_8));
        //设置结束标记 不设置的话就会等待
        socket.shutdownOutput();

        System.out.println("服务端退出了");

        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
    }
}
