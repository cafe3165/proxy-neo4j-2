package com.appleyk.Proxy.virtualObejct.init;

import java.util.Map;

import com.appleyk.Proxy.runtime.AirCondition;
import com.appleyk.Proxy.virtualObejct.Service;

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
	public static void serConfig(AirCondition airCon, Service service,Map<String, String> SerDevMaps,Map<String, Object> serMap) {
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

}
