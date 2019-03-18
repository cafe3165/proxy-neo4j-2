package com.appleyk.Proxy.virtualObejct.GenRTModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.virtualObejct.Context;
import com.appleyk.Proxy.virtualObejct.Contexts;
import com.appleyk.Proxy.virtualObejct.Service;
import com.appleyk.Proxy.virtualObejct.Services;
import com.appleyk.Proxy.virtualObejct.init.initConcept;

public class genContext {

	public static void genC(Map<String, String> serConMap, Map<String, String> userIdNameMap,
			Map<String, Object> userMap, Map<String, Object> serMap, Map<String, Object> contMap, Services services,
			Contexts contexts) {
		Context c11 = new Context();
		Context c21 = new Context();

		String CUName1 = "Jack";
		String CCType1 = "temperature";
		double RMin1 = 20.0;
		double RMax1 = 30.0;
		String CID1 = "C11";

		String CUName2 = "Ben";
		String CCType2 = "temperature";
		double RMin2 = 15.0;
		double RMax2 = 25.0;
		String CID2 = "C12";

		List<String> CIDList = new ArrayList<String>();
		CIDList.add(CID1);
		CIDList.add(CID2);

//		服务与环境的绑定
		int index = 0;
		for (String s : services.list(false)) {
			Service ts = new Service();
			ts = (Service) services.ListProperties(s, serMap, false);
			if (ts.getEffect().equals("Monitor")) {
				serConMap.put(CIDList.get(index), ts.getServiceId());
				index++;

			} else {
				continue;
			}
		}
		c11 = (Context) initConcept.initContext(CUName1, CCType1, RMin1, RMax1, CID1, c11, userIdNameMap, userMap,
				serConMap, serMap);
		c21 = (Context) initConcept.initContext(CUName2, CCType2, RMin2, RMax2, CID2, c21, userIdNameMap, userMap,
				serConMap, serMap);
		contMap.put(c11.getCId(), c11);
		contMap.put(c21.getCId(), c21);
		contexts.addlist(CID1);
		contexts.addlist(CID2);
//		for(String cid:contexts.list(true)) {
//			System.out.println("------------------");
//			contexts.ListProperties(cid, contMap,true);
//			
//		}

	}

}
