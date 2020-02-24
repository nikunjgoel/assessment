package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class fetchDatafromExcelColumnWise {

	int size = 0;
	List<XSSFCell> sheetHeader = new ArrayList<>();
	Map<String, String> getTestdata = new HashMap<>();
	private String workbook = null;
	private String sheet = null;
	private String workbookLLD = null;
	
	public Map<String, String> getTestData(String tcid, String sheet) throws Exception {

		workbook = Config.excelWorkbookName;

		List<XSSFCell> array = null;

		try {
			List<List<XSSFCell>> listData = getData(workbook, sheet);
			sheetHeader = listData.get(0);
			listData.remove(0);

			array = getArray(listData, tcid/* ,sAdditionalCol */);

			for (int i = 0; i < sheetHeader.size(); i++) {

				getTestdata.put(sheetHeader.get(i).toString(), array.get(i).toString());

			}
			System.out.println(getTestdata);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			array = null;
		}
		return getTestdata;
	}

	public static List<List<XSSFCell>> getData(String workBook, String ssheetName) {
		File dir1 = new File(".");
		List<List<XSSFCell>> sheetData = new ArrayList<List<XSSFCell>>();
		FileInputStream fis = null;
		XSSFWorkbook wb = null;
		XSSFSheet sheet = null;
		String sheetName = null;
		try {
			String strBasePath = dir1.getCanonicalPath();
			System.out.println(strBasePath);
			String file = System.getProperty("user.dir") + "/src/main/resources/TestCase.xlsx";

			sheetName = ssheetName;
			System.out.println("The file is :: " + file);

			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);

			Iterator<Row> rows = sheet.rowIterator();

			while (rows.hasNext()) {
				XSSFRow rown = (XSSFRow) rows.next();
				Iterator<Cell> cells = rown.cellIterator();

				List<XSSFCell> data = new ArrayList<XSSFCell>();
				while (cells.hasNext()) {
					XSSFCell celln = (XSSFCell) cells.next();
					celln = (XSSFCell) castCellType(celln);
					data.add(celln);
				}
				sheetData.add(data);
			}
		} catch (IOException e) {
			sheetData = null;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {

					System.out.println(e.getMessage());
				}
			}
		}
		return sheetData;
	}

	public static Cell castCellType(Cell cell) {
		try {
			if (cell != null) {
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
				} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					cell.getRichStringCellValue();
				} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
				} /*
					 * else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
					 * cell.setCellType(Cell.CELL_TYPE_STRING); }
					 */ else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
				} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
				}

			}
		} catch (Exception ex) {

			cell = null;
		}
		return cell;
	}

	public List<XSSFCell> getArray(List<List<XSSFCell>> sheetData, String tcid) {
		List<XSSFCell> list = new ArrayList<XSSFCell>();

		int intTestCaseNameIndex = getIndexFromSheetHeaderList(Config.excelClumnName); // "TCID");

		Iterator<List<XSSFCell>> checkData = sheetData.iterator();
		List<XSSFCell> oneDList = null;
		while (checkData.hasNext()) {
			oneDList = (List<XSSFCell>) checkData.next();
			if (oneDList.get(intTestCaseNameIndex).toString().equalsIgnoreCase(tcid)) {
				list.addAll(oneDList);
			}
		}

		return list;
	}

	public int getIndexFromSheetHeaderList(String hdrColName) {
		Iterator<XSSFCell> hdrItr = sheetHeader.iterator();
		XSSFCell hdrVal = null;
		int indOfCol = 0;
		try {
			while (hdrItr.hasNext()) {
				hdrVal = hdrItr.next();
				if (hdrVal.toString().trim().equals(hdrColName))
					indOfCol = sheetHeader.indexOf((Object) hdrVal);
			}
		} catch (Exception ex) {
			indOfCol = 0;
		}
		return indOfCol;
	}

	
	public static int[] findRowAndColumn(XSSFSheet sheet, String cellContent) {
		int rowNum = 0;
		CellAddress colname;
		int[] result = new int[2];
		int counter = 0;
		for (Row row : sheet) {
			for (Cell cell : row) {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				System.out.println(cell.getRichStringCellValue().getString());
				if (cell.getRichStringCellValue().getString().equalsIgnoreCase(cellContent)) {
					rowNum = row.getRowNum();
					colname = cell.getAddress();
					int columnName = colname.getColumn();

					result[0] = rowNum;
					result[1] = columnName;
					counter = 1;
					break;
				}
			}
			if (counter == 1) {
				break;
			}
		}
		return result;
	}

	public static String output(XSSFSheet sheet, int rownum, int colnum) {
		int rownr = rownum + 1;
		XSSFRow row = sheet.getRow(rownr);
		XSSFCell cell = row.getCell(colnum);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String value = cell.getStringCellValue();
		if (value.equalsIgnoreCase("")) {
			value = "null";
		}
		return value;
	}
}
