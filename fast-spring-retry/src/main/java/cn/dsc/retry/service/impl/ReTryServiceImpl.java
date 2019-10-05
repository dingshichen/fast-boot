package cn.dsc.retry.service.impl;

import cn.dsc.retry.service.ReTryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

/**
 * @author dingShiChen
 * @since 2019/9/20
 */
@Slf4j
@Service
public class ReTryServiceImpl implements ReTryService {

	@Retryable(value = {HttpServerErrorException.class}, backoff = @Backoff(delay = 10000L, multiplier = 2D))
	@Async
	@Override
	public void execute(String text) {
		log.info("测试重试执行 : {}", text);
		if(true){
			throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Recover
	@Override
	public void recover(HttpServerErrorException e) {
		log.info("重试达到上线");
	}


}
