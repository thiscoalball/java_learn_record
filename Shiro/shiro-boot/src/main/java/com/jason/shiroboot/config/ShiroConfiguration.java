package com.jason.shiroboot.config;

import com.jason.shiroboot.config.cache.RedisCacheManager;
import com.jason.shiroboot.realm.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    //1.创建shiroFilter  //负责拦截所有请求
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统受限资源
        //配置系统公共资源
        Map<String,String> map = new HashMap<String,String>();
        map.put("/user/login","anon");  // anon 设置为公共资源，放行要注意anon和authc的顺序
        //之前不能直接用jsp是因为这里
        map.put("/user/getImage","anon");
        map.put("login.jsp","anon");  // anon 设置为公共资源，放行要注意anon和authc的顺序
        map.put("/user/toRegister","anon");  // anon 设置为公共资源，放行要注意anon和authc的顺序
        map.put("/toIndex","authc");//authc 请求这个资源需要认证和授权

        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    //3.创建自定义realm
    @Bean
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置密码匹配算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);

        //设置解密匹配器
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        /**
         * 底下这样就开启了EhCache但是shiro这个缓存是本地缓存,也就是宕机重启后还需要去数据库重新加载数据
         * 所以我们考虑集成redis实现分布式缓存
         *
         * */
        //设置自定义的redis缓存管理器
        //设置之后就不用同一个用户每次登录都去数据库查询数据了 减轻数据库压力和提高登录速度
        customerRealm.setCacheManager(new RedisCacheManager());
        //开启认证缓存并指定缓存名称
        customerRealm.setAuthenticationCachingEnabled(true);
        customerRealm.setAuthenticationCacheName("authenticationCache");
        //开启授权缓存并指定缓存名称
        customerRealm.setAuthorizationCachingEnabled(true);
        customerRealm.setAuthorizationCacheName("authorizationCache");
        return customerRealm;
    }
}

