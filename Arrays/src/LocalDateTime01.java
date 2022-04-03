import java.time.LocalDateTime;

public class LocalDateTime01 {
    public static void main(String[] args) {
        String name = "jack";
        String pwd = "123456";
        String email = "jack@qq.com";

        try{
            user(name,pwd,email);
            System.out.println("注册成功");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void user(String name, String pwd, String email) {
        if (!(name.length() >= 2 && name.length() <= 4)) {
            throw new RuntimeException("用户名长度应该为2，3，4位");
        }
        if ( !(pwd.length() == 6 && isDigital(pwd))) {
            throw new RuntimeException("密码的长度需要为6而且全是数字");

        }
        int index1 = email.indexOf('@');
        int index2 = email.indexOf('.');
        if (!(index1 < index2 && index1 > 0)) {
            throw new RuntimeException("@需要在.前面");
        }
    }

    public static boolean isDigital(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return false;
            }
        }
        return true;
    }
}