package cn.dsc.spring.service.impl;

import cn.dsc.spring.service.PersionService;

import java.io.Serializable;

/**
 * @author dingShiChen
 * @since 2019/8/27
 */
public class PersionServiceImpl implements PersionService, Serializable {

	private static final long serialVersionUID = -1053226588165790104L;

	public static final String version = "1.0";
	private static String author = "dingshichen";

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void echo() {
		System.out.println("Hello World");
	}

	private void privateEcho(){
		System.out.println("Hello World privateEcho");
	}
}
