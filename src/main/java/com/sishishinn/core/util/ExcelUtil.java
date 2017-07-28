package com.sishishinn.core.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {
	
	
	/**
	 * @param path		读取原文件路径
	 * @param startRow	起始行
	 * @param colsize	总列数
	 */
	@SuppressWarnings("deprecation")
	public static List<String[]> readXls(String path,int startRow,int colsize) throws Exception{
		List<String[]> data = new ArrayList<String[]>();
		InputStream is = null;
		try {
			is = new FileInputStream(path);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			//遍历sheet
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				//遍历行
				for (int rowNum = startRow; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						String[] rowData = new String[colsize];
						//遍历列
						for (short colnum = 0; colnum < colsize; colnum++) {
							HSSFCell hssfCell = hssfRow.getCell(colnum);
							switch (hssfCell.getCellType()) {
								case HSSFCell.CELL_TYPE_STRING:
									rowData[colnum] = hssfCell.getStringCellValue().trim();
									break;
								case HSSFCell.CELL_TYPE_NUMERIC:
									NumberFormat nf = NumberFormat.getInstance();
									nf.setGroupingUsed(false);
									double acno = hssfCell.getNumericCellValue();
									rowData[colnum] = nf.format(acno);
									break;
								case HSSFCell.CELL_TYPE_FORMULA:
									rowData[colnum] = String.valueOf(hssfCell.getNumericCellValue());
									break;
								case HSSFCell.CELL_TYPE_BLANK:
									rowData[colnum] = "";
									break;
								case HSSFCell.CELL_TYPE_BOOLEAN:
									break;
								default:
									rowData[colnum] = "";
									break;
							}
						}
						data.add(rowData);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			is.close();
		}
		return data;
		
	}
	
	/**
	 * 导出表格
	 */
	public static boolean writeTableToExcel(HSSFWorkbook hssfWorkbook,OutputStream os) throws Exception{
		boolean flag = false;
		try {
			hssfWorkbook.write(os);
			os.flush();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			os.close();
		}
		return flag;
	}

	@SuppressWarnings("deprecation")
	public static boolean writeExcelTest(HttpServletResponse response) throws Exception{
		//创建HSSFWorkbook对象(excel的文档对象)  
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();  
		//建立新的sheet对象（excel的表单）  
		HSSFSheet sheet=hssfWorkbook.createSheet("成绩表");  
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个  
		HSSFRow row1=sheet.createRow(0);  
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
		HSSFCell cell=row1.createCell((short) 0);  
		//设置单元格内容  
		cell.setCellValue("学员考试成绩一览表");  
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		//在sheet里创建第二行  
		HSSFRow row2=sheet.createRow(1);      
		//创建单元格并设置单元格内容  
		row2.createCell((short) 0).setCellValue("姓名");  
		row2.createCell((short)1).setCellValue("班级");      
		row2.createCell((short)2).setCellValue("笔试成绩");  
		row2.createCell((short)3).setCellValue("机试成绩");      
		//在sheet里创建第三行  
		HSSFRow row3=sheet.createRow(2);  
		row3.createCell((short)0).setCellValue("李明");  
		row3.createCell((short)1).setCellValue("As178");  
		row3.createCell((short)2).setCellValue(87);      
		row3.createCell((short)3).setCellValue(78);      
		//.....省略部分代码  
		response.setHeader("Content-Disposition", "attachment; filename=file.xls");
		return writeTableToExcel(hssfWorkbook, response.getOutputStream());
	}
	
	
	
}
