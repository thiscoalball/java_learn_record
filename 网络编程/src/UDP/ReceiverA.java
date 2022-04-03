package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class ReceiverA {
    public static void main(String[] args) throws IOException {
        //创建一个对象在9999端口准备接收数据
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        //创建对象准备接收数据
        byte[] bytes = new byte[64 * 1024];//udp一个包最多64k
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        //调用接收方法 接收不到就阻塞 接收到了就往下走
        System.out.println("接收端A等待接收数据");
        datagramSocket.receive(datagramPacket);
        //把数据拆包并显示
        int length = datagramPacket.getLength();//实际接收到的数据长度
        byte[] data = datagramPacket.getData();
        String s = new String(data,0, length);
        System.out.println(s);

        byte[] data1 = "A:fine and you?".getBytes(StandardCharsets.UTF_8);
        DatagramPacket datagramPacket1 = new DatagramPacket(data1, data1.length, InetAddress.getLocalHost(), 9998);
        datagramSocket.send(datagramPacket1);

        datagramSocket.close();
        System.out.println("A退出");
    }
}
