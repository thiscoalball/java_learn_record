package UDP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class SenderB {
    public static void main(String[] args) throws IOException {
        //注意这里和TCP的不同 他们不在一个端口
        DatagramSocket datagramSocket = new DatagramSocket(9998);

        byte[] data = "hello how are you?".getBytes(StandardCharsets.UTF_8);

        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 9999);

        datagramSocket.send(datagramPacket);


        //创建对象准备接收数据
        byte[] bytes = new byte[64 * 1024];//udp一个包最多64k
        datagramPacket = new DatagramPacket(bytes, bytes.length);
        //调用接收方法 接收不到就阻塞 接收到了就往下走
        datagramSocket.receive(datagramPacket);
        //把数据拆包并显示
        int length = datagramPacket.getLength();//实际接收到的数据长度
        byte[] data1 = datagramPacket.getData();
        String s = new String(data1,0, length);
        System.out.println(s);

        datagramSocket.close();
        System.out.println("b退出");
    }
}
