package utils;

/**
 * 문자열이 null, 빈 문자열, "null" 인지 확인해주는 유틸
 */
public class IsEmpty {
	public static boolean main(String args) {
		if (args == null || "".equals(args) ){
			return true;
		}
		return false;
	}
}
