package cn.dsc.cache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dingShiChen
 * @since 2019/11/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

	private static final long serialVersionUID = -3768077961658189925L;
	private String name;

	private String phone;
}
