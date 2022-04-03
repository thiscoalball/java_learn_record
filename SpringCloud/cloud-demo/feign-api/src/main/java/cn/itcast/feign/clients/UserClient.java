package cn.itcast.feign.clients;

import cn.itcast.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userservice")
public interface UserClient {

    //到这 他这里访问的就是 userservice这个服务里的 user/id 的接口
    @GetMapping("user/{id}")
    User findById(@PathVariable("id")long id);
}
