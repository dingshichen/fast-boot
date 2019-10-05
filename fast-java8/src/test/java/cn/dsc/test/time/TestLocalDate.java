package cn.dsc.test.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.MonthDay;

/**
 * java8 的新日期 api
 * @author dingShiChen
 * @since 2019/8/28
 */
public class TestLocalDate {

	/**
	 * 获取当前日期，yyyy-MM-dd
	 */
	@Test
	public void testNowDate(){
		LocalDate now = LocalDate.now();
		System.out.println("当前日期：" + now);
		System.out.println("当前日期：" + now.toString().replace("-", ""));
	}

	/**
	 * 获取当前年月日
	 */
	@Test
	public void testYMD(){
		LocalDate now = LocalDate.now();
		System.out.println("年：" + now.getYear());
		System.out.println("月：" + now.getMonthValue());
		System.out.println("日：" + now.getDayOfMonth());
	}

	/**
	 * 判断是否是今天，比较
	 * isBefore()、isAfter()、equals() 来比较两个日期
	 */
	@Test
	public void testEqualsDay(){
		LocalDate localDate = LocalDate.of(2019, 2, 12);
		System.out.println(localDate);
		LocalDate now = LocalDate.now();
		System.out.println("是否是今天：" + localDate.equals(now));
		System.out.println("是否是之前的日期：" + localDate.isBefore(now));
		System.out.println("是否是之后的日期：" + localDate.isAfter(now));
	}

	/**
	 * MonthDay 的比较
	 */
	@Test
	public void testEqualsMonth(){
		LocalDate localDate = LocalDate.of(1999, 8, 28);
		MonthDay localMonth = MonthDay.from(localDate);
		LocalDate now = LocalDate.now();
		MonthDay nowMonth = MonthDay.of(now.getMonth(), now.getDayOfMonth());
		if(localMonth.equals(nowMonth)){
			System.out.println("今天是你的生日");
		} else {
			System.out.println("今天不是你的生日");
		}
	}

	/**
	 * 时间调整计算
	 */
	@Test
	public void testPlus(){
		LocalDate now = LocalDate.now();
		System.out.println("今天之后的一个星期是" + now.plusWeeks(1L));
		System.out.println("今天的前一天是" + now.minusDays(1L));
	}
}
