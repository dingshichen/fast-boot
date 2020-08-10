package cn.dsc.parser.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dingshichen
 */
@Getter
@Setter
public class FriendCmd {

    /**
     * 朋友的姓名
     */
    private String name;

    /**
     * 朋友的手机号
     */
    private String phone;
}
