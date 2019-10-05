package cn.dsc.jwt.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 用户模型
 * @author dingShiChen
 * @since 2019/9/20
 */
@Data
public class User {

	@NotEmpty
	private String name;
	@NotEmpty
	private String pwd;

}
