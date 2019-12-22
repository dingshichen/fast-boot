package cn.dsc.security.model.po;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
    * 主键自增
    */
    private Long id;

    /**
    * 数据有效性（1有效，0无效）
    */
    private Boolean valid;

    /**
    * 用户名称
    */
    private String name;

    /**
    * 密码
    */
    private String pwd;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 角色ID
    */
    private Long roleId;

    private static final long serialVersionUID = 1L;
}