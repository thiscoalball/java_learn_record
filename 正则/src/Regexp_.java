import javafx.scene.Parent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexp_ {
    public static void main(String[] args) {
        String content = "2000年5月，JDK1.3、JDK1.4和J2SE1.3相继发布，几周后其获得了Apple公司Mac OS X的工业标准的支持。2001年9月24日，J2EE1.3发布。2002年2月26日，J2SE1.4发布。自此Java的计算能力有了大幅提升，与J2SE1.3相比，其多了近62%的类和接口。在这些新特性当中，还提供了广泛的XML支持、安全套接字（Socket）支持（通过SSL与TLS协议）、全新的I/OAPI、正则表达式、日志与断言。2004年9月30日，J2SE1.5发布，成为Java语言发展史上的又一里程碑。";

        //1创建一个Patter对象
        //Pattern compile = Pattern.compile("[a-zA-Z]+");
        //Pattern compile = Pattern.compile("[0-9]+");
        Pattern compile = Pattern.compile("([0-9]+)|([a-zA-Z]+)");

        //2创建一个匹配器对象
        Matcher matcher = compile.matcher(content);
        //循环匹配
        while (matcher.find()){
            //匹配到内容放到 m.group(0)
            System.out.println("找到：" + matcher.group(0)+" ");
        }
    }
}
