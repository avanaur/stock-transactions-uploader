package com.avanaur.tradingintelligence.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	public static String format(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
}
