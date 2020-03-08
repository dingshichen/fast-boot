package cn.dsc.test.time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author dingshichen
 */
public class TestLocalDateTime {

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
