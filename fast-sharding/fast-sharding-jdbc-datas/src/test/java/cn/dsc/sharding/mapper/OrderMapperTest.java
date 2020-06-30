package cn.dsc.sharding.mapper;

import cn.dsc.sharding.BaseTestConfiguration;
import cn.dsc.sharding.model.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Ignore
public class OrderMapperTest extends BaseTestConfiguration {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertSelective() {
        Order order = new Order();
        order.setUserId(12L);

        orderMapper.insertSelective(order);
    }

    @Test
    public void selectByOrderId() {
        Order order = orderMapper.selectByOrderId(482684633423544321L);
        System.out.println(order);
    }

    @Test
    public void selectAll() {
        List<Order> orders = orderMapper.selectAll();
        orders.forEach(System.out::println);
    }
}