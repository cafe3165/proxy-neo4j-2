package com.appleyk.Proxy.map;

import com.appleyk.Proxy.Proxy.ProxyUtils;
import com.appleyk.Proxy.virtualObejct.Services;
import com.appleyk.Proxy.virtualObejct.User;
import com.appleyk.Proxy.virtualObejct.Users;
import com.appleyk.Proxy.virtualObejct.GenRTModel.genContext;
import com.appleyk.Proxy.virtualObejct.GenRTModel.genDevice;
import com.appleyk.Proxy.virtualObejct.GenRTModel.genLocation;
import com.appleyk.Proxy.virtualObejct.GenRTModel.genService;
import com.appleyk.Proxy.virtualObejct.GenRTModel.genUser;
import com.appleyk.Proxy.virtualObejct.init.initConcept;
import com.appleyk.Proxy.virtualObejct.Context;
import com.appleyk.Proxy.virtualObejct.Contexts;
import com.appleyk.Proxy.virtualObejct.Location;
import com.appleyk.Proxy.virtualObejct.Locations;
import com.appleyk.Proxy.virtualObejct.Service;
import com.appleyk.Proxy.device.Airconditioner;
import com.appleyk.Proxy.device.Gree;
import com.appleyk.Proxy.device.Panasonic;
import com.appleyk.Proxy.device.Philips;
import com.appleyk.Proxy.device.Midea;
import com.appleyk.Proxy.device.Opple;
import com.appleyk.Proxy.runtime.AirCleaner;
import com.appleyk.Proxy.runtime.AirCleanerImpl;
import com.appleyk.Proxy.runtime.AirCondition;

import com.appleyk.Proxy.virtualObejct.Devices;
import com.appleyk.Proxy.runtime.Light;
import com.appleyk.Proxy.runtime.LightImpl;
import com.appleyk.Proxy.util.charUtil;
import com.appleyk.Proxy.util.fileUtil;
import com.appleyk.Proxy.util.sleepUtil;
import com.appleyk.Proxy.relation.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.runtime.AirCleanersImpl;

/**
 * @author chenshihong02
 * @version 1.0
 * @created 2018/12/23 下午6:50
 **/
public class Relation {

	// 类之间的映射 即 k - 底层设备类 v - 运行时设备类
	public static Map<String, String> classMaps = new HashMap<>();

	// 方法之间的映射 即 k - 运行时api v - 底层设备api
	public static Map<String, List<String>> apiMaps = new HashMap<>();

	// 底层设备对象与运行时对象之间的映射 k - 运行时对象 v - 底层设备对象
	public static Map<Object, Object> objMaps = new HashMap<>();

	// 运行时对象标识与运行时对象的映射 k-运行时对象的hashcode v-运行时对象
	public static Map<String, Object> idObjmaps = new HashMap<>();

	// 底层设备id与运行时对象标识的映射 k-底层设备id v-运行时对象的hashcode A1:685325104
	public static Map<String, String> idmaps = new HashMap<>();
	// 底层设备id与运行时对象的映射 k-底层设备id v-运行时对象
	public static Map<String, Object> uidMaps = new HashMap<>();

	// 服务id与运行时设备id的映射 k-服务id v-运行时设备id S11:2101973421
	public static Map<String, String> SerDevMaps = new HashMap<>();

//	存放服务id与服务对象的映射
	public static Map<String, Object> serMap = new HashMap<>();

//	存放服务id与环境id的映射
	public static Map<String, String> serConMap = new HashMap<>();
//	存放环境id与环境对象的映射
	public static Map<String, Object> contMap = new HashMap<>();

	/**
	 * 这边应该是读取配置文件得到映射关系 ，但我这边直接初始化映射关系
	 */

