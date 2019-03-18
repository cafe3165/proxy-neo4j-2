package com.appleyk.Proxy.map;

import com.appleyk.Proxy.Proxy.ProxyUtils;
import com.appleyk.Proxy.virtualObejct.Services;
import com.appleyk.Proxy.virtualObejct.User;
import com.appleyk.Proxy.virtualObejct.Users;
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
		sleepUtil.Sleep();
		System.out.println("系统初始化开始。");
		sleepUtil.Sleep();
		// 底层设备生成 返回一个运行时对象
		AirCondition gree = (AirCondition) generate(Gree.class.getName());
		AirCondition panasonic = (AirCondition) generate(Panasonic.class.getName());

		// 运行时对象调用
		Object dObject = findUtil.findUnderDByRuntimeD(objMaps.get(gree), objMaps);
		AirCondition ndAirCondition = (AirCondition) dObject;
		ndAirCondition.setID("A0");
		ndAirCondition.setDName("Gree");
		ndAirCondition.getID();
		ndAirCondition.setT(0.0);
		ndAirCondition.setLName("bedroom");
		ndAirCondition.setStatus("off");
		idObjmaps.put(String.valueOf(gree.hashCode()), objMaps.get(gree));
		idmaps.put(gree.getID(), String.valueOf(gree.hashCode()));

		panasonic.setID("A1");
		panasonic.setDName("Panasonic");
		panasonic.setLName("sittingroom");
		panasonic.setT(0.0);
		panasonic.setStatus("off");
		idObjmaps.put(String.valueOf(panasonic.hashCode()), objMaps.get(panasonic));
		idmaps.put(panasonic.getID(), String.valueOf(panasonic.hashCode()));

//		System.out.println("空调名： "+panasonic.getDName());
//		System.out.println("空调当前温度: "+panasonic.getT());

		// 运行时空调对象集合，有添加空调的方法addlist和列出运行时空调的方法list
		Devices airConditions = new Devices();
		// 遍历运行时对象标识与底层设备id的映射，添加运行时设备对应的底层设备id
		for (Map.Entry<String, String> mEntry : idmaps.entrySet()) {
			airConditions.addlist(mEntry.getKey());
		}
		// 列出运行时的空调对应的底层空调
//		System.out.println("当前设备为：");
		List<String> airCList = airConditions.list(false);
//		根据设备id获得所有设备的属性
//		System.out.println("设备属性列表：");
//		for (String underDeviceId : airCList) {
//			System.out.println("---------------------------");
//			airConditions.ListProperties(underDeviceId, objMaps, idObjmaps, idmaps);
//			
//
//		}
//		System.out.println();

		uidMaps.put(ndAirCondition.getID(), ndAirCondition);
		uidMaps.put(panasonic.getID(), panasonic);

		String ServiceId = "S11";
		String DName = "Gree";
		String CType = "temperature";
		String Effect = "Reduce";
		String DeviceId = findUtil.findUnderid(gree.hashCode(), idmaps);
		String RutimeDeviceId = String.valueOf(gree.hashCode());

		String ServiceId2 = "S21";
		String DeviceId2 = findUtil.findUnderid(panasonic.hashCode(), idmaps);
		String RutimeDeviceId2 = String.valueOf(panasonic.hashCode());
		String DName2 = "Panasonic";
		String CType2 = "temperature";
		String Effect2 = "Reduce";

		String ServiceId3 = "S22";
		String DeviceId3 = findUtil.findUnderid(panasonic.hashCode(), idmaps);
		String RutimeDeviceId3 = String.valueOf(panasonic.hashCode());
		String DName3 = "Panasonic";
		String CType3 = "temperature";
		String Effect3 = "Increase";

		String ServiceId4 = "S12";
		String DeviceId4 = findUtil.findUnderid(gree.hashCode(), idmaps);
		String RutimeDeviceId4 = String.valueOf(gree.hashCode());
		String DName4 = "Gree";
		String CType4 = "temperature";
		String Effect4 = "Assign";

		String ServiceId5 = "S23";
		String DeviceId5 = findUtil.findUnderid(panasonic.hashCode(), idmaps);
		String RutimeDeviceId5 = String.valueOf(panasonic.hashCode());
		String DName5 = "Panasonic";
		String CType5 = "temperature";
		String Effect5 = "Monitor";

		String ServiceId6 = "S13";
		String DeviceId6 = findUtil.findUnderid(gree.hashCode(), idmaps);
		String RutimeDeviceId6 = String.valueOf(gree.hashCode());
		String DName6 = "Gree";
		String CType6 = "temperature";
		String Effect6 = "Increase";

		String ServiceId7 = "S14";
		String DeviceId7 = findUtil.findUnderid(gree.hashCode(), idmaps);
		String RutimeDeviceId7 = String.valueOf(gree.hashCode());
		String DName7 = "Gree";
		String CType7 = "temperature";
		String Effect7 = "Monitor";

		Service coolService = new Service();
		Service coolS = (Service) initConcept.initService(ServiceId, DeviceId, RutimeDeviceId, DName, CType, Effect,
				coolService);

		Service coolService2 = new Service();
		Service coolS2 = (Service) initConcept.initService(ServiceId2, DeviceId2, RutimeDeviceId2, DName2, CType2,
				Effect2, coolService2);

		Service upService3 = new Service();
		Service upS3 = (Service) initConcept.initService(ServiceId3, DeviceId3, RutimeDeviceId3, DName3, CType3,
				Effect3, upService3);

		Service assService = new Service();
		Service assS = (Service) initConcept.initService(ServiceId4, DeviceId4, RutimeDeviceId4, DName4, CType4,
				Effect4, assService);

		Service moniService = new Service();
		Service moniS = (Service) initConcept.initService(ServiceId5, DeviceId5, RutimeDeviceId5, DName5, CType5,
				Effect5, moniService);

		Service upsService4 = new Service();
		Service upS4 = (Service) initConcept.initService(ServiceId6, DeviceId6, RutimeDeviceId6, DName6, CType6,
				Effect6, upsService4);

		Service moniService2 = new Service();
		Service moniS2 = (Service) initConcept.initService(ServiceId7, DeviceId7, RutimeDeviceId7, DName7, CType7,
				Effect7, moniService2);

