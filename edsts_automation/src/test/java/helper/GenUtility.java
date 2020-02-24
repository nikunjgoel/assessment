package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import freemarker.core._DelayedFTLTypeDescription;

public class GenUtility {

	public static int totalRows;

	public static String getDayWithSeconds() {

		DateFormat dateformat = new SimpleDateFormat("MMM_dd_yyyy_hh_mm_ss");

		Calendar cal = Calendar.getInstance();
		String curdate = dateformat.format(cal.getTime());
		return curdate;
	}

	public String currentdate() {

		DateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");

		Date today = new Date();

		System.out.println(formatter.format(today));
		String date = formatter.format(today);
		return date;
	}

	public static String takeScreenshot(WebDriver driver, String fileName) {

		String Path = System.getProperty("user.dir") + "\\screenshots\\" + fileName + "_" + getDayWithSeconds()
				+ ".jpeg";
		File srcFile = ((TakesScreenshot) (driver)).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(Path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Path;
	}

	public static String getRandomAlphaNumString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 11) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	public static String getRandomStringSixAlphaSixNum() {
		String newStr = "ED";
		String str = "" + System.currentTimeMillis() / 1000L;
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] ch = new char[str.length()];
		for (int i = 0; i < str.length(); i++) 
			ch[i] = str.charAt(i);
		
		for (int i = 0; i < ch.length; i++) {
			int z = Integer.parseInt(String.valueOf(ch[i]));
			if (i < 4) {
				char s = SALTCHARS.charAt(z);
				newStr += s;
			} else 
				newStr += ch[i];
		}
		return newStr;

	}

	public static String getSplittedStringFirstPart(String str, String splitPoint) {

		String part1 = "";
		String part2 = "";
		try {
			String[] parts = str.split(splitPoint);
			part1 = parts[0];
			part2 = parts[1];
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return part1;
	}

		public static boolean releaseChromeServerProcess(String taskName) {
		boolean flag = false;
		try {
			// Run Task manager
			Process p = Runtime.getRuntime().exec("tasklist.exe");
			// Get the list of processes in Task manager
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			// Fetch each process
			while ((line = input.readLine()) != null) {
				String processName[] = line.split(" ");
				// Compare it with Process name passed as argument and if found
				// return true
				if (processName[0].equals(taskName)) {
					flag = true;
				}
				/*
				 * if(processName[0].equals("javaw.exe"))
				 * Runtime.getRuntime().exec("taskkill /F /IM " + processName[0]);
				 */
				// System.out.println("Memory Released");
			}
			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}


	public static HashMap<String, String> getData(String sheetName, XlsReader _xls) {

		int testStartRowNum = 0;

		int colStartRowNum = testStartRowNum + 1;
		int totalCols = 0;
		while (!_xls.getCellData(sheetName, totalCols, colStartRowNum).equals("")) {
			totalCols++;
		}

		// rows
		int dataStartRowNum = testStartRowNum + 1;
		totalRows = 0;
		while (!_xls.getCellData(sheetName, 0, dataStartRowNum + totalRows).equals("")) {
			totalRows++;
		}

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		// extract data
		// Object[][] data = new Object[totalRows][1];
		// int index = 0;
		HashMap<String, String> _map = null;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + totalRows); rNum++) {
			_map = new HashMap<String, String>();
			for (int cNum = 0; cNum < totalCols; cNum++) {
				_map.put(_xls.getCellData(sheetName, cNum, colStartRowNum), _xls.getCellData(sheetName, cNum, rNum));
				System.out.print(_xls.getCellData(sheetName, cNum, rNum) + " -- ");
			}
			// data[index][0] = _table;
			// index++;
			System.out.println();
		}

		System.out.println("done");

		return _map;
	}

	public static String convertStringintoDecimaluptoTwoChars(String s) {

		double d = Double.parseDouble(s);
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(d);

	}

	public static double convertStringintoDecimal(String s) {
		return Double.parseDouble(s);

	}

}
