package cn.mldn.util;

public class ValidatorUtil {
	/**
	 * 判断给定的数据是否为null
	 * @return 如果为null返回false，如果正确返回true
	 */
	public static boolean isString(String str) {
		if (str == null || "".equals(str)) {
			return false ;
		}
		return true ;
	}
	public static boolean isStringArray(String str[]) {	// 判断一组的字符串是否有空
		for (int x = 0 ; x < str.length ; x ++) {
			if (!ValidatorUtil.isString(str[x])) {
				return false ;
			}
		}
		return true ;
	}
	public static boolean isInt(String str) {
		return ValidatorUtil.isRegex(str, "\\d+") ;
	}
	public static boolean isIntArray(String str[]) {
		for (int x = 0 ; x < str.length ; x ++) {
			if (!ValidatorUtil.isInt(str[x])) {
				return false ;
			}
		}
		return true ;
	}
	public static boolean isDouble(String str) {
		return ValidatorUtil.isRegex(str, "\\d+(\\.\\d+)?") ;
	}
	public static boolean isDoubleArray(String str[]) {
		for (int x = 0 ; x < str.length ; x ++) {
			if (!ValidatorUtil.isDouble(str[x])) {
				return false ;
			}
		}
		return true ;
	}
	public static boolean isDate(String str) {
		return ValidatorUtil.isRegex(str, "\\d{4}-\\d{2}-\\d{2}") ;
	}
	public static boolean isDateArray(String str[]) {
		for (int x = 0 ; x < str.length ; x ++) {
			if (!ValidatorUtil.isDate(str[x])) {
				return false ;
			}
		}
		return true ;
	}
	public static boolean isDatetimeArray(String str[]) {
		for (int x = 0 ; x < str.length ; x ++) {
			if (!ValidatorUtil.isDatetime(str[x])) {
				return false ;
			}
		}
		return true ;
	}
	public static boolean isDatetime(String str) {
		return ValidatorUtil.isRegex(str, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") ;
	} 
	/**
	 * 使用指定的正则进行数据的验证处理
	 * @param str 要验证的数据
	 * @param regex 验证通过返回true，否则返回false
	 * @return
	 */
	public static boolean isRegex(String str,String regex) {
		if (ValidatorUtil.isString(str)) {
			return str.matches(regex) ;
		}
		return false ;
	}
}
