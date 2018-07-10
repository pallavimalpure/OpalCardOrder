package dataAccessObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import configObjects.ConfigHelper;

public class GetOpalFares
{
	  HSSFWorkbook wb;
	  HSSFSheet sh;
	  HSSFRow rw;
	  HSSFCell cl;
	  FileInputStream fis;
	  String fPath = null;
	  int rcount, ccount, row, col;
	  HashMap<String, OpalFareData> opalfareData = new HashMap<String, OpalFareData>();
		
	  public HashMap<String, OpalFareData> getOpalFareDetails() throws IOException
	  {
		  fPath = ConfigHelper.getGetconfig().getFilePath();
		  fis = new FileInputStream(fPath);
		  OpalFareData data = new OpalFareData();
		  
		  wb = new HSSFWorkbook(fis);
		  sh = wb.getSheet("OpalFares");
		  
		  String shName = wb.getSheetName(1);
		  System.out.println("SheetName:" + shName);
		  
			rcount = sh.getPhysicalNumberOfRows();
			ccount = sh.getRow(0).getPhysicalNumberOfCells();
			
		  	for(row = 0;row<rcount;row++)
		  	{
		  		rw = sh.getRow(row);
		  		for (col =0;col<ccount;col++)
		  		{
		  			cl = rw.getCell(col);
		  			if(col == 0)
		  			{
		  				data.cardType = cl.getStringCellValue();
		  			}
		  			if(col == 1)
		  			{
		  				data.Value0to10 = cl.getStringCellValue();
		  			}
		  			if(col == 2)
		  			{
		  				data.Value10to20 = cl.getStringCellValue();
		  			}
		  			if(col == 3)
		  			{
		  				data.Value20to30 = cl.getStringCellValue();
		  			}
		  			if(col == 4)
		  			{
		  				data.Value30to40 = cl.getStringCellValue();
		  			}
		  			if(col == 5)
		  			{
		  				data.Value40to50 = cl.getStringCellValue();
		  			}
		  			opalfareData.put(data.cardType, data);
		  		}
		  	}
		  	
		  	return opalfareData;
		  	
	  }
}
