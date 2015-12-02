package com.venk.uuid;

/**
 * 
 * @Description: 批量生成UUID，可用于数据变更
 * @author: Venk007
 * @date: 2015年12月2日下午2:35:51
 * @TIPS:生成的UUID一共有32位，第13位的数字永远是4
 */

public class TestUUID {

	public static void main(String[] args) {

		// 定义生成数量(因控制台限制,打印序号建议上限2000,不打印序号上限2900)
		final int Num = 100;

		// With Serial Number
		for (int i = 1; i <= Num; i++) {
			System.out.println(i + " :\t"
					+ UUIDUtils.get32UpperUUID().toLowerCase());
		}

		// Only UUID
		for (int i = 1; i <= Num; i++) {
			System.out.println(UUIDUtils.get32UpperUUID().toLowerCase());
		}

	}
}