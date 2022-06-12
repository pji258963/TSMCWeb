package com.tsmc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getFormatToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
}
