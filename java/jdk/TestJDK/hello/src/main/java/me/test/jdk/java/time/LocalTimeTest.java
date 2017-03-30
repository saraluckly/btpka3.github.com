package me.test.jdk.java.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Duration Test
 */
public class LocalTimeTest {


    public static void main(String[] args) throws InterruptedException {

        dateToLocalDateTime();
        localDateTimeToDate();
        formatLocalDateTime();
        settingLocalDateTime();
        addLocalDateTime();
    }


    // LocalDateTime <- Instant <- Date
    static void dateToLocalDateTime() {
        System.out.println("============================= dateToLocalDateTime");
        Date date = new Date();
        System.out.println("Date          : " + date);
        Instant instant = date.toInstant();
        System.out.println("Instant       : " + instant);
        //LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("LocalDateTime : " + ldt);
    }

    // LocalDateTime -> Instant -> Date
    static void localDateTimeToDate() {
        System.out.println("============================= localDateTimeToDate");
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("LocalDateTime : " + ldt);


        // Instant instant = ldt.toInstant(ZoneOffset.UTC);
        Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println("Instant       : " + instant);
        Date date = Date.from(instant);
        System.out.println("Date          : " + date);
    }

    static void formatLocalDateTime() {
        System.out.println("============================= formatLocalDateTime");
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("LocalDateTime : " + ldt);
        String text = ldt.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("formated str  : " + text);
        LocalDateTime parsedDateLdt = LocalDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("parsed        : " + parsedDateLdt);
    }

    static void settingLocalDateTime() {
        System.out.println("============================= settingLocalDateTime");
        // 秒，
        // 毫秒 (ms, )
        // 微秒 (μs, microsecond)
        // 纳秒 (ns, nanosecond)
        LocalDateTime ldt = LocalDateTime.of(2000, 1, 2, 11, 22, 33, 444 * 1000 * 1000);
        System.out.println("LocalDateTime : " + ldt);
    }

    static void addLocalDateTime() {
        System.out.println("============================= addLocalDateTime");

        LocalDateTime ldt = LocalDateTime.of(2000, 1, 2, 11, 22, 33, 444 * 1000 * 1000);

        LocalDateTime newLdt = ldt
                .minusYears(1)
                .plusMonths(1)
                .plusDays(1)
                .plusHours(1)
                .plusMinutes(1)
                .plusSeconds(1)
                .plusNanos(1 * 1000 * 1000);

        System.out.println("LocalDateTime     : " + ldt);
        System.out.println("new LocalDateTime : " + newLdt);
    }

}
