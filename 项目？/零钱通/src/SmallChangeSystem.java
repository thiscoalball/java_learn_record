import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSystem {
    public static void main(String[] args) {
        //define
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";

        // 完成功能1
        String details = "---------------零钱通明细-------------";//用于后面拼接
        double money = 0;
        double balance = 0;
        String note = ""; //消费说明
        Date date = null;  //java是java.util.Date 类型 表示日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH：mm");

        do{
            System.out.println("\n===============零钱通菜单==============");
            System.out.println("\t\t\t1：零钱通明细");
            System.out.println("\t\t\t2：收益入账");
            System.out.println("\t\t\t3：消费");
            System.out.println("\t\t\t4：退出");
            System.out.println("=====================================");

            System.out.println("请选择1-4 ：");
            key = scanner.next();

            switch (key)
            {
                case "1":
                    System.out.println(details);
                    break;
                case "2":
                    System.out.println("收益入账金额");
                    money = scanner.nextDouble(); //money的值应该校验
                    if(money <= 0){
                        System.out.println("收益入账金额需要大于0");
                        break;
                    }

                    date = new Date();
                    balance += money;
                    details +="\n收益入账：\t+" + money + "\t"
                                + (sdf.format(date)) + "\t余额为："+ balance; //拼接
                    System.out.println("收益入账成功");
                    break;
                case "3":
                    System.out.println("消费的金额：");
                    money = scanner.nextDouble(); //money的值应该校验
                    if(money <= 0 || money > balance){
                        System.out.println("消费不能小于0 并且你的金额应该在0-" + balance);
                        break;
                    }

                    balance -= money;
                    System.out.println("消费说明： ");
                    note = scanner.next();

                    date = new Date();
                    details +="\n" + note+ "\t-" + money + "\t"
                            + (sdf.format(date)) + "\t余额为："+ balance; //拼接
                    System.out.println("消费完成");
                    break;
                case "4":
                    String choice = "";
                    while(true)
                    {
                        System.out.println("你确定要退出吗 y/n");
                        choice = scanner.next();
                        if("y".equals(choice) || "n".equals(choice)) {
                            break;
                        }
                    }


                    if("y".equals(choice)) {
                        loop = false;
                    }
                    break;
                default:
                    System.out.println("选择有误 请重选");
            }
        }while(loop);
        System.out.println("推出了零钱通项目");
    }
}
