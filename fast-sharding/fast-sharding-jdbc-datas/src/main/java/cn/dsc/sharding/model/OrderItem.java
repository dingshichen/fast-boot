package cn.dsc.sharding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long id;

    private Long orderItemId;

    private Long orderId;

    private Long userId;
}