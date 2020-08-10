package cn.dsc.parser.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author dingshichen
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserGetCommand extends Command {

    /**
     * 名称
     * @description 需要填写中文名
     */
    @NotEmpty
    private String name;

    /**
     * 地址
     */
    private String address = "china";

    /**
     * 年龄对象
     */
    private AgeCmd age;

    /**
     * 朋友们
     */
    private List<FriendCmd> friends;

    /**
     * id集合
     */
    private List<String> ids;
}
