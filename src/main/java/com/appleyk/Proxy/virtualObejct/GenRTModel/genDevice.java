package com.appleyk.Proxy.virtualObejct.GenRTModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.device.Gree;
import com.appleyk.Proxy.device.Panasonic;
import com.appleyk.Proxy.map.Relation;
import com.appleyk.Proxy.map.findUtil;
import com.appleyk.Proxy.runtime.AirCondition;
import com.appleyk.Proxy.virtualObejct.Devices;

public class genDevice {

	public static List<Object> genD(Map<Object, Object> objMaps, Map<String, Object> idObjmaps, Map<String, String> idmaps,
			Map<String, Object> uidMaps, Devices devices) throws Exception {
		List<Object> dList=new ArrayList<Object>();
		
		// 底层设备生成 返回一个运行时对象
		AirCondition gree = (AirCondition) Relation.generate(Gree.class.getName());
		AirCondition panasonic = (AirCondition) Relation.generate(Panasonic.class.getName());

		// 运行时对象调用
		Object dObject = findUtil.findUnderDByRuntimeD(objMaps.get(gree), objMaps);
		AirCondition ndAirCondition = (AirCondition) dObject;
		ndAirCondition.setID("D5");
		ndAirCondition.setDName("Gree");
		ndAirCondition.getID();
		ndAirCondition.setT(0.0);
		ndAirCondition.setLName("bedroom");
		ndAirCondition.setStatus("off");
		idObjmaps.put(String.valueOf(gree.hashCode()), objMaps.get(gree));
		idmaps.put(gree.getID(), String.valueOf(gree.hashCode()));

		panasonic.setID("D1");
		panasonic.setDName("Panasonic");
		panasonic.setLName("sittingroom");
		panasonic.setT(0.0);
		panasonic.setStatus("off");
		idObjmaps.put(String.valueOf(panasonic.hashCode()), objMaps.get(panasonic));
		idmaps.put(panasonic.getID(), String.valueOf(panasonic.hashCode()));

		// 运行时空调对象集合，有添加空调的方法addlist和列出运行时空调的方法list
//				Devices airConditions = new Devices();
		// 遍历运行时对象标识与底层设备id的映射，添加运行时设备对应的底层设备id
		for (Map.Entry<String, String> mEntry : idmaps.entrySet()) {
			devices.addlist(mEntry.getKey());
		}
		// 列出运行时的空调对应的底层空调
//				System.out.println("当前设备为：");
		List<String> airCList = devices.list(false);
//				根据设备id获得所有设备的属性
//				System.out.println("设备属性列表：");
//				for (String underDeviceId : airCList) {
//					System.out.println("---------------------------");
//					airConditions.ListProperties(underDeviceId, objMaps, idObjmaps, idmaps);
//					
		//
//				}
//				System.out.println();

		uidMaps.put(ndAirCondition.getID(), ndAirCondition);
		uidMaps.put(panasonic.getID(), panasonic);
		dList.add(ndAirCondition);
		dList.add(panasonic);
		
		return dList;
	}

}
