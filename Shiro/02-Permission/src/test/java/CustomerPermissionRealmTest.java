import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

public class CustomerPermissionRealmTest {
    @Test
    public void doGetAuthenticationInfo() {
        //创建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //设置自定义realm
        CustomerPermissionRealm realm = new CustomerPermissionRealm();
        //为realm设置凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法和设置hash次数
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1024);
        //将匹配器设置到realm中
        realm.setCredentialsMatcher(credentialsMatcher);
        //将realm设置到SecurityManager
        defaultSecurityManager.setRealm(realm);
        //将SecurityManager设置到安全工具类
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //通过安全工具类获取subject
        Subject subject = SecurityUtils.getSubject();
        //创建token
        UsernamePasswordToken token = new UsernamePasswordToken("jason", "123123");
        try {
            // 登录认证
            subject.login(token);
            System.out.println("认证成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }

        if(subject.isAuthenticated()){
            System.out.println("是否具有admin角色:" + subject.hasRole("admin"));
            System.out.println("是否同时具有admin和super角色:"+subject.hasAllRoles(Arrays.asList("admin","super")));
            System.out.print("是否具有admin,super,user之一(按下标):");
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "super", "user"));
            for(boolean b:booleans){
                System.out.print(b+"  ");
            }
            System.out.println();
            //基于权限字符串的访问控制  资源标识符:操作:资源类型
            System.out.println("=========================================================================");
            System.out.println("是否有对用户1的修改权限:" + subject.isPermitted("user:update:01"));
            System.out.println("是否有对用户2的修改权限:" + subject.isPermitted("user:update:02"));
            System.out.println("是否有对商品的创建权限:"+subject.isPermitted("product:create:02"));

            //分别具有那些权限
            System.out.print("是否有这两个权限之一(按下标):");
            boolean[] permitted = subject.isPermitted("user:*:01", "order:*:10");
            for (boolean b : permitted) {
                System.out.print(b+"  ");
            }
            System.out.println();
            //同时具有哪些权限
            System.out.print("是否同时具有:"+subject.isPermittedAll("user:*:01", "product:create:02"));

        }
    }
}