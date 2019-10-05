package cn.dsc.webflux.service.impl;

import cn.dsc.webflux.model.Writer;
import cn.dsc.webflux.service.ClientServcie;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Optional;

/**
 * Spring WebFlux 提供WebClient类来给开发者进行请求调用，比RestTempate还要强大
 * @author dingShiChen
 * @since 2019/9/21
 */
@Service
public class ClientServiceImpl implements ClientServcie {

	@Override
	public Flux<Writer> post() {
		//创建WebClient对象，并设置请求基础路径
		WebClient webClient = WebClient.create("http://localhost:8080");
		//定义post请求
		Mono<Writer> writerMono = webClient.post()
				//接口url
				.uri("/book/1")
				//请求体为json数据流
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				//请求体内容
				.body(Mono.just(new Writer()), Writer.class)
				//接受请求结果类型
				.accept(MediaType.APPLICATION_STREAM_JSON)
				//设置请求结果检索规则
				.retrieve()
				//客户端错误处理
				.onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
						response -> Mono.empty())
				//将结果体转换成Mono封装的数据流
				.bodyToMono(Writer.class);
		//获取服务器发布的数据流，此实才会发送请求！！！
//		Writer writer = writerMono.block();
		Optional<Writer> writerOptinal = writerMono.blockOptional(Duration.ofSeconds(3L));
		return Flux.just(writerOptinal.orElse(new Writer()));
	}
}
