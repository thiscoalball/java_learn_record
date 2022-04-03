package qqCommon;

import java.io.Serializable;

//表示一个用户信息
//因为要在网络中通过流传输 所以需要序列化
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;//用户Id
    private String pwd;   //密码

    public User() {
    }

    public User(String userId, String pwd) {
        this.userId = userId;
        this.pwd = pwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
