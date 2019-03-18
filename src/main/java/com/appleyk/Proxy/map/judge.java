package com.appleyk.Proxy.map;

import java.util.Map;

public class judge {
	
	public static String judgeType(Map<String, String> cmdMaps) {

		String type = null;
		String t = null;
		t = judgeOperation(cmdMaps.get("operation"));
		switch (t) {
		case "Increase":
			type = "stepUp";
			break;
		case "Reduce":
			type = "stepDown";
			break;

		default:
			break;
		}

		return type;

	}
	
	
	public static String judgeSkey(Map<String, String> cmdMaps) {
		String key = null;

		if (cmdMaps.get("attribute") != null) {
			key = "CType";
		} else {
			key = "Status";
		}

		return key;

	}

	public static String judgeOperation(String operation) {
		String op = null;

		switch (operation) {
		case "turnup":
			op = "Increase";
			break;
		case "turndown":
			op = "Reduce";
			break;
		case "turnoff":
			op = "off";
			break;
		case "turnon":
			op = "on";
			break;

		default:
			break;
		}

		return op;
	}



}

