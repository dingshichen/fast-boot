package cn.dsc.spring.test.beanUtil;

import lombok.Data;

import java.util.Date;

/**
 * @author dingShiChen
 * @since 2019/9/19
 */
@Data
public class User {

	private String name;

	private String phone;

	private Date createDate;
}
