package com.appleyk.Proxy.relation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.runtime.AirCondition;
import com.appleyk.Proxy.virtualObejct.Location;
import com.appleyk.Proxy.virtualObejct.User;

public class LocatedIn {
	public static Map<String, String> XLIdMaps = new HashMap<String, String>();
	public static Map<String, String> ULIdMaps = new HashMap<String, String>();
	public static Map<String, String> DLIdMaps = new HashMap<String, String>();

	public static Map<Object, Object> XLObjMaps = new HashMap<Object, Object>();

	public static void createLocatedIn(List<String> XList, List<String> LList, Map<String, Object> XMap,
			Map<String, Object> locationMap) {
//		System.out.println("this is createLocatedIn : ");
//		System.out.println(XList);
//		System.out.println(LList);
//		System.out.println(XMap);
//		System.out.println(locationMap);

		String[] fStrings = null;
		for (String xId : XList) {
			fStrings = XMap.get(xId).getClass().getName().split("\\.");
			break;
		}
		String ctl = "";
		if (fStrings[3].equals("$Proxy0"))
			ctl = "Device";
		else {
			ctl = fStrings[4];
		}

//		System.out.println(fStrings[4]);

		switch (ctl) {
		case "User":
			userLocatedIn(XList, LList, XMap, locationMap);
//			System.out.println(ULIdMaps);
			break;
		case "Device":
			devicelocatedIn(XList, LList, XMap, locationMap);
//			System.out.println(DLIdMaps);

			break;
		default:
			break;
		}
	}

	public static void userLocatedIn(List<String> XList, List<String> LList, Map<String, Object> XMap,
			Map<String, Object> locationMap) {
//		System.out.println("这个是用户");
		for (String xId : XList) {
			User user = (User) XMap.get(xId);
			for (String lId : LList) {
				Location loc = (Location) locationMap.get(lId);
				if (user.getLName().equals(loc.getLName())) {
					ULIdMaps.put(xId, lId);
				} else {
					continue;
				}
			}

		}

	}

	public static void devicelocatedIn(List<String> XList, List<String> LList, Map<String, Object> XMap,
			Map<String, Object> locationMap) {
//		System.out.println("这个是设备");
//		System.out.println(XList);
		for (String xId : XList) {
			AirCondition d = (AirCondition) XMap.get(xId);
			for (String lId : LList) {
				Location loc = (Location) locationMap.get(lId);
				if (d.getLName().equals(loc.getLName())) {

					DLIdMaps.put(xId, lId);
				} else {
					continue;
				}
			}

		}

	}

}
