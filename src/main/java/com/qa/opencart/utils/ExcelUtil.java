package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	public static Workbook book;
	public static Sheet sheet;
	

	public static Object[][] getExcelData(String sheetName) {
		
		Object [][] data = null;
		try {
			FileInputStream file = new FileInputStream("./src/test/resources/testdata/TestData.xlsx");
			book = WorkbookFactory.create(file);
			sheet = book.getSheet(sheetName);
			int row = sheet.getLastRowNum();
			int col = sheet.getRow(0).getLastCellNum();
			
			data = new Object[row][col];
			for(int i=0;i<row;i++) {
				for(int j=0;j<col;j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();

				}
			}
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}

}
