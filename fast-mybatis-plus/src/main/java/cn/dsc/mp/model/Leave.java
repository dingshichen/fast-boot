package cn.dsc.mp.model;

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
 * @since 2019/9/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("`leave`")	//leave是数据库的关键字，这里要加``符号
public class Leave {

	@TableId(type = IdType.AUTO)
	private Long id;

	private Boolean valid;

	private Long empId;

	private Date createDate;
}
