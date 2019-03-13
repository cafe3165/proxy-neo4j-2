package com.appleyk.Proxy.virtualObejct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.runtime.AirCondition;
import com.appleyk.Proxy.virtualObejct.Service;

public class Services {
	public List<String> sl = new ArrayList<String>();
//	public HashMap<Object,List<String>> propertiesList=new HashMap<Object,List<String>>();
	public static Map<String, String> sdmapHashMap = new HashMap<>();

	public void addlist(Map<String, String> sd) {
		for (String s : sd.keySet()) {
			sl.add(s);
		}
	}

	public List<String> list() {
		System.out.println(sl);
		return sl;
	}

	public List<String> getList() {
		return sl;
	}

//	列出服务的所有属性
	public void ListProperties(String SerId, Map<String, Object> sMap) {
//		sMap存放服务id与服务对象的映射
//		根据服务id(SerId)去寻找对应的服务对象
		Service s = new Service();
		for (String sid : sMap.keySet()) {
			if (SerId.equals(sid))
				s = (Service) sMap.get(sid);
		}
		System.out.println("ServiceId: " + s.getServiceId());
		System.out.println("DeviceId: " + s.getDeviceId());
//		System.out.println("RutimeDeviceId: " + s.getRutimeDeviceId());
		System.out.println("DName: " + s.getDName());
		System.out.println("CType: " + s.getCType());
		System.out.println("Effect: " + s.getEffect());
		
		System.out.println("LName: " + s.getLName());
		System.out.println("Status: " + s.getStatus());
		System.out.println("SValue: " + s.getSValue());

	}

	// 对服务进行属性设置从而转化成对设备的设值
	public void SetDevProperties(String SerId, String Value, String SKey, Map<String, String> SerDevMaps,
			Map<String, String> idmaps, Map<String, Object> idObjmaps, Map<Object, Object> objMaps,
			Map<String, Object> SerMap,Map<String, Object> contMap) {
//		获得当前操作服务对象
		Service currentService = (Service) SerMap.get(SerId);
//		服务id与运行时设备id的映射:SerDevMaps  {S11=2101973421, S22=685325104, S21=685325104}

//		通过服务映射获得运行时设备id(dId)
		String dId = SerDevMaps.get(SerId);
		Object destinDevice = null;
//		运行时对象标识id与运行时对象的映射
//		{2101973421=com.appleyk.Proxy.device.Gree@1b6d3586, 685325104=com.appleyk.Proxy.device.Panasonic@4554617c}
//		通过运行时对象标识id映射寻找目标运行时对象设备destinDevice
		for (String iString : idObjmaps.keySet()) {
			if (iString.equals(dId))
				destinDevice = idObjmaps.get(iString);
		}
//		
		Object underDevice = null;
//		{null=com.appleyk.Proxy.device.Gree@1b6d3586, null=com.appleyk.Proxy.device.Panasonic@4554617c}
//		通过底层设备与运行时设备的映射找到底层设备对象underDevice
		for (Object o : objMaps.keySet()) {
			if (objMaps.get(o).hashCode() == destinDevice.hashCode()) {
				underDevice = o;
			}
		}
//		将找到的底层设备实例化为运行时空调
		Object runtimeDevice = underDevice;
		AirCondition airCon = (AirCondition) runtimeDevice;
//		将输入的SKey与Value进行匹配处理，SKey是服务属性，如：DName，LName；Value可以是数字或者字符串
		if (isNum(Value) && currentService.getEffect().equals("Assign")) {
			airCon.setT(Double.valueOf(Value.toString()));
			currentService.setSValue(Double.valueOf(Value.toString()));
			
//			System.out.println(contMap);
//			查找相关环境状态
			for(String cid:contMap.keySet()) {
				Context c=new Context();
				c=(Context)contMap.get(cid);
				if(c.getLName().equals(currentService.getLName())) {
					c.setCValue(Double.valueOf(Value.toString()));
				}
					
			}
			
			
			
			System.out.println("Set Service.SValue and Device.Key Success!");
			return;
		} else {
			if (isNum(Value)) {
				System.out.println("不是Assign情况，不能赋值");
				return;
			}
			if (SKey.equals("Status")) {
				airCon.setStatus(Value);
				currentService.setStatus(Value);
				
				System.out.println("Set Service.Status and Device.Status Success!");
				return;
			}

		}

	}

// 		数字判断函数
	public static boolean isNum(String string) {
		for (int i = string.length(); --i >= 0;) {
			int chr = string.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;

	}
}
