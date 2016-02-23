/**
 * 
 */
package src.main.java.com.venk.core.util;

/**
 * @Description: TODO
 * @author: Venk007
 * @date: 2016年2月23日上午10:43:16 
 *
 */
public class StringArray {

	/**
	 * @param args
	 */
	
	@SuppressWarnings("null")
	public static String[] sub(String[] input){
		
		int len = input.length;
		int teamlen = 3;	//设置每组数据长度[1,1,0,1,1,0,1,1,0](例:每组3个,所选数据为第三个)
		int teamloc = 2;	//起点位置，在每组中的位置-1
		int j = 0;
		String[] output = new String[len/teamlen];
		for(int i = teamloc; i <= len; i+=teamlen){
			output[j] = input[i];
			j++;
		}
		return output;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input[] = {"1","1","1","1","1","2","1","1","3","1","1","4","1","1","5"};
		String re[] = sub(input);
		for(int i = 0; i < re.length; i++){
			System.out.println(re[i]);
		}

	}

}