	/**
	 * 通过配置文件生成底层设备，这边我就手动写设备
	 */
	public static void generateDeviceAndRuntime(Map<String, String> cmdMaps) throws Exception {

//		存放位置id与位置对象的映射
		Map<String, Object> locationMap = new HashMap<>();

//		存放位置id与位置名的映射
		Map<String, String> locIdNameMap = new HashMap<>();

//		存放用戶id与用戶对象的映射
		Map<String, Object> userMap = new HashMap<>();

//		存放用戶id与用戶名的映射
		Map<String, String> userIdNameMap = new HashMap<>();

//		List<HashMap<String, Object>> idObjList = new ArrayList<HashMap<String,Object>>();
		System.out.println("系统初始化开始。");
		
//		生成设备
		
		Devices airConditions = new Devices();
		List dList = genDevice.genD(objMaps, idObjmaps, idmaps, uidMaps, airConditions);
//		生成服务
		Services services = new Services();
		genService.genS(idmaps,SerDevMaps,serMap,dList,services);
		List<String> SList = services.list(false);
//
//		生成位置
		Locations locations = new Locations();
		genLocation.genL(locIdNameMap, locationMap, objMaps, SerDevMaps, idmaps, locations);
		List<String> LList = locations.list(false);
//
//		生成用户
		Users users = new Users();
		genUser.genU(locIdNameMap, userMap, userIdNameMap, users);
		List<String> UList = users.list(false);
//		创建位于关系
//		LocatedIn.createLocatedIn(UList, LList, userMap, locationMap);
//		LocatedIn.createLocatedIn(airCList, LList, uidMaps, locationMap);

//		生成环境
		Contexts contexts = new Contexts();
		genContext.genC(serConMap, userIdNameMap, userMap, serMap, contMap, services, contexts);
		List<String> CList = contexts.list(false);

		System.out.println("系统初始化结束。");
		System.out.println("测试开始");
		if (!cmdMaps.get("attribute").equals("none")) {
			TestCmd.testCmd(cmdMaps, services, SerDevMaps, idmaps, idObjmaps, objMaps, serMap, contMap);
		} else {
			System.out.println("这是开关操作");
			TestCmd.testCmd2(cmdMaps, airConditions, idmaps, idObjmaps, objMaps);
		}

		inference.judgeContext(contexts, services, serMap, contMap);
		inference.changeContext(services, contexts, serMap, contMap);

	}

	/**
	 * device 底层设备类名
	 * 
	 * @param device
	 */
	public static Object generate(String device) throws Exception {
		// 生成底层设备对象
		Object deviceObj = Class.forName(device).newInstance();
//		System.out.println("deviceObj:" + deviceObj.hashCode());
		// 通过类映射关系 获取到底层设备 对应的 运行时类
		for (String deviceType : classMaps.keySet()) {

			if (deviceType.equals(device)) {
				String runtimeType = classMaps.get(deviceType);

				Class<?> runtimeClass = Class.forName(runtimeType);
				// 生成运行时对象
				Object runtimeObj = runtimeClass.newInstance();

				// 将运行时对象的类型设置成底层设备的类型
				Field type = runtimeClass.getDeclaredField("type");

				// 获得各个设备的方法
				Method[] methods = runtimeClass.getDeclaredMethods();

				String functionType = "";

				for (Method m : methods) {
					String temp = m.toString();
					String[] ff = temp.split("\\.");
					String[] ff2 = ff[2].split("\\(");
					functionType = ff2[0];
//				Method method = runtimeClass.getDeclaredMethod(functionType);

				}

				type.setAccessible(true);
				type.set(runtimeObj, deviceType);

//				System.out.println(runtimeObj);
//				System.out.println("runtimeObj:" + runtimeObj.hashCode());
				// 生成运行时对象的代理对象
				Object proxyObj = ProxyUtils.getProxy(runtimeObj);

				// 将运行时对象代理与底层设备对象放入objMaps
				objMaps.put(proxyObj, deviceObj);
//				System.out.println(proxyObj);
				return proxyObj;
			}
		}
		return null;
	}
//

	/**
	 * 测试
	 * 
	 * @param args
	 */

	public static void main(String[] args) throws Exception {
		Map<String, String> cmdMaps = new HashMap<>();
		Configuration.config(classMaps, apiMaps);

//		String filePath = "C:\\Users\\more\\Desktop\\code\\exttst.txt";
		String filePath = "exttst.txt";

		cmdMaps = fileUtil.fileOp(filePath);
//		System.out.println(cmdMaps);
		generateDeviceAndRuntime(cmdMaps);

	}

}
