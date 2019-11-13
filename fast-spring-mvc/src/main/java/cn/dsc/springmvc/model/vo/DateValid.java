package cn.dsc.springmvc.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author dingShiChen
 * @since 2019/11/2
 */
@Data
public class DateValid {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;
}
