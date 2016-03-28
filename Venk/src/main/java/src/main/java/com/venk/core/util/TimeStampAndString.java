package src.main.java.com.venk.core.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: TimeStamp 与 String 类型的日期互相转换
 * @author: Venk007
 * @date: 2016年3月28日下午5:03:43 
 *
 */
public class TimeStampAndString {

	/**
	 * TimeStamp转换成String格式(YYYY-MM-DD)
	 * 
	 * @param Timestamp
	 * @return String
	 * @throws Exception
	 */
	public static String timeToStringDate(Timestamp ts) throws Exception {

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (ts != null) {
				return sdf.format(ts);
			}
		} catch (Exception e) {
			/*logger.warn("TimeStamp转换String格式出错", e);*/
			System.out.println("TimeStamp转换String格式出错");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * TimeStamp转换成String格式(YYYY-MM-DD HH:MM:SS)
	 * 
	 * @param Timestamp
	 * @return String
	 * @throws Exception
	 */
	public static String timeToStringDateTime(Timestamp ts) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (ts != null) {
				return sdf.format(ts);
			}
		} catch (Exception e) {
			System.out.println("TimeStamp转换String格式出错");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * String格式(YYYY-MM-DD)转换成TimeStamp
	 * 
	 * @param Timestamp
	 * @return String
	 * @throws Exception
	 */
	public static Timestamp stringToTime(String strTime) throws Exception {

		String dateNew = "";
		if ((strTime != null) && (!"".equals(strTime))) {
			try {
				dateNew = strTime.concat(" 00:00:00"); // 默认添加时分秒为0时0分0秒
				Timestamp time = new Timestamp(System.currentTimeMillis());
				time = Timestamp.valueOf(dateNew);
				return time;
			} catch (Exception e) {
				System.out.println("String日期格式转换TimeStamp异常");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public static void main(String[] args) throws Exception {
		
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		String dateStr = timeToStringDate(time);
		String dateTimeStr = timeToStringDateTime(time);
		Timestamp strToTime = stringToTime(dateStr);

		System.out.println("dateStr: \n" + dateStr);
		System.out.println("\ndateTimeStr: \n" + dateTimeStr);
		System.out.println("\nstrToTime: \n" + strToTime);
		
	}

}
