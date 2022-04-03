public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //设置代理对象
        pih.setTarget(userService);
        //动态生成代理类
        UserService proxy =(UserService) pih.getProxy();
        proxy.delete();
    }
}
