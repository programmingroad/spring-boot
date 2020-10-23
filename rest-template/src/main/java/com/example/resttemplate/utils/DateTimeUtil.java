package com.example.resttemplate.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author: programmingroad
 * @create: 2020/01/04 16:36
 * @description:
 **/
public class DateTimeUtil {

    public static final DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATETIMEFORMATTER_MINUTE = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_PATH = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_BUCKET_PATH = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter DATE_PATH_MMDD = DateTimeFormatter.ofPattern("MMdd");

    private static final DateTimeFormatter HOUR_MINUTE = DateTimeFormatter.ofPattern("HH:mm");

    public static String formatHourMinute() {
        return LocalDateTime.now().format(HOUR_MINUTE);
    }

    public static String formatHourMinute(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(HOUR_MINUTE);
    }

    public static String formatNow() {
        return format(LocalDateTime.now());
    }

    public static String formatNowDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    public static String formatDate(Long time) {
        return DATE_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    public static LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    public static String format(LocalDateTime localDateTime) {
        return localDateTime.format(defaultDateTimeFormatter);
    }

    public static String formatTimeMills(Long time) {
        return defaultDateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    public static String formatTodayStart() {
        //当天零点
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return format(todayStart);

    }

    /**
     * 格式化date类型为默认格式的字符串类型
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return format(localDateTime);

    }

    public static String formatTodayEnd() {
        //当日59:59
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return format(todayEnd);
    }

    //指定时间的零点
    public static String formatEnd(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        LocalDateTime todayEnd = LocalDateTime.of(localDate, LocalTime.MIN);
        return format(todayEnd);
    }

    //指定时间的零点
    public static String formatMinute(Timestamp timestamp) {
        return DATETIMEFORMATTER_MINUTE.format(timestamp) + ":00";
    }

    //指定时间的零点
    public static String formatTimestampStart(Timestamp timestamp) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime todayEnd = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
        return format(todayEnd);
    }

    public static String formatNowPath() {
        return formatDatePath(LocalDate.now());
    }

    public static String formatDatePath(LocalDate localDate) {
        return localDate.format(DATE_PATH);
    }

    public static String formatNowBucketPath() {
        return formatBucketPath(LocalDate.now());
    }

    public static String formatBucketPath(LocalDate localDate) {
        return localDate.format(DATE_BUCKET_PATH);
    }

    public static String formatBucketPath(final DateTimeFormatter formatter) {
        return LocalDate.now().format(formatter);
    }

    public static String formatBucketPath(Timestamp timestamp) {
        return formatBucketPath(timestamp.toLocalDateTime().toLocalDate());
    }

    /**
     * 得到unix时间戳 秒数
     *
     * @param defaultFormatDataStr
     * @return
     */
    public static Long getEpochSecond(String defaultFormatDataStr) {
        LocalDateTime dateTime = LocalDateTime.parse(defaultFormatDataStr, defaultDateTimeFormatter);
        return dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    /**
     * 得到unix时间戳 毫秒数
     *
     * @param defaultFormatDataStr
     * @return
     */
    public static Long getEpochMillSecond(String defaultFormatDataStr) {
        return getEpochSecond(defaultFormatDataStr) * 1000;
    }

    /**
     * 从unix时间戳 秒数或者时间字符串（2019-06-25 18:36:00）
     *
     * @param epochSecond
     * @return
     */
    public static String fromEpochSecond(Long epochSecond) {
        return defaultDateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.systemDefault()));
    }

    /**
     * utc time -> local time
     *
     * @param utc
     * @return
     */
    public static String UtcToLoal(String utc) {
        return defaultDateTimeFormatter.format(LocalDateTime.ofInstant(Instant.parse(utc), ZoneId.systemDefault()));
    }

}
