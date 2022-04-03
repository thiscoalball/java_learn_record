package Common;
@SuppressWarnings({"all"})
public interface MessageType {
    String MESSAGE_LOGGING_SUCCEED = "1";//表示登录成功
    String MESSAGE_LOGGING_FALSE = "2";//登录失败
    String MESSAGE_COMMON_MES = "3";//发送普通消息包
    String MESSAGE_SEND_ONE_SCORE = "4";//发送个人成绩消息包
    String MESSAGE_SEND_ALL_SCORE = "5";//发送全班成绩消息包
    String MESSAGE_TO_CLASS_STUDENT_MES = "6";//全班学生消息包
    String MESSAGE_TO_CLASS_GUARDIANS_MES = "7";//全班家长消息包
    String MESSAGE_GET_CLASS_GRADE = "8";//要求获取全班成绩
    String MESSAGE_GET_ONE_GRADE = "9";//要求获取指定学生成绩
    String MESSAGE_GET_RANK = "10";//排名消息包               工厂模式生成解析出需要获取什么类型的排名
    String MESSAGE_FILE_MES = "11";//文件消息包
    String MESSAGE_GET_STATE_BY_ID = "12";//转型消息包         可以利用工厂模式实例化
    String MESSAGE_GET_CLASS_INFO = "13";//获取班级信息包
    String MESSAGE_CREATE = "14";//注册消息包
    String MESSAGE_BIND = "15";//绑定消息包
    String MESSAGE_CLIENT_EXIT = "20";//客户端退出消息包

}
