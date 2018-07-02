package com.zomato.utility;

import java.util.ArrayList;

import com.zomato.utility.excel.Xls_Reader;

public class ExcelUtil {
	
	static Xls_Reader reader;
	
	public static ArrayList<Object[]> getDataFromExcel(String sheetName){
		
		ArrayList<Object[]> mydata = new ArrayList<Object[]>();
		
		try{
			reader = new Xls_Reader("C:\\Shwetali\\Eclipse_Workspace\\zomato_TestNG\\src\\com\\zomato\\testdata\\users.xlsx");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		for(int rowCount = 2; rowCount <= reader.getRowCount(sheetName); rowCount++)
		{
			String email = reader.getCellData(sheetName,"email",rowCount);
			String password = reader.getCellData(sheetName,"password",rowCount);
			String firstname = reader.getCellData(sheetName,"firstname",rowCount);
			String lastname = reader.getCellData(sheetName,"lastname",rowCount);
			
		//	System.out.println(email+" "+password+" "+firstname+" "+lastname);
			
			Object obj[] = {email, password, firstname,lastname};
			mydata.add(obj);
		}
		
		return mydata;
	}

}
