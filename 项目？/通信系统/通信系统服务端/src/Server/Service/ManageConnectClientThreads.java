package Server.Service;

import java.util.HashMap;
import java.util.Iterator;

//管理连接客户端的线程
public class ManageConnectClientThreads {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

    public static HashMap<String, ServerConnectClientThread> getHm() {
        return hm;
    }

    public static void addConnectClientThread(String userId, ServerConnectClientThread serverConnectClientThread) {
        hm.put(userId, serverConnectClientThread);
    }

    //根据UserId返回线程
    public static ServerConnectClientThread getServerConnectClientThread(String userId) {
        return hm.get(userId);
    }

    //编写一个方法返回在线用户列表
    public static String getOnlineUser() {
        //集合遍历
        Iterator<String> iterator = hm.keySet().iterator();
        String onlineUserList = "";
        while (iterator.hasNext()) {
            //记得转化成字符串
            onlineUserList += iterator.next().toString() + " ";
        }
        return onlineUserList;
    }

    //编写方法从集合中移除某个线程对象
    public static void removeServerConnectClientThread(String userId) {
        hm.remove(userId);
    }



}
