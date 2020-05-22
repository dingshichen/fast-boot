package cn.dsc.fasttest.model;

import lombok.*;

import java.util.Date;

/**
 * 角色表
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private static final long serialVersionUID = 1L;
    /**
     * pkid
     */
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 状态：0=禁用；1=启用
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 数据有效性：1=有效；0=无效
     */
    private Boolean valid;

    public static RoleBuilder builder() {
        return new RoleBuilder();
    }
}