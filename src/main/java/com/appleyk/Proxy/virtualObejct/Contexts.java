package com.appleyk.Proxy.virtualObejct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Contexts {

	public List<String> ContextIdList = new ArrayList<String>();

	public void addlist(String CId) {
		ContextIdList.add(CId);
	}

//	列出所有环境id
	public List<String> list(boolean f) {
		if(f) {
			System.out.println(ContextIdList);
		}
		
		return ContextIdList;

	}

//	列出环境的所有属性
	public Object ListProperties(String ContextId, Map<String, Object> contextMap,boolean f) {
		Object context = null;
		for (String cId : contextMap.keySet()) {
			if (cId.equals(ContextId)) {
				context = contextMap.get(cId);
			}
		}
		Context c = (Context) context;
		if(f) {
			System.out.println("CId: " + c.getCId());
			System.out.println("UName: " + c.getUName());
			System.out.println("LName: " + c.getLName());
			System.out.println("CType： " + c.getCType());
			System.out.println("CValue: " + c.getCValue());
			System.out.println("RMin: " + c.getRMin());
			System.out.println("RMax: " + c.getRMax());
			
		}
		
		
		return c;
		
		
		
		
		
		
	}

}
