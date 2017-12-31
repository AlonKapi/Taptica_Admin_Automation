package admin_bulk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InfoDataExcel {

	protected  String filePath;
	protected static  FileInputStream in;
	//protected static ArrayList<advDATAinfo> datArr;
	private static ArrayList<advDATAinfo> dataArr;
	private Map<String, ArrayList<advDATAinfo>> AdvId_INFO = new HashMap<String, ArrayList<advDATAinfo>>();
	ArrayList<advDATAinfo> advData = new ArrayList<advDATAinfo>();

	
	public InfoDataExcel(String filePath1){
		filePath = filePath1;
		InfoDataExcel.dataArr = new ArrayList<advDATAinfo>();
		try {
			in = new FileInputStream(filePath1);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//.@SuppressWarnings("unchecked")
	public ArrayList<advDATAinfo> getDataInfoFromExcel()throws IOException
	{
		DataFormatter formatter = new DataFormatter();
		Workbook workbook = new XSSFWorkbook(in);
		Sheet sheet = workbook.getSheetAt(0); 
		Row headerRow = sheet.getRow(0);
		for (int i=1; i<sheet.getLastRowNum()+1; i++) {	// iterates through all the rows minus the header, each row is an offer
			advDATAinfo aDInfo = new advDATAinfo();
			Row dataRow = sheet.getRow(i);
			
			for (int j=0; j<headerRow.getLastCellNum(); j++) {	// iterates through all the cells, saving the relevant data
				Cell headerCell = headerRow.getCell(j);
				Cell dataCell = dataRow.getCell(j);
				
				String header = formatter.formatCellValue(headerCell);
				String temp = formatter.formatCellValue(dataCell);
				aDInfo.setHeader(formatter.formatCellValue(headerCell));
				switch(header) {
				case "id":
					aDInfo.setAdvID(temp);
					break;
				case "shuli name":
					aDInfo.setAdvName(temp);
					break;
				case "xpath":
					aDInfo.setAdvXpath(temp);
					break;
				}
				
	}
			dataArr.add(aDInfo);
			//advData = dataperAdv().get(aDInfo.advID) == null ? new ArrayList<advDATAinfo>() :dataperAdv().get(aDInfo.advID) ;
			//advData.add(aDInfo);
			//dataperAdv().put(aDInfo.advID, advData);			
	}
		
			//System.out.println(offersPerAdvId.entrySet());

		workbook.close();
		return dataArr;
}

	//public ArrayList<<advDATAinfo>> ArrayList dataperAdv() {
	//	return dataperAdv();
	//}
}