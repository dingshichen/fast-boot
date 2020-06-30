package cn.dsc.sharding.mapper;

import cn.dsc.sharding.BaseTestConfiguration;
import cn.dsc.sharding.model.Order;
import cn.dsc.sharding.model.OrderItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderItemMapperTest extends BaseTestConfiguration {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Test
    public void insertSelective() {
        Order order = orderMapper.selectByOrderId(484757023460687872L);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getOrderId());
        orderItem.setUserId(order.getUserId());

        orderItemMapper.insertSelective(orderItem);

        selectByOrderItemId(orderItem.getOrderItemId());
    }

    private void selectByOrderItemId(Long orderItemId) {
        OrderItem orderItem = orderItemMapper.selectByOrderItemId(orderItemId);
        System.out.println(orderItem);
    }
}