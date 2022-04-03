package socket;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


//客户端
public class socketTcp01Client {
    public static void main(String[] args) throws IOException {

        //连接本地的9999端口
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端连接成功socket= " + socket.getClass());

        //发送数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello server".getBytes());


        outputStream.close();
        socket.close();
    }
}
