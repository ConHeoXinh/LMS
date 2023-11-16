package longND.fpt.home.util;

import longND.fpt.home.request.BookRequest;

public class Util {
	public static String genCode(int size) {
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(size);

		for (int i = 0; i < size; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
	
	public static BookRequest convertStringToBookRequest(String item) {
		

		return null;
	}
}
