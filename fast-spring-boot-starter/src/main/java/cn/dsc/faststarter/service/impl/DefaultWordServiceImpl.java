package cn.dsc.faststarter.service.impl;

import cn.dsc.faststarter.service.WordService;

/**
 * @author dingShiChen
 * @since 2019/8/30
 */
public class DefaultWordServiceImpl implements WordService {

	private String prefix;
	private String suffix;

	public DefaultWordServiceImpl(String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
	}

	@Override
	public String getMyWord() {
		return this.prefix + "-SDA51V8-" + this.suffix;
	}
}
