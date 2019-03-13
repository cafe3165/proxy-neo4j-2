package com.appleyk.Proxy.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceMapService {

	// 类之间的映射 即 k - 底层设备类 v - 运行时设备类
		public static Map<String, String> classMaps = new HashMap<>();

		// 方法之间的映射 即 k - 运行时api v - 底层设备api
		public static Map<String, List<String>> apiMaps = new HashMap<>();

		// 底层设备对象与运行时对象之间的映射 k - 运行时对象 v - 底层设备对象
		public static Map<Object, Object> objMaps = new HashMap<>();

}
