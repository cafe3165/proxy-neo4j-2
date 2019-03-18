package com.appleyk.Proxy.virtualObejct.GenRTModel;

import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.virtualObejct.Location;
import com.appleyk.Proxy.virtualObejct.Locations;
import com.appleyk.Proxy.virtualObejct.init.initConcept;

public class genLocation {

	public static void genL(Map<String, String> locIdNameMap, Map<String, Object> locationMap,
			Map<Object, Object> objMaps, Map<String, String> SerDevMaps,Map<String, String> idmaps,Locations ls) {

		locIdNameMap.put("666", "777");

		// 位置生成
		String lName1 = "bedroom";
		String lId1 = "L1";
		Location l1 = new Location();

		String lName2 = "sittingroom";
		String lId2 = "L2";
		Location l2 = new Location();

		locIdNameMap.put(lName1, lId1);
		locIdNameMap.put(lName2, lId2);
		l1 = (Location) initConcept.initLocation(lId1, lName1, l1, objMaps, SerDevMaps, idmaps);
		l2 = (Location) initConcept.initLocation(lId2, lName2, l2, objMaps, SerDevMaps, idmaps);
		locationMap.put(l1.getLId(), l1);
		locationMap.put(l2.getLId(), l2);

		ls.addlist(l1.getLId());
		ls.addlist(l2.getLId());
		
//				ls.ListProperties(l1.getLId(), locationMap);
//				ls.ListProperties(l2.getLId(), locationMap);

	}

}
