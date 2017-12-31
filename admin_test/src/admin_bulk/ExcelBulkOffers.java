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

public class ExcelBulkOffers {
	protected  String filePath;
	protected static  FileInputStream in;
	protected static ArrayList<BulkUpdateoffers> offersArr;
	private Map<String, ArrayList<BulkUpdateoffers>> offersPerAdvId = new HashMap<String, ArrayList<BulkUpdateoffers>>();
	ArrayList<BulkUpdateoffers> bulkOffers = new ArrayList<BulkUpdateoffers>();

	public ExcelBulkOffers(String filePath1){
		filePath = filePath1;
		ExcelBulkOffers.offersArr = new ArrayList<BulkUpdateoffers>();
		try {
			in = new FileInputStream(filePath1);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//.@SuppressWarnings("unchecked")
	public ArrayList<BulkUpdateoffers> getBulkOfferFromExcel()throws IOException
	{
		DataFormatter formatter = new DataFormatter();
		Workbook workbook = new XSSFWorkbook(in);
		Sheet sheet = workbook.getSheetAt(0); 
		Row headerRow = sheet.getRow(0);
		for (int i=1; i<sheet.getLastRowNum()+1; i++) {	// iterates through all the rows minus the header, each row is an offer
			BulkUpdateoffers bulk = new BulkUpdateoffers();
			Row dataRow = sheet.getRow(i);
			
			for (int j=0; j<headerRow.getLastCellNum(); j++) {	// iterates through all the cells, saving the relevant data
				Cell headerCell = headerRow.getCell(j);
				Cell dataCell = dataRow.getCell(j);
				
				String header = formatter.formatCellValue(headerCell);
				String temp = formatter.formatCellValue(dataCell);
				bulk.setHeader(formatter.formatCellValue(headerCell));
				switch(header) {
				case "Offer ID":
					bulk.setoffersID(temp);
					break;
				case "id":
					bulk.setadvID(temp);
					break;
				}
				
	}
			offersArr.add(bulk);
			bulkOffers = offersPerAdvId.get(bulk.advID) == null ? new ArrayList<BulkUpdateoffers>() : offersPerAdvId.get(bulk.advID) ;
			bulkOffers.add(bulk);
			offersPerAdvId.put(bulk.advID, bulkOffers);			
	}
		
			//System.out.println(offersPerAdvId.entrySet());

		workbook.close();
		return offersArr;
}

	public Map<String, ArrayList<BulkUpdateoffers>> getOffersPerAdvId() {
		return offersPerAdvId;
	}

}