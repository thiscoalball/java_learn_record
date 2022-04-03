package domain;

import java.time.LocalDateTime;

/**
 * 映射数据库的离线消息表
 * CREATE TABLE offlineMessage(
 * 	senderId VARCHAR(50) NOT NULL DEFAULT '',#发送人Id
 * 	senderName VARCHAR(50) NOT NULL DEFAULT '',#发送人姓名
 * 	receiverId VARCHAR(50) NOT NULL DEFAULT '',#接收人Id
 * 	receiverofName VARCHAR(50) NOT NULL DEFAULT '',#接收人姓名
 * 	`time` DATETIME ,     #通信的时间
 * 	`type` VARCHAR(50) NOT NULL DEFAULT '',#通信类型
 * 	`content` VARCHAR(50) NOT NULL DEFAULT ''#通信内容
 * );
 * */
@SuppressWarnings({"all"})
public class OfflineMessage {
    private String senderId;
    private String senderName;
    private String receiverId;
    private String receiverName;
    private LocalDateTime time;
    private String type;
    private String content;

    public OfflineMessage() {
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "offlineMessage{" +
                "senderId='" + senderId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
