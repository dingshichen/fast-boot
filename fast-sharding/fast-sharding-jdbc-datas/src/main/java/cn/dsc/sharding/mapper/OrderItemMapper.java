package cn.dsc.sharding.mapper;

import cn.dsc.sharding.model.OrderItem;

public interface OrderItemMapper {

    int insertSelective(OrderItem record);

    OrderItem selectByOrderItemId(Long OrderItemId);
}