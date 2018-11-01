package test;


import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import utilities.utility;

public class ExcelExtractionTest extends utility {
	
	
	// test case to print all the data from excel file 
	@Test
	public void read_and_print() 
	{	
		reading_excel_and_printing();
	}
	
	@Test
	public void write()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream("Resources\\write_data.xlsx");
			wb= new XSSFWorkbook();
			sh = wb.createSheet("TestData");
			wb.write(fos);
			fos.close();
			FileInputStream fis = new FileInputStream("Resources\\write_data.xlsx");
			wb = new XSSFWorkbook(fis);
			sh = wb.getSheet("TestData");
			row = sh.createRow(0);
			cell = row.createCell(1);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("26");
			FileOutputStream fos1 = new FileOutputStream("Resources\\write_data.xlsx");
			wb.write(fos1);
			fos1.close();
			fis.close();
			
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
}

