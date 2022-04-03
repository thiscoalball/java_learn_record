package socket;

import java.io.IOException;
import java.io.InputStream;

import java.net.ServerSocket;
import java.net.Socket;


//服务端
public class socketTcp01Server {
    public static void main(String[] args) throws IOException {
        //在本机的9999端口监听（要求本机没有其他服务在监听这个端口）
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端在9999端口等待连接");

        //当没有客户端连接9999的时候就会阻塞 并且等待连接
        //如果有客户端连接则会返回Socket对象程序继续执行
        Socket accept = serverSocket.accept();
        System.out.println("服务端连接成功socket= " + accept.getClass());
        //读取
        InputStream inputStream = accept.getInputStream();

        byte[] buf = new byte[1024];
        int readlen = 0;
        while ((readlen =inputStream.read(buf)) != -1){
            System.out.println(new String(buf,0,readlen));
        }
        //关闭流对象和socket
        accept.close();
        serverSocket.close();
        inputStream.close();
        System.out.println("客户端退出了");

    }
}
