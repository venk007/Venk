package src.main.java.com.venk.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.security.MessageDigest;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONException;
//import net.sf.json.JSONObject;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.lang.StringEscapeUtils;
//import org.apache.commons.lang.StringUtils;
//import org.jsoup.Jsoup;
//import org.jsoup.safety.Whitelist;

/**
 * 字符串操作类
 * 
 */
public class StringUtil {
	/**
	 * 对字符串 escape 编码
	 * 
	 * Venk
	 * 2015-11-05
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();

		// void ensuerCapacity(int minimumCapacity)
		// 确保容量至少等于指定的最小值,提高初始化速度
		// 容量最大的情况为全unicode字符编码，%uXXXX，所以长度*6
		tmp.ensureCapacity(src.length() * 6);

		for (i = 0; i < src.length(); i++) {

			j = src.charAt(i);

			// 如果是数字或大小写字母，则不进行转码
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				// 字符小于16位时，在前面补0，以满足两位长度的16位编码
				if (j < 16)
					tmp.append("0");
				// toString()的基数为16，将字符转换为16进制
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		// 返回编码后的字符串
		return tmp.toString();
	}
	
	/**
	 * 对编码的字符串解码
	 * 
	 * Venk
	 * 2015-11-05
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		
		// 解码后字符串的长度不会大于解码前
		// 最大情况:全数字和大小写字符，长度与编码前相等
		tmp.ensureCapacity(src.length());
		
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()){
			// 从lastPos位置开始找"%"，并返回找到"%"的位置
			pos = src.indexOf("%",lastPos);
			if (pos == lastPos)	{
				// 判断是否为unicode字符
				if (src.charAt(pos + 1) =='u'){
					// 将"%u"后面4位的unicode编码转换为字符
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					// 将"%"后面的2位编码转换为字符
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3),16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					// 未找到"%"则将后面的数字和字母全部添加
					tmp.append(src.substring(lastPos));
					// 查找到最后，跳出while循环
					lastPos = src.length();
				} else {
					
					/*
					 * ???why???
					 */
					tmp.append(src.substring(lastPos,pos));
					lastPos = pos;
				}
			}	
		}	
		//返回解码后的字符串
		return tmp.toString();
	}
	
	/**
	 * MD5加密
	 * @param args
	 */
	public static String toMd5(String str) {

		String re = null;
		byte encrypt[];
		try {
			byte[] tem = str.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("md5");
			md5.reset();
			md5.update(tem);
			encrypt = md5.digest();
			StringBuilder sb = new StringBuilder();
			
			for (byte t : encrypt) {
				
				sb.append(Integer.toHexString(t & 0xFF));
			}
			re = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re;
	}

	
	  public static void main(String[] args) { 
		  
		  // 测试escape编码
		 /* String testEscapeA = "嘿嘿啊哈";
		  String testEscapeB = "`~!@-=+,./?01234aBcDeFg啊哈";
		  System.out.println("A编码：" + escape(testEscapeA));
		  System.out.println("B编码：" + escape(testEscapeB));*/
		  
	  // 测试unescape解码
		 /* String testUnescapeA = "%u563f%u563f%u554a%u54c8";
		  String testUnescapeB = "%60%7e%21%40%2d%3d%2b%2c%2e%2f%3f01234aBcDeFg%u554a%u54c8";
		  System.out.println("A解码：" + unescape(testUnescapeA));
		  System.out.println("B解码：" + unescape(testUnescapeB));*/
		  
		  // 测试md5加密
		/*  String testMd5 = "Hello Venk";
		  System.out.println(toMd5(testMd5));
		  System.out.println(toMd5("Hello Venk"));
		  System.out.println(toMd5("Hello md5"));
		  System.out.println(toMd5("c945041e6aa78ff5ca8fdb2a238d9d")); 
		  System.out.println(toMd5(toMd5("Hello md5")));*/
	  
	  }
	   
}
