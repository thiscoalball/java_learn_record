package qqCommon;

import java.io.Serializable;


//表示客户端和服务器端通信时的消息对象
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sender;//发送者
    private String receiver;//接收方
    private String content; //发送的消息内容
    private String sendTime;//发送时间
    private String mesType;//消息类型[在接口中定义消息的类型]
    private byte[] fileBytes;//文件字节数组
    private int fileLen = 0;//文件长度
    private String dest;//将文件传输到哪
    private String src;//源文件路径

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }
}
