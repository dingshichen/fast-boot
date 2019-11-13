package cn.dsc.springmvc.control;

import cn.dsc.springmvc.model.vo.LocalDateVO;
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
public class LocalDateController {

	/**
	 * 需要设置 @JsonFormat(pattern = "yyyy-MM-dd")，yyyy-MM-dd HH:mm:ss 会报错，只接受parttern格式的参数
	 * @param vo
	 * @return
	 */
	@PostMapping("/localDate")
	public LocalDateVO localDate(@RequestBody LocalDateVO vo){
		log.info("LocalDateController localDate vo : {}", vo);
		return vo;
	}

}
