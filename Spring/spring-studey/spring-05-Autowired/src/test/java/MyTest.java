import com.jason.pojo.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class MyTest {
    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person person = context.getBean("person", Person.class);
        person.getCat().say();
        person.getDog().say();
        System.out.println(person.toString());

        List<String> list = new ArrayList<>();

        for (String s : list) {
            System.out.println(s);
        }
    }
}
