package com.appleyk.Proxy.map;

import java.util.HashMap;
import java.util.Map;

import com.appleyk.Proxy.runtime.AirCondition;
import com.appleyk.Proxy.util.sleepUtil;
import com.appleyk.Proxy.virtualObejct.Devices;
import com.appleyk.Proxy.virtualObejct.Services;

public class TestCmd {
//	核心是去寻找设备，并改变其状态
	public static void testCmd2(Map<String, String> cmdMaps, Devices devices,Map<String, String> idmaps, Map<String, Object> idObjmaps, Map<Object, Object> objMaps) {

//		System.out.println(cmdMaps);
		Object airCon = null;
		for (String did : devices.list(false)) {
			AirCondition airCondition = (AirCondition) devices.ListProperties(did, objMaps, idObjmaps, idmaps, false);
			if (airCondition.getLName().equals(cmdMaps.get("location"))) {
				airCon = airCondition;
				break;
			}

		}
		AirCondition airC = (AirCondition) airCon;
		String op = judge.judgeOperation(cmdMaps.get("operation"));
		System.out.println(airC.getDName() + "空调当前状态为：" + airC.getStatus());
		System.out.println("改变空调" + airC.getDName() + "状态。");
		airC.setStatus(op);
		System.out.println(airC.getDName() + "空调当前状态为：" + airC.getStatus());
	}

	// 核心是去找service，并最终执行
	public static void testCmd(Map<String, String> cmdMaps, Services services, Map<String, String> SerDevMaps,
			Map<String, String> idmaps, Map<String, Object> idObjmaps, Map<Object, Object> objMaps,
			Map<String, Object> serMap, Map<String, Object> contMap) throws InterruptedException {
		Map<String, String> doMap = new HashMap<String, String>();

		System.out.println("根据已知信息，寻找对应服务。");
		sleepUtil.Sleep();
		doMap = findUtil.findSer(cmdMaps, services, serMap);
		sleepUtil.Sleep();
		System.out.println("开始执行服务操作：");
//		System.out.println(doMap);
		String SerId = doMap.get("SerId");
		String Value = doMap.get("Value");
		String SKey = doMap.get("SKey");
		services.SetDevProperties(SerId, Value, SKey, SerDevMaps, idmaps, idObjmaps, objMaps, serMap, contMap);
//		changeContext(services, contexts);
//		for (String cid : contexts.list(false)) {
//			Context context = (Context) contexts.ListProperties(cid, contMap, true);
//		}
//
//		judgeContext(contexts, services);
//		outputTime();

//		Sleep();
//		

//		Sleep();
//		outputTime();

	}

}
