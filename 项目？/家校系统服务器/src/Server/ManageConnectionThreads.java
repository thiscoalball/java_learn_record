package Server;

import java.util.HashMap;

//管理连接客户端的线程的集合
@SuppressWarnings({"all"})
public class ManageConnectionThreads {
    private static HashMap<String, ServerConnectionThread> hashMap = new HashMap<>();

    public static void addConnectClientThread(String personId, ServerConnectionThread serverConnectionThread) {
        hashMap.put(personId, serverConnectionThread);
    }

    public static ServerConnectionThread getServerConnectionThread(String userId){
        return hashMap.get(userId);
    }

    public static void removeServerConnectClientThread(String personId) {
        hashMap.remove(personId);
    }
}
