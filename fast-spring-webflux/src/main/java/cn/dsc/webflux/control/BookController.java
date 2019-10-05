package cn.dsc.webflux.control;

import cn.dsc.webflux.model.Writer;
import cn.dsc.webflux.service.ClientServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 与 SpringMVC 不同德是，Spring WebFlux 使用的是WebHandler，它与DispatcherSevlet有异曲同工之妙
 * 与 SpringMVC 一样，Spring WebFlux 也可以用使用@Controller、@RestController、@GetMapping等注解。并且对于WebFlux而言，使用REST风格更为合适
 * @author dingShiChen
 * @since 2019/9/21
 */
@RestController
public class BookController {

	@Autowired
	private ClientServcie clientServcie;

	/**
	 * WebFlux的返回值类型只有这两种
	 * Flux是一个0~N个数据流序列
	 * @param id
	 * @return
	 */
	@GetMapping("/book/{id}")
	public Flux<String> book(@PathVariable("id") Long id){
		if(id == 1L){
			return Flux.just("Think in java");
		}
		return Flux.just("fail");
	}

	/**
	 * Mono是一个0~1个数据流序列
	 * @param name
	 * @return
	 */
	@GetMapping("/writer/{name}")
	public Mono<Writer> writer(@PathVariable("name") String name){
		if("laozi".equals(name)){
			Writer writer = new Writer();
			writer.setBook("dao de jing");
			writer.setName("laozi");
			return Mono.just(writer);
		}
		return Mono.just(new Writer());
	}

	@GetMapping("/client")
	public Flux<Writer> client(){
		return clientServcie.post();
	}
}
