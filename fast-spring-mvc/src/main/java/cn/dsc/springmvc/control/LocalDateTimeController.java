package cn.dsc.springmvc.control;

import cn.dsc.springmvc.model.vo.LocalDateTimeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingShiChen
 * @since 2019/11/2
 */
@Slf4j
@RestController
public class LocalDateTimeController {

	/**
	 * 需要设置 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") ，yyyy-MM-dd 会报错，只接受parttern格式的参数
	 * @param vo
	 * @return
	 */
	@PostMapping("/localDateTime")
	public LocalDateTimeVO localDateTime(@RequestBody LocalDateTimeVO vo){
		log.info("LocalDateTimeController vo : {}", vo);
		return vo;
	}
}
