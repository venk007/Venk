package com.venk.uuid;

/**
 * 
 * @Description: 批量生成UUID，可用于数据变更
 * @author: Venk007
 * @date: 2015年12月2日下午2:35:51
 * 
 */

public class Test {

	public static void main(String[] args) {

		// 定义生成数量(因控制台限制,打印序号建议上限2000,不打印序号上限2900)
		int Num = 2000;

		// 打印序号的生成
		for (int i = 1; i <= Num; i++) {
			System.out.println(i + " :\t"
					+ UUIDUtils.get32UpperUUID().toLowerCase());
		}
		
		/*
		// Only UUID
		for (int i = 1; i <= Num; i++) {
			System.out.println(UUIDUtils.get32UpperUUID().toLowerCase());
		}
		*/
	}
}