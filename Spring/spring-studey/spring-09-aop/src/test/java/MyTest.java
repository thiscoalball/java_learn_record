import com.jason.service.UserService;
import com.jason.service.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //这里的类型要用接口类型 因为代理的是接口 如果不用接口类型就会报错
        UserService userService =(UserService) new ClassPathXmlApplicationContext("applicationContext.xml").getBean("userService");
        userService.add();
    }
}
