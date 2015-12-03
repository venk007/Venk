package src.com.venk.plan.ufpasspos;

/**
 * 
 * @Description: CalcCap测试类
 * @author: Venk007
 * @date: 2015年12月3日下午6:28:06 
 *
 */
public class TestCalcCap {

	public static void main(String[] args) {
		
		StringBuffer idNumBuf = new StringBuffer();
		//idNumBuf.append("34052419800101001");
		idNumBuf.append("33032919850223958");
		System.out.println(CalcCap.calcCap(idNumBuf));


	}

}
