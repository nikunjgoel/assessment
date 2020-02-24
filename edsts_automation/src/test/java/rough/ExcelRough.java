package rough;

import java.util.Hashtable;

import helper.Config;
import helper.XlsReader;

public class ExcelRough {
	
	
	public static int totalRows;
	
	public static XlsReader xls_LoginSuite = new XlsReader(Config.testDataFilePath);
	public static void main(String[] args) {
		
		getData("Create Deal",xls_LoginSuite);
		
	}
	
	public static Object[][] getData(String sheetName, XlsReader xls) {
		// find the row num from which test starts
		// number of cols in the test
		// number of rows
		// put the data in hastable and put hastable in array

		int testStartRowNum = 0;
		// find the row num from which test starts
		//System.out.println("Test " + testName + " starts from " + testStartRowNum);

		int colStartRowNum = testStartRowNum + 1;
		int totalCols = 0;
		while (!xls.getCellData(sheetName, totalCols, colStartRowNum).equals("")) {
			totalCols++;
		}
		//System.out.println("Total Cols in test " + testName + " are " + totalCols);

		// rows
		int dataStartRowNum = testStartRowNum + 1;
		totalRows = 0;
		while (!xls.getCellData(sheetName, 0, dataStartRowNum + totalRows).equals("")) {
			totalRows++;
		}
		//System.out.println("Total Rows in test " + testName + " are " + totalRows);

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		// extract data
		Object[][] data = new Object[totalRows][1];
		int index = 0;
		Hashtable<String, String> table = null;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + totalRows); rNum++) {
			table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < totalCols; cNum++) {
				table.put(xls.getCellData(sheetName, cNum, colStartRowNum), xls.getCellData(sheetName, cNum, rNum));
				System.out.print(xls.getCellData(sheetName, cNum, rNum) + " -- ");
			}
			data[index][0] = table;
			index++;
			System.out.println();
		}

		System.out.println("done");

		return data;
	}

}
