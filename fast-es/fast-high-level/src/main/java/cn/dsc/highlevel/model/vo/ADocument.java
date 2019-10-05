package cn.dsc.highlevel.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dingShiChen
 * @since 2019/9/25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("st_av")
public class ADocument {

	@TableId(type = IdType.AUTO)
	private Long id;

	@TableField("createdate")
	private Date createDate;

	private String title;

	private String designation;

	private String plot;

	private String image;
}
