package cn.dsc.springmvc.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author dingShiChen
 * @since 2019/11/2
 */
@Data
public class LocalDateVO {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
}
