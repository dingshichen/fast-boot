package cn.dsc.springmvc.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author dingShiChen
 * @since 2019/11/2
 */
@Data
public class DateyyyyMMdd {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;
}
