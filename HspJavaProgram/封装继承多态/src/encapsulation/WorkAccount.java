package encapsulation;

//通过set方法给Account的属性赋值
//在AccountTest中测试
public class WorkAccount{
    private String name;  //名字为2-3-4位
    private double balance; //余额必须大雨20
    private String pwd;    //密码必须是6位，
    public WorkAccount() {}
    public WorkAccount(String name, double balance, String pwd) {
        this.setBalance(balance);
        this.setName(name);
        this.setPwd(pwd);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() >= 2 && name.length() <= 4 ) {
            this.name = name;
        }
        else{
            System.out.println("名字的长度要求位2-3-4位");
            this.name = "NULL";
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance > 20) {
            this.balance = balance;
        }
        else{
            System.out.println("余额必须大于20");
            this.balance = 0;
        }
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        if(pwd.length() == 6) {
            this.pwd = pwd;
        }
        else{
            System.out.println("密码必须是6位（默认000000）");
            this.pwd = "000000";
        }
    }
    //显示账号信息
    public void showInfo(){
        //可以增加一个权限校验  调用show方法的时候先判断身份是否合法
        System.out.println("账号信息 name=" + name +" 余额=" + balance + " 密码" + pwd);
    }
}
