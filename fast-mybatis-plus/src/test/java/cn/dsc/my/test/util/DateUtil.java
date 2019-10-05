package cn.dsc.my.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dingShiChen
 * @since 2019/8/31
 */
public class DateUtil {

	private final static String DATA_1 = "yyyy-MM-dd";

	public static Date stringToDate(String text) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(DATA_1);
		return format.parse(text);
	}

	public static String dataToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat(DATA_1);
		return format.format(date);
	}
}
