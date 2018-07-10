package dataAccessObjects;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import configObjects.ConfigHelper;

public class GetLoginData
{
	  HSSFWorkbook wb;
	  HSSFSheet sh;
	  HSSFRow rw;
	  HSSFCell cl;
	  FileInputStream fis;
	  String fPath = null;
	  int rcount, ccount, row, col;
		
	  public Object[][] getLoginDetails() throws IOException
	  {
		  fPath = ConfigHelper.getGetconfig().getFilePath();
		  fis = new FileInputStream(fPath);
		  
		  wb = new HSSFWorkbook(fis);
		  sh = wb.getSheetAt(0);
		  String shName = wb.getSheetName(0);
		  System.out.println("SheetName:" + shName);
		  
			rcount = sh.getPhysicalNumberOfRows();
			ccount = sh.getRow(1).getPhysicalNumberOfCells();
			
			Object [][] data = new Object[rcount-1][ccount];
			
		  	for(row = 0;row<rcount-1;row++)
		  	{
		  		rw = sh.getRow(row+1);
		  		for (col =0;col<ccount;col++)
		  		{
		  			cl = rw.getCell(col);
		  			data[row][col] = cl.getStringCellValue();
		  		}
		  	}
		  	
		  	return data;
		  	
	  }
}
