package com.appleyk.Proxy.util;

import java.util.Map;

public class charUtil {
	
	public static void outPutInfo(Map<String, String> map) {

		for (String p : map.keySet()) {
			System.out.println(p + " : " + map.get(p));
		}

	}

	public static boolean check(String fstrData) {
		char c = fstrData.charAt(0);
		if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
			return true;
		} else {
			return false;
		}
	}

	public static String removeNonLetters(String original) {
		StringBuffer aBuffer = new StringBuffer(original.length());
		char aCharacter;
		for (int i = 0; i < original.length(); i++) {
			aCharacter = original.charAt(i);
			if (Character.isLetter(aCharacter)) {
				aBuffer.append(new Character(aCharacter));
			}
		}
		return new String(aBuffer);

	}



}
