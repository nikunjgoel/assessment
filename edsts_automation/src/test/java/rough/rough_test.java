package rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import helper.Config;

public class rough_test {

	public static void main(String[] args) throws IOException {

		ArrayList<HashMap> myList = data();
		
		for (int a =0; a<myList.size();a++)
        {
            HashMap tmpData = (HashMap) myList.get(a);
            Set<String> key = tmpData.keySet();
            Iterator it = key.iterator();
            while (it.hasNext()) {
                String hmKey = (String)it.next();
                Object hmData = (Object) tmpData.get(hmKey);

                System.out.println("Key: "+hmKey +" & Data: "+hmData);
                it.remove(); // avoids a ConcurrentModificationException
            }

        }       
		

	}

	public static ArrayList<HashMap> data() {
		ArrayList<HashMap> mydata = new ArrayList<HashMap>();
		try {
			FileInputStream fs = new FileInputStream(Config.testDataFilePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet("Create Deal");
			Row HeaderRow = sheet.getRow(0);
			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
				Row currentRow = sheet.getRow(i);
				HashMap currentHash = new HashMap();
				for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
					Cell currentCell = currentRow.getCell(j);
					switch (currentCell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						System.out.print(currentCell.getStringCellValue() + "\t");

						currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
						break;

					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(currentCell.getNumericCellValue() + "\t");

						currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getNumericCellValue());
						break;
						
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(currentCell.getBooleanCellValue() + "\t");

						currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getBooleanCellValue());
						break;
					}
				}
				mydata.add(currentHash);
				System.out.println(" ");
			}

			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mydata;
	}

}
