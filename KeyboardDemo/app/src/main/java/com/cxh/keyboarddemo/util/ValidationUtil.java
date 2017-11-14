package com.cxh.keyboarddemo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidationUtil {

	public static boolean isChinese(char a) {
		int v = (int) a;
		return (v >= 19968 && v <= 171941);
	}

	public static String replaceBlank(String str) {

		String result = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			result = m.replaceAll("");
		}
		return result;

	}

	public static String formBankCard(String input){
        String result=input.replaceAll("([\\d]{4})(?=\\d)", "$1 ");
        return result;
    }

}

// public static boolean judgeBlank(String str) {
//
// boolean result = false;
// if (str != null) {
// Pattern p = Pattern.compile("\\s*|\t");
// Matcher m = p.matcher(str);
// if (m.find()) {
// result = true;
// }
// }
// if (str != null) {
// Pattern p = Pattern.compile("\\s*|\r");
// Matcher m = p.matcher(str);
// if (m.find()) {
// result = true;
// }
// }
// if (str != null) {
// Pattern p = Pattern.compile("\\s*|\n");
// Matcher m = p.matcher(str);
// if (m.find()) {
// result = true;
// }
// }
//
// return result;
//
// }
