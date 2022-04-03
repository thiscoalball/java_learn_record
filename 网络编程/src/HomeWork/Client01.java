package HomeWork;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client01 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的问题 name或者hobby");
        String next = scanner.next();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(next);

        bw.newLine();
        bw.flush();//刷新一次 将数据真正写入;

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(br.readLine());

        bw.close();
        br.close();
        socket.close();
        System.out.println("客户端退出");
    }
}
