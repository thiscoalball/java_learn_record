import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexp05 {
    public static void main(String[] args) {
        String content = "11111111aaaahello";

        //String regStr = "a{3}";//表示匹配 aaa
        //String regStr = "1{4}";//表示匹配 1111
        //String regStr = "\\d{2}";//表示匹配 2位的任意数字字符
        //String regStr = "a{3,4}";//表示匹配 aaa 或者 aaaa
        // 细节：java匹配默认是贪婪匹配 尽可能匹配多的所以是aaaa
        //String regStr = "\\d{2,5}";//表示匹配 两位数 或者 三位数 四位数 五位数的组合 也是贪婪匹配
        //String regStr = "1+";//表示匹配 一个1或者多个1 默认贪婪匹配
        //String regStr = "1a?";
        String regStr = "1b?";

        Pattern compile = Pattern.compile(regStr);

        Matcher matcher = compile.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
        }
    }
}
