package Service;

import java.util.HashMap;
@SuppressWarnings({"all"})

public class ManageConnectServerThreads {
    //把线程放入到HashMap集合中 key为用户id
    private static HashMap<String, ConnectServerThread> hm = new HashMap<>();

    public static void addConnectServerThread(String personId, ConnectServerThread clientConnectServerThread) {
        hm.put(personId, clientConnectServerThread);
    }

    public static ConnectServerThread getClientConnectServerThread(String personId) {
        //根据id返回这个线程
        return hm.get(personId);
    }
}