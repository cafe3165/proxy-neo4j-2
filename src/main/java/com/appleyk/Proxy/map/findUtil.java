package com.appleyk.Proxy.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.util.sleepUtil;
import com.appleyk.Proxy.virtualObejct.Service;
import com.appleyk.Proxy.virtualObejct.Services;

public class findUtil {
	// 根据运行时对象找底层设备对象
	public static Object findUnderDByRuntimeD(Object p, Map<Object, Object> objMaps) {
		Object dObject = null;
		for (Object o : objMaps.keySet()) {
			if (objMaps.get(o).hashCode() == p.hashCode()) {
				dObject = o;
			}
		}
		return dObject;
	}

	// 根据运行时对象id找底层设备对象id
	public static String findUnderid(int p, Map<String, String> idmaps) {
		String iString = String.valueOf(p);
		String d = "";
		for (String o : idmaps.keySet()) {

			if (idmaps.get(o).equals(iString)) {
				d = o;
			}

		}
		return d;

	}

	public static Map<String, String> findSer(Map<String, String> cmdMaps, Services services,
			Map<String, Object> serMap) throws InterruptedException {
		Map<String, String> doMap = new HashMap<>();
//			String sid = null;
//			System.out.println(cmdMaps);
		sleepUtil.Sleep();
		List<String> sList = services.list(false);
		System.out.println("列出当前所有服务：");
		System.out.println(sList);
		sleepUtil.Sleep();
		System.out.println("开始寻找符合条件的服务：");
		for (String i : sList) {
			sleepUtil.Sleep();
			System.out.println("当前服务为： " + i);
			sleepUtil.Sleep();
			Service tObject = new Service();
			tObject = (Service) services.ListProperties(i, serMap, false);
			String effectString = judge.judgeOperation(cmdMaps.get("operation"));
			if (tObject.getLName().equals(cmdMaps.get("location"))
					&& tObject.getCType().equals(cmdMaps.get("attribute"))
					&& tObject.getEffect().equals(effectString)) {
//					System.out.println(tObject.getServiceId());
//					services.ListProperties(i, serMap, true);
//					System.out.println("------------------------------");
//					sid=tObject.getServiceId();
				doMap.put("SerId", tObject.getServiceId());
				doMap.put("Value", judge.judgeType(cmdMaps));
				doMap.put("SKey", judge.judgeSkey(cmdMaps));
				sleepUtil.Sleep();
				System.out.println("已找到服务，对应id为：" + tObject.getServiceId());
				sleepUtil.Sleep();
				System.out.println("位置为：" + tObject.getLName());
				sleepUtil.Sleep();
				System.out.println("提供该服务的设备为：" + tObject.getDName());
				sleepUtil.Sleep();
				System.out.println("需要修改的属性值为：" + tObject.getCType());
				break;
			} else {
				System.out.println("服务" + i + "不符合条件。");
			}

		}

		return doMap;

	}

}
