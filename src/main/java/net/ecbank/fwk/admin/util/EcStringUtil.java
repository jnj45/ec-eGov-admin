package net.ecbank.fwk.admin.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EcStringUtil {
	
	public static String getDateFormat(String fmt) {
		
		String datestr = "";
		
		DateFormat format = new SimpleDateFormat(fmt);
		datestr = format.format(Calendar.getInstance().getTime());

		//datestr = format.format(new Date());
		
		return datestr;
	}
	
}
