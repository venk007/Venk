package src.main.java.com.venk.core.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Excel文件(Apache POI) 测试类
 * @author: Venk007
 * @date: 2016年3月25日下午5:32:14
 * 
 */
public class Test {

	public static void main(String[] args) {

		// 创建数据模型
		Gun ak47 = new Gun();
		Gun m4a1 = new Gun();
		Gun sg552 = new Gun();

		ak47.setName("AK47");
		ak47.setWeight(4100);
		ak47.setCapacity(30);
		ak47.setRate(600);
		ak47.setLength(870);
		ak47.setProductCountry("Soviet Union");
		ak47.setRemarks("哈伊尔·季莫费耶维奇·卡拉什尼科夫");

		m4a1.setName("M4A1");
		m4a1.setWeight(3130);
		m4a1.setCapacity(30);
		m4a1.setRate(980);
		m4a1.setLength(840);
		m4a1.setProductCountry("USA");
		m4a1.setRemarks("Colt");

		sg552.setName("SG552");
		sg552.setWeight(3200);
		sg552.setCapacity(30);
		sg552.setRate(700);
		sg552.setLength(730);
		sg552.setProductCountry("Switzerland");
		sg552.setRemarks("");

		// 封装数据
		List<Gun> list = new ArrayList<Gun>();
		list.add(ak47);
		list.add(m4a1);
		list.add(sg552);

		// 创建Excel
		ExportExcel.createExcel(list);

	}

}
