package com.nab.helper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonUtil {
	public static String randomString(int length, boolean letter, boolean number){
		return RandomStringUtils.random(length, letter, number);
	}
	public static String convertTimestampToString(Timestamp timestamp, String formatStr)  throws Exception{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatStr);
		return formatter.format(timestamp.toLocalDateTime());
	}
	public static Timestamp convertStrToTimestamp(String dateStr, String formatStr) throws Exception{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatStr);
		return Timestamp.valueOf(LocalDateTime.from(formatter.parse(dateStr)));
	}
	
	public static String replaceStrByName(String strRoot, Map<String, String> keyValue) {
		for (Map.Entry<String, String> entry : keyValue.entrySet()) {
			String target = entry.getKey();
			String replacement = entry.getValue();
			strRoot.replace(target, replacement);
	    }
		return strRoot;
	}
	public static LocalDate convertStrToLocalDate(String dateStr, String formatStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatStr);
		return LocalDate.parse(dateStr, formatter);
	}
}
