import com.jason.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //获取spring的上下文对象

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象现在都在spring中进行管理了 需要的话就从Spring里取出来
        Hello hello = (Hello) context.getBean("hello");
        hello.show();
        Hello hello1 = context.getBean("hello", Hello.class);
    }
}
