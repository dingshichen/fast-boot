package cn.dsc.p6spy.entity;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author dingshichen
 */
@Data
public class Role {

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
}