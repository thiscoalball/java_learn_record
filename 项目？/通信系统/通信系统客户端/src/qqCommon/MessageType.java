package qqCommon;

//表示消息类型
public interface MessageType {
    String MESSAGE_LOGING_SUCCEED = "1";//表示登录成功
    String MESSAGE_LOGING_FALSE = "2";
    String MESSAGE_COMON_MES = "3";//发送普通消息包
    String MESSAGE_GET_ONLINE_FRIEND = "4";//要求获取在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND = "5";//返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6";//退出消息包
    String MESSAGE_TOALL_MES = "7";//群发消息包
    String MESSAGE_FILE_MES = "8";

}
