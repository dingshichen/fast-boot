package cn.dsc.mp.model;

import cn.dsc.mp.constant.Sex;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dingShiChen
 * @since 2019/8/31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("emp")
public class Emp {

	@TableId(type = IdType.AUTO)
	private Long id;

	private Boolean valid;

	private Sex sex;

	private String name;

	private String phone;

	private Integer salary;

	private Date birthday;


}
