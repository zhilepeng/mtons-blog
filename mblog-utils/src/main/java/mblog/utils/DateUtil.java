package mblog.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 日期工具类
 * 
 * 
 */
public class DateUtil {

	/**
	 * 日期与时间
	 */
	public final static String BOTH = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期
	 */
	public final static String DATE = "yyyy-MM-dd";
	/**
	 * 时间
	 */
	public final static String TIME = "HH:mm:ss";
	
	public final static String YYYYMMDD24HHMMSS = "yyyyMMddHHmmss";
	/**
	 * 一日的毫秒数
	 */
	public static long MILLION_SECONDS_OF_DAY = 24 * 60 * 60 * 1000L;// 86400000
	/**
	 * 
	 * 一小时的毫秒数
	 */
	public static long MILLION_SECONDS_OF_HOUR = 60 * 60 * 1000L;// 3600000;

	private DateUtil() {

	}

	/**
	 * 是否有效日期
	 * 
	 * @param str
	 * @param fmt
	 * @return
	 */
	public static boolean isValidDate(String str, String fmt) {
		Date date = parse(str, fmt);
		if (date == null) {
			return false;
		}
		String dateStr = format(date, fmt);
		if (dateStr.equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 解析日期字符串
	 * 
	 * @param str
	 *            日期串
	 * @param fmt
	 *            日期格式
	 * @return
	 */
	public static Date parse(String str, String fmt) {
		SimpleDateFormat simDateFormat = new SimpleDateFormat(fmt);
		Date date = null;
		try {
			date = simDateFormat.parse(str);
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            日期
	 * @param fmt
	 *            日期格式
	 * @return
	 */
	public static String format(Date date, String fmt) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmt);
		return simpleDateFormat.format(date);
	}

	/**
	 * 计算日期加月
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonth(Date date, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
		return c.getTime();
	}

	/**
	 * 计算日期加天数
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	/**
	 * 计算日期加天数
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String addDay(String str, int days, String fmt) {
		Calendar c = Calendar.getInstance();
		c.setTime(parse(str, fmt));
		c.add(Calendar.DAY_OF_MONTH, days);
		Date date = c.getTime();
		return format(date, fmt);
	}

	/**
	 * 计算日期加分钟
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinutes(Date date, int minutes) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}

	/**
	 * 计算天数差
	 * 
	 * @param sd
	 * @param ed
	 * @return
	 */
	public static int subDateDays(Date sd, Date ed) {
		Long eds = ed.getTime();
		Long sds = sd.getTime();
		return (int) ((eds - sds) / MILLION_SECONDS_OF_DAY);
	}

	/**
	 * 计算天数差
	 * 
	 * @param sd
	 * @param ed
	 * @return
	 */
	public static int subDateDays(String sd, String ed) {
		Long eds = parse(ed, DATE).getTime();
		Long sds = parse(sd, DATE).getTime();
		return (int) ((eds - sds) / MILLION_SECONDS_OF_DAY);
	}

	/**
	 * 判断某段时间是否在指定的时间段内(包含开始时间等于指定时间段的结束时间)
	 * 
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @param tBegin
	 *            开始时间段
	 * @param tEnd
	 *            结束时间段
	 * @param fmt
	 * @return
	 */
	public static boolean isContain(String begin, String end, String tBegin, String tEnd, String fmt) {
		long beginTime = parse(begin, fmt).getTime();
		long endTime = parse(end, fmt).getTime();
		long bTime = parse(tBegin, fmt).getTime();
		long eTime = parse(tEnd, fmt).getTime();
		if (bTime > endTime || eTime <= beginTime) {
			return false;
		}
		return true;
	}

	/**
	 * 将日期字符串,转换成XMLGregorianCalendar对象
	 * 
	 * @param str
	 *            日期字符串
	 * @param fmt
	 *            日期格式
	 * @return
	 */
	public static XMLGregorianCalendar toXMLGC(String str, String fmt) {
		GregorianCalendar cal = new GregorianCalendar();
		XMLGregorianCalendar gc = null;
		try {
			Date date = parse(str, fmt);
			cal.setTime(date);
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gc;
	}

	/**
	 * 将XMLGregorianCalendar日期字符串转换成java.util.Date对象
	 * 
	 * @param str
	 * @return
	 */
	public static Date toDate(String str) {
		return DatatypeConverter.parseDate(str).getTime();
	}

	public static void main(String[] args) {
		System.out.println(isValidDate("2010-03-31", DATE));
	}
}
