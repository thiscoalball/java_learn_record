import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddress_ {
    public static void main(String[] args) throws UnknownHostException {
        //获取本机的静态对象
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println(localhost);//DESKTOP-D67SUMA/192.168.43.180

        //根据指定主机名获取对象
        InetAddress host1 = InetAddress.getByName("DESKTOP-D67SUMA");
        System.out.println(host1);

        //根据一个域名返回一个对象
        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println(host2);

        //通过对象获取对应的地址
        String hostAddress = host2.getHostAddress();
        System.out.println(hostAddress);

        //获取对应的主机名或者域名
        String hostName = host2.getHostName();
        System.out.println(hostName);
    }
}
