package cn.dsc.springmvc.control;

import cn.dsc.springmvc.model.vo.DateValid;
import cn.dsc.springmvc.model.vo.DateyyyyMMdd;
import cn.dsc.springmvc.model.vo.DateyyyyMMddHHmmss;
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
public class DateController {

	/**
	 * 如果时间格式是：yyyy-MM-dd HH:mm:ss，可以不用增加任何设置和注解，就可以完成转换
	 * @param dateParams
	 * @return
	 */
	@PostMapping("/date")
	public DateyyyyMMddHHmmss dateParams(@RequestBody DateyyyyMMddHHmmss dateParams){
		log.info("DateController dateParams : {}", dateParams);
		return dateParams;
	}

	/**
	 * 如果时间格式是：yyyy-MM-dd，直接转换会报错
	 * 需要在转换的属性上添加 @JsonFormat(pattern = "yyyy-MM-dd") 注解，即可完成转换
	 * @param dateyyyyMMdd
	 * @return
	 */
	@PostMapping("/date2")
	public DateyyyyMMdd dateParams(@RequestBody DateyyyyMMdd dateyyyyMMdd){
		log.info("DateController dateParams : {}", dateyyyyMMdd);
		return dateyyyyMMdd;
	}


	/**
	 * 时间参数格式校验加转换，@Valid校验对时间无效
	 * 当设置 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 时，yyyy-MM-dd格式的参数会报错
	 * 当试着 @JsonFormat(pattern = "yyyy-MM-dd") 时，yyyy-MM-dd HH:mm:ss格式的参数会被忽略为yyyy-MM-dd
	 * @param dateValid
	 * @return
	 */
	@PostMapping("/dateValid")
	public DateValid dateValid(@RequestBody DateValid dateValid){
		log.info("DateController dateValid : {}", dateValid);
		return dateValid;
	}
}
