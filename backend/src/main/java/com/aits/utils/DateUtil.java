package com.aits.utils;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jared
 */
public class DateUtil {
	private static Logger logger = Logger.getLogger(DateUtil.class);

	private static Map<String, SimpleDateFormat> formats;
	private static final String DATE_FORMATE_STRING_DEFAULT = "yyyyMMddHHmmss";
	private static final String DATE_FORMATE_STRING_A = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FORMATE_STRING_B = "yyyy-MM-dd";
	private static final String DATE_FORMATE_STRING_C = "MM/dd/yyyy HH:mm:ss a";
	private static final String DATE_FORMATE_STRING_D = "yyyy-MM-dd HH:mm:ss a";
	private static final String DATE_FORMATE_STRING_E = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	private static final String DATE_FORMATE_STRING_F = "yyyy-MM-dd'T'HH:mm:ssZ";
	private static final String DATE_FORMATE_STRING_G = "yyyy-MM-dd'T'HH:mm:ssz";
	private static final String DATE_FORMATE_STRING_H = "yyyyMMdd";
	private static final String DATE_FORMATE_STRING_I = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String DATE_FORMATE_STRING_J = "yyyyMMddHHmmss.SSS";
	private static final String DATE_FORMATE_STRING_K = "yyMMDDHHmm";
	private static final String DATE_FORMATE_STRING_L = "yyMMdd";
	private static final String DATE_FORMATE_STRING_M = "yyMM";
	private static final String DATE_FORMATE_STRING_N = "yyMMddHHmmss";

	private DateUtil() {
	}