//		服务配置
		initConcept.serConfig(ndAirCondition, coolS, SerDevMaps, serMap);
		initConcept.serConfig(ndAirCondition, assS, SerDevMaps, serMap);
		initConcept.serConfig(ndAirCondition, upS4, SerDevMaps, serMap);
		initConcept.serConfig(ndAirCondition, moniS2, SerDevMaps, serMap);
		initConcept.serConfig(panasonic, coolS2, SerDevMaps, serMap);
		initConcept.serConfig(panasonic, upS3, SerDevMaps, serMap);
		initConcept.serConfig(panasonic, moniS, SerDevMaps, serMap);

		Services services = new Services();
		services.addlist(SerDevMaps);
		List<String> SerList = new ArrayList<>();
		SerList = services.list(false);

//		测试设置服务属性值，根据映射设置设备属性值
//		String SerId2 = "S11";
//		String Value2 = "On";
//		String SKey2 = "Status";
//		services.SetDevProperties(SerId2, Value2, SKey2, SerDevMaps, idmaps, idObjmaps, objMaps, serMap, contMap);

		// 位置生成
		String lName1 = "bedroom";
		String lId1 = "L1";
		Location l1 = new Location();

		String lName2 = "sittingroom";
		String lId2 = "L2";
		Location l2 = new Location();

		locIdNameMap.put(lName1, lId1);
		locIdNameMap.put(lName2, lId2);
		l1 = (Location) initConcept.initLocation(lId1, lName1, l1, objMaps, SerDevMaps, idmaps);
		l2 = (Location) initConcept.initLocation(lId2, lName2, l2, objMaps, SerDevMaps, idmaps);
		locationMap.put(l1.getLId(), l1);
		locationMap.put(l2.getLId(), l2);

		Locations ls = new Locations();
		ls.addlist(l1.getLId());
		ls.addlist(l2.getLId());
		List<String> LList = ls.list(false);
//		ls.ListProperties(l1.getLId(), locationMap);
//		ls.ListProperties(l2.getLId(), locationMap);

		String UName1 = "Jack";
		String UId1 = "U1";
		String ULName1 = "bedroom";
		List<String> uclist1 = new ArrayList<>();
		uclist1.add("C11");

		String UName2 = "Ben";
		String UId2 = "U2";
		String ULName2 = "sittingroom";
		List<String> uclist2 = new ArrayList<>();
		uclist2.add("C21");

		User u1 = new User();
		User u2 = new User();
		u1 = (User) initConcept.initUser(UName1, ULName1, UId1, u1, locIdNameMap, uclist1);
		u2 = (User) initConcept.initUser(UName2, ULName2, UId2, u2, locIdNameMap, uclist2);

