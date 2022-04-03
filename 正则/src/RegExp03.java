import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp03 {
    public static void main(String[] args) {
        //演示字符匹配使用
        String content = "a11cA8";
        //String regStr = "[a-z]";这样默认是区分大小写的
        String regStr = "(?i)[a-z]";//这样就不区分大小写了
        //以及(?i)abc表示abc都不区分大小写
        //    a(?i)bc表示bc不区分大小写
        //    a((?i)b)c表示只有b区分大小写
        Pattern compile = Pattern.compile(regStr);
        //Pattern compile = Pattern.compile(regStr,Pattern.CASE_INSENSITIVE);这也表示不区分大小写
        Matcher matcher = compile.matcher(content);
        while (matcher.find()){
            System.out.println("找到 "+matcher.group(0));
        }
    }
}
