package com.aits.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 解析excel
 * 
 * @author jared
 *
 */
public class ExcelUtil {

	public ExcelUtil() {
	}

	/**
	 * 获得excel表格第一个sheet对象
	 * 
	 * @param upload
	 * @return
	 * @throws IOException
	 */
	public static Sheet getSheet(MultipartFile upload) throws IOException {
		String filename = upload.getOriginalFilename();
		Workbook workbook = null;
		if (filename.endsWith("xls")) {
			workbook = new HSSFWorkbook(upload.getInputStream());
		} else if (filename.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(upload.getInputStream());
		}
		Sheet sheet = workbook.getSheetAt(0);
		return sheet;
	}

	/**
	 * 根据单元格的类型取对应的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 把数字当成String来读，避免出现1读成1.0的情况
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数字
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}

	/**
	 * 设置单元格的分格
	 * 
	 * @param workbook
	 */
	public static void setCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle.setWrapText(true);// 设置自动换行

	}

	/**
	 * 为某个单元格赋值
	 * 
	 * @param filePath
	 * @param i
	 * @param j
	 * @param str
	 */
	public static void setExcelCell(String filePath, int i, int j, String str) {
		HSSFWorkbook wb = null;
		FileOutputStream os = null;
		try {
			wb = new HSSFWorkbook(new FileInputStream(filePath));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = sheet.getRow(i);
			HSSFCell cell = row.getCell(j);
			cell.setCellValue(str);
			os = new FileOutputStream(filePath);
			wb.write(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 设置单元格边框宽度
	 * 
	 * @param cellStyle
	 * @param borderWidth
	 */
	public static void setBorder(CellStyle cellStyle, short borderWidth) {
		cellStyle.setBorderTop(borderWidth);
		cellStyle.setBorderBottom(borderWidth);
		cellStyle.setBorderLeft(borderWidth);
		cellStyle.setBorderRight(borderWidth);
	}

	/**
	 * 显示单元格边框问题
	 * 
	 * @param sheet
	 * @param region
	 * @param cs
	 */
	public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress region, HSSFCellStyle cs) {

		for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {

			HSSFRow row = sheet.getRow(i);
			if (row == null) {
				row = sheet.createRow(i);
			}
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				HSSFCell cell = row.getCell(j);
				if (cell == null) {
					cell = row.createCell(j);
					cell.setCellValue("");
				}
				cell.setCellStyle(cs);

			}
		}
	}

}