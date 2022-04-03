package Homework03;

import upLoad.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server03 {
    public static void main(String[] args) throws Exception {
        System.out.println("服务器端等待连接");
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        System.out.println("服务器与客户端连接成功");
        //接收客户端发来的下载请求
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String musicName = br.readLine();

        String filePath = "src\\" + musicName + ".mp3";

        //如果查找的到这个文件，则将其转化为字节数组进行写入

        //先从自己的文件里查找有无这个东西
        if("高山流水".equals(musicName)){
            System.out.println("正在下载高山流水");
        }else {
            filePath = "src\\无名.mp3";
            System.out.println("查找不到你要的音乐，随便帮你下一首");
        }
        //读取该文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        //转化为字符数组
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);
        socket.shutdownOutput();

        br.close();
        bis.close();
        bos.close();
        socket.close();
        serverSocket.close();
        System.out.println("文件传输成功");
    }
}