	public static String addDay(Date date, int n) {
		if (date == null)
			return null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DAY_OF_MONTH, n);
		return asString(gc.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String addMinutes(Date date, int n) {
		if (date == null)
			return null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MINUTE, n);
		return asString(gc.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	public static Date parseDate(String dateStr, String pattern) {
		if ((dateStr == null) || (dateStr.length() == 0) || (pattern == null) || (pattern.length() == 0))
			return null;
		DateFormat fmt = new SimpleDateFormat(pattern);
		Date result = null;
		try {
			result = fmt.parse(dateStr);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	public static String asString(Date date, String pattern) {
		if (date == null)
			return null;
		DateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(date);
	}

	public static Date getCurrentDate() {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date result = null;
		try {
			result = fmt.parse(new Timestamp(System.currentTimeMillis()).toString());
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	public static Timestamp getSysTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	static {
		formats = new HashMap<String, SimpleDateFormat>();

		formats.put(DATE_FORMATE_STRING_DEFAULT, new SimpleDateFormat(DATE_FORMATE_STRING_DEFAULT));
		formats.put(DATE_FORMATE_STRING_A, new SimpleDateFormat(DATE_FORMATE_STRING_A));
		formats.put(DATE_FORMATE_STRING_B, new SimpleDateFormat(DATE_FORMATE_STRING_B));
		formats.put(DATE_FORMATE_STRING_C, new SimpleDateFormat(DATE_FORMATE_STRING_C));
		formats.put(DATE_FORMATE_STRING_D, new SimpleDateFormat(DATE_FORMATE_STRING_D));
		formats.put(DATE_FORMATE_STRING_E, new SimpleDateFormat(DATE_FORMATE_STRING_E));
		formats.put(DATE_FORMATE_STRING_F, new SimpleDateFormat(DATE_FORMATE_STRING_F));
		formats.put(DATE_FORMATE_STRING_G, new SimpleDateFormat(DATE_FORMATE_STRING_G));
		formats.put(DATE_FORMATE_STRING_H, new SimpleDateFormat(DATE_FORMATE_STRING_H));
		formats.put(DATE_FORMATE_STRING_I, new SimpleDateFormat(DATE_FORMATE_STRING_I));
		formats.put(DATE_FORMATE_STRING_J, new SimpleDateFormat(DATE_FORMATE_STRING_J));
		formats.put(DATE_FORMATE_STRING_K, new SimpleDateFormat(DATE_FORMATE_STRING_K));
		formats.put(DATE_FORMATE_STRING_L, new SimpleDateFormat(DATE_FORMATE_STRING_L));
		formats.put(DATE_FORMATE_STRING_M, new SimpleDateFormat(DATE_FORMATE_STRING_M));
		formats.put(DATE_FORMATE_STRING_N, new SimpleDateFormat(DATE_FORMATE_STRING_N));
	}

	/**
	 * 将Date转换为 pattern 格式的字符串，格式为： yyyyMMddHHmmss yyyy-MM-dd HH:mm:ss
	 * yyyy-MM-dd MM/dd/yyyy HH:mm:ss a yyyy-MM-dd HH:mm:ss a
	 * yyyy-MM-dd'T'HH:mm:ss'Z' yyyy-MM-dd'T'HH:mm:ssZ yyyy-MM-dd'T'HH:mm:ssz
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式
	 * @return String --格式化的日期字符串
	 * @see java.util.Date
	 */
	public static String getFormatTimeString(Date date, String pattern) {
		SimpleDateFormat sDateFormat = getDateFormat(pattern);
		return sDateFormat.format(date);
	}

	/**
	 * 将Date转换为默认的YYYYMMDDHHMMSS 格式的字符串
	 *
	 * @param date
	 * @return
	 */
	public static String getDefaultFormateTimeString(Date date) {
		return getFormatTimeString(date, DATE_FORMATE_STRING_DEFAULT);
	}

	/**
	 * 将Date转换为默认的YYYY-MM-DD 格式的字符串
	 *
	 * @param date
	 * @return
	 */
	public static String getFormateStringB(Date date) {
		return getFormatTimeString(date, DATE_FORMATE_STRING_B);
	}

	/**
	 * 根据pattern取得的date formate
	 *
	 * @param pattern
	 * @return
	 */
	public static SimpleDateFormat getDateFormat(String pattern) {
		SimpleDateFormat sDateFormat = formats.get(pattern);
		if (sDateFormat == null) {
			sDateFormat = new SimpleDateFormat(pattern);
			formats.put(pattern, sDateFormat);
		}
		return sDateFormat;
	}

	/**
	 * 将格式将日期字符串转换为Date对象
	 *
	 * @param date
	 *            字符串
	 * @param pattern
	 *            格式如下： yyyyMMddHHmmss yyyy-MM-dd HH:mm:ss yyyy-MM-dd MM/dd/yyyy
	 *            HH:mm:ss a yyyy-MM-dd HH:mm:ss a yyyy-MM-dd'T'HH:mm:ss'Z'
	 *            yyyy-MM-dd'T'HH:mm:ssZ yyyy-MM-dd'T'HH:mm:ssz
	 * @return 日期Date对象
	 * @throws ParseException
	 * @see java.util.Date
	 */
	public static Date getDateFromString(String date, String pattern) throws ParseException {
		SimpleDateFormat sDateFormat = getDateFormat(pattern);
		return sDateFormat.parse(date);
	}

	/**
	 * 将日期字符串转化成默认格式YYYYMMDDHHMMSS的Date对象
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getDefaultDateFromString(String date) throws ParseException {
		return getDateFromString(date, DATE_FORMATE_STRING_DEFAULT);
	}

	/**
	 * 取当前时间,格式为YYYYMMDDHHMMSS的日期字符串
	 *
	 * @return 当前日期字符串
	 * @throws ParseException
	 * @see java.util.Date
	 */
	public static String getNowDefault() {
		return getNow(DATE_FORMATE_STRING_DEFAULT);
	}

	/**
	 * 取当前时间,格式为YYMMDDHHMMSS的日期字符串
	 *
	 * @return 当前日期字符串
	 * @throws ParseException
	 * @see java.util.Date
	 */
	public static String getNowYYMMDDHHmmss() {
		return getNow(DATE_FORMATE_STRING_N);
	}

	/**
	 * 按照pattern格式取当前日期字符串
	 *
	 * @param pattern
	 *            日期字符串格式
	 * @return 格式化后的当前日期字符串
	 */
	public static String getNow(String pattern) {
		return getFormatTimeString(new Date(), pattern);
	}

	/**
	 * 取当前时间,格式为YYYYMMDD
	 *
	 * @return 当前日期字符串
	 * @throws ParseException
	 * @see java.util.Date
	 */
	public static String getNowII() {
		return getFormatTimeString(new Date(), DATE_FORMATE_STRING_H);
	}

	/**
	 * 取当前时间,格式为YYMMDD
	 *
	 * @return 当前日期字符串
	 * @throws ParseException
	 * @see java.util.Date
	 */
	public static String getNowYYMMDD() {
		return getFormatTimeString(new Date(), DATE_FORMATE_STRING_L);
	}

	/**
	 * 取当前时间,格式为YYMM
	 *
	 * @return 当前日期字符串
	 * @throws ParseException
	 * @see java.util.Date
	 */
	public static String getNowYYMM() {
		return getFormatTimeString(new Date(), DATE_FORMATE_STRING_M);
	}

	/**
	 * 将输入pattern格式的日期字符串转换为取时间的毫秒数 since 1976
	 *
	 * @return 时间毫秒数
	 * @throws ParseException
	 * @see java.util.Date
	 */
	public static long dateString2Long(String str, String pattern) throws ParseException {
		return getDateFromString(str, pattern).getTime();
	}

	/**
	 * 把since1976的毫秒数转成默认格式yyyyMMddHHmmss的String日期字符串
	 *
	 * @param time
	 * @return
	 */
	public static String longToDateStringDefault(long time) {
		return getFormatTimeString(new Date(time), DATE_FORMATE_STRING_DEFAULT);
	}

	/**
	 * 将时间的毫秒数 since 1976转换为按照pattern格式的日期字符串
	 *
	 * @return 日期字符串
	 * @see java.util.Date
	 */
	public static String longToDateString(long time, String pattern) {
		return getFormatTimeString(new Date(time), pattern);
	}

	/**
	 * 将Date对象转成since 1976的毫秒数
	 * 
	 * @param date
	 * @return since1976的毫秒数
	 */
	public static long date2Long(Date date) {
		return date.getTime();
	}

	/**
	 * 将since1976毫秒数转成Date对象
	 * 
	 * @param time
	 * @return
	 */
	public static Date longToDate(long time) {
		return new Date(time);
	}

	/**
	 * 自动适配两种格式的日期字符串转换为date对象 A格式 : yyyy-MM-dd HH:mm:ss B格式 : yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFromStringAdaptTwoPattern(String date) throws ParseException {
		try {
			return getDateFromString(date, DATE_FORMATE_STRING_B);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			return getDateFromString(date, DATE_FORMATE_STRING_B);
		}
	}

	/**
	 * 计算与当前系统时间相差的秒数
	 * 
	 * @param startDate
	 * @return
	 */
	public static int calLastedTime(Date startDate) {
		long a = new Date().getTime();
		long b = startDate.getTime();
		int c = (int) ((a - b) / 1000);
		return c;
	}

	public static int calLastedTime(Date startDate, Date endDate) {
		long a = startDate.getTime();
		long b = endDate.getTime();
		int c = (int) ((b - a) / 1000);
		return c;
	}

	public static int calLastedTime(String startDate, String endDate) throws Exception {
		Date start = getDateFromString(startDate, "yyyy-MM-dd HH:mm:ss");
		Date end = getDateFromString(endDate, "yyyy-MM-dd HH:mm:ss");
		long a = start.getTime();
		long b = end.getTime();
		int c = (int) ((b - a) / 1000);
		return c;
	}

	public static Long getDistanceTimes(String str1, String str2) {

		if (str2 == null) {
			return -1L;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one;
		Date two;
		long diff = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			diff = time2 - time1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;

	}

	/**
	 * 获取前一天的日期
	 * 
	 * @return
	 */
	public static String getDateBefore() {
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
		dBefore = calendar.getTime(); // 得到前一天的时间

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		String defaultStartDate = sdf.format(dBefore); // 格式化前一天
		// String defaultEndDate = sdf.format(dNow); // 格式化当前时间

		return defaultStartDate;
	}

	/**
	 * 获取指定日期之前或者之后的日期
	 * @param days Day
	 * @return
	 */
	public static Date getPreviousOrNextDaysOfDate(Date date, int days) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DAY_OF_YEAR, days);
	    return calendar.getTime();
	}
	
	/**
	 * 比较两个字符串日期是否相等
	 * @param date1
	 * @param date2
	 * @param pattern
	 * @return
	 */
	public static boolean compateStrDate(String date1, String date2, String pattern){
		DateFormat sdf = new SimpleDateFormat(pattern);
		Date leaderDate = new Date();
		Date expiryDate = new Date();;
		try {
			leaderDate = sdf.parse(sdf.format(date1));
			expiryDate = sdf.parse(sdf.format(date2));
			return leaderDate.before(expiryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * 比较一个日期和当前系统时间是否相同
	 * @param date1
	 * @param pattern
	 * @return
	 */
	public static boolean compateStrDate(String date1, String pattern){
		try {
			String leaderDate = getFormatTimeString(new Date(),pattern);
			if(date1.equals(leaderDate)){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static void main(String[] args) {
		System.out.println(new Date());
	}
}