import org.junit.jupiter.api.Test;

import java.util.*;

public class homework1010 {
    public static void main(String[] args) {
//调用Junit单元测试类进行测试
   }
   @Test
   public void testlist(){
       DAO<User> userDAO = new DAO<>();
       userDAO.save("1",new User(1,10,"jack"));
       userDAO.save("2",new User(2,14,"tom"));
       userDAO.save("3",new User(3,19,"marry"));

       List<User> list = userDAO.list();
       System.out.println(list);

       userDAO.update("3",new User(3,58,"milan"));
       list = userDAO.list();//记得重新获取一次
       System.out.println(list);
       userDAO.delete("1");
       list = userDAO.list();//记得重新获取一次
       System.out.println(list);

       System.out.println(userDAO.get("2"));
   }
}

class DAO<T>{

    private Map<String,T> map = new HashMap<>();


    @Test
    public void save(String id,T entity){
        map.put(id,entity);
    }
    @Test
    public T get(String id){//返回id对应的t对象
        T t = map.get(id);
        return t;
    }
    @Test
    public void update(String id,T entity){//更新改变id的entity
        map.put(id,entity);
    }
    @Test
    public List<T> list(){
        List<T> list = new ArrayList<>();

        Set<String> keyset = map.keySet();//keyset方法遍历
        for(String key:keyset){
            list.add(get(key));//这里就是我们前面的返回T对象的函数 用在这就是遍历取所有的t 封装到list里
        }
        return list;
    }
    @Test
    public void delete(String id){
        map.remove(id);
    }
}

class User
{

    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}