//		System.out.println(u1.getContextList());

		userMap.put(u1.getUId(), u1);
		userMap.put(u2.getUId(), u2);
		userIdNameMap.put(UName1, UId1);
		userIdNameMap.put(UName2, UId2);

		Users users = new Users();
		users.addlist(u1.getUId());
		users.addlist(u2.getUId());

		List<String> UList = users.list(false);
//		列出用户的属性
//		for (String uid : UList) {
//			User tempUser = (User) userMap.get(uid);
//			users.ListProperties(tempUser.getUId(), userMap);
//		}

		LocatedIn.createLocatedIn(UList, LList, userMap, locationMap);
		LocatedIn.createLocatedIn(airCList, LList, uidMaps, locationMap);

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

//		服务与环境的绑定
		serConMap.put(CID2, moniS.getServiceId());
		serConMap.put(CID1, moniS2.getServiceId());
//		System.out.println(serConMap);
		c11 = (Context) initConcept.initContext(CUName1, CCType1, RMin1, RMax1, CID1, c11, userIdNameMap, userMap,
				serConMap, serMap);
		c21 = (Context) initConcept.initContext(CUName2, CCType2, RMin2, RMax2, CID2, c21, userIdNameMap, userMap,
				serConMap, serMap);

		contMap.put(c11.getCId(), c11);
		contMap.put(c21.getCId(), c21);
//		System.out.println(c11.getCId() + " " + c11.getCType() + " " + c11.getRMin() + " " + c11.getRMax()+" "+c11.getCValue());
		Contexts contexts = new Contexts();
		contexts.addlist(CID1);
		contexts.addlist(CID2);

//		contexts.list();
//		
//		for(String cid:contexts.list(true)) {
//			System.out.println("------------------");
//			contexts.ListProperties(cid, contMap,true);
//			
//		}

//		System.out.println(SerDevMaps);
//		System.out.println(idmaps);
//		String SerId = "S12";
//		String Value = "50";
//		String SKey = "Status";
//		services.SetDevProperties(SerId, Value, SKey, SerDevMaps, idmaps, idObjmaps, objMaps, serMap, contMap);

//		for (String si : SerList) {
//			System.out.println("---------------------------");
//			services.ListProperties(si, serMap);
//		}
//		services.ListProperties(SerId, serMap);

//		for(String cid:contexts.list()) {
//			System.out.println("------------------");
//			contexts.ListProperties(cid, contMap);
//			
//		}
		sleepUtil.Sleep();
		System.out.println("系统初始化结束。");
		sleepUtil.Sleep();
		System.out.println("测试开始");
		sleepUtil.Sleep();
		if (!cmdMaps.get("attribute").equals("none")) {
			TestCmd.testCmd(cmdMaps, services, SerDevMaps, idmaps, idObjmaps, objMaps, serMap, contMap);
		} else {
			System.out.println("这是开关操作");
			TestCmd.testCmd2(cmdMaps, airConditions,idmaps,idObjmaps,objMaps);
		}

	}



	public static void changeContext(Services services, Contexts contexts) {
//		services.list();
//		contexts.list();

		for (String sid : services.list(false)) {
			Service service = (Service) services.ListProperties(sid, serMap, false);

			for (String cid : contexts.list(false)) {
				Context context = (Context) contexts.ListProperties(cid, contMap, false);

				if (service.getLName().equals(context.getLName()) && service.getCType().equals(context.getCType())) {

					if ((service.getEffect().equals("Increase") || service.getEffect().equals("Reduce")
							|| service.getEffect().equals("Assign")) && service.getStatus().equals("on")) {
						context.setCValue(service.getSValue());
//						System.out.println(service.getServiceId() + context.getCId());
						context.setCValue(service.getSValue());

					}

				}

			}
		}

	}

	public static boolean judgeContext(Contexts contexts, Services services) {
		boolean status = true;

		for (String cid : contexts.list(false)) {
			Context context = (Context) contexts.ListProperties(cid, contMap, false);

			if (context.getCValue() > context.getRMax()) {
				System.out.println(context.getCValue());

			}

			if (context.getCValue() < context.getRMin()) {
				System.out.println(context.getCValue());
				for (String sid : services.list(false)) {
					Service service = new Service();
					service = (Service) services.ListProperties(sid, serMap, false);
					if (service.getLName().equals(context.getLName())
							&& service.getCType().equals(context.getCType())) {
						System.out.println(service.getServiceId() + service.getStatus() + service.getEffect());

					}
				}
			}

		}

		return status;
	}

	

	


	/**
	 * device 底层设备类名
	 * 
	 * @param device
	 */
	private static Object generate(String device) throws Exception {
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
