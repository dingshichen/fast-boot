package cn.dsc.faststarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dingShiChen
 * @since 2019/8/30
 */
@ConfigurationProperties("fast.word")
public class WordProperties {

	private String enabled;
	private String prefix;
	private String suffix;

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}
