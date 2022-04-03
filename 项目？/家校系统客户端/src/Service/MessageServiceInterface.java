package Service;

import java.io.IOException;


 interface MessageServiceInterface {
    public void sendMessageToOne(String senderName,String senderId, String personId,String personName,String content) throws IOException;
    public void chatClass(String personName, String personId, String classId, String content, String MessageType) throws IOException;
    public void quitMessage(String senderName,String senderId) throws IOException;
}
