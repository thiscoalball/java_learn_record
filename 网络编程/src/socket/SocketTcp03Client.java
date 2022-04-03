package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketTcp03Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端连接到服务器的9999端口");


        OutputStream outputStream = socket.getOutputStream();
        //注意这个转换流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write("hello server字符流");
        bw.newLine();//插入一个换行符标识写入的内容结束
        // 要求对方要使用readLine来读，否则无法结束
        bw.flush();//需要使用这个手动刷新不然不会写入数据通道



        InputStream inputStream = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String s = br.readLine();
        System.out.println(s);

        System.out.println("客户端退出了");

        br.close();
        bw.close();
        socket.close();
    }
}