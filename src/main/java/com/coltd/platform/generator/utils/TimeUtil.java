package com.coltd.platform.generator.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @ClassName: TimeUtil
 * @Description: 时间处理工具类
 * @author panhaicheng@gomeholdings.com
 * @date 2016年7月12日 下午1:58:45
 *
 */
public class TimeUtil {
	public static String date(String fmt) {
		return new SimpleDateFormat(fmt).format(new Date());
	}

	public static String date(String fmt, long t) {
		return new SimpleDateFormat(fmt).format(new Date(t));
	}

	public static String date8() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	public static String date8(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	public static String date8(Timestamp date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	public static String time6() {
		return new SimpleDateFormat("HHmmss").format(new Date());
	}

	public static String time6(Date date) {
		return new SimpleDateFormat("HHmmss").format(date);
	}

	public static String time6(Timestamp date) {
		return new SimpleDateFormat("HHmmss").format(date);
	}

	public static String datetime14() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public static String datetime14(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}

	public static String datetime14(long t) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(t));
	}

	public static String calcMonth(String month6, int m) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(month6.substring(0, 4)),
				Integer.parseInt(month6.substring(4, 6)) - 1, 1);
		cal.add(Calendar.MONTH, m);
		return new SimpleDateFormat("yyyyMM").format(cal.getTime());
	}

	public static String calcDay(String day8, int d) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(day8.substring(0, 4)),
				Integer.parseInt(day8.substring(4, 6)) - 1,
				Integer.parseInt(day8.substring(6, 8)));
		cal.add(Calendar.DATE, d);
		return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
	}

	public static String calcSecond(String time14, int s) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(time14.substring(0, 4)),
				Integer.parseInt(time14.substring(4, 6)) - 1,
				Integer.parseInt(time14.substring(6, 8)),
				Integer.parseInt(time14.substring(8, 10)),
				Integer.parseInt(time14.substring(10, 12)),
				Integer.parseInt(time14.substring(12, 14)));
		cal.add(Calendar.SECOND, s);
		return new SimpleDateFormat("yyyyMMddHHmmss").format(cal.getTime());
	}

	public static long toMilliSec(String time14) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(time14.substring(0, 4)),
				Integer.parseInt(time14.substring(4, 6)) - 1,
				Integer.parseInt(time14.substring(6, 8)),
				Integer.parseInt(time14.substring(8, 10)),
				Integer.parseInt(time14.substring(10, 12)),
				Integer.parseInt(time14.substring(12, 14)));
		return cal.getTimeInMillis();
	}

	public static int getActualMaximum(String day8, int field) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(day8.substring(0, 4)),
				Integer.parseInt(day8.substring(4, 6)) - 1,
				Integer.parseInt(day8.substring(6, 8)));
		return cal.getActualMaximum(field);
	}

	public static int getActualMinimum(String day8, int field) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(day8.substring(0, 4)),
				Integer.parseInt(day8.substring(4, 6)) - 1,
				Integer.parseInt(day8.substring(6, 8)));
		return cal.getActualMinimum(field);
	}

	/**
	 * 
	 * 得到系统时间年份
	 * 
	 * @return
	 */
	public static String getDateYear() {
		return new SimpleDateFormat("yyyy").format(new Date());
	}

	/**
	 * 
	 * 得到指定时间年份
	 * 
	 * @return
	 */
	public static String getYearByDate(String date) {
		return date.substring(0, 4);
	}

	/**
	 * 
	 * description: 获得当天是当年的第几周
	 * 
	 * @return
	 */
	public static int getWeekIndex() {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 
	 * description: 在now基础上增加amount个日
	 * 
	 * @param endDate
	 * @param amount
	 * @return
	 */
	public static Date addDay(Date now, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, amount);
		return cal.getTime();
	}

	/**
	 * 
	 * description: 在now基础上增加amount个日
	 * 
	 * @param endDate
	 * @param amount
	 * @return
	 */
	public static String addDay(String now, int amount) {
		Date d;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		try {
			sf.setLenient(false);
			d = sf.parse(now);
		} catch (Exception e) {
			d = new Date();
		}

		return sf.format(addDay(d, amount));
	}

	/**
	 * 
	 * description: 在now基础上增加amount个月
	 * 
	 * @param now
	 * @param amount
	 * @return
	 */
	public static Date addMonth(Date now, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.MONTH, amount);
		return cal.getTime();
	}

	/**
	 * 
	 * description: 在now基础上增加amount个年
	 * 
	 * @param endDate
	 * @param amount
	 * @return
	 */
	public static Date addYear(Date now, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.YEAR, amount);
		return cal.getTime();
	}

	/**
	 * 
	 * description: 在now基础上增加amount个年
	 * 
	 * @param endDate
	 * @param amount
	 * @return
	 */
	public static String addYear(String now, int amount) {
		Date d;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		try {
			sf.setLenient(false);
			d = sf.parse(now);
		} catch (Exception e) {
			d = new Date();
		}

		return sf.format(addYear(d, amount));
	}

	/**
	 * 
	 * description: 在now基础上增加amount个分钟
	 * 
	 * @param endDate
	 * @param amount
	 * @return
	 */
	public static Timestamp addMin(Timestamp now, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.MINUTE, amount);
		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 * 格式化日期为制定字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String foramtDate2String(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 将日期格式的字符串转换为日期
	 * 
	 * @param dateStr
	 *            源日期字符串
	 * @param formatStr
	 *            源日期字符串格式
	 */
	public static Date formatStringToDate(String dateStr, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		try {
			return format.parse(dateStr);
		} catch (Exception ex) {
			RuntimeException rex = new RuntimeException(ex.getMessage());
			rex.setStackTrace(ex.getStackTrace());
			throw rex;
		}
	}

	/**
	 * 
	 * description: 判断当前时间是否为19点以后
	 * 
	 * @param endDate
	 * @param amount
	 * @return
	 */
	public static boolean nowIsNight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int i = cal.get(Calendar.HOUR_OF_DAY);
		return i > 18;
	}

	/**
	 * 
	 * <br>
	 * description : 校验交易日期是否小于当前数据库日期指定天数
	 * 
	 * @param trace
	 * @param day
	 * @return
	 * @author Administrator
	 * @version 1.0
	 * @date Feb 9, 20122:34:25 PM
	 * @see TODO
	 */
	public static boolean checkTransDateByTrace(String trace, int day) {
		String traceDate = trace.substring(0, 6);
		return checkTransDate(traceDate, day, "yyMMdd");
	}

	/**
	 * 
	 * <br>
	 * description : 校验日期是否小于当前数据库日期的指定天数
	 * 
	 * @param datestr
	 *            需要验证的日期
	 * @param day
	 *            指定天数
	 * @param patt
	 *            日期格式,例:yyyyMMdd
	 * @return
	 * @author Wangrqa
	 * @version 1.0
	 * @date Feb 9, 20122:33:07 PM
	 * @see TODO
	 */
	public static boolean checkTransDate(String datestr, int day, String patt) {
		SimpleDateFormat format = new SimpleDateFormat(patt);
		try {
			Date date = format.parse(datestr);
			Date dbDate = format.parse(getSystemDate(patt));
			return addDay(date, day).getTime() < dbDate.getTime();
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 获取指定格式日期
	 * 
	 * @method name : getDate
	 * @return : String
	 * @param : @param format
	 * @param : @return
	 * @see :
	 * @modified : xuchaofu , Mar 8, 2012
	 * @exception :
	 */
	public static String getSystemDate(String format) {
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(System.currentTimeMillis());
	}

	/**
	 * 
	 * <br>
	 * description : 校验截止日期是否小于起始日期的指定天数
	 * 
	 * @param startDateStr
	 *            起始日期
	 * @param endDateStr
	 *            结束日期
	 * @param day
	 *            指定天数
	 * @return
	 * @author Wangrqa
	 * @version 1.0
	 * @date Feb 9, 20122:33:07 PM
	 * @see TODO
	 */
	public static boolean checkDateDays(String startDateStr, String endDateStr,
			int day) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			Date startDate = format.parse(startDateStr);
			Date endDate = format.parse(endDateStr);
			return addDay(startDate, day).getTime() > endDate.getTime();
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 
	 * <br>
	 * description : 校验截止日期是否小于起始日期的指定天数
	 * 
	 * @param startDateStr
	 *            起始日期
	 * @param endDateStr
	 *            结束日期
	 * @param day
	 *            指定天数
	 * @return
	 * @author Wangrqa
	 * @version 1.0
	 * @date Feb 9, 20122:33:07 PM
	 * @see TODO
	 */
	public static boolean checkDateBefore(String startDateStr, String endDateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			Date startDate = format.parse(startDateStr);
			Date endDate = format.parse(endDateStr);
			return startDate.getTime() <= endDate.getTime();
		} catch (ParseException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		// checkDateDays("20140701","20140702",5);
		System.out.println(checkDateBefore("20140702", "20140703"));
		;
	}
}