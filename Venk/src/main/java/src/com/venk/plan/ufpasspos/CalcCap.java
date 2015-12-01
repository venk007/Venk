package src.com.venk.plan.ufpasspos;
/*
 * 身份号码验证位模块
 * @Author:venk
 * @Time:2015-12-01 16:55
 * TestEnd 17:30
 */
public class CalcCap {
	
	public static char calcCap(StringBuffer idNum) {
		
		int[] idNumber = new int [17] ;
		
		int sumNum = 0;												//前17为乘以权然后求和得到的数
		int[] weightArray = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};	//权数数组
        String vCode = "10X98765432";								//校验码字符串
		
        // 循环乘以权，再求和
		for (int i = 0; i < 17; i++){
			idNumber[i] = idNum.charAt(i) - 48;
			sumNum = sumNum + idNumber[i] * weightArray[i];
		}
        
        int modNum = sumNum%11;										//求模
        char sercretId = vCode.charAt(modNum);						//从验证码中找出对应的数
        
        // 完美的返回~
        return sercretId;		
	}
}
