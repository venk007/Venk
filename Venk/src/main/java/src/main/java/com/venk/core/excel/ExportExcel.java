package src.main.java.com.venk.core.excel;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

	/**
	 * @Description: 导出Excel文件优化版 Demo
	 * @author: Venk007
	 * @date: 2016年3月25日下午1:54:04 
	 *
	 */
	public class ExportExcel {
	
	/** 文件输出位置，设置在D盘的temp目录 */
	public static String outputName = "test-" + dateStamp() + ".xlsx";
	public static String outputFile = "D:/temp/" + outputName;
	
	/**
	 * 设置单元格格式
	 * @param cell
	 * @param wb
	 * @return Cell
	 */
	public static Cell cellStyle(Cell cell, Workbook wb) {

		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 设置单元格字体
		Font font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		font.setFontHeightInPoints((short) 12);
		cellStyle.setFont(font);
		// 水平布局居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 边框
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		cell.setCellStyle(cellStyle);
		return cell;
	}
	
	/**
	 * 自定义生成Excel名称添加日期
	 * @return String
	 */
	protected static String dateStamp() {
		DateFormat df = new SimpleDateFormat("HH-mm-ss");
		return df.format(new Date());
	}
	
	public static void createExcel(List<Gun> list){
		
		try {
			// 创建工作簿(SXSSF为防止内存溢出,可设置缓存值)
			Workbook workbook = new SXSSFWorkbook(100);
			// 新建工作表，其名为缺省值(也可设置)
			Sheet sheet = workbook.createSheet("Venk007");
			// 在索引0的位置创建行（最顶端的行）
			Row row = sheet.createRow((short) 0);

			// 设置列标题及格式
			String[] cellText = new String[] { "名称", "重量(G)", "弹匣容量",
					"理论射速", "全长(毫米)", "产地", "备注" };
			for (int textNum = 0; textNum < cellText.length; textNum++) {
				Cell cell = row.createCell(textNum);
				cell.setCellValue(new XSSFRichTextString(cellText[textNum]));
				cellStyle(cell, workbook); // 设置列标题格式
			}
			
			// 遍历内容
			if (list != null && list.size() != 0) {
				for (int rownum = 0; rownum < list.size(); rownum++) {
					Row r = sheet.createRow(rownum + 1);
					Gun rs = list.get(rownum);
					r.createCell(0).setCellValue(rs.getName());
					r.createCell(1).setCellValue(rs.getWeight());
					r.createCell(2).setCellValue(rs.getCapacity());
					r.createCell(3).setCellValue(rs.getRate() + "RPM");
					r.createCell(4).setCellValue(rs.getLength());
					r.createCell(5).setCellValue(rs.getProductCountry());
					if (rs.getRemarks() != null && !"".equals(rs.getRemarks())) {
						r.createCell(6).setCellValue(rs.getRemarks());
					} else {
						r.createCell(6).setCellValue("Venk's favor!");
					}
				}
			}
			
			// 自动调整列宽(Beta)
			try {
				for (int tempNum = 0; tempNum < cellText.length; tempNum++) {
					sheet.getColumnWidth(tempNum);
					sheet.setColumnWidth(tempNum, 3000);
					//sheet.autoSizeColumn(tempNum);
				}
			} catch (Exception e) {
				System.out.println("设置列宽时出错 \n");
				e.printStackTrace();
			}

			// 保存 Excel
			FileOutputStream fOut = new FileOutputStream(outputFile);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
			System.out.println("Excel创建成功\n位置--> " + outputFile);

		} catch (Exception e) {
			System.out.println("生成Excel出错!");
			e.printStackTrace();
		}

	}

}
