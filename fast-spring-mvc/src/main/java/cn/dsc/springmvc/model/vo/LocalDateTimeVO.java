package cn.dsc.springmvc.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author dingShiChen
 * @since 2019/11/2
 */
@Data
public class LocalDateTimeVO {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;
}
