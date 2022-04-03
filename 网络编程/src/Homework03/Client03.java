package Homework03;

import upLoad.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client03 {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要下载的音乐名");
        String musicName = scanner.next();

        //准备发送音乐名字给服务器端
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(musicName);
        bw.newLine();
        bw.flush();
        System.out.println("发送完成，你请求下载的音乐是："+musicName);
        //准备读取服务器发回来的内容
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        //把读取到的转化为字符数组
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        String filePath = "C:\\java创建文件尝试\\" + musicName + ".mp3";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        bos.write(bytes);

        bos.close();
        bw.close();
        socket.close();
        System.out.println("客户端关闭");
    }
}
