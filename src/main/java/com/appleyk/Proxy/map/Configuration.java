package com.appleyk.Proxy.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.device.Gree;
import com.appleyk.Proxy.device.Midea;
import com.appleyk.Proxy.device.Opple;
import com.appleyk.Proxy.device.Panasonic;
import com.appleyk.Proxy.device.Philips;
import com.appleyk.Proxy.runtime.AirCleaner;
import com.appleyk.Proxy.runtime.AirCleanerImpl;
import com.appleyk.Proxy.runtime.AirCondition;
import com.appleyk.Proxy.runtime.AirConditionImpl;
import com.appleyk.Proxy.runtime.Light;
import com.appleyk.Proxy.runtime.LightImpl;

public class Configuration {
	// 类之间的映射 即 k - 底层设备类 v - 运行时设备类
		public static Map<String, String> classMaps = new HashMap<>();

		// 方法之间的映射 即 k - 运行时api v - 底层设备api
		public static Map<String, List<String>> apiMaps = new HashMap<>();

		// 底层设备对象与运行时对象之间的映射 k - 运行时对象 v - 底层设备对象
		public static Map<Object, Object> objMaps = new HashMap<>();

		/**
		 * 这边应该是读取配置文件得到映射关系 ，但我这边直接初始化映射关系
		 */
		public static void config() throws Exception {

			// 类之间的映射关系
			// 模拟配置
			String packageUnderDevice = "com.appleyk.Proxy.device";
			String configUnderDevice = "Gree";
			String UDString = packageUnderDevice + "." + configUnderDevice;

			String packageRuntimeDevice = "com.appleyk.Proxy.runtime";
			String configRutimeDevice = "AirConditionImpl";
			String RTString = packageRuntimeDevice + "." + configRutimeDevice;

			// 空调
			Class<?> underDevice = Class.forName(UDString);
			Class<?> runtimeDevice = Class.forName(RTString);
			classMaps.put(underDevice.getName(), runtimeDevice.getName());
			classMaps.put(Panasonic.class.getName(), AirConditionImpl.class.getName());
			// 电灯
			classMaps.put(Midea.class.getName(), LightImpl.class.getName());
			classMaps.put(Opple.class.getName(), LightImpl.class.getName());
			// 空气净化器
			classMaps.put(Philips.class.getName(), AirCleanerImpl.class.getName());

			// 方法之间的映射关系
			// 1.空调的降温方法
			apiMaps.put(AirCondition.class.getName() + "." + AirCondition.class.getMethod("cool").getName(),
					Arrays.asList(new String[] { Gree.class.getName() + "." + Gree.class.getMethod("cool").getName(),
							Panasonic.class.getName() + "." + Panasonic.class.getMethod("down").getName() }));
			apiMaps.put(AirCondition.class.getName() + "." + AirCondition.class.getMethod("setT", float.class).getName(),
					Arrays.asList(new String[] {
							Gree.class.getName() + "." + Gree.class.getMethod("setTemperature", float.class).getName(),
							Panasonic.class.getName() + "."
									+ Panasonic.class.getMethod("setTemperature", float.class).getName() }));
			apiMaps.put(AirCondition.class.getName() + "." + AirCondition.class.getMethod("setID", String.class).getName(),
					Arrays.asList(new String[] {
							Gree.class.getName() + "." + Gree.class.getMethod("setId", String.class).getName(),
							Panasonic.class.getName() + "."
									+ Panasonic.class.getMethod("setId", String.class).getName() }));
			apiMaps.put(AirCondition.class.getName() + "." + AirCondition.class.getMethod("getID").getName(),
					Arrays.asList(new String[] { Gree.class.getName() + "." + Gree.class.getMethod("getId").getName(),
							Panasonic.class.getName() + "." + Panasonic.class.getMethod("getId").getName() }));

			// 2.电灯的提高亮度方法
			apiMaps.put(Light.class.getName() + "." + Light.class.getMethod("illumine").getName(),
					Arrays.asList(new String[] {
							Midea.class.getName() + "." + Midea.class.getMethod("IncreaseLedBrightness").getName(),
							Opple.class.getName() + "." + Opple.class.getMethod("RaiseBrightness").getName() }));
			// 3.电灯的降低亮度方法
			apiMaps.put(Light.class.getName() + "." + Light.class.getMethod("darken").getName(),
					Arrays.asList(new String[] {
							Midea.class.getName() + "." + Midea.class.getMethod("ReduceLedBrightness").getName(),
							Opple.class.getName() + "." + Opple.class.getMethod("LowerBrightness").getName() }));

			// 4.空气净化器的净化方法

			apiMaps.put(AirCleaner.class.getName() + "." + AirCleaner.class.getMethod("purify").getName(), Arrays.asList(
					new String[] { Philips.class.getName() + "." + Philips.class.getMethod("ReducePM2_5").getName() }));

			// 5.空气净化器的获得当前PM2.5方法
			apiMaps.put(AirCleaner.class.getName() + "." + AirCleaner.class.getMethod("getPM2_5").getName(), Arrays.asList(
					new String[] { Philips.class.getName() + "." + Philips.class.getMethod("getPM2_5").getName() }));
			// 5.空气净化器的获得设置PM2.5方法
			apiMaps.put(AirCleaner.class.getName() + "." + AirCleaner.class.getMethod("setPM2_5", float.class).getName(),
					Arrays.asList(new String[] {
							Philips.class.getName() + "." + Philips.class.getMethod("setPM2_5", float.class).getName() }));

//			for (List<String> s : apiMaps.values()) {
//				System.out.println(s);
//			}

//			
//			apiMaps.put(AirConditioners.class.getName() + "." + AirConditioners.class.getMethod("addlist",Object.class).getName(),
//					Arrays.asList(new String[] { Airconditioner.class.getName() + "." + Airconditioner.class.getMethod("addlist",Object.class).getName()}));
//			apiMaps.put(AirConditioners.class.getName() + "." + AirConditioners.class.getMethod("list").getName(),
//					Arrays.asList(new String[] { Airconditioner.class.getName() + "." + Airconditioner.class.getMethod("list").getName()}));

		}

}
