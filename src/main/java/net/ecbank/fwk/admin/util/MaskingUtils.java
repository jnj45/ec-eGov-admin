package net.ecbank.fwk.admin.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskingUtils {
	
	public static String phoneNum(String str) {
		String replaceString = str;
		
		Matcher matcher = Pattern.compile("^(\\d{3})-?(\\d{3,4})-?(\\d{4})$").matcher(str);
	
		if(matcher.matches()) {
			replaceString = "";
			
			boolean isHyphen = false;
			if(str.indexOf("-") > -1) {
				isHyphen = true;
			}
			
			for(int i=1;i<=matcher.groupCount();i++) {
				String replaceTarget = matcher.group(i);
				if(i == 2) {
					char[] c = new char[replaceTarget.length()];
					Arrays.fill(c, '*');
					
					replaceString = replaceString + String.valueOf(c);	
				} else {
					replaceString = replaceString + replaceTarget;
				}
				
				if(isHyphen && i < matcher.groupCount()) {
					replaceString = replaceString + "-";
				}
			}
		}
		
		return replaceString;
	}
	
	public static String email(String str) {
		String replaceString = str;
		
		Matcher matcher = Pattern.compile("^(....)(.*)([@]{1})(.*)$").matcher(str);
		
		if(matcher.matches()) {
			replaceString = "";
			
			for(int i=1;i<=matcher.groupCount();i++) {
				String replaceTarget = matcher.group(i);
				if(i == 1) {
					char[] c = new char[replaceTarget.length()];
					Arrays.fill(c, '*');
					
					replaceString = replaceString + String.valueOf(c);
				} else {
					replaceString = replaceString + replaceTarget;
				}
			}
			
		}
		
		return replaceString;
	}
}
