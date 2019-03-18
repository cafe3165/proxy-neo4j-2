package com.appleyk.Proxy.virtualObejct.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.runtime.AirCondition;
import com.appleyk.Proxy.virtualObejct.Context;
import com.appleyk.Proxy.virtualObejct.Location;
import com.appleyk.Proxy.virtualObejct.Service;
import com.appleyk.Proxy.virtualObejct.User;

public class initConcept {

//	初始化服务
	public static Object initService(String ServiceId, String DeviceId, String RutimeDeviceId, String DName,
			String CType, String Effect, Object obj) {
		Service ser = new Service();
		ser.setServiceId(ServiceId);
		ser.setDeviceId(DeviceId);
		ser.setRutimeDeviceId(RutimeDeviceId);
		ser.setDName(DName);
		ser.setCType(CType);
		ser.setEffect(Effect);
		ser.setSValue(0.0);

		obj = ser;
		return obj;

	}

//  服务配置
	public static void serConfig(AirCondition airCon, Service service, Map<String, String> SerDevMaps,
			Map<String, Object> serMap) {
//		将服务id与运行时设备id绑定
		SerDevMaps.put(service.getServiceId(), service.getRutimeDeviceId());
//		将服务id与服务对象绑定
		serMap.put(service.getServiceId(), service);
//		服务从设备哪里获得相应的属性值
		SerMapDev_AirC(airCon, service);
	}

//	从服务所绑定的设备中提取对应属性值
	public static void SerMapDev_AirC(Object dev, Object ser) {
		Service service = (Service) ser;
		AirCondition airc = (AirCondition) dev;
		service.setLName(airc.getLName());
		service.setStatus("off");
		service.setSValue(airc.getT());
//		Field[] fields = d.getClass().getDeclaredFields();
//		List<String> atrrList = new ArrayList<>();

//		System.out.println(d.getClass().getName());
//		for (Field field : fields) {
//			String temp = field.toString();
//			String[] fStrings = temp.split("\\.");
//			if (fStrings.length == 6) {
//				atrrList.add(fStrings[5]);
//			}
//
//			else {
//				atrrList.add(fStrings[7]);
//			}
//
//		}
//		System.out.println(atrrList);

	}

// 	初始化位置
	public static Object initLocation(String LId, String LName, Object location, Map<Object, Object> objMaps,
			Map<String, String> SerDevMaps, Map<String, String> idmaps) {

		AirCondition tempA = null;
		AirCondition tempB = null;
//		{null=com.appleyk.Proxy.device.Gree@1b6d3586, null=com.appleyk.Proxy.device.Panasonic@4554617c}
//		通过底层设备与运行时设备的映射找到底层设备对象underDevice
		for (Object o : objMaps.keySet()) {
			tempA = (AirCondition) o;
			if (tempA.getLName().equals(LName)) {
				tempB = tempA;
			}
		}

		List<String> DIdList = new ArrayList<>();
		List<String> SIdList = new ArrayList<>();
		for (String DId : idmaps.keySet()) {
			if (tempB.hashCode() == Integer.valueOf(idmaps.get(DId))) {
				DIdList.add(DId);
			}
		}

		for (String SId : SerDevMaps.keySet()) {
			if (tempB.hashCode() == Integer.valueOf(SerDevMaps.get(SId))) {
				SIdList.add(SId);
			}
		}

		Location tempLocation = (Location) location;
		tempLocation.setLId(LId);

		tempLocation.setLName(LName);
		tempLocation.setdList(DIdList);
		tempLocation.setsList(SIdList);

		return tempLocation;

	}

//	初始化用户
	public static Object initUser(String UName, String LName, String UId, Object user, Map<String, String> locIdNameMap,
			List<String> CList) {
		User u = (User) user;
		u.setUName(UName);
		u.setUId(UId);
		u.setLName(LName);
		u.setLId(locIdNameMap.get(LName));
		u.setContextList(CList);

		return u;
	}
	
//	初始化环境状态
	public static Object initContext(String UName, String CType, double RMin, double RMax, String CId, Object context,
			Map<String, String> userIdNameMap, Map<String, Object> userMap, Map<String, String> serConMap,
			Map<String, Object> serMap) {
		Context c = (Context) context;
		c.setUName(UName);
		c.setCType(CType);
		c.setRMin(RMin);
		c.setRMax(RMax);
		c.setCId(CId);

		User user = new User();
		user = (User) userMap.get(userIdNameMap.get(UName));

		c.setLName(user.getLName());
		c.setLId(user.getLId());
//		System.out.println(serConMap);
		if (serConMap.get(CId) != null) {
			Service s = (Service) serMap.get(serConMap.get(CId));
//			System.out.println(s.getStatus());
			if (s.getStatus().equals("on")) {
				c.setCValue(s.getSValue());
//				System.out.println(c.getCId());
//				System.out.println(c.getLName());
			}

			else {

//				System.out.println("已找到该服务，但服务尚未开启！");
			}
		}

		else {
//			System.out.println(c.getCId());
//			System.out.println("没有找到提供该服务的设备！");
		}

		return c;

	}
	
}
