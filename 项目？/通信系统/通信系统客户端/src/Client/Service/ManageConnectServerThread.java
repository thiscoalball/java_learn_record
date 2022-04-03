package Client.Service;


import java.util.HashMap;

//管理客户端连接到服务器端的线程的类
public class ManageConnectServerThread {
    //把线程放入到HashMap集合中 key为用户id
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    public static void addConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread) {
        hm.put(userId, clientConnectServerThread);
    }

    public static ClientConnectServerThread getClientConnectServerThread(String userId) {
        //根据id返回这个线程
        return hm.get(userId);
    }
}
