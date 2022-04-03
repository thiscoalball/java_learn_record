package Server.Service;

import Util.Utility;
import qqCommon.Message;
import qqCommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class SendNewsToAll implements Runnable {


    @Override
    public void run() {
        while (true) {
            //格式化时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            System.out.println("请输入服务器要给客户推送的广告(exit退出服务)");
            String s = Utility.readString(100);
            if("exit".equals(s)){
                System.out.println("已经退出了");
                break;
            }
            //构建一个消息 类型为群发消息
            Message message = new Message();
            message.setMesType(MessageType.MESSAGE_TOALL_MES);
            message.setSender("服务器");
            message.setContent(s);
            message.setSendTime(simpleDateFormat.format(date));
            System.out.println("服务器推送消息完成\t\t\t\t\t\t" + simpleDateFormat.format(date));

            //利用迭代器循环群发消息
            HashMap<String, ServerConnectClientThread> hm = ManageConnectClientThreads.getHm();
            Iterator<String> iterator = hm.keySet().iterator();
            while (iterator.hasNext()) {//如果还有人那么就进行迭代循环
                String onLineUserId = iterator.next();
                ServerConnectClientThread serverConnectClientThread = hm.get(onLineUserId);
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    oos.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
