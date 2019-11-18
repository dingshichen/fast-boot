package cn.dsc.test.time;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * ●Instant 它代表的是时间戳，比如2016-04-14T14:20:13.592Z，这可以从java.time.Clock类中获取，像这样： Instant current = Clock.system(ZoneId.of("Asia/Tokyo")).instant();
 *
 * ●LocalDate 它表示的是不带时间的日期，比如2016-04-14。它可以用来存储生日，周年纪念日，入职日期等。
 *
 * ●LocalTime - 它表示的是不带日期的时间
 *
 * ●LocalDateTime - 它包含了时间与日期，不过没有带时区的偏移量
 *
 * ●ZonedDateTime - 这是一个带时区的完整时间，它根据UTC/格林威治时间来进行时区调整
 *
 * ●这个库的主包是java.time，里面包含了代表日期，时间，瞬时以及持续时间的类。它有两个子package，一个是java.time.foramt，这个是什么用途就很明显了，还有一个是java.time.temporal，它能从更低层面对各个字段进行访问。
 *
 * ●时区指的是地球上共享同一标准时间的地区。每个时区都有一个唯一标识符，同时还有一个地区/城市(Asia/Tokyo)的格式以及从格林威治时间开始的一个偏移时间。比如说，东京的偏移时间就是+09:00。
 *
 * ●OffsetDateTime类实际上包含了LocalDateTime与ZoneOffset。它用来表示一个包含格林威治时间偏移量（+/-小时：分，比如+06:00或者 -08：00）的完整的日期（年月日）及时间（时分秒，纳秒）。
 *
 * ●DateTimeFormatter类用于在Java中进行日期的格式化与解析。与SimpleDateFormat不同，它是不可变且线程安全的，如果需要的话，可以赋值给一个静态变量。DateTimeFormatter类提供了许多预定义的格式器，你也可以自定义自己想要的格式。当然了，根据约定，它还有一个parse()方法是用于将字符串转换成日期的，如果转换期间出现任何错误，它会抛出DateTimeParseException异常。类似的，DateFormatter类也有一个用于格式化日期的format()方法，它出错的话则会抛出DateTimeException异常。
 *
 * ●再说一句，“MMM d yyyy”与“MMm dd yyyy”这两个日期格式也略有不同，前者能识别出"Jan 2 2014"与"Jan 14 2014"这两个串，而后者如果传进来的是"Jan 2 2014"则会报错，因为它期望月份处传进来的是两个字符。为了解决这个问题，在天为个位数的情况下，你得在前面补0，比如"Jan 2 2014"应该改为"Jan 02 2014"。
 *
 *  转载：https://www.cnblogs.com/comeboo/p/5378922.html
 * @author dingShiChen
 * @since 2019/8/28
 */
public class TestLocalTime {

	/**
	 * 获取当前的时间
	 * 默认的格式是hh:mm:ss:nnn
	 */
	@Test
	public void testNowTime(){
		LocalTime time = LocalTime.now();
		System.out.println("当前时间：" + time);
	}

	/**
	 * 当前时间戳 2019-02-13T02:18:33.426Z
	 */
	@Test
	public void testNowTime02(){
		Instant instant = Instant.now();
		System.out.println("当前时间戳：" + instant);
	}

	/**
	 * 时间调整
	 */
	@Test
	public void test02(){
		LocalTime time = LocalTime.now();
		System.out.println("两小时之后的时间是：" + time.plusHours(2L));
		System.out.println("一分钟之前的时间是：" + time.minusMinutes(1L));
	}


	@Test
	public void dataTimeFormatter(){
		System.out.println("1 - " + DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println("2 - " + DateTimeFormatter.ISO_DATE);
		System.out.println("3 - " + DateTimeFormatter.ISO_DATE_TIME);
		System.out.println("4 - " + DateTimeFormatter.ISO_INSTANT);
		System.out.println("5 - " + DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println("6 " + DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		System.out.println("7 - " + DateTimeFormatter.ISO_LOCAL_TIME);
		System.out.println("8 - " + DateTimeFormatter.ISO_OFFSET_DATE);
		System.out.println("9 - " + DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		System.out.println("10 - " + DateTimeFormatter.ISO_OFFSET_TIME);
		System.out.println("11 - " + DateTimeFormatter.ISO_ORDINAL_DATE);
		System.out.println("12 - " + DateTimeFormatter.ISO_TIME);
		System.out.println("13 - " + DateTimeFormatter.ISO_WEEK_DATE);
		System.out.println("14 - " + DateTimeFormatter.ISO_ZONED_DATE_TIME);
	}

	@Test
	public void stringParseTime(){
		String time = "2019-09-02 00:00:00";
		LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime result = localDateTime.plusDays(7);
		System.out.println(result);
		System.out.println(result.isBefore(LocalDateTime.now()));
	}

}
