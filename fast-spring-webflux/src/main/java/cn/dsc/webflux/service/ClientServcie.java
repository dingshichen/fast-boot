package cn.dsc.webflux.service;

import cn.dsc.webflux.model.Writer;
import reactor.core.publisher.Flux;

/**
 * @author dingShiChen
 * @since 2019/9/21
 */
public interface ClientServcie {

	Flux<Writer> post();
}
