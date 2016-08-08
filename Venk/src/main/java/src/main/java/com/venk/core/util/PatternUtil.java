/**
 * 
 */
package src.main.java.com.venk.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 正则表达式Util
 * @author: Venk007
 * @date: 2016年8月8日下午3:17:12 
 *
 */
public class PatternUtil {

	static String tregEx="[0]{1}[0-9]{2,3}-[0-9]{7,8}";//固定电话 正则表达式
	static Pattern patternTelNo = Pattern.compile(tregEx);
	
	
	public static void main(String[] args) throws Exception {
	String[] tel = {"12345678","01-123456","010-123456","010-1234567","010-12345678","0101-12345678","01002-123456789","010-12340000-001"}; 
		for(String telNo : tel){
			Matcher m = patternTelNo.matcher(telNo);
			if(m.matches()){
				System.out.println("TRUE:"+telNo);
			}else{
				System.out.println("FALSE:"+telNo);
			}
		}
	}
		
}
