package tn.esprit.spring.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadCell {

	//ReadCell rc=new ReadCell(); // object of the class
	// reading the value of 2nd row and 2nd column
	//String vOutput = rc.ReadCellData(2, 2);
	//System.out.println(vOutput);
public static  Workbook   readFile (String path) {
	Workbook wb = null;
	try  
	{  
	//reading data from a file in the form of bytes 
		//"D:\\invitationsExcel.xlsx"
	FileInputStream fis=new FileInputStream(path);  
	//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
	 wb = new XSSFWorkbook(fis);  
	}  
	catch(FileNotFoundException e)  
	{  
	e.printStackTrace();  
	}  
	catch(IOException e1)  
	{  
	e1.printStackTrace();  
	}
	return wb;  
}
	// method defined for reading a cell
	public  String ReadCellData(int vRow, int vColumn)  
	{  
	String value=null;          //variable for storing the cell value  
	Workbook wb=null;           //initialize Workbook null  
	wb = readFile("D:\\invitationsExcel.xlsx");
	Sheet sheet=wb.getSheetAt(0);   //getting the XSSFSheet object at given index  
	Row row=sheet.getRow(vRow); //returns the logical row  
	Cell cell=row.getCell(vColumn); //getting the cell representing the given column  
	DataFormatter formatter = new DataFormatter();
	formatter.formatCellValue(cell);
	value=cell.getStringCellValue().toString();    //getting cell value  
	sheet.removeRow(row);
	return value;               //returns the cell value  
	}
	
	public  int ReadIntCellData(int vRow, int vColumn)  
	{  
	int value;          //variable for storing the cell value  
	Workbook wb=null;           //initialize Workbook null  
	wb = readFile("D:\\invitationsExcel.xlsx");
	Sheet sheet=wb.getSheetAt(0);   //getting the XSSFSheet object at given index  
	Row row=sheet.getRow(vRow); //returns the logical row  
	Cell cell=row.getCell(vColumn); //getting the cell representing the given column  
	DataFormatter formatter = new DataFormatter();
	formatter.formatCellValue(cell);
	value=(int) cell.getNumericCellValue();    //getting cell value  
	System.out.println("test remove "+row);
	sheet.removeRow(row);
	return value;               //returns the cell value  
	}
	
	public static int getNumRows () {
		Workbook wb=null;           //initialize Workbook null  
		wb = readFile("D:\\invitationsExcel.xlsx");
		Sheet sheet=wb.getSheetAt(0);
		return sheet.getPhysicalNumberOfRows();
	}
}
