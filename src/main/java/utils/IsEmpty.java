package utils;

public class IsEmpty {

	public static boolean main(String args) {
		if (args == null || args.equals("") || args.equals("null")){
			return true;
		}
		return false;
	}
}
