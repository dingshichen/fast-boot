package cn.dsc.sharding.mapper;

import cn.dsc.sharding.model.Order;

import java.util.List;

public interface OrderMapper {

    int insertSelective(Order record);

    Order selectByOrderId(Long orderId);

    List<Order> selectAll();
}