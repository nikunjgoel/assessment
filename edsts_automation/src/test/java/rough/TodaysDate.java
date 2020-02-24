package rough;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodaysDate {
	
	public static void main(String[] args) throws ParseException {
		
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");

		Date today = new Date();

		//Date todayWithZeroTime = formatter.parse(formatter.format(today));
		
		System.out.println(formatter.format(today));
		
		
	}

}
