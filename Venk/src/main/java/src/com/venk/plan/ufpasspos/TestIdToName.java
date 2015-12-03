/**
 * 
 */
package src.com.venk.plan.ufpasspos;

import java.io.UnsupportedEncodingException;

/**
 * @Description: TODO
 * @author: Venk007
 * @date: 2015年12月3日上午10:39:47 
 *
 */
public class TestIdToName {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		String a ="<span id=\"Label4\">李文珂  在校学习成绩：</span>";
		String b ="   李文珂   ";
		
		int first = a.lastIndexOf("Label4");
		int end = a.lastIndexOf("在校学习成绩");
		System.out.println(first);
		System.out.println(end);
		System.out.println(a.substring(first+8,end).trim());
		//System.out.println("-" + b.trim() + "-");
		*/
		String id = "1205290311";
		String a = null;
		try {
			a = IdToName.idToName(id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a);
	}

}
