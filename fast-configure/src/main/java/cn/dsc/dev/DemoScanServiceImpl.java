package cn.dsc.dev;

import cn.dsc.conf.service.DemoScanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author dingShiChen
 * @since 2019/8/29
 */
@Slf4j
@Component
public class DemoScanServiceImpl implements DemoScanService {

	public DemoScanServiceImpl() {
		init();
	}

	@Override
	public void init() {
		log.info("DemoScanServiceImpl init success");
	}
}
