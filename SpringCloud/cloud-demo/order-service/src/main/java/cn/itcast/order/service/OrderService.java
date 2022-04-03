package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserClient userClient;
    //@Autowired
    //private RestTemplate restTemplate;
    //public Order queryOrderById(Long orderId) {
    //    // 1.查询订单
    //    Order order = orderMapper.findById(orderId);
    //    // 2.利用restTemplate发起请求 远程调用
    //    String url = "http://userservice/user/"+order.getUserId();
    //    User user = restTemplate.getForObject(url, User.class);
    //    order.setUser(user);
    //    // 4.返回
    //    return order;
    //}
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        // 4.返回
        return order;
    }
}
