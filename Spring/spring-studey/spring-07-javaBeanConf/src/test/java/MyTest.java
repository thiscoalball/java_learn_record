import com.kuang.pojo.AppConf;
import com.kuang.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        User getUser = new AnnotationConfigApplicationContext(AppConf.class).getBean("getUser", User.class);
        System.out.println(getUser);
    }
}
