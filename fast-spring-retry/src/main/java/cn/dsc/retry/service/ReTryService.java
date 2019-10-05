package cn.dsc.retry.service;

import org.springframework.web.client.HttpServerErrorException;

/**
 * @author dingShiChen
 * @since 2019/9/20
 */
public interface ReTryService {

	void execute(String text);

	void recover(HttpServerErrorException e);
}
